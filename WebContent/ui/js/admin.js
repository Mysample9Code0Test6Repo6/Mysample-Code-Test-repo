// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

//all startup logic should exist in the init function.
function document_onInit()
{	    
	// validate login form on keyup and submit
	var lg_validator = $("#createacct_form").validate({
		rules: {
			login_pwd: {
				required: true,
				minlength: 6,
				maxlength: 20
			},
			login_email: {
				required: true,
				email: true
			}		},
		messages: {
			login_pwd: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			login_email: "Please enter a valid email address"		}
	}); 	
	
	$('#login_acct').click(function(){
    	//alert("Form Valid: "+ $("#createacct_form").valid());
    	lg_validator.form();
    	logIn2inCyyte();
    });
	
	$('#enterAccount').click(function(){    	
    	goToUserAccount();
    });
	
	
	var searchForm_validator = $("#searchForm").validate({
		rules: {			
			su_email: {
				required: true,
				email: true
			}		
		},
		messages: {			
			su_email: "Please enter a valid email address"		}
	}); 	
	
	$('#searchUserEmail').click(function(){
		searchForm_validator.form();    	
		searchUserEmail();
    });
	
	 
	 
	 $("#listTopinCyyteUsers").click(function () {		 	
			parent.$.fn.colorbox({'href':'div#topInCyyteList', 'open':true, 'inline':true});
	 });
	 
	 $("#closePopup").click(function () {
			parent.$.fn.colorbox.close();
	 });

    setDefaultFields();
}

function setDefaultFields(){	
	$("#common_error").css("display", "none");
}


function logIn2inCyyte(){
	if ($("#createacct_form").valid()) {
		//Process our Form Submission with jQuery AJAX Function	
			$("#createacct_form").ajaxSubmit({	
				type: 'POST',
				url: 'admin/submit.cyt',
				success: function(data) {
					if(data == 'error'){
						$("#common_error").css("display", "");
						//window.location = "admin.cyt";
					}else{
						window.location = "admindash.cyt";
					}																	
				},
				error: function(jqXHR, textStatus, errorThrown) {
					window.location = "admin.cyt";	
				}
			});
	 }
}

function searchUserEmail(){
	if ($("#searchForm").valid()) {
		//Process our Form Submission with jQuery AJAX Function	
		$("#searchForm").ajaxSubmit({	
			type: 'POST',
			url: 'searchUserDetail.cyt',
			success: function(data) {			
				$("#choose_your_answers").html(data);																
			},
			error: function(jqXHR, textStatus, errorThrown) {	
				alert(errorThrown);
			}
		});
	}
}

function goToUserAccount(){
	window.location = "dash.cyt";	
}

function displayUserDetail(userEmail){		
	$.ajax({
		type:'POST',
		url: "loadUserDetail.cyt?email="+userEmail,
		success: function(data){
			parent.$.fn.colorbox.close();
			$("#choose_your_answers").html(data);
			
		}
	});
}

function uploadLogoFunc() {
	$("#errorForLogoFormat").css("display","none");
	filePath = $('#uploadedDocFile').val();
	
	if (filePath == null || filePath == '') {
		return false;
	}
	var fileName = filePath.substring(12);
	$('#fileName').val(fileName);
	$('#incyyteCode').val($('#cyytecode').val());
	alert("incyyteCode -  " + $('#incyyteCode').val());

	var ext = filePath.split('.').pop().toLowerCase();
	if($.inArray(ext, ['xls']) == -1) {
		$("#errorForLogoFormat").css("display","");
		$(".errorLabel").text("Please upload the correct file format (.xls)");
		return false;
	}
	else{
		$("#imageLoaderLogo").ajaxStart(function(){
			$(this).show();
		}).ajaxStop(function(){
			$(this).hide();
		});
	}  
	
	$("#addImportEmailsForm").ajaxSubmit({
		type:'POST',
		url:'uploadPreRegMemCSVFile.cyt',
		success:function (data) {
			 if (data.indexOf("failure") != -1) {
				$("#errorForLogoFormat").css("display","");
				$(".errorLabel").text("The uploaded Doc exceeds the maximum allowed size(2 MB)");
			}else if(data == "noColumnError"){
				$("#errorForLogoFormat").css("display","");
				$(".errorLabel").text("Error Msg: No column named 'Email' was found in this csv upload. Please add a column name(Email).");
			}else{
				$("#msgLabel").html(data);
				//window.setTimeout(location.reload(true),1);
			} 
		},
		error:function (jqXHR, textStatus, errorThrown) {
			$("#errorForLogoFormat").css("display","");
		}
	});
}
	
