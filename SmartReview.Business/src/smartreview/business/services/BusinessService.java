/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;
import smartreview.business.dtos.BusinessDTO;
import smartreview.business.dtos.BusinessImageDTO;
import smartreview.business.models.CountModel;
import smartreview.business.models.ListBusinessModel;
import smartreview.business.models.BadReviewDetailModel;
import smartreview.business.models.BusinessReviewGeneralModel;
import smartreview.data.daos.BusinessDAO;
import smartreview.data.models.Business;
import smartreview.data.models.BusinessImage;
import smartreview.data.models.ReviewCategory;
import smartreview.helper.StringHelper;

/**
 *
 * @author TNT
 */
public class BusinessService {

    protected BusinessDAO businessDAO;
    protected EntityManager entityManager;

    public BusinessService(EntityManager entityManager, BusinessDAO businessDAO) {
        this.entityManager = entityManager;
        this.businessDAO = businessDAO;
    }

    public Business findBusinessByCode(String code) {
        String sql = "SELECT * FROM Business WHERE code=?code";
        Query query = businessDAO.nativeQuery(sql, Business.class).setParameter("code", code);
        List<Business> list = query.getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    public CountModel countBusiness(Integer limit) {
        String sql = "SELECT COUNT(code) FROM Business";
        Query query = businessDAO.nativeQuery(sql);
        Integer totalItems = (Integer) query.getSingleResult();
        Integer totalPages = totalItems / limit + 1;
        totalPages = (totalItems % limit) == 0
                ? totalPages - 1 : totalPages;
        CountModel model = new CountModel();
        model.setItemsPerPage(limit);
        model.setTotalItems(totalItems);
        model.setTotalPages(totalPages);
        return model;
    }

    public Business findBusinessById(Integer id) {
        String sql = "SELECT * FROM Business WHERE id=?id";
        Query query = businessDAO.nativeQuery(sql, Business.class).setParameter("id", id);
        List<Business> list = query.getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Business> getBusiness(String search, int page, int limit, CountModel countModel) {
        page = page - 1;
        boolean hasSearch = !StringHelper.isNullOrWhiteSpace(search);
        String whereClause = (hasSearch ? "WHERE [name] LIKE ?search\n" : "");
        //count query
        String countQuery = "SELECT COUNT(code) FROM Business " + whereClause;
        Query query = businessDAO.nativeQuery(countQuery);
        if (hasSearch) {
            query = query.setParameter("search", "%" + search + "%");
        }
        Integer totalItems = (Integer) query.getSingleResult();
        Integer totalPages = totalItems / limit + 1;
        totalPages = (totalItems % limit) == 0
                ? totalPages - 1 : totalPages;
        countModel.setItemsPerPage(limit);
        countModel.setTotalItems(totalItems);
        countModel.setTotalPages(totalPages);

        //get list query
        String sql = "SELECT * FROM Business\n" + whereClause
                + "ORDER BY [name] ASC\n"
                + "OFFSET ?offset ROWS\n"
                + "FETCH NEXT ?limit ROWS ONLY";
        query = businessDAO.nativeQuery(sql, Business.class);
        if (hasSearch) {
            query = query.setParameter("search", "%" + search + "%");
        }
        query = query.setParameter("offset", page * limit);
        query = query.setParameter("limit", limit);
        List<Business> list = query.getResultList();
        return list;
    }

    public ListBusinessModel toListBusinessDTO(List<Business> entities, CountModel countModel) {
        ListBusinessModel o = new ListBusinessModel();
        List<BusinessDTO> dtos = entities.stream().map((t) -> {
            BusinessDTO dto = new BusinessDTO();
            dto.copyFrom(t);
            return dto;
        }).collect(Collectors.toList());
        o.setList(dtos);
        o.setCount(countModel);
        return o;
    }

    public BusinessDTO toBusinessDTO(Business entity) {
        BusinessDTO dto = new BusinessDTO();
        dto.copyFrom(entity);
        return dto;
    }

    public BusinessReviewGeneralModel generalizeReviewDataOfBusiness(Business entity, Map<String, ReviewCategory> cateMap) {
        String sql = "SELECT c.categoryCode,COUNT(c.id) totalReview,CAST(COUNT(c.id) as float)/(SELECT COUNT(id) FROM BusinessReview WHERE businessId=?bId) ratio \n"
                + "FROM CategoriesOfReviews c\n"
                + "INNER JOIN BusinessReview r ON c.reviewId=r.id\n"
                + "WHERE r.businessId = ?bId\n"
                + "GROUP BY c.categoryCode";
        Query query = businessDAO.nativeQuery(sql).setParameter("bId", entity.getId());
        List<Object[]> results = query.getResultList();
        BusinessReviewGeneralModel model = new BusinessReviewGeneralModel();
        model.setBusinessId(entity.getId());
        model.setOverall(entity.getRating() > 3.5);
        List<BadReviewDetailModel> badReviews = results.stream().map((t) -> {
            BadReviewDetailModel m = new BadReviewDetailModel();
            m.setRatio((Double) t[2]);
            m.setTotalReview((Integer) t[1]);
            String cateCode = (String) t[0];
            m.setReviewCateCode(cateCode);
            m.setReviewCateName(cateMap.get(cateCode).getName());
            return m;
        }).collect(Collectors.toList());
        model.setBadReviewDetails(badReviews);
        return model;
    }

    public List<BusinessImageDTO> toListBusinessImageDTO(Collection<BusinessImage> entities) {
        return Arrays.asList(entities.toArray(new BusinessImage[entities.size()])).stream().map((t) -> {
            BusinessImageDTO dto = new BusinessImageDTO();
            dto.copyFrom(t);
            return dto;
        }).collect(Collectors.toList());
    }

    public boolean businessCodeExists(String code) {
        String sql = "SELECT COUNT(code) FROM Business WHERE code=?code";
        Query query = businessDAO.nativeQuery(sql).setParameter("code", code);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public Business createBusiness(Business entity) {
        return businessDAO.create(entity);
    }

}
