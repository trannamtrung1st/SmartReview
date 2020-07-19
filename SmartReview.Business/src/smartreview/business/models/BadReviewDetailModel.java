/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.models;

/**
 *
 * @author TNT
 */
public class BadReviewDetailModel {

    protected String reviewCateCode;
    protected String reviewCateName;
    protected Integer totalReview;
    protected Double ratio;

    public String getReviewCateCode() {
        return reviewCateCode;
    }

    public void setReviewCateCode(String reviewCateCode) {
        this.reviewCateCode = reviewCateCode;
    }

    public String getReviewCateName() {
        return reviewCateName;
    }

    public void setReviewCateName(String reviewCateName) {
        this.reviewCateName = reviewCateName;
    }

    public Integer getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(Integer totalReview) {
        this.totalReview = totalReview;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

}
