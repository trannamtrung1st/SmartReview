package smartreview.parser.yelp.models.xmlschema;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * smartreview.parser.tripadvisor.models.xmlschema package.
 * <p>
 An ObjectFactory allows you to programatically construct new instances of
 the Java representation for XML content. The Java representation of XML
 content can consist of schema derived interfaces and classes representing the
 binding of schema type definitions, element declarations and model groups.
 Factory methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package:
     * smartreview.parser.tripadvisor.models.xmlschema
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Reviews }
     *
     */
    public Reviews createReviews() {
        return new Reviews();
    }

    /**
     * Create an instance of {@link Reviews.Codes }
     *
     */
    public Reviews.Codes createReviewsCodes() {
        return new Reviews.Codes();
    }

    /**
     * Create an instance of {@link Reviews.Dates }
     *
     */
    public Reviews.Dates createReviewsDates() {
        return new Reviews.Dates();
    }

    /**
     * Create an instance of {@link Reviews.RatingClasses }
     *
     */
    public Reviews.RatingClasses createReviewsRatingClasses() {
        return new Reviews.RatingClasses();
    }

    /**
     * Create an instance of {@link Reviews.ReviewTitles }
     *
     */
    public Reviews.ReviewTitles createReviewsReviewTitles() {
        return new Reviews.ReviewTitles();
    }

    /**
     * Create an instance of {@link Reviews.ReviewContents }
     *
     */
    public Reviews.ReviewContents createReviewsReviewContents() {
        return new Reviews.ReviewContents();
    }

    /**
     * Create an instance of {@link Reviews.Usernames }
     *
     */
    public Reviews.Usernames createReviewsUsernames() {
        return new Reviews.Usernames();
    }

    /**
     * Create an instance of {@link Reviews.UserImages }
     *
     */
    public Reviews.UserImages createReviewsUserImages() {
        return new Reviews.UserImages();
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
