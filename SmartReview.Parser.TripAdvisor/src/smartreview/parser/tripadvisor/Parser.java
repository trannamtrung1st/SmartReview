/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.parser.tripadvisor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import javax.persistence.EntityManager;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import smartreview.business.services.BusinessService;
import smartreview.business.services.ParserInfoService;
import smartreview.data.models.Business;
import smartreview.data.models.ParserInfo;
import smartreview.helper.FileHelper;
import smartreview.helper.HttpHelper;
import smartreview.helper.RegexHelper;
import smartreview.helper.XMLHelper;
import smartreview.parser.tripadvisor.models.xmlschema.BusinessItem;
import smartreview.xmlparser.XmlParserConfig;
import smartreview.xmlparser.preprocessor.HtmlPreprocessor;

/**
 *
 * @author TNT
 */
public class Parser {

    protected XmlParserConfig xmlParserConfig;
    protected ParserConfig parserConfig;
    protected XPath xpath;
    protected Templates businessTemplate;
    protected Validator businessValidator;
    protected EntityManager entityManager;
    protected ParserInfoService parserInfoService;
    protected ParserInfo parserInfo;
    protected Set<String> businessLinks;
    protected WebDriver webDriver;
    protected BusinessService businessService;

    public Parser(
            Templates businessTemplate,
            Validator businessValidator,
            BusinessService businessService,
            EntityManager entityManager,
            XmlParserConfig xmlParserConfig,
            ParserInfoService parserInfoService,
            WebDriver webDriver,
            ParserConfig parserConfig) {
        this.businessTemplate = businessTemplate;
        this.businessValidator = businessValidator;
        this.parserInfoService = parserInfoService;
        this.xmlParserConfig = xmlParserConfig;
        this.parserConfig = parserConfig;
        this.xpath = XMLHelper.getXPath();
        this.entityManager = entityManager;
        this.webDriver = webDriver;
        this.businessService = businessService;
        businessLinks = new HashSet<>();
    }

    public void start() throws Exception {
        String parserCode = parserConfig.getCode();
        parserInfo = parserInfoService.findParserInfoByCode(parserCode);
        if (parserInfo == null) {
            parserInfo = getNewParserInfo();
            entityManager.getTransaction().begin();
            parserInfo = parserInfoService.createParserInfo(parserInfo);
            entityManager.getTransaction().commit();
        }
        crawlBusinessLinks();
        System.out.println("Crawled links count: " + businessLinks.size());
        parseBusinessInfoAndReview();
        webDriver.close();
    }

    protected void crawlBusinessLinks() throws Exception {
        String bUrl = parserConfig.getBusinessListUrl();
        webDriver.get(bUrl);
        Integer pageNum = 1;
        String pSource;
        if (pageNeedCrawl(pageNum)) {
            pSource = webDriver.getPageSource();
            parseBusinessLinks(pSource, pageNum);
        }
        try {
            String nextPageSelector = parserConfig.getNextPageCssSelector();
            WebElement nextPage = webDriver.findElement(By.cssSelector(nextPageSelector));
            while (nextPage != null && pageNum < parserInfo.getToPage()) {
                nextPage.click();
                Wait<WebDriver> wait = new FluentWait<>(webDriver)
                        .withTimeout(Duration.ofSeconds(20))
                        .pollingEvery(Duration.ofSeconds(1))
                        .ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, ElementClickInterceptedException.class));
                waitForNextPage(wait, pageNum, nextPage);
                WebElement currentPage = webDriver.findElement(By.cssSelector(parserConfig.getCurrentPageCssSelector()));
                pageNum = Integer.parseInt(currentPage.getText());
                if (pageNeedCrawl(pageNum)) {
                    pSource = webDriver.getPageSource();
                    parseBusinessLinks(pSource, pageNum);
                }
                nextPage = webDriver.findElement(By.cssSelector(nextPageSelector));
            }
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean pageNeedCrawl(Integer pageNum) {
        return pageNum >= parserInfo.getFromPage() && pageNum <= parserInfo.getToPage();
    }

    protected void waitForNextPage(Wait<WebDriver> wait, Integer pageNum, WebElement nextPage) {
        wait.until((driver) -> {
            WebElement currentPage = driver.findElement(By.cssSelector(parserConfig.getCurrentPageCssSelector()));
            if (!currentPage.getText().equals(pageNum.toString())) {
                return driver.findElements(By.cssSelector(parserConfig.getBusinessItemCssSelector())).size() > 0;
            }
            nextPage.click();
            return false;
        });
    }

    protected void parseBusinessLinks(String pageSource, int pageNum) throws Exception {
        System.out.println(String.format("Parse business list page %d ", pageNum));
        String content = preprocess(pageSource);
//            FileHelper.writeToFile(content, "temp.html");
        //parse DOM and use XPath to get links
        Document doc = XMLHelper.parseDOMFromString(content);
        NodeList linkNodes = (NodeList) xpath.evaluate(parserConfig.getBusinessLinksXPath(), doc, XPathConstants.NODESET);
        int oldLength = businessLinks.size();
        System.out.println("Links for page " + pageNum + ": " + linkNodes.getLength());
        for (int i = 0; i < linkNodes.getLength(); i++) {
            String link = linkNodes.item(i).getNodeValue();
            link = resolveFullUrl(link);
            businessLinks.add(link);
        }
        System.out.println("Added links: " + (businessLinks.size() - oldLength));
        FileHelper.writeToFile(pageSource, "temp.html");
    }

    protected void parseBusinessInfoAndReview() throws Exception {
        for (String businessLink : businessLinks) {
            String code = getCodeFromLink(businessLink);
            System.out.println(code);
            boolean existed = businessService.businessCodeExists(code);
            if (existed && parserInfo.getRefreshExistedData() == true) {
                //update business info
            } else {
                //create business info
                webDriver.get(businessLink);
                String pageSource = webDriver.getPageSource();
                System.out.println("Start parsing page: " + businessLink);
                pageSource = preprocess(pageSource);
//                FileHelper.writeToFile(pageContent, "temp.html");
                String modelXml = transform(businessLink, pageSource, code);
                validateBusinessXml(modelXml);
//                FileHelper.writeToFile(modelXml, "temp.xml");
                BusinessItem bItem = XMLHelper.unmarshallDocXml(modelXml, smartreview.parser.tripadvisor.models.xmlschema.ObjectFactory.class);
                System.out.println(bItem.getName());
                processBusinessItem(bItem);
                System.out.println("Finish parsing page: " + businessLink);
                System.out.println("------------------------");
            }
        }
    }

    protected void validateBusinessXml(String modelXml) throws SAXException, IOException {
        businessValidator.validate(new StreamSource(new StringReader(modelXml)));
    }

    protected String transform(String pageUrl, String pageContent, String code) throws TransformerConfigurationException, FileNotFoundException, TransformerException, Exception {
        // Use the template to create a transformer
        Transformer xformer = businessTemplate.newTransformer();
        xformer.setParameter("url", pageUrl);
        xformer.setParameter("code", code);
        // Prepare the input and output files
        Source source = new StreamSource(new StringReader(pageContent));
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        // Apply the xsl file to the source file and write the result
        // to the output file
        xformer.transform(source, result);
        return writer.toString();
    }

    protected void processBusinessItem(BusinessItem bItem) {
    }

    protected String getCodeFromLink(String url) throws Exception {
        Matcher matcher = RegexHelper.matcherDotAll(url, parserConfig.getCodeFromUrlRegex());
        String code = null;
        if (matcher.find()) {
            code = matcher.group(1);
        } else {
            throw new Exception("Code not found");
        }
        return code;
    }

    protected ParserInfo getNewParserInfo() {
        ParserInfo entity = new ParserInfo();
        entity.setParserBaseUrl(parserConfig.getBaseUrl());
        entity.setParserCode(parserConfig.getCode());
        entity.setFromPage((int) parserConfig.getDefaultFromPage());
        entity.setToPage((int) parserConfig.getDefaultToPage());
        entity.setRefreshExistedData(false);
        return entity;
    }

    protected String preprocess(String content) throws IOException {
        HtmlPreprocessor processor = new HtmlPreprocessor(xmlParserConfig);
        content = processor.refineHtml(content);
        return content;
    }

    protected String resolveFullUrl(String relPath) {
        return relPath.startsWith("http") ? relPath
                : ((parserConfig.getBaseUrl() + (relPath.startsWith("/") ? relPath : "/" + relPath)));
    }

}
