/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.data.daos;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author TNT
 * @param <T>
 */
public abstract class BaseDAO<T> {

    protected EntityManager eManager;

    public BaseDAO(EntityManager eManager) {
        this.eManager = eManager;
    }

    public T create(T entity) {
        eManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return eManager.merge(entity);
    }

    public T delete(T entity) {
        eManager.remove(entity);
        return entity;
    }

    public T findById(Class<T> eClass, Object id) {
        return eManager.find(eClass, id);
    }

    public Query nativeQuery(String sql) {
        return eManager.createNativeQuery(sql);
    }

    public Query nativeQuery(String sql, Class<T> resultClass) {
        return eManager.createNativeQuery(sql, resultClass);
    }
}
