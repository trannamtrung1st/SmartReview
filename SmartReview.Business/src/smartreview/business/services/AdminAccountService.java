/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.services;

import java.util.List;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import smartreview.data.daos.AdminAccountDAO;
import smartreview.data.models.AdminAccount;

/**
 *
 * @author TNT
 */
public class AdminAccountService {

    protected AdminAccountDAO adminAccountDAO;
    protected EntityManager entityManager;

    public AdminAccountService(EntityManager entityManager, AdminAccountDAO adminAccountDAO) {
        this.entityManager = entityManager;
        this.adminAccountDAO = adminAccountDAO;
    }

    public AdminAccount findAdminAccountByLogin(String username, String password) {
        String sql = "SELECT * FROM AdminAccount WHERE username=?username AND password=?password";
        Query query = adminAccountDAO.nativeQuery(sql, AdminAccount.class).setParameter("username", username)
                .setParameter("password", password);
        List<AdminAccount> list = query.getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean anyExisted() {
        String sql = "SELECT COUNT(username) FROM AdminAccount";
        Query query = adminAccountDAO.nativeQuery(sql);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public AdminAccount createAdminAccount(String username, String password) {
        return adminAccountDAO.create(new AdminAccount(username, password));
    }

}
