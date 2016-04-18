<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buy For Friend</title>
    </head>
    <body>
        <div class="container">
            <h1>Buy for your friend</h1>
            <h2>Please, select a friend for buying the game.</h2>
            <form action="FrontController">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>

            <c:forEach var="element" items="${namesAndFollowsIDs}">
                <tr>
                    <td>${element.name}</td>
                    <td>${element.id}</td>
                    <td>
                        <form action="FrontController">
                            <input type="hidden" name="id" value="${element.productId}">
                            <input type="hidden" name="command" value="BuyForFriendCommand">
                            <input type="submit" value="Buy for your friend">
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </div>
    </body>
</html>

