/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import smartreview.data.models.ReviewCategory;

/**
 *
 * @author TNT
 */
@XmlRootElement(name = "reviewCategory")
public class ReviewCategoryDTO extends BaseDTO<ReviewCategory> {

    private String code;
    private String name;
    private String description;

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

    @Override
    public void copyFrom(ReviewCategory entity) {
        setCode(entity.getCode());
        setDescription(entity.getDescription());
        setName(entity.getName());
    }

}
