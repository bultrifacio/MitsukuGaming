<%-- 
    Document   : showProducts
    Created on : 15-mar-2016, 13:02:06
    Author     : juancarlos
--%>
<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Products</title>
    </head>
    <body>
        <h1>Show Products</h1>
        <form action="FrontController">
            <input type="hidden" name="command" value="ShowCartCommand">
            <input type="submit" value="Show Cart">
        </form>
        <table border="1">
            <c:forEach var="element" items="${productList}">
                <tr>
                    <td>${element.productId}</td>
                    <td>${element.name}</td>
                    <td>${element.quantity}</td>
                    <td>${element.available}</td>
                    <td>${element.price}</td>
                    <td>${element.cost}</td>
                    <td>
                        <form action="FrontController">
                            <input type="hidden" name="id" value="${element.productId}">
                            <input type="hidden" name="command" value="AddToCartCommand">
                            <input type="submit" value="Add to cart">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
