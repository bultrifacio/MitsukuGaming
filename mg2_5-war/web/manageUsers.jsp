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
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/shop-homepage.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <link href="bootstrap/bootstrap.css" rel="stylesheet">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
        <script src="bootstrap/jquery.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h1>Manage Users</h1>
            <form action="FrontController">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>

            <table border="1" class="table-striped">
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
        </div>
    </body>
</html>
