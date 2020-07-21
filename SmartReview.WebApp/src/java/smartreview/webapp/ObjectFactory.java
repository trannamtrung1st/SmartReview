package smartreview.webapp;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the smartreview.webapp package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: smartreview.webapp
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link XmlParserConfig }
     *
     */
    public XmlParserConfig createXmlParserConfig() {
        return new XmlParserConfig();
    }

    /**
     * Create an instance of {@link XmlParserConfig.AttrReplacements }
     *
     */
    public XmlParserConfig.AttrReplacements createXmlParserConfigAttrReplacements() {
        return new XmlParserConfig.AttrReplacements();
    }

    /**
     * Create an instance of {@link XmlParserConfig.OpenBracketReplacements }
     *
     */
    public XmlParserConfig.OpenBracketReplacements createXmlParserConfigOpenBracketReplacements() {
        return new XmlParserConfig.OpenBracketReplacements();
    }

    /**
     * Create an instance of {@link XmlParserConfig.Miscs }
     *
     */
    public XmlParserConfig.Miscs createXmlParserConfigMiscs() {
        return new XmlParserConfig.Miscs();
    }

    /**
     * Create an instance of {@link XmlParserConfig.AttrReplacements.Replacement
     * }
     *
     */
    public XmlParserConfig.AttrReplacements.Replacement createXmlParserConfigAttrReplacementsReplacement() {
        return new XmlParserConfig.AttrReplacements.Replacement();
    }

    /**
     * Create an instance of {@link XmlParserConfig.OpenBracketReplacements.Replacement
     * }
     *
     */
    public XmlParserConfig.OpenBracketReplacements.Replacement createXmlParserConfigOpenBracketReplacementsReplacement() {
        return new XmlParserConfig.OpenBracketReplacements.Replacement();
    }

    /**
     * Create an instance of {@link WebConfig }
     *
     */
    public WebConfig createWebConfig() {
        return new WebConfig();
    }

    /**
     * Create an instance of {@link WebConfig.Parsers }
     *
     */
    public WebConfig.Parsers createWebConfigParsers() {
        return new WebConfig.Parsers();
    }

    /**
     * Create an instance of {@link WebConfig.ReviewCateMap }
     *
     */
    public WebConfig.ReviewCateMap createWebConfigReviewCateMap() {
        return new WebConfig.ReviewCateMap();
    }

    /**
     * Create an instance of {@link WebConfig.ReviewCateMap.Item }
     *
     */
    public WebConfig.ReviewCateMap.Item createWebConfigReviewCateMapItem() {
        return new WebConfig.ReviewCateMap.Item();
    }

    /**
     * Create an instance of {@link WebConfig.Parsers.Item }
     *
     */
    public WebConfig.Parsers.Item createWebConfigParsersItem() {
        return new WebConfig.Parsers.Item();
    }

    /**
     * Create an instance of {@link WebConfig.ReviewCateMap.Item.Value }
     *
     */
    public WebConfig.ReviewCateMap.Item.Value createWebConfigReviewCateMapItemValue() {
        return new WebConfig.ReviewCateMap.Item.Value();
    }

}
