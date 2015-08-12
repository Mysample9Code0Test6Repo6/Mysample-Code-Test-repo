// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

//all startup logic should exist in the init function.
function document_onInit()
{
	
	document.getElementById("label1").innerText = 0;
	
	// Get all textareas that have a "maxlength" property. 
    //Now, and when later adding HTML using jQuery-scripting: 
    $('#myTextarea2').live('keyup blur', function() { 
        // Store the maxlength and value of the field. 
        var maxlength = $(this).attr('maxlength'); 
        var val = $(this).val(); 
 
        // Trim the field if it has content over the maxlength. 
        if (val.length > maxlength) { 
            $(this).val(val.slice(0, maxlength)); 
        } 
    }); 

    $('#faccebook').click(function(){
     //    window.open("socialauth.do?id=facebook", "loginWindow", "location=1,status=1,scrollbars=1, width=400,height=400");
         
    
    }
    );
    
    $('#twitter').click(function(){
     //   window.open("socialauth.do?id=twitter", "loginWindow", "location=1,status=1,scrollbars=1, width=400,height=400");
    });
    
    
    
    //var $dialog_msg = $('<div id="dialog-message" title="Please Enter a Question"> <p> To get insight on opinions you need to enter a probing question.	</p></div>');
    
    $("#buttonaria_red").click(function(event){  
    	event.preventDefault();
    	
    		$("form#inCyyteForm").submit(); 
    		return true;
 		
     });       
    
	$('#fgt_pwd').click(function(){
		//alert("forgot pwd");
		window.location = "login/fgtpwd.cyt";	
    });
    //setDefaultFields();
}


function countit(what){
	formcontent=what.value;
	document.getElementById("label1").innerText = formcontent.length;
}

function setDefaultFields(){

	resetRegistrationFields('#username', 'Username');
	resetRegistrationFields('#su_email', 'Email');
	//resetRegistrationFields('#confirm_email', 'Confirm Email');
	resetRegistrationFields('#su_password', 'Password');
}

function resetRegistrationFields(name, value){ 
		$(name).val(value);
		//$(name).addClass("fadeInputTxt");	  
		$(name).focus(function() {  
	         if($(this).val() == value){//clear and set to black font
				$(this).val(''); 
			 	//$(name).removeClass("fadeInputTxt");
	         	//$(name).addClass("inputTxt");
			 }
		}).blur(function() {  
			 if( $(this).val() == ''){//reset blur
				$(this).val(value);
	         	//$(name).removeClass("inputTxt");
	         	//$(name).addClass("fadeInputTxt");
			 }

		}); 
}



