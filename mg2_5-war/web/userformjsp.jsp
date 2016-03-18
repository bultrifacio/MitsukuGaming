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
        <title>Register</title>
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
        <h1>Register</h1>
        <form action="FrontController">
            <input type="hidden" name="command">
            <input type="submit" value="Go to Main page" class="btn-link">
        </form><br><br>
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
