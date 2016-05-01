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
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>Modify User</h1>
            <br><br>
            <form method="post" action="FrontController">
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
                        <input type="password" name="password" value="${element.password}"><br>
                        <input type="hidden" name="command" value="ModifyUsersCommand">
                        <input type="submit"  value="Save changes">
                    </c:forEach>
                </fieldset>
            </form>
        </div>
    </body>
</html>