<%-- 
    Document   : recover
    Created on : Sep 21, 2012, 6:01:23 PM
    Author     : sushil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
        <script src="ajax.js" type="text/javascript"></script>
        <title>Password Recovery-Geodine</title>
    </head>
    <body>

    <h1>Recover Password</h1>
    <form name="recover" action="">
        <input class="hidden_param" type="hidden" name="type" value="recovery"/>
        <input class="recovery_email" type="text" name="email"/>
        <input class="recover" type="submit" name="submit" value="Recover Password" /></br>
        <div class="status"></div>
        
    </form>

     
    </body>
</html>
