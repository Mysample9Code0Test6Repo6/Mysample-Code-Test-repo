// JavaScript Document
// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

// all startup logic should exist in the init function.
function document_onInit()
{
    setDefaultInput();
	setGroupListDefault();
	//wire event handlers
	$("#send").click(function() { send_inCyyte(); });

}

function setDefaultInput()
{
	//default checkbox
    $("#question").focus();
	$("#communicator").css("visibility", "hidden");

}

function setGroupListDefault()
{
	//default groupList settings
	groupList_setMinRows();
	groupList_setRowColors();
    // wire event handlers
    
    // mouse over and out handlers for setting background image on payment history table.
    // ignore blank rows that were added via groupList_setMinRows()
	
    $("#groupList tbody tr").not(".blank").mouseover(function() {
    	$(this).css("backgroundImage", "url(css/images/gridBackground_over.png)");
    	
    	// first and last rows have a cap on them.
    	$(this).find("td:eq(0)").css("backgroundImage", "url(./css/images/gridBackground_over_left.png)");
    	$(this).find("td:eq(2)").css("backgroundImage", "url(css/images/gridBackground_over_right.png)");
	});	
	
    $("#groupList tbody tr").not(".blank").mouseout(function() {
    	$(this).css("backgroundImage", "none");
    	$(this).find("td:eq(0)").css("backgroundImage", "none");
    	$(this).find("td:eq(2)").css("backgroundImage", "none");
	});
	
    $("#groupList tbody tr")
	.not(".blank")
	.mouseover(function() { $(this).addClass("hover"); })
	.mouseout(function() { $(this).removeClass("hover"); });


}

// looks at the number of rows in the payment history, visually it looks
// better if there's at least about 20 rows, look at the number of rows,
// if there's less than 20 then add blank rows.
function groupList_setMinRows()
{
	var numRows = $("#groupList tbody tr").length;
	while(numRows < 7)
	{
		var newRow = '<tr class="blank"><td colspan="7"></td></tr>';
		$("#groupList tbody").append(newRow);
		numRows++;
	}
}

// simply sets the alternating colors on the table grid.
function groupList_setRowColors()
{
	// tbody is important, without it our header get's colored as well.
	/*
	$("#groupList tbody tr:even").css("backgroundColor", "#d3dde7");
	$("#groupList tbody tr:odd").css("backgroundColor", "#e1e8ef");*/

	$("#groupList tbody tr:even").css("backgroundColor", "#F4F4F4");
	$("#groupList tbody tr:odd").css("backgroundColor", "#FFFFFF");

}

function send_inCyyte(){
	
 if (validated())
 {
	alert("Valid: SEND");
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

  if(!isGroupSelected())
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
	 
	 // loop over all the input fields looking for "known good" text input,
	 // we'll ignore email addresses because they will get check later.
	 $("textarea").each(function(index){
		 // if input doesn't have an id, skip the whole process theres no need to validate it
		 // server should ignore it, and we can't toggle error states without an id.
		 var inputID = $(this).attr("id");
		 if( $(this).val() == "")
		 {
			 setError(inputID, "Enter your question below...");
			 formGood = false;
		 }
	 
	 }); // each function
	 
	 
 return formGood;
 
}   // function

function isCategorySelected(){

	 var formGood = true;
	//check Category
	if($("#category").val() == "select"){
		$("#categoryLabel").addClass("errorLabel");
		setError("", "Select a category for this question.");
		formGood = false;
	}
	

	return formGood;
}

function isOptionsEntered(){

	 var formGood = true;
	
	var countMax = 0
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

	//alert("total opts: "+ countMax);
	//check max 2 Options entered
	if(countMax < 2){
		$("#optionsLabel").addClass("errorLabel");
		setError("", "Select a maximum of 2 answer options.");
		formGood = false;
	}

	return formGood;
}

function isGroupSelected(){

	 var formGood = true;
	 var countChecked = 0

	//check Group
	 $("input:checkbox").each(function(index){    
        // if input doesn't have an id, skip the whole process theres no need to count it
        var inputID = $(this).attr("id");
        if($("#"+inputID).attr("checked"))
        {   
            	countChecked++;
        }           
    }); // each function	
	
	if(countChecked == 0){
		$("#groupLabel").addClass("errorLabel");
		setError("", "Select a group/s to send the question.");
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

// opposite of setError
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

	$( "#grpList-dialog-form" ).dialog({
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

	$( "#groupLabel" )
	.click(function() {	
		PositionDialog("#grpList-dialog-form","#groupLabel");					
	});	

	$( "#linkFileLabel" )
	.click(function() {	
		PositionDialog("#linkfile-dialog-form","#linkFileLabel");					
	});	

});
