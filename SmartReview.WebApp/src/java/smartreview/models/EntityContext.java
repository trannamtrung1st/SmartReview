package smartreview.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Closeable;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author TNT
 */
public class EntityContext implements Closeable {

    public static final String PU_NAME = "SmartReview.WebAppPU";
    protected EntityManager entityManager;
    protected EntityManagerFactory entityManagerFactory;

    public static EntityContext newInstance() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(PU_NAME);
        EntityManager entitymanager = emfactory.createEntityManager();
        EntityContext context = new EntityContext();
        context.entityManager = entitymanager;
        context.entityManagerFactory = emfactory;
        return context;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @Override
    public void close() throws IOException {
        entityManager.close();
        entityManagerFactory.close();
    }

}
