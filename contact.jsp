<%-- 
    Document   : contact.jsp
    Created on : Nov 20, 2012, 5:38:08 PM
    Author     : shiva
--%>

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
        <script type="text/javascript">
            $(document).ready(function(){
                $(".button").click(function(){
                    $(this).hide();
                    var name=$("#name").val();
                    var email=$("#email").val();
                    var msg=$("#body").val();
                    var dataString="&name="+name+"&email="+email+"&msg="+msg;
                    $.ajax({
                        type:"POST",
                        url:"contactus",
                        data:dataString,
                        success:function(html){
                            alert(html);
                        }
                    });
                });
            });
        </script>

        <link href='http://fonts.googleapis.com/css?family=Bubbler+One' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Boogaloo' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Finger+Paint' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=McLaren' rel='stylesheet' type='text/css'>
        <title>Geodine-Contact Us</title>
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



            <div id="content-wrap" style="height:auto;">

                <div id="page-wrap" style="height:auto;">
                    <div class="contact">
                        <h3 style="margin-left:100px;" >Contact Us</h3></br>

                        <label for="name" style="margin-left:-30px;">Name</label></br>
                        <input type="text" id="name" name="name" style="margin-left:20px;""/></br></br>
                        <label for="email" style="margin-left:-30px;">Email</label></br>
                        <input type="text"  id="email" style="margin-left:20px;""/></br></br>
                        <label for="message" style="margin-left:-20px;">Message</label></br>
                        <textarea id="body" style="margin-left:20px;width:250px;height:50px;overflow:auto;" id="body"></textarea></br>
                        <button name="contactus" class="button"value="Send" style="margin-left:-50px;">Send</button></br></br>

                    </div>
                    <div class ="maps">
                        <iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=d&amp;source=s_d&amp;saddr=Jaypee+Institute+of+Information+Technology,+Sector+62,+Noida,+Uttar+Pradesh,+India&amp;daddr=&amp;hl=en&amp;geocode=FYvdtAEdFZucBCHgsZV-7LhmQSnj7xQxUeUMOTHgsZV-7LhmQQ&amp;aq=0&amp;oq=jii&amp;sll=28.628603,77.373104&amp;sspn=0.021358,0.042272&amp;mra=prev&amp;ie=UTF8&amp;t=m&amp;ll=28.628603,77.373104&amp;spn=0.021358,0.042272&amp;output=embed"></iframe><br /><small><a href="https://maps.google.com/maps?f=d&amp;source=embed&amp;saddr=Jaypee+Institute+of+Information+Technology,+Sector+62,+Noida,+Uttar+Pradesh,+India&amp;daddr=&amp;hl=en&amp;geocode=FYvdtAEdFZucBCHgsZV-7LhmQSnj7xQxUeUMOTHgsZV-7LhmQQ&amp;aq=0&amp;oq=jii&amp;sll=28.628603,77.373104&amp;sspn=0.021358,0.042272&amp;mra=prev&amp;ie=UTF8&amp;t=m&amp;ll=28.628603,77.373104&amp;spn=0.021358,0.042272" style="color:#0000FF;text-align:left">View Larger Map</a></small>
                    </div>

                </div>
            </div>
        </div>
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




