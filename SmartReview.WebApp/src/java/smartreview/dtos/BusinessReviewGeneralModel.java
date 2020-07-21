/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author TNT
 */
public class BusinessReviewGeneralModel {

    protected Integer businessId;
    protected boolean overall;
    protected List<BadReviewDetailModel> badReviewDetails;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public boolean isOverall() {
        return overall;
    }

    public void setOverall(boolean overall) {
        this.overall = overall;
    }

    @XmlElementWrapper(name = "badReviewDetails")
    @XmlElement(name = "item")
    public List<BadReviewDetailModel> getBadReviewDetails() {
        return badReviewDetails;
    }

    public void setBadReviewDetails(List<BadReviewDetailModel> badReviewDetails) {
        this.badReviewDetails = badReviewDetails;
    }

}
