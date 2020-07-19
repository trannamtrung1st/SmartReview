<%-- 
    Document   : error
    Created on : Jun 28, 2020, 2:18:55 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Something's wrong</title>
        <style>
            .status-code{
                color:blue;
            }
        </style>
    </head>
    <body>
        <h2 class="status-code">Status code: ${status_code}</h2>
        <h1>Something's wrong, contact administrator for more information</h1>
        <script>
            console.log("ex: ${exception_mess}");
            console.log("code: ${status_code}");
            console.log("uri: ${uri}");
        </script>
    </body>
</html>
