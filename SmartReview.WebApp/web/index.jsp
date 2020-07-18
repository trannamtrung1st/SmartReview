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
            </div>
        </form>
        <hr/>
        <div>Result for page 1</div>
        <div id="list-container">
            <div class="list-item">
                <h3><a href="#">Restaurant A</a></h3>
                <div><span class="label">Total review:</span> 124</div>
                <div><span class="label">Rating:</span> 4.5</div>
                <div><span class="label">Address:</span> 124 A DSKA</div>
                <div><span class="label">Phone:</span> 012423123</div>
                <div><span class="label">From page:</span> <a href="tripadvisor.com">tripadvisor.com</a></div>
            </div>
            <div class="list-item">
                <h3><a href="#">Restaurant A</a></h3>
                <div><span class="label">Total review:</span> 124</div>
                <div><span class="label">Rating:</span> 4.5</div>
                <div><span class="label">Address:</span> 124 A DSKA</div>
                <div><span class="label">Phone:</span> 012423123</div>
                <div><span class="label">From page:</span> <a href="tripadvisor.com">tripadvisor.com</a></div>
            </div>
            <div class="list-item">
                <h3><a href="#">Restaurant A</a></h3>
                <div><span class="label">Total review:</span> 124</div>
                <div><span class="label">Rating:</span> 4.5</div>
                <div><span class="label">Address:</span> 124 A DSKA</div>
                <div><span class="label">Phone:</span> 012423123</div>
                <div><span class="label">From page:</span> <a href="tripadvisor.com">tripadvisor.com</a></div>
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
