// JavaScript Document
// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

// all startup logic should exist in the init function.
function document_onInit()
{
    setDefaultInput();
	
	//wire event handlers
	$('#editName').click( function() {displayField('name')});
	$('#editEmail').click( function() {displayField('email')});
	$('#editPhone').click( function() {displayField('phone')});
	$('#editGender').click( function() {displayField('gender')});
	$('#editAge').click( function() {displayField('age')});
}
function updateCloseDate(){
	$('#closureDateE').val($('#closureDate').val());
}

function setDefaultInput()
{
	$("#communicator").css("visibility", "hidden");
	
	$("#nameField").hide();
	$("#emailField").hide();
	$("#phoneField").hide();
	$("#genderField").hide();
	$("#ageField").hide();

}

function displayField(field){
		$("#"+field+"Text").hide();
		$("#"+field+"Field").hide().fadeIn(2000);
}

function createInCyyte(){
	//alert($("#category").val());
 if (validated())
 {
	 //document.createInCyyteForm.submit();
	 window.location.href = "create_mailList.cyt";
 }
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
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }

 if(!isClosureDateRequired())
 {
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }

if(!isFileUploadRequired())
 {
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }

 if(!isURLLinkRequired())
 {
     scroll(0,0);    // scroll to top of page so user can see communicator message.
     return false;
 }


 return true;
}
function isFileUploadRequired()
{
 // we'll use this to check error state once after ALL fields have validated.
 var formGood = true;
 
	 if( $('#addFile').is(':checked') && $("#uploadfile").val() == "")
	 {
		 setError("uploadfile");
		 formGood = false;
	 }
 
 return formGood;
 
}   // function

function isClosureDateRequired()
{
 // we'll use this to check error state once after ALL fields have validated.
 var formGood = true;
 var dateFilter = /^(\d{1,2})\/(\d{1,2})\/(\d{4}) \d{1,2}:\d{2}([ap]m)?$/;
 
	 if( $('#closureDate').val() != 'Select a closing date' )
	 {
			if(!dateFilter.test( $("#closureDate").val() ) )
			 {
				 //alert("ERROR");
				 setError("closureDate", "Invalid closure date format entered");
				 formGood = false;
			 }		 
		 
	 }
 
 return formGood;
 
}   // function




function isURLLinkRequired()
{
 // we'll use this to check error state once after ALL fields have validated.
 var formGood = true;
 
	 if( $('#addLink').is(':checked') && $("#eLink").val() == "http://...")
	 {
		 setError("eLink");
		 formGood = false;
	 }
 
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
	   
			 if( $(this).val() == "")
			 {
				 alert(inputID);
				 setError(inputID);
				 formGood = false;
			 }
				  
	 
	 }); // each function
	 
	 if( $("#question").val() == "What is your question?")
	 {
		 setError("question");
		 formGood = false;
	 }

	 if( $("#category").val() == "select")
	 {
		 setError("category");
		 formGood = false;
	 }
 
 return formGood;
 
}   // function

//sets error status icon visibility, tooltip message, and input error style
function setError(inputField)
{
	/*
 $("#errorMsg").addClass("error");
 $("#errorMsg").css("visibility", "visible");
*/
 $("#communicator").addClass("error");
 $("#communicator").css("visibility", "visible");


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
 	//$("#errorMsg").removeClass("error");
	//$("#errorMsg").css("visibility", "hidden");
	$("#communicator").removeClass("error");
	$("#communicator").css("visibility", "hidden");

	$('textarea,input:text,input:file').each(function(){
		 var inputID = $(this).attr("id");
		if(inputID != "")
		 clearError(inputID);
	});
	
	clearError("category");
   
}

function clearError(inputField)
{
    var field = "#" + inputField;
    $(field).removeClass("inputError");

}
