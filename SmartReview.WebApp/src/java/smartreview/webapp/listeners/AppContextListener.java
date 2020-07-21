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
import javax.xml.bind.JAXBException;
import smartreview.webapp.Settings;
import smartreview.services.ReviewCategoryService;
import smartreview.models.EntityContext;
import smartreview.daos.ReviewCategoryDAO;
import smartreview.models.ReviewCategory;
import smartreview.helper.FileHelper;
import smartreview.helper.XMLHelper;
import smartreview.webapp.ObjectFactory;
import smartreview.webapp.WebConfig;

/**
 * Web application lifecycle listener.
 *
 * @author TNT
 */
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sContext = sce.getServletContext();
        WebConfig webConfig;
        String webConfigPath = sContext.getRealPath("/WEB-INF/web-config.xml");
        try {
            webConfig = XMLHelper.unmarshallDocFile(webConfigPath, ObjectFactory.class);
            sContext.setAttribute("webConfig", webConfig);
            Settings.baseApiUrl = webConfig.getBaseApiUrl();
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }

        try (EntityContext context = EntityContext.newInstance()) {
            EntityManager em = context.getEntityManager();
            ReviewCategoryService cateService = new ReviewCategoryService(em, new ReviewCategoryDAO(em));
            if (!cateService.anyExisted()) {
                em.getTransaction().begin();
                webConfig.getReviewCateMap().getItem().forEach((t) -> {
                    ReviewCategory entity = new ReviewCategory();
                    entity.setCode(t.getValue().getCode());
                    entity.setName(t.getValue().getName());
                    cateService.createReviewCategory(entity);
                });
                em.getTransaction().commit();
            }
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
