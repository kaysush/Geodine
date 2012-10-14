<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<c:if test="${sessionScope.userName==null}">
    <c:redirect url="login.jsp?login=none"/>
</c:if>
<!DOCTYPE html>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="cs/main.css" type="text/css" />
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
        <link href='http://fonts.googleapis.com/css?family=Skranji:700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Nothing+You+Could+Do' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Metamorphous' rel='stylesheet' type='text/css'>
        <link rel="icon" href="/favicon.ico" type="image/x-icon"> 
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <script type="text/javascript" src="js/jquery-ui-i18n.min.js"></script>
        <script  type="text/javascript" src="js/script.js"></script>
        <script type="text/javascript">var switchTo5x=true;</script>
        <script type="text/javascript">stLight.options({publisher: "043a2aff-de02-4bbb-94b3-27f33cdfbcab"}); </script>
        <script type="text/javascript"
                src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCTXBgCubUQLyVqA6e5y0Br5HTW4buErkQ&sensor=true">
        </script>
        <title>Geodine-A new approach for foodies.</title>
    </head>

    <body onload="initialize()">


        <div class="app">
            <div id="map_canvas" >

            </div>
        </div>
    



    <div class="results">
        <div class="country"><text>Cuisines</text>
        </div>
        <div class="output">
            <div class="select">Click on map for food travel.
            </div>
            <div class="loader">
                <div class="ball"></div>
                <div class="ball1"></div>
            </div>
        </div>
    </div>
    <div class="recepiecontainer">
        <div class="recepieheader"><text>Recepies</text>
        </div>
        <div class="recepie">
        </div>
    </div>

   


</body>


</html>





