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
                <th>Available</th>
                <th>Price</th>
                <th>Cost</th>
                <th>Options</th>
            </tr>

            <c:forEach var="element" items="${productList}">
                <tr>
                <form action="FrontController">
                    <td>
                        ${element.productId}
                        <input type="hidden" name="id" value="${element.productId}">
                    </td>

                    <td>
                        ${element.name}
                        <input type="hidden" name="name" value="${element.name}">
                    </td>

                    <td>
                        ${element.quantity}
                        <input type="hidden" name="quantity" value="${element.quantity}">
                    </td>

                    <td>
                        ${element.available}
                        <input type="hidden" name="available" value="${element.available}">
                    </td>

                    <td>
                        ${element.price}
                        <input type="hidden" name="price" value="${element.price}">
                    </td>

                    <td>
                        ${element.cost}
                        <input type="hidden" name="cost" value="${element.cost}">
                    </td>

                    <td>
                        <input type="submit" value="Modify Product">
                        <input type="hidden" name="command" value="CopyProductDetailsCommand">
                </form>
                
                </td>
                </tr>
            </c:forEach>
        </table>


    </body>
</html>
