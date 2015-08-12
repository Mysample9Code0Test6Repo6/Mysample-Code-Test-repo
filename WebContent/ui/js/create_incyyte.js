$(document).ready(function () {
	toggleMail();
	document_onInit();
});
var incyyte_validator;
//all startup logic should exist in the init function.
function document_onInit() {
	var usn;
	var sumail;
	var pass;

	var maxOption = $("#answerMaxOption").val();
	var ansCount = $("#answer_count").val();

	if (ansCount  <= 10) {
		$("#add_an_answer").show();
	}
	if (ansCount  >= 10) {
		$("#add_an_answer").hide("slow");
	}

	$("#videoLoader").css("display", "none");
	$("#imageLoader").css("display", "none");
	$("#docLoader").css("display", "none");

	$("#ansVideoLoader_1").css("display", "none");
	$("#ansPhotoLoader_1").css("display", "none");
	$("#ansDocLoader_1").css("display", "none");

	$("#ansVideoLoader_2").css("display", "none");
	$("#ansPhotoLoader_2").css("display", "none");
	$("#ansDocLoader_2").css("display", "none");

	$("#ansVideoLoader_3").css("display", "none");
	$("#ansPhotoLoader_3").css("display", "none");
	$("#ansDocLoader_3").css("display", "none");

	$("#ansVideoLoader_4").css("display", "none");
	$("#ansPhotoLoader_4").css("display", "none");
	$("#ansDocLoader_4").css("display", "none");

	$("#ansVideoLoader_5").css("display", "none");
	$("#ansPhotoLoader_5").css("display", "none");
	$("#ansDocLoader_5").css("display", "none");

	$("#ansVideoLoader_6").css("display", "none");
	$("#ansPhotoLoader_6").css("display", "none");
	$("#ansDocLoader_6").css("display", "none");

	$("#ansVideoLoader_7").css("display", "none");
	$("#ansPhotoLoader_7").css("display", "none");
	$("#ansDocLoader_7").css("display", "none");

	$("#ansVideoLoader_8").css("display", "none");
	$("#ansPhotoLoader_8").css("display", "none");
	$("#ansDocLoader_8").css("display", "none");


	$("#ansVideoLoader_9").css("display", "none");
	$("#ansPhotoLoader_9").css("display", "none");
	$("#ansDocLoader_9").css("display", "none");


	$("#ansVideoLoader_10").css("display", "none");
	$("#ansPhotoLoader_10").css("display", "none");
	$("#ansDocLoader_10").css("display", "none");
	
	$("#searchSubmitContacts").click(function () {
     	sharedContactsJsonCall();
});

	$("#close_sent_dialog").click(function(){
		$.fn.colorbox.close();
	});

	$('#grpId').change(function() {
		$('#grpName').val($('option:selected',this).text());		
	});

	$('#uploadButton').click(function () {
		uploadLogo();
	});
	$("#uploadAnotherButton").click(function () {
		displayNotLoad();
	});

	$('#previewSendInCyyte').click(function () {
		parent.$.fn.colorbox.close();

		incyyte_validator.form();
		if($("#answerChoice1").val()==''){
			$("#answerChoice1").val("Yes");
		}
		if($("#answerChoice2").val()==''){
			$("#answerChoice2").val("No");
		}
		if($("#answerChoice3").val()==''){
			$("#answerChoice3").val("Not Sure");
		}
		validateAns();
		validateCategory();
		if($("#inCyyteForm").valid()&&validateAns()&&validateCategory()&& validateGroupSel() && validateRegion()){
			//THIS MUST NEVER SEND THE INCYYTE, DIALOG SHOULD JUST CLOSE AFTER PREVIEW
			//sendInCyyte();
		}
	});
	
	$("#okButton").click(function () {		
		parent.$.fn.colorbox.close();
	});
	
	
	$('#checkbox-1').change(function () {
        if ($(this).is(':checked')) {
        	if ($('#post').attr('checked') === 'checked') {
    		    $(".edit_pro_pop_txt").text("Incyyte polls that are attached to exclusive poll pages cannot be set as anonymous. Please deselect anonymous checkbox to attach this poll");
    			parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
    		}
        }
	 });

	$('#modal_datepicker_btn').click(function () {
		closeDatePicker();
	});

	var cDateTime = $('#closingDateVal').text();	
	if(cDateTime != null){
		var dArry = cDateTime.split(' ');		
		$('#selecttime').val(dArry[1]);
	}

	$('#send_your_poll_top').click(function(){
		sendYourPoll(); 
	});
	$('#poll_settings_left').click(function(){
		pollSetting(); 
	});
	$('#pollResultHidden_settings').click(function(){
		pollSetting(); 
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

	$('#incyyteDeleteVideoButton').click(function () {
		deleteFileFunc('Video');
	});
	$('#incyyteDeletePhotoButton').click(function () {
		deleteFileFunc('image');
	});
	$('#incyyteDeleteDocButton').click(function () {
		deleteFileFunc('Doc');
	});

	$('#ansDeleteVideoButton1').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton1').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton1').click(function () {
		ansDeleteFileFunc('Doc');
	});
	$('#ansDeleteVideoButton2').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton2').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton2').click(function () {
		ansDeleteFileFunc('Doc');
	});
	$('#ansDeleteVideoButton3').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton3').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton3').click(function () {
		ansDeleteFileFunc('Doc');
	});
	$('#ansDeleteVideoButton4').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton4').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton4').click(function () {
		ansDeleteFileFunc('Doc');
	});
	$('#ansDeleteVideoButton5').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton5').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton5').click(function () {
		ansDeleteFileFunc('Doc');
	});
	$('#ansDeleteVideoButton6').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton6').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton6').click(function () {
		ansDeleteFileFunc('Doc');
	});
	$('#ansDeleteVideoButton7').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton7').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton7').click(function () {
		ansDeleteFileFunc('Doc');
	});
	$('#ansDeleteVideoButton8').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton8').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton8').click(function () {
		ansDeleteFileFunc('Doc');
	});

	$('#ansDeleteVideoButton9').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton9').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton9').click(function () {
		ansDeleteFileFunc('Doc');
	});


	$('#ansDeleteVideoButton10').click(function () {
		ansDeleteFileFunc('Video');
	});
	$('#ansDeletePhotoButton10').click(function () {
		ansDeleteFileFunc('image');
	});
	$('#ansDeleteDocButton10').click(function () {
		ansDeleteFileFunc('Doc');
	});

	$('#ansUploadVideoButton1').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton1').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton1').click(function () {
		uploadAnsFileFunc('Doc');
	});
	$('#ansUploadVideoButton2').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton2').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton2').click(function () {
		uploadAnsFileFunc('Doc');
	});
	$('#ansUploadVideoButton3').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton3').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton3').click(function () {
		uploadAnsFileFunc('Doc');
	});
	$('#ansUploadVideoButton4').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton4').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton4').click(function () {
		uploadAnsFileFunc('Doc');
	});
	$('#ansUploadVideoButton5').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton5').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton5').click(function () {
		uploadAnsFileFunc('Doc');
	});
	$('#ansUploadVideoButton6').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton6').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton6').click(function () {
		uploadAnsFileFunc('Doc');
	});
	$('#ansUploadVideoButton7').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton7').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton7').click(function () {
		uploadAnsFileFunc('Doc');
	});

	$('#ansUploadVideoButton8').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton8').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton8').click(function () {
		uploadAnsFileFunc('Doc');
	});


	$('#ansUploadVideoButton9').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton9').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton9').click(function () {
		uploadAnsFileFunc('Doc');
	});

	$('#ansUploadVideoButton10').click(function () {
		uploadAnsFileFunc('Video');
	});
	$('#ansUploadPhotoButton10').click(function () {
		uploadAnsFileFunc('Image');
	});
	$('#ansUploadDocButton10').click(function () {
		uploadAnsFileFunc('Doc');
	});

	$('#locality').change(function () {
		toggleLocalityFields();
		countMembers();
	});
	$('#filterAgeMin').change(function () {
		countMembers();
	});
	$('#filterAgeMax').change(function () {
		countMembers();
	});
	$('#filterGender').change(function () {
		countMembers();
	});
	$('#postcodex').change(function () {
		countMembers();
	});
	$('#Neighbourhood').change(function () {
		countMembers();
	});
	$('#county').change(function () {
		countMembers();
	});

	$('#mail').change(function () {
		toggleMail();
	});
	$('#post').change(function () {
		toggleMail();
	});
	$('#area').change(function () {
		toggleMail();
	});

	$("#sports").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Sports');
		countMembers();
	});
	$("#music").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Music');
		countMembers();
	});
	$("#religion").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Religion');
		countMembers();
	});
	$("#politics").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Politics');
		countMembers();
	});
	$("#community").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Community');
		countMembers();
	});
	$("#relationships").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Relationships');
		countMembers();
	});
	$("#business").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Business');
		countMembers();
	});
	$("#shopping").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Shopping');
		countMembers();
	});
	$("#health").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Health');
		countMembers();
	});
	$("#beauty").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Beauty');
		countMembers();
	});
	$("#travel").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Travel');
		countMembers();
	});
	$("#food").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Food');
		countMembers();
	});
	$("#motoring").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Motoring');
		countMembers();
	});
	$("#internet").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Internet');
		countMembers();
	});
	$("#entertainment").click(function () {
		deselectCategory();
		// Make currently clicked active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Entertainment');
		countMembers();
	});
	$("#others").click(function () {
		deselectCategory();
		// Make currently clicked  active
		$(this).css("background-image", "url(ui/images/category_button3.png)");
		$("#category").val('Others');
		countMembers();
	});


	$('#incyyte_video_file_input').change(function() {
		$('#incyyte_video_error_msg').hide("fast");
		$('#incyyte_photos_error_msg').hide("fast");
		$('#incyyte_doc_error_msg').hide("fast");

		var vName = $(this).val().substring(0,25).concat("...");
		$('#incyyte_video_file_name').text(vName);
		$('#view_video_file_name').text(vName);		
	});

	$('#incyyte_photo_file_input').change(function () {
		$('#incyyte_video_error_msg').hide("fast");
		$('#incyyte_photos_error_msg').hide("fast");
		$('#incyyte_doc_error_msg').hide("fast");
		var pName = $(this).val().substring(0,25).concat("...");
		$('#incyyte_photo_file_name').text(pName);
		$('#view_photo_file_name').text(pName);
	});

	$('#incyyte_doc_file_input').change(function () {
		$('#incyyte_video_error_msg').hide("fast");
		$('#incyyte_photos_error_msg').hide("fast");
		$('#incyyte_doc_error_msg').hide("fast");
		var dName = $(this).val().substring(0,25).concat("...");
		$('#incyyte_doc_file_name').text(dName);
		$('#view_doc_file_name').text(dName);
	});

	$('#answer_file_video_1').change(function () {
		$('#incyyte_ans_video_error_msg_1').hide("fast");
		$('#incyyte_ans_image_error_msg_1').hide("fast");
		$('#incyyte_ans_doc_error_msg_1').hide("fast");
		var v1Name = $(this).val().substring(12,25).concat("...");
		$('#ans_video_file_name_1').text(v1Name);
		$('#view_ans_video_file_name_1').text(v1Name);
	});
	$('#answer_file_photo_1').change(function () {
		$('#incyyte_ans_video_error_msg_1').hide("fast");
		$('#incyyte_ans_image_error_msg_1').hide("fast");
		$('#incyyte_ans_doc_error_msg_1').hide("fast");
		var p1Name = $(this).val().substring(12,25).concat("...");
		$('#ans_photo_file_name_1').text(p1Name);
		$('#view_ans_photo_file_name_1').text(p1Name);
	});
	$('#answer_file_doc_1').change(function () {
		$('#incyyte_ans_video_error_msg_1').hide("fast");
		$('#incyyte_ans_image_error_msg_1').hide("fast");
		$('#incyyte_ans_doc_error_msg_1').hide("fast");
		var d1Name = $(this).val().substring(12,25).concat("...");
		$('#ans_doc_file_name_1').text(d1Name);
		$('#view_ans_doc_file_name_1').text(d1Name);
	});

	$('#answer_file_video_2').change(function () {
		$('#incyyte_ans_video_error_msg_2').hide("fast");
		$('#incyyte_ans_image_error_msg_2').hide("fast");
		$('#incyyte_ans_doc_error_msg_2').hide("fast");
		var v2Name = $(this).val().substring(12,25).concat("...");
		$('#ans_video_file_name_2').text(v2Name);
		$('#view_ans_video_file_name_2').text(v2Name);
	});
	$('#answer_file_photo_2').change(function () {
		$('#incyyte_ans_video_error_msg_2').hide("fast");
		$('#incyyte_ans_image_error_msg_2').hide("fast");
		$('#incyyte_ans_doc_error_msg_2').hide("fast");
		var p2Name = $(this).val().substring(12,25).concat("...");
		$('#ans_photo_file_name_2').text(p2Name);
		$('#view_ans_photo_file_name_2').text(p2Name);
	});
	$('#answer_file_doc_2').change(function () {
		$('#incyyte_ans_video_error_msg_2').hide("fast");
		$('#incyyte_ans_image_error_msg_2').hide("fast");
		$('#incyyte_ans_doc_error_msg_2').hide("fast");
		var d2Name = $(this).val().substring(12,25).concat("...");
		$('#ans_doc_file_name_2').text(d2Name);
		$('#view_ans_doc_file_name_2').text(d2Name);
	});

	$('#answer_file_video_3').change(function () {
		$('#incyyte_ans_video_error_msg_3').hide("fast");
		$('#incyyte_ans_image_error_msg_3').hide("fast");
		$('#incyyte_ans_doc_error_msg_3').hide("fast");
		var v3Name = $(this).val().substring(12,25).concat("...");
		$('#ans_video_file_name_3').text(v3Name);
		$('#view_ans_video_file_name_3').text(v3Name);
	});
	$('#answer_file_photo_3').change(function () {
		$('#incyyte_ans_video_error_msg_3').hide("fast");
		$('#incyyte_ans_image_error_msg_3').hide("fast");
		$('#incyyte_ans_doc_error_msg_3').hide("fast");
		var p3Name = $(this).val().substring(12,25).concat("...");
		$('#ans_photo_file_name_3').text(p3Name);
		$('#view_ans_photo_file_name_3').text(p3Name);
	});
	$('#answer_file_doc_3').change(function () {
		$('#incyyte_ans_video_error_msg_3').hide("fast");
		$('#incyyte_ans_image_error_msg_3').hide("fast");
		$('#incyyte_ans_doc_error_msg_3').hide("fast");
		var d3Name = $(this).val().substring(12,25).concat("...");
		$('#ans_doc_file_name_3').text(d3Name);
		$('#view_ans_doc_file_name_3').text(d3Name);
	});

	$('#answer_file_video_4').change(function () {
		$('#incyyte_ans_video_error_msg_4').hide("fast");
		$('#incyyte_ans_image_error_msg_4').hide("fast");
		$('#incyyte_ans_doc_error_msg_4').hide("fast");
		var v4Name = $(this).val().substring(12,25).concat("...");

		$('#ans_video_file_name_4').text(v4Name);
		$('#view_ans_video_file_name_4').text(v4Name);
	});
	$('#answer_file_photo_4').change(function () {
		$('#incyyte_ans_video_error_msg_4').hide("fast");
		$('#incyyte_ans_image_error_msg_4').hide("fast");
		$('#incyyte_ans_doc_error_msg_4').hide("fast");
		var p4Name = $(this).val().substring(12,25).concat("...");

		$('#ans_photo_file_name_4').text(p4Name);
		$('#view_ans_photo_file_name_4').text(p4Name);
	});
	$('#answer_file_doc_4').change(function () {
		$('#incyyte_ans_video_error_msg_4').hide("fast");
		$('#incyyte_ans_image_error_msg_4').hide("fast");
		$('#incyyte_ans_doc_error_msg_4').hide("fast");
		var d4Name = $(this).val().substring(12,25).concat("...");

		$('#ans_doc_file_name_4').text(d4Name);
		$('#view_ans_doc_file_name_4').text(d4Name);
	});

	$('#answer_file_video_5').change(function () {
		$('#incyyte_ans_video_error_msg_5').hide("fast");
		$('#incyyte_ans_image_error_msg_5').hide("fast");
		$('#incyyte_ans_doc_error_msg_5').hide("fast");
		var v5Name = $(this).val().substring(12,25).concat("...");

		$('#ans_video_file_name_5').text(v5Name);
		$('#view_ans_video_file_name_5').text(v5Name);
	});
	$('#answer_file_photo_5').change(function () {
		$('#incyyte_ans_video_error_msg_5').hide("fast");
		$('#incyyte_ans_image_error_msg_5').hide("fast");
		$('#incyyte_ans_doc_error_msg_5').hide("fast");
		var p5Name = $(this).val().substring(12,25).concat("...");

		$('#ans_photo_file_name_5').text(p5Name);
		$('#view_ans_photo_file_name_5').text(p5Name);
	});
	$('#answer_file_doc_5').change(function () {
		$('#incyyte_ans_video_error_msg_5').hide("fast");
		$('#incyyte_ans_image_error_msg_5').hide("fast");
		$('#incyyte_ans_doc_error_msg_5').hide("fast");
		var d5Name = $(this).val().substring(12,25).concat("...");

		$('#ans_doc_file_name_5').text(d5Name);
		$('#view_ans_doc_file_name_5').text(d5Name);
	});

	$('#answer_file_video_6').change(function () {
		$('#incyyte_ans_video_error_msg_6').hide("fast");
		$('#incyyte_ans_image_error_msg_6').hide("fast");
		$('#incyyte_ans_doc_error_msg_6').hide("fast");
		var v6Name = $(this).val().substring(12,25).concat("...");

		$('#ans_video_file_name_6').text(v6Name);
		$('#view_ans_video_file_name_6').text(v6Name);
	});
	$('#answer_file_photo_6').change(function () {
		$('#incyyte_ans_video_error_msg_6').hide("fast");
		$('#incyyte_ans_image_error_msg_6').hide("fast");
		$('#incyyte_ans_doc_error_msg_6').hide("fast");
		var p6Name = $(this).val().substring(12,25).concat("...");

		$('#ans_photo_file_name_6').text(p6Name);
		$('#view_ans_photo_file_name_6').text(p6Name);
	});
	$('#answer_file_doc_6').change(function () {
		$('#incyyte_ans_video_error_msg_6').hide("fast");
		$('#incyyte_ans_image_error_msg_6').hide("fast");
		$('#incyyte_ans_doc_error_msg_6').hide("fast");
		var d6Name = $(this).val().substring(12,25).concat("...");

		$('#ans_doc_file_name_6').text(d6Name);
		$('#view_ans_doc_file_name_6').text(d6Name);
	});

	$('#answer_file_video_7').change(function () {
		$('#incyyte_ans_video_error_msg_7').hide("fast");
		$('#incyyte_ans_image_error_msg_7').hide("fast");
		$('#incyyte_ans_doc_error_msg_7').hide("fast");
		var v7Name = $(this).val().substring(12,25).concat("...");

		$('#ans_video_file_name_7').text(v7Name);
		$('#view_ans_video_file_name_7').text(v7Name);
	});
	$('#answer_file_photo_7').change(function () {
		$('#incyyte_ans_video_error_msg_7').hide("fast");
		$('#incyyte_ans_image_error_msg_7').hide("fast");
		$('#incyyte_ans_doc_error_msg_7').hide("fast");
		var p7Name = $(this).val().substring(12,25).concat("...");

		$('#ans_photo_file_name_7').text(p7Name);
		$('#view_ans_photo_file_name_7').text(p7Name);
	});
	$('#answer_file_doc_7').change(function () {
		$('#incyyte_ans_video_error_msg_7').hide("fast");
		$('#incyyte_ans_image_error_msg_7').hide("fast");
		$('#incyyte_ans_doc_error_msg_7').hide("fast");
		var d7Name = $(this).val().substring(12,25).concat("...");

		$('#ans_doc_file_name_7').text(d7Name);
		$('#view_ans_doc_file_name_7').text(d7Name);
	});

	$('#answer_file_video_8').change(function () {
		$('#incyyte_ans_video_error_msg_8').hide("fast");
		$('#incyyte_ans_image_error_msg_8').hide("fast");
		$('#incyyte_ans_doc_error_msg_8').hide("fast");
		var v8Name = $(this).val().substring(12,25).concat("...");

		$('#ans_video_file_name_8').text(v8Name);
		$('#view_ans_video_file_name_8').text(v8Name);
	});
	$('#answer_file_photo_8').change(function () {
		$('#incyyte_ans_video_error_msg_8').hide("fast");
		$('#incyyte_ans_image_error_msg_8').hide("fast");
		$('#incyyte_ans_doc_error_msg_8').hide("fast");
		var p8Name = $(this).val().substring(12,25).concat("...");

		$('#ans_photo_file_name_8').text(p8Name);
		$('#view_ans_photo_file_name_8').text(p8Name);
	});
	$('#answer_file_doc_8').change(function () {
		$('#incyyte_ans_video_error_msg_8').hide("fast");
		$('#incyyte_ans_image_error_msg_8').hide("fast");
		$('#incyyte_ans_doc_error_msg_8').hide("fast");

		var d8Name = $(this).val().substring(12,25).concat("...");

		$('#ans_doc_file_name_8').text(d8Name);
		$('#view_ans_doc_file_name_8').text(d8Name);
	});

	$('#answer_file_video_9').change(function () {
		$('#incyyte_ans_video_error_msg_9').hide("fast");
		$('#incyyte_ans_image_error_msg_9').hide("fast");
		$('#incyyte_ans_doc_error_msg_9').hide("fast");
		var v9Name = $(this).val().substring(12,25).concat("...");

		$('#ans_video_file_name_9').text(v9Name);
		$('#view_ans_video_file_name_9').text(v9Name);
	});
	$('#answer_file_photo_9').change(function () {
		$('#incyyte_ans_video_error_msg_9').hide("fast");
		$('#incyyte_ans_image_error_msg_9').hide("fast");
		$('#incyyte_ans_doc_error_msg_9').hide("fast");
		var p9Name = $(this).val().substring(12,25).concat("...");

		$('#ans_photo_file_name_9').text(p9Name);
		$('#view_ans_photo_file_name_9').text(p9Name);
	});
	$('#answer_file_doc_9').change(function () {
		$('#incyyte_ans_video_error_msg_9').hide("fast");
		$('#incyyte_ans_image_error_msg_9').hide("fast");
		$('#incyyte_ans_doc_error_msg_9').hide("fast");
		var d9Name = $(this).val().substring(12,25).concat("...");

		$('#ans_doc_file_name_9').text(d9Name);
		$('#view_ans_doc_file_name_9').text(d9Name);
	});
	$('#answer_file_video_10').change(function () {
		$('#incyyte_ans_video_error_msg_10').hide("fast");
		$('#incyyte_ans_image_error_msg_10').hide("fast");
		$('#incyyte_ans_doc_error_msg_10').hide("fast");
		var v10Name = $(this).val().substring(12,25).concat("...");

		$('#ans_video_file_name_10').text(v10Name);
		$('#view_ans_video_file_name_10').text(v10Name);
	});
	$('#answer_file_photo_10').change(function () {
		$('#incyyte_ans_video_error_msg_10').hide("fast");
		$('#incyyte_ans_image_error_msg_10').hide("fast");
		$('#incyyte_ans_doc_error_msg_10').hide("fast");
		var p10Name = $(this).val().substring(12,25).concat("...");

		$('#ans_photo_file_name_10').text(p10Name);
		$('#view_ans_photo_file_name_10').text(p10Name);
	});
	$('#answer_file_doc_10').change(function () {
		$('#incyyte_ans_video_error_msg_10').hide("fast");
		$('#incyyte_ans_image_error_msg_10').hide("fast");
		$('#incyyte_ans_doc_error_msg_10').hide("fast");
		var d10Name = $(this).val().substring(12,25).concat("...");

		$('#ans_doc_file_name_10').text(d10Name);
		$('#view_ans_doc_file_name_10').text(d10Name);
	});

	$('#videos_icon, #videos_small, #modal_videos').click(function () {
		removeActiveClass();
		$('#add_photos').hide('fast');
		$('#add_docs').hide('fast');
		$('#add_webpage').hide('fast');
		$('#add_videos').show('fast');
        $('#search_new_question_videos').val($('#myTextarea3').val());
        $('#googleVideosScroll').html("");
        makeYoutubeApiCall('search_new_question_videos' , 'start-index' , 'googleVideosScroll' , 'quesVideoSelect' );
        $("#modal_videos > a").addClass('active');
	});
	$('#photos, #photos_small, #modal_photos').click(function () {
		removeActiveClass();
		$('#search_new').val($('#myTextarea3').val());
		// To perform google search with question text
    	$('#googleSearchTrigger').val(1);
		var searchValue = document.getElementById("search_new");
		var searchLoad = document.getElementById("googleImagesScroll");
		var searchSelect = "quesImgSelect";
		loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		$('#add_videos').hide('fast');
		$('#add_docs').hide('fast');
		$('#add_webpage').hide('fast');
		$('#add_photos').show('fast');

		$("#modal_photos > a").addClass('active');
	});

	$('#docs, #docs_small, #modal_docs').click(function () {
		removeActiveClass();

		$('#add_videos').hide('fast');
		$('#add_photos').hide('fast');
		$('#add_webpage').hide('fast');
		$('#add_docs').show('fast');

		$("#modal_docs > a").addClass('active');
	});
	$('#url_small, #modal_links').click(function () {
		removeActiveClass();

		$('#add_videos').hide('fast');
		$('#add_photos').hide('fast');
		$('#add_docs').hide('fast');
		$('#add_webpage').show('fast');

		$("#modal_links > a").addClass('active');
	});


	$('#modal_videos_1, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
		removeActiveClassFor1();

		$("#youTubeAnswer_1_VideoId").val('');
		$("#ansVideoSelect_1").val('');

		$('#add_photos_1').hide('fast');
		$('#add_docs_1').hide('fast');
		$('#add_webpage_1').hide('fast');
		$('#add_videos_1').show('fast');

		$("#modal_videos_1 > a").addClass('active');
	});
	$('#modal_photos_1, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		removeActiveClassFor1();
		$('#add_videos_1').hide('fast');
		$('#add_docs_1').hide('fast');
		$('#add_webpage_1').hide('fast');
		$('#add_photos_1').show('fast');

		$("#modal_photos_1 > a").addClass('active');
	});

	$('#modal_docs_1, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor1();

		$('#add_videos_1').hide('fast');
		$('#add_photos_1').hide('fast');
		$('#add_webpage_1').hide('fast');
		$('#add_docs_1').show('fast');

		$("#modal_docs_1 > a").addClass('active');
	});

	$('#modal_links_1, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor1();

		$('#add_videos_1').hide('fast');
		$('#add_photos_1').hide('fast');
		$('#add_docs_1').hide('fast');
		$('#add_webpage_1').show('fast');

		$("#modal_links_1 > a").addClass('active');
	});


	$('#modal_videos_2, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
        removeActiveClassFor2();
		
		$("#youTubeAnswer_2_VideoId").val('');
		$("#ansVideoSelect_2").val('');
		
		$('#add_photos_2').hide('fast');
		$('#add_docs_2').hide('fast');
		$('#add_webpage_2').hide('fast');
		$('#add_videos_2').show('fast');

		$("#modal_videos_2 > a").addClass('active');
	});
	$('#modal_photos_2, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		$('#googleSearchTrigger').val(1);
		removeActiveClassFor2();

		$('#add_videos_2').hide('fast');
		$('#add_docs_2').hide('fast');
		$('#add_webpage_2').hide('fast');
		$('#add_photos_2').show('fast');

		$("#modal_photos_2 > a").addClass('active');
	});

	$('#modal_docs_2, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor2();

		$('#add_videos_2').hide('fast');
		$('#add_photos_2').hide('fast');
		$('#add_webpage_2').hide('fast');
		$('#add_docs_2').show('fast');

		$("#modal_docs_2 > a").addClass('active');
	});
	$('#modal_links_2, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor2();

		$('#add_videos_2').hide('fast');
		$('#add_photos_2').hide('fast');
		$('#add_docs_2').hide('fast');
		$('#add_webpage_2').show('fast');

		$("#modal_links_2 > a").addClass('active');
	});


	$('#modal_videos_3, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
		removeActiveClassFor3();
		
		$("#youTubeAnswer_3_VideoId").val('');
		$("#ansVideoSelect_3").val('');
		
		$('#add_photos_3').hide('fast');
		$('#add_docs_3').hide('fast');
		$('#add_webpage_3').hide('fast');
		$('#add_videos_3').show('fast');
		$("#modal_videos_3 > a").addClass('active');
	});
	$('#modal_photos_3, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		removeActiveClassFor3();
		$('#add_videos_3').hide('fast');
		$('#add_docs_3').hide('fast');
		$('#add_webpage_3').hide('fast');
		$('#add_photos_3').show('fast');
		$("#modal_photos_3 > a").addClass('active');
	});
	$('#modal_docs_3, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor3();
		$('#add_videos_3').hide('fast');
		$('#add_photos_3').hide('fast');
		$('#add_webpage_3').hide('fast');
		$('#add_docs_3').show('fast');
		$("#modal_docs_3 > a").addClass('active');
	});
	$('#modal_links_3, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor3();
		$('#add_videos_3').hide('fast');
		$('#add_photos_3').hide('fast');
		$('#add_docs_3').hide('fast');
		$('#add_webpage_3').show('fast');
		$("#modal_links_3 > a").addClass('active');
	});

	$('#modal_videos_4, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
		removeActiveClassFor4();
		
		$("#youTubeAnswer_4_VideoId").val('');
		$("#ansVideoSelect_4").val('');
		
		
		$('#add_photos_4').hide('fast');
		$('#add_docs_4').hide('fast');
		$('#add_webpage_4').hide('fast');
		$('#add_videos_4').show('fast');
		$("#modal_videos_4 > a").addClass('active');
	});
	$('#modal_photos_4, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		removeActiveClassFor4();
		$('#add_videos_4').hide('fast');
		$('#add_docs_4').hide('fast');
		$('#add_webpage_4').hide('fast');
		$('#add_photos_4').show('fast');
		$("#modal_photos_4 > a").addClass('active');
	});
	$('#modal_docs_4, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor4();
		$('#add_videos_4').hide('fast');
		$('#add_photos_4').hide('fast');
		$('#add_webpage_4').hide('fast');
		$('#add_docs_4').show('fast');
		$("#modal_docs_4 > a").addClass('active');
	});
	$('#modal_links_4, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor4();
		$('#add_videos_4').hide('fast');
		$('#add_photos_4').hide('fast');
		$('#add_docs_4').hide('fast');
		$('#add_webpage_4').show('fast');
		$("#modal_links_4 > a").addClass('active');
	});

	$('#modal_videos_5, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
		removeActiveClassFor5();
		
		$("#youTubeAnswer_5_VideoId").val('');
		$("#ansVideoSelect_5").val('');
		
		
		$('#add_photos_5').hide('fast');
		$('#add_docs_5').hide('fast');
		$('#add_webpage_5').hide('fast');
		$('#add_videos_5').show('fast');
		$("#modal_videos_5 > a").addClass('active');
	});
	$('#modal_photos_5, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		removeActiveClassFor5();
		$('#add_videos_5').hide('fast');
		$('#add_docs_5').hide('fast');
		$('#add_webpage_5').hide('fast');
		$('#add_photos_5').show('fast');
		$("#modal_photos_5 > a").addClass('active');
	});
	$('#modal_docs_5, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor5();
		$('#add_videos_5').hide('fast');
		$('#add_photos_5').hide('fast');
		$('#add_webpage_5').hide('fast');
		$('#add_docs_5').show('fast');
		$("#modal_docs_5 > a").addClass('active');
	});
	$('#modal_links_5, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor5();
		$('#add_videos_5').hide('fast');
		$('#add_photos_5').hide('fast');
		$('#add_docs_5').hide('fast');
		$('#add_webpage_5').show('fast');
		$("#modal_links_5 > a").addClass('active');
	});

	$('#modal_videos_6, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
		removeActiveClassFor6();
		
		$("#youTubeAnswer_6_VideoId").val('');
		$("#ansVideoSelect_6").val('');
		
		
		$('#add_photos_6').hide('fast');
		$('#add_docs_6').hide('fast');
		$('#add_webpage_6').hide('fast');
		$('#add_videos_6').show('fast');
		$("#modal_videos_6 > a").addClass('active');
	});
	$('#modal_photos_6, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		removeActiveClassFor6();
		$('#add_videos_6').hide('fast');
		$('#add_docs_6').hide('fast');
		$('#add_webpage_6').hide('fast');
		$('#add_photos_6').show('fast');
		$("#modal_photos_6 > a").addClass('active');
	});
	$('#modal_docs_6, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor6();
		$('#add_videos_6').hide('fast');
		$('#add_photos_6').hide('fast');
		$('#add_webpage_6').hide('fast');
		$('#add_docs_6').show('fast');
		$("#modal_docs_6 > a").addClass('active');
	});
	$('#modal_links_6, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor6();
		$('#add_videos_6').hide('fast');
		$('#add_photos_6').hide('fast');
		$('#add_docs_6').hide('fast');
		$('#add_webpage_6').show('fast');
		$("#modal_links_6 > a").addClass('active');
	});

	$('#modal_videos_7, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
		removeActiveClassFor7();
		
		$("#youTubeAnswer_7_VideoId").val('');
		$("#ansVideoSelect_7").val('');
		
		$('#add_photos_7').hide('fast');
		$('#add_docs_7').hide('fast');
		$('#add_webpage_7').hide('fast');
		$('#add_videos_7').show('fast');
		$("#modal_videos_7 > a").addClass('active');
	});
	$('#modal_photos_7, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		removeActiveClassFor7();
		$('#add_videos_7').hide('fast');
		$('#add_docs_7').hide('fast');
		$('#add_webpage_7').hide('fast');
		$('#add_photos_7').show('fast');
		$("#modal_photos_7 > a").addClass('active');
	});
	$('#modal_docs_7, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor7();
		$('#add_videos_7').hide('fast');
		$('#add_photos_7').hide('fast');
		$('#add_webpage_7').hide('fast');
		$('#add_docs_7').show('fast');
		$("#modal_docs_7 > a").addClass('active');
	});
	$('#modal_links_7, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor7();
		$('#add_videos_7').hide('fast');
		$('#add_photos_7').hide('fast');
		$('#add_docs_7').hide('fast');
		$('#add_webpage_7').show('fast');
		$("#modal_links_7 > a").addClass('active');
	});

	$('#modal_videos_8, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
		removeActiveClassFor8();
		
		$("#youTubeAnswer_8_VideoId").val('');
		$("#ansVideoSelect_8").val('');
		
		
		$('#add_photos_8').hide('fast');
		$('#add_docs_8').hide('fast');
		$('#add_webpage_8').hide('fast');
		$('#add_videos_8').show('fast');
		$("#modal_videos_8 > a").addClass('active');
	});
	$('#modal_photos_8, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		removeActiveClassFor8();
		$('#add_videos_8').hide('fast');
		$('#add_docs_8').hide('fast');
		$('#add_webpage_8').hide('fast');
		$('#add_photos_8').show('fast');
		$("#modal_photos_8 > a").addClass('active');
	});
	$('#modal_docs_8, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor8();
		$('#add_videos_8').hide('fast');
		$('#add_photos_8').hide('fast');
		$('#add_webpage_8').hide('fast');
		$('#add_docs_8').show('fast');
		$("#modal_docs_8 > a").addClass('active');
	});
	$('#modal_links_8, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor8();
		$('#add_videos_8').hide('fast');
		$('#add_photos_8').hide('fast');
		$('#add_docs_8').hide('fast');
		$('#add_webpage_8').show('fast');
		$("#modal_links_8 > a").addClass('active');
	});


	$('#modal_videos_9, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
		removeActiveClassFor9();
		
		$("#youTubeAnswer_9_VideoId").val('');
		$("#ansVideoSelect_9").val('');
		
		
		$('#add_photos_9').hide('fast');
		$('#add_docs_9').hide('fast');
		$('#add_webpage_9').hide('fast');
		$('#add_videos_9').show('fast');
		$("#modal_videos_9 > a").addClass('active');
	});
	$('#modal_photos_9, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		removeActiveClassFor9();
		$('#add_videos_9').hide('fast');
		$('#add_docs_9').hide('fast');
		$('#add_webpage_9').hide('fast');
		$('#add_photos_9').show('fast');
		$("#modal_photos_9 > a").addClass('active');
	});
	$('#modal_docs_9, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor9();
		$('#add_videos_9').hide('fast');
		$('#add_photos_9').hide('fast');
		$('#add_webpage_9').hide('fast');
		$('#add_docs_9').show('fast');
		$("#modal_docs_9 > a").addClass('active');
	});
	$('#modal_links_9, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor9();
		$('#add_videos_9').hide('fast');
		$('#add_photos_9').hide('fast');
		$('#add_docs_9').hide('fast');
		$('#add_webpage_9').show('fast');
		$("#modal_links_9 > a").addClass('active');
	});


	$('#modal_videos_10, #videos_small_1, #videos_small_2, #videos_small_3, #videos_small_4, #videos_small_5, #videos_small_6, #videos_small_7, #videos_small_8, #videos_small_9, #videos_small_10').click(function () {
		removeActiveClassFor10();
		
		$("#youTubeAnswer_10_VideoId").val('');
		$("#ansVideoSelect_10").val('');
		
		
		$('#add_photos_10').hide('fast');
		$('#add_docs_10').hide('fast');
		$('#add_webpage_10').hide('fast');
		$('#add_videos_10').show('fast');
		$("#modal_videos_10 > a").addClass('active');
	});
	$('#modal_photos_10, #photos_small_1, #photos_small_2, #photos_small_3, #photos_small_4, #photos_small_5, #photos_small_6, #photos_small_7, #photos_small_8, #photos_small_9, #photos_small_10').click(function () {
		removeActiveClassFor10();
		$('#add_videos_10').hide('fast');
		$('#add_docs_10').hide('fast');
		$('#add_webpage_10').hide('fast');
		$('#add_photos_10').show('fast');
		$("#modal_photos_10 > a").addClass('active');
	});
	$('#modal_docs_10, #docs_small_1, #docs_small_2, #docs_small_3, #docs_small_4, #docs_small_5, #docs_small_6, #docs_small_7, #docs_small_8, #docs_small_9, #docs_small_10').click(function () {
		removeActiveClassFor10();
		$('#add_videos_10').hide('fast');
		$('#add_photos_10').hide('fast');
		$('#add_webpage_10').hide('fast');
		$('#add_docs_10').show('fast');
		$("#modal_docs_10 > a").addClass('active');
	});
	$('#modal_links_10, #url_small_1, #url_small_2, #url_small_3, #url_small_4, #url_small_5, #url_small_6, #url_small_7, #url_small_8, #url_small_9, #url_small_10').click(function () {
		removeActiveClassFor10();
		$('#add_videos_10').hide('fast');
		$('#add_photos_10').hide('fast');
		$('#add_docs_10').hide('fast');
		$('#add_webpage_10').show('fast');
		$("#modal_links_10 > a").addClass('active');
	});

	//These array's are used to view the answer files. Also to show the answer file name.
	images = ['gif','png','jpg','jpeg','bmp'];
	videoExts = ['flv','mp4','mpg','swf','wmv'];
	docExts = ['doc','docx','log','rtf','txt','wpd','wps','csv','pps','ppt','xml','xlr','xls','xlsx','pdf'];
	ansDocs = ["wpd","wps","xml","xlr","pdf"];
	ansGooleDocs = ['doc','docx','log','rtf','txt','csv','pps','ppt','xls','xlsx'];


	$('#view_small_icon').click(function () {
		removeActiveClass();
		var type = $("#uploadedType").val();
		if (type == 'Video') {
			$('#view_photos').hide('fast');
			$('#view_docs').hide('fast');
			$('#view_videos').show('fast');

			$("#modal_videos > a").addClass('active');
		}
		else if (type == 'Image') {
			$('#view_videos').hide('fast');
			$('#view_docs').hide('fast');
			$('#view_photos').show('fast');

			$("#modal_photos > a").addClass('active');
		}
		else if (type == 'Doc') {
			$('#view_videos').hide('fast');
			$('#view_photos').hide('fast');
			$('#view_docs').show('fast');

			$("#modal_docs > a").addClass('active');
		}
	});

	$('#links').click(function () {
		removeActiveClass();
		$('#add_webpage').show('fast');
		$("#modal_links > a").addClass('active');
	});

	function getAnsModalPopUp(){
		$("#answerFileName" + ansNum).text($("#answerFileName" + ansNum).val());
		var remote = $("#answer_uploaded_url_" + ansNum).val();
		$("#answerFileURL").val(remote);
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#view_photos_' + ansNum).hide('fast');
			$('#view_docs_' + ansNum).hide('fast');
			$('#view_videos_' + ansNum).show('fast');
		} else if (images.indexOf(ext) >= 0) {
			$('#view_videos_' + ansNum).hide('fast');
			$('#view_docs_' + ansNum).hide('fast');
			$('#view_photos_' + ansNum).show('fast');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("href", $("#answerFileURL").val());
			document.getElementById("answerPhotoDisplaySrc_" + ansNum).setAttribute("src", $("#answerFileURL").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$('#view_videos_' + ansNum).hide('fast');
			$('#view_photos_' + ansNum).hide('fast');
			$('#view_docs_' + ansNum).show('fast');
			var remoteUrl = $('#answerFileURL').val();
			var ansDocUrl = "https://docs.google.com/viewer?url=";
			var viewAnsUrl = ansDocUrl + remoteUrl; 
			if(ansGooleDocs.indexOf(ext) >= 0){
				document.getElementById("answerDocDisplay_" + ansNum).setAttribute("href", viewAnsUrl);
			}else if (ansDocs.indexOf(ext) >= 0){
				document.getElementById("answerDocDisplay_" + ansNum).setAttribute("href", $("#answerFileURL").val());
			}
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("target", "_blank");
		}
	}

	$('#view_small_1').click(function () {
		removeActiveClassFor1();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_1 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName1").val());
		}else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_1 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName1").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_1 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName1").val());
		}	
	});

	$('#view_small_2').click(function () {
		removeActiveClassFor2();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_2 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName2").val());
		} else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_2 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName2").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_2 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName2").val());
		}
	});

	$('#view_small_3').click(function () {
		removeActiveClassFor3();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_3 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName3").val());
		} else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_3 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName3").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_3 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName3").val());
		}
	});

	$('#view_small_4').click(function () {
		removeActiveClassFor4();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_4 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName4").val());
		} else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_4 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName4").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_4 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName4").val());
		}
	});

	$('#view_small_5').click(function () {
		removeActiveClassFor5();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_5 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName5").val());
		} else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_5 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName5").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_5 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName5").val());
		}
	});

	$('#view_small_6').click(function () {
		removeActiveClassFor6();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_6 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName6").val());
		} else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_6 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName6").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_6 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName6").val());
		}
	});

	$('#view_small_7').click(function () {
		removeActiveClassFor7();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_7 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName7").val());
		} else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_7 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName7").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_7 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName7").val());
		}
	});

	$('#view_small_8').click(function () {
		removeActiveClassFor8();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_8 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName8").val());
		} else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_8 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName8").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_8 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName8").val());
		}
	});

	$('#view_small_9').click(function () {
		removeActiveClassFor9();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_9 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName9").val());
		} else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_9 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName9").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_9 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName9").val());
		}
	});

	$('#view_small_10').click(function () {
		removeActiveClassFor10();
		getAnsModalPopUp();
		var fileNameExt = $('#answerFileName' + ansNum).val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$("#modal_videos_10 > a").addClass('active');
			document.getElementById("answerVideoDisplay_" + ansNum).setAttribute("title", $("#answerFileName10").val());
		} else if (images.indexOf(ext) >= 0) {
			$("#modal_photos_10 > a").addClass('active');
			document.getElementById("answerPhotoDisplay_" + ansNum).setAttribute("title", $("#answerFileName10").val());
		} else if (docExts.indexOf(ext) >= 0) {
			$("#modal_docs_10 > a").addClass('active');
			document.getElementById("answerDocDisplay_" + ansNum).setAttribute("title", $("#answerFileName10").val());
		}
	});

	$("#signup-user").click(function () {
		parent.$.fn.colorbox({'href':'form#create_signupform', 'open':true, 'inline':true});
	});

	$("#login-user, #login-user2, #login-user3").click(function () {
		//parent.$.fn.colorbox({'href':'form#create_loginForm', 'open':true, 'inline':true});
		loginProceessInModelLoginPage("login_incyyte");
	});
	$("#fgtpwd-user").click(function () {
		parent.$.fn.colorbox({'href':'form#create_fgtpwdform', 'open':true, 'inline':true});
	});

	$("#displayEmailList").click(function () {
		$('#search_share_contacts').val('');
		parent.$.fn.colorbox({'href':'div#contactEmailList', 'open':true, 'inline':true});
		sharedContactsJsonCall();
	});

	$('#sendToGroup').change(function () {
		toggleMailGroup();
	});
	$('#sendToIndividual').change(function () {
		toggleMailGroup();
	});

	if (($('#file_name_tst').val() != '') ||($('#youTubeQuestionVideoId').val() != '')) {
		$("#incyyte_media_add").hide("fast");
		$("#incyyte_media_view").show("fast");

		if ($("#uploadedType").val() == 'Video') {

			if($('#youTubeQuestionVideoId').val() != '') {
				var videoId = $('#youTubeQuestionVideoId').val();
				$("#viewQuesYouTubeVideoId").attr("onClick", "javascript:playYoutubeVideoQues('videolinkId', '"+videoId+"')");
			}
			
			$('#incyyte_video_file_name').text($('#file_name_tst').val().substring(0,13).concat("..."));
			$('#view_video_file_name').text($('#file_name_tst').val().substring(0,13).concat("..."));
		}
		else if ($("#uploadedType").val() == 'Image') {
			$('#incyyte_photo_file_name').text($('#file_name_tst').val().substring(0,13).concat("..."));
			$('#view_photo_file_name').text($('#file_name_tst').val().substring(0,13).concat("..."));
		}
		else if ($("#uploadedType").val() == 'Doc') {
			$('#incyyte_doc_file_name').text($('#file_name_tst').val().substring(0,13).concat("..."));
			$('#view_doc_file_name').text($('#file_name_tst').val().substring(0,13).concat("..."));
		}

	} else {
		$("#incyyte_media_add").show("fast");
		$("#incyyte_media_view").hide("fast");

		$('#incyyte_video_file_name').text('');
		$('#view_video_file_name').text('');

		$('#incyyte_photo_file_name').text('');
		$('#view_photo_file_name').text('');

		$('#incyyte_doc_file_name').text('');
		$('#view_doc_file_name').text('');
	}

	if ($('#answerFileName1').val() != '') {
		var fileNameExt = $('#answerFileName1').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_1').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_1').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_1').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_1').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_1').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_1').text(fileNameExt.substring(0,13).concat("..."));
		}

	} else {
		$('#ans_video_file_name_1').text('');
		$('#view_ans_video_file_name_1').text('');

		$('#ans_photo_file_name_1').text('');
		$('#view_ans_photo_file_name_1').text('');

		$('#ans_doc_file_name_1').text('');
		$('#view_ans_doc_file_name_1').text('');
	}

	if ($('#answerFileName2').val() != '') {
		var fileNameExt = $('#answerFileName2').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_2').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_2').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_2').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_2').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_2').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_2').text(fileNameExt.substring(0,13).concat("..."));
		}

	} else {
		$('#ans_video_file_name_2').text('');
		$('#view_ans_video_file_name_2').text('');

		$('#ans_photo_file_name_2').text('');
		$('#view_ans_photo_file_name_2').text('');

		$('#ans_doc_file_name_2').text('');
		$('#view_ans_doc_file_name_2').text('');
	}

	if ($('#answerFileName3').val() != '') {
		var fileNameExt = $('#answerFileName3').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_3').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_3').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_3').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_3').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_3').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_3').text(fileNameExt.substring(0,13).concat("..."));
		}

	} else {
		$('#ans_video_file_name_3').text('');
		$('#view_ans_video_file_name_3').text('');
		$('#ans_photo_file_name_3').text('');
		$('#view_ans_photo_file_name_3').text('');
		$('#ans_doc_file_name_3').text('');
		$('#view_ans_doc_file_name_3').text('');
	}

	if ($('#answerFileName4').val() != '') {
		var fileNameExt = $('#answerFileName4').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_4').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_4').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_4').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_4').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_4').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_4').text(fileNameExt.substring(0,13).concat("..."));
		}

	} else {
		$('#ans_video_file_name_4').text('');
		$('#view_ans_video_file_name_4').text('');
		$('#ans_photo_file_name_4').text('');
		$('#view_ans_photo_file_name_4').text('');
		$('#ans_doc_file_name_4').text('');
		$('#view_ans_doc_file_name_4').text('');
	}

	if ($('#answerFileName5').val() != '') {
		var fileNameExt = $('#answerFileName5').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_5').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_5').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_5').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_5').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_5').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_5').text(fileNameExt.substring(0,13).concat("..."));
		}

	} else {
		$('#ans_video_file_name_5').text('');
		$('#view_ans_video_file_name_5').text('');
		$('#ans_photo_file_name_5').text('');
		$('#view_ans_photo_file_name_5').text('');
		$('#ans_doc_file_name_5').text('');
		$('#view_ans_doc_file_name_5').text('');
	}

	if ($('#answerFileName6').val() != '') {
		var fileNameExt = $('#answerFileName6').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_6').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_6').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_6').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_6').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_6').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_6').text(fileNameExt.substring(0,13).concat("..."));
		}

	} else {
		$('#ans_video_file_name_6').text('');
		$('#view_ans_video_file_name_6').text('');
		$('#ans_photo_file_name_6').text('');
		$('#view_ans_photo_file_name_6').text('');
		$('#ans_doc_file_name_6').text('');
		$('#view_ans_doc_file_name_6').text('');
	}

	if ($('#answerFileName7').val() != '') {
		var fileNameExt = $('#answerFileName7').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_7').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_7').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_7').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_7').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_7').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_7').text(fileNameExt.substring(0,13).concat("..."));
		}

	} else {
		$('#ans_video_file_name_7').text('');
		$('#view_ans_video_file_name_7').text('');
		$('#ans_photo_file_name_7').text('');
		$('#view_ans_photo_file_name_7').text('');
		$('#ans_doc_file_name_7').text('');
		$('#view_ans_doc_file_name_7').text('');
	}

	if ($('#answerFileName8').val() != '') {
		var fileNameExt = $('#answerFileName8').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_8').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_8').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_8').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_8').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_8').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_8').text(fileNameExt.substring(0,13).concat("..."));
		}

	} else {
		$('#ans_video_file_name_8').text('');
		$('#view_ans_video_file_name_8').text('');
		$('#ans_photo_file_name_8').text('');
		$('#view_ans_photo_file_name_8').text('');
		$('#ans_doc_file_name_8').text('');
		$('#view_ans_doc_file_name_8').text('');
	}
	if ($('#answerFileName9').val() != '') {
		var fileNameExt = $('#answerFileName9').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_9').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_9').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_9').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_9').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_9').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_9').text(fileNameExt.substring(0,13).concat("..."));
		}

	} else {
		$('#ans_video_file_name_9').text('');
		$('#view_ans_video_file_name_9').text('');
		$('#ans_photo_file_name_9').text('');
		$('#view_ans_photo_file_name_9').text('');
		$('#ans_doc_file_name_9').text('');
		$('#view_ans_doc_file_name_9').text('');
	}
	if ($('#answerFileName10').val() != '') {
		var fileNameExt = $('#answerFileName10').val();
		var ext = fileNameExt.substr(fileNameExt.lastIndexOf('.') + 1).toLowerCase();
		if (videoExts.indexOf(ext) >= 0) {
			$('#ans_video_file_name_10').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_video_file_name_10').text(fileNameExt.substring(0,13).concat("..."));
		} else if (images.indexOf(ext) >= 0) {
			$('#ans_photo_file_name_10').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_photo_file_name_10').text(fileNameExt.substring(0,13).concat("..."));
		} else if (docExts.indexOf(ext) >= 0) {
			$('#ans_doc_file_name_10').text(fileNameExt.substring(0,13).concat("..."));
			$('#view_ans_doc_file_name_10').text(fileNameExt.substring(0,13).concat("..."));
		}
	} else {
		$('#ans_video_file_name_10').text('');
		$('#view_ans_video_file_name_10').text('');
		$('#ans_photo_file_name_10').text('');
		$('#view_ans_photo_file_name_10').text('');
		$('#ans_doc_file_name_10').text('');
		$('#view_ans_doc_file_name_10').text('');
	}


	$("#close_elink").click(function () {
		$.fn.colorbox.close();
	});

	setCategory();

	$(function () {
		var button = $('#loginButton1');
		var box = $('#loginBox1');
		var form = $('#loginForm1');
		button.removeAttr('href');
		button.mouseup(function (login) {
			box.toggle();
			button.toggleClass('active');
		});
		form.mouseup(function () {
			return false;
		});
		$(this).mouseup(function (login) {
			if (!($(login.target).parent('#loginButton1').length > 0)) {
				button.removeClass('active');
				box.hide();
			}
		});
	});

	// validate login form on keyup and submit
	incyyte_validator = $("#inCyyteForm").validate({
		rules: {
			incyyte: {
				required: true,
				minlength: 5,
				maxlength: 150
			}
			/*emailArr:{
				required: function(element){
					return $("input:radio[name='sendType']:checked").val() == 'mail' && $("input:radio[name='sendToGroup']:checked").val() == 'N';
				}
			},	
			pageName:{
				required: function(element){
					return $("input:radio[name='sendType']:checked").val() == 'post';
				}
			}*/	
		},
		messages: {
			incyyte: {
				required: "Please write a question",
				minlength: "Your question must be at least 5 characters long",
				maxlength: "Your question must be at less than 150 characters long"
			}
			/*emailArr:{
				required: "Please provide the email address"
			},
			pageName:{
				required: "Please provide the page name"
			}*/
		}
	}); 	

}

function validatePageName(){
	
	if($("input:radio[name='sendType']:checked").val() == 'post' ){
		if($("#pageName").val() == ''){
			$("#pageName_error").text("Please provide the page name");
			$("#pageName_error").show();
			return false;
		}
		
		var pageNameFilter = /^[0-9a-zA-Z ]+$/;
		if (!pageNameFilter.test($("#pageName").val())) {			
			$("#pageName_error").text("Please provide a valid page name - e.g. myincyytepoll ");
			$("#pageName_error").show();
			return false;
		}
		
		$("#pageName_error").text("");
		$("#pageName_error").hide();
		
	}
	return true;	
}


function validateUniquePageName(){	
	var success = true;
	if($("input:radio[name='sendType']:checked").val() == 'post' ){
		
		if(validatePageName()){
			$("#pageName_error").text("");
			$("#pageName_error").hide();	
			
			
			$("#inCyyteForm").ajaxSubmit({
				type:'POST',
				url:'validatepagename.cyt',
				success:function (data) {
					
					if (data.indexOf("success") != -1) {
						$("#pageName_error").text("");
						$("#pageName_error").hide();	
						success = true;						
					}else{
						$("#pageName_error").text("The page name is already exist");
						$("#pageName_error").show();						
						success = false;
					}
				},
				error:function (jqXHR, textStatus, errorThrown) {
					
				}
			});							
		}
		else{
			success = false;
		}
	}	
	return success;
}



function validateCategory(){
	if($("#category").val() == ''){
		$("#category_error").text("Please select category");
		$("#category_error").show();
		scroll(0,0);
		return false;
	}else{
		$("#category_error").text("");
		$("#category_error").hide();	
	}
	return true;
}
function validateSplc(txt) {
	//var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
	 var iChars = "!&`~-[]\\\'/\":<>";
	for (var i = 0; i < txt.length; i++) {
		if (iChars.indexOf(txt.charAt(i)) != '-1') {
			return false;
		}
	}
	return true;
}
function validateAns(){
	var ansCount = $("#answer_count").val();
	//var spChars = /^[A-Za-z0-9\s!@#$��%&*()+={}_|;:,.<>\/?\\-]+$/;
	//var regex = new RegExp(spChars);
	var ansFlag = true;

	if(ansCount==0){
		ansCount = 3;
	}	

	if($("#answerChoice1").val()==''){
		$("#answerChoice1").val("Yes");
	} else if($("#answerChoice2").val()==''){
		$("#answerChoice2").val("No");
	}else if($("#answerChoice3").val()==''){
		$("#answerChoice3").val("Not Sure");
	}
	
	for(var i=1; i <= ansCount; i++){
		
		if($("#answerChoice"+i).val() == ''){
			$("#answerChoice"+ i +"_error").text("Please enter value for answer "+i+"");
			$("#answerChoice"+ i +"_error").show();
			scroll(0,0);
			ansFlag = false;
		} 
		else if(!validateSplc( $("#answerChoice"+i).val() )){
			$("#answerChoice"+ i +"_error").text("Answer "+i+" contains illegal characters. please remove any of the following (', `, ^, ~, [])");
			$("#answerChoice"+ i +"_error").show();
			scroll(0,0);
			ansFlag = false;
		}else{
			$("#answerChoice"+ i +"_error").text("");
			$("#answerChoice"+ i +"_error").hide();
		}

	}	
	return ansFlag;
}

function validateGroupSel(){
	if($("input:radio[name='sendType']:checked").val() == 'mail' && $("input:radio[name='sendToGroup']:checked").val() == 'Y'){

		if($("#grpId").val() == ""){			
			$("#grp_error").text("Please select group");
			$("#grp_error").show();
			scroll(0,0);
			return false;
		}else{
			$("#grp_error").text("");
			$("#grp_error").hide();
		}

	}
	return true;
}

function validateRegion(){
	if($("input:radio[name='sendType']:checked").val() == 'area'){
		if($("#locality").val() == "Postcode"){
			if($("#postcodex").val() == ""){
				$("#region_error").text("Please provide the post code");
				$("#region_error").show();
				//scroll(0,0);
				return false;
			}
			else if($("#postcodex").val() != ""){
				var pCode = $("#postcodex").val();
				if(pCode.indexOf(" ") < 0 || pCode.length < 6){
					$("#region_error").text("Please provide valid post code");
					$("#region_error").show();
					return false;
				}

				var postcodeFilter = /^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))) {0,1}[0-9][A-Za-z]{2})$/;
				var validFlag = postcodeFilter.test(pCode);
				if(!validFlag){
					$("#region_error").text("Please provide valid post code");
					$("#region_error").show();
					return false;
				}
			}			
		}
		else if($("#locality").val() == "Region"){
			if($("#Neighbourhood").val() == ""){
				$("#region_error").text("Please provide the regional code");
				$("#region_error").show();
				//scroll(0,0);
				return false;
			}
			else if($("#Neighbourhood").val() != ""){
				var reg = $("#Neighbourhood").val();
				if(reg.length < 2 || reg.length > 4){
					$("#region_error").text("Please provide valid regional code");
					$("#region_error").show();
					//scroll(0,0);
					return false;
				}
			}
		}
		else if($("#locality").val() == "County"){
			if($("#county").val() == ""){
				$("#region_error").text("Please select county");
				$("#region_error").show();
				//scroll(0,0);
				return false;
			}
		}
		if(!validateAgeRange()){
			return false;
		}
		if(!validateCategory()){
			return false;
		}
	}
	$("#region_error").text("");
	$("#region_error").hide();
	return true;
}

function validateAgeRange(){
	var min = $("#filterAgeMin").val();
	var max = $("#filterAgeMax").val();
	if(min == 'select' && max == 'select'){
		$("#filterAgeMin").val('13');
		$("#filterAgeMax").val('Over');
	}else if(min != 'select' || max != 'select'){
	
		if(min != 'select' && max == 'select') {
			$("#agerange_error").text("Please select maximum age");		
			$("#agerange_error").show();
			return false;
		}
		if(min == 'select' && max != 'select'){
			$("#agerange_error").text("Please select minmum age");		
			$("#agerange_error").show();
			return false;
		}
		if(max != 'Over'){
			if(min > max){
				$("#agerange_error").text("Please select valid maximum age");		
				$("#agerange_error").show();
				return false;
			}
		}

	}
	$("#agerange_error").text("");		
	$("#agerange_error").hide();
	return true;
}

function validateEmail(){
	if($("input:radio[name='sendType']:checked").val() == 'mail' && $("input:radio[name='sendToGroup']:checked").val() == 'N'){
		var mailAddrs = $("#emailArr").val();
		var re = /\s*[,;]\s*/;
		var filter = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
		if( mailAddrs == ""){
			$('#email_error').text("Please provide the email address");
			$("#email_error").show();
			return false;
		}
		var maillAdrArr = mailAddrs.split(re);
		for(var x=0; x < maillAdrArr.length; x++){
			var addr = $.trim(maillAdrArr[x]);
			if(addr != '' && addr != ' '){
				$("#email_error").text("");
				$("#email_error").hide();
			}		
		}

		if(hasDuplicate(maillAdrArr)){
			$("#email_error").text("You have entered duplicate email address.");
			$("#email_error").show();
			return false;
		}else{
			$("#email_error").text("");
			$("#email_error").hide();
		}

	}
	return true;
}

function hasDuplicate(arr) {
	var i = arr.length, j, val;

	while (i--) {
		val = arr[i];
		j = i;
		while (j--) {
			if (arr[j] === val) {
				return true;
			}
		}
	}
	return false;
}

function isSpecialCharEmail(su_email){
	var su_email=$("#su_email").val();
	var iChars = /^([\w\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	var isValid=true;
	for (var i = 0; i < su_email.length; i++) 
	{
		if (iChars.indexOf(su_email.charAt(i))!='-1') {
			isValid=false;
			break;
		}else{
			isValid=true;
		}
	}
	return isValid;
}
function isValidateEmailStr(emailStr) {
	/* The following variable tells the rest of the function whether or not
     to verify that the address ends in a two-letter country or well-known
     TLD.  1 means check it, 0 means don't. */

	var checkTLD = 1;
	/* The following is the list of known TLDs that an e-mail address must end with. */
	var knownDomsPat = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
	/* The following pattern is used to check if the entered e-mail address
     fits the user@domain format.  It also is used to separate the username
     from the domain. */

	var emailPat = /^(.+)@(.+)$/;

	/* The following string represents the pattern for matching all special
     characters.  We don't want to allow special characters in the address.
     These characters include ( ) < > @ , ; : \ " . [ ] */

	var specialChars = "\\(\\)><@,;:\\\\\\\"\\.\\[\\]";

	/* The following string represents the range of characters allowed in a
     username or domainname.  It really states which chars aren't allowed.*/

	var validChars = "\[^\\s" + specialChars + "\]";

	/* The following pattern applies if the "user" is a quoted string (in
     which case, there are no rules about which characters are allowed
     and which aren't; anything goes).  E.g. "jiminy cricket"@disney.com
     is a legal e-mail address. */

	var quotedUser = "(\"[^\"]*\")";

	/* The following pattern applies for domains that are IP addresses,
     rather than symbolic names.  E.g. joe@[123.124.233.4] is a legal
     e-mail address. NOTE: The square brackets are required. */

	var ipDomainPat = /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;

	/* The following string represents an atom (basically a series of non-special characters.) */

	var atom = validChars + '+';

	/* The following string represents one word in the typical username.
     For example, in john.doe@somewhere.com, john and doe are words.
     Basically, a word is either an atom or quoted string. */

	var word = "(" + atom + "|" + quotedUser + ")";

	// The following pattern describes the structure of the user

	var userPat = new RegExp("^" + word + "(\\." + word + ")*$");

	/* The following pattern describes the structure of a normal symbolic
     domain, as opposed to ipDomainPat, shown above. */

	var domainPat = new RegExp("^" + atom + "(\\." + atom + ")*$");

	/* Finally, let's start trying to figure out if the supplied address is valid. */

	/* Begin with the coarse pattern to simply break up user@domain into
     different pieces that are easy to analyze. */

	var matchArray = emailStr.match(emailPat);

	if (matchArray == null) {

		/* Too many/few @'s or something; basically, this address doesn't
         even fit the general mould of a valid e-mail address. */

		//alert("Email address seems incorrect (check @ and .'s)");
		return false;
	}
	var user = matchArray[1];
	var domain = matchArray[2];

	// Start by checking that only basic ASCII characters are in the strings (0-127).

	for (i = 0; i < user.length; i++) {
		if (user.charCodeAt(i) > 127) {
			//alert("Ths username contains invalid characters.");
			return false;
		}
	}
	for (i = 0; i < domain.length; i++) {
		if (domain.charCodeAt(i) > 127) {
			//alert("Ths domain name contains invalid characters.");
			return false;
		}
	}

	// See if "user" is valid

	if (user.match(userPat) == null) {
		// user is not valid
		return false;
	}

	/* if the e-mail address is at an IP address (as opposed to a symbolic
     host name) make sure the IP address is valid. */

	var IPArray = domain.match(ipDomainPat);
	if (IPArray != null) {

		// this is an IP address

		for (var i = 1; i <= 4; i++) {
			if (IPArray[i] > 255) {
				return false;
			}
		}
		return true;
	}

	// Domain is symbolic name.  Check if it's valid.

	var atomPat = new RegExp("^" + atom + "$");
	var domArr = domain.split(".");
	var len = domArr.length;
	for (i = 0; i < len; i++) {
		if (domArr[i].search(atomPat) == -1) {
			return false;
		}
	}

	/* domain name seems valid, but now make sure that it ends in a
     known top-level domain (like com, edu, gov) or a two-letter word,
     representing country (uk, nl), and that there's a hostname preceding
     the domain or country. */

	if (checkTLD && domArr[domArr.length - 1].length != 2 &&
			domArr[domArr.length - 1].search(knownDomsPat) == -1) {
		///alert("The address must end in a well-known domain or two letter " + "country.");
		return false;
	}

	// Make sure there's a host name preceding the domain.

	if (len < 2) {
		return false;
	}

	// If we've gotten this far, everything is valid!
	return true;
}

function setCategory() {
	if ($("#category").val() == 'Sports') {
		$("#sports").click();
	}
	else if ($("#category").val() == 'Music') {
		$("#music").click();
	}
	else if ($("#category").val() == 'Religion') {
		$("#religion").click();
	}
	else if ($("#category").val() == 'Politics') {
		$("#politics").click();
	}
	else if ($("#category").val() == 'Community') {
		$("#community").click();
	}
	else if ($("#category").val() == 'Relationships') {
		$("#relationships").click();
	}
	else if ($("#category").val() == 'Business') {
		$("#business").click();
	}
	else if ($("#category").val() == 'Shopping') {
		$("#shopping").click();
	}
	else if ($("#category").val() == 'Health') {
		$("#health").click();
	}
	else if ($("#category").val() == 'Beauty') {
		$("#beauty").click();
	}
	else if ($("#category").val() == 'Travel') {
		$("#travel").click();
	}
	else if ($("#category").val() == 'Food') {
		$("#food").click();
	}
	else if ($("#category").val() == 'Motoring') {
		$("#motoring").click();
	}
	else if ($("#category").val() == 'Internet') {
		$("#internet").click();
	}
	else if ($("#category").val() == 'Entertainment') {
		$("#entertainment").click();
	}
	else if ($("#category").val() == 'Others') {
		$("#others").click();
	}
}

function removeActiveClass() {
	$("#modal_videos > a").removeClass('active');
	$("#modal_photos > a").removeClass('active');
	$("#modal_docs > a").removeClass('active');
	$("#modal_links > a").removeClass('active');
}


function removeActiveClassFor1() {
	$("#modal_videos_1 > a").removeClass('active');
	$("#modal_photos_1 > a").removeClass('active');
	$("#modal_docs_1 > a").removeClass('active');
}

function removeActiveClassFor2() {
	$("#modal_videos_2 > a").removeClass('active');
	$("#modal_photos_2 > a").removeClass('active');
	$("#modal_docs_2 > a").removeClass('active');
	$("#modal_links_2 > a").removeClass('active');
}

function removeActiveClassFor3() {
	$("#modal_videos_3 > a").removeClass('active');
	$("#modal_photos_3 > a").removeClass('active');
	$("#modal_docs_3 > a").removeClass('active');
	$("#modal_links_3 > a").removeClass('active');
}

function removeActiveClassFor4() {
	$("#modal_videos_4 > a").removeClass('active');
	$("#modal_photos_4 > a").removeClass('active');
	$("#modal_docs_4 > a").removeClass('active');
	$("#modal_links_4 > a").removeClass('active');
}

function removeActiveClassFor5() {
	$("#modal_videos_5 > a").removeClass('active');
	$("#modal_photos_5 > a").removeClass('active');
	$("#modal_docs_5 > a").removeClass('active');
	$("#modal_links_5 > a").removeClass('active');
}

function removeActiveClassFor6() {
	$("#modal_videos_6 > a").removeClass('active');
	$("#modal_photos_6 > a").removeClass('active');
	$("#modal_docs_6 > a").removeClass('active');
	$("#modal_links_6 > a").removeClass('active');
}

function removeActiveClassFor7() {
	$("#modal_videos_7 > a").removeClass('active');
	$("#modal_photos_7 > a").removeClass('active');
	$("#modal_docs_7 > a").removeClass('active');
	$("#modal_links_7 > a").removeClass('active');
}

function removeActiveClassFor8() {
	$("#modal_videos_8 > a").removeClass('active');
	$("#modal_photos_8 > a").removeClass('active');
	$("#modal_docs_8 > a").removeClass('active');
	$("#modal_links_8 > a").removeClass('active');
}
function removeActiveClassFor9() {
	$("#modal_videos_9 > a").removeClass('active');
	$("#modal_photos_9 > a").removeClass('active');
	$("#modal_docs_9 > a").removeClass('active');
	$("#modal_links_9 > a").removeClass('active');
}
function removeActiveClassFor10() {
	$("#modal_videos_10 > a").removeClass('active');
	$("#modal_photos_10 > a").removeClass('active');
	$("#modal_docs_10 > a").removeClass('active');
	$("#modal_links_10 > a").removeClass('active');
}



function addVideo(index) {
	$('#add_photos' + index).hide('fast');
	$('#add_docs' + index).hide('fast');
	$('#add_webpage' + index).hide('fast');
	$('#add_videos' + index).show('fast');
}

function addPhotos(index) {
	$('#add_videos' + index).hide('fast');
	$('#add_docs' + index).hide('fast');
	$('#add_webpage' + index).hide('fast');
	$('#add_photos' + index).show('fast');
}

function addDocs(index) {
	$('#add_videos' + index).hide('fast');
	$('#add_photos' + index).hide('fast');
	$('#add_webpage' + index).hide('fast');
	$('#add_docs' + index).show('fast');
}

function addWebpage(index) {
	$('#add_videos' + index).hide('fast');
	$('#add_photos' + index).hide('fast');
	$('#add_docs' + index).hide('fast');
	$('#add_webpage' + index).show('fast');
}

function toggleMail() {
	if ($('#mail').attr('checked') === 'checked') {
		//buttons
		$("#sendInCyytePoll").css("display", "");
		$("#attachPoll").css("display", "none");
		
		$("#by_email").css("display", "");
		$("#post_on_webpage").css("display", "none");
		$("#postal_region").css("display", "none");

		$("#send_your_poll_top").css("display", "");

		$("#postal_region_left").css("display", "none");
		$("#postal_region_right").css("display", "none");
		$("#send_your_poll_right, #send_your_poll_left").css({
			'border-radius':'35px',
			'-moz-border-radius':'35px',
			'-webkit-border-radius':'35px',
			'border-top-right-radius':'0',
			'border-top-left-radius':'0',
			'-moz-border-top-right-radius':'0',
			'-moz-border-top-left-radius':'0',
			'-webkit-border-top-right-radius':'0',
			'-webkit-border-top-left-radius':'0',
			'min-height':'auto'
		});
	} else if ($('#post').attr('checked') === 'checked') {
		$("#sendInCyytePoll").css("display", "none");
		$("#attachPoll").css("display", "");
		
		if($("#checkbox-1").attr("checked")== 'checked')
		{		    
		    $(".edit_pro_pop_txt").text("Incyyte polls that are attached to exclusive poll pages cannot be set as anonymous. Please deselect anonymous checkbox to attach this poll");
			parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
		}		
		
		$("#post_on_webpage").css("display", "");
		$("#by_email").css("display", "none");
		$("#postal_region").css("display", "none");

		$("#send_your_poll_top").css("display", "");

		$("#postal_region_left").css("display", "none");
		$("#postal_region_right").css("display", "none");
		$("#send_your_poll_right, #send_your_poll_left").css({
			'border-radius':'35px',
			'-moz-border-radius':'35px',
			'-webkit-border-radius':'35px',
			'border-top-right-radius':'0',
			'border-top-left-radius':'0',
			'-moz-border-top-right-radius':'0',
			'-moz-border-top-left-radius':'0',
			'-webkit-border-top-right-radius':'0',
			'-webkit-border-top-left-radius':'0',
			'min-height':'auto'
		});		
	} else if ($('#area').attr('checked') === 'checked') {
		$("#sendInCyytePoll").css("display", "");
		$("#attachPoll").css("display", "none");

		$("#postal_region").css("display", "");
		$("#postal_region_left").css("display", "");
		$("#postal_region_right").css("display", "");
		$("#by_email").css("display", "none");
		$("#post_on_webpage").css("display", "none");

		$('input:radio[name=public_poll]').val('Y');
		$('input[name=public_poll]').val(['Y']);

		$("#send_your_poll_top").css("display", "none");
		$("#send_your_poll_right, #send_your_poll_left").css("border-radius", "0");
		$("#send_your_poll_left, #send_your_poll_right").css("min-height", "130px");
	}
}

function toggleMailGroup() {
	if ($('#sendToIndividual').attr('checked') === 'checked') {
		$("#mailIndividual").css("display", "");
		$("#mailGroup").css("display", "none");
	} else if ($('#sendToGroup').attr('checked') === 'checked') {
		$("#mailGroup").css("display", "");
		$("#mailIndividual").css("display", "none");
	}
}

function deselectCategory() {
	$("#category_error").hide();
	$('#categories_ul').children('li').each(function () {
		$(this).css("background-image", "url(ui/images/category_button.png)");
	});	
}


function uploadLogo() {

	$("#loadingDiv").ajaxStart(
			function () {
				$(this).show();
			}).ajaxStop(function () {
				$(this).hide();
			});

	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'send_question/uploadLogoFile.cyt',
		success:function (data) {
			if (data.indexOf("success") != -1) {
				//set progress to 100%
				//clearError("uploadedLogo");
				displayLoad(data);
			}
			else {
				displayNotLoad();
				//setError("uploadedLogo");
			}
		},
		error:function (jqXHR, textStatus, errorThrown) {
			displayNotLoad();
			//setError("uploadedLogo");
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});


}

function displayLoad(data) {
	$("#notLoaded").hide();
	data = data.replace("<pre>", "");
	data = data.replace("</pre>", "");
	data = data.replace("success", "");
	$("#loadText").html("<b>" + data + "</b>");
	$("#loaded").show();
	$("#loadingDiv").hide();
}

function displayNotLoad() {
	$("#loaded").hide();
	$("#notLoaded").show();
	$("#loadingDiv").hide();
}


function uploadFileFunc(fileType) {
	var filePath = '';
	var validFlag = true;

	$('#incyyte_video_error_msg2').hide("fast");
	$('#incyyte_photos_error_msg2').hide("fast");	
	$('#incyyte_doc_error_msg2').hide("fast");

	if (fileType == 'Video') {
		/*filePath = $("#incyyte_video_file_input").val();
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
		}*/
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
		$("#inCyyteForm").ajaxSubmit({
			type:'POST',		
			url: 'multiPartFileSingle.cyt',	
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
					if(start != -1 &&  end != -1)
						var filelocation=data.substring(start+1,end);
					else
						var filelocation=data;
					if(fileType == 'Video'){
					//This check is done in an assumption that Youtube video ID is always 11 digits
					// Entire You Tube URL built is of 41 characters length	
						if(filelocation.length == 41) {
							var videoId = filelocation.substring(30 , 41);
							$("#viewQuesYouTubeVideoId").attr("onClick", "javascript:playYoutubeVideoQues('videolinkId', '"+videoId+"')");
						}else{
							$("#viewQuesYouTubeVideoId").attr("onClick", "javascript:playVideo('videolinkId','playervideolinkId', '"+filelocation+"')");
						}
						$("#incyyte_video_not_loaded").hide("fast");
						$("#incyyte_video_loaded").show("fast");
					}
					else if(fileType == 'Image'){
						$("#photolinkId").attr("href", filelocation);
						$("#photoImg").attr("src", filelocation);

						$("#addPhotolink").attr("href", filelocation);
						$("#addPhotoImg").attr("src", filelocation);

						$("#incyyte_photo_not_loaded").hide();
						$("#incyyte_photo_loaded").show();
					}
					else{
						$("#doclinkId").attr("href", filelocation);
						$("#addDoclink").attr("href", filelocation);

						$("#incyyte_doc_not_loaded").hide();
						$("#incyyte_doc_loaded").show();
						setTimeout(location.reload(true), 5);
					}

					$("#incyyte_media_add").hide();
					$("#incyyte_media_view").show();

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


function deleteFileFunc(fileType) {
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'deleteuploadedfile.cyt',
		success:function (data) {

			$('#file_name_tst').val('');
			$("#uploadedType").val('');

			$("#incyyte_media_add").show();
			$("#incyyte_media_view").hide();

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
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});

}

var ansNum = 0;
function getAnsNum(answerNumber) {
	ansNum = answerNumber;
	if($("#answerChoice1").val()==''){
		$("#answerChoice1").val("Yes");
	}
	if($("#answerChoice2").val()==''){
		$("#answerChoice2").val("No");
	}
	if($("#answerChoice3").val()==''){
		$("#answerChoice3").val("Not Sure");
	}
	$('#search_value_'+ansNum).val($('#answerChoice'+ansNum).val());
	var searchValue = document.getElementById("search_value_"+ansNum);
	var searchLoad = document.getElementById("search_result_"+ansNum);
	var searchSelect = "search_selected_pic_"+ansNum;
	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    //This call is for Videos
    var ansVideos = "search_answer_videos_" + ansNum;
    var ansIndex = "start-index_answer_" + ansNum;
    var ansScroll = "googleVideosScrollAnswer_" + ansNum;
    var ansSelect = "ansVideoSelect_" + ansNum;
    $('#'+ansVideos).val($('#answerChoice'+ansNum).val());
    $('#' + ansScroll).html("");
    makeYoutubeApiCall(ansVideos , ansIndex ,  ansScroll , ansSelect);
}

function ansDeleteFileFunc(fileType) {
	setCategory();
	$('#answer_upload_opt').val(ansNum);
//	alert($('#answer_upload_opt').val());
	var opt = $("#answer_opt_" + ansNum).val();
	var choice = $("#answerChoice" + ansNum).val();
	var remote = $("#answer_uploaded_url_" + ansNum).val();
	var type = $("#answerType" + ansNum).val();
//	alert("opt::"+opt+" choice::"+choice+" remote::"+remote+" type::"+type);
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'deleteAnsUploadedFile.cyt',
		success:function (data) {

			$('#answerFileName'+ansNum).val('');
			$("#answerUploadedType"+ansNum).val('');

			$("#ans_video_file_name_" + ansNum).text('');
			$('#view_ans_video_file_name_' + ansNum).text('');

			$("#ans_photo_file_name_" + ansNum).text('');
			$('#view_ans_photo_file_name_' + ansNum).text('');

			$("#ans_doc_file_name_" + ansNum).text('');
			$('#view_ans_doc_file_name_' + ansNum).text('');
			/*	$("#ans_one_add_"+ansNum).show();
			$("#ans_one_view_"+ansNum).hide();*/
			$("#inCyyteForm").attr("action", "create_question.cyt");
			$("#inCyyteForm").attr("method", "POST");
			$("#inCyyteForm").submit();
			$.fn.colorbox.close();
			window.setTimeout(location.reload(true),1);
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});

}

function uploadAnsFileFunc(fileType) {
	setCategory();
	//alert("Inside Upload Ans File");
	$("#incyyte_ans_video_error_msg2_" + ansNum).css("display","none");
	$("#incyyte_ans_image_error_msg2_" + ansNum).css("display","none");	
	$("#incyyte_ans_doc_error_msg2_" + ansNum).css("display","none");

	var filePath = '';
	if (fileType == 'Video') {
		filePath = $("#answer_file_video_" + ansNum).val();
		var ext = $('#answer_file_video_' + ansNum).val().split('.').pop().toLowerCase();
		var youtubeId = $('#youTubeAnswer_'+ansNum+'_VideoId').val();
		
		 if($.inArray(ext, ['flv','mp4','swf']) == -1 &&  $('#youTubeAnswer_'+ansNum+'_VideoId').val() == "" ) {
			$("#incyyte_ans_video_error_msg_" + ansNum).css("display","");	 
			return false;
		}else{
			$("#incyyte_ans_video_error_msg_" + ansNum).css("display","none");	
			$("#ansVideoLoader_" + ansNum).ajaxStart(
					function () {
						$(this).show();
					}).ajaxStop(function () {
						$(this).hide();
					});
		}
	} else if (fileType == 'Image') {
		filePath = $("#answer_file_photo_" + ansNum).val();
		if (filePath == null || filePath == '') {
			filePath = document.getElementById("incyyte_photo_search_file").value;
			$('#searchedFileURL').val(document.getElementById("incyyte_photo_search_url").value);
			$('#searchedFileName').val(filePath);
		}
		if (filePath == null || filePath == '') {
			return false;
		}
		var ext = filePath.split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif','png','jpg','jpeg','bmp']) == -1) {
			$("#incyyte_ans_image_error_msg_" + ansNum).css("display","");	 
			return false;
		}else{
			$("#incyyte_ans_image_error_msg_" + ansNum).css("display","none");
			$("#ansPhotoLoader_" + ansNum).ajaxStart(
					function () {
						$(this).show();
					}).ajaxStop(function () {
						$(this).hide();
					});
		}
	} else if (fileType == 'Doc') {
		filePath = $("#answer_file_doc_" + ansNum).val();
		var ext = $('#answer_file_doc_' + ansNum).val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['doc','docx','log','rtf','txt','wpd','wps','csv','pps','ppt','xml','xlr','xls','xlsx','pdf']) == -1) {
			$("#incyyte_ans_doc_error_msg_" + ansNum).css("display","");	 
			return false;
		}else{
			$("#incyyte_ans_doc_error_msg_" + ansNum).css("display","none");
			$("#ansDocLoader_" + ansNum).ajaxStart(
					function () {
						$(this).show();
					}).ajaxStop(function () {
						$(this).hide();
					});
		}
	}
	if($("#answerChoice1").val()==''){
		$("#answerChoice1").val("Yes");
	}
	if($("#answerChoice2").val()==''){
		$("#answerChoice2").val("No");
	}
	if($("#answerChoice3").val()==''){
		$("#answerChoice3").val("Not Sure");
	}
	$('#answer_upload_opt').val(ansNum);
	$("#answerType" + ansNum).val(fileType);
	$('#answerFileName' + ansNum).val(filePath);
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'uploadAnsFile.cyt',
		success:function (data) {
			if (data.indexOf("failure") != -1) {
				if (fileType == 'Video') {
					$("#incyyte_ans_video_error_msg2_" + ansNum).css("display","");	 
				}else if (fileType == 'Image') {
					$("#incyyte_ans_image_error_msg2_" + ansNum).css("display","");	
				}else if (fileType == 'Doc') {
					$("#incyyte_ans_doc_error_msg2_" + ansNum).css("display","");
				}
			}
			else{
				$.fn.colorbox.close();
				$("#inCyyteForm").attr("action", "create_question.cyt");
				$("#inCyyteForm").attr("method", "POST");
				$("#inCyyteForm").submit();
				window.setTimeout(location.reload(true),1);
			}
		},
		error:function (jqXHR, textStatus, errorThrown) {
			if (fileType == 'Video') {
				$("#incyyte_ans_video_error_msg2_" + ansNum).css("display","");	 
			}else if (fileType == 'Image') {
				$("#incyyte_ans_image_error_msg2_" + ansNum).css("display","");	
			}else if (fileType == 'Doc') {
				$("#incyyte_ans_doc_error_msg2_" + ansNum).css("display","");
			}
		}
	});
}

function previewInCyyte() {
	var ansCount = $("#answer_count").val();

	if($("#answerChoice1").val()==''){
		$("#answerChoice1").val("Yes");
	}
	if($("#answerChoice2").val()==''){
		$("#answerChoice2").val("No");
	}
	if($("#answerChoice3").val()==''){
		$("#answerChoice3").val("Not Sure");
	}

	if(ansCount==0||ansCount==1||ansCount==2){
		ansCount = 3;
	}
	$("#answer_count").val(ansCount);
	$("#answer_upload_opt").val(ansCount);
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'send_question/preview.cyt',
		success:function (data) {
			parent.$.fn.colorbox({href:'send_question/displaypreview.cyt', open:true});
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}

function sendAnswers() {
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'storeAnswers.cyt',
		success:function (data) {
			parent.$.fn.colorbox({href:'send_question/displaypreview.cyt', open:true});
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}

function toggleLocalityFields() {
	var local = $('#locality option:selected').val();

	if (local == "Postcode") {
		$("#postcodeSec").css("display", "");
		$("#neighbourhoodSec").css("display", "none");
		$("#countySec").css("display", "none");
	} else if (local == "Region") {
		$("#neighbourhoodSec").css("display", "");
		$("#postcodeSec").css("display", "none");
		$("#countySec").css("display", "none");
	} else if (local == "County") {
		$("#countySec").css("display", "");
		$("#postcodeSec").css("display", "none");
		$("#neighbourhoodSec").css("display", "none");
	}
}

function countMembers() {

	if($("input:radio[name='sendType']:checked").val() == 'area'){

		$("#category_error").text("");
		$("#category_error").hide();		
		$("#region_error").text("");
		$("#region_error").hide();
		$("#agerange_error").text("");		
		$("#agerange_error").hide();

		if(validateRegion()){

			var local = $('#locality option:selected').val();
			if (local == "Postcode") {
				$("#inCyyteForm").ajaxSubmit({
					type:'POST',
					url:'countbypostcode.cyt',
					success:function (data) {
                        var pollCount  = data.split("|")[0];
                        var pollCharge  = data.split("|")[1];
                        var charges = "<br> <span style='color: #da4332;'><b>Charges apply</b> </span>";
						var msg = "<b> " + pollCount + " Poll Recipients</b> ";
						if (pollCount != null && pollCount != '' && pollCount > 0 && pollCharge > 0) {
							msg = msg + charges;
						}
						$("#pollRecipients").val(pollCount);
						$('#pollCount').html(msg);
					},
					error:function (jqXHR, textStatus, errorThrown) {
						alert("error:" + textStatus + " exception:" + errorThrown);
					}
				});
			} else if (local == "Region") {
				$("#inCyyteForm").ajaxSubmit({
					type:'POST',
					url:'countbyregion.cyt',
					success:function (data) {
                        var pollCount  = data.split("|")[0];
                        var pollCharge  = data.split("|")[1];
                        var charges = "<br> <span style='color: #da4332;'><b>Charges apply</b> </span>";
                        var msg = "<b> " + pollCount + " Poll Recipients</b> ";
                        if (pollCount != null && pollCount != '' && pollCount > 0 && pollCharge > 0) {
                            msg = msg + charges;
                        }
                        $("#pollRecipients").val(pollCount);
                        $('#pollCount').html(msg);
                    },
					error:function (jqXHR, textStatus, errorThrown) {
						alert("error:" + textStatus + " exception:" + errorThrown);
					}
				});
			} else if (local == "County") {
				$("#inCyyteForm").ajaxSubmit({
					type:'POST',
					url:'countbycounty.cyt',
					success:function (data) {
                        var pollCount  = data.split("|")[0];
                        var pollCharge  = data.split("|")[1];
                        var charges = "<br> <span style='color: #da4332;'><b>Charges apply</b> </span>";
                        var msg = "<b> " + pollCount + " Poll Recipients</b> ";
                        if (pollCount != null && pollCount != '' && pollCount > 0 && pollCharge > 0) {
                            msg = msg + charges;
                        }
                        $("#pollRecipients").val(pollCount);
                        $('#pollCount').html(msg);
                    },
					error:function (jqXHR, textStatus, errorThrown) {
						alert("error:" + textStatus + " exception:" + errorThrown);
					}
				});
			}
		}
	}
}

function submitInCyytePoll() {
    if (contentUploaded()) {
        parent.$.fn.colorbox({'href':'div#upload_content', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
        return;
    } else {
        sendInCyytePoll();
    }
}

function contentUploaded() {
    if (($("#uploadedFile").val() != null && $("#uploadedFile").val() != undefined)
        || ($('#file_name_tst').val() != '')
        || ($("#youTubeQuestionVideoId").val() != null && $("#youTubeQuestionVideoId").val() != undefined && $("#youTubeQuestionVideoId").val() != '')
        || ($("#youTubeAnswer_1_VideoId").val() != null && $("#youTubeAnswer_1_VideoId").val() != undefined && $("#youTubeAnswer_1_VideoId").val() != '')
        || ($("#youTubeAnswer_2_VideoId").val() != null && $("#youTubeAnswer_2_VideoId").val() != undefined && $("#youTubeAnswer_2_VideoId").val() != '')
        || ($("#youTubeAnswer_3_VideoId").val() != null && $("#youTubeAnswer_3_VideoId").val() != undefined && $("#youTubeAnswer_3_VideoId").val() != '')
        || ($("#youTubeAnswer_4_VideoId").val() != null && $("#youTubeAnswer_4_VideoId").val() != undefined && $("#youTubeAnswer_4_VideoId").val() != '')
        || ($("#youTubeAnswer_5_VideoId").val() != null && $("#youTubeAnswer_5_VideoId").val() != undefined && $("#youTubeAnswer_5_VideoId").val() != '')
        || ($("#youTubeAnswer_6_VideoId").val() != null && $("#youTubeAnswer_6_VideoId").val() != undefined && $("#youTubeAnswer_6_VideoId").val() != '')
        || ($("#youTubeAnswer_7_VideoId").val() != null && $("#youTubeAnswer_7_VideoId").val() != undefined && $("#youTubeAnswer_7_VideoId").val() != '')
        || ($("#youTubeAnswer_8_VideoId").val() != null && $("#youTubeAnswer_8_VideoId").val() != undefined && $("#youTubeAnswer_8_VideoId").val() != '')
        || ($("#youTubeAnswer_9_VideoId").val() != null && $("#youTubeAnswer_9_VideoId").val() != undefined && $("#youTubeAnswer_9_VideoId").val() != '')
        || ($("#youTubeAnswer_10_VideoId").val() != null && $("#youTubeAnswer_10_VideoId").val() != undefined && $("#youTubeAnswer_10_VideoId").val() != '')
        || ($("#answer_uploaded_url_1").val() != null && $("#answer_uploaded_url_1").val() != undefined && $("#answer_uploaded_url_1").val() != '')
        || ($("#answer_uploaded_url_2").val() != null && $("#answer_uploaded_url_2").val() != undefined && $("#answer_uploaded_url_2").val() != '')
        || ($("#answer_uploaded_url_3").val() != null && $("#answer_uploaded_url_3").val() != undefined && $("#answer_uploaded_url_3").val() != '')
        || ($("#answer_uploaded_url_4").val() != null && $("#answer_uploaded_url_4").val() != undefined && $("#answer_uploaded_url_4").val() != '')
        || ($("#answer_uploaded_url_5").val() != null && $("#answer_uploaded_url_5").val() != undefined && $("#answer_uploaded_url_5").val() != '')
        || ($("#answer_uploaded_url_6").val() != null && $("#answer_uploaded_url_6").val() != undefined && $("#answer_uploaded_url_6").val() != '')
        || ($("#answer_uploaded_url_7").val() != null && $("#answer_uploaded_url_7").val() != undefined && $("#answer_uploaded_url_7").val() != '')
        || ($("#answer_uploaded_url_8").val() != null && $("#answer_uploaded_url_8").val() != undefined && $("#answer_uploaded_url_8").val() != '')
        || ($("#answer_uploaded_url_9").val() != null && $("#answer_uploaded_url_9").val() != undefined && $("#answer_uploaded_url_9").val() != '')
        || ($("#answer_uploaded_url_10").val() != null && $("#answer_uploaded_url_10").val() != undefined && $("#answer_uploaded_url_10").val() != '')
        ) {
        return true;
    }
    return false;
}

function checkUploadedContent() {
    parent.$.fn.colorbox.close();
}
function sendInCyytePoll() {
    parent.$.fn.colorbox.close();
    incyyte_validator.form();
	if($("#answerChoice1").val()==''){
		$("#answerChoice1").val("Yes");
	}
	if($("#answerChoice2").val()==''){
		$("#answerChoice2").val("No");
	}
	if($("#answerChoice3").val()==''){
		$("#answerChoice3").val("Not Sure");
	}
	validateAns();
	validateCategory();
	validateGroupSel();
	validateRegion();	
	validateEmail();
	var postcodeofSender = $('#postcodeofSender').val();
	var regionofSender = postcodeofSender.split(" ")[0];
	var recipentRegion = $('#Neighbourhood').val();
	if ((recipentRegion == null || recipentRegion == '') && ($("input:radio[name='sendType']:checked").val() == 'area')) {
		var recipentPostCode= $('#postcodex').val();
		recipentRegion = recipentPostCode.split(" ")[0];
	}
	var pollReceipientCount = $("#pollRecipients").val();
    if(($("input:radio[name='sendType']:checked").val() == 'area')  && (pollReceipientCount != 0) && (regionofSender != recipentRegion)){
		if(validateRegion()){
			parent.$.fn.colorbox({'href':'div.poll_pay', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
		}
	 } else if(($("#inCyyteForm").valid() && validateAns() && validateCategory() && validateGroupSel() && validateEmail() && validateRegion()) || ($("#inCyyteForm").valid() &&  postcodeofSender == postcodeofRegion) ){
		 if($("input:radio[name='sendType']:checked").val() == 'post' ){
				 if($("#checkbox-1").attr("checked")== 'checked'){
				    $(".edit_pro_pop_txt").text("Incyyte polls that are attached to exclusive poll pages cannot be set as anonymous. Please deselect anonymous checkbox to attach this poll");
					parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
				 }else{	
					if(validatePageName()){
						$("#inCyyteForm").ajaxSubmit({
							type:'POST',
							url:'validatepagename.cyt',
							success:function (data) {						
								if (data.indexOf("success") != -1) {
									$("#pageName_error").text("");
    								$("#pageName_error").hide();
									submitInCyyteForm();
								}
								else{
									$("#pageName_error").text("The page name is already exist");
									$("#pageName_error").show();							
								}
							},
							error:function (jqXHR, textStatus, errorThrown) {
							}
						});	
					}
				 }
			}	
		 	else{		 		
		 		submitInCyyteForm();
		 	}
		}
}

function submitInCyyteForm() {
	
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'send_question/submit.cyt',
		success:function (data) {
			if (data.indexOf("send_question") != -1) {
				loginProceessInModelLoginPage('create_incyyte');
			}
			else if(data == 'deactivated'){
				parent.$.fn.colorbox({'href':'div#loginErrorForDeactAcct', 'open':true, 'inline':true});
			}
			else if(data == 'pollCountError'){	
			    parent.$.fn.colorbox({'href':'div.pollEmailCountPopup_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
			}
			else if(data == 'no_msg_sent'){				
				parent.$.fn.colorbox({'href':'div#no_msg_sent_dialog', 'open':true, 'inline':true});
			}
			else if(data == "dash"){
				window.location = "dash.cyt";
			}
			else if(data.indexOf("POST") != -1){
					window.location = data.substring(5);
			}else{
				parent.$.fn.colorbox({'href':'div#no_msg_sent_dialog', 'open':true, 'inline':true});
			}
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}

function closeDatePicker() {
	$('#closingDateVal').text($('#alternate').val());
	$('#selecttimeVal').text($('#selecttime').val());

	var clsDate = $('#alternate').val();
	var clsTime = $('#selecttime').val();
	$('#closure_Date').val(clsDate + " " + clsTime);
	parent.$.fn.colorbox.close();
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'getClosureDate.cyt',
		success:function (data) {
			parent.$.fn.colorbox.close();
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}
function sendYourPoll(){
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'send_poll.cyt',
		success:function (data) {
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}
function addEmail(){
	$("#email_error").hide();
	if(validateEmail()){
		$("#inCyyteForm").ajaxSubmit({
			type:'POST',
			url:'addEmailAddress.cyt',
			success:function (data) {
			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});
	}
}
function pollSetting(){
	$("#inCyyteForm").ajaxSubmit({
		url:'pollSettings.cyt',
		success:function (data) {
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}

function submitFgtPwd() {
	$("#create_fgtpwdform").ajaxSubmit({
		type:'POST',
		url:'send_question/fpassword.cyt',
		success:function (data) {
			if (data == 'success') {
				$(".fgtpwd").css("display","none");
				$(".fgpwdmsg").css("display","");
				$("#login_acc").click(function(){
					parent.$.fn.colorbox({'href':'form#create_loginForm', 'open':true, 'inline':true});
				});
				$("#login_back").click(function(){
					window.location = "create_question.cyt";
					$.fn.colorbox.close();
				});
			}
			else {
				$('#fgtpwdErrmsg').show('fast');
			}
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}


function getActivationMsg(email) {
	var msg = "Congratulations!!! Your account has now been setup. Your Activation code has been mailed to your email address ('" + email + "'). <BR/> Please <strong>enter you activation code</strong> in the text field below to access your account";
	return msg;
}

function createAnswerRow(){
	var ansCount = $("#answer_count").val();
	if(ansCount==0||ansCount==1||ansCount==2){
		ansCount = 3;
	}
	ansCount++;

	$("#answer_upload_opt").val(ansCount);
	//window.location.reload(true);
	var maxOption = $("#answerMaxOption").val();
	if (ansCount  <= maxOption) {
		$("#answer_count").val(ansCount);
		$("#inCyyteForm").ajaxSubmit({
			type:'POST',
			url:'createNewRow.cyt',
			success:function (data) {
				setTimeout("location.reload(true);", 1);				
			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});
	}

	if (ansCount  >= 10) {
		$("#add_an_answer").hide("slow");
	}
	setCategory();
}

//started the serach functionality in add contacts form 
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
     httpRequest.open("GET", "searchAddContactsGetIncyyte.cyt?param=" + param  , true);
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
	
		var spanValue  =  "<span id='group_1'>" 
						+ '<input type="checkbox"  class="contactList" name="selectedGroupContactsList" onclick="selectContact('+"'contactId'+" + contactList[i].contactIdValue +');"  id="contactId'+ contactList[i].contactIdValue +'"  value="' + contactList[i].contactEmail +'" /> ';
			
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
		$('#create_incyyte').show();
		if(contactList.length < 10) {
			$('#create_incyyte').hide();
		}
	};

}
//ends the search in add contacts form

function selectIndividualContact(selectedContacId){
	$("#"+selectedContacId).attr("checked", "checked");
}

function deleteAnswerRow() {
	var ansCount = $("#answer_count").val();
	$("#answerChoice"+ ansCount+"_error").hide();
	if (ansCount > 3) {
		$("#ans_div"+ansCount).remove();
		$("#answer_count").val(ansCount-1);
		$("#answer_upload_opt").val(ansCount);
		$("#inCyyteForm").ajaxSubmit({
			type:'POST',
			url:'deleteNewRow.cyt',
			success:function (data) {
			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});

	}
	var maxOption = $("#answerMaxOption").val();	
	if (ansCount  <= 10) {
		$("#add_an_answer").show("slow");
	}
	setCategory();
}

function setResponses() {
	var resps = "";
	$("input:text").each(function (index) {
		// if input doesn't have an id, skip the whole process there is no need to count it
		var inputID = $(this).attr("id");
		if (inputID != null) {
			if (inputID.indexOf("answerChoice") != -1) {
				resps += $("#" + inputID).val() + "|";
			}
		}
	}); // each function

	$("#responses").val(resps);
	$("#answer_count").val(row);

	for (var i = 1; i < row; i++) {
		$("#answer_opt_" + i).val($("#answerChoice" + i).val());
	}
}

function displayContactList(){	
	$("#contactForm").ajaxSubmit({
		type:'POST',
		url:'displaycontactlist.cyt',
		success:function (data) {
			$("#emailList").load("loadcontactlist.cyt").show().css("top", "400px").animate({top: 50}, 200);
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}