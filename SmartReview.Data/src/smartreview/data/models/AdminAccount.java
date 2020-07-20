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
    @NamedQuery(name = "AdminAccount.findAll", query = "SELECT a FROM AdminAccount a")
    , @NamedQuery(name = "AdminAccount.findByUsername", query = "SELECT a FROM AdminAccount a WHERE a.username = :username")
    , @NamedQuery(name = "AdminAccount.findByPassword", query = "SELECT a FROM AdminAccount a WHERE a.password = :password")})
public class AdminAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String username;
    @Basic(optional = false)
    private String password;

    public AdminAccount() {
    }

    public AdminAccount(String username) {
        this.username = username;
    }

    public AdminAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminAccount)) {
            return false;
        }
        AdminAccount other = (AdminAccount) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smartreview.data.models.AdminAccount[ username=" + username + " ]";
    }
    
}
