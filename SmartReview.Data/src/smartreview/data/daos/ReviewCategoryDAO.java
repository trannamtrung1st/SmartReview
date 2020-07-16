/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.data.daos;

import javax.persistence.EntityManager;
import smartreview.data.models.ReviewCategory;

/**
 *
 * @author TNT
 */
public class ReviewCategoryDAO extends BaseDAO<ReviewCategory> {

    public ReviewCategoryDAO(EntityManager eManager) {
        super(eManager);
    }

}
