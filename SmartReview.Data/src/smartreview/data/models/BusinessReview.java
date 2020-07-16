/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.data.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "BusinessReview.findAll", query = "SELECT b FROM BusinessReview b")
    , @NamedQuery(name = "BusinessReview.findById", query = "SELECT b FROM BusinessReview b WHERE b.id = :id")
    , @NamedQuery(name = "BusinessReview.findByCode", query = "SELECT b FROM BusinessReview b WHERE b.code = :code")
    , @NamedQuery(name = "BusinessReview.findByRating", query = "SELECT b FROM BusinessReview b WHERE b.rating = :rating")
    , @NamedQuery(name = "BusinessReview.findByTitle", query = "SELECT b FROM BusinessReview b WHERE b.title = :title")
    , @NamedQuery(name = "BusinessReview.findByReviewContent", query = "SELECT b FROM BusinessReview b WHERE b.reviewContent = :reviewContent")
    , @NamedQuery(name = "BusinessReview.findByReviewDate", query = "SELECT b FROM BusinessReview b WHERE b.reviewDate = :reviewDate")
    , @NamedQuery(name = "BusinessReview.findByUsername", query = "SELECT b FROM BusinessReview b WHERE b.username = :username")
    , @NamedQuery(name = "BusinessReview.findByUserImage", query = "SELECT b FROM BusinessReview b WHERE b.userImage = :userImage")
    , @NamedQuery(name = "BusinessReview.findByIsPositive", query = "SELECT b FROM BusinessReview b WHERE b.isPositive = :isPositive")})
public class BusinessReview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String code;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double rating;
    private String title;
    private String reviewContent;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;
    private String username;
    private String userImage;
    private Boolean isPositive;
    @JoinColumn(name = "businessId", referencedColumnName = "id")
    @ManyToOne
    private Business businessId;
    @OneToMany(mappedBy = "reviewId")
    private Collection<CategoriesOfReviews> categoriesOfReviewsCollection;

    public BusinessReview() {
    }

    public BusinessReview(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Boolean getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(Boolean isPositive) {
        this.isPositive = isPositive;
    }

    public Business getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Business businessId) {
        this.businessId = businessId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusinessReview)) {
            return false;
        }
        BusinessReview other = (BusinessReview) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smartreview.data.models.BusinessReview[ id=" + id + " ]";
    }
    
}
