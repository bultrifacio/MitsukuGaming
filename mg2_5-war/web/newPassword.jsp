<%-- 
    Document   : newPassword
    Created on : 28-mar-2016, 13:47:20
    Author     : ENTRAR
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
        <title>Insert new password</title>
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
        <h1>New password</h1>
        <br><br>
        <form method="post" action="FrontController">
            <fieldset>
                <legend>Insert the new password:</legend>
                <input type="password" name="pass1" placeholder="Insert your password">
                <input type="password" name="pass2" placeholder="Repeat your password">
                <input type="submit" value="Modify password">
                <input type="hidden" name="command" value="ModifyPasswordCommand">
            </fieldset>
            <%
                if(((Integer)session.getAttribute("passNoEqual"))!=null){
                    if(((Integer)session.getAttribute("passNoEqual"))==1){
                        out.println("Please, enter equal password.");
                    }
                }
            %>
        </form>
    </body>
</html>
