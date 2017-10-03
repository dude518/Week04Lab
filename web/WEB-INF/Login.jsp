<%-- 
    Document   : Login
    Created on : Oct 3, 2017, 1:47:15 PM
    Author     : 578291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html"/>
<h1>Remember me login page</h1>
<form action="login" method="post">
    Username: <input type="text" name="username">
    Password: <input type="password" name="password">
    <input type="submit" name="submit">
    <input type="checkbox" name="rememberme">Remember me
</form>
<c:import url="/includes/footer.html"/>
