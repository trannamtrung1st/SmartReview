/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.parser.tripadvisor;

import java.io.File;
import javax.persistence.EntityManager;
import javax.xml.XMLConstants;
import javax.xml.transform.Templates;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.openqa.selenium.WebDriver;
import smartreview.business.services.BusinessService;
import smartreview.business.services.ParserInfoService;
import smartreview.data.EntityContext;
import smartreview.data.daos.BusinessDAO;
import smartreview.data.daos.ParserInfoDAO;
import smartreview.helper.HttpHelper;
import smartreview.helper.XMLHelper;
import smartreview.xmlparser.XmlParserConfig;

/**
 *
 * @author TNT
 */
public class Entry {

    public static void main(String[] args) throws Exception {
        ParserConfig parserConfig = XMLHelper.unmarshallDocFile("parser-config.xml", ObjectFactory.class);
        System.setProperty("webdriver.chrome.driver", parserConfig.getDriverPath());
        XmlParserConfig xmlParserConfig = XMLHelper.unmarshallDocFile("xml-parser-config.xml", smartreview.xmlparser.ObjectFactory.class);
        WebDriver wDriver = HttpHelper.getWebDriver();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Templates bTemplate = XMLHelper.getTemplates("business-item.xsl");
        Schema bSchema = schemaFactory.newSchema(new File("business-item.xsd"));
        Validator bValidator = bSchema.newValidator();

        Templates rTemplate = XMLHelper.getTemplates("reviews.xsl");
        Schema rSchema = schemaFactory.newSchema(new File("reviews.xsd"));
        Validator rValidator = rSchema.newValidator();

        try (EntityContext context = EntityContext.newInstance()) {
            EntityManager em = context.getEntityManager();
            ParserInfoService parserInfoService = new ParserInfoService(em, new ParserInfoDAO(em));
            BusinessService businessService = new BusinessService(em, new BusinessDAO(em));
            Parser parser = new Parser(bTemplate, bValidator, rTemplate, rValidator,
                    businessService, em, xmlParserConfig, parserInfoService, wDriver, parserConfig);
            parser.start();
        }
    }
}
