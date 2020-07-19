<%-- 
    Document   : detail
    Created on : Jul 18, 2020, 11:43:57 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${businessName}</title>
        <style>
            .label {
                font-weight: bold;
            }

            .list-item{
                margin: 30px 0;
            }
        </style>
    </head>
    <body>
        <div><a href="${pageContext.servletContext.contextPath}/">Back to home</a></div>
        <x:transform doc="${xmlData}" xslt="${bDetailXsl}">
        </x:transform>
        <hr/>
        <h2>List reviews</h2>
        <div id="list-container">
            <div class="list-item">
                <div>
                    <img src="/aa.jpg" style="width:50px;height:50px;vertical-align: middle"/> <span>UserA</span>
                </div>
                <div><span class="label">Rating:</span> 4.5</div>
                <div><span class="label">Review date:</span> June 23, 2020</div>
                <div>
                    <span class="label">This is review title</span>
                </div>
                <div>This is the review content</div>
                <div><span class="label">Overall:</span> Good</div>
                <div><span class="label">Bad reviews:</span> Bad service, Rude service</div>
            </div>
            <div class="list-item">
                <div>
                    <img src="/aa.jpg" style="width:50px;height:50px;vertical-align: middle"/> <span>UserA</span>
                </div>
                <div><span class="label">Rating:</span> 4.5</div>
                <div><span class="label">Review date:</span> June 23, 2020</div>
                <div>
                    <span class="label">This is review title</span>
                </div>
                <div>This is the review content</div>
                <div><span class="label">Overall:</span> Good</div>
                <div><span class="label">Bad reviews:</span> Bad service, Rude service</div>
            </div>
            <div class="list-item">
                <div>
                    <img src="/aa.jpg" style="width:50px;height:50px;vertical-align: middle"/> <span>UserA</span>
                </div>
                <div><span class="label">Rating:</span> 4.5</div>
                <div><span class="label">Review date:</span> June 23, 2020</div>
                <div>
                    <span class="label">This is review title</span>
                </div>
                <div>This is the review content</div>
                <div><span class="label">Overall:</span> Good</div>
                <div><span class="label">Bad reviews:</span> Bad service, Rude service</div>
            </div>
        </div>
        <hr/>
        <div>
            Page: 
            <a href="#">1</a> , 
            <a >2</a> , 
            <a href="#">3</a> , 
            <a href="#">4</a> , 
            <a href="#">5</a> , 
            <a href="#">6</a> , 
        </div>
    </body>
</html>
