<%-- 
    Document   : login
    Created on : Jul 20, 2020, 10:50:47 PM
    Author     : TNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div><a href="${pageContext.servletContext.contextPath}/">Back to home</a></div>
        <h1>Log into the system</h1>
        <form method="POST" action="${pageContext.servletContext.contextPath}/admin/login">
            <div style="color:red">${message}</div> 
            Username: <input type="text" name="username"/><br/>
            Password: <input type="password" name="password"/><br/>
            <input type="submit" value="LOG IN"/>
        </form>
    </body>
</html>
