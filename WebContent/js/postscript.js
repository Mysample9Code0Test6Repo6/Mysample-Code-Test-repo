$(document).ready(function() {
	
	$("#communicator").css("display", "none");
	
	// Add the mask to body
	$("body").css("display", "none");

    $("body").fadeIn(2000);
    
    
    
	//======SIGNUP DIALOG==========
	$( "#login-box" ).dialog({
		autoOpen: true,
		height: 150,
		width: 250,
		modal: true,
		show: "blind",
				
			buttons: {					
					enter: function() {						
						$("#accessform").ajaxSubmit({	
							type: 'POST',
							url: '/inCyyte/postscript/access.cyt',
							success: function(data) {
								//alert(data);
								if(data == 'failure'){
									$("#communicator").css("display", "");
									var inputID = $('#access_code').attr("id");
									setError(inputID, "Enter a valid access code.");
								}
								else{										
									$( this ).dialog( "close" );
									linkLocation=data;
									$("body").fadeOut(500, redirectPage);	
								}
							},
							error: function(jqXHR, textStatus, errorThrown) {
								$("#communicator").css("display", "");
								var inputID = $('#access_code').attr("id");
								setError(inputID, "Error in the system, please try later");
							}
						});						
				
					},
					cancel: function() {
						$( this ).dialog( "close" );
						linkLocation="home.cyt";
						$("body").fadeOut(500, redirectPage);	
					}
				},
				close: function() {
					linkLocation="home.cyt";
					$("body").fadeOut(300, redirectPage);	
				}
			});
			
		function redirectPage() {
			window.location = linkLocation;
		}
		
		function setError(inputField, message){
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


		// opposite of setError
		function clearError(inputField)
		{
		    var field = "#" + inputField;
		    $(field).removeClass("inputError");
		    
		    var errorIcon = "#e_" + inputField;
		    $(errorIcon).css("visibility", "hidden");
		}
	
});
