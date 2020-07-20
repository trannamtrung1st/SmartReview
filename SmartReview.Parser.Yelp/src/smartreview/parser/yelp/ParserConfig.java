
package smartreview.parser.yelp;

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
 *         &lt;element name="baseApiUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         &lt;element name="reviewStringRules">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="regex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="remove" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ratingRule">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="regex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="div" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="reviewDateFormat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="defaultConfigs">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="waitForNextBusinessListPage" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="defaultWebDriverWait" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="defaultPollingSecs" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="waitForAction" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="maxTryClick" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="defaultMaxReviewPages" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
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
    "baseApiUrl",
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
    "reviewCateMap",
    "reviewStringRules",
    "ratingRule",
    "reviewDateFormat",
    "defaultConfigs"
})
@XmlRootElement(name = "parserConfig")
public class ParserConfig {

    @XmlElement(required = true)
    protected String baseApiUrl;
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
    @XmlElement(required = true)
    protected ParserConfig.ReviewStringRules reviewStringRules;
    @XmlElement(required = true)
    protected ParserConfig.RatingRule ratingRule;
    @XmlElement(required = true)
    protected String reviewDateFormat;
    @XmlElement(required = true)
    protected ParserConfig.DefaultConfigs defaultConfigs;

    /**
     * Gets the value of the baseApiUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseApiUrl() {
        return baseApiUrl;
    }

    /**
     * Sets the value of the baseApiUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseApiUrl(String value) {
        this.baseApiUrl = value;
    }

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
     * Gets the value of the reviewStringRules property.
     * 
     * @return
     *     possible object is
     *     {@link ParserConfig.ReviewStringRules }
     *     
     */
    public ParserConfig.ReviewStringRules getReviewStringRules() {
        return reviewStringRules;
    }

    /**
     * Sets the value of the reviewStringRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParserConfig.ReviewStringRules }
     *     
     */
    public void setReviewStringRules(ParserConfig.ReviewStringRules value) {
        this.reviewStringRules = value;
    }

    /**
     * Gets the value of the ratingRule property.
     * 
     * @return
     *     possible object is
     *     {@link ParserConfig.RatingRule }
     *     
     */
    public ParserConfig.RatingRule getRatingRule() {
        return ratingRule;
    }

    /**
     * Sets the value of the ratingRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParserConfig.RatingRule }
     *     
     */
    public void setRatingRule(ParserConfig.RatingRule value) {
        this.ratingRule = value;
    }

    /**
     * Gets the value of the reviewDateFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewDateFormat() {
        return reviewDateFormat;
    }

    /**
     * Sets the value of the reviewDateFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewDateFormat(String value) {
        this.reviewDateFormat = value;
    }

    /**
     * Gets the value of the defaultConfigs property.
     * 
     * @return
     *     possible object is
     *     {@link ParserConfig.DefaultConfigs }
     *     
     */
    public ParserConfig.DefaultConfigs getDefaultConfigs() {
        return defaultConfigs;
    }

    /**
     * Sets the value of the defaultConfigs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParserConfig.DefaultConfigs }
     *     
     */
    public void setDefaultConfigs(ParserConfig.DefaultConfigs value) {
        this.defaultConfigs = value;
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
     *         &lt;element name="waitForNextBusinessListPage" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="defaultWebDriverWait" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="defaultPollingSecs" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="waitForAction" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="maxTryClick" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="defaultMaxReviewPages" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
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
        "waitForNextBusinessListPage",
        "defaultWebDriverWait",
        "defaultPollingSecs",
        "waitForAction",
        "maxTryClick",
        "defaultMaxReviewPages"
    })
    public static class DefaultConfigs {

        @XmlSchemaType(name = "unsignedByte")
        protected short waitForNextBusinessListPage;
        @XmlSchemaType(name = "unsignedByte")
        protected short defaultWebDriverWait;
        @XmlSchemaType(name = "unsignedByte")
        protected short defaultPollingSecs;
        @XmlSchemaType(name = "unsignedByte")
        protected short waitForAction;
        @XmlSchemaType(name = "unsignedByte")
        protected short maxTryClick;
        @XmlSchemaType(name = "unsignedByte")
        protected short defaultMaxReviewPages;

        /**
         * Gets the value of the waitForNextBusinessListPage property.
         * 
         */
        public short getWaitForNextBusinessListPage() {
            return waitForNextBusinessListPage;
        }

        /**
         * Sets the value of the waitForNextBusinessListPage property.
         * 
         */
        public void setWaitForNextBusinessListPage(short value) {
            this.waitForNextBusinessListPage = value;
        }

        /**
         * Gets the value of the defaultWebDriverWait property.
         * 
         */
        public short getDefaultWebDriverWait() {
            return defaultWebDriverWait;
        }

        /**
         * Sets the value of the defaultWebDriverWait property.
         * 
         */
        public void setDefaultWebDriverWait(short value) {
            this.defaultWebDriverWait = value;
        }

        /**
         * Gets the value of the defaultPollingSecs property.
         * 
         */
        public short getDefaultPollingSecs() {
            return defaultPollingSecs;
        }

        /**
         * Sets the value of the defaultPollingSecs property.
         * 
         */
        public void setDefaultPollingSecs(short value) {
            this.defaultPollingSecs = value;
        }

        /**
         * Gets the value of the waitForAction property.
         * 
         */
        public short getWaitForAction() {
            return waitForAction;
        }

        /**
         * Sets the value of the waitForAction property.
         * 
         */
        public void setWaitForAction(short value) {
            this.waitForAction = value;
        }

        /**
         * Gets the value of the maxTryClick property.
         * 
         */
        public short getMaxTryClick() {
            return maxTryClick;
        }

        /**
         * Sets the value of the maxTryClick property.
         * 
         */
        public void setMaxTryClick(short value) {
            this.maxTryClick = value;
        }

        /**
         * Gets the value of the defaultMaxReviewPages property.
         * 
         */
        public short getDefaultMaxReviewPages() {
            return defaultMaxReviewPages;
        }

        /**
         * Sets the value of the defaultMaxReviewPages property.
         * 
         */
        public void setDefaultMaxReviewPages(short value) {
            this.defaultMaxReviewPages = value;
        }

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
     *         &lt;element name="regex" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="div" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
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
        "regex",
        "div"
    })
    public static class RatingRule {

        @XmlElement(required = true)
        protected String regex;
        @XmlSchemaType(name = "unsignedByte")
        protected short div;

        /**
         * Gets the value of the regex property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRegex() {
            return regex;
        }

        /**
         * Sets the value of the regex property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRegex(String value) {
            this.regex = value;
        }

        /**
         * Gets the value of the div property.
         * 
         */
        public short getDiv() {
            return div;
        }

        /**
         * Sets the value of the div property.
         * 
         */
        public void setDiv(short value) {
            this.div = value;
        }

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
     *         &lt;element name="regex" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="remove" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "regex",
        "remove"
    })
    public static class ReviewStringRules {

        @XmlElement(required = true)
        protected String regex;
        @XmlElement(required = true)
        protected String remove;

        /**
         * Gets the value of the regex property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRegex() {
            return regex;
        }

        /**
         * Sets the value of the regex property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRegex(String value) {
            this.regex = value;
        }

        /**
         * Gets the value of the remove property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRemove() {
            return remove;
        }

        /**
         * Sets the value of the remove property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRemove(String value) {
            this.remove = value;
        }

    }

}
