/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "CategoriesOfReviews.findAll", query = "SELECT c FROM CategoriesOfReviews c")
    , @NamedQuery(name = "CategoriesOfReviews.findById", query = "SELECT c FROM CategoriesOfReviews c WHERE c.id = :id")})
public class CategoriesOfReviews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "reviewId", referencedColumnName = "id")
    @ManyToOne
    private BusinessReview reviewId;
    @JoinColumn(name = "categoryCode", referencedColumnName = "code")
    @ManyToOne
    private ReviewCategory categoryCode;

    public CategoriesOfReviews() {
    }

    public CategoriesOfReviews(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BusinessReview getReviewId() {
        return reviewId;
    }

    public void setReviewId(BusinessReview reviewId) {
        this.reviewId = reviewId;
    }

    public ReviewCategory getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(ReviewCategory categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriesOfReviews)) {
            return false;
        }
        CategoriesOfReviews other = (CategoriesOfReviews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smartreview.models.CategoriesOfReviews[ id=" + id + " ]";
    }
    
}
