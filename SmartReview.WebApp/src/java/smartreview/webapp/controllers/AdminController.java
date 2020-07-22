/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.webapp.controllers;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import smartreview.services.AdminAccountService;
import smartreview.models.EntityContext;
import smartreview.daos.AdminAccountDAO;
import smartreview.daos.ParserInfoDAO;
import smartreview.models.AdminAccount;
import smartreview.models.ParserInfo;
import smartreview.parser.tripadvisor.TripAdvisorParser;
import smartreview.parser.yelp.YelpParser;
import smartreview.services.ParserInfoService;
import smartreview.webapp.Constants;
import smartreview.webapp.WebConfig;

/**
 *
 * @author TNT
 */
@MultipartConfig
public class AdminController extends BaseController {

    protected final String ADMIN = "/admin.jsp";
    protected final String ADMIN_CONTROLLER = "/admin";
    protected final String LOGIN = "/login.jsp";

    protected void startParser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ServletContext sContext = getServletContext();
        WebConfig webConfig = (WebConfig) sContext.getAttribute("webConfig");
        response.setContentType("text/plain");
        String parserCode = getStringParamter(request, "parserCode");
        boolean found = true;
        try (EntityContext context = EntityContext.newInstance()) {
            EntityManager em = context.getEntityManager();
            ParserInfoService pInfoService = new ParserInfoService(em, new ParserInfoDAO(em));
            ParserInfo pInfo = pInfoService.findParserInfoByCode(parserCode, true);
            Integer maxBusinessCount = getIntegerParamter(request, "maxBusinessCount");
            Integer fromPage = getIntegerParamter(request, "fromPage");
            Integer toPage = getIntegerParamter(request, "toPage");
            Integer maxReviewParsedPages = getIntegerParamter(request, "maxReviewParsedPages");

            if (pInfo != null) {
                em.getTransaction().begin();
                pInfo.setFromPage(fromPage);
                pInfo.setToPage(toPage);
                pInfo.setMaxParsedReviewsPage(maxReviewParsedPages);
                em.getTransaction().commit();
            }
            switch (parserCode) {
                case Constants.TRIP_ADVISOR_CODE: {
                    String folderPath = sContext.getRealPath("/WEB-INF/" + parserCode);
                    TripAdvisorParser.start(fromPage, toPage, maxReviewParsedPages, folderPath, maxBusinessCount);
                }
                break;
                case Constants.YELP_CODE: {
                    String folderPath = sContext.getRealPath("/WEB-INF/" + parserCode);
                    YelpParser.start(fromPage, toPage, maxReviewParsedPages, folderPath, maxBusinessCount);
                }
                break;
                default:
                    found = false;
            }
        }
        if (found) {
            request.setAttribute("message", "Successfully parsed " + parserCode);
        } else {
            request.setAttribute("message", "Not found " + parserCode);
        }
        request.getRequestDispatcher(ADMIN).forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminAccount acc = (AdminAccount) request.getSession(true).getAttribute("user");
        if (acc == null) {
            dispatchLogin(request, response);
            return;
        }
        String pathInfo = getFinalPathInfo(request);
        try {
            if (pathInfo == null) {
                dispatchAdmin(request, response);
            } else {
                String pathVal = getPathInfo(request, 1);
                if (pathVal.equals("logout")) {
                    handleLogout(request, response);
                } else if (pathVal.equals("start-parser")) {
                    startParser(request, response);
                } else {
                    response.setStatus(HttpStatus.SC_NOT_FOUND);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = getStringParamter(request, "username");
        String password = getStringParamter(request, "password");
        try (EntityContext eContext = EntityContext.newInstance();) {
            EntityManager em = eContext.getEntityManager();
            AdminAccountService adminService = new AdminAccountService(em, new AdminAccountDAO(em));
            if (!adminService.anyExisted()) {
                em.getTransaction().begin();
                adminService.createAdminAccount(username, password);
                em.getTransaction().commit();
            }
            AdminAccount entity = adminService.findAdminAccountByLogin(username, password);
            if (entity == null) {
                request.setAttribute("message", "Invalid username or password");
                dispatchLogin(request, response);
                return;
            }
            request.getSession(true).setAttribute("user", entity);
            response.sendRedirect(getServletContext().getContextPath() + ADMIN_CONTROLLER);
        }
    }

    protected void dispatchLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext sContext = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher(LOGIN).forward(request, response);
    }

    protected void dispatchAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext sContext = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher(ADMIN).forward(request, response);
    }

    protected void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("user", null);
        response.sendRedirect(getServletContext().getContextPath() + ADMIN_CONTROLLER);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
