<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Follower List</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>FollowList</h1>
            <form action="FrontController" method="post">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>

            <c:forEach var="element" items="${usersList}">
                <b>Name: </b><br>

                ${element.name}<br>
                
                <form action="FrontController" method="post">
                    <input type="hidden" name="id" value="${element.userId}">
                    <input type="submit" value="View Profile">
                    <input type="hidden" name="command" value="CopyFriendsDetailsCommand">
                </form>
                <br><br>
            </c:forEach><br><br>

            <h1>Estos usuarios tienen gustos similares a ti</h1>
            <table class="table-striped table table-hover">
                <tr>
                    <th>Name</th>
                </tr>
                <%                    
                    List<Users> recomendedUsersList = (List<Users>) request.getAttribute("recomendedUsersList");
                    for (Users user : recomendedUsersList) {
                %>
                <tr>
                    <td><%=user.getName()%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            <form action="FrontController" method="post">
                <input type="text" name="nametofollow" placeholder="Seguir a :">
                <input type="hidden" name="command" value="AddToFollowingListCommand">
                <input type="submit" value="Follow">
            </form>
        
        </div>
    </body>
</html>

