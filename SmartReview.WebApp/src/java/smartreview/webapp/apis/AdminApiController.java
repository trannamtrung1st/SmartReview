/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.webapp.apis;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import smartreview.business.Constants;
import smartreview.business.Settings;
import smartreview.business.services.ParserInfoService;
import smartreview.data.EntityContext;
import smartreview.data.daos.ParserInfoDAO;
import smartreview.data.models.ParserInfo;
import smartreview.helper.ProcessHelper;
import smartreview.webapp.WebConfig;
import smartreview.webapp.controllers.BaseController;

/**
 *
 * @author TNT
 */
public class AdminApiController extends BaseController {

    protected void handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String command = getStringParamter(request, "command");
            switch (command) {
                case Constants.COMMAND_START:
                    startParser(request, response);
                    break;
                case Constants.COMMAND_STOP:
                    stopParser(request, response);
                    break;
                case Constants.COMMAND_OUTPUT:
                    handleGetOutput(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void stopParser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        try (EntityContext context = EntityContext.newInstance()) {
            EntityManager em = context.getEntityManager();
            ParserInfoService pInfoService = new ParserInfoService(em, new ParserInfoDAO(em));
            String parserCode = getStringParamter(request, "parserCode");
            ParserInfo pInfo = pInfoService.findParserInfoByCode(parserCode, true);
            if (!pInfo.getCurrentCommand().startsWith(Constants.COMMAND_START)) {
                response.setStatus(HttpStatus.SC_BAD_REQUEST);
                PrintWriter out = response.getWriter();
                out.write("Not running");
                out.close();
                return;
            }
            em.getTransaction().begin();
            pInfo.setCurrentCommand(Constants.COMMAND_STOP);
            em.getTransaction().commit();
        }
    }

    protected void startParser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sContext = getServletContext();
        WebConfig webConfig = (WebConfig) sContext.getAttribute("webConfig");
        response.setContentType("text/plain");
        try (EntityContext context = EntityContext.newInstance()) {
            EntityManager em = context.getEntityManager();
            ParserInfoService pInfoService = new ParserInfoService(em, new ParserInfoDAO(em));
            String parserCode = getStringParamter(request, "parserCode");
            ParserInfo pInfo = pInfoService.findParserInfoByCode(parserCode, true);
            if (!pInfo.getCurrentCommand().equals(Constants.COMMAND_STOP)) {
                response.setStatus(HttpStatus.SC_BAD_REQUEST);
                PrintWriter out = response.getWriter();
                out.write("Already started");
                out.close();
                return;
            }

            Integer fromPage = getIntegerParamter(request, "fromPage");
            Integer toPage = getIntegerParamter(request, "toPage");
            Integer maxReviewParsedPages = getIntegerParamter(request, "maxReviewParsedPages");

            em.getTransaction().begin();
            pInfo.setFromPage(fromPage);
            pInfo.setToPage(toPage);
            pInfo.setMaxParsedReviewsPage(maxReviewParsedPages);
            pInfo.setCurrentCommand(Constants.COMMAND_START);
            pInfo.setCurrentOutput("");
            em.getTransaction().commit();

            for (WebConfig.Parsers.Item item : webConfig.getParsers().getItem()) {
                if (item.getCode().equals(parserCode)) {
                    String location = item.getLocation();
                    ProcessHelper.startParser(location);
                }
            }
        }
    }

    protected void handleGetOutput(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        try (EntityContext context = EntityContext.newInstance(); PrintWriter out = response.getWriter();) {
            EntityManager em = context.getEntityManager();
            ParserInfoService pInfoService = new ParserInfoService(em, new ParserInfoDAO(em));
            String parserCode = getStringParamter(request, "parserCode");
            ParserInfo pInfo = pInfoService.findParserInfoByCode(parserCode, true);
            String output = pInfo.getCurrentOutput();
            output = output != null ? output : "";
            out.write(output);
        }
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
        handleCommand(request, response);
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
        handleCommand(request, response);
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
