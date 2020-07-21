
package smartreview.parser.yelp.models.xmlschema;

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
 *         &lt;element name="item" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="userImages" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="reviewDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="reviewContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
@XmlRootElement(name = "reviews")
public class Reviews {

    @XmlElement(required = true)
    protected List<Reviews.Item> item;

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
     * {@link Reviews.Item }
     * 
     * 
     */
    public List<Reviews.Item> getItem() {
        if (item == null) {
            item = new ArrayList<Reviews.Item>();
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
     *         &lt;element name="userImages" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="reviewDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="reviewContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
        "userImages",
        "username",
        "rating",
        "reviewDate",
        "reviewContent",
        "title"
    })
    public static class Item {

        @XmlElement(required = true)
        protected String userImages;
        @XmlElement(required = true)
        protected String username;
        protected double rating;
        @XmlElement(required = true)
        protected String reviewDate;
        @XmlElement(required = true)
        protected String reviewContent;
        @XmlElement(required = true)
        protected Object title;

        /**
         * Gets the value of the userImages property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUserImages() {
            return userImages;
        }

        /**
         * Sets the value of the userImages property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUserImages(String value) {
            this.userImages = value;
        }

        /**
         * Gets the value of the username property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUsername() {
            return username;
        }

        /**
         * Sets the value of the username property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUsername(String value) {
            this.username = value;
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

        /**
         * Gets the value of the reviewDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReviewDate() {
            return reviewDate;
        }

        /**
         * Sets the value of the reviewDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReviewDate(String value) {
            this.reviewDate = value;
        }

        /**
         * Gets the value of the reviewContent property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReviewContent() {
            return reviewContent;
        }

        /**
         * Sets the value of the reviewContent property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReviewContent(String value) {
            this.reviewContent = value;
        }

        /**
         * Gets the value of the title property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getTitle() {
            return title;
        }

        /**
         * Sets the value of the title property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setTitle(Object value) {
            this.title = value;
        }

    }

}
