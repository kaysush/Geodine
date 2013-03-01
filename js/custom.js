var index=1;
$(document).ready(function($) {

    
    loadFeeds();
    loadFacts();
    loadBlogFeeds();
    loadRandomVideo();
    
    setInterval(function(){
        console.log("Loading Facts....");
        $(".box-content").html("<div class=\"box-content\"><img src=\"img/loading.gif\" alt=\"loader\"/>");
        loadFacts();
    },60000);
    
    setInterval(function(){
        $('.recipeoftheday ul').empty();
        $(".recipeoftheday img").show();
        loadFeeds();
    },300000);
    
    setInterval(function(){
        $(".blog-feed").html("<img src=\"img/loading.gif\" alt=\"loader\"/>");
        loadBlogFeeds();
    },60000);
    
    


    setInterval(function(){
        var name=index+"food.jpg";
        var value="url('img/bg/"+name+"')";
        $("body").css({
            "background":value
        });
        index++;
        if(index>3)
        {
            index=1;
        }
    },5000);

    $("#next").click(function()
    {

        if(index<3)
        {
            index++;
        }
        else
        {
            index=1;
        }
        var name=index+"food.jpg";
        var value="url('img/bg/"+name+"')";
        $("body").css({
            "background":value
        });

    });

    $("#back").click(function()
    {
        if(index>1)
        {
            index--;
        }
        else
        {
            index=3;
        }
        var name=index+"food.jpg";
        var value="url('img/bg/"+name+"')";
        $("body").css({
            "background":value
        });
    });



  
    $(".navbar span").hide();
    var x=document.URL ;
    if(x=="http://localhost:8084/geodine/")
        $('#home').fadeIn();
    else if(x=="http://localhost:8084/geodine/contact.jsp")
        $('#contact').fadeIn();
    else if(x=="http://localhost:8084/geodine/app/")
        $('#app').fadeIn();
    else if(x=="http://localhost:8084/geodine/blog/")
        $('#blog').fadeIn();
    $(".navbar ul li").mouseover(function()
    {
        $(this).children("span").fadeIn(200);
    });
    $(".navbar ul li").mouseout(function()
    {$(this).children("span").fadeOut(100);
        var x=document.URL ;
        if(x=="http://localhost:8084/geodine/")
            $('#home').fadeIn();
        else if(x=="http://localhost:8084/geodine/contact.jsp")
            $('#contact').fadeIn();
        else if(x=="http://localhost:8084/geodine/app/")
            $('#app').fadeIn();
        else if(x=="http://localhost:8084/geodine/blog/")
            $('#blog').fadeIn();
        
    });




    $(".social ul li").rotate({ 
        bind: 
        { 
            mouseover : function() { 
                var a=$(this).children("a");
                a.children("img").rotate({
                    animateTo:360
                })
                a.children("span").stop(true,true).animate({
                    "color":"blue",
                    "font-size":"20px"
                },500,function(){});
            },
            mouseout : function() {
                var a=$(this).children("a");
                a.children("img").rotate({
                    animateTo:0
                })
                a.children("span").stop(true,true).animate({
                    "color":"black",
                    "font-size":"12px"
                },500,function(){});
            }
        } 
    });
  
    $('#signup').hide();
    $('a#signuppress').click(function (event)
    {
        event.preventDefault();
        $("#password1").val("");
        $("#password2").val("");
        $("#captcha-code").val("");
        $("#signup-status").hide();
        $('#login').hide();
        $('#signup').toggle(400);
    });


    $(".fblogin").click(function()
    {
        window.location='https://facebook.com/dialog/oauth/?client_id=326194097435770&redirect_uri=http://projects-sushilkumar.rhcloud.com/geodine/FBLoginAuthentication&scope=publish_stream,user_hometown,email';
    });
	

    $('button.login-window').bind('click',function() 
    {

        $(".status").hide();
        var loginBox=$("#login-box");
        $('#signup').hide();
        //Fade in the Popup
        $(loginBox).fadeIn(300);
        $('#login').fadeIn(300);

        var popMargTop = ($(loginBox).height() + 24) / 2; 
        var popMargLeft = ($(loginBox).width() + 24) / 2; 
        console.log(popMargTop+" "+popMargLeft);
		
        $(loginBox).css ({ 
            'margin-top' : -popMargTop,
            'margin-left' : -popMargLeft
        });
		

        $('body').append('<div id="mask"></div>');
        $('#mask').fadeIn(300);
		
        return false;
    });
	
 
    $('a.close, #mask').live('click', function() 
    { 
        $('#mask , .login-popup').fadeOut(300 , function() 
        {
            $('#mask').remove();
        }); 
	
        return false;
    });
	
	
    $(document).mousemove(function(e){
        if((e.pageY) < 200){
            $("#headernav").fadeIn();
        }else{
            $("#headernav").fadeOut();
        }
    }); 
	
	
	
    $('#front-slides').slides({
        preload: true,
        generateNextPrev: false,
        slideSpeed: 500,
        play: 20000,
        autoHeight:true,
        animationStart: function(current){
            $slideCaption = $(".slides_container div.slide:eq("+ (current-1) +") .caption").text();
            $("#headline h6").text($slideCaption);
			
            if($slideCaption !== ''){
                $("#headline").stop().hide().slideDown(600);
            }else{
                $("#headline").hide();
            }
        }
    });
	
        
    $firstCaption = $(".slides_container div.slide:eq(0) .caption").text();
    if($firstCaption !==''){
        $("#headline h6").text($firstCaption);
    }else{
        $("#headline").hide();
    }
});

function loadFeeds()
{
    $.getJSON('util/WhatsCooking',function(data)
    {
        $.each(data['links'],function(i,row){
            $('.food-feeds ul').append('<li><a target="_blank" href='+row.link+'>'+row.title+'</a></li>');
            $(".recipeoftheday img").hide();
        });
			
    });
}

function loadFacts()
{
    $.get("FeedHandler",function(data)
    {
        $(".box-content").html(data);
    });
}

function loadBlogFeeds()
{
    $(".blog-feed").load("TurboFeeds",function(data){
           
        });
}

function loadRandomVideo()
{
    var iframe=$("#ytplayer");
    $.ajax({
        type:"POST",
        url:"youtube",
        data:"&type=random",
        success:function(data)
        {
            iframe.attr("src","https://www.youtube.com/embed/"+data)
        }
    });
}



