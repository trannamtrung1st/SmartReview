/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;
import smartreview.business.dtos.BusinessDTO;
import smartreview.business.dtos.CountBusinessModel;
import smartreview.business.dtos.ListBusinessDTO;
import smartreview.data.daos.BusinessDAO;
import smartreview.data.models.Business;
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

    public CountBusinessModel countBusiness(Integer limit) {
        String sql = "SELECT COUNT(code) FROM Business";
        Query query = businessDAO.nativeQuery(sql);
        Integer totalItems = (Integer) query.getSingleResult();
        Integer totalPages = totalItems / limit + 1;
        totalPages = (totalItems % limit) == 0
                ? totalPages - 1 : totalPages;
        CountBusinessModel model = new CountBusinessModel();
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

    public List<Business> getBusiness(String search, int page, int limit, CountBusinessModel countModel) {
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

    public ListBusinessDTO toListBusinessDTO(List<Business> entities, CountBusinessModel countModel) {
        ListBusinessDTO o = new ListBusinessDTO();
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
