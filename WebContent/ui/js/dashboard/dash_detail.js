
// JavaScript Document
// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

// all startup logic should exist in the init function.
function document_onInit()
{
	 
	$("#videoLoader").css("display", "none");
	$("#imageLoader").css("display", "none");
	$("#docLoader").css("display", "none");
	
	 $('#incyyte_browse_videos').click(function () {
         $('#incyyte_video_file_input').click();
     });
     $('#incyyte_browse_photos').click(function () {
         $('#incyyte_photo_file_input').click();
     });
     $('#incyyte_browse_comments_photos').click(function () {
         $('#uploadCommentPhotoFile').click();
     });

     $('#incyyte_browse_docs').click(function () {
         $('#incyyte_doc_file_input').click();
     });
     
$("#searchSubmitContacts").click(function () {
     	sharedContactsJsonCall();
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

    $('#uploadCommentPhotoFile').change(function () {
        $('#comments_photo_error_msg').hide("fast");
        var pName = $(this).val().substring(12,25).concat("...");
        $('#comments_photo_file_name').text(pName);
        $('#view_comment_photo_file_name').text(pName);
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

$('#incyyteUploadVideoButton').click(function () {
	uploadFileFunc('Video');
});
$('#incyyteUploadPhotoButton').click(function () {
	uploadFileFunc('Image');
});
$('#CommentPhotoButton').click(function () {
	addPhotosToComments('Image');
});
$('#CommentVideoButton').click(function () {
	addPhotosToComments('Video');
});

$('#incyyteUploadDocButton').click(function () {
	uploadFileFunc('Doc');
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

//These array's are used to view the answer files. Also to show the answer file name.
images = ['gif','png','jpg','jpeg','bmp'];
videoExts = ['flv','mp4','mpg','swf','wmv'];
docExts = ['doc','docx','log','rtf','txt','wpd','wps','csv','pps','ppt','xml','xlr','xls','xlsx','pdf'];
ansDocs = ["wpd","wps","xml","xlr","pdf"];
ansGooleDocs = ['doc','docx','log','rtf','txt','csv','pps','ppt','xls','xlsx'];


$('#view_small_icon').click(function () {
	parent.$.fn.colorbox({'href':'div#view_files', 'open':true, 'inline':true});
	removeActiveClass();
	var type = $("#type").val();
	if (type == 'Video') {
		$('#view_photos').hide('fast');
		$('#view_docs').hide('fast');
		$('#view_videos').show('fast');

		$("#view_modal_videos > a").addClass('active');
	}
	else if (type == 'Image') {
		$('#view_videos').hide('fast');
		$('#view_docs').hide('fast');
		$('#view_photos').show('fast');

		$("#view_modal_photos > a").addClass('active');
	}
	else if (type == 'Doc') {
		$('#view_videos').hide('fast');
		$('#view_photos').hide('fast');
		$('#view_docs').show('fast');

		$("#view_modal_docs > a").addClass('active');
	}
});

// code for uploading the documents.

$("#openDocUploadwindow, #modal_docs, #modal_docs1 , #modal_docs2").click(function(){
    $.fn.colorbox({'href':'div#ModalDocWindow', 'open':true, 'inline':true});
});

$('#docs, #docs_small, #modal_docs').click(function () {
	removeActiveClass();
	$('#add_videos').hide('fast');
	$('#add_photos').hide('fast');
	$('#add_webpage').hide('fast');
	$('#add_docs').show('fast');

	$("#modal_docs > a").addClass('active');
});

//ends doc uploading  code
//this is for video uploading.
$("#openVideoUploadwindow, #modal_videos, #modal_videos1 , #modal_videos2").click(function(){
    $.fn.colorbox({'href':'div#VideoModalWindow', 'open':true, 'inline':true});
});
$('#videos_icon, #videos_small, #modal_videos').click(function () {
		removeActiveClass();

		$('#add_photos').hide('fast');
		$('#add_docs').hide('fast');
		$('#add_webpage').hide('fast');
		$('#add_videos').show('fast');

		$("#modal_videos > a").addClass('active');
	});
//ends video uploading  code
}

function downloadCSV(tableId){
	//alert("Table: "+ tableId );
	$('#'+tableId).table2CSV();	
}

function getXmlHttpRequestObject() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		alert("Your Browser Doesn't support AJAX!\\n Please upgrade your browser");
	}
}

var httpRequest = getXmlHttpRequestObject();
function sharedContactsJsonCall(){
	 var table = document.getElementById('contactsTable');
	 var rowCount = table.rows.length;
     for(var i=0; i<rowCount; i++) {
             table.deleteRow(i);
             rowCount--;
             i--;
     }
	 var param = $('#search_share_contacts').val();
	 var code = $('#code').val();
	 var contextPath = $('#contextPathVar').val();
    httpRequest.open("GET", contextPath+"/searchSharedContacts.cyt?param=" + param + "&code="+code  , true);
 	httpRequest.onreadystatechange = getSharedContactsAfterSearch;
 	httpRequest.send(null);
}

function getSharedContactsAfterSearch(){
	sharedContactsDiv = document.getElementById("contactsTable");
	if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
		var resultText = httpRequest.responseText;
		var contacts = json_parse(resultText);
		var contactList = contacts.contacts;
		for (var i = 0; i < contactList.length; i++) {
	
		var spanValue  = "";
		if(contactList[i].displayForCheckedCheckBox == "none" ){
			spanValue  =  "<span id='group_1'>" 
						+ '<input type="checkbox" style="display:' + contactList[i].displayForNormalCheckBox + '" class="contactList" name="selectedGroupContactsList" onclick="selectContact('+"'contactId'+" + contactList[i].contactIdValue +');"  id="contactId'+ contactList[i].contactIdValue +'"  value="' + contactList[i].contactEmail +'" /> ';
		}else if(contactList[i].displayForCheckedCheckBox == ""){
			spanValue  =  "<span id='group_1'>" 
						+ '<img alt="already received this incyyte" style="display:' + contactList[i].displayForCheckedCheckBox + '" src="ui/images/check_save.png" />';
		}
			
	sharedContactsDiv.innerHTML += "<tr>"
								   + '<td style="padding-left: 10px; padding-right: 10px;"> '
								   + spanValue
								   + '</span>'
								   + '</td>'
								   + '<td height="40" style="padding-right: 10px;">'
								   + '<img src="'+ contactList[i].profileImgUrl + '" width="36" height="35" alt="User Photo"></td>'
								   + '<td class="font_12px">'+ contactList[i].contactEmail + '</td>'
								   + '<td class="font_12px">'+ contactList[i].username + '</td>'
								   + "</tr>" ; 
		};
		$('#share_poll').show();
		if(contactList.length < 10) {
			$('#share_poll').hide();
		}
	};

}

function removeActiveClass() {
	$("#view_modal_videos > a").removeClass('active');
	$("#view_modal_photos > a").removeClass('active');
	$("#view_modal_docs > a").removeClass('active');
}

function addPhotosToComments(fileType) {
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

	}
    $("#uploadCommentType").val(fileType);
    $("#CommentsModel").ajaxSubmit({
        type:'POST',
        url:'uploadimageforcomment.cyt',
        success:function (data) {
            if (data.indexOf("failure") != -1) {
                $('#comments_photo_error_msg2').show("fast");
            } else if(fileType == 'Image') {
            	
				data = data.replace(/&amp;/g, "&");
				var start=data.indexOf(">");
				var end=data.lastIndexOf("<");
				var filelocation ;
				if(start != -1 &&  end != -1)
					 filelocation=data.substring(start+1,end);
				else
					 filelocation=data;

            	$("#photolinkId").attr("href", filelocation);
				$("#photoImg").attr("src", filelocation);
                window.setTimeout(location.reload(true),1);
            } else if (fileType == "Video"){
                window.setTimeout(location.reload(true),1);
            }
        },
        error:function (jqXHR, textStatus, errorThrown) {
            $('#incyyte_photos_error_msg2').show("fast");
        }
    });
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
			$('#file_name_tst').val(filePath);
			$('#searchedFileName').val(filePath);
			$("#uploadedType").val(fileType);
			$("#PollMesageForm").ajaxSubmit({
				type:'POST',		
				url: 'dashdetailuploadlogo.cyt',	
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
							window.location = "dashdetails.cyt?code="+$('#code').val()+"&from="+$('#from').val()+"&openSection=openSilverMemOptions";
						}
						else if(fileType == 'Image'){
							$("#photolinkId").attr("href", filelocation);
							$("#photoImg").attr("src", filelocation);

							$("#addPhotolink").attr("href", filelocation);
							$("#addPhotoImg").attr("src", filelocation);

							$("#incyyte_photo_not_loaded").hide();
							$("#incyyte_photo_loaded").show();
                            window.location = "dashdetails.cyt?code="+$('#code').val()+"&from="+$('#from').val()+"&openSection=openSilverMemOptions";
						}
						else{
							$("#doclinkId").attr("href", filelocation);
							$("#addDoclink").attr("href", filelocation);

							$("#incyyte_doc_not_loaded").hide();
							$("#incyyte_doc_loaded").show();
                            window.location = "dashdetails.cyt?code="+$('#code').val()+"&from="+$('#from').val()+"&openSection=openSilverMemOptions";
						}
						$.fn.colorbox.close();	
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
		$("#PollMesageForm").ajaxSubmit({
			type:'POST',
			url:'deleteSilverMemeberuploadedfile.cyt',
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
                window.location = "dashdetails.cyt?code="+$('#code').val()+"&from="+$('#from').val()+"&openSection=openSilverMemOptions";
			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});

	}
	
	function  deleteCommentPhoto(commentId , fileType){
        $('#commentid').val(commentId);
    	deleteCommentPhotos(fileType);
    }
	
	function deleteCommentPhotos(fileType){
		if(fileType = "Image"){
			
		} else if (fileType = "Video"){
		}
		$("#CommentsModel").ajaxSubmit({
			type:'POST',
			url:'deleteCommentPhotos.cyt',
			success:function (data) {
                window.setTimeout(location.reload(true),1);
			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});
	}
 
 