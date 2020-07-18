
package smartreview.parser.tripadvisor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="driverPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="baseUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="businessListUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="businessLinksXPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="businessItemCssSelector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nextPageCssSelector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currentPageCssSelector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="defaultFromPage" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="defaultToPage" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="codeFromUrlRegex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="moresBtnXPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reviewCateMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="item" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="value">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
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
    "driverPath",
    "baseUrl",
    "businessListUrl",
    "code",
    "businessLinksXPath",
    "businessItemCssSelector",
    "nextPageCssSelector",
    "currentPageCssSelector",
    "defaultFromPage",
    "defaultToPage",
    "codeFromUrlRegex",
    "moresBtnXPath",
    "reviewCateMap"
})
@XmlRootElement(name = "parserConfig")
public class ParserConfig {

    @XmlElement(required = true)
    protected String driverPath;
    @XmlElement(required = true)
    protected String baseUrl;
    @XmlElement(required = true)
    protected String businessListUrl;
    @XmlElement(required = true)
    protected String code;
    @XmlElement(required = true)
    protected String businessLinksXPath;
    @XmlElement(required = true)
    protected String businessItemCssSelector;
    @XmlElement(required = true)
    protected String nextPageCssSelector;
    @XmlElement(required = true)
    protected String currentPageCssSelector;
    @XmlSchemaType(name = "unsignedByte")
    protected short defaultFromPage;
    @XmlSchemaType(name = "unsignedByte")
    protected short defaultToPage;
    @XmlElement(required = true)
    protected String codeFromUrlRegex;
    @XmlElement(required = true)
    protected String moresBtnXPath;
    @XmlElement(required = true)
    protected ParserConfig.ReviewCateMap reviewCateMap;

    /**
     * Gets the value of the driverPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverPath() {
        return driverPath;
    }

    /**
     * Sets the value of the driverPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverPath(String value) {
        this.driverPath = value;
    }

    /**
     * Gets the value of the baseUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Sets the value of the baseUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseUrl(String value) {
        this.baseUrl = value;
    }

    /**
     * Gets the value of the businessListUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessListUrl() {
        return businessListUrl;
    }

    /**
     * Sets the value of the businessListUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessListUrl(String value) {
        this.businessListUrl = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the businessLinksXPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessLinksXPath() {
        return businessLinksXPath;
    }

    /**
     * Sets the value of the businessLinksXPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessLinksXPath(String value) {
        this.businessLinksXPath = value;
    }

    /**
     * Gets the value of the businessItemCssSelector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessItemCssSelector() {
        return businessItemCssSelector;
    }

    /**
     * Sets the value of the businessItemCssSelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessItemCssSelector(String value) {
        this.businessItemCssSelector = value;
    }

    /**
     * Gets the value of the nextPageCssSelector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextPageCssSelector() {
        return nextPageCssSelector;
    }

    /**
     * Sets the value of the nextPageCssSelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextPageCssSelector(String value) {
        this.nextPageCssSelector = value;
    }

    /**
     * Gets the value of the currentPageCssSelector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentPageCssSelector() {
        return currentPageCssSelector;
    }

    /**
     * Sets the value of the currentPageCssSelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentPageCssSelector(String value) {
        this.currentPageCssSelector = value;
    }

    /**
     * Gets the value of the defaultFromPage property.
     * 
     */
    public short getDefaultFromPage() {
        return defaultFromPage;
    }

    /**
     * Sets the value of the defaultFromPage property.
     * 
     */
    public void setDefaultFromPage(short value) {
        this.defaultFromPage = value;
    }

    /**
     * Gets the value of the defaultToPage property.
     * 
     */
    public short getDefaultToPage() {
        return defaultToPage;
    }

    /**
     * Sets the value of the defaultToPage property.
     * 
     */
    public void setDefaultToPage(short value) {
        this.defaultToPage = value;
    }

    /**
     * Gets the value of the codeFromUrlRegex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeFromUrlRegex() {
        return codeFromUrlRegex;
    }

    /**
     * Sets the value of the codeFromUrlRegex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeFromUrlRegex(String value) {
        this.codeFromUrlRegex = value;
    }

    /**
     * Gets the value of the moresBtnXPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoresBtnXPath() {
        return moresBtnXPath;
    }

    /**
     * Sets the value of the moresBtnXPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoresBtnXPath(String value) {
        this.moresBtnXPath = value;
    }

    /**
     * Gets the value of the reviewCateMap property.
     * 
     * @return
     *     possible object is
     *     {@link ParserConfig.ReviewCateMap }
     *     
     */
    public ParserConfig.ReviewCateMap getReviewCateMap() {
        return reviewCateMap;
    }

    /**
     * Sets the value of the reviewCateMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParserConfig.ReviewCateMap }
     *     
     */
    public void setReviewCateMap(ParserConfig.ReviewCateMap value) {
        this.reviewCateMap = value;
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
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="value">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "item"
    })
    public static class ReviewCateMap {

        @XmlElement(required = true)
        protected List<ParserConfig.ReviewCateMap.Item> item;

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
         * {@link ParserConfig.ReviewCateMap.Item }
         * 
         * 
         */
        public List<ParserConfig.ReviewCateMap.Item> getItem() {
            if (item == null) {
                item = new ArrayList<ParserConfig.ReviewCateMap.Item>();
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
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="value">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "key",
            "value"
        })
        public static class Item {

            @XmlElement(required = true)
            protected String key;
            @XmlElement(required = true)
            protected ParserConfig.ReviewCateMap.Item.Value value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link ParserConfig.ReviewCateMap.Item.Value }
             *     
             */
            public ParserConfig.ReviewCateMap.Item.Value getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link ParserConfig.ReviewCateMap.Item.Value }
             *     
             */
            public void setValue(ParserConfig.ReviewCateMap.Item.Value value) {
                this.value = value;
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
             *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                "code",
                "name"
            })
            public static class Value {

                @XmlElement(required = true)
                protected String code;
                @XmlElement(required = true)
                protected String name;

                /**
                 * Gets the value of the code property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCode() {
                    return code;
                }

                /**
                 * Sets the value of the code property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCode(String value) {
                    this.code = value;
                }

                /**
                 * Gets the value of the name property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Sets the value of the name property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }

    }

}
