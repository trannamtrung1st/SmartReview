/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import smartreview.data.daos.ReviewCategoryDAO;
import smartreview.data.models.ReviewCategory;

/**
 *
 * @author TNT
 */
public class ReviewCategoryService {

    protected ReviewCategoryDAO reviewCategoryDAO;
    protected EntityManager entityManager;

    public ReviewCategoryService(EntityManager entityManager, ReviewCategoryDAO reviewCategoryDAO) {
        this.entityManager = entityManager;
        this.reviewCategoryDAO = reviewCategoryDAO;
    }

    public List<ReviewCategory> getAll() {
        String sql = "SELECT * FROM ReviewCategory";
        Query query = reviewCategoryDAO.nativeQuery(sql, ReviewCategory.class);
        List<ReviewCategory> list = query.getResultList();
        return list;
    }

    public ReviewCategory findReviewCategoryByCode(String code) {
        String sql = "SELECT * FROM ReviewCategory WHERE code=?code";
        Query query = reviewCategoryDAO.nativeQuery(sql, ReviewCategory.class).setParameter("code", code);
        List<ReviewCategory> list = query.getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean reviewCategoryCodeExists(String code) {
        String sql = "SELECT COUNT(code) FROM ReviewCategory WHERE code=?code";
        Query query = reviewCategoryDAO.nativeQuery(sql).setParameter("code", code);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public boolean anyExisted() {
        String sql = "SELECT COUNT(code) FROM ReviewCategory";
        Query query = reviewCategoryDAO.nativeQuery(sql);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public ReviewCategory createReviewCategory(ReviewCategory entity) {
        return reviewCategoryDAO.create(entity);
    }

}
