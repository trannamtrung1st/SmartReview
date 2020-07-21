
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
 *         &lt;element name="results">
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
 *                             &lt;element name="prediction" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="badReviewDetail">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="prediction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="topOutputs">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="topOutput" maxOccurs="unbounded">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
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
    "results"
})
@XmlRootElement(name = "reviewAnalysisResult")
public class ReviewAnalysisResult {

    @XmlElement(required = true)
    protected ReviewAnalysisResult.Results results;

    /**
     * Gets the value of the results property.
     * 
     * @return
     *     possible object is
     *     {@link ReviewAnalysisResult.Results }
     *     
     */
    public ReviewAnalysisResult.Results getResults() {
        return results;
    }

    /**
     * Sets the value of the results property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReviewAnalysisResult.Results }
     *     
     */
    public void setResults(ReviewAnalysisResult.Results value) {
        this.results = value;
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
     *                   &lt;element name="prediction" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="badReviewDetail">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="prediction" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="topOutputs">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="topOutput" maxOccurs="unbounded">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                 &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
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
        "item"
    })
    public static class Results {

        @XmlElement(required = true)
        protected List<ReviewAnalysisResult.Results.Item> item;

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
         * {@link ReviewAnalysisResult.Results.Item }
         * 
         * 
         */
        public List<ReviewAnalysisResult.Results.Item> getItem() {
            if (item == null) {
                item = new ArrayList<ReviewAnalysisResult.Results.Item>();
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
         *         &lt;element name="prediction" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="badReviewDetail">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="prediction" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="topOutputs">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="topOutput" maxOccurs="unbounded">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                       &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
            "reviewCode",
            "prediction",
            "badReviewDetail"
        })
        public static class Item {

            @XmlElement(required = true)
            protected String reviewCode;
            protected boolean prediction;
            @XmlElement(required = true)
            protected ReviewAnalysisResult.Results.Item.BadReviewDetail badReviewDetail;

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
             * Gets the value of the prediction property.
             * 
             */
            public boolean isPrediction() {
                return prediction;
            }

            /**
             * Sets the value of the prediction property.
             * 
             */
            public void setPrediction(boolean value) {
                this.prediction = value;
            }

            /**
             * Gets the value of the badReviewDetail property.
             * 
             * @return
             *     possible object is
             *     {@link ReviewAnalysisResult.Results.Item.BadReviewDetail }
             *     
             */
            public ReviewAnalysisResult.Results.Item.BadReviewDetail getBadReviewDetail() {
                return badReviewDetail;
            }

            /**
             * Sets the value of the badReviewDetail property.
             * 
             * @param value
             *     allowed object is
             *     {@link ReviewAnalysisResult.Results.Item.BadReviewDetail }
             *     
             */
            public void setBadReviewDetail(ReviewAnalysisResult.Results.Item.BadReviewDetail value) {
                this.badReviewDetail = value;
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
             *         &lt;element name="prediction" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="topOutputs">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="topOutput" maxOccurs="unbounded">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                             &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
                "prediction",
                "topOutputs"
            })
            public static class BadReviewDetail {

                @XmlElement(required = true)
                protected String prediction;
                @XmlElement(required = true)
                protected ReviewAnalysisResult.Results.Item.BadReviewDetail.TopOutputs topOutputs;

                /**
                 * Gets the value of the prediction property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPrediction() {
                    return prediction;
                }

                /**
                 * Sets the value of the prediction property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPrediction(String value) {
                    this.prediction = value;
                }

                /**
                 * Gets the value of the topOutputs property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link ReviewAnalysisResult.Results.Item.BadReviewDetail.TopOutputs }
                 *     
                 */
                public ReviewAnalysisResult.Results.Item.BadReviewDetail.TopOutputs getTopOutputs() {
                    return topOutputs;
                }

                /**
                 * Sets the value of the topOutputs property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link ReviewAnalysisResult.Results.Item.BadReviewDetail.TopOutputs }
                 *     
                 */
                public void setTopOutputs(ReviewAnalysisResult.Results.Item.BadReviewDetail.TopOutputs value) {
                    this.topOutputs = value;
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
                 *         &lt;element name="topOutput" maxOccurs="unbounded">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                   &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
                    "topOutput"
                })
                public static class TopOutputs {

                    @XmlElement(required = true)
                    protected List<ReviewAnalysisResult.Results.Item.BadReviewDetail.TopOutputs.TopOutput> topOutput;

                    /**
                     * Gets the value of the topOutput property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the topOutput property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getTopOutput().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link ReviewAnalysisResult.Results.Item.BadReviewDetail.TopOutputs.TopOutput }
                     * 
                     * 
                     */
                    public List<ReviewAnalysisResult.Results.Item.BadReviewDetail.TopOutputs.TopOutput> getTopOutput() {
                        if (topOutput == null) {
                            topOutput = new ArrayList<ReviewAnalysisResult.Results.Item.BadReviewDetail.TopOutputs.TopOutput>();
                        }
                        return this.topOutput;
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
                     *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *         &lt;element name="score" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
                        "label",
                        "score"
                    })
                    public static class TopOutput {

                        @XmlElement(required = true)
                        protected String label;
                        protected double score;

                        /**
                         * Gets the value of the label property.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getLabel() {
                            return label;
                        }

                        /**
                         * Sets the value of the label property.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setLabel(String value) {
                            this.label = value;
                        }

                        /**
                         * Gets the value of the score property.
                         * 
                         */
                        public double getScore() {
                            return score;
                        }

                        /**
                         * Sets the value of the score property.
                         * 
                         */
                        public void setScore(double value) {
                            this.score = value;
                        }

                    }

                }

            }

        }

    }

}
