<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Admin Panel</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <h1>Admin Panel</h1>
            <br><br>
            <%                if (loggedUser != null && loggedUser.getName().equals("Pepe")) {
            %>
            <form method="post" action="FrontController">
                <input type="hidden" name="command" value="ShowProductsCommand">
                <input type="submit" value="Manage products">
            </form>

            <form method="post" action="FrontController">
                <input type="submit" value="Manage users">
                <input type="hidden" name="command" value="ShowUsersCommand">
            </form>

            <form method="post" action="FrontController">
                <input type="hidden" name="command" value="ShowPacksCommand">
                <input type="submit" value="Manage packs">
            </form>
            <form method="post" action="FrontController">
                <input type="hidden" name="command" value="SendOffersCommand">
                <input type="submit" value="Send offers">
            </form>
            <form method="post" action="FrontController">
                <input type="hidden" name="command" value="ShowBannedUsersCommand">
                <input type="submit" value="Banned Users List">
            </form>
            <%
                }
            %>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
