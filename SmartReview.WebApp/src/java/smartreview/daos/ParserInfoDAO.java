/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.daos;

import javax.persistence.EntityManager;
import smartreview.models.ParserInfo;

/**
 *
 * @author TNT
 */
public class ParserInfoDAO extends BaseDAO<ParserInfo> {

    public ParserInfoDAO(EntityManager eManager) {
        super(eManager);
    }

}
