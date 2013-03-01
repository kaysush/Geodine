<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<c:if test="${sessionScope.userName==null}">
    <c:redirect url="../login.jsp?login=none"/>
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
        <style>
            h2{
                color:black;
                font-size:50px;
            }
            .date
            {
                font-size: 12px;
                position: relative;
                left: 10px;
                top:-20px;
                color:gray;

            }
            .content
            {
                font-family:arial;
                font-size:25px;
            }
            .author
            {
                position: relative;
                top: -20px;
                left: 20px;
                color:gray;
                text-decoration: underline;
            }





        </style>
        <title>Geodine-Blog</title>
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


<div class="headlogin">
                        <button  class="write orange">Write a blog</button>
                    </div>


            </div>



            <div id="content-wrap" style="height:auto;">

                <div id="page-wrap" style="height:auto;">
                    <sql:setDataSource var="datasource" driver="com.mysql.jdbc.Driver"
                                       url="jdbc:mysql://localhost:3306/geodine"
                                       user="root"  password=""/>

                    <sql:query dataSource="${datasource}" var="result">
                        SELECT * from tb_posts;
                    </sql:query>
                    <c:forEach var="row" items="${result.rows}">
                        <div class="post">
                            <h2><c:out value="${row.post_title}"/></h2>
                            <span class="date"><c:out value="${row.post_date}"/></span>
                            <span class="author"><c:out value="${row.post_author}"/></span>
                            <div class="content"><c:out value="${row.post_content}" escapeXml="false" /></div>

                        </div>
                        <hr/>
                    </c:forEach>
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
