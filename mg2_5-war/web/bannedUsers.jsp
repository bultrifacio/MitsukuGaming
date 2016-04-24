<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BanList</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>Banned Users List</h1>
            <form action="FrontController" method="post">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>
            <c:forEach var="element" items="${bannedUsersList}">
                <b>User:</b><br>
                ${element.name}<br>
                <br><br>
            </c:forEach>
            <form action="FrontController" method="post">
                <input type="text" name="nametoban" placeholder="Ban to :">
                <input type="hidden" name="command" value="BanToCommand">
                <input type="submit" value="Ban">
            </form>
        </div>
    </body>
</html>