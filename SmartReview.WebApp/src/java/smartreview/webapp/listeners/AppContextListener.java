/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.webapp.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import smartreview.business.Settings;
import smartreview.business.services.ReviewCategoryService;
import smartreview.data.EntityContext;
import smartreview.data.daos.ReviewCategoryDAO;
import smartreview.data.models.ReviewCategory;
import smartreview.helper.FileHelper;

/**
 * Web application lifecycle listener.
 *
 * @author TNT
 */
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sContext = sce.getServletContext();
        Settings.baseApiUrl = sContext.getInitParameter("baseApiUrl");
        Settings.tripAdvisorParserLocation = sContext.getInitParameter("tripAdvisorParserLocation");
        
        
        try (EntityContext context = EntityContext.newInstance()) {
            EntityManager em = context.getEntityManager();
            ReviewCategoryService cateService = new ReviewCategoryService(em, new ReviewCategoryDAO(em));
            List<ReviewCategory> categories = cateService.getAll();
            Map<String, ReviewCategory> cateMap = new HashMap<>();
            categories.forEach((t) -> {
                cateMap.put(t.getCode(), t);
            });
            sContext.setAttribute("cateMap", cateMap);

            //business-list.xsl
            String path = sContext.getRealPath("/WEB-INF/business-list.xsl");
            String bListXsl = FileHelper.readContent(path).replace("\n", "");
            sContext.setAttribute("bListXsl", bListXsl);
            
            //review-list.xsl
            path = sContext.getRealPath("/WEB-INF/review-list.xsl");
            String rListXsl = FileHelper.readContent(path).replace("\n", "");
            sContext.setAttribute("rListXsl", rListXsl);

            //business-detail.xsl
            path = sContext.getRealPath("/WEB-INF/business-detail.xsl");
            String bDetailXsl = FileHelper.readContent(path).replace("\n", "");
            sContext.setAttribute("bDetailXsl", bDetailXsl);
        } catch (Exception e) {
            Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
