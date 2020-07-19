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
        <title>${business.name}</title>
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
        <div>Result for page <span class="current-page">1</span></div>
        <div id="list-container">

        </div>
        <hr/>
        <div id="paging-container">

        </div>

        <script src="${pageContext.servletContext.contextPath}/js/main.js"></script>
        <script>
            var rListXsl = getXMLDoc('${rListXsl}');
            var currentPage = 1;
            loadData();
            function loadData() {
                let listContainer = document.getElementById("list-container");
                listContainer.innerHTML = '<p>...... Loading ......</p>';
                //get business list
                const paramsObject = {
                    page: currentPage.toString(),
                    bId: ${business.id}
                };
                var query = new URLSearchParams(paramsObject).toString();
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "${pageContext.servletContext.contextPath}/api/reviews?" + query.toString());
                xhr.setRequestHeader("Accept", "application/xml");
                xhr.send();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        var status = xhr.status;
                        if (status === 0 || (status >= 200 && status < 400)) {
                            // The xhr has been completed successfully
                            console.log(xhr.responseXML);
                            displayItems(xhr.responseXML);
                            displayPaging(xhr.responseXML.getElementsByTagName("count")[0]);
                            handleLoadedData();
                        } else {
                            alert("Something's wrong");
                        }
                    }
                };
            }

            function handleLoadedData() {
                let currPage = document.querySelector('.current-page');
                currPage.innerHTML = currentPage.toString();
                let curr = document.querySelector('.current');
                if (curr) {
                    curr.setAttribute('href', '#');
                    curr.setAttribute("class", curr.getAttribute("class").replace("current", ""));
                    curr.onclick = () => changePage(parseInt(curr.innerText));
                }
                let newPage = document.querySelector('.p-' + currentPage);
                if (newPage) {
                    newPage.removeAttribute("href");
                    newPage.removeAttribute("onclick");
                    newPage.setAttribute("class", "current " + newPage.getAttribute("class"));
                }
            }

            function changePage(page) {
                currentPage = page;
                loadData();
            }

            function displayPaging(xml) {
                console.log(xml);
                let totalPages = xml.getElementsByTagName("totalPages")[0].textContent;
                totalPages = parseInt(totalPages);
                console.log(totalPages);
                var xmlStr = 'Page: ';
                for (let i = 1; i <= totalPages; i++) {
                    if (currentPage === i)
                        xmlStr += '<a class="current p-' + i + '">' + i + '</a> , ';
                    else
                        xmlStr += '<a class="p-' + i + '" href="#list-container" onclick="changePage(' + i + ')">' + i + '</a> , ';
                }
                let pagingContainer = document.getElementById("paging-container");
                pagingContainer.innerHTML = xmlStr;
            }

            function displayItems(xml) {
                console.log(xml);
                let listContainer = document.getElementById("list-container");
                if (document.implementation && document.implementation.createDocument)
                {
                    let xsltProcessor = new XSLTProcessor();
                    xsltProcessor.importStylesheet(rListXsl);
                    let resultDocument = xsltProcessor.transformToFragment(xml, document);
                    listContainer.innerHTML = '';
                    listContainer.append(resultDocument);
                }
            }

        </script>
    </body>
</html>
