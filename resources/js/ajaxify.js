$(document).ready(function()
    {
        $(".button").click(function()
        {
            $(".status").hide();
		
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
                        
            else
            {
                $(".button").attr("value","loggin in...");
                $(".button").attr("disabled","true");
                    
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
                    console.log("Not same");
                            $(".password").val("");
                            $(".button").attr("value","Login");
                            $(".button").removeAttr("disabled");
                            $(".status").show();
                            $(".status").text(data);
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
