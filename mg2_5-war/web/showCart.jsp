<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
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
            <h1>Cart</h1>
            <br><br>
            <table border="1">
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Discount</th>
                    <th>Price with discount</th>
                </tr>
                <%
                    for (Product product : cart.getContents()) {
                        
                %>
                
                    <tr>
                        <td><%=product.getName() %></td>
                        <td><%=product.getPrice() %>
                            <%
                                if (currency.equals("Euro")) {
                            %>
                            &euro;
                            <%
                                } else {
                                    if (currency.equals("Dollar")) {
                            %>
                            $
                            <%
                                    }
                                }
                            %>
                        </td>
                        <td><font color="green"><%=product.getDiscount()%> %</font></td>
                        <td>
                            <%
                            if (product.getDiscount() > 0) {
                            %>
                            <%=product.getPrice() - (product.getPrice() * (product.getDiscount() / 100.0)) %>
                            <% } else { %>
                            <%=product.getPrice() %>
                            <% } %>
                        </td>
                        <td>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="id" value="<%=product.getProductId()%>">
                                <input type="hidden" name="command" value="RemoveFromCartCommand">
                                <input type="submit" value="Remove from cart">
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td>
                            <strong>Total</strong>
                        </td>
                        <td>
                            <%=(Float)session.getAttribute("total")%>
                            <%
                                if (currency.equals("Euro")) {
                            %>
                            &euro;
                            <%
                                } else {
                                    if (currency.equals("Dollar")) {
                            %>
                            $
                            <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
            </table>
            <br>
            <form action="FrontController" method="post">
                <input type="hidden" name="command" value="CheckoutCommand">
                <input type="submit" value="Checkout">
            </form>
        </div>
    </body>
</html>
