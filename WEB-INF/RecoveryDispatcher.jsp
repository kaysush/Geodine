<%-- 
    Document   : RecoveryDispatcher
    Created on : Sep 26, 2012, 10:09:22 PM
    Author     : sushil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Recovery-Geodine</title>
    </head>
    <body>

        <form method="POST" action="/geodine/auth/recover">
            <input type="hidden" name="email" value="<%=request.getAttribute("email")%>"/>
            <input type="hidden" name="type" value="reset"/>
            <label title="password">Password</label>
            <input type="text" name="password"/></br>
            <label title="confirm password">Confirm Password</label>
            <input type="text" name="confirm_password"/>
            <input type="submit" name="submit" value="Reset Password"/>
        </form>
    </body>
</html>
