<%-- 
    Document   : app
    Created on : Sep 12, 2012, 9:25:22 PM
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
            response.sendRedirect("http://localhost:8080/geodine/login.jsp?login=none");
                       
        %>
        
        <%
        }    
            else
            {
        %>
       <font color="green">Hurray... you are logged in <%=session.getAttribute("user-name")%></font>
       <%}%>
    </body>
</html>
