<%-- 
    Document   : wishList
    Created on : 28-mar-2016, 19:42:01
    Author     : Jusio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>WishList</h1>
         <c:forEach var="element" items="${productListFiltre}">
                    <b>Product name:</b><br>
                    ${element.name}<br>
                    <b>Price:</b><br>
                    ${element.price}<br>
                    <b>Description:</b><br>
                    ${element.description}<br>
                    <br><br>
         </c:forEach>
        
    </body>
</html>
