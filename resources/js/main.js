$(document).ready(function ()
{
        $('#signup').hide();
        $('a#signuppress').click(function (event)
	{
			event.preventDefault();
			$('#login').hide();
			$('#signup').toggle(400);
	});


	$(".fblogin").click(function()
	{
		window.location='https://facebook.com/dialog/oauth/?client_id=326194097435770&redirect_uri=http://projects-sushilkumar.rhcloud.com/geodine/FBLoginAuthentication&scope=publish_stream,user_hometown,email';
	});
	

		$('button.login-window').bind('click',function() 
		{
			//Getting the variable's value from a link 
			$(".status").hide();
			var loginBox = $(this).attr('href');
			$('#signup').hide();
			//Fade in the Popup
			$(loginBox).fadeIn(300);
	                $('#login').fadeIn(300);
                	//Set the center alignment padding + border see css style
			var popMargTop = ($(loginBox).height() + 24) / 2; 
			var popMargLeft = ($(loginBox).width() + 24) / 2; 
		
			$(loginBox).css
			({ 
				'margin-top' : -popMargTop,
				'margin-left' : -popMargLeft
			});
		
			// Add the mask to body
			$('body').append('<div id="mask"></div>');
			$('#mask').fadeIn(300);
		
			return false;
		});
	
			// When clicking on the button close or the mask layer the popup closed
			$('a.close, #mask').live('click', function() 
			{ 
				  $('#mask , .login-popup').fadeOut(300 , function() 
				{
					$('#mask').remove();  
			}); 
	
			return false;
		});


});


function popitup(url) 
{
	newwindow=window.open(url,'name','height=600,width=880');
	if (window.focus) {newwindow.focus()}
	newwindow.onbeforeunload = function() 
	{
		if (newwindow.opener && !newwindow.opener.closed) 
			{
				newwindow.opener.popUpClosed();
	    		}
	};

	return false;
}

function popUpClosed() 
{
    window.location.reload();
}

var image1=new Image();
image1.src="resources/images/slide1.jpg";
var image2=new Image();
image2.src="resources/images/slide2.jpg";
var image3=new Image();
image3.src="resources/images/slide3.jpg";
var image4=new Image();
image4.src="resources/images/slide4.jpg";
var step=1;
function slideit()
{
	if (!document.images)
		return
	console.log(step);
	document.images.slide.src=eval("image"+step+".src")
	if (step<4)
		step++
	else
		step=1
	setTimeout("slideit()",2500)
}

slideit();

