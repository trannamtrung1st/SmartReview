/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.models;

import javax.xml.bind.annotation.XmlRootElement;
import smartreview.business.dtos.BusinessDTO;

/**
 *
 * @author TNT
 */
@XmlRootElement(name = "businessDetailModel")
public class BusinessDetailModel {

    protected BusinessDTO business;
    protected BusinessReviewGeneralModel reviewGeneral;

    public BusinessDTO getBusiness() {
        return business;
    }

    public void setBusiness(BusinessDTO business) {
        this.business = business;
    }

    public BusinessReviewGeneralModel getReviewGeneral() {
        return reviewGeneral;
    }

    public void setReviewGeneral(BusinessReviewGeneralModel reviewGeneral) {
        this.reviewGeneral = reviewGeneral;
    }

}
