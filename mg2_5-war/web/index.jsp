<%@page import="java.math.BigDecimal"%>
<%@page import="entities.Review"%>
<%@page import="entities.Product"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entities.Users"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Mitsuku Gaming</title>

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

    <body background="img/main/fc3.jpg">
        <%@include file="header.jsp" %>
        <br>
        <br>
        <!-- Page Content -->
        <img class="banner" alt="offer" src="img/banners/banner-ark.jpg" style="float:right"/>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <p class="lead"><font color="white">Mitsuku Gaming</font></p>
                    <div class="list-group">
                        <a href="register.jsp" class="list-group-item">Register</a>
                        <%                            if (loggedUser != null && loggedUser.getName().equals("Pepe")) {
                        %>
                        <a href="adminPanel.jsp" class="list-group-item">Admin Panel</a>
                        <%
                            }
                        %>
                        <div class="list-group-item">
                            <form method="post" action="FrontController">
                                <input type="hidden" name="command" value="ShowPopularProductsCommand">
                                <input type="submit" value="Popular Games" class="btn-link2">
                            </form>
                        </div>
                        <div class="list-group-item">
                            <form method="post" action="FrontController">
                                <input type="hidden" name="command" value="ShowShopProductsCommand">
                                <input type="submit" value="Games" class="btn-link2">
                            </form>
                        </div>

                        <div class="list-group-item">
                            <form method="post" action="FrontController">
                                <input type="hidden" name="command" value="ShowPacksForAddToCart">
                                <input type="submit" value="See Packs" class="btn-link2">
                            </form>
                        </div>

                        <%                            if (loggedUser != null) {
                        %>
                        <div class="list-group-item">
                            <form method="post" action="FrontController">
                                <input type="hidden" name="command" value="ShowMyWishListCommand">
                                <input type="submit" value="My Wishlist" class="btn-link2">
                            </form>
                        </div>
                        <div class="list-group-item">
                            <form method="post" action="FrontController">
                                <input type="hidden" name="command" value="ShowMyPurchaseHistoryCommand">
                                <input type="submit" value="My Purchase History" class="btn-link2">
                            </form>
                        </div>
                        <div class="list-group-item">
                            <form method="post" action="FrontController">
                                <input type="hidden" name="command" value="ShowMyFollowingListCommand">
                                <input type="submit" value="My Following List" class="btn-link2">
                            </form>
                        </div>
                        <%
                            }
                        %>
                        <div class="list-group-item">
                            <form method="post" action="FrontController">
                                <input type="text" name="search" class="form-control" placeholder="Search a game">
                                <input type="hidden" name="command" value="SearchCommand">
                                <input type="submit" class="btn btn-primary" value="Search">
                            </form>
                            <br>
                            <form method="post" action="FrontController">
                                <input type="text" name="minimum" class="form-control" placeholder="Minimum">
                                <input type="text" name="maximum" class="form-control" placeholder="Maximum">
                                <input type="hidden" name="command" value="SearchByPriceCommand">
                                <%
                                    if ((Integer) session.getAttribute("wrongRange") != null) {
                                        if ((Integer) session.getAttribute("wrongRange") == 1) {
                                %>
                                <div class="error-text">You have specified a wrong range.<br></div>
                                    <%
                                                session.setAttribute("wrongRange", 0);
                                            }
                                        }
                                    %>
                                <input type="submit" class="btn btn-primary" value="Filter by price">
                            </form>
                                
                            <form method ="post" action="FrontController">
                                <input type="hidden" name="command" value="SortByPriceCommand">
                                <input type="submit" class="btn btn-primary" value="Sort by price">
                            </form>    
                                
                            <br>
                            <form method="post" action="FrontController">
                                <input type="radio" name="category" value="FPS" checked> FPS<br>
                                <input type="radio" name="category" value="Stealth"> Stealth<br>
                                <input type="radio" name="category" value="RPG"> RPG<br>
                                <input type="hidden" name="command" value="SearchByCategoryCommand">
                                <input type="submit" class="btn btn-primary" value="Search by category">
                            </form>
                        </div>

                    </div>


                </div>
                <div class="col-md-9">
                    <div class="row carousel-holder">
                        <div class="col-md-12">
                            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="img/ds3.jpg" data-slide-to="0" class="active"></li>
                                    <li data-target="#img/theDivision.jpg" data-slide-to="1"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <form action="FrontController" method="post">
                                            <input type="hidden" name="productId" value="6">
                                            <input type="hidden" name="command" value="ShowProductDetailsCommand">
                                            <input class="slide-image" type="image" src="img/main/ds3.jpg" alt="" />
                                        </form>
                                    </div>
                                    <div class="item">
                                        <form action="FrontController" method="post">
                                            <input type="hidden" name="productId" value="1">
                                            <input type="hidden" name="command" value="ShowProductDetailsCommand">
                                            <input class="slide-image" type="image" src="img/main/theDivision.jpg" alt="" />
                                        </form>
                                    </div>
                                    <div class="item">
                                        <form action="FrontController" method="post">
                                            <input type="hidden" name="productId" value="3">
                                            <input type="hidden" name="command" value="ShowProductDetailsCommand">
                                            <input class="slide-image" type="image" src="img/main/fallout-4.png" alt="" />
                                        </form>
                                    </div>
                                </div>
                                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right"></span>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="row">

                        <%
                            List<Product> productList = (List<Product>) request.getAttribute("productList");
                            List<Review> reviewList = (List<Review>) request.getAttribute("reviewList");
                            for (Product element : productList) {
                        %>

                        <div class="col-sm-4 col-lg-4 col-md-4">
                            <div class="thumbnail">
                                <img src="img/logos/<%=element.getLogo()%>" alt="">
                                <div class="caption">
                                    <h4>
                                        <form method="post" action="FrontController">
                                            <input type="hidden" name="productId" value="<%=element.getProductId()%>">
                                            <input type="submit" value="<%=element.getName()%>" class="btn-link">
                                            <input type="hidden" name="command" value="ShowProductDetailsCommand">
                                        </form>
                                    </h4><div>
                                        <table class="tabledetails">
                                            <tr>
                                                <td><%=element.getDeveloper()%></td>
                                                <td align="right">
                                                    <%
                                                        BigDecimal price = new BigDecimal(Float.toString(element.getPrice()));
                                                        price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
                                                    %>
                                                    <strong><%=price%> 
                                                        <%
                                                            if (currency.equals("Euro")) {
                                                        %>
                                                        &euro;
                                                        <%
                                                        } else if (currency.equals("Dollar")) {
                                                        %>
                                                        $
                                                        <%
                                                            }
                                                        %>
                                                    </strong>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td align="right">
                                                    <font color="green">
                                                    <% if (element.getDiscount() != 0) {%>
                                                    <strong><%=element.getDiscount()%> %</strong><br>
                                                    <%
                                                        float discountedPrice = (float) (element.getPrice() - (element.getPrice() * (element.getDiscount() / 100.0)));
                                                        BigDecimal priceDis = new BigDecimal(Float.toString(discountedPrice));
                                                        priceDis = priceDis.setScale(2, BigDecimal.ROUND_HALF_UP);
                                                    %>
                                                    <strong><%=priceDis%>
                                                    <%
                                                        if (currency.equals("Euro")) {
                                                        %>
                                                        &euro;
                                                        <%
                                                        } else if (currency.equals("Dollar")) {
                                                        %>
                                                        $
                                                        <%
                                                            }
                                                        } %>
                                                        </strong>
                                                    </font>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="ratings">
                                    <p class="pull-right"> 
                                        <%
                                            int reviewQuantity = 0;
                                            for (int i = 0; i < reviewList.size(); i++) {
                                                if (element.getProductId() == reviewList.get(i).getProductId()) {
                                                    reviewQuantity++;
                                                }
                                            }
                                        %>
                                        <%=reviewQuantity%> review<% if (reviewQuantity != 1) { %>s <% }%>
                                    </p>
                                    <p>
                                        <%
                                            HashMap<String, Integer> stars = (HashMap<String, Integer>) session.getAttribute("stars");
                                            Integer productStars = stars.get(element.getName());

                                            for (int i = 0; i < 5; i++) {
                                                if (i < productStars) {
                                        %>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <%
                                        } else {
                                        %>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <%
                                                }
                                            }
                                        %>     
                                    </p>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <center>
                    <ul class="pagination">
                        <li><a>
                                <form method="post" action="FrontController">
                                    <input type="hidden" name="command" value="ArrowsCommand">
                                    <input type="submit" name="arrow" value="&laquo;" class="btn-link">
                                    <input type="hidden" name="direction" value="-1">
                                </form>
                            </a> 
                        </li>
                            <%
                                int pages = (Integer) session.getAttribute("pages");
                                int indexPagination = (Integer) session.getAttribute("indexPagination");

                                for (int i = 1; i <= pages; i++) {
                                    if (i == indexPagination) {
                            %>
                        <li class="active">
                            <a>
                                <form method="post" action="FrontController">
                                    <input type="hidden" name="command" value="GetInitialDataCommand">
                                    <input type="submit" name="index" value="<%=i%>" class="btn-link3">
                                </form>
                            </a>
                        </li>
                        <%
                        } else {
                        %>
                        <li>
                            <a>
                                <form method="post" action="FrontController">
                                    <input type="hidden" name="command" value="GetInitialDataCommand">
                                    <input type="submit" name="index" value="<%=i%>" class="btn-link">
                                </form>
                            </a>
                        </li>
                        <%
                                }
                            }
                        %>
                        <li>
                            <a>
                                <form method="post" action="FrontController">
                                    <input type="hidden" name="command" value="ArrowsCommand">
                                    <input type="submit" name="arrow" value="&raquo;" class="btn-link">
                                    <input type="hidden" name="direction" value="1">
                                </form>
                            </a>
                        </li>
                        
                    </ul>
                </center>
            </div>

        </div>

    </body>
    <%@include file="footer.jsp" %>
</html>
