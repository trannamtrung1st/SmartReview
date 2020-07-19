/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.dtos;

import smartreview.business.models.CountBusinessModel;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TNT
 */
@XmlRootElement(name = "listBusiness")
public class ListBusinessDTO {

    protected List<BusinessDTO> list;

    CountBusinessModel count;

    public void setCount(CountBusinessModel count) {
        this.count = count;
    }

    public CountBusinessModel getCount() {
        return count;
    }

    @XmlElementWrapper(name = "list")
    @XmlElement(name = "item")
    public List<BusinessDTO> getList() {
        return list;
    }

    public void setList(List<BusinessDTO> list) {
        this.list = list;
    }

}
