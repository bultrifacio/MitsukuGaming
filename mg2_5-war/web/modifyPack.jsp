<%@page import="java.util.List"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="entities.Product"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Pack</title>
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
            <h1>Modify Pack</h1>
            <%                if (loggedUser != null && loggedUser.getName().equals("Pepe")) {
            %>
            <form action="FrontController" method="post">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>
            <form action="FrontController" method="post">
                <fieldset>
                    <legend>Pack information:</legend>
                    <c:forEach var="element" items="${packList}">
                        ID:<br>
                        <input type="text" name="id" value="${element.packId}"><br>
                        Pack name:<br>
                        <input type="text" name="name" value="${element.name}"><br>
                        Description:<br>
                        <input type="text" name="description" value="${element.description}"><br>
                        Discount:<br>
                        <input type="text" name="discount" value="${element.discount}"><br>
                        <input type="hidden" name="command" value="ModifyPackCommand">
                        <input type="submit"  value="Save changes">           
                    </c:forEach>
                </fieldset>
            </form>
            <table class="table-striped table table-hover">
                <tr>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="element" items="${itemsInPack}">
                    <tr>
                        <td>${element.name}</td>
                        <td>${element.category}</td>
                        <td>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="id" value="${element.productId}">
                                <input type="hidden" name="name" value="${element.name}">
                                <input type="hidden" name="category" value="${element.category}">

                                <input type="hidden" name="command" value="RemoveProductPackCommand">
                                <input type="submit"  value="Remove">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <table class="table-striped table table-hover">
                <tr>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Action</th>
                </tr>

                <c:forEach var="element" items="${itemsNotInPack}">
                    <tr>
                        <td>${element.name}</td>
                        <td>${element.category}</td>
                        <td>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="id" value="${element.productId}">
                                <input type="hidden" name="name" value="${element.name}">
                                <input type="hidden" name="category" value="${element.category}">

                                <input type="hidden" name="command" value="AddProductPackCommand">
                                <input type="submit"  value="Add">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <%
                }
            %>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
