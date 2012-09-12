<%-- 
    Document   : login
    Created on : Sep 12, 2012, 9:10:26 PM
    Author     : sushil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Geodine-User Login</title>
    </head>
    <body>
        <%
        if(session.getAttribute("user-name")==null)
                      {
            
        %>
        <%
        
        if(request.getParameter("login")!=null)
                       {
        %>
        <font size="2px" color="red">You muse first login to use <strong>Geodine</strong></font>
        <%}%>
        <%
        
        if(request.getParameter("error")!=null)
                       {
        %>
        <font size="2px" color="red">Boooo!!! You should remember you credentials.</font>
        <%}%>
        <form method="post" action="auth/login">
            <label for="user-name">Email</label>
            <input type="text" name="user-name"/></br>
            <label for="user-pass">Password</label>
            <input type="password" name="user-pass"/></br>
            <input type="submit" name="login" value="Login"/>
        </form>
        <%
               }
        
        else
       {
           response.sendRedirect("http://localhost:8080/geodine/app.jsp");
       }
       %>
    </body>
</html>
