
package smartreview.dtos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reviews">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="item" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="reviewCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="reviewText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reviews"
})
@XmlRootElement(name = "analyzeReviewModel")
public class AnalyzeReviewModel {

    @XmlElement(required = true)
    protected AnalyzeReviewModel.Reviews reviews;

    /**
     * Gets the value of the reviews property.
     * 
     * @return
     *     possible object is
     *     {@link AnalyzeReviewModel.Reviews }
     *     
     */
    public AnalyzeReviewModel.Reviews getReviews() {
        return reviews;
    }

    /**
     * Sets the value of the reviews property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnalyzeReviewModel.Reviews }
     *     
     */
    public void setReviews(AnalyzeReviewModel.Reviews value) {
        this.reviews = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="item" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="reviewCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="reviewText" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "item"
    })
    public static class Reviews {

        @XmlElement(required = true)
        protected List<AnalyzeReviewModel.Reviews.Item> item;

        /**
         * Gets the value of the item property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the item property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AnalyzeReviewModel.Reviews.Item }
         * 
         * 
         */
        public List<AnalyzeReviewModel.Reviews.Item> getItem() {
            if (item == null) {
                item = new ArrayList<AnalyzeReviewModel.Reviews.Item>();
            }
            return this.item;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="reviewCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="reviewText" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "reviewCode",
            "reviewText",
            "rating"
        })
        public static class Item {

            @XmlElement(required = true)
            protected String reviewCode;
            @XmlElement(required = true)
            protected String reviewText;
            protected double rating;

            /**
             * Gets the value of the reviewCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReviewCode() {
                return reviewCode;
            }

            /**
             * Sets the value of the reviewCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReviewCode(String value) {
                this.reviewCode = value;
            }

            /**
             * Gets the value of the reviewText property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReviewText() {
                return reviewText;
            }

            /**
             * Sets the value of the reviewText property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReviewText(String value) {
                this.reviewText = value;
            }

            /**
             * Gets the value of the rating property.
             * 
             */
            public double getRating() {
                return rating;
            }

            /**
             * Sets the value of the rating property.
             * 
             */
            public void setRating(double value) {
                this.rating = value;
            }

        }

    }

}
