
package smartreview.parser.tripadvisor;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the smartreview.parser.tripadvisor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: smartreview.parser.tripadvisor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ParserConfig }
     * 
     */
    public ParserConfig createParserConfig() {
        return new ParserConfig();
    }

    /**
     * Create an instance of {@link ParserConfig.ReviewCateMap }
     * 
     */
    public ParserConfig.ReviewCateMap createParserConfigReviewCateMap() {
        return new ParserConfig.ReviewCateMap();
    }

    /**
     * Create an instance of {@link ParserConfig.ReviewCateMap.Item }
     * 
     */
    public ParserConfig.ReviewCateMap.Item createParserConfigReviewCateMapItem() {
        return new ParserConfig.ReviewCateMap.Item();
    }

    /**
     * Create an instance of {@link ParserConfig.ReviewStringRules }
     * 
     */
    public ParserConfig.ReviewStringRules createParserConfigReviewStringRules() {
        return new ParserConfig.ReviewStringRules();
    }

    /**
     * Create an instance of {@link ParserConfig.RatingRule }
     * 
     */
    public ParserConfig.RatingRule createParserConfigRatingRule() {
        return new ParserConfig.RatingRule();
    }

    /**
     * Create an instance of {@link ParserConfig.ReviewCateMap.Item.Value }
     * 
     */
    public ParserConfig.ReviewCateMap.Item.Value createParserConfigReviewCateMapItemValue() {
        return new ParserConfig.ReviewCateMap.Item.Value();
    }

}
