<%@page import="entities.Review"%>
<%@page import="entities.Users"%>
<%@page import="java.util.Date"%>
<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Stats</title>
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
            <h1>Product Stats</h1>
            <%                if (loggedUser != null && loggedUser.getName().equals("Pepe")) {
            %>
            <fieldset>
                <legend>Product: ${name}</legend>
                <b>Number of sales: </b>
                ${salesProduct}<br>
                <b>Number of wish: </b>
                ${wishProduct}<br><br>

                <legend>Payment methods: </legend>
                <b>Visa: </b>
                ${MethodVisa}<br>
                <b>MasterCard: </b>
                ${MethodMasterCard}<br>
                <b>Paypal: </b>
                ${MethodPaypal}<br>
                <b>Wire transfer: </b>
                ${MethodWireTranfer}<br><br>
            </fieldset>
        </div>
        <%
            }
        %>
    </body>
    <%@include file="footer.jsp" %>
</html>

