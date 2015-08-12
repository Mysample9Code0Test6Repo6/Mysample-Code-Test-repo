// JavaScript Document
// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

// all startup logic should exist in the init function.
function document_onInit()
{
    setDefaultInput();
    
    // Get all textareas that have a "maxlength" property. 
    //Now, and when later adding HTML using jQuery-scripting: 
    $('#question').live('keyup blur', function() { 
        // Store the maxlength and value of the field. 
        var maxlength = $(this).attr('maxlength'); 
        var val = $(this).val(); 
 
        // Trim the field if it has content over the maxlength. 
        if (val.length > maxlength) { 
            $(this).val(val.slice(0, maxlength)); 
        } 
    }); 

	
    if( $('#resp1').val() == '') $('#resp1').val('Yes');  
    if( $('#resp2').val() == '') $('#resp2').val('No');  
    if( $('#resp3').val() == '') $('#resp3').val('Not Sure');  
	
    
    $('#uploadButton').click(function() { uploadFileFunc(); });
    
	//wire event handlers
    $("#create").click(function() { createInCyyte(); });
    
    $("#uploadAnotherButton").click(function() { displayNotLoad(); });
	
	$('#addResOpt').click( function() {newRow();});
	$('#delete').click( function() {deleteRow();});
	$('#editQuestion').click( function() {displayQuField();});
	
	$('#eLink').val('http://...');
	
	$('#closureDate').datetimepicker({
		minDate: new Date(),
		stepMinute: 10,	
		dateFormat: 'dd/mm/yy'
	});
	$('#closureDateE').datetimepicker({
		minDate: new Date(),
		stepMinute: 10,	
		dateFormat: 'dd/mm/yy'
	});
	$('#closureDate').change(function() {updateCloseDate();});
}

function uploadFileFunc(){
	
	$("#loadingDiv").ajaxStart(function(){
	    $(this).show();
	 }).ajaxStop(function(){
	    $(this).hide();
	 });
	
	$("#createInCyyteForm").ajaxSubmit({	
		type:'POST',
		url: 'multiPartFileSingle.cyt',		
		success: function(data) {	
			if(data.indexOf("success") != -1){
				//set progress to 100%
				clearError("uploadfile");
				$("#communicator").removeClass("error");
				$("#communicator").css("visibility", "hidden");
				displayLoad(data);	
			}
			else{				
				displayNotLoad();
				setError("uploadfile");
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			displayNotLoad();
			setError("uploadfile");
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

function updateCloseDate(){
	$('#closureDateE').val($('#closureDate').val());
}

function setDefaultInput()
{
	$("#communicator").css("visibility", "hidden");
	
	//default upload checkbox
    if ($('#addFile').is(':checked')){
		//$('#uploadfile').hide().fadeIn(1500);
		$('#uploadDiv').hide().fadeIn(1500);		
	}else{
		$("#uploadDiv").hide(); 
		//$("#uploadfile").hide();
		clearError("uploadfile");
	}	

	//default addLink checkbox
    if ($('#addLink').is(':checked')){
		$('#eLink').hide().fadeIn(1500);
	}else{
		$("#eLink").hide(); 
	}	

	//elink
	$('#eLink').focus(function() {  
         if($(this).val() == 'http://...') $(this).val('');  
	}).blur(function() {  
     	if( $(this).val() == '') $(this).val('http://...');  
	}); 
	
	//default anonymity checkbox
    if ($('#anonymity').is(':checked')){
    	$("#senderDetails").hide(); 
	}else{		
		$('#senderDetails').hide().fadeIn(1500);
	}	
	
	//default search
	$('#qsearch').focus(function() {  
         if($(this).val() == 'seek insight...') $(this).val('');  
	}).blur(function() {  
     	if( $(this).val() == '') $(this).val('seek insight...');  
	}); 
	//default question
	$('#question').focus(function() {  
         if($(this).val() == 'What is your question?') $(this).val('');  
	}).blur(function() {  
     	if( $(this).val() == '') $(this).val('What is your question?');  
	}); 
	//default responses
	$('#resp1').focus(function() {  
         if($(this).val() == 'Yes') $(this).val('');  
	}).blur(function() {  
     	if( $(this).val() == '') $(this).val('Yes');  
	}); 
	$('#resp2').focus(function() {  
         if($(this).val() == 'No') $(this).val('');  
	}).blur(function() {  
     	if( $(this).val() == '') $(this).val('No');  
	}); 
	$('#resp3').focus(function() {  
        if($(this).val() == 'Not Sure') $(this).val('');  
	}).blur(function() {  
    	if( $(this).val() == '') $(this).val('Not Sure');  
	}); 

	$("#errorMsg").css("visibility", "hidden");
	$("#questionField").hide();


}

function isUploadChecked(){
	if ($('#addFile').is(':checked')){
		//$('#uploadfile').hide().fadeIn(1500);
		$('#uploadDiv').hide().fadeIn(1500);
	}else{
		$("#uploadDiv").hide(); 
		//$("#uploadfile").hide(); 
		clearError("uploadfile");
	}
}

function isAddLinkChecked(){
	if ($('#addLink').is(':checked')){
		$('#eLink').hide().fadeIn(1500);
	}else{
		$("#eLink").hide(); 
	}
}

function isAddAnonymityChecked(){
	if ($('#anonymity').is(':checked')){
		$("#senderDetails").hide(); 
	}else{		
		$('#senderDetails').hide().fadeIn(1500);
	}
}

function displayQuField(){
	$("#questionText").hide();
	$('#questionField').hide().fadeIn(1500);
}

var row = 4;
function newRow(){
	var countMax = 0;
	 $("input:text").each(function(index){    
        // if input doesn't have an id, skip the whole process there is no need to count it
        var inputID = $(this).attr("id");
        if(inputID.indexOf("resp") != -1)
        {   
            countMax++;
        }           
    }); // each function
	
	var maxOption = $("#answerMaxOption").val();
	if (countMax < maxOption){
		$("#responseOpts tbody" ).append( "<tr> <td class=\"style9\"><input name=\"resp"+row+"\" type=\"text\" id=\"resp"+row+"\" size=\"30\" class=\"inputwidth\" title=\"answer..\"/><img src=\"images/icons/delete-icon3.png\" alt=\"\" width=\"12\" height=\"12\"  id=\"delete\" title=\"remove this option\"/> </td> </tr>" );
		row++;
	}
	
}

function deleteRow(){
	$("#delete").live('click', function(event) {         
			$(this).parent().parent().remove(); 
	});
}

function setResponses(){
	var resps="";
	$("input:text").each(function(index){    
        // if input doesn't have an id, skip the whole process theres no need to count it
        var inputID = $(this).attr("id");
        if(inputID.indexOf("resp") != -1)
        {   
            resps +=  $("#"+inputID).val() + "|";
        }           
    }); // each function
	//alert("responese: "+ resps);
	$("#responses").val(resps);
}

function sendInCyyte() { 
	if (validated()) {
		setResponses();
		$('#createInCyyteForm').attr("action", "send.cyt"); 
		$("#createInCyyteForm").submit();
	} 
} 

function previewInCyyte() { 
	if (validated()) {
		setResponses();
		$('#createInCyyteForm').attr("action", "preview.cyt"); 
		$("#createInCyyteForm").submit();
	}
} 


function createInCyyte(){
	//alert($("#category").val());
 if (validated())
 {
	 setResponses();
	 
	 if($("#eLink").val() == "http://..."){
		 var empty="";
		 $("#eLink").val(empty);		 
	 }
	 
	 document.createInCyyteForm.submit();
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
				 //alert(inputID);
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
 	
 	//$("#errorMsg").addClass("error");
 	//$("#errorMsg").css("visibility", "visible");

 	$("#communicator").addClass("error");
 	$("#communicator").css("visibility", "visible");


 	var field = "#" + inputField;
 	$(field).addClass("inputError");

 	var errorIcon = "#e_" + inputField;
 	$(errorIcon).css("visibility", "visible");
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
   
}

function clearError(inputField)
{
    var field = "#" + inputField;
    $(field).removeClass("inputError");

}

