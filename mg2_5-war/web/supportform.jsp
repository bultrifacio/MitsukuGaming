<%-- 
    Document   : supportform
    Created on : 28-mar-2016, 5:49:28
    Author     : Ismael
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
                <legend>Technical Support:</legend>
                Explain your problem:<br>
                <textarea name="comentarios" rows="10" cols="40"></textarea><br>
                <input type="hidden" name="command" value="SupportCommand">
                <input type="submit" value="Sent">
            </fieldset>
        </form>
    </body>
</html>
