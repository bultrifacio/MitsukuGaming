
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html><head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>Add a product</h1>
            <%                if (loggedUser != null && loggedUser.getName().equals("Pepe")) {
            %>
            <form action="FrontController" method="post">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>
            <form action="FrontController" method="post">
                <fieldset>
                    <legend>Product information:</legend>
                    Name: <input name="name" type="text"><br>
                    Quantity: <input name="quantity" type="text"><br>
                    Category: <input name="category" type="text"><br>
                    Price: <input name="price" type="text"><br>
                    Cost: <input name="cost" type="text"><br>
                    Developer: <input name="developer" type="text"><br>
                    Discount: <input name="discount" type="text"><br>
                    Platform: <input name="platform" type="text"><br>
                    Description:<br>
                    <textarea name="description" rows="10" cols="20"></textarea><br>
                    Available: <select name="available">
                        <option value="True">True</option>
                        <option value="False">False</option>
                    </select><br>
                    Release date: <input name="date" type="text"><br>
                    Synopsis:<br>
                    <textarea name="synopsis" rows="10" cols="20"></textarea><br>
                    <input name="command" value="AddProductCommand" type="hidden"><br>
                    <input value="Add new product" type="submit"><br>
                </fieldset>
            </form>
            <%
                }
            %>
        </div>
    </body>
</html>