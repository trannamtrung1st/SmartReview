/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import smartreview.data.models.BusinessImage;

/**
 *
 * @author TNT
 */
@XmlRootElement(name = "businessImage")
public class BusinessImageDTO extends BaseDTO<BusinessImage> {

    private Integer id;
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void copyFrom(BusinessImage entity) {
        setId(entity.getId());
        setImageUrl(entity.getImageUrl());
    }

}
