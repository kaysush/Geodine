var index=1;
var marker,map;
function initialize() {
    var mapOptions = {
        center: new google.maps.LatLng(20.530892091775808, 77.8705625),
        zoom: 3,
        apanControl: false,
        zoomControl: false,
        mapTypeControl: false,
        scaleControl: false,
        streetViewControl: false,
        overviewMapControl: false,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map_canvas"),
        mapOptions);
    google.maps.event.addListener(map, 'click', function(event) {
        placeMarker(event.latLng);
        $('#reel').show(300);
        $("#country").html("<img class='slides_loader' src='../img/loading.gif'/>");
        var latlng="latlng="+event.latLng.lat()+","+event.latLng.lng();
        $.ajax(
        {
            type: "POST",
            url: "/geodine/GetCountry",
            data: latlng,
            success: function(result) {
                
                $("#country").html(result);
                
                
            }
        });
    });

    google.maps.event.addListener(map, 'zoom_changed', function(event) {

        map.setZoom(3);
    });
}
function placeMarker(location) {
    if ( marker ) {
        marker.setPosition(location);
    } else {
        marker = new google.maps.Marker({
            position: location,
            map: map
        });
    }
}
$(document).ready(function($) {
    $('#reel').hide();
$(".message").hide();
	$(".yt").hide();
	loadRandomVideo();
    loadRecent();
loadFavourites();
    setInterval(function(){
        var name=index+"food.jpg";
        var value="url('../img/bg/"+name+"')";
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
        var value="url('../img/bg/"+name+"')";
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
        var value="url('../img/bg/"+name+"')";
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
    else if(x=="http://localhost:8084/geodine/app/"||x=="http://localhost:8084/geodine/app/index.jsp")
        $('#app').fadeIn();
    else if(x=="http://localhost:8084/geodine/blog/")
        $('#blog').fadeIn();
    $(".navbar ul li").mouseover(function()
    {
        $(this).children("span").fadeIn(200);
    });
    $(".navbar ul li").mouseout(function()
    {   $(this).children("span").fadeOut(100);
        var x=document.URL ;
    
    if(x=="http://localhost:8084/geodine/")
        $('#home').fadeIn();
    else if(x=="http://localhost:8084/geodine/contact.jsp")
        $('#contact').fadeIn();
    else if(x=="http://localhost:8084/geodine/app/"||x=="http://localhost:8084/geodine/app/index.jsp")
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
        slideSpeed: 0,
        play: 0,
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
    $('#country').on('click', 'a', function (event){
        event.preventDefault();
        $(".data").html("<img class='data_loader' src='../img/loading.gif'/>");
        var cuisine=$(event.target).text();
        var dataString="cuisine="+cuisine;
        $.ajax(
        {
            type: "GET",
            url: "/geodine/util/yummly",
            data:dataString,
            success:function(xml)
            {
                $(".data").empty();
                var id=$(xml).find('id').text();
                var cuisinesId=$(xml).find("cuisine-id").text();
                var fav=$(xml).find("favourite").text().trim();
                var ytid=$(xml).find("ytid").text();
                var iframe=$("#ytr");
                iframe.attr("src","https://www.youtube.com/embed/"+ytid)
                $(".data").append("<h4>Recpie of "+cuisine+"</h4>");
                $(".data").append("<img class='fav' src='../img/gold-star.png' height='32' width='32'/>");
                $(".data").append("<button type='button'  value='"+id+"' class='css3button'>Add to Favourite</button>");
                $(".data button").hide();
                $(".data .fav").hide();
                if(fav=="true")
                {
                    
                    $(".data .fav").show();
                    $(".data button").hide();

                }
                else
                {
                    
                    $(".data .fav").hide();
                    $(".data button").show();
                }
  $(".data").append("<h6>Ingredients</h6>");
                $(".data").append("<ul>");
                $(xml).find('ingredient').each(function(){
                    $(".data").append("<li>"+$(this).text()+"</li>");
                });
                $(".data").append("</ul>");
                $(".data").append("<br/>");


                $(".data").append("<h6>Preparation</h6>");
                $(".data").append("<ul>");
                $(xml).find('step').each(function(){
                    $(".data").append("<li>"+$(this).text()+"</li>");
                });
                $(".data").append("</ul>");
                loadRecent();
            }
        });
    });
    
    $('.data').on('click', 'button', function (event){
$(".data button").hide();
var id=$(this).attr("value");
var dataString="id="+id;
       $.ajax(
	{
		type:"GET",
		url:"/geodine/util/favourites",
		data:dataString,
		success:function()
		{

                        $(".data .fav").show();
			loadFavourites()
		}
	});
    });

$(".swap").mouseover(function()
{
$(".message").toggle();
});
$(".swap").mouseout(function()
{
$(".message").toggle();
});

$(".swap").click(function()
{
	$(".data").slideToggle();
	$(".yt").slideToggle();
});

});

function loadRecent()
{
    $(".poweredby").html("<img src='../img/loading.gif'/>");
    $.ajax(
    {
        type: "GET",
        url: "/geodine/util/recent",
        success:function(html)
        {
            $(".poweredby").html(html);
        }
    });
}

function loadFavourites()
{
    $(".food-feeds").html("<img src='../img/loading.gif'/>");
    $.ajax(
    {
        type: "GET",
        url: "/geodine/util/fetchfavourite",
        success:function(html)
        {
            $(".food-feeds").html(html);
        }
    });
}

function loadRandomVideo()
{
    var iframe=$("#ytplayer");
    $.ajax({
        type:"POST",
        url:"/geodine/youtube",
        data:"&type=random",
        success:function(data)
        {
            iframe.attr("src","https://www.youtube.com/embed/"+data)
        }
    });
}


if (!String.prototype.trim) {
    String.prototype.trim = function() {
        return this.replace(/^\s+|\s+$/g,'');
    }
}




