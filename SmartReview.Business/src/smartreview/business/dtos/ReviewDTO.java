/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.dtos;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import smartreview.data.models.Business;
import smartreview.data.models.BusinessReview;

/**
 *
 * @author TNT
 */
@XmlRootElement(name = "review")
public class ReviewDTO extends BaseDTO<BusinessReview> {

    private Integer id;
    private String code;
    private Double rating;
    private String title;
    private String reviewContent;
    private Date reviewDate;
    private String username;
    private String userImage;
    private Boolean isPositive;
    private Integer businessId;
    private List<ReviewCategoryDTO> categories;

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "item")
    public List<ReviewCategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<ReviewCategoryDTO> categories) {
        this.categories = categories;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
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

    @Override
    public void copyFrom(BusinessReview entity) {
        setCode(entity.getCode());
        setBusinessId(entity.getBusinessId().getId());
        setId(entity.getId());
        setIsPositive(entity.getIsPositive());
        setRating(entity.getRating());
        setReviewContent(entity.getReviewContent());
        setReviewDate(entity.getReviewDate());
        setTitle(entity.getTitle());
        setUserImage(entity.getUserImage());
        setUsername(entity.getUsername());
    }

}
