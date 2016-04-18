<%@page import="entities.NameAndFollowID"%>
<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buy For Friend</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>Buy for your friend</h1>
            <h2>Please, select a friend for buying the game.</h2>
            <form action="FrontController">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>

            <%
                List<NameAndFollowID> namesAndFollowsIDs = (List<NameAndFollowID>) request.getAttribute("namesAndFollowsIDs");
                for (NameAndFollowID follower : namesAndFollowsIDs) {
            %>

            <tr>
                <td><%= follower.getName()%></td>
                <td>
                    <form action="FrontController">
                        <input type="hidden" name="name" value="<%= follower.getId()%>">
                        <input type="hidden" name="payment" value="<%= request.getAttribute("payment") %>">
                        <input type="hidden" name="command" value="BuyForFollowerCommand">
                        <input type="submit" value="Buy for your friend">
                    </form>
                </td>
            </tr>

            <%
                }
            %>

        </div>
    </body>
</html>

