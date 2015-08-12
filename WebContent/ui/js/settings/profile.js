// JavaScript Document
// jquery initializer, do not alter.
var occId;
$(document).ready(function () {
    document_onInit();
});

// all startup logic should exist in the init function.
function document_onInit() {
    setDefaultInput();

    //wire event handlers

    $(".inline").colorbox({inline:true});
    $("#imageLoader").css("display", "none");

    $('#incyyte_browse_photos').click(function () {
    	$("#incyyte_photos_error_msg").hide();
    	$("#incyyte_photos_error_msg3").hide();
        $('#incyyte_photo_file_input').click();
    });

    $('#incyyteUploadPhotoButton').click(function () {
        uploadLogo();
    });
    $('#incyyte_photo_file_input').change(function () {
    	$('#incyyte_photo_file_name').text($(this).val().substring(12,25).concat("..."));
       // $('#view_photo_file_name').text($(this).val().substring(0,13).concat("..."));
    });


    $("#year").change(function () {
        changeDays();
    });
    $("#month").change(function () {
        changeDays();
    });

    //COUNTRY
    $.fn.clearField = function () {
        return this.focus(
            function () {
                if (this.value == this.defaultValue) {
                    this.value = "";
                }
            }).blur(function () {
                if (!this.value.length) {
                    this.value = this.defaultValue;
                }
            });
    };
    
    $('#postcode').keyup(function(){
	    this.value = this.value.toUpperCase();
	});

    //Change this to the ID of the country input you want to be autocompleted
    //make sure to update the CSS for this ID as well
    var ac_country = "#ac_country";

    //options are the same as the JQuery Autocomplete plugin
    $(ac_country).autocomplete(countries, {
        minChars:2,
        width:320,
        matchContains:true,
        scroll:true,
        max:0,
        formatItem:function (row, i, max, term) {
            return "<img src='css/accountry.0.2/images/flags/" + row.code.toLowerCase() + ".gif'/> " + row.name;
        },
        formatResult:function (row) {
            return row.name;
        },
        formatMatch:function (row, i, max) {
            return row.name;
        }
    });

/*    $(ac_country).after($(ac_country).clone().attr('value', '').attr('id', $(ac_country).attr('id') + '_hidden'));
    $(ac_country).removeAttr('name', '').clearField();
    $(ac_country).result(function (event, data, formatted) {
        if (data) {
            $(ac_country + '_hidden').val(data.code.toLowerCase());
            //togglePostcode(data.code.toLowerCase());
        }
        var src = 'css/accountry.0.2/images/flags/' + data.code.toLowerCase() + '.gif';
        $(ac_country).css('backgroundImage', 'url(' + src + ')');
    });*/

    //setDefaultCountry("GB");


    $.ajax({
        type:"GET",
        url:"occupations.json",
        dataType:"json",
        async:false,
        success:function (data) {
            contactJSONData = data.occupations;
        }
    });
    contactJSONData = JSON.stringify(contactJSONData);
    contactJSONData = jQuery.parseJSON(contactJSONData);

    ////alert(contactJSONData);
    var acOptions = {
        minChars:2,
        matchContains:true,
        autoFill:true,
        autoFocus:true,
        mustMatch:true,
        formatItem:function (item) {

            return item.occupation;
        }

    };

    $('#occupation').autocomplete(contactJSONData, acOptions);

    $('#occupation').result(
        function (event, data, formatted) {

            if (data) {
                $("#occId").val(data.id);
            } else {
                occId = undefined;
            }

        });

    $.ajax({
        type:"GET",
        url:"religions.json",
        dataType:"json",
        async:false,
        success:function (data) {
            contactJSONData = data.religions;
        }
    });
    contactJSONData = JSON.stringify(contactJSONData);
    contactJSONData = jQuery.parseJSON(contactJSONData);

    ////alert(contactJSONData);
    var acOptions = {
        minChars:2,
        matchContains:true,
        autoFill:true,
        autoFocus:true,
        mustMatch:true,
        formatItem:function (item) {
            return item.name;
        }

    };

    $('#religion_id').autocomplete(contactJSONData, acOptions);

    $('#religion_id').result(
        function (event, data, formatted) {

            if (data) {
                $("#religionId").val(data.id);
            }

        });


}
function cleanLocation() {
    $("#invalidLocation").css("display", "none");
    $("#validLocation").css("display", "none");
}
function cleanPostcode() {
    $("#invalidPostcode").css("display", "none");
    $("#validPostcode").css("display", "none");
}
function cleanMobile() {
    $("#invalidMobile").css("display", "none");
    $("#validMobile").css("display", "none");
}

function cleanEmail1(){
	$("#invalidEmail1").css("display", "none");	
	$("#validEmail1").css("display", "none");	
}
function cleanEmail(){
	$("#invalidEmail").css("display", "none");	
	$("#validEmail").css("display", "none");
}
function cleanEmail2(){
	$("#invalidEmail2").css("display", "none");	
	$("#validEmail2").css("display", "none");	
}
function clientValidation(UID_Name)
{
//alert('client validation...'+UID_Name);
//	$("#"+UID_Name+"Loader").css("display", "");

    // perform each of the different key types of input validation only, no
    // business rules outside of input validation should be done client side.
if( UID_Name=='Uname' || UID_Name=='Email' || UID_Name=='Postcode' || UID_Name=='opinion' ){
	if (!isRequiredField(UID_Name)){
//	 //alert('client validation..not requaired.');
            return false;
        }
    }

    if (UID_Name == 'Uname') {
        //alert('client validation...IF'+UID_Name);
        return isUsernameValid(UID_Name);
    }

    if (UID_Name == 'firstName') {
        return isFirstNameValid(UID_Name);
    }

    if (UID_Name == 'lastName') {

        return isLastNameValid(UID_Name);
    }
 if (UID_Name  ==  'opinion') {
//	alert('client validation...IF'+UID_Name);
	 return isOpinionValid(UID_Name);
    }    
     else if (UID_Name == 'Location') {
//	 //alert('client validation...IF'+UID_Name);
        return isLocationValid(UID_Name);
    } else if (UID_Name == 'Postcode') {
//	 //alert('client validation...IF'+UID_Name);
        return isPostcodeValid(UID_Name);
 }  else  if (UID_Name  ==  'Mobile'){
//	 alert('client validation...IF'+UID_Name);
	 return isMobileValid(UID_Name);
 }  else  if (UID_Name  ==  'Email' || UID_Name  ==  'Email1' || UID_Name  ==  'Email2'){
//	 alert('client validation...IF'+UID_Name);
	 return isValidEmail(UID_Name);
 }
 else return true;
}


function isRequiredField(UID_Name) {
    //alert("isRequiredFields "+UID_Name );

    if (UID_Name == 'Uname') {
        //alert("isRequiredFields if condition  "+UID_Name );

        if (!validReqFields('username', UID_Name)) {
            return false;
        } else return true;
    }if (UID_Name == 'firstName') {
        if (!validReqFields('firstname', UID_Name)) {
            return false;
        } else return true;
    
    } if (UID_Name == 'lastName') {
        if (!validReqFields('lastname', UID_Name)) {
            return false;
        } else return true;
    }
	 if (UID_Name  ==  'opinion') {
//		alert("isRequiredFields if condition  "+UID_Name );
		if (!validReqFields('opinion', UID_Name)){
			return false;
		}else return true;
	}else if (UID_Name  ==  'Email') {
//		alert("isRequiredFields if condition  "+UID_Name );
		if (!validReqFields('suemail', UID_Name)){
			return false;
		}else return true;		
	}else if (UID_Name  ==  'Pwd') {
		if (!validReqFields('su_password', UID_Name)){
			return false;
		}else return true;		
	}else if (UID_Name  ==  'Postcode') {
		if (!validReqFields('postcode', UID_Name)){
			return false;
		}else return true;
	
	}
	

}   // function

function validReqFields(fieldName, UID_Name) {
//	alert("validReqFields "+UID_Name );
    var valid = true;
    //clear all previous errors
    if ($('#' + fieldName).val() == "") {
        if (UID_Name == 'Uname') {
            cleanUname();
            displayErrorMsg(fieldName, UID_Name, 'Username is required');
        }
        if (UID_Name == 'firstName') {
            cleanFirstName();
            displayErrorMsg(fieldName, UID_Name, 'FirstName is required');
        }
        if (UID_Name == 'lastName') {
            cleanLastName();
            displayErrorMsg(fieldName, UID_Name, 'LastName is required');
        }
        if (UID_Name == 'opinion') {
            cleanOpinion();
            displayErrorMsg(fieldName, UID_Name, 'Opinion is required');
        } 
        else if (UID_Name == 'Email') {
            cleanEmail();
		    	displayErrorMsg(fieldName, UID_Name, 'Email is required');
        } else if (UID_Name == 'Pwd') {
            cleanPwd();
            displayErrorMsg(fieldName, UID_Name, 'Password is required');
        } else if (UID_Name == 'Postcode') {
            cleanPostcode();
            displayErrorMsg(fieldName, UID_Name, 'Postcode is required');
        }
        valid = false;
    }

    return valid;
}

function displayErrorMsg(fieldName, UID_Name, msg) {
    //$("#"+UID_Name+"Loader").css("display", "none");
    $("#invalid" + UID_Name).css("display", "");
    $("#invalid" + UID_Name).css('color', "#ec3f41");
    $("#invalid" + UID_Name).text(msg);
}
function displayValidMsg(fieldName, UID_Name, msg) {
//	$("#"+UID_Name+"Loader").css("display", "none");
    $("#valid" + UID_Name).css("display", "");
    $("#valid" + UID_Name).css("padding-bottom", "24px");
    $("#valid" + UID_Name).css('color', "#3a7d34");
    $("#valid" + UID_Name).text(msg);
}

function cleanUname() {
    $("#invalidUname").css("display", "none");
    $("#validUname").css("display", "none");
}
function cleanLastName() {
    $("#invalidlastName").css("display", "none");
    $("#validlastName").css("display", "none");
}
function cleanFirstName() {
    $("#invalidfirstName").css("display", "none");
    $("#validfirstName").css("display", "none");
}

function cleanOpinion(){
	$("#invalidopinion").css("display", "none");
	$("#validopinion").css("display", "none");
}
function isUsernameValid(UID_Name) {
    var username=$("#username").val();
    var valid = true;
    var min = 3;
    var max = 20;
    cleanUname();
    valid = checkLength($('#username'), "username", min, max);
    if (!valid) {
        displayErrorMsg('#username', UID_Name, 'Username must range between ' + min + ' and ' + max);
    }
     if(!isSpclCharUserName(username)){
    	  displayErrorMsg('#username', UID_Name, "An invalid character has been entered  !@#$%^&*()+=_`~-[]';,./{ }|\":<>?\\ are not allowed");
	  }else{
	  return valid;
    }

}   // function
function isOpinionValid(UID_Name)
{
//	alert("inside isOpinionValid ");
	 var valid = true;
	 var min = 0;	
	 var max = 210;
    cleanOpinion(); 
	valid = checkLength( $('#opinion'), "opinion", min, max );
//	alert("is opinion valid"+valid);
	if (!valid){
    	displayErrorMsg('#opinion', UID_Name, 'opinion must not exceed '+ max +' characters');		
	}
	
	if (valid){
		valid = isSpecialChar($('#opinion').val());
		
		if (!valid){
	    	displayErrorMsg('#opinion', UID_Name, 'invalid character entered');		
		}		
	}
	
	return valid;
 
}  

function isSpecialChar(UID_Name){	
	var iChars ="^()`~[]\\\';/{}|\":<>"; 
	var isValid=true;
	for (var i = 0; i < UID_Name.length; i++) 
    {
	    if (iChars.indexOf(UID_Name.charAt(i))!='-1') {
	    	isValid=false;
	    	break;
	      }else{
	    	 isValid=true;
		 }
    }
	return isValid;
}

function isFirstNameValid(UID_Name) {
    //alert("inside isFirstNameValid ");
    cleanFirstName();
    var valid = false;
    if ($("#firstname").val() == '') {

        displayErrorMsg('#firstname', UID_Name, 'For Firstname Atleast one alphabit Required');
        return false;
    } else {

        if ($("#firstname").val().search(/[^a-zA-Z]+/) === -1) {
            return true;
        }
        else {
            displayErrorMsg('#firstname', UID_Name, 'Only alpha characters are valid in this field');
            return false;
        }
    }
}

function isLastNameValid(UID_Name) {
    //alert("inside isLastNameValid ");
    cleanLastName();
    if ($("#lastname").val() == "") {
        displayErrorMsg('#lastname', UID_Name, 'For Lastname Atleast one alphabit Required');
    } else {
        if ($("#lastname").val().search(/[^a-zA-Z]+/) === -1) {
            return true;
        }
        else {
            displayErrorMsg('#lastname', UID_Name, 'Only alpha characters are valid in this field');
            return false;
        }
    }

}
function isLocationValid(UID_Name) {
//	//alert("inside isLocation ");
    var valid = true;
    var min = 0;
    var max = 100;
    cleanLocation();
    valid = checkLength($('#location'), "location", min, max);
//	//alert("is location valid"+valid);
    if (!valid) {
        displayErrorMsg('#location', UID_Name, 'Location must not exceed ' + max + 'characters');
    } else if (!checkRegexp($('#location'), /^[a-z0-9A-Z\s\@\!\:\;\_\&\(\)\.\,\-\&\'\\]*$/)) {
        displayErrorMsg('#location', UID_Name, 'Location field only allow : a-z / A-Z / 0-9.');
        return false;
    }
    return valid;

}

function isPostcodeValid(UID_Name) {
//alert("inside isPostcodeValid ");
    var valid = true;
    var min = 6;
    var max = 8;
    var pCode = $('#postcode').val();
    cleanPostcode();
    valid = checkLength($('#postcode'), "postcode", min, max);
    
    if(pCode.indexOf(" ") < 0){
    	 displayErrorMsg('#postcode', UID_Name, 'Please add a space to your postcode e.g. AB1 2CD');  
    	 return false;
    }
   
    if (!valid) {
        displayErrorMsg('#postcode', UID_Name, 'Please enter a valid Postcode');        
    } 
    else if (!checkRegexp($('#postcode'), /^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))) {0,1}[0-9][A-Za-z]{2})$/)) {
        displayErrorMsg('#postcode', UID_Name, 'Please enter a valid Postcode');
        return false;
    }
    return valid;
}

function isMobileValid(UID_Name)
{
//	alert("inside isMobileValid ");
	 var min = 10;	 
	 var max = 15;
	 cleanMobile(); 
//	alert("is location valid"+valid);
	if (!checkRegexp( $('#mobile'), /^[0-9\s\-\+]*$/ )){
		displayErrorMsg('#mobile', UID_Name, 'Mobile Number should be Numeric');
		return false;
	}
	var valid = checkLength( $('#mobile'), "mobile", min, max );
	  if (!valid){
    	displayErrorMsg('#mobile', UID_Name, 'Mobile must range between '+min +' and '+ max	);
    	return valid;
	  }
	  return true;
}

function isValidEmail(UID_Name)
{
	if(UID_Name == "Email") {
	 cleanEmail();
	 var valid = true;
	// if (!validateEmail($('#email1').val()) ){
	//	  valid = false;
	//	 displayErrorMsg('#email1', UID_Name, 'Enter a valid email address');		
	// }else
	// alert("Inside suemail");
	 if (!checkRegexp( $('#suemail'), /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/ )){
			displayErrorMsg('#suemail', UID_Name, 'Enter a valid email address');
			return false;
	 }
	 return valid;
	}
	if(UID_Name == "Email1") {
		 cleanEmail1();
		 var valid = true;

		 if (!checkRegexp( $('#email1'),/^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/ )){
				displayErrorMsg('#email1', UID_Name, 'Enter a valid email1 address');
				return false;
		 }
		 return valid;
	}
	if(UID_Name == "Email2") {
		var valid = true;
		cleanEmail2();
//		alert("Inside email2"+UID_Name);
		if (!checkRegexp( $('#email2'), /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/)){
			displayErrorMsg('#email2', UID_Name, 'Enter a valid email2 address');
			return false;
		}
		return valid;
	}
}

function checkLength( o, n, min, max ) {
	/*alert("checkLength length"+o.val().length);
	alert("checkLength min "+min);
	alert("checkLength max "+max);
	alert("checkLength tag"+n);*/
	if ( o.val().length > max || o.val().length < min ) {
		
//		alert("checkLength if condition false");	
		return false;
	} else {
//		alert("checkLength if condition true");
		return true;
	}
}


function checkRegexp(o, regexp) {
//	alert("checkRegexp regexp"+regexp+"---"+o.val());
	if (!( regexp.test(o.val()) )) {
//		alert("checkRegexp if condition false");	
		return false;
	} else {
//		alert("checkRegexp if condition true");	
		return true;
	}
}
var httpRequest = getXmlHttpRequestObject();
function getXmlHttpRequestObject() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        alert("Your Browser Doesn't support AJAX!\\n Please upgrade your browser");
    }
}
function getOccupations() {
    httpRequest.open("GET", 'occupations.json', true);
    httpRequest.onreadystatechange = displayOccupations;
    httpRequest.send(null);
}

function displayOccupations() {
    if (httpRequest.readyState == 4 || httpRequest.readyState == 200) {
        var occupations = httpRequest.responseText;
        occupations = occupations.replace('[', '');
        occupations = occupations.replace(']', '');
        occupationsList = occupations.split(",");
        // $("#occupation_id").autocomplete({ source:occupationsList });
        $("#occupation_id").autocomplete({
            source:function (occupations, response) {
                response($.map(occupations, function (item) {
                    return {
                        label:item.occupation,
                        value:item.id
                    }
                }));

            },
            minLength:2
        });

    }
}

function getReligions() {
    httpRequest.open("GET", 'religions.json', true);
    httpRequest.onreadystatechange = displayReligions;
    httpRequest.send(null);
}

function displayReligions() {
    if (httpRequest.readyState == 4 || httpRequest.readyState == 200) {
        var religions = httpRequest.responseText;
        religions = religions.replace('[', '');
        religions = religions.replace(']', '');
        religionsList = religions.split(",");
        // $("#occupation_id").autocomplete({ source:occupationsList });
        $("#religion_id").autocomplete({
            source:function (religionsList, response) {
                response($.map(religionsList, function (item) {
                    return {
                        label:item.name,
                        value:item.id
                    }
                }));


            },
            minLength:2
        });

    }
}
function setDefaultCountry(code) {
    var ac_country = "#ac_country";
    var src = 'css/accountry.0.2/images/flags/' + code.toLowerCase() + '.gif';
    $(ac_country).css('backgroundImage', 'url(' + src + ')');
    $(ac_country).val('United Kingdom');
   

    $("#postal_area_lb").css("display", "none");
    $("#postal_area").css("display", "none");

}
function updateCloseDate() {
    $('#closureDateE').val($('#closureDate').val());
}

function setDefaultInput() {
    $("#communicator").css("visibility", "hidden");

    $("#nameField").hide();
    $("#emailField").hide();
    $("#phoneField").hide();
    $("#genderField").hide();
    $("#ageField").hide();

}

function displayField(field) {
    $("#" + field + "Text").hide();
    $("#" + field + "Field").hide().fadeIn(2000);
}

function createInCyyte() {
    ////alert($("#category").val());
    if (validated()) {
        //document.createInCyyteForm.submit();
        window.location.href = "create_mailList.cyt";
    }
}

//iterates over form elements making sure input validation is good.
function validated() {
    // clear out all previous form errors.
    clearAllErrors();

    // perform each of the different key types of input validation only, no
    // business rules outside of input validation should be done client side.
    if (!isRequiredFields()) {
        scroll(0, 0);    // scroll to top of page so user can see communicator message.
        return false;
    }

    if (!isClosureDateRequired()) {
        scroll(0, 0);    // scroll to top of page so user can see communicator message.
        return false;
    }

    if (!isFileUploadRequired()) {
        scroll(0, 0);    // scroll to top of page so user can see communicator message.
        return false;
    }

    if (!isURLLinkRequired()) {
        scroll(0, 0);    // scroll to top of page so user can see communicator message.
        return false;
    }


    return true;
}
function isFileUploadRequired() {
    // we'll use this to check error state once after ALL fields have validated.
    var formGood = true;

    if ($('#addFile').is(':checked') && $("#uploadfile").val() == "") {
        setError("uploadfile");
        formGood = false;
    }

    return formGood;

}   // function

function isClosureDateRequired() {
    // we'll use this to check error state once after ALL fields have validated.
    var formGood = true;
    var dateFilter = /^(\d{1,2})\/(\d{1,2})\/(\d{4}) \d{1,2}:\d{2}([ap]m)?$/;

    if ($('#closureDate').val() != 'Select a closing date') {
        if (!dateFilter.test($("#closureDate").val())) {
            ////alert("ERROR");
            setError("closureDate", "Invalid closure date format entered");
            formGood = false;
        }

    }

    return formGood;

}   // function


function isURLLinkRequired() {
    // we'll use this to check error state once after ALL fields have validated.
    var formGood = true;

    if ($('#addLink').is(':checked') && $("#eLink").val() == "http://...") {
        setError("eLink");
        formGood = false;
    }

    return formGood;

}   // function

function isRequiredFields() {
    // we'll use this to check error state once after ALL fields have validated.
    var formGood = true;

    // loop over all the input fields looking for "known good" text input,
    // we'll ignore email addresses because they will get check later.
    $("input:text").each(function (index) {
        // if input doesn't have an id, skip the whole process theres no need to validate it
        // server should ignore it, and we can't toggle error states without an id.
        var inputID = $(this).attr("id");

        if ($(this).val() == "") {
            //alert(inputID);
            setError(inputID);
            formGood = false;
        }


    }); // each function

    if ($("#question").val() == "What is your question?") {
        setError("question");
        formGood = false;
    }

    if ($("#category").val() == "select") {
        setError("category");
        formGood = false;
    }

    return formGood;

}   // function

//sets error status icon visibility, tooltip message, and input error style
function setError(inputField) {
    $("#communicator").addClass("error");
    $("#communicator").css("visibility", "visible");


    var field = "#" + inputField;
    $(field).addClass("inputError");

    var errorIcon = "#e_" + inputField;
    $(errorIcon).css("visibility", "visible");
}

function setError(inputField, message) {
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

function clearAllErrors() {
    //$("#errorMsg").removeClass("error");
    //$("#errorMsg").css("visibility", "hidden");
    $("#communicator").removeClass("error");
    $("#communicator").css("visibility", "hidden");

    $('textarea,input:text,input:file').each(function () {
        var inputID = $(this).attr("id");
        if (inputID != "")
            clearError(inputID);
    });

    clearError("category");

}

function clearError(inputField) {
    var field = "#" + inputField;
    $(field).removeClass("inputError");

}


function uploadLogo() {
   	$('#incyyte_photos_error_msg2').hide("fast");
	
    var filePath = $("#incyyte_photo_file_input").val();
    if (filePath == null || filePath == '') {
    	filePath = document.getElementById("incyyte_photo_search_file").value;
        $('#searchedFileNameProfile').val(document.getElementById("incyyte_photo_search_file").value);
        $('#searchedFileURLProfile').val(document.getElementById("incyyte_photo_search_url").value);
    } 
    filePath = filePath.split('\\').pop();
    var ext = filePath.split('.').pop().toLowerCase();
	if($.inArray(ext, ['gif','png','jpg','jpeg','bmp']) == -1) {
		$("#incyyte_photos_error_msg").css("display","");	 
		return false;
	}
	
	if(filePath.length > 40){
		$("#incyyte_photos_error_msg3").css("display","");	 
		return false;
	}
	
    $("#imageLoader").ajaxStart(
    		function () {
            $(this).show();
        }).ajaxStop(function () {
            $(this).hide();
        });

    $('#file_name_tst').val(filePath);

    $("#editProfileForm").ajaxSubmit({
        type:'POST',
        url:'uploadlogo.cyt',
        success:function (data) {
        	if (data.indexOf("failure") != -1) {				
				$('#incyyte_photos_error_msg2').show("fast");
			}
			else{
				$.fn.colorbox.close();
				window.location = "editProfile.cyt";
			}
        },
        error:function (jqXHR, textStatus, errorThrown) {
        	$('#incyyte_photos_error_msg2').show("fast");
        }
    });
}


function saveOpinion() {
        if ($("#saveopinionlink").hasClass("save_check")) {
        $("#saveopinionlink").removeClass("save_check");
        $("#saveopinionlink").addClass("edit").text('Edit');
        $("#opinion").attr("readOnly", "true").addClass("readonly_input");
        $("#editProfileForm").ajaxSubmit({
            type:"GET",
            url:"saveopinion.json",
            dataType:"json",
            success:function (data) {
            }
        });
    }
    else {
        $("#saveopinionlink").attr("title", "Save").removeClass("edit");
        $("#opinion").removeAttr("readOnly").removeClass("readonly_input");
        $("#saveopinionlink").addClass("save_check").text("SAVE");
    }
}

function saveUserName() {
    var username = $("#username").val();
    $("#hid_userName").val(username);
    $("#editProfileForm").attr("action", "saveusername.cyt");
    $("#editProfileForm").submit();
}





function editSaveField(UID_Name, linkname, submitUrl) {
	if(UID_Name=='Postcode'){
	var postCodeChanged=confirm("Once changed postcode will be blocked for 3months.");
	   if (postCodeChanged !=true)
	     {
	       return false ;
	     }
	}
    var urlValue = getContextPath() + "/" + submitUrl + '?tempVar=' + Math.random();
    if ($("#save" + linkname + "link").hasClass("button_green")) {
        if (validateFields(UID_Name, linkname)) {
            $("#editProfileForm").ajaxSubmit({
                type:"POST",
                url:urlValue,
                dataType:"text",
                success:function (data) {
                   if (data.search("Updated successfully") == 0) {
                        $("#invalid" + UID_Name).css("display", "none");
                        $("#valid" + UID_Name).css("display", "");
                        $("#valid" + UID_Name).css('color', "#3a7d34");
                        $("#valid" + UID_Name).text(data);
                        $("#save" + linkname + "link").removeClass("button_green").addClass("edit").css("width", "auto").css("float", "right").css("margin-bottom", "");
                        $("#save" + linkname + "link" + " span").removeClass("title_green").text('Edit');
                        $("#" + linkname).attr("readOnly", "true").addClass("readonly_input").removeClass("edit_profile_input_enable").addClass("edit_profile_input_ie8");
                        $("select").selectbox("disable");
                        if(linkname =='postcode'){
                        	  $("#save" + linkname + "link").css("display", "none");
                 	   }
                    } else if (data.search("error") == 0) {
                        $("#valid" + UID_Name).css("display", "none");
                        $("#invalid" + UID_Name).css("display", "");
                        $("#invalid" + UID_Name).css('color', "#ec3f41");
                        $("#invalid" + UID_Name).text(data);
                    }
                },
                error:function (jqXHR, textStatus, errorThrown) {
                    alert("editProfileForm:error:" + textStatus + " exception:" + errorThrown);
                }
            });
        }
    }
    else {
        $("#save" + linkname + "link").attr("title", "Save").removeClass("edit").addClass("button_green").css("width", "60px").css("float", "right").css("margin-bottom", "-3px");
        $("#" + linkname).removeAttr("readOnly").removeClass("readonly_input").removeClass("edit_profile_input_ie8").addClass("edit_profile_input_enable");
        $("#save" + linkname + "link" + " span").addClass("title_green edit_profile_save_bot_ie8").text("SAVE");
        if (UID_Name === 'dob') {
            $("#day").selectbox("enable");
            $("#month").selectbox("enable");
            $("#year").selectbox("enable");
        } else {
            try {
                $("#" + UID_Name).selectbox("enable");
            } catch(e) {
                //Suppress error because selectbox will not be present for normal text fields
                // At that moment we will enter into this catch
            }
        }
    }
}

function saveGender() {
    var gender = $("#gender").val();
    $("#hid_gender").val(gender);
    $("#editProfileForm").attr("action", "savegender.cyt");
    $("#editProfileForm").submit();
}

function saveOccupation() {
    var occupation = $("#occupation").val();
    $("#hid_occupation").val(occupation);
    $("#editProfileForm").attr("action", "saveoccupation.cyt");
    $("#editProfileForm").submit();
}

function saveReligion() {
    var religion = $("#religion").val();
    $("#hid_religion").val(religion);
    $("#editProfileForm").attr("action", "savereligion.cyt");
    $("#editProfileForm").submit();
}

function saveSexuality() {
    var sexuality = $("#sexuality").val();
    $("#hid_sexuality").val(sexuality);
    $("#editProfileForm").attr("action", "savesexuality.cyt");
    $("#editProfileForm").submit();
}

function saveIncome() {
    var mobile = $("#income").val();
    $("#hid_income").val(mobile);
    $("#editProfileForm").attr("action", "saveincome.cyt");
    $("#editProfileForm").submit();
}

function saveDob() {
    var day = $("#day").val();
    var month = $("#month").val();
    var year = $("#year").val();
    $("#hid_birthDay").val(day);
    $("#hid_birthMonth").val(month);
    $("#hid_birthYear").val(year);
    $("#editProfileForm").attr("action", "savedob.cyt");

    $("#editProfileForm").submit();
}

function deleteLogo(confirmFlag) {
	if(confirmFlag=='true'){
		   $("#editProfileForm").ajaxSubmit({
		        type:'POST',
		        url:"deletelogo.cyt",
		        success:function (data) {
		         setTimeout(location.reload(true), 5); 
		  },
		  error:function (jqXHR, textStatus, errorThrown) {
		   alert("error:" + textStatus + " exception:" + errorThrown);

		  }
	    });
	  }else{
		  document.getElementById("profilepic_delete").className = 'nodisplay';
       }
}
function deleteLogoCinfirm(){
	document.getElementById("profilepic_delete").className='profilepic_delete_confirm';
}
function makeDefault(num) {
	$("#mkDefault").val(num);
	$("#editProfileForm").ajaxSubmit({
		type : "GET",
		url : "makedefault.cyt",
		dataType : "text",
		success : function(data) {
			window.setTimeout(location.reload(true),1);
		}
		});
}

function changeDays() {
    var month = $("#month").val();
    if (month == 4 || month == 6 || month == 9 || month == 11) {
        $("#day").find("option[value='31']").remove();
    }
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
        $("#day").find("option[value='31']").add();
    }

    if (month == 2) {
        if (($("#year").val() % 4 == 0)) {
            $("#day").find("option[value='31']").remove();
            $("#day").find("option[value='30']").remove();
        }
        else {
            $("#day").find("option[value='31']").remove();
            $("#day").find("option[value='30']").remove();
            $("#day").find("option[value='29']").remove();
        }
    }
}

function updateCategory(categoryName, linkName, id) {
   var clas = $("#" + id).attr('class');
    if (clas == (linkName + '_choice active1')) {
        ////alert("in if");
        $("#" + linkName + "link").removeClass(linkName + '_choice active1');
        $("#" + linkName + "link").addClass(linkName + '_choice');

    }
    else {
        $("#" + linkName + "link").removeClass(linkName + '_choice');
        $("#" + linkName + "link").addClass(linkName + '_choice active1');
    }
    $("#hid_incyyteCategory").val(categoryName);

    $("#editProfileForm").ajaxSubmit({
        url:"savecategory.cyt",
        success:function (data) {
        	window.setTimeout(location.reload(true),1);
        }
    });
}

function anotherlink() {
    $('#email2tr').css("display", "");
    $('#addanothertr').css("display", "none");
}