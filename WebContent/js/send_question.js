// JavaScript Document
// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

// all startup logic should exist in the init function.
function document_onInit()
{
    setDefaultInput();
    
    var usn;
	var sumail;
	var pass;
	
	$("#pageName").blur(function() { validatePageName(); });
	
	 $('#uploadButton').click(function() { uploadFileFunc(); });
	
	//wire event handlers
	$("#send").click(function() { sendInCyyte(); });
	
	$("#back").click(function() { history.back(); return false; });
	
	$("#postScript").click(function() { postInCyyte(); });

	$('#addEmail').click( function() {newRow();});
	$('#delete').click( function() {deleteRow();});

	$('#mail').change( function() {toggleMail();});
	$('#post').change( function() {toggleMail();});
	$('#area').change( function() {toggleMail();});

	$('#locality').change( function() {toggleLocalityFields();});
	
	$('#postcodex').focus(function() {  
        if($(this).val() == 'RM16 6RL') $(this).val('');  
	}).blur(function() {  
    	if( $(this).val() == '') $(this).val('RM16 6RL');  
	});
	
	$('#Neighbourhood').focus(function() {  
        if($(this).val() == 'Enter your Neighbourhood') $(this).val('');  
	}).blur(function() {  
    	if( $(this).val() == '') $(this).val('Enter your Neighbourhood');  
	});
	
	//default groupName
	$('#grpName').focus(function() {  
         if($(this).val() == 'Enter a name for your group') $(this).val('');  
	}).blur(function() {  
     	if( $(this).val() == '') $(this).val('Enter a name for your group');  
	}); 

	//default groupType
	$('#grpType').focus(function() {  
		//alert($(this).val());
         if($(this).val() == 'Enter or select from list') $(this).val('');  
	}).blur(function() {  
     	if( $(this).val() == '') $(this).val('Enter or select from list');  
	}); 
	
	//COUNTRY
	$.fn.clearField = function() {
    return this.focus(function() {
        if( this.value == this.defaultValue ) {
            this.value = "";
        }
		}).blur(function() {
			if( !this.value.length ) {
				this.value = this.defaultValue;
			}
		});
	};	

	//Change this to the ID of the country input you want to be autocompleted
	//make sure to update the CSS for this ID as well
	var ac_country = "#ac_country";
	var ac_country1 = "#ac_country1";
	
	setDefaultCountry("GB",ac_country);
	setDefaultCountry("GB",ac_country1);
	//$('#ac_country').change( function() {togglePostcode()});
	//loadTestData();
}

function validatePageName(){
	var pageName_val;    
	pageName_length = $("#pageName").val().length;    
	pageName_val = $("#pageName").val();
	$("#pageName_warning").empty();          
	$.ajax(
		{
			url: "validatePageName.cyt",
			cache: false, 
			data: "name="+pageName_val,
			success: function(html){ 				
				$("#pageName_warning").append(html);  
			}
		}
	);
	
}

function loadTestData(){
	$("#grpName").val("Group Name");
	$("#grpType").val("Group Type");

	$("#email1").val("test@test.com");
	$("#email2").val("test@test2.com");
	$("#email3").val("test@test3.com");
	
	$("#username").val("Timi");
	$("#su_email").val("tboboye@hotmail.com");
	$("#confirm_email").val("tboboye@hotmail.com");
	$("#su_password").val("tboboye");
	
}

function setDefaultInput()
{
	//default checkbox
    $("#email1").focus();
	$("#communicator").css("visibility", "hidden");
}

function setDefaultCountry(code, ac_country)
{
	//options are the same as the JQuery Autocomplete plugin
	$(ac_country).autocomplete(countries, {
		minChars: 2,
		width: 320,
		matchContains: true,
		scroll: true,
		max:0,
		formatItem: function(row, i, max, term) {
			return "<img src='css/accountry.0.2/images/flags/" + row.code.toLowerCase() + ".gif'/> " + row.name;
		},
		formatResult: function(row) {
			return row.name;
		},
		formatMatch: function(row, i, max) {
			return row.name;
		}
	});

	$(ac_country).after($(ac_country).clone().attr('value','').attr('id', $(ac_country).attr('id') + '_hidden'));
	$(ac_country).removeAttr('name', '').clearField();
	$(ac_country).result(function(event, data, formatted) {
		if(data) {
			$(ac_country + '_hidden').val(data.code.toLowerCase());
			togglePostcode(data.code.toLowerCase());
		}
		var src = 'css/accountry.0.2/images/flags/' + data.code.toLowerCase() + '.gif';
		$(ac_country).css('backgroundImage', 'url(' + src + ')');
	});
	
		//var ac_country = "#ac_country";
		var src = 'css/accountry.0.2/images/flags/' + code.toLowerCase() + '.gif';
		$(ac_country).css('backgroundImage', 'url(' + src + ')');
		$(ac_country).val('United Kingdom');
		$(ac_country+"_hidden").val(code.toLowerCase());
		
		$("#postal_area_lb").css("display", "none");
		$("#postal_area").css("display", "none");
					
}

function toggleMail(){
	 clearAllErrors();

	if($('#mail').attr('checked')===true) {  
		$("#mailWrap").css("display", ""); 
		$("#postWrap").css("display", "none"); 
		$("#areaWrap").css("display", "none"); 
	}else if($('#post').attr('checked')===true) {  
		$("#postWrap").css("display", ""); 
		$("#mailWrap").css("display", "none"); 
		$("#areaWrap").css("display", "none"); 
	}else if($('#area').attr('checked')===true) {  
		$("#areaWrap").css("display", ""); 
		$("#mailWrap").css("display", "none"); 
		$("#postWrap").css("display", "none"); 
	}
}


function toggleLocalityFields(){
	var local = $('#locality option:selected').val();

	if (local == "Postcode"){
		$("#postcodeSec").css("display", ""); 
		$('#postcodex').hide().fadeIn(2000);
		$("#neighbourhoodSec").css("display", "none"); 
		$("#countySec").css("display", "none"); 
	}else if (local == "Region"){
		$("#neighbourhoodSec").css("display", ""); 
		$('#Neighbourhood').hide().fadeIn(2000);
		$("#postcodeSec").css("display", "none"); 
		$("#countySec").css("display", "none"); 
	}else if (local == "County"){
		$("#countySec").css("display", ""); 
		$('#county').hide().fadeIn(2000);
		$("#postcodeSec").css("display", "none"); 
		$("#neighbourhoodSec").css("display", "none"); 
	}
	
}
function togglePostcode(code){
	
		if (code == "gb"){			
			$("#postcode_lb").css("display", "");
			$("#postcode").css("display", "");	
			
			$("#postal_area_lb").css("display", "none");
			$("#postal_area").css("display", "none");
		}else{
			$("#postal_area_lb").css("display", "");
			$("#postal_area").css("display", "");	
			
			$("#postcode_lb").css("display", "none");
			$("#postcode").css("display", "none");	
		}

}


var row = 4;
function newRow(){
	var countMax = 0;
	 $("input:text").each(function(index){    
        // if input doesn't have an id, skip the whole process there is no need to count it
        var inputID = $(this).attr("id");
        if(inputID.indexOf("email") != -1)
        {   
            countMax++;
        }           
    }); // each function
	
	
	if (countMax<25){
		$("#responseOpts tbody" ).append( "<tr> <td class=\"style9\"><input name=\"email"+row+"\" type=\"text\" id=\"email"+row+"\" size=\"50\" class=\"inputwidth\" title=\"response\"/>&nbsp;&nbsp;&nbsp;<img src=\"images/icons/delete-icon2.png\" alt=\"\" width=\"12\" height=\"12\" id=\"delete\" title=\"remove this option\"/> </td> </tr>" );
		row++;
	}
	
}

function deleteRow(){
	$("#delete").live('click', function(event) {         
			$(this).parent().parent().remove(); 
	});
}

function setEmails(){
	var emails="";
	$("input:text").each(function(index){    
        // if input doesn't have an id, skip the whole process theres no need to count it
        var inputID = $(this).attr("id");
        if(inputID.indexOf("email") != -1)
        {   
        	emails +=  $("#"+inputID).val() + "&&";
        }           
    }); // each function
	$("#responses").val(emails);
	//alert("responses: "+ emails);
}

function uploadFileFunc(){
	
	$("#loadingDiv").ajaxStart(function(){
	    $(this).show();
	 }).ajaxStop(function(){
	    $(this).hide();
	 });
	
	$("#postInCyyteForm").ajaxSubmit({	
		type:'POST',
		url: 'send_question/uploadLogoFile.cyt',		
		success: function(data) {	
			if(data.indexOf("success") != -1){
				//set progress to 100%
				clearError("uploadedLogo");				
				displayLoad(data);	
			}
			else{				
				displayNotLoad();
				setError("uploadedLogo");
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			displayNotLoad();
			setError("uploadedLogo");
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});	
	

 }

function displayLoad(data){
	$("#notLoaded").hide();
	data = data.replace("<pre>", "");
	data = data.replace("</pre>", "");
	data = data.replace("success", "");
	$("#loadText").html("<b>"+data+"</b>");
	$("#loaded").show();
}

function displayNotLoad(){
	$("#loaded").hide();
	$("#notLoaded").show();
}

function sendInCyyte(){
 if (validated())
 {	 
	 setValidateTips($( ".validateTips_df" ));	
	 
	 setEmails();	 
	
	 $("#sendInCyyteForm").ajaxSubmit({
		 type: 'POST',
		 url: 'send_question/submit.cyt',
		 success: function(data) {
			 if(data == 'send_question'){
				 $( "#login-dialog-form" ).dialog( "open" ); 
			 }
			 else{
				 window.location = "home.cyt";
			 }		 	 	
		 },
		 error: function(jqXHR, textStatus, errorThrown) {
		    alert("error:" + textStatus + " exception:" + errorThrown);
		 }
	 });
 }
}

function postInCyyte(){
	 
	$("#postInCyyteForm").ajaxSubmit({
			 type: 'POST',
			 url: 'send_question/postScript.cyt',
			 success: function(data) {
				 if(data.indexOf("post_question") != -1){
					 $( "#login-dialog-form" ).dialog( "open" ); 
				 }
				 else{
					 window.location = data;
				 }		 	 	
			 },
			 error: function(jqXHR, textStatus, errorThrown) {
			    alert("error:" + textStatus + " exception:" + errorThrown);
			 }
	});	
}

//iterates over form elements making sure input validation is good.
function validated()
{
 // clear out all previous form errors.
 clearAllErrors();
 
 // perform each of the different key types of input validation only, no
 // business rules outside of input validation should be done client side.
 if(!isRequiredFields())
 {
	 //alert("isRequiredFields");
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }

 if(!isValidMailList())
 {
	 //alert("isValidMailList");
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }

 if(!isDuplicateEmail())
 {
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }

 return true;
}

function isDuplicateEmail(){
	 var formGood = true;
	 var emails = [];

	var countMails = 0;
	//get all emails
	 $("input:text").each(function(index){    
        var inputID = $(this).attr("id");
		
		if(inputID != "su_email" && inputID != "confirm_email" && inputID != "user_email"){
	
			if(inputID.indexOf("email") != -1)
			{   
				emails[countMails] = $("#"+inputID).val();;
				countMails++;
			}
		}
    }); // each function
	 
	 //alert("email size: "+ emails.length + " 1st: "+emails[0]);
	 
  	var l = emails.length; 
	//check duplicates
	 $("input:text").each(function(index){    
        var inputID = $(this).attr("id");
		
		if(inputID != "su_email" && inputID != "confirm_email" && inputID != "user_email" ){	
			if(inputID.indexOf("email") != -1)
			{   
				var exists = 0;
				for(var i=0; i<l; i++){
					//alert("email array: "+ emails[i] + " field: "+$("#"+inputID).val());					
					if(emails[i] == $("#"+inputID).val())exists++;
				}
									
				if (exists > 1){
					setError(inputID, "You have entered a duplicate email address.");
					formGood = false;
					return formGood;
				}
			}
		}
    }); // each function
	

	return formGood;


}

function isValidMailList()
{

	 // we'll use this to check error state once after ALL fields have validated.
	 var formGood = true;
	 var emailFilter = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i;
 
	 
	 // loop over all the input fields looking for "known good" text input,
	 $("input:text").each(function(index){
		 // if input doesn't have an id, skip the whole process theres no need to validate it
		 // server should ignore it, and we can't toggle error states without an id.
		var inputID = $(this).attr("id");
		 //alert("field " +inputID);

		if(inputID != "grpName" && inputID != "grpType" && inputID != "username" && inputID != "su_email" 
		   && inputID != "confirm_email" && inputID != "ac_country" && inputID != "ac_country_hidden" && inputID != "user_email"
		   && inputID != "su_password" && inputID != "gender" && inputID != "ageGroup" && inputID != "activate_act"
		   && inputID != "login_email" && inputID != "login_pwd" && inputID != "postal_area" && inputID != "postcode" && inputID != "pageName"
		   && inputID != "locality" && inputID != "postcodex" && inputID != "Neighbourhood" && inputID != "county"
		   && inputID != "ac_country1" && inputID != "ac_country1_hidden"){

			 if(!emailFilter.test( $("#"+inputID).val() ) )
			 {
				 //alert("ERROR");
				 setError(inputID, "You have entered an invalid email address.");
				 formGood = false;
			 }
		}
	 }); // each function
	 
 return formGood;
 
}   // function

function isRequiredFields()
{
	 // we'll use this to check error state once after ALL fields have validated.
	 var formGood = true;
	 
	 // loop over all the input fields looking for "known good" text input,
	 // we'll ignore email addresses because they will get check later.
	 $("input:text").each(function(index){
		 // if input doesn't have an id, skip the whole process theres no need to validate it
		 // server should ignore it, and we can't toggle error states without an id.
		 var inputID = $(this).attr("id");
		 //alert("field " +inputID);
		 if(inputID != "username" && inputID != "su_email" 
		   && inputID != "confirm_email" && inputID != "ac_country" && inputID != "ac_country_hidden" && inputID != "user_email" 
		   && inputID != "su_password" && inputID != "gender" && inputID != "ageGroup" && inputID != "activate_act" 
		   && inputID != "login_email" && inputID != "login_pwd" && inputID != "postal_area" && inputID != "postcode" && inputID != "pageName"
		   && inputID != "locality" && inputID != "postcodex" && inputID != "Neighbourhood" && inputID != "county"   
			   && inputID != "ac_country1" && inputID != "ac_country1_hidden"){
		   
				 if( $(this).val() == "")
				 {
					 //alert(inputID);
					 setError(inputID, "Enter required fields.");
					 formGood = false;
				 }
			}
	 
	 }); // each function
	 
	if( $("#grpName").val() == "Enter a name for your group")
	 {
		 setError("grpName", "Please Enter required field.");
		 formGood = false;
	 }
	 
	 if( $("#grpType").val() == "Enter or select from list")
	 {
		 setError("grpType", "Please Enter required field.");
		 formGood = false;
	 }
	 
 return formGood;
 
}   // function

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

	$('textarea,input:text,input:file').each(function(){
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


var tips = $( ".validateTips_df" );
function setValidateTips(val){
	tips = val;
}
//=============================================================================
	$(function() {
		$( "#dialog:ui-dialog" ).dialog( "destroy" );
		
		var username = $( "#username" ),
			email = $( "#su_email" ),
			confirmEmail = $( "#confirm_email" ),
			password = $( "#su_password" ),
			allFields = $( [] ).add( username ).add( email ).add( confirmEmail ).add( password ),
			//login variables
			lgEmail = $( "#login_email" ),
			lgPassword = $( "#login_pwd" ),
			loginFields = $( [] ).add( lgEmail ).add( lgPassword ),
			//extra signup variables
			country = $( "#ac_country" ),
			postcode = $( "#postcode" ),
			postal_area = $( "#postal_area" ),			
			gender = $( "#gender" ),
			ageGroup = $( "#ageGroup" ),
			terms = $( "#chkTerms" ),
			activate_act = $( "#activate_act" ),
			fgtPwdEmail =  $( "#user_email" ),
			otherFields = $( [] ).add( country ).add( postcode ).add( postal_area ).add( gender ).add( ageGroup ).add( activate_act ).add( fgtPwdEmail );//,
			//tips = $( ".validateTips" );

		
		function updateTips( t ) {
			tips
				.text( t )
				.addClass( "ui-state-highlight" );
			setTimeout(function() {
				tips.removeClass( "ui-state-highlight", 1500 );
			}, 500 );
		}
		function resetTips( t ) {
			tips.text( t );
			tips.removeClass( "ui-state-highlight");
			//tips.css("background", "transparent");
		}
		
		function getActivationMsg(email){
			var msg = "Congratulations!!! Your account has now been setup. Your Activation code has been mailed to your email address ('"+email+"'). <BR/> Please <strong>enter you activation code</strong> in the text field below to access your account";
			return msg;			
		}

		function checkLength( o, n, min, max ) {
			if ( o.val().length > max || o.val().length < min ) {
				o.addClass( "ui-state-error" );
				updateTips( "Length of " + n + " must be between " +
					min + " and " + max + "." );
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp( o, regexp, n ) {
			if ( !( regexp.test( o.val() ) ) ) {
				o.addClass( "ui-state-error" );
				updateTips( n );
				return false;
			} else {
				return true;
			}
		}
		
		function validEmailAddress( o ){
		    if(email.val() != confirmEmail.val())
			{
				o.addClass( "ui-state-error" );
				updateTips( "Your email addresses do not match. Please try again." );
				return false;
			}else {
				return true;
			}
		}
		
		function validGender( ){
			//alert(o.val)
		    if( gender.val() == 'select')
			{
				gender.addClass( "ui-state-error" );
				updateTips( "please select a gender" );
				return false;
			}else {
				return true;
			}
		}

		function validCountry( ){
			//alert(o.val)
		    if( country.val() == '')
			{
				country.addClass( "ui-state-error" );
				updateTips( "please type in a country" );
				return false;
			}else {
				return true;
			}
		}

		function validActivationAct( ){
			//alert(o.val)
		    if( activate_act.val() == '')
			{
		    	activate_act.addClass( "ui-state-error" );
				updateTips( "please type in your activation code" );
				return false;
			}else {
				return true;
			}
		}
		
		function validAge( ){
			//alert(o.val)
		    if( ageGroup.val() == 'select')
			{
				ageGroup.addClass( "ui-state-error" );
				updateTips( "please select an age group" );
				return false;
			}else {
				return true;
			}
		}
		

		// make sure terms and conditions has been checked.
		function isTermsChecked(){    			
			var termsAccepted = terms.attr("checked");
		    if(termsAccepted == false){
		        var errorMessage = "accept Terms & Conditions and Privacy Policy.";
		        terms.addClass( "ui-state-error" );
				updateTips( errorMessage );

		        return false;
		    }
		    return true;
		}



		//======SIGNUP DIALOG==========
		$( "#dialog-form" ).dialog({
			autoOpen: false,
			height: 470,
			width: 350,
			modal: true,
			buttons: {
				"Continue": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
					
					//validate name
					bValid = bValid && checkLength( username, "username", 3, 16 );
					bValid = bValid && checkRegexp( username, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
					//validate email
					bValid = bValid && checkLength( email, "email", 6, 80 );
					bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "invalid email entered: eg. name@incyyte.com" );
					bValid = bValid && checkLength( confirmEmail, "confirm email", 6, 80 );
					bValid = bValid && validEmailAddress( confirmEmail );

					//validate password
					bValid = bValid && checkLength( password, "password", 5, 16 );
					bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );					
					
					if ( bValid ) {
						
						usn = $("#username").val();
						sumail = $("#su_email").val();
						pass = $("#su_password").val();
						
						$("#sn_email").val(sumail);
						$("#sn_password").val(pass);
						
						//submit form
						$("#activate_msg").text('');
						$("#activate_msg").append(getActivationMsg(email.val())).val();
						setValidateTips($( ".validateTips_daf" ));
						setDefaultCountry("GB","#ac_country");
						
						$("#signUpForm").ajaxSubmit({	
							type: 'POST',
							url: 'send_question/checkEmail.cyt',
							success: function(data) {
								if(data == 'success'){
									$( "#dialog-form" ).dialog( "close" );	
									
									$( "#dialog-area-form" ).dialog( "open" );
								}
								else{
									updateTips(data);
								}
							},
							error: function(jqXHR, textStatus, errorThrown) {
								alert("error:" + textStatus + " exception:" + errorThrown);
							}
						});	
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
				resetTips("Please sign up to send your inCyyte.");
			}
		});
/*
		$( "#send" )
			.click(function() {
				$( "#dialog-form" ).dialog( "open" );
			});
*/			
		$( "#addUserGrp" )
			.click(function() {
				setValidateTips($( ".validateTips_df" ));
				$( "#login-dialog-form" ).dialog( "open" );
			});			
			
			
			
		//======LOGIN DIALOG==========
		$( "#login-dialog-form" ).dialog({
			autoOpen: false,
			height: 290,
			width: 350,
			modal: true,
			buttons: {
				"Log In": function() {
					var bValid = true;
					loginFields.removeClass( "ui-state-error" );										
					
					//validate email
					bValid = bValid && checkLength( lgEmail, "email", 6, 80 );
					bValid = bValid && checkRegexp( lgEmail, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "invalid email entered: eg. name@incyyte.com" );

					//validate password
					bValid = bValid && checkLength( lgPassword, "password", 5, 16 );
					bValid = bValid && checkRegexp( lgPassword, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );					
									
					if ( bValid ) {
						
						var email = $("#login_email").val();
						var pwd = $("#login_pwd").val();
						
						$("#loginEmail").val(email);
						$("#password-password").val(pwd);
						
						//Process our Form Submission with jQuery AJAX Function						
						$("#loginForm").ajaxSubmit({
							type: 'POST',
							url: 'send_question/login.cyt',
							success: function(data) {
								$( this ).dialog( "close" );
								if(data == 'success'){
									window.location = "home.cyt";
								}
								else{
									window.location = "login.cyt";
								}
							},
							error: function(jqXHR, textStatus, errorThrown) {
								alert("error:" + textStatus + " exception:" + errorThrown);
							}
						});						
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				loginFields.val( "" ).removeClass( "ui-state-error" );
				resetTips("Please log in to send your inCyyte.");
			}
		});

		$( "#login-user" )
			.click(function() {
				$( "#dialog-form" ).dialog( "close" );
				setValidateTips($( ".validateTips_ldf" ));
				$( "#login-dialog-form" ).dialog( "open" );
			});
		$( "#fgtpwd-user" )
			.click(function() {
				$( "#login-dialog-form" ).dialog( "close" );
				setValidateTips($( ".validateTips_fdf" ));
				$( "#fgtpwd-dialog-form" ).dialog( "open" );
		});
		
		$( "#signup-user" )
		.click(function() {
			$( "#login-dialog-form" ).dialog( "close" );
			setValidateTips($( ".validateTips_fdf" ));
			$( "#dialog-form" ).dialog( "open" );
		});
			

		//======SIGNUP AREA DIALOG==========
		$( "#dialog-area-form" ).dialog({
			autoOpen: false,
			height: 520,
			width: 350,
			modal: true,
			buttons: {
				"Continue": function() {
					var bValid = true;
					otherFields.removeClass( "ui-state-error" );					
					 
					 //validate country					 
					bValid = bValid && validCountry( );
					bValid = bValid && checkLength( country, "country", 3, 80 );
					
					if ($("#ac_country_hidden").val() == "gb"){
						bValid = bValid && checkLength( postcode, "postcode", 3, 8 );
						bValid = bValid && checkRegexp( postcode, /^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))) {0,1}[0-9][A-Za-z]{2})$/, "invalid postcode entered" );					
					}else{
						bValid = bValid && checkLength( postal_area, "postal area", 3, 80 );
					}
					//validate age & gender
					bValid = bValid && validGender( );
					bValid = bValid && validAge( );
					bValid = bValid && isTermsChecked( );
					//alert("Country: "+ $("#ac_country").val() + " code: "+ $("#ac_country_hidden").val())
					
					if ( bValid ) {
						var cty = $("#ac_country_hidden").val();
						var pcode = $("#postcode").val();
						var parea = $("#postal_area").val();
						var gen = $("#gender").val();
						var age = $("#ageGroup").val();
						
						
						$("#su_username").val(usn);
						$("#sn_email").val(sumail);
						$("#sn_password").val(pass);
						
						$("#su_country").val(cty);
						$("#su_postcode").val(pcode);
						$("#su_postalarea").val(parea);
						$("#su_ageGroup").val(age);
						$("#su_gender").val(gen);    
						
						//alert($("#sn_email").val());
						
						//Process our Form Submission with jQuery AJAX Function	
						$("#signUpForm").ajaxSubmit({	
							type: 'POST',
							url: 'send_question/signup.cyt',
							success: function(data) {
								//alert(data);
								if(data == 'success'){
									//submit form
									$("#activate_msg").text('');
									$("#activate_msg").append(getActivationMsg(sumail)).val();
									$("#dialog-area-form").dialog( "close" );
									setValidateTips($( ".validateTips_adf" ));
									
									window.location = "send_question/complete.cyt";
									//$( "#activate-dialog-form" ).dialog( "open" );
								}else{
									updateTips( "Error occured, while setting this account" );
								}								
							},
							error: function(jqXHR, textStatus, errorThrown) {
								alert("error:" + textStatus + " exception:" + errorThrown);
							}
						});
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				otherFields.val( "" ).removeClass( "ui-state-error" );
				resetTips("All form fields are required.");
			}
		});

		$( "#login-user3" )
			.click(function() {
				//alert($('#create-user option:selected').val())			 
				$( "#dialog-area-form" ).dialog( "close" );
				setValidateTips($( ".validateTips_ldf" ));
				$( "#login-dialog-form" ).dialog( "open" );
			});


		
		//======ACTIVATION DIALOG==========
		$( "#activate-dialog-form" ).dialog({
			autoOpen: false,
			height: 300,
			width: 500,
			modal: true,
			buttons: {
				"Activate": function() {
					var bValid = true;
					otherFields.removeClass( "ui-state-error" );
					
					 //validate activation Code					 
					bValid = bValid && validActivationAct( );
					bValid = bValid && checkLength( activate_act, "activation code", 7, 8 );
				 					 					

					if ( bValid ) {
						var activate = $("#activate_act").val();
						$("#su_activate").val(activate);
						
						$("#signUpForm").ajaxSubmit({	
							type: 'POST',
							url: 'send_question/activate.cyt',
							success: function(data) {
								if(data == "failure"){
									activate_act.addClass( "ui-state-error" );
									updateTips( "The activation code is invalid."  );									
								}
								else{
									$("#activate-dialog-form").dialog( "close" );
									window.location = "home.cyt";
								}
							},
							error: function(jqXHR, textStatus, errorThrown) {
								alert("error:" + textStatus + " exception:" + errorThrown);
							}
						});		
					}

				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				otherFields.val( "" ).removeClass( "ui-state-error" );
				resetTips("Enter your Activation code.");
			}
		});

		$( "#resend_activation_cd" )
			.click(function() {
				alert("Activation Email resent..");
			});	
		
		
		//======FORGOTTEN PASSWORD DIALOG==========
		$( "#fgtpwd-dialog-form" ).dialog({
			autoOpen: false,
			height: 290,
			width: 350,
			modal: true,
			buttons: {
				"Request password": function() {
					var bValid = true;
					otherFields.removeClass( "ui-state-error" );
					
					//validate email
					bValid = bValid && checkLength( fgtPwdEmail, "email", 6, 80 );
					bValid = bValid && checkRegexp( fgtPwdEmail, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "invalid email entered: eg. name@incyyte.com" );

					if ( bValid ) {
						var user_email = $("#user_email").val();
						$("#li_userEmail").val(user_email);
						
						$("#loginForm").ajaxSubmit({	
							type: 'POST',
							url: 'send_question/fpassword.cyt',
							success: function(data) {
								//alert(data);
								if(data == 'success'){
									$( this ).dialog( "close" );
									$( "#fgtpwd-dialog-msg" ).dialog( "open" );
								}
								else{
									fgtPwdEmail.addClass( "ui-state-error" );
									updateTips( user_email + " does not exist."  );
								}
							},
							error: function(jqXHR, textStatus, errorThrown) {
								alert("error:" + textStatus + " exception:" + errorThrown);
							}
						});	
					}

				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				otherFields.val( "" ).removeClass( "ui-state-error" );
				resetTips("Request a new password.");
			}
		});
		
		//======FORGOTTEN PASSWORD MESSAGE==========
		$( "#fgtpwd-dialog-msg" ).dialog({
			autoOpen: false,
			height: 200,
			width: 350,
			modal: true,
			buttons: {
				Ok: function() {
					$( this ).dialog( "close" );
				}
			}
		});

		
	});


  $(function() {  
	    $('.editable-select').editableSelect(
	      {
	        bg_iframe: false,
	        onSelect: function(list_item) {
	        	//alert(list_item.text())
	          //$('#results').html('List item text: '+ list_item.text() +'<br> \
	          //Input value: '+ this.text.val());
	        }
	      }
	    );
	    var select = $('.editable-select:first');
	    var instances = select.editableSelectInstances();
	    //instances[0].addOption('Germany, value added programmatically');
	    
	    $('.editable-select').focus(function() {  
	         if($(this).val() == 'Enter or select from list') $(this).val('');  
		}).blur(function() {  
	     	if( $(this).val() == '') $(this).val('Enter or select from list');  
		}); 
		
	  });
	
//=============================================================================

