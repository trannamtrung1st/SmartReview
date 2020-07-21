/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TNT
 */
@Entity
@Table(catalog = "SmartReview", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReviewCategory.findAll", query = "SELECT r FROM ReviewCategory r")
    , @NamedQuery(name = "ReviewCategory.findByCode", query = "SELECT r FROM ReviewCategory r WHERE r.code = :code")
    , @NamedQuery(name = "ReviewCategory.findByName", query = "SELECT r FROM ReviewCategory r WHERE r.name = :name")
    , @NamedQuery(name = "ReviewCategory.findByDescription", query = "SELECT r FROM ReviewCategory r WHERE r.description = :description")})
public class ReviewCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private String code;
    private String name;
    private String description;
    @OneToMany(mappedBy = "categoryCode", cascade = CascadeType.PERSIST)
    private Collection<CategoriesOfReviews> categoriesOfReviewsCollection;

    public ReviewCategory() {
    }

    public ReviewCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<CategoriesOfReviews> getCategoriesOfReviewsCollection() {
        return categoriesOfReviewsCollection;
    }

    public void setCategoriesOfReviewsCollection(Collection<CategoriesOfReviews> categoriesOfReviewsCollection) {
        this.categoriesOfReviewsCollection = categoriesOfReviewsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewCategory)) {
            return false;
        }
        ReviewCategory other = (ReviewCategory) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smartreview.models.ReviewCategory[ code=" + code + " ]";
    }

}
