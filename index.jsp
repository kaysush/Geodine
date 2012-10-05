<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <link href='http://fonts.googleapis.com/css?family=Skranji:700' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Cagliostro' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Metamorphous' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Oregano' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Archivo+Black' rel='stylesheet' type='text/css'/>
        <link rel="stylesheet" type="text/css" href="resources/css/style.css" />
        <link href='http://fonts.googleapis.com/css?family=Lato:700,400italic' rel='stylesheet' type='text/css'>
        <script src="resources/js/jquery-1.8.1.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
        <script src="resources/js/main.js" type="text/javascript"></script>
        <script src="resources/js/ajaxify.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Geodine-A new approach for foodies</title>
    </head>

    <body>
        <c:if test="${sessionScope.userName==null}">
            <div class="framecon">
                <div class="header">
                    <div class="headlogo">
                        <div class="headlogotext">Geodine
                        </div>
                    </div>
                    <div class="headlamp"></div>
                    <div class="headlogin">
                        <button href="#login-box" class="login-window orange" class="loginbutton">Login/SignUp</button>
                    </div>
                    <div class="services">
                        <div class="servicestext">
                            <text>Powered By</text>
                            <ul>
                                <li><a href="http://yummly.com" title="Yummly Recepie Search">Yummly</a></li>
                                <li><a href="http://maps.google.com" title="Google Maps">Google Maps</a></li>
                                <li><a href="https://openshift.redhat.com/app/" title="Openshift Cloud Hosting">OpenShift</a></li>
                                <li><a href="https://developers.google.com/maps/documentation/javascript/geocoding" title="Google Reverse Geocoding">Google Geocoding</a></li>
                            </ul>
                        </div>
                    </div>
                    <div id="slideshow">
                        <div id="slideimage">
                            <img src="resources/images/slide1.jpg" name="slide" width="594" height="227"  />
                        </div>
                    </div>

                </div>
                <div class="container">
                    <div class="quote">
                        <div class="quotetext">
                            <h1>Quote of Day</h1>
                            A man can live and be healthy without killing animals for food; therefore, if he eats meat, he participates in taking animal life merely for the sake of his appetite. 
                        </div>
                    </div>


                    <div id="login-box" class="login-popup">
                        <a href="#" class="close"><img src="resources/images/close_pop.png" class="btn_close" title="Close Window" alt="Close" /></a>
                        <div id="login">  
                            <h1>User Login</h1>
                            <form method="POST" class="signin"action="auth/login">
                                <text class="status"></text></br>
                                <label for="user-name">Email</label></br>
                                <input type="text" name="user-name" class="formtextfield email"/></br></br>
                                <label for="user-pass">Password</label></br>
                                <input type="password" name="user-pass" class="formtextfield password"/></br>
                                </br><input type="submit" name="login" value="Login" class="button"/>
                                <a class="forgot" href="recover.jsp">Forgot your password?</a>  
                            </form>
                            <p>or</p>
                            <img class="fblogin" src="resources/images/facebook-login.png"/></br></br></br></br>
                            <a href="#" id="signuppress"> Sign Up for new users</a>
                        </div>       

                        <div id="signup">
                            <h1>Signup for new users</h1>
                            <form method="post" class="signup" action="signup">
                                <label for="first-name">First Name</label></br>
                                <input type="text" name="first-name" class="formtextfield"/></br>
                                <label for="last-name">Last Name</label></br>
                                <input type="text" name="last-name" class="formtextfield"/></br>
                                <label for="user-name">Email</label></br>
                                <input type="text" name="user-name" class="formtextfield"/></br>
                                <label for="user-pass">Password</label></br>
                                <input type="password" name="user-pass" class="formtextfield"/></br> 
                                <label for="user-pass">Confirm Password</label></br>
                                <input type="password" name="user-pass2" class="formtextfield"/></br>
                                <img src="auth/captcha"/></br>
                                <input type="text" name="captcha-code" class="formtextfield"/></br>
                                <input type="submit" name="signup" value="Signup" class="button"/></br></br>
                            </form>
                        </div>
                    </div>

                    <div class="footer"></div>

                </div>

            </div>
        </c:if>
        <c:if test="${sessionScope.userName!=null}">
            <h1>Logged In</h1>
            <c:redirect url="app.jsp"/>  
        </body>
    </c:if>


</html>
