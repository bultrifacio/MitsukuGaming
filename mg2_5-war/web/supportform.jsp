<%@page import="entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Support Form</title>
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/shop-homepage.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <link href="bootstrap/bootstrap.css" rel="stylesheet">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
        <script src="bootstrap/jquery.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <form action="FrontController" method="post">
                <fieldset>
                    <legend>Technical Support:</legend>
                    Explain your problem:<br>
                    <textarea name="support" rows="10" cols="40"></textarea><br>
                    <input type="hidden" name="command" value="SupportCommand">
                    <input type="submit" value="Sent">
                </fieldset>
            </form>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
