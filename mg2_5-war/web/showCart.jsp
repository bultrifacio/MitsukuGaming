<%-- 
    Document   : cart
    Created on : 15-mar-2016, 19:21:01
    Author     : Blarzek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cart</h1>

        <table border="1">
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>

            <c:forEach var="element" items="${Cart}">
                <tr>
                    <td>${element.name}</td>
                    <td>${element.quantity}</td>
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
