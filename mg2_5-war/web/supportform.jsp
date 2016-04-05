<%-- 
    Document   : supportform
    Created on : 28-mar-2016, 5:49:28
    Author     : Ismael
--%>

<%@page import="entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Support Form</title>
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
                                    out.println("<form action=\"FrontController\">");
                                    out.println("<input type=\"hidden\" name=\"command\" value=\"ShowCartCommand\">");
                                    out.println("<input type=\"submit\" value=\"Show cart\" class=\"btn-link\">");
                                    out.println("</form>");
                                } else {
                                    out.println("<form action=\"FrontController\">");
                                    out.println("<input type=\"text\" name=\"username\" placeholder=\"Username\">");
                                    out.println("<input type=\"password\" name=\"password\" placeholder=\"Password\">");
                                    out.println("<input type=\"hidden\" name=\"command\" value=\"LoginCommand\">");
                                    out.println("<input type=\"submit\" value=\"Login\">");
                                    out.println("<a href=\"resetPassword.jsp\">Forgot password?</a>");
                                    out.println("</form>");
                                    out.println("<form action=\"FrontController\">");
                                    out.println("<input type=\"hidden\" name=\"command\" value=\"ShowCartCommand\">");
                                    out.println("<input type=\"submit\" value=\"Show cart\" class=\"btn-link\">");
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
            <!-- /.container -->
        </nav>
        <br>
        <br>
        <div class="container">
            <form action="FrontController">
                <fieldset>
                    <legend>Technical Support:</legend>
                    Explain your problem:<br>
                    <textarea name="comentarios" rows="10" cols="40"></textarea><br>
                    <input type="hidden" name="command" value="SupportCommand">
                    <input type="submit" value="Sent">
                </fieldset>
            </form>
        </div>
    </body>
</html>
