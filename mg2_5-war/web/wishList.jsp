<%@page import="java.math.BigDecimal"%>
<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wishlist</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>WishList</h1>
            <div class="container">
                <table class="table-striped table table-hover">
                    <tr>
                        <th>Logo</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Platform</th>
                        <th>Discount</th>
                        <th>Buy!!</th>
                    </tr>
                    <%
                        List<Product> productList = (List<Product>) request.getAttribute("productListWished");
                        for (Product element : productList) {
                    %>
                    <tr>
                        <td><img src="img/logos/<%=element.getLogo()%>" height="50%" width="50%" alt=""></td>
                        <td><%=element.getName()%></td>
                        <%
                            float p;
                            if (element.getDiscount() > 0) {
                                p = (float) (element.getPrice() - (element.getPrice() * (element.getDiscount() / 100.0)));
                            } else {
                                p = (float) element.getPrice();
                            } 
                            BigDecimal priceDiscounted = new BigDecimal(Float.toString(p));
                            priceDiscounted = priceDiscounted.setScale(2, BigDecimal.ROUND_HALF_UP);
                        %>
                        <td><%=priceDiscounted%>
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
                        <td><%=element.getPlatform()%></td>
                        <td><%=element.getDiscount()%></td>
                        <td>
                            <%
                                if (element.getQuantity() != 0) {
                            %>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="productId" value="<%=element.getProductId()%>">
                                <input type="hidden" name="command" value="AddToCartCommand">
                                <input type="image" src="img/icons/add-to-cart-icon.png" alt="Submit Form" />
                            </form>
                            <%
                                } else {
                            %>
                            <font color="red">No hay Stock</font>
                            <%
                                }
                            %>

                        </td>
                    </tr>
                    <%
                        }
                    %>    
                </table>
            </div>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
