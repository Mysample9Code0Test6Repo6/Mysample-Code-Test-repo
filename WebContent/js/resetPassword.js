// JavaScript Document
// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

// all startup logic should exist in the init function.
function document_onInit()
{
    setDefaultInput();
	
	//wire event handlers
	$("#resetPwd").click(function() { resetPassword(); });

}



function setDefaultInput()
{
	//default checkbox
    $("#oldPwd").focus();
	//$("#communicator").css("visibility", "hidden");
	$("#communicator").css("display", "none");
}

function resetPassword(){
	 if (validated())
	 {
		 $("#loginForm").ajaxSubmit({	
				type: 'POST',
				url: 'resetPassword/submit.cyt',
				success: function(data) {				
						window.location = "home.cyt";																					
				},
				error: function(jqXHR, textStatus, errorThrown) {
					 $("#communicator").css("display", "");
					 setError("oldPwd", "You have entered an invalid password.");
				}
			});
	 }
}

//iterates over form elements making sure input validation is good.
function validated()
{
 // clear out all previous form errors.
 clearAllErrors();
 
 if(!isValidCurrentPassword())
 {
	 //alert("isRequiredFields");
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }


 if(!isValidNewPassword())
 {
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }

 if(!isValidConfirmedPwd())
 {
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }

 return true;
}

function isValidCurrentPassword(){
	 var formGood = true;
	 var pwdFilter = /^([0-9a-zA-Z])+$/;
	 var min = 5;
	 var max = 16;
	 if( $("#oldPwd").val() == "")
	 {
		 $("#communicator").css("display", "");
		 setError("oldPwd", "Enter required fields.");
		 formGood = false;
		 return formGood;
	 }
	 
	 if(!pwdFilter.test( $("#oldPwd").val() ))
	 {
		 $("#communicator").css("display", "");
		 setError("oldPwd", "You have entered an invalid password. use a mixture of numbers(0-9), lowercase & uppercase alphabets");
		 formGood = false;
		 return formGood;
	 }
	 
	 if (!checkLength( $("#oldPwd"), min, max )){
		 $("#communicator").css("display", "");
		 setError("oldPwd", "Length must be between " +min + " and " + max + ".");
		 formGood = false;
		 return formGood;		 
	 }
	 return formGood;
}


function isValidNewPassword(){
	 var formGood = true;
	 var pwdFilter = /^([0-9a-zA-Z])+$/;
	 var min = 5;
	 var max = 16;
	 if( $("#newPwd").val() == "")
	 {
		 $("#communicator").css("display", "");
		 setError("newPwd", "Enter required fields.");
		 formGood = false;
		 return formGood;
	 }
	 
	 if(!pwdFilter.test( $("#oldPwd").val() ))
	 {
		 $("#communicator").css("display", "");
		 setError("newPwd", "You have entered an invalid password. use a mixture of numbers(0-9), lowercase & uppercase alphabets");
		 formGood = false;
		 return formGood;
	 }
	 
	 if (!checkLength( $("#newPwd"), min, max )){
		 $("#communicator").css("display", "");
		 setError("newPwd", "Length must be between " +min + " and " + max + ".");
		 formGood = false;
		 return formGood;		 
	 }
	 
	 return formGood;
}

function isValidConfirmedPwd(){
	 var formGood = true;
	 
	 if ($("#confirm_newPwd").val() != $("#newPwd").val()){
		 $("#communicator").css("display", "");
		 setError("confirm_newPwd", "Please confirm your new password. This must be the same as your new password");
		 formGood = false;
		 return formGood;			 
	 }
	 return formGood;
}


function checkLength( o, min, max ) {
	if ( o.val().length > max || o.val().length < min ) {
		return false;
	} else {
		return true;
	}
}


//sets error status icon visibility, tooltip message, and input error style
function setError(inputField)
{
 $("#errorMsg").addClass("error");
 $("#errorMsg").css("visibility", "visible");


 var field = "#" + inputField;
 $(field).addClass("inputError");

 var errorIcon = "#e_" + inputField;
 $(errorIcon).css("visibility", "visible");
}

function setError(inputField, message)
{
 $("#communicator").addClass("error");
 $("#communicator").css("visibility", "visible");
 
    communicator.setIcon("error");
    communicator.setMessage(message);
    
    var field = "#" + inputField;
    $(field).addClass("inputError");

    var errorIcon = "#e_" + inputField;
    $(errorIcon).css("visibility", "visible");
    $(errorIcon).attr("title", message);
}


function clearAllErrors()
{
 	$("#communicator").removeClass("error");
	$("#communicator").css("visibility", "hidden");

	$('textarea,input:text,input:file,input:password').each(function(){
		 var inputID = $(this).attr("id");
		if(inputID != "")
		 clearError(inputID);
	});
   
}

// opposite of setError
function clearError(inputField)
{
    var field = "#" + inputField;
    $(field).removeClass("inputError");
    
    var errorIcon = "#e_" + inputField;
    $(errorIcon).css("visibility", "hidden");
}
