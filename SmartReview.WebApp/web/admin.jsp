<%-- 
    Document   : admin
    Created on : Jul 18, 2020, 11:31:42 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <a href="#">Restaurants list</a> --- <a style="font-weight: bold;">Administration</a>  
        </div>
        <hr/>
        <h1 style="color:red;">Welcome to SmartReview admin page!</h1>
        <h3>Crawl list of restaurants</h3>
        <form id="form-parse-list">
            <div>
                From: 
                <select name="from">
                    <option>TripAdvisor</option>
                    <option>Yelp</option>
                </select>
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
            <button type="button">START</button>
        </form>
        <hr/>
        <h3>Crawl single restaurant</h3>
        <form id="form-parse-single">
            <div>
                Page url: <input type="text" name="pageUrl"/>
            </div>
            <div>
                Max review parsed pages: <input type="number" name="maxReviewParsedPages"/>
            </div>
            <button type="button">START</button>
        </form>
        <hr/>
        <h3>OUTPUT</h3>
        <textarea id="output" placeholder="Process output" style="width:100%;height:400px"></textarea>
    </body>
</html>
