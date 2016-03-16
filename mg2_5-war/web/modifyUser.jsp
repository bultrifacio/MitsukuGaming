<%-- 
    Document   : modifyProduct
    Created on : 14-mar-2016, 17:12:50
    Author     : Ismael
--%>

<%@page import="java.util.List"%>
<%@page import="java.text.ParseException"%>
<%@page import="entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify User</title>
    </head>
    <body>
        <h1>Modify User</h1>
        <form action="FrontController">
            <fieldset>
                <legend>User information:</legend>

                <%

                    //Product product = (Product) session.getAttribute("product");
                    //if (product != null) {

                %>

                <c:forEach var="element" items="${usersList}">
                    ID:<br>
                    <input type="text" name="id" value="${element.userId}"><br>
                    User name:<br>
                    <input type="text" name="name" value="${element.name}"><br>
                    Email:<br>
                    <input type="text" name="email" value="${element.email}"><br>
                    Password:<br>
                    <input type="text" name="password" value="${element.password}"><br>
                    <input type="hidden" name="command" value="ModifyUsersCommand">
                    <input type="submit"  value="Save changes">
                </c:forEach>
            </fieldset>
        </form>
    </body>
</html>