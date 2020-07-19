/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.webapp.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
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

        try {
            //business-paging.xsl
            String path = sContext.getRealPath("/WEB-INF/business-list.xsl");
            String bListXsl = FileHelper.readContent(path).replace("\n", "");
            sContext.setAttribute("bListXsl", bListXsl);
        } catch (Exception e) {
            Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
