/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.dtos;

import smartreview.dtos.CountModel;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import smartreview.dtos.ReviewDTO;

/**
 *
 * @author TNT
 */
@XmlRootElement(name = "listReview")
public class ListReviewModel {

    protected List<ReviewDTO> list;

    CountModel count;

    public void setCount(CountModel count) {
        this.count = count;
    }

    public CountModel getCount() {
        return count;
    }

    @XmlElementWrapper(name = "list")
    @XmlElement(name = "item")
    public List<ReviewDTO> getList() {
        return list;
    }

    public void setList(List<ReviewDTO> list) {
        this.list = list;
    }

}
