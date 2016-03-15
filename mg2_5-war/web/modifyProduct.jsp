<%-- 
    Document   : modifyProduct
    Created on : 14-mar-2016, 17:12:50
    Author     : Blarzek
--%>

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
    </head>
    <body>
        <h1>Modify Product</h1>
        <form action="FrontController">
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
                    Quantity:<br>
                    <input type="text" name="quantity" value="${element.quantity}"><br>
                    Release date:<br>
                    <%
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        List<Product> list = (List<Product>)request.getAttribute("productList");
                        Date releaseDate = list.get(0).getReleaseDate();
                        String formatedDate = df.format(releaseDate);
                    %>
                    
                    <input type="text" name="release" value="<%= formatedDate %>"><br>
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
    </body>
</html>
