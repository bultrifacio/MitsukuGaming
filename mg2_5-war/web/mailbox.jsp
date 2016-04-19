<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suggestions Mailbox</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <form action="FrontController" method="post">
            <input type="hidden" name="command">
            <input type="submit" value="Go to Main page" class="btn-link">
        </form><br><br>
        <form action="FrontController" method="post">
            <fieldset>
                <legend>Suggestions Mailbox:</legend>
                <textarea name="suggestion" rows="10" cols="40"></textarea><br>
                <input name="command" value="MailBoxCommand" type="hidden"><br>
                    <input value="Sent" type="submit"><br>
            </fieldset>
        </form>
    </body>
</html>
