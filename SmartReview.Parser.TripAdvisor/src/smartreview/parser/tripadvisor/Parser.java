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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
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
import smartreview.business.services.ReviewCategoryService;
import smartreview.business.services.ReviewService;
import smartreview.data.models.Business;
import smartreview.data.models.BusinessImage;
import smartreview.data.models.BusinessReview;
import smartreview.data.models.ParserInfo;
import smartreview.data.models.ReviewCategory;
import smartreview.helper.DateHelper;
import smartreview.helper.FileHelper;
import smartreview.helper.HttpHelper;
import smartreview.helper.RegexHelper;
import smartreview.helper.XMLHelper;
import smartreview.parser.tripadvisor.models.xmlschema.BusinessItem;
import smartreview.parser.tripadvisor.models.xmlschema.Reviews;
import smartreview.xmlparser.XmlParserConfig;
import smartreview.xmlparser.preprocessor.HtmlPreprocessor;

/**
 *
 * @author TNT
 */
public class Parser {

    protected final int WAIT_NEXT_PAGE_SECS_B_LIST = 5;
    protected final int DEFAULT_WD_WAIT_TIMEOUT = 20;
    protected final int DEFAULT_POLLING_SECS = 1;
    protected final int WAIT_FOR_ACTION = 2;
    protected final int MAX_TRY_CLICK = 10;
    protected final int DEFAULT_MAX_REVIEW_PAGES = 7;

    protected XmlParserConfig xmlParserConfig;
    protected ParserConfig parserConfig;
    protected XPath xpath;
    protected Templates businessTemplate;
    protected Validator businessValidator;
    protected Templates reviewsTemplate;
    protected Validator reviewsValidator;
    protected EntityManager entityManager;
    protected ParserInfoService parserInfoService;
    protected ParserInfo parserInfo;
    protected Set<String> businessLinks;
    protected WebDriver webDriver;
    protected BusinessService businessService;
    protected ReviewService reviewService;
    protected ReviewCategoryService reviewCategoryService;

    public Parser(
            ReviewCategoryService reviewCategoryService,
            ReviewService reviewService,
            Templates businessTemplate,
            Validator businessValidator,
            Templates reviewsTemplate,
            Validator reviewsValidator,
            BusinessService businessService,
            EntityManager entityManager,
            XmlParserConfig xmlParserConfig,
            ParserInfoService parserInfoService,
            WebDriver webDriver,
            ParserConfig parserConfig) {
        this.reviewCategoryService = reviewCategoryService;
        this.reviewService = reviewService;
        this.businessTemplate = businessTemplate;
        this.businessValidator = businessValidator;
        this.reviewsTemplate = reviewsTemplate;
        this.reviewsValidator = reviewsValidator;
        this.parserInfoService = parserInfoService;
        this.xmlParserConfig = xmlParserConfig;
        this.parserConfig = parserConfig;
        this.xpath = XMLHelper.getXPath();
        this.entityManager = entityManager;
        this.webDriver = webDriver;
        this.businessService = businessService;
        this.businessLinks = new HashSet<>();
    }

    public void start() throws Exception {
        init();
        crawlBusinessLinks();
        System.out.println("Crawled links count: " + businessLinks.size());
        parseBusinessInfoAndReview();
        webDriver.close();
    }

    protected void init() {
        String parserCode = parserConfig.getCode();
        parserInfo = parserInfoService.findParserInfoByCode(parserCode);
        if (parserInfo == null) {
            parserInfo = getNewParserInfo();
            entityManager.getTransaction().begin();
            parserInfo = parserInfoService.createParserInfo(parserInfo);
            entityManager.getTransaction().commit();
        }
        if (reviewCategoryService.anyExisted()) {
            return;
        }
        entityManager.getTransaction().begin();
        parserConfig.getReviewCateMap().getItem().forEach((t) -> {
            ReviewCategory entity = new ReviewCategory();
            entity.setCode(t.getValue().getCode());
            entity.setName(t.getValue().getName());
            reviewCategoryService.createReviewCategory(entity);
        });
        entityManager.getTransaction().commit();
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
                waitForNextPage(pageNum, nextPage, WAIT_NEXT_PAGE_SECS_B_LIST);
                pageNum++;
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

    protected void waitForNextPage(Integer pageNum, WebElement nextPage, String checkVisibleSelector) {
        Wait<WebDriver> wait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(DEFAULT_WD_WAIT_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(DEFAULT_POLLING_SECS))
                .ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, ElementClickInterceptedException.class));
        wait.until((driver) -> {
            WebElement currentPage = driver.findElement(By.cssSelector(parserConfig.getCurrentPageCssSelector()));
            if (!currentPage.getText().equals(pageNum.toString())) {
                return checkVisibleSelector != null ? driver.findElements(By.cssSelector(checkVisibleSelector)).size() > 0 : true;
            }
            nextPage.click();
            return false;
        });
    }

    protected void waitForNextPage(Integer pageNum, WebElement nextPage, Integer waitAtLeastSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(DEFAULT_WD_WAIT_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(DEFAULT_POLLING_SECS))
                .ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class, ElementClickInterceptedException.class));
        wait.until(new Function<WebDriver, Object>() {
            int count = 0;

            @Override
            public Object apply(WebDriver driver) {
                count++;
                WebElement currentPage = driver.findElement(By.cssSelector(parserConfig.getCurrentPageCssSelector()));
                if (!currentPage.getText().equals(pageNum.toString())) {
                    return count > waitAtLeastSeconds;
                }
                nextPage.click();
                return false;
            }
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
            String businessXml;
            try {
                String code = getCodeFromLink(businessLink);
                System.out.println(code);
                boolean existed = businessService.businessCodeExists(code);
                if (existed) {
                    if (parserInfo.getRefreshExistedData() == true) {
                        //update business info
                    }
                } else {
                    //create business info
                    webDriver.get(businessLink);
                    webDriver.manage().timeouts().implicitlyWait(WAIT_FOR_ACTION, TimeUnit.SECONDS);
                    String pageSource = webDriver.getPageSource();
                    System.out.println("Start parsing page: " + businessLink);
                    pageSource = preprocess(pageSource);
//                FileHelper.writeToFile(pageContent, "temp.html");
                    businessXml = transformBusiness(businessLink, pageSource, code);
                    validateBusinessXml(businessXml);
//                FileHelper.writeToFile(modelXml, "temp.xml");
                    BusinessItem bItem = XMLHelper.unmarshallDocXml(businessXml, smartreview.parser.tripadvisor.models.xmlschema.ObjectFactory.class);
                    System.out.println(bItem.getName());
                    Business bEntity = convertToBusinessEntity(bItem);
                    Map<String, BusinessReview> bReviews = parseAllBusinessReviews(pageSource, bEntity);
                    insertToDb(bEntity, bReviews.values());
                    System.out.println("Finish parsing page: " + businessLink);
                    System.out.println("------------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void validateBusinessXml(String modelXml) throws SAXException, IOException {
        businessValidator.validate(new StreamSource(new StringReader(modelXml)));
    }

    protected void validateReviewsXml(String modelXml) throws SAXException, IOException {
        reviewsValidator.validate(new StreamSource(new StringReader(modelXml)));
    }

    protected String transformBusiness(String pageUrl, String pageContent, String code) throws TransformerConfigurationException, FileNotFoundException, TransformerException, Exception {
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

    protected String transformReviews(String pageContent) throws TransformerConfigurationException, FileNotFoundException, TransformerException, Exception {
        // Use the template to create a transformer
        Transformer xformer = reviewsTemplate.newTransformer();
        // Prepare the input and output files
        Source source = new StreamSource(new StringReader(pageContent));
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        // Apply the xsl file to the source file and write the result
        // to the output file
        xformer.transform(source, result);
        return writer.toString();
    }

    protected Business convertToBusinessEntity(BusinessItem bItem) throws Exception {
        Business entity = new Business();
        entity.setAddress(bItem.getAddress());
        List<BusinessImage> bImages = new ArrayList<>();
        bItem.getImages().getItem().forEach((img) -> {
            BusinessImage e = new BusinessImage();
            e.setBusinessId(entity);
            e.setImageUrl(img);
            bImages.add(e);
        });
        entity.setBusinessImageCollection(bImages);
        entity.setCode(bItem.getCode());
        entity.setDetailUrl(bItem.getDetailUrl());
        entity.setFromPage(parserInfo.getParserBaseUrl());
        entity.setName(bItem.getName());
        entity.setPhone(bItem.getPhone());
        entity.setRating(bItem.getRating());

        String reviewStr = bItem.getTotalReview();
        String reviewRegex = parserConfig.getReviewStringRules().getRegex();
        String removeInReviewStr = parserConfig.getReviewStringRules().getRemove();
        Matcher matcher = RegexHelper.matcherDotAll(reviewStr, reviewRegex);
        if (matcher.find()) {
            reviewStr = matcher.group(1).replace(removeInReviewStr, "");
        } else {
            throw new Exception("Code not found");
        }
        Integer totalReviews = Integer.parseInt(reviewStr);
        entity.setTotalReview(totalReviews);
        return entity;
    }

    protected Map<String, BusinessReview> parseAllBusinessReviews(String pageSource, Business bEntity) throws Exception {
        Map<String, BusinessReview> entities = new HashMap<>();
        try {
            WebElement nextPage;
            Integer pageNum = 1;
            while (pageNum <= parserInfo.getMaxParsedReviewsPage()) {
                try {
                    //show full content
                    List<WebElement> mores = webDriver.findElements(By.xpath(parserConfig.getMoresBtnXPath()));
                    while (mores.size() > 0) {
                        try {
                            for (WebElement more : mores) {
                                more.click();
                                webDriver.manage().timeouts().implicitlyWait(WAIT_FOR_ACTION, TimeUnit.SECONDS);
                            }
                        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                            mores = webDriver.findElements(By.xpath(parserConfig.getMoresBtnXPath()));
                        }
                    }

                } catch (NoSuchElementException e) {
                    System.out.println("No more");
                }
                pageSource = webDriver.getPageSource();
                pageSource = preprocess(pageSource);
                FileHelper.writeToFile(pageSource, "temp.html");
                try {
                    addReviews(entities, pageSource, bEntity);
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                int tryClick = 0;
                while (tryClick++ < MAX_TRY_CLICK) {
                    try {
                        nextPage = webDriver.findElement(By.cssSelector(parserConfig.getNextPageCssSelector()));
                        nextPage.click();
                        waitForNextPage(pageNum, nextPage, WAIT_FOR_ACTION);
                        tryClick = MAX_TRY_CLICK;
                    } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                    }
                }
                pageNum++;
            }
        } catch (NoSuchElementException e) {
            System.out.println("No more");
        }
        reviewService.analyzeReviews(entities, 0.2f);
        bEntity.setBusinessReviewCollection(entities.values());
        return entities;
    }

    protected void addReviews(Map<String, BusinessReview> entities, String currentPageSource, Business bEntity) throws Exception {
        FileHelper.writeToFile(currentPageSource, "temp.html");
        String reviewxXml = transformReviews(currentPageSource);
        FileHelper.writeToFile(reviewxXml, "temp.xml");
        validateReviewsXml(reviewxXml);
        Reviews reviewsItem = XMLHelper.unmarshallDocXml(reviewxXml, smartreview.parser.tripadvisor.models.xmlschema.ObjectFactory.class);
        Integer size = reviewsItem.getCodes().getItem().size();
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            BusinessReview bR = new BusinessReview();
            bR.setBusinessId(bEntity);
            String code = reviewsItem.getCodes().getItem().get(i);
            bR.setCode(code);

            String rClasses = reviewsItem.getRatingClasses().getItem().get(i);
            String rStrRegex = parserConfig.getRatingRule().getRegex();
            Matcher matcher = RegexHelper.matcherDotAll(rClasses, rStrRegex);
            String rStr;
            if (matcher.find()) {
                rStr = matcher.group(1);
            } else {
                throw new Exception("Code not found");
            }
            Integer div = (int) parserConfig.getRatingRule().getDiv();
            Double rating = Double.parseDouble(rStr) / div;
            bR.setRating(rating);

            String reviewContent = reviewsItem.getReviewContents().getItem().get(i);
            bR.setReviewContent(reviewContent);

            String rDateStr = reviewsItem.getDates().getItem().get(i);
            Date rDate = DateHelper.convertToJavaDate(parserConfig.getReviewDateFormat(), rDateStr);
            bR.setReviewDate(rDate);

            String rTitle = reviewsItem.getReviewTitles().getItem().get(i);
            bR.setTitle(rTitle);

            String uImg = reviewsItem.getUserImages().getItem().get(i);
            bR.setUserImage(uImg);

            String uName = reviewsItem.getUsernames().getItem().get(i);
            bR.setUsername(uName);
            System.out.println(rTitle);
            entities.put(bR.getCode(), bR);
        }
    }

    protected void insertToDb(Business bEntity, Collection<BusinessReview> bReviews) {
        entityManager.getTransaction().begin();
        businessService.createBusiness(bEntity);
        entityManager.getTransaction().commit();
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
        entity.setMaxParsedReviewsPage(DEFAULT_MAX_REVIEW_PAGES);
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
