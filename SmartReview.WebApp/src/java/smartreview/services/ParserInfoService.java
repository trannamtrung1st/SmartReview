/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.services;

import java.util.List;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import smartreview.daos.ParserInfoDAO;
import smartreview.models.ParserInfo;

/**
 *
 * @author TNT
 */
public class ParserInfoService {

    protected ParserInfoDAO parserInfoDAO;
    protected EntityManager entityManager;

    public ParserInfoService(EntityManager entityManager, ParserInfoDAO parserInfoDAO) {
        this.entityManager = entityManager;
        this.parserInfoDAO = parserInfoDAO;
    }
    
    public ParserInfo findParserInfoByCode(String parserCode, boolean refresh) {
        String sql = "SELECT * FROM ParserInfo WHERE parserCode=?code";
        Query query = parserInfoDAO.nativeQuery(sql, ParserInfo.class).setParameter("code", parserCode);
        if (refresh) {
            query.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        }
        List<ParserInfo> list = query.getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean parserInfoCodeExists(String parserCode) {
        String sql = "SELECT COUNT(code) FROM ParserInfo WHERE parserCode=?code";
        Query query = parserInfoDAO.nativeQuery(sql).setParameter("code", parserCode);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public ParserInfo createParserInfo(ParserInfo entity) {
        return parserInfoDAO.create(entity);
    }

}
