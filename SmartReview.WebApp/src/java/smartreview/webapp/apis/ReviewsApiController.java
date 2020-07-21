/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.webapp.apis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import smartreview.dtos.CountModel;
import smartreview.dtos.ListReviewModel;
import smartreview.services.ReviewService;
import smartreview.models.EntityContext;
import smartreview.daos.BusinessReviewDAO;
import smartreview.daos.ReviewCategoryDAO;
import smartreview.models.BusinessReview;
import smartreview.models.ReviewCategory;
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
