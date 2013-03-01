<%-- 
    Document   : login
    Created on : Sep 12, 2012, 9:10:26 PM
    Author     : sushil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${sessionScope.userName!=null}">
    <c:redirect url="app/index.jsp"/>
</c:if>
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
                            <span id="home">Home</span>
                        </li>
                        <li>
                            <a href="/geodine/app/"><img src="img/app.png" height="64" width="64" alt="Goto to App" /></a>
                            <span id="app">Goto App</span>
                        </li>
                        <li>
                            <a href="/geodine/blog/"><img src="img/blog.png" height="64" width="64" alt="Visit our blog" /></a>
                            <span id="blog">Visit  our Blog</span>
                        </li>
                        <li>
                            <a href="/geodine/contact.jsp"><img src="img/contactus.png" height="64" width="64" alt="Contact Us" /></a>
                            <span id="contact">Contact Us</span>
                        </li>
                    </ul>
                </div>
            </div>



            <div id="content-wrap">

                <div id="page-wrap">



                    <div id="front-slides">
                        <div class="slides_container">
                            <div class="slide">
                                <img src="img/dummies/1.jpg"  alt="Geodine" width="940" height="360"  />
                                <div class="caption">Geodine-A new approach for foodies</div>
                            </div>
                            <div class="slide">
                                <img src="img/dummies/2.jpg" alt="Geodine" width="940" height="360"  />
                                <div class="caption">It lets you browse recipies from all over world.</div>
                            </div>
                            <div class="slide">
                                <img src="img/dummies/3.jpg" alt="Geodine" width="940" height="360"  />
                                <div class="caption">Browse cuisines and beverages against geographical locations.</div>
                            </div>
                        </div>

                        <div id="front-slides-cover"></div>


                        <div id="headline"><h6>Geodine-A new approach for foodies</h6></div>


                    </div>




                    <div id="reel" style="height: 500px; background:#ffffff">
                        <div class="slides_container" style="height: 450px; position: absolute;
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

                            <div id="login" >  

                                <h1>User Login</h1>
                                <c:if test="${param.login!=null}">
                                    <span class="statuses">You must first login to use <strong>Geodine</strong></span>
                                </c:if>

                                <form method="POST" class="signin" action="auth/login">
                                    <span class="status"></span><br/>
                                    <label for="user-name1">Email&nbsp;&nbsp;</label>
                                    <input id="user-name1" type="text" name="user-name" class="formtextfield email"/><br/><br/>
                                    <label for="user-pass">Password&nbsp;&nbsp;</label>
                                    <input id="user-pass" type="password" name="user-pass" class="formtextfield password"/><br/>
                                    <br/><input id="login-button" type="submit" name="login" value="Login" class="button"/><br/>

                                </form>
                                <img class="login-loader" src="img/loading.gif" alt="loading" />
                                <br/><a class="forgot" href="recover.jsp">Forgot your password?</a>

                                <br/><p>or</p>
                                <img class="fblogin" src="img/facebook-login.png" alt="Login With Facebook" style="margin-left:120px;"/><br/><br/><br/><br/>
                                <a href="#" id="signuppress"> Sign Up for new users</a>
                            </div>       
                            <div id="signup">
                            <h1>Signup for new users</h1>
                            <span id="signup-status">Error!!! First Name Required</span>
                            <form method="post" class="signup" action="signup">
                                <label for="fname">First Name&nbsp;&nbsp;</label>
                                <input id="fname" type="text" name="first-name" class="formtextfield"/><br/><br/>
                                <label for="lname">Last Name&nbsp;&nbsp;</label>
                                <input id="lname" type="text" name="last-name" class="formtextfield"/><br/><br/>
                                <label for="user-name">Email&nbsp;&nbsp;</label>
                                <input id="user-name" type="text" name="user-name" class="formtextfield"/><br/><br/>
                                <label for="password1">Password&nbsp;&nbsp;</label>
                                <input id="password1" type="password" name="user-pass" class="formtextfield"/><br/><br/>
                                <label for="password2">Re-Enter&nbsp;&nbsp;</label>
                                <input id="password2" type="password" name="user-pass2" class="formtextfield"/><br/><br/>
                                <img class="captcha-image" src="auth/captcha" alt="CAPTCHA" /><br/>
                                <label for="captcha-code">CAPTCHA&nbsp;&nbsp;</label>
                                <input id="captcha-code" type="text" name="captcha-code" class="formtextfield"/><br/>
                                <input id="signup-button" type="submit" name="signup" value="Signup" class="button"/><br/><br/>
                            </form>
                            <img class="signup-loader" src="img/loading.gif" alt="loading" />
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
                        <div class="recipeoftheday">
                            <h6>What's Cooking</h6>
                            <img src="img/loading.gif" alt="loader"/>
                            <div class="food-feeds"><ul></ul></div>

                        </div>
                        <div class="poweredby">
                            <h6>Powered By</h6>
                            <br/>
                            <ul>
                                <li><a href="http://yummly.com" title="Yummly Recepie Search">Yummly</a></li>
                                <li><a href="http://maps.google.com" title="Google Maps">Google Maps</a></li>
                                <li><a href="https://openshift.redhat.com/app/" title="Openshift Cloud Hosting">OpenShift</a></li>
                                <li><a href="https://developers.google.com/maps/documentation/javascript/geocoding" title="Google Reverse Geocoding">Google Geocoding</a></li>
                            </ul>
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



