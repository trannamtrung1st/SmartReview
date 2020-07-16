/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.parser.tripadvisor;

import javax.persistence.EntityManager;
import javax.xml.xpath.XPath;
import smartreview.business.services.ParserInfoService;
import smartreview.data.models.ParserInfo;
import smartreview.helper.XMLHelper;
import smartreview.xmlparser.XmlParserConfig;

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
    }

    public void start() {
        String parserCode = parserConfig.getCode();
        parserInfo = parserInfoService.findParserInfoByCode(parserCode);
        if (parserInfo == null) {
            parserInfo = getNewParserInfo();
            entityManager.getTransaction().begin();
            parserInfo = parserInfoService.createParserInfo(parserInfo);
            entityManager.getTransaction().commit();
        }
    }

    protected ParserInfo getNewParserInfo() {
        ParserInfo entity = new ParserInfo();
        entity.setParserBaseUrl(parserConfig.getBaseUrl());
        entity.setParserCode(parserConfig.getCode());
        return entity;
    }

}
