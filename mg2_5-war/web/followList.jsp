<%-- 
    Document   : followList
    Created on : 31-mar-2016, 21:43:39
    Author     : hector
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Follower List</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>FollowList</h1>
            <form action="FrontController" method="post">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>
           
            <c:forEach var="element" items="${usersList}">
                <b>Name: </b><br>
               
                ${element.name}<br>
                
                <br><br>
            </c:forEach><br><br>
            
            <form action="FrontController" method="post">
                <input type="text" name="nametofollow" placeholder="Seguir a :">
                <input type="hidden" name="command" value="AddToFollowingListCommand">
                <input type="submit" value="Follow">
            </form>
        </div>
    </body>
</html>

