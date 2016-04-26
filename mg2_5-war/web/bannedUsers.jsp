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
            <table border="1" class="table-striped">
                <tr>
                    <th>Name</th>
                    <th>Options</th>
                </tr>
            <c:forEach var="element" items="${bannedUsersList}">
                <tr>
                    <td>${element.name}</td>
                    <td>
                          <form action="FrontController" method="post">
                              <input type="hidden" name="id" value="${element.userId}">
                              <input type="hidden" name="name" value="${element.name}">
                              <input type="hidden" name="email" value="${element.email}">
                              <input type="hidden" name="password" value="${element.password}">
                              <input type="hidden" name="password" value="${element.state}">
                              <input type="submit" value="Unban user">
                              <input type="hidden" name="command" value="UnbanCommand">
                          </form>
                    </td>
                </tr>
            </c:forEach>
            </table>
            <form action="FrontController" method="post">
                <input type="text" name="nametoban" placeholder="Ban to :">
                <input type="hidden" name="command" value="BanToCommand">
                <input type="submit" value="Ban">
            </form>
        </div>
    </body>
</html>