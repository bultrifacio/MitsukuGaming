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
        <title>Modify user</title>
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
            <h1>Modify user</h1>
            <br><br>
            <form method="post" action="ModifyUserFormServlet">
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
        </div>
    </body>
</html>
