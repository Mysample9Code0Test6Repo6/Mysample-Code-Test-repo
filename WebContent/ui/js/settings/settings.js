// JavaScript Document
// jquery initializer, do not alter.
function toggleButton(linkname,submitUrl) {
	
	if($("#save" + linkname + "link").hasClass("button_green")) {
	    $("#editSettingsForm").ajaxSubmit({
	        type:"POST",
	        url:submitUrl,
	        dataType:"text",
	        success:function (data) {
	        	if (data.search("success") == 0) {
            		//alert("in ajax success::"+data);
	        		 $("#savepagenamelink").css("display", "none");
            		$("#sucess" + linkname).show();
                    $("#invalid" + linkname).css("display", "none");
                   $("#" + linkname).css("background", "#cfff00");
                    $("#" + linkname).css("color", "#1b4d5f");
                    $("#valid" + linkname).css("display", "");
                    $("#valid" + linkname).css('color', "#3a7d34");
                   $("#valid" + linkname).text("Success");
	    		$("#save" + linkname + "link").removeClass("button_green").addClass("edit").css("width","").css("float","").css("margin-bottom","");
	    		$("#save" + linkname + "link" + " span").removeClass("title_green").text('Edit');
	    		$("#" + linkname).attr("readOnly","true").addClass("readonly_input");
	        	}else if (data.search("error") == 0) {
                	 $("#sucess" + linkname).hide();
                    $("#valid" + linkname).css("display", "none");
                    $("#" + linkname).css("color", "#ffffff");
                    $("#" + linkname).css("background", "#c2002d");
                    $("#invalid" + linkname).css("display", "");
                    $("#invalid" + linkname).css('color', "#ec3f41");
                    $("#invalid" + linkname).text(data);
                    
                }
	        }
	    });
	}
	else {
		if(linkname == 'pagename') {
			$("#save" + linkname + "link").attr("onFocus","this.placeholder = ''").attr("onBlur","this.placeholder = 'Type your name here...'").attr("placeholder","Type your name here... ").attr("title","Save").removeClass("edit").addClass("button_green").css("width","60px").css("float","right").css("margin-bottom","8px");
		}
		else {
			$("#save" + linkname + "link").attr("href","#").attr("title","Save").removeClass("edit").addClass("button_green").css("width","60px").css("float","right").css("margin-bottom","8px");
		}
		$("#" + linkname).removeAttr("readOnly").removeClass("readonly_input");
		$("#save" + linkname + "link" + " span").addClass("title_green edit_profile_save_bot_ie8").text("SAVE");
	}
}

$(document).ready(function () {
	$("#ok").click(function(){
		var submitUrl = $('#saveSetting').val();
		submitUrl=submitUrl+'?tempVar=' + Math.random();
	    $("#editSettingsForm").ajaxSubmit({
	        type:"POST",
	        url:submitUrl+ '?tempVar=' + Math.random(),
	        dataType:"text",
	        success:function (data) {
	        	$.fn.colorbox.close();
	        }
	    });	
	});
	
    // validate signup form on keyup and submit
    var st_validator = $("#editSettingsForm").validate({
        rules:{
            password:{
                required:true,
                minlength:7,
                maxlength:20
            },
            newpass:{
                required:true,
                minlength:7,
                maxlength:20
            },
            newPassword:{
            	 required:true,
                 minlength:7,
                 maxlength:20
            }
        },
        messages:{ 
        	password:{
        	  required:"Please enter a current password",
              minlength:"Password must be at least 7 characters",
              maxlength:"Password must range between 7 and 20"
        },
        	newpass:{
                required:"Please enter a new password",
                minlength:"New password must be at least 7 characters",
                maxlength:"New password must range between 7 and 20"
            },
            newPassword:{
                required:"Please confirm new password",
                minlength:"Password must be at least 7 characters",
                maxlength:"Password must range between 7 and 20"
            }
                    
        }
    });
});


function isSpclCharuniquePageName(pagename){
	var pagename=$("#pagename").val();
	var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
	var isValid=true;
	if(pagename != ""){
		for (var i = 0; i < pagename.length; i++) 
	    {
		   if (iChars.indexOf(pagename.charAt(i))!='-1') {
		    	isValid=false;
		    	break;
		    	return isValid;
		     }else{
		    	 isValid=true;
		     }
	    }
	}else{
		isValid=false;
	}
	return isValid;
}
function uniquePageValidate(){
	var pagename=$("#pagename").val();
	if(!isSpclCharuniquePageName(pagename)){
	   	$("#uniquepagename_error").text("Special characters are not allowed");
	   	$("#uniquepagename_error").css("display","");
	}	
	else{
		if(pagename.indexOf(" ") != -1){
		   	$("#uniquepagename_error").text("Character space is not allowed");
		   	$("#uniquepagename_error").css("display","");
		}
		else{
			$("#uniquepagename_error").css("display","none");	
		}
	}
}

function uniquePage(){
	$("#validpagename").css("display","none");
	$("#pagename").css("background-color","");
	var pagename=$("#pagename").val();
	parent.$.fn.colorbox({'href':'div.edit_Uniquepagename_pop_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
}

function SaveUniquePageName(){
	var pagename=$("#pagename").val();
	if(!isSpclCharuniquePageName(pagename)){
	   	$("#uniquepagename_error").text("Special characters are not allowed");
	   	$("#uniquepagename_error").css("display","");
	}
	else{
		if(pagename.indexOf(" ") != -1){
		   	$("#uniquepagename_error").text("Character space is not allowed");
		   	$("#uniquepagename_error").css("display","");
		}
		else{
			$("#uniquepagename_error").css("display","none");	
			toggleButton('pagename','savepagename.cyt');
		}
	}
}

function togglePwd(linkname,submitUrl) {
	var urlValue = getContextPath() + "/" + submitUrl + '?tempVar=' + Math.random();
	if($("#save" + linkname + "link").hasClass("button_green")) {
		 //password match
		 if($("#newpass").val() != $("#newpassword").val()){
			 cleanConfirmPwd();
			 displayErrorMsg("newpassword", "newpassword",'Passwords do not match');
			 return false;
		 }
        $("#editSettingsForm").ajaxSubmit({
            type:"POST",
            url:urlValue,
            dataType:"text",
           success:function (data) {
              	if(data == "Updated successfully") {
                    $("#invalid" + "newpassword").css("display", "none");
                    $("#valid" + "newpassword").css("display", "");
                    $("#valid" + "newpassword").css('color', "#3a7d34");
                    $("#valid" + "newpassword").text(data);
                    $("#save" + linkname + "link").removeClass("button_green").removeClass("edit_profile_save_bot_ie8").addClass("edit").css("width","").css("float","").css("margin-bottom","");
            		$("#save" + linkname + "link" + " span").removeClass("title_green").text('Edit');
            		$("#" + linkname).attr("readOnly","true").addClass("readonly_input").removeClass("edit_setting_input_ie8_enable").addClass("edit_setting_input_ie8");
            		$("#newpass").attr("readOnly","true").addClass("readonly_input");
            		$("#newpassword").attr("readOnly","true").addClass("readonly_input");
                
            	} else if (data.search("error") == 0) {
                    $("#valid" + "newpassword").css("display", "none");
                    $("#invalid" + "newpassword").css("display", "");
                    $("#invalid" + "newpassword").text(data);
                }
            }
        }); 
 	} else {
		$("#save" + linkname + "link").attr("href","#").attr("title","Save").removeClass("edit").addClass("button_green").css("width","60px").css("float","right").css("margin-bottom","8px");
		$("#newpassword").removeAttr("readOnly").removeClass("readonly_input");
		$("#newpass").removeAttr("readOnly").removeClass("readonly_input").removeClass("edit_setting_input_ie8").addClass("edit_setting_input_ie8_enable");
		$("#" + linkname).removeAttr("readOnly").removeClass("readonly_input");
		$("#save" + linkname + "link" + " span").addClass("title_green edit_profile_save_bot_ie8").text("SAVE");
	}
}

function saveoptions(submitUrl){
	$('#saveSetting').val(submitUrl);
}

function saveSettingsOptions(submitUrl, submitValue){
    $('#saveSetting').val(submitUrl);
    if(submitUrl == "savedisablepoll.cyt") {
        saveDisablePollComments(submitValue);
    }
    if(submitUrl == "savenotifypolls.cyt"){
        saveNotifyPolls(submitValue);
    }
}

function saveDisablePollComments(submitValue){
	if (submitValue == 'Y') {
        $('#dynamicText').text("Once disabled you will no longer be able to view any comments on any polls");
    } else {
        $('#dynamicText').text("Once enabled you will be able to view any comments on any polls");
    }
	parent.$.fn.colorbox({'href':'div.edit_pro_pop_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
	
}
function saveNotifyPolls(submitValue){
	if (submitValue == 'Y') {
        $('#dynamicText').text("Once enabled you will receive email alerts");
    } else {
        $('#dynamicText').text("Once set you will no longer receive email alerts");
    }
	parent.$.fn.colorbox({'href':'div.edit_pro_pop_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
}

function deactivateAccount(submitUrl) {
	  var r = confirm("Are you sure you want to Deactivate Your Account : ");
	    if (r != true) {
	        return false;
	    }
	    
    parent.$.fn.colorbox({'href':'div.deactive_pop_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
    $('#cboxClose').remove();
    $("#closepop").click(function(){
        $.fn.colorbox.close();
    });
    
  
	submitUrl=submitUrl+'?tempVar=' + Math.random();
    $("#editSettingsForm").ajaxSubmit({
        type:"POST",
        url:submitUrl+ '?tempVar=' + Math.random(),
        dataType:"text",
        success:function (data) {
        }
    });
}

function displayErrorMsg(fieldName, UID_Name, msg){
	$("#invalid"+UID_Name).css("display", "");
	$("#invalid"+UID_Name).text(msg);	
}

function displayValidMsg(fieldName, UID_Name, msg){
	//$("#"+UID_Name+"Loader").css("display", "none");
	$("#valid"+UID_Name).css("display", "");
	$("#valid"+UID_Name).css("padding-bottom", "24px");
	$("#valid"+UID_Name).css('color',"#3a7d34");
	$("#valid"+UID_Name).text(msg);	
}

function cleanConfirmPwd(){
	$("#invalidnewpassword").css("display", "none");	
	$("#validnewpassword").css("display", "none");	
}
var PwdFlag = false;