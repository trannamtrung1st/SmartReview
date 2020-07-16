/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.data.models;

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
    , @NamedQuery(name = "ParserInfo.findByParserBaseUrl", query = "SELECT p FROM ParserInfo p WHERE p.parserBaseUrl = :parserBaseUrl")})
public class ParserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String parserCode;
    private String parserBaseUrl;

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
        return "smartreview.data.models.ParserInfo[ parserCode=" + parserCode + " ]";
    }
    
}
