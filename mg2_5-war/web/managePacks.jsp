<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Packs</title>
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
            <h1>Manage Packs</h1>
            <form action="addPack.jsp" method="post">
                <input type="submit" value="Add Pack">
            </form>

            <table class="table-striped table table-hover">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Discount</th>
                </tr>

                <c:forEach var="element" items="${packList}">
                    <tr>
                        <td>${element.packId}</td>
                        <td>${element.name}</td>
                        <td>${element.description}</td>
                        <td>${element.discount}</td>
                        <td>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="id" value="${element.packId}">
                                <input type="hidden" name="name" value="${element.name}">
                                <input type="hidden" name="quantity" value="${element.description}">
                                <input type="hidden" name="available" value="${element.discount}">
                                
                                <input type="submit" value="Modify Pack">
                                <input type="hidden" name="command" value="CopyPackDetailsCommand">
                            </form>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="id" value="${element.packId}">
                                <input type="submit" value="Remove Pack">
                                <input type="hidden" name="command" value="RemovePackCommand">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
