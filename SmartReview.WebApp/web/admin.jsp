<%-- 
    Document   : admin
    Created on : Jul 18, 2020, 11:31:42 PM
    Author     : TNT
--%>

<%@page import="smartreview.business.Constants"%>
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
            <a href="${pageContext.servletContext.contextPath}/">Restaurants list</a> --- <a style="font-weight: bold;">Administration</a>  
        </div>
        <hr/>
        <h1 style="color:red;">Welcome to SmartReview admin page!</h1>
        <h3>Crawl list of restaurants</h3>
        <form id="form-parse-list" onsubmit="return false;">
            <div>
                From: 
                <select name="parserCode">
                    <option value="trip-advisor">TripAdvisor</option>
                    <option value="yelp">Yelp</option>
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
            <button type="button" onclick="startParser()">START</button>
            <button type="button" onclick="stopParser()">STOP</button>
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
        <h3>OUTPUT</h3>
        <form id="output-form" onsubmit="return false;">
            <select name="parserCode">
                <option value="trip-advisor">TripAdvisor</option>
                <option value="yelp">Yelp</option>
            </select>
            <button type="button" onclick="startGetOutput()">START GET OUTPUT</button>
            <button type="button" onclick="stopGetOutput()">STOP GET OUTPUT</button>
            <button type="button" onclick="clearOutput()">CLEAR OUTPUT</button>
            <br/><br/>
            <div>Current output: <span class="current-parser"></span></div>
            <textarea id="output" placeholder="Process output" style="width:100%;height:400px" readonly="true"></textarea>
        </form>

        <script>
            var getOutputInterval;
            function startGetOutput(fromParser) {
                let output = document.getElementById("output");
                output.value = '';
                var parserCode = fromParser ? fromParser : document.querySelector('#output-form select[name=parserCode]').value;
                let currentParser = document.querySelector(".current-parser");
                currentParser.innerHTML = parserCode;
                const paramsObject = {
                    command: "<%=Constants.COMMAND_OUTPUT%>",
                    parserCode: parserCode,
                };
                var query = new URLSearchParams(paramsObject).toString();
                if (getOutputInterval)
                    clearInterval(getOutputInterval);
                getOutputInterval = setInterval(() => {
                    getOutput(query);
                }, 3000);
            }

            function clearOutput() {
                let output = document.getElementById("output");
                output.value = '';
                output.scrollTo(0, output.scrollHeight);
            }

            function stopGetOutput() {
                if (getOutputInterval)
                    clearInterval(getOutputInterval);
            }

            function getOutput(query) {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "${pageContext.servletContext.contextPath}/api/admin?" + query.toString());
                xhr.setRequestHeader("Accept", "application/xml");
                xhr.send();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        var status = xhr.status;
                        if (status === 0 || (status >= 200 && status < 400)) {
                            // The xhr has been completed successfully
                            displayOutput(xhr.responseText);
                        } else {
                            alert("Something's wrong");
                        }
                    }
                };
            }

            function displayOutput(text) {
                let output = document.getElementById("output");
                output.value = text;
                output.scrollTo(0, output.scrollHeight);
            }

            function startParser() {
                var parserCode = document.querySelector('#form-parse-list select[name=parserCode]').value;
                var fromPage = document.querySelector('#form-parse-list input[name=fromPage]').value;
                var toPage = document.querySelector('#form-parse-list input[name=toPage]').value;
                var maxReviewParsedPages = document.querySelector('#form-parse-list input[name=maxReviewParsedPages]').value;
                const paramsObject = {
                    command: "<%=Constants.COMMAND_START%>",
                    parserCode: parserCode,
                    maxReviewParsedPages,
                    toPage,
                    fromPage
                };
                var query = new URLSearchParams(paramsObject).toString();
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "${pageContext.servletContext.contextPath}/api/admin?" + query.toString());
                xhr.setRequestHeader("Accept", "application/xml");
                xhr.send();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        var status = xhr.status;
                        if (status === 0 || (status >= 200 && status < 400)) {
                            startGetOutput(parserCode);
                            alert("Start successfully. Already start get output");
                        } else {
                            alert(xhr.responseText ? xhr.responseText : "Something's wrong");
                        }
                    }
                };
            }

            function stopParser() {
                var parserCode = document.querySelector('#form-parse-list select[name=parserCode]').value;
                const paramsObject = {
                    command: '<%=Constants.COMMAND_STOP%>',
                    parserCode: parserCode,
                };
                var query = new URLSearchParams(paramsObject).toString();
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "${pageContext.servletContext.contextPath}/api/admin?" + query.toString());
                xhr.setRequestHeader("Accept", "application/xml");
                xhr.send();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        var status = xhr.status;
                        if (status === 0 || (status >= 200 && status < 400)) {
                            stopGetOutput();
                            alert("Stop successfully. also stop get output");
                        } else {
                            alert(xhr.responseText ? xhr.responseText : "Something's wrong");
                        }
                    }
                };
            }
        </script>
    </body>
</html>
