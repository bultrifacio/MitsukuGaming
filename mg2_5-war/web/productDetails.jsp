<%@page import="entities.Video"%>
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
        <title>Buy <%= request.getAttribute("name") %> on Mitsuku Gaming</title>
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
        <h1>Buy <%= request.getAttribute("name") %></h1>
        <br><br>
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
                            <li data-target="img/games/${element.path}" data-slide-to="<%= index%>" class="active"></li>

                            <c:forEach var="element" items="${imageFilter}">
                                <% 
                                    if (active) { 
                                %>
                                <li data-target="img/games/${element.path}" data-slide-to="<%= index%>" class="active"></li>
                                <% 
                                    active = false;
                                    } else {    
                                %>
                                <li data-target="img/games/${element.path}" data-slide-to="<%= index%>"></li>
                                <%
                                    }
                                    index++;
                                %>
                            </c:forEach>
                        </ol>
                        <div class="carousel-inner">
                            <%
                                List<Video> videoList = (List<Video>) request.getAttribute("videoFilter");
                                for (Video video : videoList) {   
                            %>
                            <div class="item">
                                <center>
                                <iframe width="850" height="312" src=<%=video.getUrl()%> frameborder="1" allowfullscreen=""></iframe>
                                </center>
                            </div>
                            <% 
                                }
                                active = true;
                            %>
                            <c:forEach var="element" items="${imageFilter}">
                                <% 
                                    if (active) {
                                %>
                                <div class="item active">
                                    <img class="slide-image" src="img/games/${element.path}" alt="">
                                </div>
                                <%
                                    active = false;
                                    } else {
                                %>
                                <div class="item">
                                    <img class="slide-image" src="img/games/${element.path}" alt="">
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
                <!-- <b>ID:</b><br>
                ${element.productId}<br> -->
                <b>Product name:</b><br>
                ${element.name}<br>
                <b>Category:</b><br>
                ${element.category}<br>
                <b>Price:</b><br>
                ${element.price}
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
                <br>

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
                <%= formatedDate %><br>
                <b>Available:</b><br>
                <% 
                    //if ((int) request.getAttribute("available") == 1) {
                %>
                Yes
                <%
                //} else if ((int) request.getAttribute("available") == 0) {
                %>
                No
                <%
                //}
                %>
                <!-- ${element.available} --><br>
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
                                <form action="FrontController" method="post">
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
                                <br>
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
                    <form action="FrontController" method="post">
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

                <form action="FrontController" method="post">
                    <input type="hidden" name="id" value="${element.productId}">
                    <!--
                    <input type="hidden" name="name" value="${element.name}">
                    <input type="hidden" name="quantity" value="${element.quantity}">
                    <input type="hidden" name="available" value="${element.available}">
                    <input type="hidden" name="price" value="${element.price}">
                    <input type="hidden" name="cost" value="${element.cost}">
                    
                    <input type="submit" value="Add to cart">
                    -->
                    <input type="hidden" name="command" value="AddToCartCommand">
                    <input type="image" src="img/icons/add-to-cart-icon.png" alt="Submit Form" />
                </form>
                    
                <form action="FrontController" method="post">
                    <input type="hidden" name="id" value="${element.productId}">
                    <input type="hidden" name="name" value="${element.name}">
                    
                    <input type="submit" value="Stats">
                    <input type="hidden" name="command" value="ShowProductStatsCommand">
                </form>

                <%
                    if (loggedUser != null) {
                %>
                <form action="FrontController" method="post">
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
