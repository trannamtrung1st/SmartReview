/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.daos;

import javax.persistence.EntityManager;
import smartreview.models.CategoriesOfReviews;

/**
 *
 * @author TNT
 */
public class CategoriesOfReviewsDAO extends BaseDAO<CategoriesOfReviews> {

    public CategoriesOfReviewsDAO(EntityManager eManager) {
        super(eManager);
    }

}
