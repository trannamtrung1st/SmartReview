/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.data.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @NamedQuery(name = "Business.findAll", query = "SELECT b FROM Business b")
    , @NamedQuery(name = "Business.findById", query = "SELECT b FROM Business b WHERE b.id = :id")
    , @NamedQuery(name = "Business.findByCode", query = "SELECT b FROM Business b WHERE b.code = :code")
    , @NamedQuery(name = "Business.findByName", query = "SELECT b FROM Business b WHERE b.name = :name")
    , @NamedQuery(name = "Business.findByTotalReview", query = "SELECT b FROM Business b WHERE b.totalReview = :totalReview")
    , @NamedQuery(name = "Business.findByRating", query = "SELECT b FROM Business b WHERE b.rating = :rating")
    , @NamedQuery(name = "Business.findByAddress", query = "SELECT b FROM Business b WHERE b.address = :address")
    , @NamedQuery(name = "Business.findByPhone", query = "SELECT b FROM Business b WHERE b.phone = :phone")
    , @NamedQuery(name = "Business.findByDetailUrl", query = "SELECT b FROM Business b WHERE b.detailUrl = :detailUrl")})
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private String code;
    private String name;
    private Integer totalReview;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double rating;
    private String address;
    private String phone;
    private String detailUrl;
    @OneToMany(mappedBy = "businessId")
    private Collection<BusinessReview> businessReviewCollection;
    @OneToMany(mappedBy = "businessId")
    private Collection<BusinessImage> businessImageCollection;

    public Business() {
    }

    public Business(Integer id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(Integer totalReview) {
        this.totalReview = totalReview;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @XmlTransient
    public Collection<BusinessReview> getBusinessReviewCollection() {
        return businessReviewCollection;
    }

    public void setBusinessReviewCollection(Collection<BusinessReview> businessReviewCollection) {
        this.businessReviewCollection = businessReviewCollection;
    }

    @XmlTransient
    public Collection<BusinessImage> getBusinessImageCollection() {
        return businessImageCollection;
    }

    public void setBusinessImageCollection(Collection<BusinessImage> businessImageCollection) {
        this.businessImageCollection = businessImageCollection;
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
        if (!(object instanceof Business)) {
            return false;
        }
        Business other = (Business) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smartreview.data.models.Business[ id=" + id + " ]";
    }
    
}
