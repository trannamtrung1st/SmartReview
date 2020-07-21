package smartreview.parser.yelp.models.xmlschema;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the smartreview.parser.yelp.models.xmlschema
 * package.
 * <p>
 * An ObjectFactoryOld allows you to programatically construct new instances of
 * the Java representation for XML content. The Java representation of XML
 * content can consist of schema derived interfaces and classes representing the
 * binding of schema type definitions, element declarations and model groups.
 * Factory methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactoryOld {

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package:
     * smartreview.parser.yelp.models.xmlschema
     *
     */
    public ObjectFactoryOld() {
    }

    /**
     * Create an instance of {@link Reviews }
     *
     */
    public Reviews createReviews() {
        return new Reviews();
    }

    /**
     * Create an instance of {@link Reviews.Item }
     *
     */
    public Reviews.Item createReviewsItem() {
        return new Reviews.Item();
    }

    /**
     * Create an instance of {@link BusinessItem }
     *
     */
    public BusinessItem createBusinessItem() {
        return new BusinessItem();
    }

    /**
     * Create an instance of {@link BusinessItem.Images }
     *
     */
    public BusinessItem.Images createBusinessItemImages() {
        return new BusinessItem.Images();
    }

}
