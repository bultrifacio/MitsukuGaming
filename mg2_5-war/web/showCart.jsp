<%-- 
    Document   : cart
    Created on : 15-mar-2016, 19:21:01
    Author     : Blarzek
--%>

<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
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
        <h1>Cart</h1>
        <form action="FrontController">
            <input type="hidden" name="command">
            <input type="submit" value="Go to Main page" class="btn-link">
        </form><br><br>
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Price</th>
            </tr>       
            <c:forEach var="element" items="${cart}">
                <tr>
                    <td>${element.name}</td>
                    <td>${element.price}</td>
                    <td>
                        <form action="FrontController">
                            <input type="hidden" name="id" value="${element.productId}">
                            <input type="hidden" name="command" value="RemoveFromCartCommand">
                            <input type="submit" value="Remove from cart">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form action="FrontController">
            <input type="hidden" name="id" value="${element.productId}">
            <input type="hidden" name="command" value="CheckoutCommand">
            <input type="submit" value="Checkout">
        </form>
    </body>
</html>
