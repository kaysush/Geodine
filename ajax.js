/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function()
{
    $(".recover").click(function()
    {
        var hidden_param=$(".hidden_param").val();
        var recovery_email=$(".recovery_email").val();
        $(".recover").attr("value","Recovering...");
        $(".recover").attr("disabled","true")
        var post_data="&type="+hidden_param+"&email="+recovery_email;
        $.ajax({
            type:"POST",
            url:"auth/recover",
            data:post_data,
            success:function(data)
            {
                var str=new String(data);
                str=str.trim();
                if(str === "Instructions for Pasword recovery has been mailed to you.")
                {
                    $(".status").html("<font color=\"green\">"+data+"</font>");
                }
                else
                {
                    $(".status").html("<font color=\"red\">"+data+"</font>");
                }
                $(".recover").attr("value","Recover Password");
                $(".recover").removeAttr("disabled");
                
            }
        });//end of ajax post handler
        return false;
    }); //end of recover button click handler
});//end of document ready handler
    
if (!String.prototype.trim) {
    String.prototype.trim = function() {
        return this.replace(/^\s+|\s+$/g,'');
    }
}

