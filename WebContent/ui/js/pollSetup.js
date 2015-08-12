$(document).ready(function () {
	$("#errorDeleteImage").css("display","none");
	document_onInit();
	valueFromPollPreview();
});

function document_onInit() {

	$(".link1").click(function(){
		$("#link1").slideDown();
		$(".open1").show();
		$("#opencolor1").css("color","red");
		$("#opencolor2").css("color","#1b303f");
		$("#opencolor3").css("color","#1b303f");
		$(".open2").hide();
		$(".open3").hide();
		$(".link1").hide();
		$("#link2").hide();
		$("#link3").hide();
		$(".link2").show();
		$(".link3").show();
		for(var j=1 ;j<=10;j++){$("#add_files"+j).hide();$("#view_image"+j).hide();}

	});
	$(".link2").click(function(){
		$("#link2").slideDown();
		$("#opencolor2").css("color","red");
		$("#opencolor1").css("color","#1b303f");
		$("#opencolor3").css("color","#1b303f");
		$(".link2").hide();
		$("#link1").hide();
		$("#link3").hide();
		$(".link1").show();
		$(".link3").show();
		$(".open2").show();
		$(".open1").hide();
		$(".open3").hide();
		for(var j=1 ;j<=10;j++){$("#add_files"+j).hide();$("#view_image"+j).hide();}
	});
	$(".link3").click(function(){
		$("#link3").slideDown();
		$("#opencolor3").css("color","red");
		$("#opencolor2").css("color","#1b303f");
		$("#opencolor1").css("color","#1b303f");
		$(".link3").hide();
		$("#link1").hide();
		$("#link2").hide();
		$(".link1").show();
		$(".link2").show();
		$(".open3").show();
		$(".open2").hide();
		$(".open1").hide();
		for(var j=1 ;j<=10;j++){$("#add_files"+j).hide();}

	});

	$('#searchSubmit_new_logo').click(function () {
		googleImageSearchForLogo();
	});
	$('#searchSubmit_new_banner').click(function () {
		googleImageSearchForBanner();
	});

	$('#search_submit_1').click(function () {
		googleImageSearchForImg1();
	});
	$('#search_submit_2').click(function () {
		googleImageSearchForImg2();
	});
	$('#search_submit_3').click(function () {
		googleImageSearchForImg3();
	});
	$('#search_submit_4').click(function () {
		googleImageSearchForImg4();
	});
	$('#search_submit_5').click(function () {
		googleImageSearchForImg5();
	});
	$('#search_submit_6').click(function () {
		googleImageSearchForImg6();
	});
	$('#search_submit_7').click(function () {
		googleImageSearchForImg7();
	});
	$('#search_submit_8').click(function () {
		googleImageSearchForImg8();
	});
	$('#search_submit_9').click(function () {
		googleImageSearchForImg9();
	});
	$('#search_submit_10').click(function () {
		googleImageSearchForImg10();
	});
	$('#show_more_images_logo').click(function() {
		showImages();
	});
	$('#show_more_images_banner').click(function() {
		showImages();
	});

	$('#show_more_images_1').click(function() {
		showImages();
	});
	$('#show_more_images_2').click(function() {
		showImages();
	});
	$('#show_more_images_3').click(function() {
		showImages();
	});
	$('#show_more_images_4').click(function() {
		showImages();
	});
	$('#show_more_images_5').click(function() {
		showImages();
	});
	$('#show_more_images_6').click(function() {
		showImages();
	});
	$('#show_more_images_7').click(function() {
		showImages();
	});
	$('#show_more_images_8').click(function() {
		showImages();
	});
	$('#show_more_images_9').click(function() {
		showImages();
	});
	$('#show_more_images_10').click(function() {
		showImages();
	});

	$(".uploadimg1").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg1").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files1").fadeIn();

		var searchValue = document.getElementById("search_value_1");
		var searchLoad = document.getElementById("search_result_1");
		var searchSelect = "image_searched_1";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});
	$(".uploadimg2").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg2").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files2").fadeIn();

		var searchValue = document.getElementById("search_value_2");
		var searchLoad = document.getElementById("search_result_2");
		var searchSelect = "image_searched_2";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});
	$(".uploadimg3").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg3").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files3").fadeIn();

		var searchValue = document.getElementById("search_value_3");
		var searchLoad = document.getElementById("search_result_3");
		var searchSelect = "image_searched_3";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});
	$(".uploadimg4").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg4").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files4").fadeIn();

		var searchValue = document.getElementById("search_value_4");
		var searchLoad = document.getElementById("search_result_4");
		var searchSelect = "image_searched_4";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});
	$(".uploadimg5").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg5").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files5").fadeIn();

		var searchValue = document.getElementById("search_value_5");
		var searchLoad = document.getElementById("search_result_5");
		var searchSelect = "image_searched_5";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});
	$(".uploadimg6").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg6").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files6").fadeIn();

		var searchValue = document.getElementById("search_value_6");
		var searchLoad = document.getElementById("search_result_6");
		var searchSelect = "image_searched_6";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});
	$(".uploadimg7").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg7").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files7").fadeIn();

		var searchValue = document.getElementById("search_value_7");
		var searchLoad = document.getElementById("search_result_7");
		var searchSelect = "image_searched_7";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});
	$(".uploadimg8").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg8").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files8").fadeIn();

		var searchValue = document.getElementById("search_value_8");
		var searchLoad = document.getElementById("search_result_8");
		var searchSelect = "image_searched_8";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});
	$(".uploadimg9").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg9").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files9").fadeIn();

		var searchValue = document.getElementById("search_value_9");
		var searchLoad = document.getElementById("search_result_9");
		var searchSelect = "image_searched_9";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});
	$(".uploadimg10").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.uploadimg10").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#add_files10").fadeIn();

		var searchValue = document.getElementById("search_value_10");
		var searchLoad = document.getElementById("search_result_10");
		var searchSelect = "image_searched_10";
		if(searchValue != ""){
        	$('#googleSearchTrigger').val(1);
			loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
		}
	});

	$(".view_image1").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image1").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image1").fadeIn();
		document.getElementById("photoImg1").setAttribute("src", $("#ImageURL1").val());
		var pName = $("#imageName1").val().substring(0,13).concat("...");
		$('#viewFileName1').text(pName);
	});
	$(".view_image2").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image2").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image2").fadeIn();
		document.getElementById("photoImg2").setAttribute("src", $("#ImageURL2").val());
		var pName = $("#imageName2").val().substring(0,13).concat("...");
		$('#viewFileName2').text(pName);
	});
	$(".view_image3").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image3").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image3").fadeIn();
		document.getElementById("photoImg3").setAttribute("src", $("#ImageURL3").val());
		var pName = $("#imageName3").val().substring(0,13).concat("...");
		$('#viewFileName3').text(pName);
	});
	$(".view_image4").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image4").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image4").fadeIn();
		document.getElementById("photoImg4").setAttribute("src", $("#ImageURL4").val());
		var pName = $("#imageName4").val().substring(0,13).concat("...");
		$('#viewFileName4').text(pName);
	});
	$(".view_image5").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image5").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image5").fadeIn();
		document.getElementById("photoImg5").setAttribute("src", $("#ImageURL5").val());
		var pName = $("#imageName5").val().substring(0,13).concat("...");
		$('#viewFileName5').text(pName);
	});
	$(".view_image6").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image6").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image6").fadeIn();
		document.getElementById("photoImg6").setAttribute("src", $("#ImageURL6").val());
		var pName = $("#imageName6").val().substring(0,13).concat("...");
		$('#viewFileName6').text(pName);
	});
	$(".view_image7").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image7").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image7").fadeIn();
		document.getElementById("photoImg7").setAttribute("src", $("#ImageURL7").val());
		var pName = $("#imageName7").val().substring(0,13).concat("...");
		$('#viewFileName7').text(pName);
	});
	$(".view_image8").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image8").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image8").fadeIn();
		document.getElementById("photoImg8").setAttribute("src", $("#ImageURL8").val());
		var pName = $("#imageName8").val().substring(0,13).concat("...");
		$('#viewFileName8').text(pName);
	});
	$(".view_image9").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image9").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image9").fadeIn();
		document.getElementById("photoImg9").setAttribute("src", $("#ImageURL9").val());
		var pName = $("#imageName9").val().substring(0,13).concat("...");
		$('#viewFileName9').text(pName);
	});
	$(".view_image10").click(function(){
		$(".poll_img_txt a").css ("color" , "#1b303f");
		$("a.view_image10").css("color","red");
		for(var  j=1 ;j<=10;j++){$("#add_files"+j).hide(); $("#view_image"+j).hide();}
		$("#view_image10").fadeIn();
		document.getElementById("photoImg10").setAttribute("src", $("#ImageURL10").val());
		var pName = $("#imageName10").val().substring(0,13).concat("...");
		$('#viewFileName10').text(pName);
	});

	$("#imageBrowseButton1").click(function(){
		$('#image1').click();
	});
	$("#imageBrowseButton2").click(function(){
		$('#image2').click();
	});
	$("#imageBrowseButton3").click(function(){
		$('#image3').click();
	});
	$("#imageBrowseButton4").click(function(){
		$('#image4').click();
	});
	$("#imageBrowseButton5").click(function(){
		$('#image5').click();
	});
	$("#imageBrowseButton6").click(function(){
		$('#image6').click();
	});
	$("#imageBrowseButton7").click(function(){
		$('#image7').click();
	});
	$("#imageBrowseButton8").click(function(){
		$('#image8').click();
	});
	$("#imageBrowseButton9").click(function(){
		$('#image9').click();
	});
	$("#imageBrowseButton10").click(function(){
		$('#image10').click();
	});

	$("#browseLogoImage").click(function(){
		$('#logoImage').click();
	});
	$("#browseBannerImage").click(function(){
		$('#bannerImage').click();
	});

	$('#pageHeader').change(function () {
		promptSaveChanges();
	});

	//to display file names
	$('#logoImage').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#logoFileName').text(pName);
	});
	$('#bannerImage').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#bannerFileName').text(pName);
	});
	$('#image1').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName1').text(pName);
	});
	$('#image2').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName2').text(pName);
		$('#viewFileName2').text(pName);
	});
	$('#image3').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName3').text(pName);
	});
	$('#image4').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName4').text(pName);
	});
	$('#image5').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName5').text(pName);
	});

	$('#image6').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName6').text(pName);
	});
	$('#image7').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName7').text(pName);
	});
	$('#image8').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName8').text(pName);
	});
	$('#image9').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName9').text(pName);
	});
	$('#image10').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#fileName10').text(pName);
	});


	$('#bannerUploadButton').click(function () {
		uploadBannerFunc();
	});
	$('#logoUploadButton').click(function () {
		uploadLogoFunc();
	});
	$('#incyyteUploadPhotoButton1').click(function () {
		uploadImageFunc('1');
	});
	$('#incyyteUploadPhotoButton2').click(function () {
		uploadImageFunc('2');
	});
	$('#incyyteUploadPhotoButton3').click(function () {
		uploadImageFunc('3');
	});
	$('#incyyteUploadPhotoButton4').click(function () {
		uploadImageFunc('4');
	});
	$('#incyyteUploadPhotoButton5').click(function () {
		uploadImageFunc('5');
	});
	$('#incyyteUploadPhotoButton6').click(function () {
		uploadImageFunc('6');
	});
	$('#incyyteUploadPhotoButton7').click(function () {
		uploadImageFunc('7');
	});
	$('#incyyteUploadPhotoButton8').click(function () {
		uploadImageFunc('8');
	});
	$('#incyyteUploadPhotoButton9').click(function () {
		uploadImageFunc('9');
	});
	$('#incyyteUploadPhotoButton10').click(function () {
		uploadImageFunc('10');
	});
	$('#imageDeleteButton1').click(function () {
		deleteImagesFunc('1');
	});
	$('#imageDeleteButton2').click(function () {
		deleteImagesFunc('2');
	});
	$('#imageDeleteButton3').click(function () {
		deleteImagesFunc('3');
	});
	$('#imageDeleteButton4').click(function () {
		deleteImagesFunc('4');
	});
	$('#imageDeleteButton5').click(function () {
		deleteImagesFunc('5');
	});
	$('#imageDeleteButton6').click(function () {
		deleteImagesFunc('6');
	});
	$('#imageDeleteButton7').click(function () {
		deleteImagesFunc('7');
	});
	$('#imageDeleteButton8').click(function () {
		deleteImagesFunc('8');
	});
	$('#imageDeleteButton9').click(function () {
		deleteImagesFunc('9');
	});
	$('#imageDeleteButton10').click(function () {
		deleteImagesFunc('10');
	});
	$('#logoDeleteButton').click(function () {
		deleteLogoFunc();
	});
	$('#bannerDeleteButton').click(function () {
		deleteBannerFunc();
	});

	$('#saveTemplate').click(function () {
		saveChanges();
	});	
	
	$('#saveChangesButton').click(function () {
		saveChanges();
	});
	
	$('#cancelSaveTemplate').click(function () {
		parent.$.fn.colorbox.close();
	});
	
	$('#previewButton').click(function () {
		previewSaveChanges();
	});

    $('#newTemplate').click(function () {
    	addNewTemplate();
    });
    
    $('#templateId').change(function () {
    	loadTemplate();
    });
	
	function deleteImagesFunc(imgNum) {
		$("#errorDeleteImage").css("display","none");
		$('#uploadedFileType').val('IMAGE'+imgNum);
		$('#uploadedFileName').val($('#image'+imgNum+'CdnFileName').val());
		$("#pollSetupForm").ajaxSubmit({
			type:'POST',
			url:'deleteUploadedImages.cyt',
			success:function (data) {
				if(data == "failure"){
					$("#errorDeleteImage").css("display","");
				}else{
					window.setTimeout(location.reload(true),1);
				}

			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});
	}
	function deleteLogoFunc() {
		$('#uploadedFileType').val('LOGO');
		$('#uploadedFileName').val($('#logoCdnFileName').val());
		$("#pollSetupForm").ajaxSubmit({
			type:'POST',
			url:'deleteUploadedImages.cyt',
			success:function (data) {
				if(data == "failure"){
				}else{
					window.setTimeout(location.reload(true),1);
				}

			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});
	}
	function deleteBannerFunc() {
		$('#uploadedFileType').val('BANNER');
		$('#uploadedFileName').val($('#bannerCdnFileName').val());
		$("#pollSetupForm").ajaxSubmit({
			type:'POST',
			url:'deleteUploadedImages.cyt',
			success:function (data) {
				if(data == "failure"){
				}else{
					window.setTimeout(location.reload(true),1);
				}

			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});
	}
	
	function loadTemplate(){		
		$("#pollSetupForm").attr("action", "loadTemplate.cyt");
		$("#pollSetupForm").submit();	
	}
	
	function addNewTemplate(){		
			$("#pollSetupForm").attr("action", "newPageTemplate.cyt");
			$("#pollSetupForm").submit();	
	}
	
	function previewSaveChanges(){
		if(isHeaderValid()  && isAddress1Valid() && isAddress2Valid() && isCityValid() && isPostcodeValid() && isContactEmailValid() && isWebsiteUrlValid() && isPhoneNumber1Valid() && isPhoneNumber2Valid() ){
			//$("#pollSetupForm").attr("action", "pollPreview.cyt");
			//$("#pollSetupForm").submit();			
			$("#pollSetupForm").ajaxSubmit({
				type:'POST',
				url:'saveChanges.cyt',
				success:function (data) {
					$("#pollSetupForm").attr("action", "pollPreview.cyt");
					$("#pollSetupForm").submit();
				},
				error:function (jqXHR, textStatus, errorThrown) {
					alert("error:" + textStatus + " exception:" + errorThrown);
				}
			});
		}
	}
	function saveChanges(){
		if(isHeaderValid()  && isAddress1Valid() && isAddress2Valid() && isCityValid() && isPostcodeValid() && isContactEmailValid() && isWebsiteUrlValid() && isPhoneNumber1Valid() && isPhoneNumber2Valid() ){
			$("#pollSetupForm").ajaxSubmit({
				type:'POST',
				url:'saveChanges.cyt',
				success:function (data) {
					parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
					$('#cboxClose').remove();
				},
				error:function (jqXHR, textStatus, errorThrown) {
					alert("error:" + textStatus + " exception:" + errorThrown);
				}
			});
		}
	}
	
	function promptSaveChanges(){	
		if(isHeaderValid()){
			$("#save_prompt_txt").html($("#pageHeader").val());
			parent.$.fn.colorbox({'href':'div.Poll_save_prompt_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
			$('#cboxClose').remove();
		}
	}
	
	function uploadImageFunc(imgNum) {
		$('#imageLink'+imgNum).val($('#imageLinkText'+imgNum).val());
		$("#errorForImageFormat"+imgNum).css("display","none");
		filePath = $('#image'+imgNum).val();
		if (filePath == null || filePath == '') {
			filePath = document.getElementById("incyyte_photo_search_file").value;
			$('#searchedFileNamePollPage').val(document.getElementById("incyyte_photo_search_file").value);
			$('#searchedFileURLPollPage').val(document.getElementById("incyyte_photo_search_url").value);			
		}			
		//commented by Remi
		//var fileName = filePath.substring(12);
		var fileName = filePath;
		//TODO - need to decide on the size of the file name
		if(filePath != null && filePath.length > 20){
			fileName = filePath.substr(filePath.length - 12);
		}
		
		$('#imageName'+imgNum).val(fileName);
				
		var ext = filePath.split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif','png','jpg','jpeg','bmp']) == -1) {
			$("#errorForImageFormat"+imgNum).css("display","");
			$(".errorLabel").text("Please upload the correct image format (jpg, gif, ..)");
			return false;
		} else{
			$("#errorForImageFormat"+imgNum).css("display","none");
			$("#imageLoader"+imgNum).ajaxStart(
					function () {
						$(this).show();
					}).ajaxStop(function () {
						$(this).hide();
					});
		}
		$("#pollSetupForm").ajaxSubmit({
			type:'POST',
			url:'uploadImage.cyt',
			success:function (data) {
				if (data.indexOf("failure") != -1) {
					$("#errorForImageFormat"+imgNum).css("display","");
					$(".errorLabel").text("The uploaded image exceeds the maximum allowed size(2 MB)");
				}else{
					$("#imageLink"+imgNum).css("display","none");
					$("#pollSetupForm").attr("action", "uploadImage.cyt");
					$("#pollSetupForm").attr("method", "POST");
					$("#pollSetupForm").submit();
					$("#uploadedSection").val("uploaded");
					//window.location = "pollSetup.cyt?uploadedSection=uploaded";
					window.setTimeout(location.reload(true),1);
					$("#view_image").hide();
					$("#imagelink1").css("display","none");
				}
			},
			error:function (jqXHR, textStatus, errorThrown) {
				$("#incyyte_ans_image_error_msg2_" + ansNum).css("display","");
			}
		});
	}
	function uploadLogoFunc() {
		$('#logoLink').val($('#logoLinkText').val());
		$("#errorForLogoFormat").css("display","none");
		filePath = $('#logoImage').val();
		if (filePath == null || filePath == '') {
			filePath = document.getElementById("incyyte_photo_search_file").value;
			$('#searchedFileNamePollPage').val(document.getElementById("incyyte_photo_search_file").value);
			$('#searchedFileURLPollPage').val(document.getElementById("incyyte_photo_search_url").value);
			// $('#logoImageName').val(filePath); 
		}
		
		//commented by Remi
		//var fileName = filePath.substring(12);
		//alert("filePath - " + filePath);
		var fileName = filePath;
		//TODO - need to decide on the size of the file name
		if(filePath != null && filePath.length > 20){
			fileName = filePath.substr(filePath.length - 12);
		}
		//alert("fileName - " + fileName);
		$('#logoImageName').val(fileName);
		var ext = filePath.split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif','png','jpg','jpeg','bmp']) == -1) {
			$("#errorForLogoFormat").css("display","");
			$(".errorLabel").text("Please upload the correct image format (jpg, gif, ..)");
			return false;
		}
		else{
			$("#imageLoaderLogo").ajaxStart(function(){
				$(this).show();
			}).ajaxStop(function(){
				$(this).hide();
			});
		}       
		
		if ($('#logoImageName').val() != null && $('#logoImageName').val() != '') {
			
			$("#pollSetupForm").ajaxSubmit({
				type:'POST',
				url:'uploadImage.cyt',
				success:function (data) {
					if (data.indexOf("failure") != -1) {
						$("#errorForLogoFormat").css("display","");
						$(".errorLabel").text("The uploaded image exceeds the maximum allowed size(2 MB)");
					}else{
						$("#pollSetupForm").attr("action", "uploadImage.cyt");
						$("#pollSetupForm").attr("method", "POST");
						$("#pollSetupForm").submit();
						window.setTimeout(location.reload(true),1);
						$("#view_image").hide();
	
					}
				},
				error:function (jqXHR, textStatus, errorThrown) {
					$("#errorForLogoFormat").css("display","");
				}
			});
		}
		else{
			$("#errorForLogoFormat").css("display","");
			$(".errorLabel").text("Please upload the correct image format (jpg, gif, ..)");
			return false;
		}
	}

	function uploadBannerFunc() {
		$('#bannerLink').val($('#bannerLinkText').val());
		$("#errorForBannerFormat").css("display","none");
		filePath = $('#bannerImage').val();
		if (filePath == null || filePath == '') {
			filePath = document.getElementById("incyyte_photo_search_file").value;
			$('#searchedFileNamePollPage').val(document.getElementById("incyyte_photo_search_file").value);
			$('#searchedFileURLPollPage').val(document.getElementById("incyyte_photo_search_url").value);
			$('#bannerImageName').val(filePath); 
		}
		
		//commented by Remi
		//var fileName = filePath.substring(12);
		
		var fileName = filePath;
		//TODO - need to decide on the size of the file name
		if(filePath != null && filePath.length > 20){
			fileName = filePath.substr(filePath.length - 12);
		}
		
		$('#bannerImageName').val(fileName);
		var ext = filePath.split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif','png','jpg','jpeg','bmp']) == -1) {
			$("#errorForBannerFormat").css("display","");
			$(".errorLabel").text("Please upload the correct image format (jpg, gif, ..)");
			return false;
		}
		else{
			$("#imageLoaderBanner").ajaxStart(
					function () {
						$(this).show();
					}).ajaxStop(function () {
						$(this).hide();
					});
		}
		
		if ($('#bannerImageName').val() != null && $('#bannerImageName').val() != '') {
		
			$("#pollSetupForm").ajaxSubmit({
				type:'POST',
				url:'uploadImage.cyt',
				success:function (data) {
					if (data.indexOf("failure") != -1) {
						$("#errorForBannerFormat").css("display","");
						$(".errorLabel").text("The uploaded image exceeds the maximum allowed size(2 MB)");
					}else{
						$("#pollSetupForm").attr("action", "uploadImage.cyt");
						$("#pollSetupForm").attr("method", "POST");
						$("#pollSetupForm").submit();
						window.setTimeout(location.reload(true),1);
					}
				},
				error:function (jqXHR, textStatus, errorThrown) {
					$("#errorForBannerFormat").css("display","");
				}
			});
		}
		else{
			$("#errorForBannerFormat").css("display","");
			$(".errorLabel").text("Please upload the correct image format (jpg, gif, ..)");
			return false;
		}
	}

}

function editPollSetupFromPreview(category){
	window.location = "pollSetup.cyt?editFrom="+category;
}

function valueFromPollPreview(){
	var editFrom = $('#editFrom').val();
	if (editFrom == 1) {
		$("#link1").slideDown();
		$(".open1").show();
		$("#opencolor1").css("color","red");
		$("#opencolor2").css("color","#1b303f");
		$("#opencolor3").css("color","#1b303f");
		$(".open2").hide();
		$(".open3").hide();
		$(".link1").hide();
		$("#link2").hide();
		$("#link3").hide();
		$(".link2").show();
		$(".link3").show();
		for(var j=1 ;j<=10;j++){$("#add_files"+j).hide();$("#view_image"+j).hide();}
	} else if (editFrom == 2) {
		$("#link2").slideDown();
		$("#opencolor2").css("color","red");
		$("#opencolor1").css("color","#1b303f");
		$("#opencolor3").css("color","#1b303f");
		$(".link2").hide();
		$("#link1").hide();
		$("#link3").hide();
		$(".link1").show();
		$(".link3").show();
		$(".open2").show();
		$(".open1").hide();
		$(".open3").hide();
		for(var j=1 ;j<=10;j++){$("#add_files"+j).hide();$("#view_image"+j).hide();}
	} else if (editFrom == 3) {
		$("#link3").slideDown();
		$("#opencolor3").css("color","red");
		$("#opencolor2").css("color","#1b303f");
		$("#opencolor1").css("color","#1b303f");
		$(".link3").hide();
		$("#link1").hide();
		$("#link2").hide();
		$(".link1").show();
		$(".link2").show();
		$(".open3").show();
		$(".open2").hide();
		$(".open1").hide();
		for(var j=1 ;j<=10;j++){$("#add_files"+j).hide();}
	}

}

function isHeaderValid() {
	$('#headerError').css("display", "none");
	var pageHeader = $("#pageHeader").val();
	var valid = true;
	var min = 5;
	var max = 30;
	valid = checkLength($('#pageHeader'), "pageHeader", min, max);
	if (pageHeader == "") {
		$('#headerError').text("Your pageHeader is required");
		$('#headerError').css("display", "");
		return false;
	}
	if (pageHeader != "") {
		if (!valid) {
			$('#headerError').text('pageHeader must range between ' + min + ' and ' + max);
			$('#headerError').css("display", "");
			return false;
		}
		if (!isSpclCharValidHeader(pageHeader)) {
			// Following invalid characters !^*+=`~[]\\\';,./{}|\":<> are not allowed
			$('#headerError').text('Please enter a valid Poll Header');
			$('#headerError').css("display", "");
			return false;
		}
	}
	return true;
}


function isAddress1Valid() {
	$('#address1Error').css("display", "none");
	var address1 = $("#address1").val();
	var valid = true;
	var min = 5;
	var max = 20;
	valid = checkLength($('#address1'), "address1", min, max);
	if (address1 != "") {
		if (!isSpclCharValid(address1)) {
			$('#address1Error').text('Following invalid characters @$&/_#%?+*^[]{}|`~<>!= are not allowed');
			$('#address1Error').css("display", "");
			return false;
		}
	}
	return true;
}
function isAddress2Valid() {
	$('#address2Error').css("display", "none");
	var address2 = $("#address2").val();
	var valid = true;
	var min = 5;
	var max = 20;
	valid = checkLength($('#address2'), "address2", min, max);
	if (address2 != "") {
		if (!isSpclCharValid(address2)) {
			$('#address2Error').text('Following invalid characters @$&/_#%?+*^[]{}|`~<>!= are not allowed');
			$('#address2Error').css("display", "");
			return false;
		}
	}
	return true;
}
function isCityValid() {
	$('#cityError').css("display", "none");
	var city = $("#city").val();
	var valid = true;
	var min = 5;
	var max = 20;
	var checkRegexp = /^[a-zA-Z ]+$/;
	valid = checkLength($('#city'), "city", min, max);
	if ($("#city").val() != "") {
		if (!valid) {
			$('#cityError').text('city must range between ' + min + ' and ' + max);
			$('#cityError').css("display", "");
			return false;
		}
		if (!checkRegexp.test(city)) {
			$('#cityError').css("display", "none");
			$('#cityError').text('Enter a valid city');
			$('#cityError').css("display", "");
			return false;
		}
	}
	return true;
}


function checkLength(o, n, min, max) {
	if (o.val().length > max || o.val().length < min) {
		return false;
	} else {
		return true;
	}
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
function isSpclCharValidHeader(value) {
	var iChars = "!^*+=`~[]\\\;,./{}|\":<>";
	for (var i = 0; i < value.length; i++) {
		if (iChars.indexOf(value.charAt(i)) != '-1') {
			return false;
		}
	}
	return true;
}

function isPostcodeValid() {
	var add1=$("#address1").val();
	var add2=$("#address2").val();
	var city=$("#city").val();
	var emailid=$("#contactEmail").val();
	var webaddr=$("#websiteUrl").val();
	var phone1=$("#contactPhone1").val();
	var phone2=$("#contactPhone2").val();
	var postcode=$("#postcode").val();

	$('#postcodeError').css("display", "none");
	$('#postcode').keyup(function(){
		this.value = this.value.toUpperCase();
	});
	var postalCode = $("#postcode").val();
	var valid = true;

	var checkRegexp = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
	// These are the fields that are related to address and need postcode
	if((add1.length==0)&&(add2.length==0)&&(city.length==0)) {
		$("#postcodeError").css("display","none");
		return true;
	} else{
		/*
               if(postcode==""){
                $('#postcodeError').text('Postcode is required');
                $('#postcodeError').css("display", "");
                showerror();
                return false;
               }
		 */
	}

	for (var i = 0; i < postalCode.length; i++) {
		if (checkRegexp.indexOf(postalCode.charAt(i)) != '-1') {
			$('#postcodeError').text("Special Characters &/#%?+*^[]{}|`~<>!=' are not allowed");
			$('#postcodeError').css("display", "");
			showerror();
			return false;
		}
	}
	return true;
}
function isCountryValid() {
	$('#countryError').css("display", "none");
	var country = $("#country").val();
	var valid = true;
	var min = 5;
	var max = 20;
	var checkRegexp = /^[a-zA-Z ]*$/;
	valid = checkLength($('#country'), "country", min, max);
	if ($("#country").val() != "") {
		if (!valid) {
			$('#countryError').text('country must range between ' + min + ' and ' + max);
			$('#countryError').css("display", "");
			return false;
		}
		if (!checkRegexp.test(country)) {
			$('#countryError').css("display", "none");
			$('#countryError').text('Alphabets only allowed');
			$('#countryError').css("display", "");
			return false;
		}
	}
	return true;
}
function isContactEmailValid() {
	$('#contactEmailError').css("display", "none");
	var contactEmail = $("#contactEmail").val();
	var checkRegexp = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
	var valid = true;
	if ($("#contactEmail").val() != "") {
		if (!checkRegexp.test(contactEmail)) {
			$('#contactEmailError').text('Enter a valid email address');
			$('#contactEmailError').css("display", "");
			return false;
		}
	}
  return true;
}
function isWebsiteUrlValid() {
	$("#websiteUrlError").css("display", "none");
	var websiteUrl = $("#websiteUrl").val();
	var length = websiteUrl.length;
	if (length >= 80) {
		$("#websiteUrlError").text("Your website url  has exceeded our 80 characters limit");
		$("#websiteUrlError").css("display", "");
	} else {
		$("#websiteUrlError").css("display", "none");
	}
	var iChars = "^&?+*[]{}\|`~<>%#$@!%(); ";
	for (var i = 0; i < websiteUrl.length; i++) {
		if (iChars.indexOf(websiteUrl.charAt(i)) != '-1') {
			$('#websiteUrlError').text('Enter a valid Website Url');
			$('#websiteUrlError').css("display", "");
			return false;
			break;
		}
	}
	var checkRegexp = /(www\.)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;
	if ($("#websiteUrl").val() != "") {
		if (!checkRegexp.test(websiteUrl) || isSpclCharWebsiteUrl(websiteUrl) == false) {
			$('#websiteUrlError').text('Enter a valid Website Url');
			$('#websiteUrlError').css("display", "");
			return false;
		}
	}
	return true;
}
function isSpclCharWebsiteUrl(fieldName) {
	var websiteUrl = $("#websiteUrl").val();
	var iChars = "^&?+*[]{}\|`~<>%#$!=-'";
	var isValid = true;
	for (var i = 0; i < websiteUrl.length; i++) {
		if (iChars.indexOf(websiteUrl.charAt(i)) != '-1') {
			isValid = false;
			break;
		}
	}
	return isValid;
}

function isPhoneNumber1Valid() {
	$('#Phone1Error').css("display", "none");
	var valid = true;
	var min = 10;
	var max = 16;
	var phone = $("#contactPhone1").val();
	var checkRegexp = /^[0-9\s\-\+]*$/;
	valid = checkLength($('#contactPhone1'), "phone", min, max);
	if ($("#contactPhone1").val() != "") {
		if (!valid) {
			$('#Phone1Error').text('phone number must range between ' + min + ' and ' + max);
			$('#Phone1Error').css("display", "");
			return false;
		}
		if (!checkRegexp.test(phone)) {
			$('#Phone1Error').text('Phone number should be Numeric');
			$('#Phone1Error').css("display", "");
			return false;
		}
	}
	return true;
}
function isPhoneNumber2Valid() {
	$('#Phone2Error').css("display", "none");
	var valid = true;
	var min = 10;
	var max = 16;
	var phone = $("#contactPhone2").val();
	var checkRegexp = /^[0-9\s\-\+]*$/;
	valid = checkLength($('#contactPhone2'), "phone", min, max);
	if ($("#contactPhone2").val() != "") {
		if (!valid) {
			$('#Phone2Error').text('phone number must range between ' + min + ' and ' + max);
			$('#Phone2Error').css("display", "");
			return false;
		}
		if (!checkRegexp.test(phone)) {
			$('#Phone2Error').text('Phone number should be Numeric');
			$('#Phone2Error').css("display", "");
			return false;
		}
	}
	return true;
}
function isImageLinkValid(id) {
	$("#"+id+"Error").css("display", "none");
	var imageLInk = $("#"+id).val();
	var length = imageLInk.length;
	if (length >= 80) {
		$("#"+id+"Error").text("Your Link   has exceeded our 80 characters limit");
		$("#"+id+"Error").css("display", "");
	} else {
		$("#"+id+"Error").css("display", "none");
	}
	var iChars = "^&?+*[]{}\|`~<>%#$@!%(); ";
	for (var i = 0; i < imageLInk.length; i++) {
		if (iChars.indexOf(imageLInk.charAt(i)) != '-1') {
			$("#"+id+"Error").text('Enter a valid Link');
			$("#"+id+"Error").css("display", "");
			return false;
			break;
		}
	}
	var checkRegexp =  /(www\.)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/;
	if ($("#"+id).val() != "") {
		if (!checkRegexp.test(imageLInk) || isSpclCharImageLink(imageLInk) == false) {
			$("#"+id+"Error").text('Enter a valid Link');
			$("#"+id+"Error").css("display", "");
			return false;
		}
	}
	return true;
}
function isSpclCharImageLink(fieldName) {
	var iChars = "^&?+*[]{}\|`~<>%#$!=-'";
	var isValid = true;
	for (var i = 0; i < fieldName.length; i++) {
		if (iChars.indexOf(fieldName.charAt(i)) != '-1') {
			isValid = false;
			break;
		}
	}
	return isValid;
}
function showerror(){
	$("#postcode").focus();
	$('#postcodeError').css("display", "");
	$("#link2").slideDown();
	$("#opencolor2").css("color","red");
	$("#opencolor1").css("color","#1b303f");
	$("#opencolor3").css("color","#1b303f");
	$(".link2").hide();
	$("#link1").hide();
	$("#link3").hide();
	$(".link1").show();
	$(".link3").show();
	$(".open2").show();
	$(".open1").hide();
	$(".open3").hide()
}

function linkify(inputText) {
	var replacedText, replacePattern1, replacePattern2, replacePattern3;

	//URLs starting with http://, https://, or ftp://
	replacePattern1 = /(\b(https?|ftp):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/gim;
	replacedText = inputText.replace(replacePattern1, '<a href="$1" target="_blank">$1</a>');

	//URLs starting with "www." (without // before it, or it'd re-link the ones done above).
	replacePattern2 = /(^|[^\/])(www\.[\S]+(\b|$))/gim;
	replacedText = replacedText.replace(replacePattern2, '$1<a href="http://$2" target="_blank">$2</a>');

	//Change email addresses to mailto:: links.
	replacePattern3 = /(\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6})/gim;
	replacedText = replacedText.replace(replacePattern3, '<a href="mailto:$1">$1</a>');

	return replacedText;
}
