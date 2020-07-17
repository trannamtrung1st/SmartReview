/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.parser.tripadvisor;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import smartreview.business.services.ParserInfoService;
import smartreview.data.models.ParserInfo;
import smartreview.helper.FileHelper;
import smartreview.helper.HttpHelper;
import smartreview.helper.XMLHelper;
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
    protected EntityManager entityManager;
    protected ParserInfoService parserInfoService;
    protected ParserInfo parserInfo;
    protected Set<String> businessLinks;

    public Parser(
            EntityManager entityManager,
            XmlParserConfig xmlParserConfig,
            ParserInfoService parserInfoService,
            ParserConfig parserConfig) {
        this.parserInfoService = parserInfoService;
        this.xmlParserConfig = xmlParserConfig;
        this.parserConfig = parserConfig;
        this.xpath = XMLHelper.getXPath();
        this.entityManager = entityManager;
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
        crawlData();
    }

    public void crawlData() throws Exception {
        WebDriver wd = HttpHelper.getWebDriver();
        String bUrl = parserConfig.getBusinessListUrl();
        wd.get(bUrl);
        String pSource = wd.getPageSource();
        String pageNum = "1";
        parseBusinessLinks(pSource, pageNum);
        try {
            String nextPageSelector = parserConfig.getNextPageCssSelector();
            WebElement nextPage = wd.findElement(By.cssSelector(nextPageSelector));
            while (nextPage != null) {
                nextPage.click();
                Wait<WebDriver> wait = new FluentWait<>(wd)
                        .withTimeout(Duration.ofSeconds(20))
                        .pollingEvery(Duration.ofSeconds(1))
                        .ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, ElementClickInterceptedException.class));
                waitForNextPage(wait, pageNum, nextPage);
                WebElement currentPage = wd.findElement(By.cssSelector(parserConfig.getCurrentPageCssSelector()));
                pageNum = currentPage.getText();
                pSource = wd.getPageSource();
                parseBusinessLinks(pSource, pageNum);
                nextPage = wd.findElement(By.cssSelector(nextPageSelector));
            }
        } catch (NoSuchElementException e) {
        }
    }

    protected void waitForNextPage(Wait<WebDriver> wait, String pageNum, WebElement nextPage) {
        wait.until((driver) -> {
            WebElement currentPage = driver.findElement(By.cssSelector(parserConfig.getCurrentPageCssSelector()));
            if (!currentPage.getText().equals(pageNum)) {
                return driver.findElements(By.cssSelector(parserConfig.getBusinessItemCssSelector())).size() > 0;
            }
            nextPage.click();
            return false;
        });
    }

    protected void parseBusinessLinks(String pageSource, String pageNum) throws Exception {
        System.out.println(String.format("Parse business list page %s ", pageNum));
        String content = preprocess(pageSource);
//            FileHelper.writeToFile(content, "temp.html");
        //parse DOM and use XPath to get links
        Document doc = XMLHelper.parseDOMFromString(content);
        NodeList linkNodes = (NodeList) xpath.evaluate(parserConfig.getBusinessLinksXPath(), doc, XPathConstants.NODESET);
        System.out.println("Links for page " + pageNum + ": " + linkNodes.getLength());
        for (int i = 0; i < linkNodes.getLength(); i++) {
            String link = linkNodes.item(i).getNodeValue();
            link = resolveFullUrl(link);
            businessLinks.add(link);
        }
        FileHelper.writeToFile(pageSource, "temp.html");
    }

    protected ParserInfo getNewParserInfo() {
        ParserInfo entity = new ParserInfo();
        entity.setParserBaseUrl(parserConfig.getBaseUrl());
        entity.setParserCode(parserConfig.getCode());
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
