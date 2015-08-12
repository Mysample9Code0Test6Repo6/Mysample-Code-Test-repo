var emailList;

document.write("<ul id='left_mainmenu' class='accordion'>");
document.write("<li ><a href='./messages.cyt'>All Messages</a></li>");
document.write("<li ><a href='./composeNew.cyt'>Compose New Message</a></li>");
document.write("</ul>");

function doHideShow(display, messageId) {
    if (display) {
        var deletePopups = document.getElementsByName("msg_delete_popup");
        for (var i = 0; i < deletePopups.length; i++) {
            deletePopups[i].className = 'nodisplay';
        }
        document.getElementById("msg_delete_confirm_" + messageId).className = 'msg_delete_confirm msg_delete_bg_ie8';
    } else {
        document.getElementById("msg_delete_confirm_" + messageId).className = 'nodisplay';
    }
}

function displayDetailMessage(messageId) {
    document.getElementById("message_detail_" + messageId).className = 'message_detail';
}

function displayContacts() {
    $("#error").css("display", "none");
    var contactName = document.getElementById("contactList").value;
    httpRequest.open("GET", 'searchMyContacts.json?contactId=' + contactName, true);
    httpRequest.onreadystatechange = getContacts;
    httpRequest.send(null);
}

function getContacts() {
    if (httpRequest.readyState == 4 || httpRequest.readyState == 200) {
        var username = httpRequest.responseText;
        username = username.replace('[', '');
        username = username.replace(']', '');
        emailList = username.split(",");
        $("#contactList").autocomplete({ source:emailList });
    }
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
function submitDelete(messageId, timeoutPeriod) {
    httpRequest.open("GET", 'deleteConversation.cyt?messageId=' + messageId, true);
    httpRequest.send(null);
    document.getElementById("msg_delete_confirm_" + messageId).className = 'nodisplay';
    setTimeout("location.reload(true);", timeoutPeriod);
}

function confirmDelete(messageId, timeoutPeriod) {
    //this method is for delete the message
    httpRequest.open("GET", 'deleteMessage.cyt?messageId=' + messageId, true);
    httpRequest.send(null);
    document.getElementById("msg_delete_confirm_" + messageId).className = 'nodisplay';
    setTimeout("location.reload(true);", timeoutPeriod);
}

function doDetailView(messageId, contactId, userEmail) {
    window.open('messagesDetail.cyt?messageId=' + messageId + '&contactId=' + contactId + '&email=' + userEmail, '_self');
}

function doCancel(isDeleteRequire, deleteMsgId) {
    if (isDeleteRequire) {
        var deletePopups = document.getElementsByName("msgdtl_delete_popup");
        for (var i = 0; i < deletePopups.length; i++) {
            deletePopups[i].className = 'nodisplay';
        }
        document.getElementById("msg_delete_confirm_" + deleteMsgId).className = 'msg_delete_confirm msg_delete_bg_ie8';
    } else {
        document.getElementById("msg_delete_confirm_" + deleteMsgId).className = 'nodisplay';
    }
}

function replyMessage() {
    var contactId = document.getElementById("contactId").value;
    var messageId = document.getElementById("messageId").value;
    var messageContent = document.getElementById("messageContent").value;
    messageContent = $.trim($('#messageContent').val());
    if(messageContent == "Add your Reply here..."){
    	messageContent = '';
    }    
    if (messageContent != '') {
        var textLength = messageContent.length;
        if (textLength <= 2000) {
            if (isSpclChar(messageContent)) {            	
                //httpRequest.onreadystatechange = replyStateChange;
                //httpRequest.open("GET", 'sendReply.cyt?contactId=' + contactId + '&messageId=' + messageId + '&messageContent=' + messageContent, true);
                //httpRequest.send(null);
            	
            	$("#messages").ajaxSubmit({
                    type:'POST',
                    url:'./sendReply.cyt',
                    success:function (response) {
                    	if (response.search("success") == 0) {
                            setTimeout(location.reload(true), 5);
                        } else if (response.search("redirect") == 0) {
                            $("#composeNewMsg").attr("action", "welcome.cyt");
                            $("#composeNewMsg").submit();
                        } else {
                            $("#sendmsg_error").css("display", "none");
                            $("#sendmsg_error").text(response);
                            $("#sendmsg_error").css("display", "");
                        }

                    },
                    error:function (jqXHR, textStatus, errorThrown) {
                        alert("error:" + textStatus + " exception:" + errorThrown);
                    }
                });
            	
            } else {
                $("#sendmsg_error").text("An invalid character has been entered ^+*\|`~ are not allowed");
                $("#sendmsg_error").css("display", "");
            }
        } else {
            $("#sendmsg_error").text("Your Message has exceeded our 2000 characters limit. Please reduce content.");
            $("#sendmsg_error").css("display", "");
        }
    } else {
        $("#error").css("display", "none");
        $("#sendmsg_error").text("Please enter the Message Text");
        $("#sendmsg_error").css("display", "");
    }
}

function replyStateChange() {
    if (httpRequest.readyState == 4 || httpRequest.readyState == "complete") {
        var response = httpRequest.responseText;
        if (response.search("success") == 0) {
            setTimeout(location.reload(true), 5);
        } else if (response.search("redirect") == 0) {
            $("#composeNewMsg").attr("action", "welcome.cyt");
            $("#composeNewMsg").submit();
        } else {
            $("#sendmsg_error").css("display", "none");
            $("#sendmsg_error").text(response);
            $("#sendmsg_error").css("display", "");
        }
    }
}


function isSpclUsernameChar(messageText) {
    //Changed the restriction list of special characters as per bug: 201
    var iChars = "/^&?+*[]{}\|`~<>%#$";   
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

function isSpclChar(messageText) {
    //Changed the restriction list of special characters as per bug: 201
    //var iChars = "/^&?+*[]{}\|`~<>%#$";
    var iChars = "^+*\|`~";
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

function messageValidation(message) {
    $("#sendmsg_error").css("display", "none");
    $("#smileyiconsuccess").css("display", "none");
    var messageContent = message.value;
    var replytextLength = messageContent.length;
    if (isSpclChar(messageContent) == false) {
        $("#sendmsg_error").text("An invalid character has been entered ^+*\|`~ are not allowed");
        $("#sendmsg_error").css("display", "");
    }
    if (replytextLength > 2000) {
        $("#sendmsg_error").text("Your Message has exceeded our 2000 characters limit. Please reduce content.");
        $("#sendmsg_error").css("display", "");
    }
}

function smileyIconAvailability(){
	$("#smileyiconsuccess").text("AVAILABLE SOON!");
    $("#smileyiconsuccess").css("display", "");
}

function composeMsg() {
    var contactname = $("#contactList").val();
    var messagetext = $("#messagetext").val(); 
    messagetext= $.trim($('#messagetext').val());
    if(contactname == "Enter Contact Name"){
    	contactname = '';
    }
    if(messagetext == "Add your message here..."){
    	messagetext ='';
    }
    if (messagetext != '' && contactname != '') {
        //If Contact Name contains any special character then it will display the below error message.
        if (!isSpclUsernameChar(contactname)) {
            $("#error").text(contactname + " is not a valid contact. Please re-enter");
            $("#error").css("display", "");
            return false;
        }
        var textLength = messagetext.length;
        if (textLength <= 2000) {
            if (isSpclChar(messagetext)) {
                //httpRequest.onreadystatechange = stateChange;
                //httpRequest.open("GET", 'sendMessage.cyt?contactname=' + contactname + '&messagetext=' + messagetext, true);
                //httpRequest.send(null);            	
            	$("#composeNewMsg").ajaxSubmit({
                    type:'POST',
                    url:'./sendMessage.cyt',
                    success:function (response) {
                    	if (response.search("success") == 0) {
                            $("#composeNewMsg").attr("action", "success_message.cyt");
                            $("#composeNewMsg").submit();
                        } else if (response.search("redirect") == 0) {
                            $("#composeNewMsg").attr("action", "welcome.cyt");
                            $("#composeNewMsg").submit();
                        } else {
                            $("#sendmsg_error").css("display", "none");
                            $("#error").text(response);
                            $("#error").css("display", "");
                            $("#send_new_msg").css("display", "");
                        }
                    },
                    error:function (jqXHR, textStatus, errorThrown) {
                        alert("error:" + textStatus + " exception:" + errorThrown);
                    }
                });
            	
            	
            } else {
                $("#sendmsg_error").text("An invalid character has been entered /^+*\|`~ are not allowed");
                $("#sendmsg_error").css("display", "");
            }
        } else {
            $("#sendmsg_error").text("Your Message has exceeded our 2000 characters limit. Please reduce content.");
            $("#sendmsg_error").css("display", "");
        }
    } else {
        if (contactname == '') {
            $("#error").css("display", "none");
            $("#error").text("Please enter the contact username");
            $("#error").css("display", "");
        } else {
            $("#error").css("display", "none");
            $("#sendmsg_error").text("Please enter the Message Text");
            $("#sendmsg_error").css("display", "");
        }
    }
}

function stateChange() {
    if (httpRequest.readyState == 4 || httpRequest.readyState == "complete") {
        var response = httpRequest.responseText;
        if (response.search("success") == 0) {
            $("#composeNewMsg").attr("action", "success_message.cyt");
            $("#composeNewMsg").submit();
        } else if (response.search("redirect") == 0) {
            $("#composeNewMsg").attr("action", "welcome.cyt");
            $("#composeNewMsg").submit();
        } else {
            $("#sendmsg_error").css("display", "none");
            $("#error").text(response);
            $("#error").css("display", "");
            $("#send_new_msg").css("display", "");
        }
    }
}