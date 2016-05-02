<%@page import="java.util.List"%>
<%@page import="java.text.ParseException"%>
<%@page import="entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Profile</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>Modify Profile</h1>
            <form action="FrontController" method="post">
                <fieldset>
                    <legend>User information:</legend>
                    <c:forEach var="element" items="${userInfo}">
                        User name:<br>
                        <input type="text" name="name" value="${element.name}" placeholder="Name"><br>
                        Email:<br>
                        <input type="text" name="email" value="${element.email}" placeholder="Email"><br>
                        Password:<br>
                        <input type="password" name="password" value="" placeholder="New Password"><br>
                        <input type="hidden" name="id" value="${element.userId}">
                        <input type="hidden" name="command" value="ModifyProfileCommand">
                        <input type="submit"  value="Save changes">
                    </c:forEach>
                </fieldset>
            </form>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>