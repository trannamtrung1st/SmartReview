/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.webapp.apis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import smartreview.business.dtos.BusinessDTO;
import smartreview.business.models.CountModel;
import smartreview.business.models.ListBusinessModel;
import smartreview.business.services.BusinessService;
import smartreview.data.EntityContext;
import smartreview.data.daos.BusinessDAO;
import smartreview.data.models.Business;
import smartreview.helper.XMLHelper;
import smartreview.webapp.controllers.BaseController;

/**
 *
 * @author TNT
 */
public class BusinessApiController extends BaseController {

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
        String pathInfo = getFinalPathInfo(request);
        try {
            if (pathInfo == null) {
                handleGetBusinessList(request, response);
            } else {
                String pathVal = getPathInfo(request, 1);
                if (pathVal.equals("count")) {
                    handleCountBusiness(request, response);
                } else {
                    handleGetBusinessDetail(request, response, pathVal);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void handleCountBusiness(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try (EntityContext eContext = EntityContext.newInstance(); PrintWriter out = response.getWriter();) {
            Integer limit = getIntegerParamter(request, "limit");
            if (limit == null) {
                limit = 10;
            }
            EntityManager em = eContext.getEntityManager();
            BusinessService businessService = new BusinessService(em, new BusinessDAO(em));
            CountModel model = businessService.countBusiness(limit);
            String xml = XMLHelper.marshall(model, CountModel.class);
            out.print(xml);
        }
    }

    protected void handleGetBusinessList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try (EntityContext eContext = EntityContext.newInstance(); PrintWriter out = response.getWriter();) {
            EntityManager em = eContext.getEntityManager();
            BusinessService businessService = new BusinessService(em, new BusinessDAO(em));
            String search = getStringParamter(request, "search");
            Integer page = getIntegerParamter(request, "page");
            if (page == null) {
                page = 1;
            }
            Integer limit = getIntegerParamter(request, "limit");
            if (limit == null) {
                limit = 10;
            }
            CountModel countModel = new CountModel();
            List<Business> entities = businessService.getBusiness(search, page, limit, countModel);
            ListBusinessModel dto = businessService.toListBusinessModel(entities, countModel);
            String xml = XMLHelper.marshall(dto, ListBusinessModel.class);
            out.print(xml);
        }
    }

    protected void handleGetBusinessDetail(HttpServletRequest request, HttpServletResponse response, String pathVal)
            throws ServletException, IOException, Exception {
        Integer id = Integer.parseInt(pathVal);
        try (EntityContext eContext = EntityContext.newInstance(); PrintWriter out = response.getWriter();) {
            EntityManager em = eContext.getEntityManager();
            BusinessService businessService = new BusinessService(em, new BusinessDAO(em));
            Business entity = businessService.findBusinessById(id);
            if (entity == null) {
                response.setStatus(HttpStatus.SC_NOT_FOUND);
                return;
            }
            BusinessDTO dto = businessService.toBusinessDTO(entity);
            String xml = XMLHelper.marshall(dto, BusinessDTO.class);
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
