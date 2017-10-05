<%-- 
    Document   : Home
    Created on : Oct 3, 2017, 1:49:47 PM
    Author     : 578291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html"/>
<h1>Home page</h1>
<h3>Hello ${username}</h3>
<a href="login?action=loggedout">Log out</a>
<c:import url="/includes/footer.html"/>
