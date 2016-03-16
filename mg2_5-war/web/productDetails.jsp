<%-- 
    Document   : productDetails
    Created on : 16-mar-2016, 11:27:06
    Author     : alumno
--%>

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
        <title>Buy</title>
    </head>
    <body>
        <h1>Buy </h1>

        <fieldset>
            <legend>Product information:</legend>
            <c:forEach var="element" items="${selectedProduct}">
                ID:<br>
                ${element.productId}<br>
                Product name:<br>
                ${element.name}<br>
                Price:<br>
                ${element.price}<br>
                
                <%
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    List<Product> list = (List<Product>) request.getAttribute("selectedProduct");
                    Date releaseDate = list.get(0).getReleaseDate();
                    String formatedDate = df.format(releaseDate);
                %>

                Release date:<br>
                <%= formatedDate %><br>
                Available:<br>
                ${element.available}<br>
                Description:<br>
                ${element.description}<br>
                Synopsis:<br>
                ${element.synopsis}<br><br>

                <form action="FrontController">
                    <input type="hidden" name="id" value="${element.productId}">
                    <input type="hidden" name="name" value="${element.name}">
                    <input type="hidden" name="quantity" value="${element.quantity}">
                    <input type="hidden" name="available" value="${element.available}">
                    <input type="hidden" name="price" value="${element.price}">
                    <input type="hidden" name="cost" value="${element.cost}">

                    <input type="submit" value="Add to cart">
                    <input type="hidden" name="command" value="AddToCartCommand">
                </form>
            </c:forEach>
        </fieldset>
    </body>
</html>
