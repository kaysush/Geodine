<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='http://fonts.googleapis.com/css?family=Lato:700,400italic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
        <link rel="icon" href="favicon.ico" type="image/x-icon">


        <!--[if IE]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!-- ENDS JS -->


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>

        <script type="text/javascript" src="js/custom.js"></script>
        <script src="js/slides/source/slides.min.jquery.js"></script>
        <script src="js/ajaxify.js" type="text/javascript"></script>
        <script src="js/jquery.backstretch.min.js" type="text/javascript"></script>
        <script src="js/jQueryRotate.2.2.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Bubbler+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Boogaloo' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Finger+Paint' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=McLaren' rel='stylesheet' type='text/css'>
        <title>Geodine-User Login</title>
    </head>
    <body>
        <div id="headernav">
            <div id="back" class="btn"></div>
            <div id="next" class="btn"></div>
        </div>
        <div id="top-gap"></div>
        <div class="wrapper">
            <a class="logohead" href=""><img id="logo" src="img/logo.png" alt="Geodine"  ></a>
            <div id="nav-bar-holder">
                <div class="navbar">
                    <ul>
                        <li>
                            <a href="/geodine"><img src="img/home.png" height="64" width="64" alt="home" /></a>
                            <span>Home</span>
                        </li>
                        <li>
                            <a href="app"><img src="img/app.png" height="64" width="64" alt="Goto to App" /></a>
                            <span>Goto App</span>
                        </li>
                        <li>
                            <a href="blog"><img src="img/blog.png" height="64" width="64" alt="Visit our blog" /></a>
                            <span>Visit  our Blog</span>
                        </li>
                        <li>
                            <a href="contact"><img src="img/contactus.png" height="64" width="64" alt="Contact Us" /></a>
                            <span>Contact Us</span>
                        </li>
                    </ul>
                </div>





            </div>



            <div id="content-wrap">

                <div id="page-wrap">



                    <div id="front-slides">
                        <div class="slides_container">
                            <div class="slide">

                                <div class="youtube" style="left :0px;">
                                    <iframe id="ytplayer" type="text/html" width="940" height="360" src="https://www.youtube.com/embed/XNAnfWpWEMg" >
                                    </iframe>
                                </div>
                            </div>
                        </div>

                        <div id="front-slides-cover"></div>


                        <div id="headline"><h6>Geodine-A new approach for foodies</h6></div>


                    </div>




                    <div id="reel" style="height: 500px; background:#ffffff">
                        <div class="slides_container" style="height: auto; position: absolute;
                             overflow: hidden;
                             top: 20px;
                             left :250px;
                             width: 400px;

                             margin-left: 20px;
                             box-shadow: 0px 0px 20px #999; /* CSS3 */
                             -moz-box-shadow: 0px 0px 20px #999; /* Firefox */
                             -webkit-box-shadow: 0px 0px 20px #999; /* Safari, Chrome */
                             border-radius:3px 3px 3px 3px;
                             -moz-border-radius: 3px; /* Firefox */
                             -webkit-border-radius: 3px; /* Safari, Chrome */

                             background: #ffffff; /* Old browsers */
                             background: -moz-linear-gradient(top,  #ffffff 0%, #f6f6f6 47%, #ededed 100%); /* FF3.6+ */
                             background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(47%,#f6f6f6), color-stop(100%,#ededed)); /* Chrome,Safari4+ */
                             background: -webkit-linear-gradient(top,  #ffffff 0%,#f6f6f6 47%,#ededed 100%); /* Chrome10+,Safari5.1+ */
                             background: -o-linear-gradient(top,  #ffffff 0%,#f6f6f6 47%,#ededed 100%); /* Opera 11.10+ */
                             background: -ms-linear-gradient(top,  #ffffff 0%,#f6f6f6 47%,#ededed 100%); /* IE10+ */
                             background: linear-gradient(to bottom,  #ffffff 0%,#f6f6f6 47%,#ededed 100%); /* W3C */
                             filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#ededed',GradientType=0 ); /* IE6-9 */
                             ">





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
                                <a href="#"><img src="img/facebook.png" alt="Facebook" height="32" width="32"/>
                                    <span>Like on Facebook</span></a>
                            </li> 
                            <li>
                                <a href="#"><img src="img/twitter.png" alt="Twitter" height="32" width="32" />
                                    <span>Follow on Twitter</span></a>
                            </li>
                            <li>
                                <a href="#"><img src="img/google-plus.png" alt="Google +" height="32" width="32" />
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



