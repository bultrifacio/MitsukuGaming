<%-- 
    Document   : modifyProduct
    Created on : 14-mar-2016, 17:12:50
    Author     : Blarzek
--%>

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
    </head>
    <body>
        <h1>Reset password</h1>
        <form action="FrontController">
            <input type="hidden" name="command">
            <input type="submit" value="Go to Main page" class="btn-link">
        </form><br><br>
        <form action="FrontController">
            <fieldset>
                <legend>Insert your email:</legend>
                <%
                    Integer validEmail = (Integer) session.getAttribute("validEmail");

                    if (validEmail != null) {
                        if (validEmail == 1) {
                            out.println("You have received a message to reset your password");
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
    </body>
</html>
