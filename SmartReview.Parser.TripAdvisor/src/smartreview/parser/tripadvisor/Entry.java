/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.parser.tripadvisor;

import java.io.File;
import javax.persistence.EntityManager;
import smartreview.business.services.ParserInfoService;
import smartreview.data.EntityContext;
import smartreview.data.daos.ParserInfoDAO;
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
        try (EntityContext context = EntityContext.newInstance()) {
            EntityManager em = context.getEntityManager();
            ParserInfoService parserInfoService = new ParserInfoService(em, new ParserInfoDAO(em));
            Parser parser = new Parser(em, xmlParserConfig, parserInfoService, parserConfig);
            parser.start();
        }
    }
}
