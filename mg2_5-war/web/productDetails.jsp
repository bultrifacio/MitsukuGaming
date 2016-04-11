<%-- 
    Document   : productDetails
    Created on : 16-mar-2016, 11:27:06
    Author     : alumno
--%>

<%@page import="entities.Review"%>
<%@page import="entities.Users"%>
<%@page import="java.util.Date"%>
<%@page import="entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buy product</title>
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
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="FrontController">Mitsuku Gaming</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="#">About</a>
                        </li>
                        <li>
                            <a href="#">Services</a>
                        </li>
                        <li>
                            <a href="#">Contact</a>
                        </li>

                        <li>
                            <%
                                Users loggedUser = (Users) session.getAttribute("loggedUser");
                                out.println("<br>");
                                if (loggedUser != null) {
                                    out.println("<input type=\"text\" name=\"username\" value=\"Welcome " + loggedUser.getName() + "\">");
                                    out.println("<form action=\"FrontController\">");
                                    out.println("<input type=\"hidden\" name=\"command\" value=\"ShowProfileCommand\">");
                                    out.println("<input type=\"hidden\" name=\"id\" value=\"" + loggedUser.getUserId() + "\">");
                                    out.println("<input type=\"hidden\" name=\"name\" value=\"" + loggedUser.getName() + "\">");
                                    out.println("<input type=\"hidden\" name=\"email\" value=\"" + loggedUser.getEmail() + "\">");
                                    out.println("<input type=\"hidden\" name=\"password\" value=\"" + loggedUser.getPassword() + "\">");
                                    out.println("<input type=\"submit\" value=\"Modify profile\">");
                                    out.println("</form>");
                                } else {
                                    out.println("<form action=\"FrontController\">");
                                    out.println("<input type=\"text\" name=\"username\" placeholder=\"Username\">");
                                    out.println("<input type=\"password\" name=\"password\" placeholder=\"Password\">");
                                    out.println("<input type=\"hidden\" name=\"command\" value=\"LoginCommand\">");
                                    out.println("<input type=\"submit\" value=\"Login\">");
                                    out.println("<a href=\"resetPassword.jsp\">Forgot password?</a>");
                                    out.println("</form>");
                                }
                            %>
                        </li>
                        <li>
                            <form action="FrontController">
                                <input type="hidden" name="command" value="ChangeCurrencyCommand">
                                <%
                                    String currency = (String) session.getAttribute("currency");
                                    if (currency.equals("Euro")) {
                                        out.println("<input type=\"submit\" name=\"currency\" value=\"Euro\">");
                                    } else {
                                        if (currency.equals("Dollar")) {
                                            out.println("<input type=\"submit\" name=\"currency\" value=\"Dollar\">");
                                        }
                                    }
                                %>
                            </form>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
        </nav>
        <br><br><br><br>
        <div class="container">
        <h1>Buy product</h1>
        <form action="FrontController">
            <input type="hidden" name="command" value="ShowCartCommand">
            <input type="submit" value="Show cart" class="btn-link">
        </form><br><br>
        <fieldset>
            <legend>Product information:</legend>
            <div class="row">
                <div class="col-md-9">
            <div class="row carousel-holder">
                <div class="col-md-12">
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <%
                                int index = 0;
                                boolean active = true;
                            %>
                            <c:forEach var="element" items="${imageFilter}">
                                <% 
                                    if (active) { 
                                %>
                                <li data-target="img/${element.path}" data-slide-to="<%= index%>" class="active"></li>
                                <% 
                                    active = false;
                                    } else {    
                                %>
                                <li data-target="img/${element.path}" data-slide-to="<%= index%>"></li>
                                <%
                                    }
                                    index++;
                                %>
                            </c:forEach>
                        </ol>
                        <div class="carousel-inner">
                            <% active = true; %>
                            <c:forEach var="element" items="${imageFilter}">
                                <% 
                                    if (active) {
                                %>
                                <div class="item active">
                                    <img class="slide-image" src="img/${element.path}" alt="">
                                </div>
                                <%
                                    active = false;
                                    } else {
                                %>
                                <div class="item">
                                    <img class="slide-image" src="img/${element.path}" alt="">
                                </div>
                                <% } %>
                            </c:forEach>
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
            
            <c:forEach var="element" items="${selectedProduct}">
                <b>ID:</b><br>
                ${element.productId}<br>
                <b>Product name:</b><br>
                ${element.name}<br>
                <b>Category:</b><br>
                ${element.category}<br>
                <b>Price:</b><br>
                ${element.price}<br>

                <%
                    String category = (String) request.getAttribute("category");
                %>
                
                <%
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    List<Product> list = (List<Product>) request.getAttribute("selectedProduct");
                    Date releaseDate = list.get(0).getReleaseDate();
                    String formatedDate = df.format(releaseDate);
                %>

                <b>Release date:</b><br>
                <%= formatedDate%><br>
                <b>Available:</b><br>
                ${element.available}<br>
                <b>Description:</b><br>
                ${element.description}<br>
                <b>Synopsis:</b><br>
                ${element.synopsis}<br><br>

                <fieldset>
                    <b>Similar Games:</b><br>
                    <c:forEach var="attribute" items="${productList}">
                        <tr>
                            <td>${attribute.productId}</td>
                            <td>${attribute.name}</td>
                            <td>${attribute.quantity}</td>
                            <td>${attribute.available}</td>
                            <td>${attribute.category}</td>
                            <td>${attribute.price}</td>
                            <td>${attribute.cost}</td>
                            <td>
                                <form action="FrontController">
                                    <input type="hidden" name="id" value="${attribute.productId}">
                                    <input type="hidden" name="name" value="${attribute.name}">
                                    <input type="hidden" name="quantity" value="${attribute.quantity}">
                                    <input type="hidden" name="available" value="${attribute.available}">
                                    <input type="hidden" name="category" value="${attribute.category}">
                                    <input type="hidden" name="price" value="${attribute.price}">
                                    <input type="hidden" name="cost" value="${attribute.cost}">

                                    <input type="submit" value="Visit Product">
                                    <input type="hidden" name="command" value="ShowProductDetailsCommand">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </fieldset>

                <fieldset>
                    <legend>User reviews</legend>
                    <%
                        loggedUser = (Users) session.getAttribute("loggedUser");
                        if (loggedUser != null) {
                    %>
                    <form action="FrontController">
                        <%
                            out.println("<input type=\"hidden\" name=\"productId\" value=\"" + request.getParameter("id") + "\">");
                            out.println("<input type=\"hidden\" name=\"category\" value=\"" + (String) category + "\">");
                        %>
                        <input type="text" name="text" placeholder="Write your review here">
                        <input type="text" name="score" placeholder="Your score">

                    <c:forEach var="attribute" items="${productList}">
                        <input type="hidden" name="price" value="${attribute.price}">
                    </c:forEach>
                    <%
                            out.println("<input type=\"hidden\" name=\"command\" value=\"WriteReviewCommand\">");
                            out.println("<input type=\"submit\" value=\"Write a review\">");
                            out.println("</form>");
                        }

                        List<Review> productReviews = (List<Review>) request.getAttribute("productReviews");
                        List<Users> reviewOwners = (List<Users>) request.getAttribute("reviewOwners");
                        for (Review review : productReviews) {
                            out.println("<b>Name: </b>");
                            for (Users owner : reviewOwners) {
                                if (owner.getUserId() == review.getUserId()) {
                                    out.println(owner.getName() + "<br>");
                                    break;
                                }
                            }
                            Date reviewDate = review.getDate();
                            String reviewDateText = df.format(reviewDate);
                            out.println("<b>Date: </b>" + reviewDateText + "<br>");
                            out.println("<b>Text: </b>" + review.getText() + "<br>");
                            out.println("<b>Score: </b>" + review.getScore() + "<br><br>");
                        }
                    %>
                </fieldset>

                <form action="FrontController">
                    <input type="hidden" name="id" value="${element.productId}">
                    <!--
                    <input type="hidden" name="name" value="${element.name}">
                    <input type="hidden" name="quantity" value="${element.quantity}">
                    <input type="hidden" name="available" value="${element.available}">
                    <input type="hidden" name="price" value="${element.price}">
                    <input type="hidden" name="cost" value="${element.cost}">
                    -->
                    <input type="submit" value="Add to cart">
                    <input type="hidden" name="command" value="AddToCartCommand">
                </form>
                    
                <form action="FrontController">
                    <input type="hidden" name="id" value="${element.productId}">
                    <input type="hidden" name="name" value="${element.name}">
                    
                    <input type="submit" value="Stats">
                    <input type="hidden" name="command" value="ShowProductStatsCommand">
                </form>

                <%
                    if (loggedUser != null) {
                %>
                <form action="FrontController">
                    <input type="hidden" name="id" value="${element.productId}">
                    <input type="hidden" name="name" value="${element.name}">
                    <input type="hidden" name="quantity" value="${element.quantity}">
                    <input type="hidden" name="available" value="${element.available}">
                    <input type="hidden" name="price" value="${element.price}">
                    <input type="hidden" name="cost" value="${element.cost}">

                    <input type="submit" value="Add to my wishlist">
                    <input type="hidden" name="command" value="AddToWishListCommand">
                </form>
                <%
                    }
                %>

            </c:forEach>
                </div>
            </div>
        </fieldset>
    </div>
</body>
</html>
