$(document).ready(function () {
	/*$("#displaySharedEmailList").click(function () {
		parent.$.fn.colorbox({'href':'div#sharedEmailList', 'open':true, 'inline':true});
	});*/
		displayShareModalOnload();
	 
		$("#videoLoader").css("display", "none");
		$("#imageLoader").css("display", "none");
		$("#docLoader").css("display", "none");
						
		 $('#incyyte_browse_videos').click(function () {
	         $('#incyyte_video_file_input').click();
	     });
	     $('#incyyte_browse_photos').click(function () {
	         $('#incyyte_photo_file_input').click();
	     });
	     $('#incyyte_browse_docs').click(function () {
	         $('#incyyte_doc_file_input').click();
	     });
	     
	     $('#incyyte_video_file_input').change(function() {
	    		$('#incyyte_video_error_msg').hide("fast");
	    		$('#incyyte_photos_error_msg').hide("fast");
	    		$('#incyyte_doc_error_msg').hide("fast");

	    		var vName = $(this).val().substring(12,25).concat("...");	
	    		$('#incyyte_video_file_name').text(vName);	
	    		$('#view_video_file_name').text(vName);		
	    	});

	    	$('#incyyte_photo_file_input').change(function () {
	    		$('#incyyte_video_error_msg').hide("fast");
	    		$('#incyyte_photos_error_msg').hide("fast");
	    		$('#incyyte_doc_error_msg').hide("fast");
	    		var pName = $(this).val().substring(12,25).concat("...");
	    		$('#incyyte_photo_file_name').text(pName);
	    		$('#view_photo_file_name').text(pName);
	    	});

	    	$('#incyyte_doc_file_input').change(function () {
	    		$('#incyyte_video_error_msg').hide("fast");
	    		$('#incyyte_photos_error_msg').hide("fast");
	    		$('#incyyte_doc_error_msg').hide("fast");
	    		var dName = $(this).val().substring(12,25).concat("...");
	    		$('#incyyte_doc_file_name').text(dName);
	    		$('#view_doc_file_name').text(dName);
	    	});
	    	
	    	
	    	$('#incyyteUploadVideoButton').click(function () {
	    		uploadFileFunc('Video');
	    	});
	    	$('#incyyteUploadPhotoButton').click(function () {
	    		uploadFileFunc('Image');
	    	});
	    	$('#incyyteUploadDocButton').click(function () {
	    		uploadFileFunc('Doc');
	    	});
	    	$('#incyyteDeletePhotoButton').click(function () {
	    		deleteFileFunc1('image');
	    	});

	    	$('#incyyteDeleteDocButton').click(function () {
	    		deleteFileFunc1('Doc');
	    	});
	    	$('#incyyteDeleteVideoButton').click(function () {
	    		deleteFileFunc1('Video');
	    	});
	    	
	    	if ($('#name').val() != '') {
	    		$("#incyyte_media_add").hide("fast");
	    		$("#incyyte_media_view").show("fast");

	    		if ($("#uploadedType").val() == 'Video') {
	    			$('#incyyte_video_file_name').text($('#name').val().substring(0,13).concat("..."));
	    			$('#view_video_file_name').text($('#name').val().substring(0,13).concat("..."));
	    		}
	    		else if ($("#uploadedType").val() == 'Image') {
	    			$('#incyyte_photo_file_name').text($('#name').val().substring(0,13).concat("..."));
	    			$('#view_photo_file_name').text($('#name').val().substring(0,13).concat("..."));
	    		}
	    		else if ($("#uploadedType").val() == 'Doc') {
	    			$('#incyyte_doc_file_name').text($('#name').val().substring(0,13).concat("..."));
	    			$('#view_doc_file_name').text($('#name').val().substring(0,13).concat("..."));
	    		}

	    	} else {
	    		$("#incyyte_media_add").show("fast");

	    		$('#incyyte_video_file_name').text('');
	    		$('#view_video_file_name').text('');

	    		$('#incyyte_photo_file_name').text('');
	    		$('#view_photo_file_name').text('');

	    		$('#incyyte_doc_file_name').text('');
	    		$('#view_doc_file_name').text('');
	    	}

	
	$("#addComment").click(function () {
		postComment();
	});
	
	$("#ok").click(function () {
		$("#messagetext").val("");
		$("#text").val("");
		parent.$.fn.colorbox.close();
 });
	//Used for adding images in comments section.	
	 $('#incyyte_browse_comments_photos').click(function () {
         $('#uploadCommentPhotoFile').click();
     });
	 $('#uploadCommentPhotoFile').change(function () {
		      $('#comments_photo_error_msg').hide("fast");
	        var pName = $(this).val().substring(12,25).concat("...");
	        $('#comments_photo_file_name').text(pName);
	        $('#view_comment_photo_file_name').text(pName);
	    }); 
	 $('#CommentPhotoButton').click(function () {
		  addPhotosToComments('Image');
	 });
	 $('#CommentVideoButton').click(function () {
			addPhotosToComments('Video');
	});
	
	 $('#view_small_icon').click(function () {
			parent.$.fn.colorbox({'href':'div#view_files', 'open':true, 'inline':true});
			removeActiveClass();
			var type = $("#type").val();
			if (type == 'Video') {
				$('#view_silvermember_photos').hide('fast');
				$('#view_docs').hide('fast');
				$('#view_videos').show('fast');
				$("#view_modal_videos > a").addClass('active');
			}
			else if (type == 'Image') {
				$('#view_videos').hide('fast');
				$('#view_docs').hide('fast');
				$('#view_silvermember_photos').show('fast');
				$("#view_modal_photos > a").addClass('active');
			}
			else if (type == 'Doc') {
				$('#view_videos').hide('fast');
				$('#view_silvermember_photos').hide('fast');
				$('#view_docs').show('fast');
				$("#view_modal_docs > a").addClass('active');
			}
		});
});

function downloadCSV(tableId){
	//alert("Table: "+ tableId );
	$('#'+tableId).table2CSV();	
}


function displayShareModalOnload(){
    var shareModal = document.getElementById("shrModal").value;
    var incyyteCode = document.getElementById("shrCode").value;
    //alert(shareModal + " " + incyyteCode );
	if(shareModal == 'share'){
		displaySharedPopup(incyyteCode);
	}
}

function postComment() {
    var comment = document.getElementById("textarea").value;
	comment = comment.replace(/’/g, "'");
    if(comment == '') {
        $("#error").css("display", "none");
        $("#error").text("Please enter your comment.");
        $("#error").css("display","");
        return;
    }
    $("#error").css("display", "none");
    var quesid = document.getElementById("questionid").value;
    $("#comment").val(comment);
    $("#quesid").val(quesid);
    var contextVar = document.getElementById("contextPathVar").value;
   
    $("#CommentsForm").ajaxSubmit({
        type: 'POST',
        url: '../addPollComments.cyt',
        success: function(data) {
        	 window.location.reload(true);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " exception:" + errorThrown);
        }
    });        
}

function removeActiveClass() {
	$("#view_modal_videos > a").removeClass('active');
	$("#view_modal_photos > a").removeClass('active');
	$("#view_modal_docs > a").removeClass('active');
}

function uploadFileFunc(fileType) {
		var filePath = '';
		var validFlag = true;

		$('#incyyte_video_error_msg2').hide("fast");
		$('#incyyte_photos_error_msg2').hide("fast");	
		$('#incyyte_doc_error_msg2').hide("fast");

		if (fileType == 'Video') {
			var youtubeVideoId = $("#youTubeVideoIdPromotion").val(); 

			if(youtubeVideoId == ""){
				filePath = $("#incyyte_video_file_input").val();
				var ext = $('#incyyte_video_file_input').val().split('.').pop().toLowerCase();
				if($.inArray(ext, ['flv','mp4','swf']) == -1) {
					$('#incyyte_video_error_msg').show("fast");
					validFlag = false;
				}
				else{
					$('#incyyte_video_error_msg').hide("fast");
					$("#videoLoader").ajaxStart(function(){
						$(this).show();
					}).ajaxStop(function(){
						$(this).hide();
					});
				}
			}else{
				//No validation for youtube Videos.
			}
		}
		else if(fileType == 'Image'){		
			filePath = $("#incyyte_photo_file_input").val();
			if (filePath == null || filePath == '') {
				filePath = document.getElementById("incyyte_photo_search_file").value;
				$('#searchedFileURL').val(document.getElementById("incyyte_photo_search_url").value);
			}
			if (filePath == null || filePath == '') {
				validFlag = false;
			}
			var ext = filePath.split('.').pop().toLowerCase();
			if($.inArray(ext, ['gif','png','jpg','jpeg','bmp']) == -1) {
				$('#incyyte_photos_error_msg').show("fast");		    		    
				validFlag = false;
			}
			else{
				$('#incyyte_photos_error_msg').hide("fast");
				$("#imageLoader").ajaxStart(function(){
					$(this).show();
				}).ajaxStop(function(){
					$(this).hide();
				});
			}
		}
		else if(fileType == 'Doc'){
			filePath = $("#incyyte_doc_file_input").val();
			var ext = $('#incyyte_doc_file_input').val().split('.').pop().toLowerCase();
			if($.inArray(ext, ['doc','docx','log','rtf','txt','wpd','wps','csv','pps','ppt','xml','xlr','xls','xlsx','pdf']) == -1) {
				$('#incyyte_doc_error_msg').show("fast");		    
				validFlag = false;
			}
			else{
				$('#incyyte_doc_error_msg').hide("fast");
				$("#docLoader").ajaxStart(function(){
					$(this).show();
				}).ajaxStop(function(){
					$(this).hide();
				});
			}
		}		
		if(validFlag){
			var contextVar = document.getElementById("contextPathVar").value;
			$('#file_name_tst').val(filePath);
			$('#searchedFileName').val(filePath);
			$("#uploadedType").val(fileType);
			$("#PollMesageForm").ajaxSubmit({
				type:'POST',		
				url: contextVar+'/dashdetailuploadlogo.cyt',	
				success: function(data) {
					if (data.indexOf("failure") != -1) {
						if(fileType == 'Video'){	
							$('#incyyte_video_error_msg2').show("fast");
						}
						else if(fileType == 'Image'){
							$('#incyyte_photos_error_msg2').show("fast");					
						}
						else{
							$('#incyyte_doc_error_msg2').show("fast");
						}
					}
					else{	
						data = data.replace(/&amp;/g, "&");
						var start=data.indexOf(">");
						var end=data.lastIndexOf("<");
						var filelocation ;
						if(start != -1 &&  end != -1)
							 filelocation=data.substring(start+1,end);
						else
							 filelocation=data;

						if(fileType == 'Video'){	
							$("#videolinkId").attr("href", filelocation);
							$("#addVideolink").attr("href", filelocation);

							$("#incyyte_video_not_loaded").hide("fast");
							$("#incyyte_video_loaded").show("fast");
							window.location = $('#pageName').val()+".cyt?openSection=openSilverMemOptions";						
							} else if(fileType == 'Image'){
							$("#photolinkId").attr("href", filelocation);
							$("#photoImg").attr("src", filelocation);

							$("#addPhotolink").attr("href", filelocation);
							$("#addPhotoImg").attr("src", filelocation);

							$("#incyyte_photo_not_loaded").hide();
							$("#incyyte_photo_loaded").show();
							window.location = $('#pageName').val()+".cyt?openSection=openSilverMemOptions";						
							}else{
							$("#doclinkId").attr("href", filelocation);
							$("#addDoclink").attr("href", filelocation);

							$("#incyyte_doc_not_loaded").hide();
							$("#incyyte_doc_loaded").show();
							window.location = $('#pageName').val()+".cyt?openSection=openSilverMemOptions";						
						$.fn.colorbox.close();	
					}
				}
				},
				error:function (jqXHR, textStatus, errorThrown) {
					if(fileType == 'Video'){
						$('#incyyte_video_error_msg2').show("fast");
					}
					else if(fileType == 'Image'){
						$('#incyyte_photos_error_msg2').show("fast");					
					}
					else{
						$('#incyyte_doc_error_msg2').show("fast");
					}
				}
			});
		}
		
	}
function deleteFileFunc1(fileType) {
	var contextVar = document.getElementById("contextPathVar").value;
	$("#PollMesageForm").ajaxSubmit({
		type:'POST',
		url:contextVar+'/deleteSilverMemeberuploadedfile.cyt',
		success:function (data) {
			$('#file_name_tst').val('');
			$("#uploadedType").val('');

			/*$("#incyyte_media_add").show();
			$("#incyyte_media_view").hide();*/

			$('#incyyte_video_file_name').text('');
			$('#view_video_file_name').text('');

			$('#incyyte_photo_file_name').text('');
			$('#view_photo_file_name').text('');

			$('#incyyte_doc_file_name').text('');
			$('#view_doc_file_name').text('');			

			$("#incyyte_video_not_loaded").show();
			$("#incyyte_video_loaded").hide();

			$("#incyyte_photo_not_loaded").show();
			$("#incyyte_photo_loaded").hide();

			$("#incyyte_doc_not_loaded").show();
			$("#incyyte_doc_loaded").hide();

			$.fn.colorbox.close();
			window.location =$('#pageName').val()+".cyt?openSection=openSilverMemOptions";
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});

}

function displaySharedPopup(incyyteCode){
	$("#incyyteCode").val(incyyteCode);
	$('#search_share_contacts').val('');
	parent.$.fn.colorbox({'href':'div#sharedEmailList', 'open':true, 'inline':true});
	sharedContactsJsonCall();
}

function addPhotosToComments(fileType) {
    var contextVar = document.getElementById("contextPathVar").value;

	if(fileType == "Image" ){

    $('#comments_photo_error_msg2').hide("fast");
    var filePath = $("#uploadCommentPhotoFile").val();
    if (filePath == null || filePath == '') {
        filePath = document.getElementById("incyyte_photo_search_file").value;
        $('#searchedFileNameComment').val(document.getElementById("incyyte_photo_search_file").value);
		$('#searchedFileURLComment').val(document.getElementById("incyyte_photo_search_url").value);

    }
    
    if (filePath == null || filePath == '') {
        return false;
    }
    var ext = filePath.split('.').pop().toLowerCase();
    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg', 'bmp']) == -1) {
        $("#comments_photo_error_msg").css("display", "");
        return false;
    } else {
        $('#comments_photo_error_msg').hide("fast");
        $("#commentsImageLoader").ajaxStart(
            function () {
                $(this).show();
            }).ajaxStop(function () {
                $(this).hide();
            });
    }
	} else if (fileType == "Video"){
		//For youtube Video Novlidation required
	}
    $("#uploadCommentType").val(fileType);
    $("#CommentsForm").ajaxSubmit({
        type:'POST',
        url:contextVar+'/uploadimageforcomment.cyt',
        success:function (data) {
            if (data.indexOf("failure") != -1) {
                $('#comments_photo_error_msg2').show("fast");
            }
            else {
                window.setTimeout(location.reload(true),1);
            }
        },
        error:function (jqXHR, textStatus, errorThrown) {
            $('#incyyte_photos_error_msg2').show("fast");
        }
    });
}


function  deleteCommentPhoto(commentId){
    $('#commentid').val(commentId);
	deleteCommentPhotos('Image');
}

function deleteCommentPhotos(fileType){
	 var contextVar = document.getElementById("contextPathVar").value;
	$("#CommentsForm").ajaxSubmit({
		type:'POST',
		url:contextVar+'/deleteCommentPhotos.cyt',
		success:function (data) {
            window.setTimeout(location.reload(true),1);
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}

function isfirstnameValid() {
	$('#firstNameError').css("display", "none");
	var FirstName = $("#firstName").val();
	var valid = true;
	var min = 3;
	var max = 20;
	var checkRegexp = /^[a-zA-Z]+$/;
	valid = checkLength($('#firstName'), "firstName", min, max);
	if($("#firstName").val() == ""){
		$('#firstNameError').text('First Name is required');
		$('#firstNameError').css("display", "");
		$('#firstNameError').css('color',"#ec3f41");
		return false;
	}
	if ($("#firstName").val() != "") {
		$('#firstNameError').css('color',"#ec3f41");
		if (!valid) {
			$('#firstNameError').text('Firstname must range between ' + min + ' and ' + max);
			$('#firstNameError').css("display", "");
			return false;
		}

		if (!isSpclCharValid(FirstName)) {
			$('#firstNameError').text('Following invalid characters @$&/_#%?+*^[]{}|`~<>!= are not allowed');
			$('#firstNameError').css("display", "");
			return false;
		}
		if (!checkRegexp.test(FirstName)) {
			$('#firstNameError').css("display", "none");
			$('#firstNameError').text('Alphabets only allowed');
			$('#firstNameError').css("display", "");
			return false;
		}
	}
	return true;
}

function isSpclCharValid(value) {
	var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
	var isValid = true;
	for (var i = 0; i < value.length; i++) {
		if (iChars.indexOf(value.charAt(i)) != '-1') {
			isValid = false;
			break;
			return isValid;
		} else {
			isValid = true;
		}
	}
	return isValid;
}

function islastnameValid() {
	$('#lastNameError').css("display", "none");
	var lastName = $("#lastName").val();
	var valid = true;
	var min = 3;
	var max = 20;
	var checkRegexp = /^[a-zA-Z]+$/;
	valid = checkLength($('#lastName'), "lastName", min, max);
	if ($("#lastName").val() != "") {
		$('#lastNameError').css('color',"#ec3f41");
		if (!valid) {
			$('#lastNameError').text('Firstname must range between ' + min + ' and ' + max);
			$('#lastNameError').css("display", "");
			return false;
		}
		if (!isSpclCharValid(lastName)) {
			$('#lastNameError').text('Following invalid characters @$&/_#%?+*^[]{}|`~<>!= are not allowed');
			$('#lastNameError').css("display", "");
			return false;
		}
		if (!checkRegexp.test(lastName)) {
			$('#lastNameError').css("display", "none");
			$('#lastNameError').text('Alphabets only allowed');
			$('#lastNameError').css("display", "");
			return false;
		}
	} else{
		if($("#lastName").val() == ""){
			$('#lastNameError').text('Last Name is required');
			$('#lastNameError').css("display", "");
			$('#lastNameError').css('color',"#ec3f41");
			return false;
		}
	}
	return true;
}

function isemailValid() {
	$('#emailError').css("display", "none");
	var email = $("#email").val();
	var checkRegexp = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
	var valid = true;
	if ($("#email").val() != "") {
		/*if (!checkRegexp.test(email)) {
			$('#emailError').text('Enter a valid email address');
			$('#emailError').css("display", "");
			$('#emailError').css('color',"#ec3f41");
			return false;
		}*/
	}else{
		if($("#email").val() == ""){
			$('#emailError').text('Email is required');
			$('#emailError').css("display", "");
			$('#emailError').css('color',"#ec3f41");
			return false;
		}
	}
	return true;
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

function checkLength(o, n, min, max) {
	if (o.val().length > max || o.val().length < min) {
		return false;
	} else {
		return true;
	}
}

function istextValid(){
	$('#textfeildError').css("display", "none");
	var text = $("#text").val();
	var valid = true;
	if($("#text").val() == ""){
		$('#textfeildError').text('text is required');
		$('#textfeildError').css("display", "");
		$('#textfeildError').css('color',"#ec3f41");
		return false;
	}
}

function sendinvite(){
	isfirstnameValid();
	islastnameValid();
	isemailValid();
	istextValid();
	var contextVar = document.getElementById("contextPathVar").value;
	$("#ConnectForm").ajaxSubmit({
		type: 'GET',
		url: contextVar + '/myPollContact.cyt',
		success:function (response) {
			if (response.search("success") == 0){
				$(".edit_pro_pop_txt").text("Your invitation has been sent");
				parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
				$('#cboxClose').remove();
			}else {
				$(".edit_pro_pop_txt").text("Your invitation has not sent.");
				parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
				$('#cboxClose').remove();
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
		}
	});
}

function messageValidation(message) {
    $("#textError").css("display", "none");
    var messageContent = message.value;
    var replytextLength = messageContent.length;
    if (!isSpclChar(messageContent)) {
        $("#textError").text("An invalid character has been entered /^&?+*[]{}\|`~<>%#$ are not allowed");
        $("#textError").css("display", "");
		$('#textError').css('color',"#ec3f41");
    }
    if (replytextLength > 2000) {
        $("#textError").text("Your Message has exceeded our 2000 characters limit. Please reduce content.");
        $("#textError").css("display", "");
		$('#textError').css('color',"#ec3f41");
    }
    if(messageContent == ''){
        $("#textError").text("Please enter the Message Text");
        $("#textError").css("display", "");
		$('#textError').css('color',"#ec3f41");
    }
}

function isSpclChar(messageText) {
    //Changed the restriction list of special characters as per bug: 201
    var iChars = "/^&?+*[]{}\|`~<>%#$";
    //var iChars = "/^+*\|`~<>%#";
    var isValid = true;
    for (var i = 0; i < messageText.length; i++) {
        if (iChars.indexOf(messageText.charAt(i)) != '-1') {
            isValid = false;
            break;
            return isValid;
        } else {
            isValid = true;
        }
    }
    return isValid;
}
function sendmesg(){
	var messagetext = $("#messagetext").val(); 
	messagetext= $.trim($('#messagetext').val());
	var contextVar = document.getElementById("contextPathVar").value;
	if (messagetext != '') {
		//If  messagetext contains any special character then it will display the below error message.
		var textLength = messagetext.length;
		if (textLength <= 2000) {
				if (isSpclChar(messagetext)) {
				$("#sendMsg").ajaxSubmit({
					type:'GET',
					url:contextVar + '/sendPollMessage.cyt',
					success:function (response) {
						if (response.search("success") == 0){
							$(".edit_pro_pop_txt").text("Your message has been sent");
							parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
							$('#cboxClose').remove();
						}else {
							$(".edit_pro_pop_txt").text("Your message has not sent.");
							parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
							$('#cboxClose').remove();
						}
					},
					error:function (jqXHR, textStatus, errorThrown) {
						alert("error:" + textStatus + " exception:" + errorThrown);
					}
				});
			} 
		}
	}
}