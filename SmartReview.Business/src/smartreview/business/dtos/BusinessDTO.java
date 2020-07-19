/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import smartreview.data.models.Business;

/**
 *
 * @author TNT
 */
@XmlRootElement(name = "business")
public class BusinessDTO extends BaseDTO<Business> {

    private Integer id;
    private String code;
    private String name;
    private Integer totalReview;
    private Double rating;
    private String address;
    private String phone;
    private String detailUrl;
    private String fromPage;
    private List<BusinessImageDTO> businessImages;

    @XmlElementWrapper(name = "images")
    @XmlElement(name = "item")
    public List<BusinessImageDTO> getBusinessImages() {
        return businessImages;
    }

    public void setBusinessImages(List<BusinessImageDTO> businessImages) {
        this.businessImages = businessImages;
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

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    @Override
    public void copyFrom(Business entity) {
        setId(entity.getId());
        setAddress(entity.getAddress());
        setCode(entity.getCode());
        setDetailUrl(entity.getDetailUrl());
        setFromPage(entity.getFromPage());
        setName(entity.getName());
        setPhone(entity.getPhone());
        setRating(entity.getRating());
        setTotalReview(entity.getTotalReview());
    }

}
