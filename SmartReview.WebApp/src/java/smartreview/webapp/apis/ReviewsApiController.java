/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.webapp.apis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import smartreview.business.models.CountModel;
import smartreview.business.models.ListReviewModel;
import smartreview.business.services.ReviewService;
import smartreview.data.EntityContext;
import smartreview.data.daos.BusinessReviewDAO;
import smartreview.data.daos.ReviewCategoryDAO;
import smartreview.data.models.BusinessReview;
import smartreview.data.models.ReviewCategory;
import smartreview.helper.StringHelper;
import smartreview.helper.XMLHelper;
import smartreview.webapp.controllers.BaseController;

/**
 *
 * @author TNT
 */
public class ReviewsApiController extends BaseController {

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
        response.setContentType("application/xml");
        try {
            handleGetReviewList(request, response);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void handleGetReviewList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try (EntityContext eContext = EntityContext.newInstance(); PrintWriter out = response.getWriter();) {
            EntityManager em = eContext.getEntityManager();
            ReviewService reviewService = new ReviewService(em, new BusinessReviewDAO(em), new ReviewCategoryDAO(em));
            Integer page = getIntegerParamter(request, "page");
            Integer bId = getIntegerParamter(request, "bId");
            if (page == null) {
                page = 1;
            }
            Integer limit = getIntegerParamter(request, "limit");
            if (limit == null) {
                limit = 10;
            }
            CountModel countModel = new CountModel();
            List<BusinessReview> entities = reviewService.getReviewsOfBusiness(bId, page, limit, countModel);
            Map<String, ReviewCategory> cateMap = (Map<String, ReviewCategory>) getServletContext().getAttribute("cateMap");
            ListReviewModel dto = reviewService.toListReviewModel(entities, countModel, cateMap);
            dto.getList().forEach((t) -> {
                if (StringHelper.isNullOrWhiteSpace(t.getUserImage())) {
                    t.setUserImage(getServletContext().getContextPath() + "/imgs/avatar.jpg");
                }
            });
            String xml = XMLHelper.marshall(dto, ListReviewModel.class);
            out.print(xml);
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
