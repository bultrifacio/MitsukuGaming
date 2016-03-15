<%-- 
    Document   : modifyUserAdmin
    Created on : 15-mar-2016, 13:20:42
    Author     : ENTRAR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="ModifyUserFormServlet">
            <fieldset>
                <legend>Personal information:</legend>
                User name:<br>
                <input type="text" name="name" value=""><br>
                User email:<br>
                <input type="text" name="email" value=""><br>
                User pass:<br>
                <input type="text" name="password" value=""><br>
                <input type="submit" name="bt" value="Submit">
            </fieldset>
        </form>
    </body>
</html>
