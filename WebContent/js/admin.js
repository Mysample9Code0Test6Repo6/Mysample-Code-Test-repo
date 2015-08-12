//JavaScript Document
//jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

//all startup logic should exist in the init function.
function document_onInit()
{
	setDefaultInput();
	//setGroupListDefault();
	//wire event handlers
	$("#send_gen").click(function() { send_inCyyte(); });

}

function setDefaultInput()
{
	//default checkbox
	$("#question").focus();
	$("#communicator").css("visibility", "hidden");

}


//return the value of the radio button that is checked
//return an empty string if none are checked, or
//there are no radio buttons
function getCheckedValue(radioObj) {
	var reps = "";
	if(!radioObj)
		return "";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "";
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {
			reps += radioObj[i].id + "|";
		}
	}
	return reps;
}

function setAnswers(){
	var ans="";
	$("input:text").each(function(index){    
		// if input doesn't have an id, skip the whole process theres no need to count it
		var inputID = $(this).attr("id");
		if(inputID.indexOf("opt") != -1)
		{   
			ans +=  $("#"+inputID).val() + "|";
		}           
	}); // each function

	return ans; 	
}

function send_inCyyte(){

	if (validated())
	{
		var anonymity = true;
		var category = $("#category").val();
		var eLink = $("#eLink").val();
		var uploadfile = $("#uploadfile").val();
		var ans = setAnswers();

		//alert("Ans : " + anonymity + "\n" + category + "\n" + ans);

		$("#hid_anonymity").val(anonymity);
		$("#hid_category").val(category);
		$("#hid_eLink").val(eLink);
		$("#hid_uploadfile").val(uploadfile);
		$("#hid_answerArr").val(ans);

		$("#sendInCyyteForm").attr("action", "send_gen.cyt"); 
		$("#sendInCyyteForm").submit();
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
		//alert("isRequiredFields");
		scroll(0,0);    // scroll to top of page so user can see communicator message.
		return false;
	}

	if(!isCategorySelected())
	{
		scroll(0,0);    // scroll to top of page so user can see communicator message.
		return false;
	}

	if(!isOptionsEntered())
	{
		scroll(0,0);    // scroll to top of page so user can see communicator message.
		return false;
	}

	return true;
}

function isRequiredFields()
{
	// we'll use this to check error state once after ALL fields have validated.
	var formGood = true;	
	var inputID = $("#question").attr("id");

	if($("#question").val() == ""){
		$("#categoryLabel").addClass("errorLabel");
		setError(inputID, "Enter your question below...");
		formGood = false;
	}	

	return formGood;

}   // function

function isCategorySelected(){

	var formGood = true;
	var inputID = $("#category").attr("id");

	//check Category
	if($("#category").val() == "select"){
		$("#categoryLabel").addClass("errorLabel");
		setError(inputID, "Select a category for this question.");
		formGood = false;
	}	

	return formGood;
}

function isOptionsEntered(){
	var formGood = true;
	var countMax = 0;
	$("input:text").each(function(index){    
		// if input doesn't have an id, skip the whole process theres no need to count it
		var inputID = $(this).attr("id");
		if(inputID.indexOf("opt") != -1)
		{   
			//alert("test opt val: "+ $("#"+inputID).val());
			if($("#"+inputID).val() != ""){
				countMax++;
			}
		}           
	}); // each function	

	//check minimum 2 Options entered
	if(countMax < 2){
		$("#optionsLabel").addClass("errorLabel");
		setError("", "Enter a minimum of 2 answer options.");
		formGood = false;
	}

	return formGood;
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
	if(inputField != "")
		$(field).addClass("inputError");

	var errorIcon = "#e_" + inputField;
	$(errorIcon).css("visibility", "visible");
	$(errorIcon).attr("title", message);
}


function clearAllErrors()
{
	$("#communicator").removeClass("error");
	$("#communicator").css("visibility", "hidden");

	$("#categoryLabel").removeClass("errorLabel");
	$("#optionsLabel").removeClass("errorLabel");
	$("#groupLabel").removeClass("errorLabel");

	$('textarea,input:text,input:file,input:password').each(function(){
		var inputID = $(this).attr("id");
		if(inputID != "")
			clearError(inputID);
	});

}

//opposite of setError
function clearError(inputField)
{
	var field = "#" + inputField;
	$(field).removeClass("inputError");

	var errorIcon = "#e_" + inputField;
	$(errorIcon).css("visibility", "hidden");
}

function PositionDialog(dialog,link) {     
	var myDialogX = $(link).position().left;     
	var myDialogY = $(link).position().top + $(link).outerHeight() + 65;     
	$(dialog).dialog('option', 'position', [myDialogX, myDialogY]); 
	$(dialog).dialog('open');    
} 


$.fx.speeds._default = 1000;
$(function() {
	$( "#dialog:ui-dialog" ).dialog( "destroy" );

	$( "#category-dialog-form" ).dialog({
		height: 125,
		width: 330,
		autoOpen: false,
		show: "blind",
		modal: true,
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
			}
		}
	});

	$( "#options-dialog-form" ).dialog({
		height: 265,
		width: 330,
		autoOpen: false,
		show: "blind",
		modal: true,
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
			}
		}
	});

	$( "#linkfile-dialog-form" ).dialog({
		height: 160,
		width: 330,
		autoOpen: false,
		show: "blind",
		modal: true,
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
			}
		}
	});

	$( "#categoryLabel" )
	.click(function() {	
		PositionDialog("#category-dialog-form","#categoryLabel");					
	});	

	$( "#optionsLabel" )
	.click(function() {	
		PositionDialog("#options-dialog-form","#optionsLabel");					
	});	

	$( "#linkFileLabel" )
	.click(function() {	
		PositionDialog("#linkfile-dialog-form","#linkFileLabel");					
	});	

});
