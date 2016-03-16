<%-- 
    Document   : manageUsers
    Created on : 14-mar-2016, 16:40:59
    Author     : Ismael
--%>

<%@page import="entities.Users"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Options</th>
            </tr>

            <c:forEach var="element" items="${usersList}">
                <tr>
                    <td>${element.userId}</td>
                    <td>${element.name}</td>
                    <td>${element.email}</td>
                    <td>${element.password}</td>
                    <td>
                        <form action="FrontController">
                            <input type="hidden" name="id" value="${element.userId}">
                            <input type="hidden" name="name" value="${element.name}">
                            <input type="hidden" name="email" value="${element.email}">
                            <input type="hidden" name="password" value="${element.password}">
                            <input type="submit" value="Modify User">
                            <input type="hidden" name="command" value="CopyUsersDetailsCommand">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
