<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Lato:700,400italic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="../style1.css" type="text/css" media="screen" />
        <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon">
        <link rel="icon" href="../favicon.ico" type="image/x-icon">


        <!--[if IE]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!-- ENDS JS -->


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>

        <script type="text/javascript" src="../custom.js"></script>
        <script src="../js/slides/source/slides.min.jquery.js"></script>
        <script src="../js/ajaxify.js" type="text/javascript"></script>
        <script src="../js/jquery.backstretch.min.js" type="text/javascript"></script>
        <script src="../js/jQueryRotate.2.2.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Bubbler+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Boogaloo' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Finger+Paint' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=McLaren' rel='stylesheet' type='text/css'>
        <title>Geodine-Recipe</title>
    </head>
    <body>
        <div id="headernav">
            <div id="back" class="btn"></div>
            <div id="next" class="btn"></div>
        </div>
        <div id="top-gap"></div>
        <div class="wrapper">
            <a class="logohead" href=""><img id="logo" src="../img/logo.png" alt="Geodine"  ></a>
            <div id="nav-bar-holder">
                <div class="navbar">
                    <ul>
                        <li>
                            <a href="/geodine"><img src="../img/home.png" height="64" width="64" alt="home" /></a>
                            <span>Home</span>
                        </li>
                        <li>
                            <a href="../app"><img src="../img/app.png" height="64" width="64" alt="Goto to App" /></a>
                            <span>Goto App</span>
                        </li>
                        <li>
                            <a href="../blog"><img src="../img/blog.png" height="64" width="64" alt="Visit our blog" /></a>
                            <span>Visit  our Blog</span>
                        </li>
                        <li>
                            <a href="../contact.jsp"><img src="../img/contactus.png" height="64" width="64" alt="Contact Us" /></a>
                            <span>Contact Us</span>
                        </li>
                    </ul>
                </div>





            </div>



            <div id="content-wrap">

                <div id="page-wrap">



                    <div class="youtube" >
                        <iframe id="ytplayer" type="text/html" width="640" height="480" src="https://www.youtube.com/embed/${requestScope.ytid}" >
                        </iframe>
                    </div>






                    <div id="reel" style="height: 500px; background:#ffffff">
                        <div class="slides_container" style="width: 920px;left:30px;padding-left:20px;">
                            <div class='data'>
                                <h1><c:out value="${requestScope.name}"></c:out></h1>
                                <b>Country : </b><c:out value="${requestScope.country}"></c:out><br/><br/>
                                <b>Ingredients</b><br/>
                                <ul>
                                    <c:forEach items="${requestScope.ingredients}" var="item">
                                        <li><c:out value="${item}"></c:out></li>
                                    </c:forEach>
                                </ul>
                                <br/><br/>
                                <b>Directions</b><br/>
                                <ul>
                                    <c:forEach items="${requestScope.directions}" var="item">
                                        <li><c:out value="${item}"></c:out></li>
                                    </c:forEach>
                                </ul>

                            </div>

                        </div>
                    </div>






                </div>
            </div>
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
                        <li><a href="/geodine">Home</a></li>
                        <li><a href="blog">Blog</a></li>
                        <li><a href="app">App</a></li>
                        <li><a href="contact">Contact Us</a></li>
                    </ul>
                </div>
            </div>
        </div>

    </body>
</html>



