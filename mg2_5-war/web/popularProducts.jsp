<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Popular Products</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>Show Products</h1>
            <form action="FrontController" method="post">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>
            <form action="FrontController" method="post">
                <input type="hidden" name="command" value="ShowCartCommand">
                <input type="submit" value="Show Cart">
            </form>
            <table class="table-striped table table-hover">
                <c:forEach var="element" items="${productList}">
                    <tr>
                        <td>${element.name}</td>
                        <td>${element.category}</td>
                        <td>${element.available}</td>
                        <td>${element.price}</td>
                        <td>${element.discount}</td>
                        <td>${element.developer}</td>
                        <td>${element.platform}</td>
                        <td>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="id" value="${element.productId}">
                                <input type="hidden" name="command" value="AddToCartCommand">
                                <input type="submit" value="Add to cart">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
