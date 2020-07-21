/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TNT
 */
@Entity
@Table(catalog = "SmartReview", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParserInfo.findAll", query = "SELECT p FROM ParserInfo p")
    , @NamedQuery(name = "ParserInfo.findByParserCode", query = "SELECT p FROM ParserInfo p WHERE p.parserCode = :parserCode")
    , @NamedQuery(name = "ParserInfo.findByParserBaseUrl", query = "SELECT p FROM ParserInfo p WHERE p.parserBaseUrl = :parserBaseUrl")
    , @NamedQuery(name = "ParserInfo.findByFromPage", query = "SELECT p FROM ParserInfo p WHERE p.fromPage = :fromPage")
    , @NamedQuery(name = "ParserInfo.findByToPage", query = "SELECT p FROM ParserInfo p WHERE p.toPage = :toPage")
    , @NamedQuery(name = "ParserInfo.findByRefreshExistedData", query = "SELECT p FROM ParserInfo p WHERE p.refreshExistedData = :refreshExistedData")
    , @NamedQuery(name = "ParserInfo.findByMaxParsedReviewsPage", query = "SELECT p FROM ParserInfo p WHERE p.maxParsedReviewsPage = :maxParsedReviewsPage")})
public class ParserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String parserCode;
    private String parserBaseUrl;
    private Integer fromPage;
    private Integer toPage;
    private Boolean refreshExistedData;
    private Integer maxParsedReviewsPage;

    public ParserInfo() {
    }

    public ParserInfo(String parserCode) {
        this.parserCode = parserCode;
    }

    public String getParserCode() {
        return parserCode;
    }

    public void setParserCode(String parserCode) {
        this.parserCode = parserCode;
    }

    public String getParserBaseUrl() {
        return parserBaseUrl;
    }

    public void setParserBaseUrl(String parserBaseUrl) {
        this.parserBaseUrl = parserBaseUrl;
    }

    public Integer getFromPage() {
        return fromPage;
    }

    public void setFromPage(Integer fromPage) {
        this.fromPage = fromPage;
    }

    public Integer getToPage() {
        return toPage;
    }

    public void setToPage(Integer toPage) {
        this.toPage = toPage;
    }

    public Boolean getRefreshExistedData() {
        return refreshExistedData;
    }

    public void setRefreshExistedData(Boolean refreshExistedData) {
        this.refreshExistedData = refreshExistedData;
    }

    public Integer getMaxParsedReviewsPage() {
        return maxParsedReviewsPage;
    }

    public void setMaxParsedReviewsPage(Integer maxParsedReviewsPage) {
        this.maxParsedReviewsPage = maxParsedReviewsPage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parserCode != null ? parserCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParserInfo)) {
            return false;
        }
        ParserInfo other = (ParserInfo) object;
        if ((this.parserCode == null && other.parserCode != null) || (this.parserCode != null && !this.parserCode.equals(other.parserCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smartreview.models.ParserInfo[ parserCode=" + parserCode + " ]";
    }
    
}
