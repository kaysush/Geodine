$(document).ready(function()
    {
        var ck_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i ;
        $("img.login-loader").hide();
        $("#signup-status").hide();
        $(".status").hide();
        $(".signup-loader").hide();
        
        $("#login-button").click(function()
        {
            $(".status").hide();
            $("img.login-loader").hide();	
            var email=$(".email").val();
            var pass=$(".password").val();
            if(email==null || email=="")
            {
                $(".status").show();
                $(".status").text("Error!!! Email can't be empty");
            }
            else if(pass==null || pass=="")
            {
                $(".status").show();
                $(".status").text("Error!!! Password can't be empty");
            }
            else if(!ck_email.test(email))
                {
                    $(".status").show();
                $(".status").text("Error!!! Not a valid Email");
                }
                        
            else
            {
                

                $("#login-button").hide();
                $(".login-popup img.login-loader").show();
                    
                var postData="&user-name="+email+"&user-pass="+pass;
                $.ajax({
                    type:"POST",
                    url:"auth/login",
                    data:postData,
                    success:function(data)
                    {
                        console.log(data);
                        var str=new String(data)
                        str=str.trim();
                        if(str === "loggedin")
                        {
                            console.log("Same");
                            location.reload();
                        }
                            
                            
                        else
                        {
                            $(".password").val("");
                            $("#login-button").show();
                            $(".login-popup img.login-loader").hide();
                            $(".status").show();
                            $(".status").text(data);
                        }
                    }
                
                
                });
            }
            return false;
        });
        
        $("#signup-button").click(function(){
            $("#signup-status").hide();
        
            var fname=$("#fname").val();
            var lname=$("#lname").val();
            var email=$("#user-name").val();
            var password1=$("#password1").val();
            var password2=$("#password2").val();
            var captcha=$("#captcha-code").val();
            if(fname==null || fname=="")
            {
                $("#signup-status").show();
                $("#signup-status").css({"color":"black"});
                $("#signup-status").text("Error!!! Fname is Required");
            }
                
            else if(lname==null || lname=="")
            {
                $("#signup-status").show();
                $("#signup-status").css({"color":"black"});
                $("#signup-status").text("Error!!! Lname is Required");
            }
                
            else if(email==null || email=="")
            {
                $("#signup-status").show();
                $("#signup-status").css({"color":"black"});
                $("#signup-status").text("Error!!! Email is Required");
            }
                
            else if(password1==null || password1=="")
            {
                $("#signup-status").show();
                $("#signup-status").css({"color":"black"});
                $("#signup-status").text("Error!!! Password is Required");
            }
                
            else if(password2==null || password2=="")
            {
                $("#signup-status").show();
                $("#signup-status").css({"color":"black"});
                $("#signup-status").text("Error!!! Password is Required");
            }
                
            else if(captcha==null || captcha=="")
            {
                $("#signup-status").show();
                $("#signup-status").css({"color":"black"});
                $("#signup-status").text("Error!!! CAPTCHA is Required");
            }
            else if(password1!=password2)
            {
                $("#signup-status").show();
                $("#signup-status").css({"color":"black"});
                $("#signup-status").text("Error!!! Password doesn't match");
            }
            else if(!ck_email.test(email))
                {
                    $("#signup-status").show();
                    $("#signup-status").css({"color":"black"});
                    $("#signup-status").text("Error!!! Not a valid Email");
                }
            else
            {
                $(".signup-loader").show();
                $("#signup-button").hide();
                $("#password1").val("");
                $("#password2").val("");
                $("#captcha-code").val("");
                var signupData="&first-name="+fname+"&last-name="+lname+"&user-name="+email+"&user-pass="+password1+"&captcha-code="+captcha;
                $.ajax({
                    type:"post",
                    data:signupData,
                    url:"signup",
                    success:function(data){
                        var str=data.trim();
                        if(str == "Congratulations. Signup Complete")
                        {
                            $("#signup-status").show();
                            $("#signup-status").text(data);
                            $(".signup-loader").hide();
                            $("#signup-button").show();
                            $("#signup-status").css({"color":"green"});
                        }
                        else
                        {
                            $("#signup-status").show();
                            $("#signup-status").text(data);
                            $(".signup-loader").hide();
                            $("#signup-button").show();
                            $(".captcha-image").attr("src","auth/captcha");
                        }
                    }
                });
            }
                
                
            return false;
        });
        
        
    });
    
    
    
    
if (!String.prototype.trim) {
    String.prototype.trim = function() {
        return this.replace(/^\s+|\s+$/g,'');
    }
}
