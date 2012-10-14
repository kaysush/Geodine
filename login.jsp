<%-- 
    Document   : login
    Created on : Sep 12, 2012, 9:10:26 PM
    Author     : sushil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${sessionScope.userName!=null}">
    <c:redirect url="app.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href='http://fonts.googleapis.com/css?family=Skranji:700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Cagliostro' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Metamorphous' rel='stylesheet' type='text/css'>

        <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Lato:700,400italic' rel='stylesheet' type='text/css'>

        <script src="resources/js/jquery-1.8.1.min.js"></script>
        
        <title>Geodine-User Login</title>
    </head>
    <body>
        

                <c:if test="${param.login!=null}">
                    <font size="2px" color="red">You must first login to use <strong>Geodine</strong></font>
                </c:if>
                <c:if test="${param.request_reset!=null}">
                    <font size="2px" color="red">You have requested a <strong>Password Rest</strong>.Check your mail for instructions.</font>
                </c:if>

                <c:if test="${param.login_attempt!=null}">
                    <font size="2px" color="red">Boooo!!! You should remember you credentials.</font>
                </c:if>

                <form method="post" action="auth/login">
                    <label for="user-name">Email</label>
                    <input type="text" name="user-name"/></br>
                    <label for="user-pass">Password</label>
                    <input type="password" name="user-pass"/></br>
                    <input type="submit" name="login" value="Login"/>
                </form>
            
    </body>
</html>
