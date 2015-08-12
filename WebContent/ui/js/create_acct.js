// jquery initializer, do not alter.
$(document).ready(function () {
    document_onInit();
});

//all startup logic should exist in the init function.
function document_onInit() {
    setDefaultFields();
    $("#username").click(function () {
        cleanUname();
        $("#defaultUnameNote").css("display", "");
    });
    $("#su_email").click(function () {
        cleanEmail();
        $("#defaultEmailNote").css("display", "");
    });
    $("#su_password").click(function () {
        cleanPwd();
        $("#defaultPwdNote").css("display", "");
    });
    $('#checkbox-1').change(function () {
        if ($(this).is(":checked")) {
            $("#term_notice").css("display", "none");
        }
    });
    $("#create_acct").click(function () {
        if (!$('#checkbox-1').is(":checked")) {
            $("#term_notice").css("display", "");
        } else if (UnameFlag & EmailFlag & PwdFlag) {
            createNewAccount();
        }
    });
}

var UnameFlag = false;
var EmailFlag = false;
var PwdFlag = false;

function setDefaultFields() {
    $("#activate_your_acct_msg").css("display", "none");
    $("#term_notice").css("display", "none");
    cleanUname();
    cleanEmail();
    cleanPwd();
    cleanLoader();
}

function cleanUname() {
    $("#defaultUnameNote").css("display", "none");
    $("#invalidUname").css("display", "none");
    $("#validUname").css("display", "none");
}

function cleanEmail() {
    $("#defaultEmailNote").css("display", "none");
    $("#invalidEmail").css("display", "none");
    $("#validEmail").css("display", "none");
}

function cleanPwd() {
    $("#defaultPwdNote").css("display", "none");
    $("#invalidPwd").css("display", "none");
    $("#validPwd").css("display", "none");
}

function cleanLoader() {
    $("#UnameLoader").css("display", "none");
    $("#EmailLoader").css("display", "none");
    $("#PwdLoader").css("display", "none");
}

function displayErrorMsg(fieldName, UID_Name, msg) {
    $("#" + UID_Name + "Loader").css("display", "none");
    $("#invalid" + UID_Name).css("display", "");
    $("#invalid" + UID_Name).text(msg);
}

function displayValidMsg(fieldName, UID_Name, msg) {
    $("#" + UID_Name + "Loader").css("display", "none");
    $("#valid" + UID_Name).css("display", "");
    $("#valid" + UID_Name).text(msg);
}

function processData(UID_Name, value, url) {
    $("#" + UID_Name + "Loader").css("display", "");
    $("#default" + UID_Name + "Note").css("display", "none");

    var serverDomain = url;

    $.ajax({
        type:"POST",
        url:serverDomain + "/process" + UID_Name + ".cyt",
        data:"action=" + value,
        dataType:"text",
        success:function (data) {
            if (data.search("success") == 0) {
                $("#" + UID_Name + "Loader").css("display", "none");
                $("#valid" + UID_Name).css("display", "");
                $("#valid" + UID_Name).text(data);
                //set flags
                if (UID_Name == 'Uname')UnameFlag = true;
                if (UID_Name == 'Email')EmailFlag = true;
            } else if (data.search("error") == 0) {
                $("#" + UID_Name + "Loader").css("display", "none");
                $("#invalid" + UID_Name).css("display", "");
                $("#invalid" + UID_Name).text(data);
                //set flags
                if (UID_Name == 'Uname')UnameFlag = false;
                if (UID_Name == 'Email')EmailFlag = false;
            }
        }
    });
}

function createNewAccount() {
    $("#createacct_form").ajaxSubmit({
        type:'POST',
        url:'signup/createAcct.cyt',
        success:function (data) {
            //1. hide forms
            $("#choose_your_answers_heading").css("display", "none");
            $("#choose_your_answers").css("display", "none");

            $("#term_settings").css("display", "none");
            $("#askyour_que_btn").css("display", "none");

            //2. display message
            $("#activate_your_acct_msg").css("display", "");
        },
        error:function (jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " exception:" + errorThrown);
        }
    });
}

//iterates over form elements making sure input validation is good.
function clientValidation(UID_Name) {
    $("#" + UID_Name + "Loader").css("display", "");
    // perform each of the different key types of input validation only, no
    // business rules outside of input validation should be done client side.
    if (!isRequiredFields(UID_Name)) {
        return false;
    }

    if (UID_Name == 'Uname') {
        return isUsernameValid(UID_Name);
    } else if (UID_Name == 'Email') {
        return isValidEmail(UID_Name);
    } else if (UID_Name == 'Pwd') {
        return isPasswordValid(UID_Name);
    } else return true;
}

function isRequiredFields(UID_Name) {
    if (UID_Name == 'Uname') {
        if (!validReqFields('username', UID_Name)) {
            return false;
        } else return true;
    } else if (UID_Name == 'Email') {
        if (!validReqFields('su_email', UID_Name)) {
            return false;
        } else return true;
    } else if (UID_Name == 'Pwd') {
        if (!validReqFields('su_password', UID_Name)) {
            return false;
        } else return true;
    } else {
        return true;
    }
}   // function

function validReqFields(fieldName, UID_Name) {
    var valid = true;
    //clear all previous errors
    if ($('#' + fieldName).val() == "") {
        if (UID_Name == 'Uname') {
            cleanUname();
            displayErrorMsg(fieldName, UID_Name, 'Username is required');
        } else if (UID_Name == 'Email') {
            cleanEmail();
            displayErrorMsg(UID_Name, UID_Name, 'Email is required');
        } else if (UID_Name == 'Pwd') {
            cleanPwd();
            displayErrorMsg(fieldName, UID_Name, 'Password is required');
            PwdFlag = false;
        }
        valid = false;
    }
    return valid;
}

function isUsernameValid(UID_Name) {
    cleanUname();
    var valid = true;
    var min = 3;
    var max = 20;
    var username = $("#username").val();
    valid = checkLength($('#username'), "username", min, max);
    if (!valid) {
    	UnameFlag = false;
        displayErrorMsg('#username', UID_Name, 'Username must range between ' + min + ' and ' + max);
    }
    if (!isSpclCharUserName(username)) {
        $("#validUname").css("display", "none");
        $("#defaultUnameNote").css("display", "none");
        UnameFlag = false;
        displayErrorMsg('#username', UID_Name, "Special Characters are not allowed");
    } else {
        return valid;
    }
}   // function

function isValidEmail(UID_Name) {
    cleanEmail();
    var valid = true;
    var su_email = $("#su_email").val();
    if (!validateEmail($('#su_email').val())) {
        valid = false;
        EmailFlag = false;
        displayErrorMsg('#su_email', UID_Name, 'Enter a valid email address');
    }
   return valid;
}   // function

function isPasswordValid(UID_Name) {
    var valid = true;
    var min = 7;
    var max = 20;
    cleanPwd();
    valid = checkLength($('#su_password'), "su_password", min, max);
    if (!valid) {
        displayErrorMsg('#su_password', UID_Name, 'Length of password must be between ' + min + ' and ' + max);
        PwdFlag = false;
        return valid;
        /*} else if (!checkRegexp($('#su_password'), /^[a-z]([0-9a-z_])+$/i)) {
        displayErrorMsg('#su_password', UID_Name, 'Password field only allow : a-z 0-9.');
        PwdFlag = false;
        return valid;*/
    } else {
        displayValidMsg('#su_password', UID_Name, 'Your password is valid');
        PwdFlag = true;
    }
    return valid;
}   // function

function checkLength(o, n, min, max) {
    if (o.val().length > max || o.val().length < min) {
        return false;
    } else {
        return true;
    }
}

function checkRegexp(o, regexp) {
    if (!( regexp.test(o.val()) )) {
        return false;
    } else {
        return true;
    }
}

function validateEmail(emailStr){
	var checkRegexp = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
	if (emailStr != "") {
		if (!checkRegexp.test(emailStr)) {
			return false;
		}
	}
	return true;
}
//  End -->
