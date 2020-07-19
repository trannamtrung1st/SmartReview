/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.webapp.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import smartreview.helper.StringHelper;

/**
 *
 * @author TNT
 */
public abstract class BaseController extends HttpServlet {

    protected String getFinalPathInfo(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.isEmpty() || pathInfo.length() == 1) {
            return null;
        }
        return pathInfo;
    }

    protected String getPathInfo(HttpServletRequest request, int part) {
        return request.getPathInfo().split("/")[part];
    }

    protected String getStringParamter(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    protected Integer getIntegerParamter(HttpServletRequest request, String name) {
        String paramStr = request.getParameter(name);
        Integer val = !StringHelper.isNullOrWhiteSpace(paramStr) ? Integer.parseInt(paramStr) : null;
        return val;
    }

    protected Boolean getBooleanParamter(HttpServletRequest request, String name) {
        String paramStr = request.getParameter(name);
        Boolean val = !StringHelper.isNullOrWhiteSpace(paramStr) ? Boolean.parseBoolean(paramStr) : null;
        return val;
    }
}
