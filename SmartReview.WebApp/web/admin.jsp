<%-- 
    Document   : admin
    Created on : Jul 18, 2020, 11:31:42 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administration</title>
        <style>
            .label {
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div style="text-align: right">
            <a href="${pageContext.servletContext.contextPath}/">Restaurants list</a> --- <a style="font-weight: bold;">Administration</a>
            <c:if test="${user!=null}">
                --- <a href="${pageContext.servletContext.contextPath}/admin/logout">Log out</a>
            </c:if>
        </div>
        <hr/>
        <h1 style="color:red;">Welcome to SmartReview admin page!</h1>
        <div style="color:red">${message}</div>
        <h3>Crawl list of restaurants</h3>
        <form id="form-parse-list" method="GET" action="${pageContext.servletContext.contextPath}/admin/start-parser">
            <div>
                From: 
                <select name="parserCode">
                    <option value="trip-advisor">TripAdvisor</option>
                    <option value="yelp">Yelp</option>
                </select>
            </div>
            <div>
                Max business count: <input type="number" name="maxBusinessCount"/>
            </div>
            <div>
                From page: <input type="number" name="fromPage"/>
            </div>
            <div>
                To page: <input type="number" name="toPage"/>
            </div>
            <div>
                Max review parsed pages: <input type="number" name="maxReviewParsedPages"/>
            </div>
            <button type="submit">START</button>
        </form>
        <hr/>
        <h3>Crawl single restaurant</h3>
        <form id="form-parse-single" onsubmit="return false;">
            <div>
                Page url: <input type="text" name="pageUrl"/>
            </div>
            <div>
                Max review parsed pages: <input type="number" name="maxReviewParsedPages"/>
            </div>
            <button type="button">START</button>
            <button type="button">STOP</button>
        </form>
        <hr/>
        <script>
            if (location.href.indexOf("/start-parser") > -1)
                window.history.pushState({}, document.title, location.href.substring(0, location.href.indexOf('/start-parser')));
        </script>
    </body>
</html>
