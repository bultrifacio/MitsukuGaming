<%-- 
    Document   : showPacksToBuy
    Created on : 30-abr-2016, 23:02:45
    Author     : Jusio
--%>

<%@page import="entities.PackDetails"%>
<%@page import="java.util.List"%>
<%@page import="entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add the Packs you want to the Cart</title>
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

        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>

    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>Add the Packs you want to the Cart</h1>
            <table class="table-striped table table-hover">
                <tr>
                    <th>Logo</th>
                    <th>Name</th>
                    <th>Descripcion</th>
                    <th>Discount</th>
                    <th>Buy!!</th>
                </tr>
                <%                    
                    List<PackDetails> packList = (List<PackDetails>) request.getAttribute("packDetailsList");
                    for (PackDetails element : packList) {
                %>
                <tr>
                    <td><img src="img/games/<%=element.getLogo()%>" height="50%" width="50%" alt=""></td>
                    <td><%=element.getName()%></td>
                    <td><%=element.getDescription()%></td>
                    <td><%=element.getDiscount()%></td>
                    <td>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="productId" value="<%=element.getPackId()%>">
                            <input type="hidden" name="discount" value="<%=element.getDiscount()%>">
                            <input type="hidden" name="command" value="AddPackToCartCommand">
                            <input type="image" src="img/icons/add-to-cart-icon.png" alt="Submit Form" />
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
