<%-- 
    Document   : manageProducts
    Created on : 14-mar-2016, 16:40:59
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
        <title>Manage Products</title>
    </head>
    <body>
        <h1>Manage Products</h1>

        <form action="AddProduct">
            <input type="submit" value="Add Product">
        </form>

        <table border="1">
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Available</th>
                <th>Price</th>
                <th>Cost</th>
                <th>Options</th>
            </tr>

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
                            <input type="hidden" name="name" value="${element.name}">
                            <input type="hidden" name="quantity" value="${element.quantity}">
                            <input type="hidden" name="available" value="${element.available}">
                            <input type="hidden" name="price" value="${element.price}">
                            <input type="hidden" name="cost" value="${element.cost}">

                            <input type="submit" value="Modify Product">
                            <input type="hidden" name="command" value="CopyProductDetailsCommand">
                        </form>
                        <form action="FrontController">
                            <input type="hidden" name="id" value="${element.productId}">
                            <input type="submit" value="Remove Product">
                            <input type="hidden" name="command" value="RemoveProductCommand">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
