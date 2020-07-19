/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.webapp.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import org.apache.hc.core5.http.HttpStatus;
import smartreview.business.dtos.BusinessDTO;
import smartreview.business.dtos.BusinessImageDTO;
import smartreview.business.models.BusinessDetailModel;
import smartreview.business.models.BusinessReviewGeneralModel;
import smartreview.business.services.BusinessService;
import smartreview.data.EntityContext;
import smartreview.data.daos.BusinessDAO;
import smartreview.data.models.Business;
import smartreview.data.models.ReviewCategory;
import smartreview.helper.FileHelper;
import smartreview.helper.XMLHelper;

/**
 *
 * @author TNT
 */
@MultipartConfig
public class BusinessDetailController extends BaseController {

    protected final String DETAIL = "/detail.jsp";

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
        String pathVal = getPathInfo(request, 1);
        try {
            handleGetBusinessDetail(request, response, pathVal);
            dispatch(request, response);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void handleGetBusinessDetail(HttpServletRequest request, HttpServletResponse response, String pathVal)
            throws ServletException, IOException, Exception {
        Integer id = Integer.parseInt(pathVal);
        try (EntityContext eContext = EntityContext.newInstance();) {
            EntityManager em = eContext.getEntityManager();
            BusinessService businessService = new BusinessService(em, new BusinessDAO(em));
            Business entity = businessService.findBusinessById(id);
            if (entity == null) {
                response.setStatus(HttpStatus.SC_NOT_FOUND);
                return;
            }
            Map<String, ReviewCategory> cateMap = (Map<String, ReviewCategory>) getServletContext().getAttribute("cateMap");
            BusinessReviewGeneralModel reviewGeneral = businessService.generalizeReviewDataOfBusiness(entity, cateMap);
            BusinessDTO dto = businessService.toBusinessDTO(entity);
            List<BusinessImageDTO> images = businessService.toListBusinessImageDTO(entity.getBusinessImageCollection());
            List<BusinessImageDTO> filterImages = new ArrayList<>();
            if (images.size() > 8) {
                for (int i = 0; i < 8; i++) {
                    filterImages.add(images.get(i));
                }
            } else {
                filterImages = images;
            }
            dto.setBusinessImages(filterImages);

            BusinessDetailModel detailModel = new BusinessDetailModel();
            detailModel.setBusiness(dto);
            detailModel.setReviewGeneral(reviewGeneral);
            String xml = XMLHelper.marshall(detailModel, BusinessDetailModel.class);
            FileHelper.writeToFile(xml, "T:\\FPT\\STUDY\\SUMMER2020\\PRX\\Project\\SmartReview\\Source\\SmartReview.WebApp\\temp.xml");
            request.setAttribute("xmlData", xml);
            request.setAttribute("businessName", dto.getName());
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
        response.setStatus(HttpStatus.SC_NOT_FOUND);
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext sContext = getServletContext();
        String bDetailXsl = (String) sContext.getAttribute("bDetailXsl");
        request.setAttribute("bDetailXsl", bDetailXsl);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher(DETAIL).forward(request, response);
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
