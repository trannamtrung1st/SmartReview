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
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import smartreview.data.daos.ParserInfoDAO;
import smartreview.data.models.ParserInfo;

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

    public void writeOutput(ParserInfo entity, String output) {
        String newOutput = entity.getCurrentOutput() + "\n" + output;
        entity.setCurrentOutput(newOutput);
    }

    public ParserInfo findParserInfoByCode(String parserCode, boolean refresh) {
        String sql = "SELECT * FROM ParserInfo WHERE parserCode=?code";
        Query query = parserInfoDAO.nativeQuery(sql, ParserInfo.class).setParameter("code", parserCode);
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
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
