
package smartreview.xmlparser;

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
 *         &lt;element name="neccessaryPart" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="miscs">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pattern" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="openBracketReplacements">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="replacement" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         &lt;element name="attrReplacements">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="replacement" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "neccessaryPart",
    "miscs",
    "openBracketReplacements",
    "attrReplacements"
})
@XmlRootElement(name = "xmlParserConfig")
public class XmlParserConfig {

    @XmlElement(required = true)
    protected String neccessaryPart;
    @XmlElement(required = true)
    protected XmlParserConfig.Miscs miscs;
    @XmlElement(required = true)
    protected XmlParserConfig.OpenBracketReplacements openBracketReplacements;
    @XmlElement(required = true)
    protected XmlParserConfig.AttrReplacements attrReplacements;

    /**
     * Gets the value of the neccessaryPart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNeccessaryPart() {
        return neccessaryPart;
    }

    /**
     * Sets the value of the neccessaryPart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeccessaryPart(String value) {
        this.neccessaryPart = value;
    }

    /**
     * Gets the value of the miscs property.
     * 
     * @return
     *     possible object is
     *     {@link XmlParserConfig.Miscs }
     *     
     */
    public XmlParserConfig.Miscs getMiscs() {
        return miscs;
    }

    /**
     * Sets the value of the miscs property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlParserConfig.Miscs }
     *     
     */
    public void setMiscs(XmlParserConfig.Miscs value) {
        this.miscs = value;
    }

    /**
     * Gets the value of the openBracketReplacements property.
     * 
     * @return
     *     possible object is
     *     {@link XmlParserConfig.OpenBracketReplacements }
     *     
     */
    public XmlParserConfig.OpenBracketReplacements getOpenBracketReplacements() {
        return openBracketReplacements;
    }

    /**
     * Sets the value of the openBracketReplacements property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlParserConfig.OpenBracketReplacements }
     *     
     */
    public void setOpenBracketReplacements(XmlParserConfig.OpenBracketReplacements value) {
        this.openBracketReplacements = value;
    }

    /**
     * Gets the value of the attrReplacements property.
     * 
     * @return
     *     possible object is
     *     {@link XmlParserConfig.AttrReplacements }
     *     
     */
    public XmlParserConfig.AttrReplacements getAttrReplacements() {
        return attrReplacements;
    }

    /**
     * Sets the value of the attrReplacements property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlParserConfig.AttrReplacements }
     *     
     */
    public void setAttrReplacements(XmlParserConfig.AttrReplacements value) {
        this.attrReplacements = value;
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
     *         &lt;element name="replacement" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "replacement"
    })
    public static class AttrReplacements {

        @XmlElement(required = true)
        protected List<XmlParserConfig.AttrReplacements.Replacement> replacement;

        /**
         * Gets the value of the replacement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the replacement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReplacement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link XmlParserConfig.AttrReplacements.Replacement }
         * 
         * 
         */
        public List<XmlParserConfig.AttrReplacements.Replacement> getReplacement() {
            if (replacement == null) {
                replacement = new ArrayList<XmlParserConfig.AttrReplacements.Replacement>();
            }
            return this.replacement;
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
         *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "from",
            "to"
        })
        public static class Replacement {

            @XmlElement(required = true)
            protected String from;
            @XmlElement(required = true)
            protected String to;

            /**
             * Gets the value of the from property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFrom() {
                return from;
            }

            /**
             * Sets the value of the from property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFrom(String value) {
                this.from = value;
            }

            /**
             * Gets the value of the to property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTo() {
                return to;
            }

            /**
             * Sets the value of the to property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTo(String value) {
                this.to = value;
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
     *         &lt;element name="pattern" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "pattern"
    })
    public static class Miscs {

        @XmlElement(required = true)
        protected List<String> pattern;

        /**
         * Gets the value of the pattern property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pattern property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPattern().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getPattern() {
            if (pattern == null) {
                pattern = new ArrayList<String>();
            }
            return this.pattern;
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
     *         &lt;element name="replacement" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "replacement"
    })
    public static class OpenBracketReplacements {

        @XmlElement(required = true)
        protected List<XmlParserConfig.OpenBracketReplacements.Replacement> replacement;

        /**
         * Gets the value of the replacement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the replacement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReplacement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link XmlParserConfig.OpenBracketReplacements.Replacement }
         * 
         * 
         */
        public List<XmlParserConfig.OpenBracketReplacements.Replacement> getReplacement() {
            if (replacement == null) {
                replacement = new ArrayList<XmlParserConfig.OpenBracketReplacements.Replacement>();
            }
            return this.replacement;
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
         *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "from",
            "to"
        })
        public static class Replacement {

            @XmlElement(required = true)
            protected String from;
            @XmlElement(required = true)
            protected String to;

            /**
             * Gets the value of the from property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFrom() {
                return from;
            }

            /**
             * Sets the value of the from property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFrom(String value) {
                this.from = value;
            }

            /**
             * Gets the value of the to property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTo() {
                return to;
            }

            /**
             * Sets the value of the to property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTo(String value) {
                this.to = value;
            }

        }

    }

}
