<%-- 
    Document   : index
    Created on : Sep 12, 2012, 4:09:52 PM
    Author     : sushil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
                <title>Geodine-A new approach for foodies</title>
       
            
        
                
        
               </head>
    <body>
        
        
        <%
        if(session.getAttribute("user-name")==null)
                      {
            
        %>
        <h1>Login for existing users</h1>
        <form method="post" action="auth/login">
            <label for="user-name">Email</label>
            <input type="text" name="user-name"/></br>
            <label for="user-pass">Password</label>
            <input type="password" name="user-pass"/></br>
            <input type="submit" name="login" value="Login"/>
        </form>
        <h1>Signup for new users</h1>
        <form method="post" action="signup">
            <label for="first-name">First Name</label>
            <input type="text" name="first-name"/></br>
            <label for="last-name">Last Name</label>
            <input type="text" name="last-name"/></br>
            <label for="user-name">Email</label>
            <input type="text" name="user-name"/></br>
            <label for="user-pass">Password</label>
            <input type="password" name="user-pass"/></br>
            <input type="submit" name="signup" value="Signup"/>
        </form>
        <%
               }
        else
       {
           response.sendRedirect("app.jsp");
       }
       %>
        <!--font color="green">Hurray... you are logged in <%=session.getAttribute("user-name")%></font>-->
        
        
        
    </body>
</html>
