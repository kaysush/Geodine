

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        
        <title>Geodine-Signup</title>
    </head>
    <body>
        
                <%

                    if (request.getParameter("captcha") != null) {
                %>
                <font size="2px" color="red">Wrong <strong>CAPTCHA</strong> code. Enter again</font>
                <%}%>

                <%

                    if (request.getParameter("email") != null) {
                %>
                <font size="2px" color="red"><strong>Email</strong> already exists</font>
                <%}%>
                <form method="post" action="signup">
                    <label for="first-name">First Name</label>
                    <input type="text" name="first-name"/></br>
                    <label for="last-name">Last Name</label>
                    <input type="text" name="last-name"/></br>
                    <label for="user-name">Email</label>
                    <input type="text" name="user-name"/></br>
                    <label for="user-pass">Password</label>
                    <input type="password" name="user-pass"/></br>
                    <img src="auth/captcha"/>
                    <input type="text" name="captcha-code"/></br>
                    <input type="submit" name="signup" value="Signup"/>
                </form>
            
    </body>
</html>
