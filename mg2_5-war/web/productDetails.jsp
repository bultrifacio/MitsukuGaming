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

        <link href="bootstrap/bootstrap.css" rel="stylesheet">
        <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
        <script src="bootstrap/jquery.js"></script>
        <script src="bootstrap/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h1>Buy product</h1>
            <form action="FrontController">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form>
            <form action="FrontController">
                <input type="hidden" name="command" value="ShowCartCommand">
                <input type="submit" value="Show cart" class="btn-link">
            </form><br><br>
            <fieldset>
                <legend>Product information:</legend>
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
                        <legend>User reviews</legend>
                        <%
                            Users loggedUser = (Users) session.getAttribute("loggedUser");
                            if (loggedUser != null) {
                                out.println("<form action=\"FrontController\">");
                                out.println("<input type=\"hidden\" name=\"productId\" value=\"" + request.getParameter("id") + "\">");
                                out.println("<input type=\"text\" name=\"text\" placeholder=\"Write your review here\">");
                                out.println("<input type=\"text\" name=\"score\" placeholder=\"Your score\">");
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
                        <input type="hidden" name="name" value="${element.name}">
                        <input type="hidden" name="quantity" value="${element.quantity}">
                        <input type="hidden" name="available" value="${element.available}">
                        <input type="hidden" name="price" value="${element.price}">
                        <input type="hidden" name="cost" value="${element.cost}">

                        <input type="submit" value="Add to cart">
                        <input type="hidden" name="command" value="AddToCartCommand">
                    </form>

                        
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

                </c:forEach>
            </fieldset>
        </div>
    </body>
</html>
