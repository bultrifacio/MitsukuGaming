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
        <title>Modify Product</title>
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
        <h1>Modify Product</h1>
        <%                if (loggedUser != null && loggedUser.getName().equals("Pepe")) {
        %>
        <form action="FrontController" method="post">
            <input type="hidden" name="command">
            <input type="submit" value="Go to Main page" class="btn-link">
        </form><br><br>
        <form action="FrontController" method="post">
            <fieldset>
                <legend>Product information:</legend>
                <c:forEach var="element" items="${productList}">
                    ID:<br>
                    <input type="text" name="id" value="${element.productId}"><br>
                    Product name:<br>
                    <input type="text" name="name" value="${element.name}"><br>
                    Price:<br>
                    <input type="text" name="price" value="${element.price}"><br>
                    Cost:<br>
                    <input type="text" name="cost" value="${element.cost}"><br>
                    Discount:<br>
                    <input type="text" name="discount" value="${element.discount}"><br>
                    Developer:<br>
                    <input type="text" name="developer" value="${element.developer}"><br>
                    Platform:<br>
                    <input type="text" name="platform" value="${element.platform}"><br>
                    Logo:<br>
                    <input type="text" name="logo" value="${element.logo}"><br>
                    Category:<br>
                    <input type="text" name="category" value="${element.category}"><br>
                    Quantity:<br>
                    <input type="text" name="quantity" value="${element.quantity}"><br>
                    Release date:<br>
                    <%
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        List<Product> list = (List<Product>) request.getAttribute("productList");
                        Date releaseDate = list.get(0).getReleaseDate();
                        String formattedDate = df.format(releaseDate);
                    %>
                    <input type="text" name="release" value="<%= formattedDate%>"><br>
                    Available:<br>
                    <input type="text" name="available" value="${element.available}"><br>
                    Description:<br>
                    <input type="text" name="description" value="${element.description}"><br>
                    Synopsis:<br>
                    <input type="text" name="synopsis" value="${element.synopsis}"><br><br>
                    <input type="hidden" name="command" value="ModifyProductCommand">
                    <input type="submit"  value="Save changes">
                </c:forEach>
            </fieldset>
        </form>
        <%
                }
            %>
    </body>
    <%@include file="footer.jsp" %>
</html>
