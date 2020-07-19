<%-- 
    Document   : index
    Created on : Jul 18, 2020, 11:17:32 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurants list</title>
        <style>
            .label {
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div style="text-align: right">
            <a style="font-weight: bold;">Restaurants list</a> --- <a href="#">Administration</a>  
        </div>
        <hr/>
        <h1 style="color:red">List of restaurants</h1>
        <form id="search-form">
            <div>
                Search: <input type="text" name="search" placeholder="Input restaurant name"/> 
                <button type="button" onclick="onSearch()">Submit</button>
                <button type="button" onclick="clearSearch()">Clear search</button>
            </div>
        </form>
        <hr/>
        <div>Result for page <span class="current-page">1</span><span class="search-value"></span></div>
        <div id="list-container">

        </div>
        <hr/>
        <div id="paging-container">

        </div>

        <script src="${pageContext.servletContext.contextPath}/js/main.js"></script>
        <script>
                    var bListXsl = getXMLDoc('${bListXsl}');
                    var currentPage = 1;
                    var currentSearch = '';
                    loadData();
                    function loadData() {
                        let listContainer = document.getElementById("list-container");
                        listContainer.innerHTML = '<p>...... Loading ......</p>';
                        //get business list
                        const paramsObject = {
                            page: currentPage.toString(),
                            search: currentSearch
                        };
                        var query = new URLSearchParams(paramsObject).toString();
                        var xhr = new XMLHttpRequest();
                        xhr.open("GET", "${pageContext.servletContext.contextPath}/api/business?" + query.toString());
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

                    function onSearch() {
                        currentSearch = document.getElementById("search-form").search.value;
                        currentPage = 1;
                        loadData();
                    }

                    function clearSearch() {
                        document.getElementById("search-form").reset();
                        currentSearch = '';
                        currentPage = 1;
                        loadData();
                    }

                    function handleLoadedData() {
                        let currPage = document.querySelector('.current-page');
                        currPage.innerHTML = currentPage.toString();
                        let searchVal = document.querySelector('.search-value');
                        if (currentSearch)
                            searchVal.innerHTML = ', search: ' + currentSearch;
                        else
                            searchVal.innerHTML = '';
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
                                xmlStr += '<a class="p-' + i + '" href="#" onclick="changePage(' + i + ')">' + i + '</a> , ';
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
                            xsltProcessor.setParameter(null, "contextPath", '${pageContext.servletContext.contextPath}');
                            xsltProcessor.importStylesheet(bListXsl);
                            let resultDocument = xsltProcessor.transformToFragment(xml, document);
                            listContainer.innerHTML = '';
                            listContainer.append(resultDocument);
                        }
                    }

        </script>
    </body>
</html>
