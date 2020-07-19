/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.dtos;

/**
 *
 * @author TNT
 */
public abstract class BaseDTO<T> {

    public abstract void copyFrom(T entity);

    public BaseDTO() {
    }

    public BaseDTO(T entity) {
        copyFrom(entity);
    }

}
