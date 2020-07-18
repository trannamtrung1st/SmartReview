
package smartreview.parser.tripadvisor;

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
    "moresBtnXPath"
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

}
