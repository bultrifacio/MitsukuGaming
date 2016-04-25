<%@page import="java.util.List"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="entities.Product"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
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
        <script>
            function newPageFunction() {
                var myWindow = window.open("", "", "width=600, height=100");
                myWindow.document.write("<p>Your can reset your password in the following link: </p>\n\
            http://localhost:8080/mg2_5-war/newPassword.jsp");
            }
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">
            <h1>Reset password</h1>
            <form action="FrontController" method="post">
                <input type="hidden" name="command">
                <input type="submit" value="Go to Main page" class="btn-link">
            </form><br><br>
            <form action="FrontController" method="post">
                <fieldset>
                    <legend>Insert your email:</legend>
                    <%                    Integer validEmail = (Integer) session.getAttribute("validEmail");

                        if (validEmail != null) {
                            if (validEmail == 1) {
                                String usermail = (String) session.getAttribute("userEmail");
                                out.println("You have received an email to reset your password");
                                out.println("<input type=\"submit\" value=\"See email\" onclick=\"newPageFunction()\">");
                            } else {
                                out.println("That email doesn't exists in our system.<br>");
                                out.println("<input type=\"text\" name=\"email\" placeholder=\"Insert your email\">");
                                out.println("<input type=\"hidden\" name=\"command\" value=\"CheckEmailCommand\">");
                                out.println("<input type=\"submit\"  value=\"Reset password\">");
                            }

                        } else {
                            out.println("<input type=\"text\" name=\"email\" placeholder=\"Insert your email\">");
                            out.println("<input type=\"hidden\" name=\"command\" value=\"CheckEmailCommand\">");
                            out.println("<input type=\"submit\"  value=\"Reset password\">");
                        }

                    %>
                </fieldset>
            </form>
        </div>
    </body>
</html>
