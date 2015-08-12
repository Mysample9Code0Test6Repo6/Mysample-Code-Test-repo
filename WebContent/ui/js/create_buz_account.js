$(document).ready(function () {
    document_onInit();
});

function document_onInit() {
    //  setDefaultInput();

    //wire event handlers

    $(".inline").colorbox({inline:true});
    $("#imageLoader").css("display", "none");
    $("#imageBannerLoader").css("display", "none");

    $('#incyyte_browse_photos').click(function () {
        $("#incyyte_photos_error_msg").hide();
        $('#incyyte_photo_file_input').click();
    });

    $('#incyyte_browse_banner').click(function () {
        $("#incyyte_banner_error_msg").hide();
        $('#incyyte_banner_file_input').click();
    });

    $('#incyyteUploadPhotoButton').click(function () {
        //alert('uploading');
        uploadLogo();
    });
    $('#incyyteUploadBannerButton').click(function () {
        //alert('uploading');
        uploadBanner();
    });
    $('#incyyte_photo_file_input').change(function () {
        $('#incyyte_photo_file_name').text($(this).val());
        $('#view_photo_file_name').text($(this).val());
    });
    $('#incyyte_banner_file_input').change(function () {
        $('#incyyte_banner_file_name').text($(this).val());
        $('#view_banner_file_name').text($(this).val());
    });
}
function uploadBanner() {
    //alert('inside upload logo');
    $('#incyyte_banner_error_msg2').hide("fast");

    var filePath = '';

    filePath = $("#incyyte_banner_file_input").val();
    //alert('filePath '+filePath);
    filePath = filePath.split('\\').pop();
    var ext = filePath.split('.').pop().toLowerCase();
    //alert('ext '+ext);
    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg', 'bmp']) == -1) {
        $("#incyyte_banner_error_msg").css("display", "");
        return false;
    }


    $('#file_name_banner_tst').val(filePath);

    $("#bizAccountForm").ajaxSubmit({
        type:'POST',
        url:'validateBannerLogo.cyt',
        success:function (data) {
            if (data.indexOf("failure") != -1) {
                //alert('validate banner size');
                $("#incyyte_banner_error_msg2").css("display", "");
            }
            else if (data.indexOf("success") != -1) {
                //alert('sccuess');
                $("#bizAccountForm").attr("action", "uploadBannerLogo.cyt");
                $("#bizAccountForm").submit();

            }

        },
        error:function (jqXHR, textStatus, errorThrown) {
            $('#incyyte_banner_error_msg2').show("fast");
        }
    });

    /* $("#bizAccountForm").attr("action", "uploadBannerLogo.cyt");
     $("#bizAccountForm").submit();*/
}
function deleteBannerConfirm() {
    //alert('delete logo');
    document.getElementById("banner_delete").className = 'banner_delete_confirm';
}
function deleteCompanyLogoConfirm() {
    //alert('delete complogo');

    document.getElementById("companylogo_delete").className = 'complogo_delete_confirm';

}
function deleteCompLogo(confirmLogo) {
    //alert('alert');
    /*	if(confirmLogo=='true'){
     $("#bizAccountForm").ajaxSubmit({
     type:'POST',
     url:".cyt",
     success:function (data) {
     },
     error:function (jqXHR, textStatus, errorThrown) {
     alert("error:" + textStatus + " exception:" + errorThrown);
     }
     });
     }else{
     document.getElementById("companylogo_delete").className = 'nodisplay';
     }*/
    if (confirmLogo == 'true') {
        $("#bizAccountForm").attr("action", "deleteCompLogo.cyt");
        $("#bizAccountForm").submit();
    } else {
        document.getElementById("companylogo_delete").className = 'nodisplay';
    }
}
function deleteBanner(confirmFlag) {
    /*if(confirmFlag=='true'){
     $("#bizAccountForm").ajaxSubmit({
     type:'POST',
     url:"deleteBanner.cyt",
     // dataType:"json",
     success:function (data) {
     },
     error:function (jqXHR, textStatus, errorThrown) {
     alert("error:" + textStatus + " exception:" + errorThrown);
     }
     });
     }else{
     document.getElementById("banner_delete").className = 'nodisplay';
     }*/
    if (confirmFlag == 'true') {
        $("#bizAccountForm").attr("action", "deleteBanner.cyt");
        $("#bizAccountForm").submit();
    } else {
        document.getElementById("banner_delete").className = 'nodisplay';
    }
}
function uploadLogo() {
    //alert('inside upload logo');
    $('#incyyte_photos_error_msg2').hide("fast");

    var filePath = '';

    filePath = $("#incyyte_photo_file_input").val();
    //alert('filePath '+filePath);
    filePath = filePath.split('\\').pop();
    var ext = filePath.split('.').pop().toLowerCase();
    //alert('ext '+ext);
    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg', 'bmp']) == -1) {
        $("#incyyte_photos_error_msg").css("display", "");
        return false;
    }
    $("#bizAccountForm").ajaxSubmit({
        type:'POST',
        url:'validateCompanyLogoSize.cyt',
        success:function (data) {
            if (data.indexOf("failure") != -1) {
                //alert('validate comp logo size');
                $("#incyyte_photos_error_msg2").css("display", "");
            }
            else if (data.indexOf("success") != -1) {
                //alert('sccuess comp');
                $("#bizAccountForm").attr("action", "uploadCompanyLogo.cyt");
                $("#bizAccountForm").submit();

            }

        },
        error:function (jqXHR, textStatus, errorThrown) {
            $('#incyyte_photos_error_msg2').show("fast");
        }
    });
    $('#file_name_tst').val(filePath);
}

function clientValidation(fieldName) {

    if (fieldName == 'companyName') {
        return isCompanyNameValid(fieldName);
    }

    if (fieldName == 'address1') {
        return isAddress1Valid(fieldName);
    }
    if (fieldName == 'address2') {
        return isAddress2Valid(fieldName);
    }
    if (fieldName == 'city') {
        return isCityValid(fieldName);
    }
    if (fieldName == 'postalCode') {
        return isPostcodeValid(fieldName);
    }
    if (fieldName == 'country') {
        return isCountryValid(fieldName);
    }
    if (fieldName == 'contactEmail') {
        return isContactEmailValid(fieldName);
    }
    if (fieldName == 'phone') {
        return isPhoneValid(fieldName);
    }
    if (fieldName == 'companyInfoPara1') {
        return isCompInfo1Valid(fieldName);
    }
    if (fieldName == 'companyInfoPara2') {
        return isCompInfo2Valid(fieldName);
    }
    if (fieldName == 'websiteUrl') {
        return isWebsiteUrlValid();
    }
}
function isCompanyNameValid(fieldName) {
    //alert('isCompanyNameValid');
    $('#inValidCompName').css("display", "none");
    var companyName = $("#companyName").val();
    var valid = true;
    var min = 5;
    var max = 20;
    var checkRegexp = /^[0-9a-zA-Z ]*$/;
    valid = checkLength($('#companyName'), "companyName", min, max);
    if (!valid) {
        //alert('valid if::'+valid);
        $('#inValidCompName').text('CompanyName must range between ' + min + ' and ' + max);
        $('#inValidCompName').css("display", "");
        return false;
    }
    if (!checkRegexp.test(companyName)) {
        //alert('Regular Exp');
        $('#inValidCompName').css("display", "none");
        $('#inValidCompName').text('AlphaNumerics only allowed');
        $('#inValidCompName').css("display", "");
        return false;
    }
    return true;
}
function isSpclCharAddress(address1) {
    //alert('inside SplCharAddress'+address1);
    //var address1=$("#address1").val();
    var iChars = "@&$/_#%?+*^[]{}|`~<>!=";
    var isValid = true;
    for (var i = 0; i < address1.length; i++) {
        if (iChars.indexOf(address1.charAt(i)) != '-1') {
            isValid = false;
            break;
            return isValid;
        } else {
            isValid = true;
        }
    }
    return isValid;
}

function isAddress1Valid(fieldName) {
    $('#inValidAddress1').css("display", "none");
    $('.inValidAddress1').css("display", "none");
    //alert('isAddress1Valid');
    var address1 = $("#address1").val();
    var valid = true;
    var min = 5;
    var max = 20;
    valid = checkLength($('#address1'), "address1", min, max);
    if ($("#address1").val() != "") {
        if (!valid) {
            //alert('valid::'+valid);
            $('#inValidAddress1').text('address1 must range between ' + min + ' and ' + max);
            $('#inValidAddress1').css("display", "");
            return false;
        }
        if (!isSpclCharAddress(address1)) {
            //alert('is SplChars address');
            $('.inValidAddress1').text('Following invalid characters @$&/_#%?+*^[]{}|`~<>!= are not allowed');
            $('.inValidAddress1').css("display", "");
            return false;
        }
    }
    return true;
}
function isAddress2Valid(fieldName) {
    $('#inValidAddress2').css("display", "none");
    $('.inValidAddress2').css("display", "none");
    //alert(' Inside isAddress2Valid');
    var address2 = $("#address2").val();
    //alert('address2 '+address2);
    var valid = true;
    var min = 5;
    var max = 20;
    valid = checkLength($('#address2'), "address2", min, max);
    if ($("#address2").val() != "") {
        //alert('address2 is not nullllllll'+address2);

        if (!valid) {
            //alert('valid Inside if::'+valid);
            $('#inValidAddress2').text('address2 must range between ' + min + ' and ' + max);
            $('#inValidAddress2').css("display", "");
            return false;
        }
        if (!isSpclCharAddress(address2)) {
            //alert('is SplChars address');
            $('.inValidAddress2').text('Following invalid characters @$&/_#%?+*^[]{}|`~<>!= are not allowed');
            $('.inValidAddress2').css("display", "");
            return false;
        }
    }
    return true;
}
function isCityValid(fieldName) {
    $('#inValidCity').css("display", "none");
    $('.inValidCity').css("display", "none");
    //alert('isCityValid');
    var city = $("#city").val();
    var valid = true;
    var min = 5;
    var max = 20;
    var checkRegexp = /^[a-zA-Z]+$/;
    valid = checkLength($('#city'), "city", min, max);
    if ($("#city").val() != "") {
        //alert('city is nullllllll'+city);
        /*return false;*/
        if (!valid) {
            //alert('valid::'+valid);
            $('#inValidCity').text('city must range between ' + min + ' and ' + max);
            $('#inValidCity').css("display", "");
            return false;
        }
        if (!checkRegexp.test(city)) {
            //alert('Regular Exp:city');
            $('#inValidCity').css("display", "none");
            $('.inValidCity').text('Alphabets only allowed');
            $('.inValidCity').css("display", "");
            return false;
        }
    }
    return true;
}
function isPostcodeValid(fieldName) {
    $('#inValidPostcode').css("display", "none");
    $('.inValidPostcode').css("display", "none");
    var postalCode = $("#postalCode").val();
    //alert('Postcode value::' + postalCode);
    var valid = true;
    var min = 6;
    var max = 8;
    var checkRegexp = /^([Gg][Ii][Rr]0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))){0,1}[0-9][A-Za-z]{2})$/;
    valid = checkLength($('#postalCode'), "postalCode", min, max);

    //alert("is location valid"+valid);
    if (!valid) {
        $('#inValidPostcode').text('postcode must range between ' + min + ' and ' + max);
        $('#inValidPostcode').css("display", "");
        return false;
    }
    if (!checkRegexp.test(postalCode)) {
        //alert('regular exp:post code');
        $('.inValidPostcode').text('Invalid Post Code');
        $('.inValidPostcode').css("display", "");
        return false;
    }
    return true;
}

function isCountryValid(fieldName) {
    $('#inValidCountry').css("display", "none");
    $('.inValidCountry').css("display", "none");
    //alert('isCountryValid');
    var country = $("#country").val();
    var valid = true;
    var min = 10;
    var max = 20;
    // var checkRegexp=/^[a-zA-Z]+$/;
    var checkRegexp = /^[a-zA-Z ]*$/;
    valid = checkLength($('#country'), "country", min, max);
    if ($("#country").val() != "") {
        //alert('country is nullllllll'+country);
        //return false;
        if (!valid) {
            //alert('valid::'+valid);
            $('#inValidCountry').text('country must range between ' + min + ' and ' + max);
            $('#inValidCountry').css("display", "");
            return false;
        }
        if (!checkRegexp.test(country)) {
            //alert('Regular Exp:country');
            $('#inValidCountry').css("display", "none");
            $('.inValidCountry').text('Alphabets only allowed');
            $('.inValidCountry').css("display", "");
            return false;
        }
    }
    return true;
}
function isContactEmailValid(fieldName) {
    $('#inValidEmail').css("display", "none");
    var contactEmail = $("#contactEmail").val();
    var checkRegexp = /^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\.)+([A-Za-z0-9]{2,4}|museum)$/;
    var valid = true;
    if ($("#contactEmail").val() != "") {
        //alert('contactEmail is nullllllll'+contactEmail);
        //return false;
        if (!checkRegexp.test(contactEmail)) {
            $('#inValidEmail').text('Enter a valid email address');
            $('#inValidEmail').css("display", "");
            return false;
        }
    }
    return true;

}
function isPhoneValid(fieldName) {
    $('#inValidPhone').css("display", "none");
    $('.inValidPhone').css("display", "none");
//	alert("inside isMobileValid ");
    var valid = true;
    var min = 10;
    var max = 15;
    var phone = $("#phone").val();
    var checkRegexp = /^[0-9\s\-\+]*$/;
    valid = checkLength($('#phone'), "phone", min, max);
//	alert("is location valid"+valid);
    if ($("#phone").val() != "") {
        //alert('phone is nullllllll'+phone);
        //return false;
        if (!valid) {
            $('#inValidPhone').text('phone number must range between ' + min + ' and ' + max);
            $('#inValidPhone').css("display", "");
            return false;
        }
        if (!checkRegexp.test(phone)) {
            $('.inValidPhone').text('Phone numner should be Numeric');
            $('.inValidPhone').css("display", "");
            return false;
        }
    }
    return true;
}
function isSpclCharWebsiteUrl(fieldName) {
    //alert('alert::'+fieldName);
    var websiteUrl = $("#websiteUrl").val();
    var iChars = "^&?+*[]{}\|`~<>%#$!=-'";
    var isValid = true;
    for (var i = 0; i < websiteUrl.length; i++) {
        if (iChars.indexOf(websiteUrl.charAt(i)) != '-1') {
            //alert('isValid::'+isValid);
            isValid = false;
            break;
        } else {
            isValid = true;
        }
    }
    return isValid;
}


function isWebsiteUrlValid() {
    $('#inValidWebsite').css("display", "none");
    var websiteUrl = $("#websiteUrl").val();
    var length = websiteUrl.length;
    if (length >= 80) {
        $("#inValidWebsiteLength").text("Your website url  has exceeded our 80 characters limit");
        $("#inValidWebsiteLength").css("display", "");
    } else {
        $("#inValidWebsiteLength").css("display", "none");
    }
    var iChars = "^&?+*[]{}\|`~<>%#$@!%(); ";
    for (var i = 0; i < websiteUrl.length; i++) {
        if (iChars.indexOf(websiteUrl.charAt(i)) != '-1') {
            $('#inValidWebsite').text('Enter a valid Website Url');
            $('#inValidWebsite').css("display", "");
            return false;
            break;
        }
    }
    var checkRegexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    if ($("#websiteUrl").val() != "") {
        if (!checkRegexp.test(websiteUrl) || isSpclCharWebsiteUrl(websiteUrl) == false) {
            $('#inValidWebsite').text('Enter a valid Website Url');
            $('#inValidWebsite').css("display", "");
            return false;
        }
    }
    return true;
}
function isCompInfo1Valid(fieldName) {
    $('#inValidDescription1').css("display", "none");
    //alert(' Inside isCompInfo1Valid');
    var companyInfo1 = document.getElementById("companyInfoPara1").value;
    //alert('companyInfoPara1::'+companyInfoPara1);
    //alert('companyInfo1::'+companyInfo1);
    if (companyInfo1 != '') {
        //alert('text length exceeds 250 chars');
        var textLength = companyInfo1.length;
        //alert('companyInfo1 textLength::'+textLength);
        if (textLength >= 250) {
            //alert("exceeded 250 chars:"+textLength);
            $('#inValidDescription1').text("Your compnay information has exceeded our 250 characters limit. Please reduce content.");
            $('#inValidDescription1').css("display", "");
            return false;
        }
        if (!isSpclCharCompInfo(companyInfo1)) {
            //alert('isSpclCharCompInfo1 in if');
            $('#inValidDescription1').text('Following invalid characters !@#$%^&*()+=_`~-[]\\\';,./{}|\":<>? are not allowed');
            $('#inValidDescription1').css("display", "");
            return false;
        }
    }
    return true;
}
function isCompInfo2Valid(fieldName) {
    $('#inValidDescription2').css("display", "none");
    //alert(' Inside isCompInfo2Valid');
    var companyInfo2 = document.getElementById("companyInfoPara2").value;
    //alert('companyInfo2::'+companyInfo2);
    if (companyInfo2 != '') {
        var textLength = companyInfo2.length;
        if (textLength >= 250) {
            $('#inValidDescription2').text("Your compnay information has exceeded our 250 characters limit. Please reduce content.");
            $('#inValidDescription2').css("display", "");
            return false;
        }
        if (!isSpclCharCompInfo(companyInfo2)) {
            //alert('is SplChars comp info');
            $('#inValidDescription2').text('Following invalid characters !@#$%^&*()+=_`~-[]\\\';,./{}|\":<>? are not allowed');
            $('#inValidDescription2').css("display", "");
            return false;
        }
    }
    return true;
}
function isSpclCharCompInfo(companyInfoPara) {
    //alert("Inside isSpclCharCompInfo "+companyInfoPara);
    //var companyInfoPara1=$("#companyInfoPara1").val();
    var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
    var isValid = true;
    for (var i = 0; i < companyInfoPara.length; i++) {
        if (iChars.indexOf(companyInfoPara.charAt(i)) != '-1') {
            isValid = false;
            break;
            return isValid;
        } else {
            isValid = true;
        }
    }
    return isValid;
}

function checkLength(o, n, min, max) {
    /*alert('checkLength');
     alert('Min:' + min);
     alert('Max:' + max);
     alert('Length:' + o.val().length);
     */
    if (o.val().length > max || o.val().length < min) {
        return false;
    } else {
        return true;
    }
}


/*function removeValidation(fieldName){
 if(fieldName=='companyName'){
 $("#inValidCompName").css("display","none");
 }
 if(fieldName=='address1'){
 $("#inValidAddress1").css("display","none");
 }
 if(fieldName=='postalCode'){
 $("#inValidPostcode").css("display","none");
 }
 if(fieldName=='websiteUrl'){
 $("#inValidWebsite").css("display","none");
 }
 }*/

function validReqFields(fieldName) {
    //alert("Inside validReqFields");
    var valid = true;
    if ($('#' + fieldName).val() == "") {
        //alert("fieldName is null");
        if (fieldName == 'companyName') {
            //alert("validReqFields "+fieldName );
            $("#inValidCompName").text('CompanyName is required');
            $("#inValidCompName").css("display", "");
        }
        if (fieldName == 'address1') {
            //alert("validReqFields "+fieldName );
            $("#inValidAddress1").text('Address1 is required');
            $("#inValidAddress1").css("display", "");
        }
        if (fieldName == 'postalCode') {
            //alert("validReqFields "+fieldName );
            $("#inValidPostcode").text("PostCode is required");
            $("#inValidPostcode").css("display", "");
        }
        if (fieldName == 'websiteUrl') {
            //alert("validReqFields "+fieldName );
            $("#inValidWebsite").text("WebsiteUrl is required");
            $("#inValidWebsite").css("display", "");
        }
        valid = false;
    }
    return valid;
}
function editSaveBuzAcct(fieldComp, fieldAdd, fieldPostCode, fieldWebsite, fieldAddr2, fieldCity, fieldCountry, fieldEmail, fieldPhone, fieldCompInfo1, fieldCompInfo2, submitUrl) {
    //alert("Inside Save and Edit");
    if ($("#editBizAcctInfo").hasClass("edit")) {
        //document.getElementById("websiteUrl").removeAttribute("disabled");
        //alert("Inside Edit");
        //alert($("#saveChanges").val());
        $("#saveChanges").val("true");
        //alert($("#saveChanges").val());
        $("#editBizAcctInfo").removeClass("edit").css("width", "200px").css("float", "right");
        $("#editBizAcctInfo").removeAttr("readOnly").removeClass("readonly_input");
        $("#editBizAcctInfo" + " span").removeClass("title_green").text("Edit");
        $("#companyName").removeAttr("disabled");
        $("#address1").removeAttr("disabled");
        $("#address2").removeAttr("disabled");
        $("#city").removeAttr("disabled");
        $("#postalCode").removeAttr("disabled");
        $("#country").removeAttr("disabled");
        $("#contactEmail").removeAttr("disabled");
        $("#phone").removeAttr("disabled");
        $("#companyInfoPara1").removeAttr("disabled");
        $("#companyInfoPara2").removeAttr("disabled");
        $("#websiteUrl").removeAttr("disabled");
        //alert("Displaying SAVE CHANGES");
        $("#editBizAcctInfo").addClass("button_green").css("width", "200px").css("float", "right");
        $("#editBizAcctInfo" + " span").addClass("title_green").text('SAVE CHANGES');
        $("#edit_bizacct").css("display", "none");
        $("#deletelogolink").css("display", "");
        $("#deletebannerlogolink").css("display", "");
        $("#uploadlogolink").css("display", "");
        $("#uploadBannerlogolink").css("display", "");
        $("#companyLogoMessage").css("display", "");
        $("#bannerLogoMessage").css("display", "");
    }

    else if ($("#editBizAcctInfo").hasClass("button_green") || $("#saveBizAcctInfo").hasClass("button_green") || $("#saveUserAcctInfo").hasClass("button_green")) {
        //alert('inside else %%%%%%%%%%%%%%%%%%%%%%%');
        //alert('inside create user business account:::'+submitUrl);
        //var urlValue = getContextPath() + "/" + submitUrl + '?tempVar=' + Math.random();
        var company_Name = validReqFields(fieldComp);
        var add_1 = validReqFields(fieldAdd);
        var post_Code = validReqFields(fieldPostCode);
        var website_Url = validReqFields(fieldWebsite);

        if (company_Name && add_1 && post_Code && website_Url) {
            var companyName = validateFields(fieldComp);
            var addr1 = validateFields(fieldAdd);
            var postCode = validateFields(fieldPostCode);
            var webUrl = isWebsiteUrlValid();
            var addr2 = isAddress2Valid(fieldAddr2);
            var city = isCityValid(fieldCity);
            var country = isCountryValid(fieldCountry);
            var email = isContactEmailValid(fieldEmail);
            var phone = isPhoneValid(fieldPhone);
            var compInfo1 = isCompInfo1Valid(fieldCompInfo1);
            var compInfo2 = isCompInfo2Valid(fieldCompInfo2);
            //alert('isRequiredvalid:true'+validateFields(fieldComp));
            //alert("companyName::"+companyName+" addr1::"+addr1+" postCode::"+postCode+" webUrl::"+webUrl+"addr2::"+addr2+" city::"+city+" country::"+country+" email::"+email+" phone::"+phone+" compInfo1::"+compInfo1+" compInfo2::"+compInfo2);
            if (companyName && addr1 && postCode && addr2 && city && country && email && phone && compInfo1 && compInfo2 && webUrl) {
                //	if(companyName && addr1 && postCode && webUrl && addr2 ){

                //alert('fieldComp::'+fieldComp);
                $("#bizAccountForm").ajaxSubmit({
                    type:"POST",
                    url:submitUrl,
                    // dataType:"text",
                    success:function (data) {
                        if (data.search("success") == 0) {
                            //alert("saveChanges value::"+$("#saveChanges").val());
                            $("#saveBizAcctInfo").css("display", "");
                            $("#saveBizAcctInfoSpan").css("display", "");

                            $("#create_bizacct").text("Your Business Account has created successfully");
                            $("#create_bizacct").css("display", "");

                            $("#edit_bizacct").text("Your Business Account have edited successfully");
                            $("#edit_bizacct").css("display", "");

                            $("#companyName").attr('disabled', true);
                            $("#address1").attr('disabled', true);
                            $("#address2").attr('disabled', true);
                            $("#city").attr('disabled', true);
                            $("#postalCode").attr('disabled', true);
                            $("#country").attr('disabled', true);
                            $("#contactEmail").attr('disabled', true);
                            $("#phone").attr('disabled', true);
                            $("#companyInfoPara1").attr('disabled', true);
                            $("#companyInfoPara2").attr('disabled', true);
                            $("#websiteUrl").attr('disabled', true);
                            window.location.reload(true);

                        }
                        else if (data.search("error") == 0) {
                            //alert('error: ' + data);
                            data = data.replace("error:", "");
                            // alert('Formatted error: ' + data);
                            $("#create_bizacct").css("display", "none");
                            $("#create_biz_error").text(data);
                            $("#create_biz_error").css("display", "");

                            $("#inValidCompName").css("display", "");
                            $("#inValidAddress1").css("display", "");
                            $("#inValidPostcode").css("display", "");
                            $("#inValidWebsite").css("display", "");
                            $("#inValidCompanyLogo").css("display", "");
                            $("#inValidBanner").css("display", "");
                        }
                    }
                });
            }
        }
    }
}