/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.parser.yelp;

import java.io.File;
import javax.persistence.EntityManager;
import javax.xml.XMLConstants;
import javax.xml.transform.Templates;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.openqa.selenium.WebDriver;
import smartreview.business.Constants;
import smartreview.business.Settings;
import smartreview.business.services.BusinessService;
import smartreview.business.services.ParserInfoService;
import smartreview.business.services.ReviewCategoryService;
import smartreview.business.services.ReviewService;
import smartreview.data.EntityContext;
import smartreview.data.daos.BusinessDAO;
import smartreview.data.daos.BusinessReviewDAO;
import smartreview.data.daos.ParserInfoDAO;
import smartreview.data.daos.ReviewCategoryDAO;
import smartreview.data.models.ParserInfo;
import smartreview.helper.HttpHelper;
import smartreview.helper.XMLHelper;
import smartreview.xmlparser.XmlParserConfig;

/**
 *
 * @author TNT
 */
public class Entry {

    protected static final int DEFAULT_BG_THREAD_SLEEP = 5000;
    protected static WebDriver wDriver;

    public static void main(String[] args) throws Exception {
        ParserConfig parserConfig = XMLHelper.unmarshallDocFile("parser-config.xml", ObjectFactory.class);
        Settings.baseApiUrl = parserConfig.getBaseApiUrl();
        System.setProperty("webdriver.chrome.driver", parserConfig.getDriverPath());
        XmlParserConfig xmlParserConfig = XMLHelper.unmarshallDocFile("xml-parser-config.xml", smartreview.xmlparser.ObjectFactory.class);
        wDriver = HttpHelper.getWebDriver();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Templates bTemplate = XMLHelper.getTemplates("business-item.xsl");
        Schema bSchema = schemaFactory.newSchema(new File("business-item.xsd"));
        Validator bValidator = bSchema.newValidator();
        Templates rTemplate = XMLHelper.getTemplates("reviews.xsl");
        Schema rSchema = schemaFactory.newSchema(new File("reviews.xsd"));
        Validator rValidator = rSchema.newValidator();

        Thread thread = startCheckCommandThread(parserConfig.getCode());
        try (EntityContext context = EntityContext.newInstance()) {
            EntityManager em = context.getEntityManager();
            ParserInfoService parserInfoService = new ParserInfoService(em, new ParserInfoDAO(em));
            ParserInfo pInfo = parserInfoService.findParserInfoByCode(parserConfig.getCode(), true);
            
            String output = "START PARSER";
            em.getTransaction().begin();
            System.out.println(output);
            parserInfoService.writeOutput(pInfo, output);
            em.getTransaction().commit();
            
            BusinessService businessService = new BusinessService(em, new BusinessDAO(em));
            ReviewService reviewService = new ReviewService(em, new BusinessReviewDAO(em), new ReviewCategoryDAO(em));
            ReviewCategoryService reviewCategoryService = new ReviewCategoryService(em, new ReviewCategoryDAO(em));
            Parser parser = new Parser(reviewCategoryService, reviewService, bTemplate, bValidator, rTemplate, rValidator,
                    businessService, em, xmlParserConfig, parserInfoService, wDriver, parserConfig);
            parser.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        wDriver.close();
        if (thread.isAlive()) {
            thread.interrupt();
        }
    }

    public static Thread startCheckCommandThread(String parserCode) {
        Thread thread = new Thread(() -> {
            while (true) {
                try (EntityContext context = EntityContext.newInstance()) {
                    Thread.sleep(DEFAULT_BG_THREAD_SLEEP);
                    EntityManager em = context.getEntityManager();
                    ParserInfoService pService = new ParserInfoService(em, new ParserInfoDAO(em));
                    ParserInfo pInfo = pService.findParserInfoByCode(parserCode, true);
                    String output = "--------------- CHECK COMMAND --------------------: " + pInfo.getCurrentCommand();
                    em.getTransaction().begin();
                    System.out.println(output);
                    pService.writeOutput(pInfo, output);
                    em.getTransaction().commit();
                    if (pInfo.getCurrentCommand().equals(Constants.COMMAND_STOP)) {
                        break;
                    }
                } catch (Exception ex) {
                    break;
                }
            }
            wDriver.close();
            wDriver.quit();
            System.exit(0);
        });
        thread.start();
        return thread;
    }
}
