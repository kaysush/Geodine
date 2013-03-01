<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.userName==null}">
    <c:redirect url="../login.jsp?login=none"/>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" >
    <head>
        <title>Geodine-Post</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link href='http://fonts.googleapis.com/css?family=Lato:700,400italic' rel='stylesheet' type='text/css'/>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
        <link rel="icon" href="favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" href="../jwysiwyg/jquery.wysiwyg.css" type="text/css" />

        <script type="text/javascript" src="../jquery/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="../jwysiwyg/jquery.wysiwyg.js"></script>
        <script src="js/slides/source/slides.min.jquery.js"></script>
        <script src="js/ajaxify.js" type="text/javascript"></script>
        <script src="js/jquery.backstretch.min.js" type="text/javascript"></script>
        <script src="js/jQueryRotate.2.2.js"></script>
        <script type="text/javascript" src="js/custom.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Bubbler+One' rel='stylesheet' type='text/css'>
            <link href='http://fonts.googleapis.com/css?family=Boogaloo' rel='stylesheet' type='text/css'>
                <link href='http://fonts.googleapis.com/css?family=Finger+Paint' rel='stylesheet' type='text/css'>
                    <link href='http://fonts.googleapis.com/css?family=McLaren' rel='stylesheet' type='text/css'>

                        <script type="text/javascript">
                            $(function()
                            {
                                $('#wysiwyg').wysiwyg();
                            });
                            $(document).ready(function()
                            {
                                $("button").click(function()
                                {
                                    var val=$("#wysiwyg").val();
                                    var title=$("#title").val();
                                    var dataString="&title="+title+"&body="+val;
                                    //			alert(dataString);
                                    $.ajax({
                                        type:"POST",
                                        url:"/geodine/blog/posthandler",
                                        data:dataString,
                                        success:function()
                                        {
                                            alert('Posted');
                                            window.location="/geodine/blog";
                                        }
				
                                    });
                                });
                            });
                        </script>
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
                                                <span id="blog">Blog</span>
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
                                        <h3 style="margin-left:350px;font-family: Finger Paint;font-size: 40px;">Post your blog</h3><br/><br/>
                                        <div class="blog" style="height: 450px; position: absolute;
                                             overflow: auto;
                                             top: 200px;
                                             left :50px;
                                             width: 875px;

                                             margin-left: 20px;
                                             box-shadow: 0px 0px 20px #999; /* CSS3 */
                                             -moz-box-shadow: 0px 0px 20px #999; /* Firefox */
                                             -webkit-box-shadow: 0px 0px 20px #999; /* Safari, Chrome */
                                             border-radius:3px 3px 3px 3px;
                                             -moz-border-radius: 3px; /* Firefox */
                                             -webkit-border-radius: 3px; /* Safari, Chrome */
                                             padding-top: 10px;
                                             background: #ffffff; /* Old browsers */
                                             background: -moz-linear-gradient(top,  #ffffff 0%, #f6f6f6 47%, #ededed 100%); /* FF3.6+ */
                                             background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(47%,#f6f6f6), color-stop(100%,#ededed)); /* Chrome,Safari4+ */
                                             background: -webkit-linear-gradient(top,  #ffffff 0%,#f6f6f6 47%,#ededed 100%); /* Chrome10+,Safari5.1+ */
                                             background: -o-linear-gradient(top,  #ffffff 0%,#f6f6f6 47%,#ededed 100%); /* Opera 11.10+ */
                                             background: -ms-linear-gradient(top,  #ffffff 0%,#f6f6f6 47%,#ededed 100%); /* IE10+ */
                                             background: linear-gradient(to bottom,  #ffffff 0%,#f6f6f6 47%,#ededed 100%); /* W3C */
                                             filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#ededed',GradientType=0 ); /* IE6-9 */
                                             ">

                                            <br/>
                                            <label for="title" style="cursor: default;
                                                   font-family:Boogaloo;
                                                   font-size:20px;
                                                   color:  #000000;
                                                   float:left;
                                                   text-align:right;
                                                   display:block;
                                                   width:95px;">Title :</label>
                                            <input type="text" name="title" id="title"/><br/><br/>
                                            <label for="title" style="cursor: default;
                                                   font-family:Boogaloo;
                                                   font-size:20px;
                                                   color:  #000000;
                                                   float:left;
                                                   text-align:right;
                                                   display:block;
                                                   width:95px;">Your Blog:</label>
                                            <div style="margin-left:100px;">
                                                <textarea  name="wysiwyg" id="wysiwyg" rows="5" cols="47" style="width:697px;height:250px;" ></textarea>
                                                <br/><button style="margin-left:625px;font-family: Boogaloo;font-size:25px;">Submit</button>
                                            </div>
                                        </div>
                                        <div id="show"></div>


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
