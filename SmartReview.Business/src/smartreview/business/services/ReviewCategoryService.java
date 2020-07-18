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
import smartreview.data.daos.BusinessDAO;
import smartreview.data.models.Business;

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
