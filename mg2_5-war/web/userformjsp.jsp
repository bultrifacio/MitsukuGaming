<%-- 
    Document   : userFormServlet
    Created on : 12-mar-2016, 23:12:30
    Author     : Jusio
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="FrontController">
            <fieldset>
                <legend>Personal information:</legend>
                First name:<br>
                <input type="text" name="name" value="yop"><br><br>
                Password:<br>
                <input type="text" name="password" value="tu"><br><br>
                email:<br>
                <input type="text" name="email" value="yo@mail.com"><br><br>
                <input type="submit" name="command" value="UsersCommand">
            </fieldset>
        </form>
    </body>
</html>
