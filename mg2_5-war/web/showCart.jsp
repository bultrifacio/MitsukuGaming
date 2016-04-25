<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
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
            <table class="table table-hover">
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Discount</th>
                    <th>Price with discount</th>
                </tr>
                <%
                    HashMap<Integer, Integer> productQuantity = new HashMap<Integer, Integer>();
                    for (Product product : cart.getContents()) {
                        if (productQuantity.containsKey(product.getProductId())) {
                            productQuantity.put(product.getProductId(), productQuantity.get(product.getProductId()) + 1);
                        } else {
                            productQuantity.put(product.getProductId(), 1);
                        }
                    }

                    Iterator it = productQuantity.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        for (Product product : cart.getContents()) {
                            if (entry.getKey() == product.getProductId()) {
                %>

                <tr>
                    <td><%=product.getName()%></td>
                    <td><%=product.getPrice()%>
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
                    <td>
                        <%=entry.getValue()%>
                    </td>
                    <td><font color="green"><%=product.getDiscount()%> %</font></td>
                    <td>
                        <%
                            if (product.getDiscount() > 0) {
                        %>
                        <%=(product.getPrice() - (product.getPrice() * (product.getDiscount() / 100.0))) * (Integer) entry.getValue()%>
                        <% } else {%>
                        <%=product.getPrice() * (Integer) entry.getValue()%>
                        <% } %>
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

                    <td>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="id" value="<%=product.getProductId()%>">
                            <input type="hidden" name="command" value="RemoveFromCartCommand">
                            <input type="image" src="img/remove-icon.png" alt="Submit Form" />
                        </form>
                    </td>
                </tr>
                <%
                                break;
                            }
                        }
                    }
                %>

                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <strong>Total</strong>
                    </td>
                    <td>
                        <%=(Float) session.getAttribute("total")%>
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
