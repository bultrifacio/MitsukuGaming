<%@page import="entities.ShoppingCartLocal"%>
<%@page import="entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                            <a href="supportform.jsp">Contact</a>
                        </li>
                        <li>
                            <table>
                                <tr>


                                    <%
                                        Users loggedUser = (Users) session.getAttribute("loggedUser");
                                        if (loggedUser != null) {
                                    %>
                                    <td>
                                        <input type="text" name="username" value="Welcome <%= loggedUser.getName()%>">
                                    </td>
                                    <td>
                                        <form method="post" action="FrontController">
                                            <input type="hidden" name="command" value="ShowProfileCommand">
                                            <input type="hidden" name="id" value="<%= loggedUser.getUserId()%>">
                                            <input type="hidden" name="name" value="<%= loggedUser.getName()%>">
                                            <input type="hidden" name="email" value="<%= loggedUser.getEmail()%>">
                                            <input type="hidden" name="password" value="<%= loggedUser.getPassword()%>">
                                            <input type="submit" value="Modify profile">
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="FrontController">
                                            <input type="hidden" name="command" value="LogoutCommand">
                                            <input type="submit" value="Logout" class="btn-link">
                                        </form>
                                    </td>
                                    <%
                                    } else {
                                    %>

                                    <%
                                        if ((Integer) session.getAttribute("wrongEmail") != null) {
                                            if ((Integer) session.getAttribute("wrongEmail") == 1) {
                                                out.println("<div class=\"error-text\">That email doesn't exists in our system.<br></div>");
                                                session.setAttribute("wrongEmail", null);
                                            } else {
                                                if ((Integer) session.getAttribute("wrongPassword") == 1) {
                                                    out.println("<div class=\"error-text\">You have entered a wrong password.<br></div>");
                                                    session.setAttribute("wrongPassword", null);
                                                    session.setAttribute("wrongEmail", null);
                                                }
                                            }
                                        }

                                    %>
                                <form method="post" action="FrontController">
                                    <td class="space-td">
                                        <input type="text" name="email" placeholder="Email">
                                    </td>
                                    <td class="space-td">
                                        <input type="password" name="password" placeholder="Password">
                                    </td>
                                    <input type="hidden" name="command" value="LoginCommand">
                                    <td>
                                        <input type="submit" value="Login">
                                    </td>
                                </form>
                                <%                                }
                                    ShoppingCartLocal cart = (ShoppingCartLocal) session.getAttribute("Cart");
                                    int productNumber = 0;
                                    if (cart != null) {
                                        productNumber = cart.getContents().size();
                                    }
                                %>

                                <td class="space-currency">
                                    <form method="post" action="FrontController">
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
                                </td>
                                <td class="space-currency">
                                    <form method="post" action="FrontController">
                                        <input type="hidden" name="command" value="ShowCartCommand">
                                        <input type="image" src="img/cart-icon.png" alt="Submit Form" />
                                    </form>
                                </td>
                                <td>
                                    <strong><p class="cart-btn">(<%=productNumber%>)</p></strong>
                                </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="resetPassword.jsp">Forgot password?</a>
                                    </td>
                                </tr>
                            </table>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>
    </body>
</html>
