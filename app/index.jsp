<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.userName==null}">
    <c:redirect url="../login.jsp?login=none"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <style type="text/css">
            html { height: 100% }
            body { height: 100%; margin: 0; padding: 0 }
            #map_canvas { height: 100% }
        </style>
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCTXBgCubUQLyVqA6e5y0Br5HTW4buErkQ&sensor=true"></script>

        <link rel="stylesheet" href="../css/main.css" type="text/css" media="screen" />
        <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon">
        <link rel="icon" href="../favicon.ico" type="image/x-icon">


        <!--[if IE]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!-- ENDS JS -->


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
        <script type="text/javascript" src="../js/custom1.js"></script>
        <script src="../js/slides/source/slides.min.jquery.js"></script>
        <script src="../js/ajaxify.js" type="text/javascript"></script>
        <script src="../js/jquery.backstretch.min.js" type="text/javascript"></script>
        <script src="../js/jQueryRotate.2.2.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Bubbler+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Boogaloo' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Finger+Paint' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=McLaren' rel='stylesheet' type='text/css'>
        <title>Geodine-Application</title>
    </head>
    <body onload="initialize()">
        <div id="headernav">
            <div id="back" class="btn"></div>
            <div id="next" class="btn"></div>
        </div>


        <div id="top-gap"></div>


        <div class="wrapper">

            <a class="logohead" href="/geodine"><img id="logo" src="../img/logo.png" alt="Geodine"  ></a>



            <div id="nav-bar-holder">
                <div class="navbar">
                    <ul>
                        <li>
                            <a href="/geodine"><img src="../img/home.png" height="64" width="64" alt="home" /></a>
                            <span id="home">Home</span>
                        </li>
                        <li>
                            <a href="/geodine/app"><img src="../img/app.png" height="64" width="64" alt="Goto to App" /></a>
                            <span id="app">App</span>
                        </li>
                        <li>
                            <a href="/geodine/blog/"><img src="../img/blog.png" height="64" width="64" alt="Visit our blog" /></a>
                            <span id="blog">Visit  our Blog</span>
                        </li>
                        <li>
                            <a href="/geodine/contact.jsp"><img src="../img/contactus.png" height="64" width="64" alt="Contact Us" /></a>
                            <span id="contact">Contact Us</span>
                        </li>
                    </ul>
                </div>

                <div class="headlogin">
                    <button onclick="window.location='../Logout'"  class="login-window orange">Logout</button>
                </div>



            </div>



            <div id="content-wrap">
                <div id="page-wrap">



                    <div id="front-slides">

                        <div class="slides_container">
                            <div class="slide">
                                <div id="map_canvas" style="width:938px; height:320px"></div>
                                <div class="caption">Select A Country</div>
                            </div>

                        </div>


                        <div id="front-slides-cover"></div>


                        <div id="headline"><h6>Geodine-A new approach for foodies</h6></div>


                    </div>




                    <div id="reel" >
                        <div class="slides_container">
                            <div class="country_header">Cuisines</div>
                            <div id="country" style="color:black">

                            </div>

                        </div>

                        <div class="data-box">
                            <div class="data-header">
                                Recipe
                                <!--div class="swap"></div-->
                                <button class='swap' style="background: url(../img/swap.png);"></button>
                                <div class="message">Click to toggle view</div>
                            </div>


                            <div class="data"></div>
                            <div class="yt">
                                <iframe id="ytr" type="text/html" width="600" height="305" src="https://www.youtube.com/embed/XNAnfWpWEMg" >
                                </iframe>
                            </div>
                        </div>
                    </div>





                    <div class="featured-title">
                        <div class="ribbon"><span>Geodine Special</span></div>

                    </div>
                    <div class="featured">
                        <div class="youtube">
                            <iframe id="ytplayer" type="text/html" width="380" height="280" src="https://www.youtube.com/embed/XNAnfWpWEMg" >
                            </iframe>
                        </div>
                        <div class="fav-header"><a href="../util/pdf">Download your Cookbook</a></div>
                        <div class="recipeoftheday">

                            <h6>Favourites</h6>
                            <!--img src="../img/loading.gif" alt="loader"/-->
                            <div class="food-feeds"><ul></ul></div>

                        </div>
                        <div class="poweredby">

                        </div>
                    </div>
                </div>
            </div>
            </br>


        </div>
        <br/>


        <div id="footer-bottom">

            <div class="bottom-wrapper">
                <div id="bottom-left">
                    <div class="social">
                        <span>Feeling little social? Spread the buzz, share with your friends</span><br/>
                        <ul>
                            <li>
                                <a href="#"><img src="../img/facebook.png" alt="Facebook" height="32" width="32"/>
                                    <span>Like on Facebook</span></a>
                            </li> 
                            <li>
                                <a href="#"><img src="../img/twitter.png" alt="Twitter" height="32" width="32" />
                                    <span>Follow on Twitter</span></a>
                            </li>
                            <li>
                                <a href="#"><img src="../img/google-plus.png" alt="Google +" height="32" width="32" />
                                    <span>+1 on Google Plus</span></a>
                            </li>
                        </ul>
                    </div>
                    &copy; 2012 Geodine  
                </div>
                <div id="bottom-right">
                    <ul id="footer-nav">
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Blog</a></li>
                        <li><a href="#">App</a></li>
                        <li><a href="#">Contact Us</a></li>
                    </ul>
                </div>
            </div>
        </div>



    </body>
</html>
