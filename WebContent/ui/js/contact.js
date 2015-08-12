var contactId;
var email;
// JavaScript Document

// jquery initializer, do not alter.
$(document).ready(function () {
    document_onInit();
});

// all startup logic should exist in the init function.
function document_onInit() {
    setDefaultInput();
    //wire event handlers
    $("#InviteFrends").click(function () {
        addContact();
    });
    $("#updateContact").click(function () {
        saveContact();
    });
    $("#searchSubmit").click(function () {
        searchContact();
    });
    $("#add").click(function () {
        $("#addfrm").css("display", "");
        $("#searchfrm").css("display", "none");
    });
    $("#close").click(function () {
        $('#contact_details').hide();
    });
    
/*
	$("#displayGroupList").click(function () {
		parent.$.fn.colorbox({'href':'div#groupList', 'open':true, 'inline':true});
		$("#NewGrpRow").css("display", "");
		userGroupListJsonCall();
	});
*/

	$("#newGroup").click(function () {
		$("#NewGrpRow").css("display", "");
		addnewGrpTextField = true;
		
		//uncheck radio button
		$("input:radio").attr("checked", false);
		$("#groupRadio").val("");		
	});

	$("#removeNewGrpName").click(function () {
		addnewGrpTextField = false;
        $("#NewGrpRow").css('display', 'none');
		$("#gname_error").css("display", "none");	
	});
}

function shareToGroup(pollId) {
    document.getElementById("selectedPoll").value = pollId;
    parent.$.fn.colorbox({'href':'div#groupList', 'open':true, 'inline':true});
    $("#NewGrpRow").css("display", "");
    userGroupListJsonCall();
}

function searchContact() {
    var search = $("#search").val();
    $("#searchForm").ajaxSubmit({
        type:'POST',
        url:'./contactsHome.cyt?param=' + search
    });
}
function setDefaultInput() {
    $("#communicator").css("display", "none");
	$("#gname_error").css("display", "none");			
}

function validateEmail(field) {
    var regex = /^([\w-`~!#$^&*()-+=";:,<>\.]+)(.[\w-`~!#$^&*()-+=";:,<>\.]+)*@([\w`~!#$^&*()-+=";:,<>\.]+\.){1,5}([A-Za-z-`~!#$^&*()-+=";:,<>\.]){2,4}$/;
    return (regex.test(field)) ? true : false;
}

function validateMultipleEmailsCommaSeparated(value) {
	var comma = /,/;
	var semicolon =/;/;
	var importEmails = $('#importFrom').val();
	if(comma.test(value)|| semicolon.test(value)) {
		var result = value.split(",");
		for (var i = 0; i < result.length; i++)
			if (!validateEmail(result[i])) {
				if(importEmails == 'importEmails'){
					$("#singlImportEmaileroor").css("display","none");
					$("#sameImportEmailmessage").css("display", "none");
					$("#existingImportEmail").css("display", "none");
					$('#invalidImportEmail').css("display", "");
					return false;
				}else{
					$("#singlemaileroor").css("display","none");
					$("#sameemailmessage").css("display", "none");
					$("#existingContact").css("display", "none");
					$('#invalidemail').css("display", "");
					return false;
				}
			}
		var result = value.split(";");
		for (var i = 0; i < result.length; i++)
			if (!validateEmail(result[i])) {
				if(importEmails == 'importEmails'){
					$("#singlImportEmaileroor").css("display","none");
					$("#sameImportEmailmessage").css("display", "none");
					$("#existingImportEmail").css("display", "none");
					$('#invalidImportEmail').css("display", "");
					return false;
				}else{
					$("#singlemaileroor").css("display","none")
					$("#sameemailmessage").css("display", "none");
					$("#existingContact").css("display", "none");
					$('#invalidemail').css("display", "");
					return false;
				}
			}
		return true;
	}
	else{
		if(validateEmail(value)){
			return true;
		}
		else{
			if(importEmails == 'importEmails'){
				$("#sameImportEmailmessage").css("display", "none");
				$("#existingImportEmail").css("display", "none");
				$('#invalidImportEmail').css("display", "none");
				$("#singlImportEmaileroor").css("display","");
				return false;
			}else{
				$("#sameemailmessage").css("display", "none");
				$("#existingContact").css("display", "none");
				$('#invalidemail').css("display", "none");
				$("#singlemaileroor").css("display","")
				return false;
			}
		}
	}
}


function addContact() {
   if (validateMultipleEmailsCommaSeparated($("#email").val())) {
        $("#addContactForm").ajaxSubmit({
            type: 'POST',
            url: 'mycontacts/submit.cyt',
            success: function (data) {
                if (data === "error") {
                    $("#singlemaileroor").css("display","none")
                    $("#invalidemail").css("display", "none");
                    $("#sameemailmessage").css("display", "");
                    $("#existingContact").css("display", "none");
                    $("#invite_poeple_sucess").css("display", "none");
                    $("#invite_poeple").css("display", "");
                } else if (data === "isContactError") {
                    $("#singlemaileroor").css("display","none")
                    $("#invalidemail").css("display", "none");
                    $("#sameemailmessage").css("display", "none");
                    $("#existingContact").css("display", "");
                    $("#invite_poeple_sucess").css("display", "none");
                    $("#invite_poeple").css("display", "");
                } else {
                    $("#singlemaileroor").css("display","none")
                    $("#invalidemail").css("display", "none");
                    $("#sameemailmessage").css("display", "none");
                    $("#existingContact").css("display", "none");
                    $("#invite_poeple").css("display", "none");
                    $("#invite_poeple_sucess").css("display", "");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#communicator").css("display", "");
                // setError("oldPwd", "You have entered an invalid password.");
            }
        });
  }
}

function uploadLogoFunc() {
	$("#errorForLogoFormat").css("display","none");
	filePath = $('#uploadedDocFile').val();
	if (filePath == null || filePath == '') {
		return false;
	}
	var fileName = filePath.substring(12);
	$('#fileName').val(fileName);

	var ext = filePath.split('.').pop().toLowerCase();
	if($.inArray(ext, ['csv']) == -1) {
		$("#errorForLogoFormat").css("display","");
		$(".errorLabel").text("Please upload the correct file format (.csv)");
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
		url:'mycontacts/uploadCSVFile.cyt',
		success:function (data) {
			 if (data.indexOf("failure") != -1) {
				$("#errorForLogoFormat").css("display","");
				$(".errorLabel").text("The uploaded Doc exceeds the maximum allowed size(2 MB)");
			}else if(data == "noColumnError"){
				$("#errorForLogoFormat").css("display","");
				$(".errorLabel").text("Error Msg: No column named 'Email' was found in this csv upload. Please add a column name(Email).");
			}else{
				window.setTimeout(location.reload(true),1);
			} 
		},
		error:function (jqXHR, textStatus, errorThrown) {
			$("#errorForLogoFormat").css("display","");
		}
	});
}

function importEmails() {
	var importEmails = $('#importEmail').val();
	importEmails = importEmails.trim();
	if(importEmails == '') {
		$("#singlImportEmaileroor").text("No emails are found to Import. Please provide atleast one email address.");
		$("#singlImportEmaileroor").css("display","");
		return;
	}
        $("#addImportEmailsForm").ajaxSubmit({
            type: 'POST',
            url: 'mycontacts/submit.cyt?importEmails=importing',
            success: function (data) {
                if (data === "error") {
                    $("#singlImportEmaileroor").css("display","none");
                    $("#invalidImportEmail").css("display", "none");
                    $("#sameImportEmailmessage").css("display", "");
                    $("#existingImportEmail").css("display", "none");
                    $("#import_emails_sucess").css("display", "none");
                    $("#importEmails").css("display", "");
                } else if (data === "isContactError") {
                    $("#singlImportEmaileroor").css("display","none");
                    $("#invalidImportEmail").css("display", "none");
                    $("#sameImportEmailmessage").css("display", "none");
                    $("#existingImportEmail").css("display", "");
                    $("#import_emails_sucess").css("display", "none");
                    $("#importEmails").css("display", "");
                } else {
                    $("#singlImportEmaileroor").css("display","none");
                    $("#invalidImportEmail").css("display", "none");
                    $("#sameImportEmailmessage").css("display", "none");
                    $("#existingImportEmail").css("display", "none");
                    $("#importEmails").css("display", "none");
                    $("#import_emails_sucess").css("display", "");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#communicator").css("display", "");
            }
        });
}


var addnewGrpTextField = false;
function importEmailsToGroup() {
	var importEmails = $('#importEmail').val();

	var selectedGroupID = "";//$("#groupRadio").val();
	var newGroupName = $('#newGrpName').val();
	//alert("New Group Name: " + newGroupName + " Selected Group: "+selectedGroupID );
	
	if (newGroupName == ''){
		$("#gname_error").css("display", "");	
		$("#gname_error").text("Enter your new group name.");		
		return;
	}
	
	
	importEmails = importEmails.trim();
	if(importEmails == '') {
		parent.$.fn.colorbox.close();
		$("#singlImportEmaileroor").text("No emails are found to Import. Please provide atleast one email address.");
		$("#singlImportEmaileroor").css("display","");
		return;
	}
        $("#addImportEmailsForm").ajaxSubmit({
            type: 'POST',
            url: 'mycontacts/submit.cyt?importEmails=importing&grpID='+selectedGroupID+'&newgrpName='+newGroupName,
            success: function (data) {
                if (data === "error") {
            		parent.$.fn.colorbox.close();
                    $("#singlImportEmaileroor").css("display","none");
                    $("#invalidImportEmail").css("display", "none");
                    $("#sameImportEmailmessage").css("display", "");
                    $("#existingImportEmail").css("display", "none");
                    $("#import_emails_sucess").css("display", "none");
                    $("#importEmails").css("display", "");
                } else if (data === "groupNameExists") {
                	 $("#gname_error").css("display", ""); 
                	 $("#gname_error").text("This Group Name already exists");  
                } else if (data === "isContactError") {
            		parent.$.fn.colorbox.close();
                    $("#singlImportEmaileroor").css("display","none");
                    $("#invalidImportEmail").css("display", "none");
                    $("#sameImportEmailmessage").css("display", "none");
                    $("#existingImportEmail").css("display", "");
                    $("#import_emails_sucess").css("display", "none");
                    $("#importEmails").css("display", "");
                } else {
            		parent.$.fn.colorbox.close();
                    $("#singlImportEmaileroor").css("display","none");
                    $("#invalidImportEmail").css("display", "none");
                    $("#sameImportEmailmessage").css("display", "none");
                    $("#existingImportEmail").css("display", "none");
                    $("#importEmails").css("display", "none");
                    $("#import_emails_sucess").css("display", "");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#communicator").css("display", "");
            }
        });
}

function processBlockContact(contactid, email) {
    $("#contactid").val(contactid);
    $("#email").val(email);
    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/block.cyt',
        success: function (data) {
            if (data.search("error") == 0) {
                $("#message_" + contactid).text("contact " + email + " Not Blocked");
                $("#message_" + contactid).css("color", "fireBrick");
                $("#message_" + contactid).css("display", "");
                $("#unblock_" + contactid).css("display", "none");
                $("#block_" + contactid).css("display", "");
                $("#sendMessage").css("display", "");
                } else {
            	$("#message_" + contactid).text(data);
                $("#message_" + contactid).css("display", "");
                $("#block_" + contactid).css("display", "none");
                $("#unblock_" + contactid).css("display", "");
                $("#sendMessage").css("display", "none");
                document.getElementById("blockStatus_" + contactid).value = "Y";
                $("#sendMessageContact_"+contactid).css("display","none");
            }
        }
    });
}

function processUnBlockContact(contactid, email) {
    $("#contactid").val(contactid);
    $("#email").val(email);
    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/unblock.cyt',
        success: function (data) {
            if (data.search("error") == 0) {
                $("#message_" + contactid).text("contact " + email + " not Un-Blocked");
                $("#message_" + contactid).css("color", "fireBrick");
                $("#message_" + contactid).css("display", "");
                $("#block_" + contactid).css("display", "none");
                $("#unblock_" + contactid).css("display", "");
                $("#sendMessage").css("display", "none");
               } else {
            	$("#message_" + contactid).text(data);
                $("#message_" + contactid).css("display", "");
                $("#unblock_" + contactid).css("display", "none");
                $("#block_" + contactid).css("display", "");
                $("#sendMessage").css("display", "");
                document.getElementById("blockStatus_" + contactid).value = "N";
                $("#sendMessageContact_"+contactid).css("display","");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
        }
    });
}

function processDeleteContact(contactid, email) {
    $("#contactid").val(contactid);
    var r = confirm("Are you sure you want to Delete the Contact");
    if (r != true) {
        return false;
    }
    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/delete.cyt',
        success: function (data) {
            if (data.search("error") == 0) {
                $("#message_" + contactid).text("contact " + email + " not deleted ");
                $("#message_" + contactid).css("color", "fireBrick");
                $("#message_" + contactid).css("display", "");
            } else {
                window.location = "contactsHome.cyt";
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}

function processReinvite(contactid, email) {
    $("#contactid").val(contactid);
    $("#email").val(email);
    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/sendinvites.cyt',
        success: function (data) {
            if (data.search("error") == 0) {
                $("#message_" + contactid).text(email + " Re-invite failed");
                $("#message_" + contactid).css("color", "fireBrick");
                $("#message_" + contactid).css("display", "");
                $("#reInvite_" + contactid).css("display", "");
            } else {
                $("#message_" + contactid).text(data);
                $("#message_" + contactid).css("display", "");
                $("#reInvite_" + contactid).css("display", "");
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });

}
function sendMessageContact(contactName){
    window.location.href = './composeNewMessage.cyt?contactName=' + contactName;
}
function processInvite(contactid, email) {
    $("#contactid").val(contactid);
    $("#email").val(email);
    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/sendinvites.cyt',
        success: function (data) {
            if (data.search("error") == 0) {
                $("#message_" + contactid).text(email + " Invite failed");
                $("#message_" + contactid).css("color", "fireBrick");
                $("#message_" + contactid).css("display", "");
                $("#reInvite_" + contactid).css("display", "none");
                $("#invite_" + contactid).css("display", "");
              } else {
                $("#message_" + contactid).text(data);
                $("#message_" + contactid).css("display", "");
                $("#invite_" + contactid).css("display", "none");
                $("#reInvite_" + contactid).css("display", "");
                $("#sendMessage").css("display", "none");
                document.getElementById("sendInvite_" + contactid).value = "Y";
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}
function processSendMsg() {
    var contactName = document.getElementById("contactName_" + contactId).value;
    window.location.href = './composeNewMessage.cyt?contactName=' + contactName;
}

function processDelete() {
    $("#contactid").val(contactId);
    var r = confirm("Are you sure you want to Delete the Contact");
    if (r != true) {
        return false;
    }
    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/delete.cyt',
        success: function (data) {
            setTimeout("location.reload(true);", 1);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}

function processInviteContact() {
    $("#contactid").val(contactId);
    $("#email").val(email);
    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/sendinvites.cyt',
        success: function (data) {
            $("#invite").css("display", "none");
            $("#reinvite").css("display", "");
            $("#invite_" + contactId).css("display", "none");
            $("#reInvite_" + contactId).css("display", "");
            $("#sendMessage").css("display", "none");
            document.getElementById("sendInvite_" + contactId).value = "Y";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}

function processReinviteContact() {
    $("#contactid").val(contactId);
    $("#email").val(email);
    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/sendinvites.cyt',
        success: function (data) {
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}

function processBlock() {
    $("#contactid").val(contactId);
    $("#email").val(email);

    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/block.cyt',
        success: function (data) {
            $("#block").css("display", "none");
            $("#unblock").css("display", "");
            $("#block_" + contactId).css("display", "none");
            $("#unblock_" + contactId).css("display", "");
            $("#sendMessage").css("display", "none");
            $("#sendMessageContact_"+contactId).css("display", "none");
            document.getElementById("blockStatus_" + contactId).value = "Y";
        }
    });
}

function processUnBlock() {
    $("#contactid").val(contactId);
    $("#email").val(email);
    var inviteStatus = $("#inviteStatus_" + contactId).val();
    $("#ViewContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/unblock.cyt',
        success: function (data) {
            $("#unblock").css("display", "none");
            $("#block").css("display", "");
            $("#block_" + contactId).css("display", "");
            $("#unblock_" + contactId).css("display", "none");
            $("#sendMessageContact_"+contactId).css("display", "");
            if (inviteStatus == 'Y') {
                $("#sendMessage").css("display", "");
            }
            document.getElementById("blockStatus_" + contactId).value = "N";
        }
    });
}

function processContact(contactid, nickname, firstname, lastname, email1, mobile, status, note, pcode, memsince, blocked, invitestatus, sendinvite, profileImage, uname, pollhome) {
    status = document.getElementById("memberStatus_" + contactid).value;
    blocked = document.getElementById("blockStatus_" + contactid).value;
    sendinvite = document.getElementById("sendInvite_" + contactid).value;
    invitestatus = document.getElementById("inviteStatus_" + contactid).value;
    $("#contact_details").css("display", "");
    $("#vw_nickname").text(nickname);
    $("#vw_realname").text(firstname + " " + lastname);
    $("#vw_mobile").text(mobile);
    $("#vw_email").text(email1);
    $("#vw_pollpage").text(pollhome);
    $("a.mylink").attr("href", pollhome);
    var postalcode = pcode.split(" ");
    $("#vw_pcode").text(postalcode[0]);
    $("#vw_note").text(note).css("font-style","italic");
    if (status != 'M') {
        $("#vw_memsince").text("");
        $("#profileImage").attr('src', "/ui/images/default_avatar.png");
        $("#vw_username").text("Username");
        $("#sendMessage").hide();
    } else {
        $("#vw_memsince").text(memsince);
        $("#profileImage").attr('src', profileImage);
        $("#vw_username").text(uname);
        $("#sendMessage").show();
    }
    if (invitestatus == 'Y' && sendinvite == 'Y') {
        $("#sendMessage").css("display", "");
        if (blocked == 'Y') {
            $("#sendMessage").css("display", "none");
        }
        $("#invite").css("display", "none");
        $("#reinvite").css("display", "none");
    } else if (invitestatus != 'Y' && sendinvite == 'Y') {
        $("#sendMessage").css("display", "none");
        $("#invite").css("display", "none");
        $("#reinvite").css("display", "");
      } else {
        $("#sendMessage").css("display", "none");
        $("#reinvite").css("display", "none");
        $("#invite").css("display", "");
    }
    if (blocked == 'Y') {
        $("#block").css("display", "none");
        $("#unblock").css("display", "");
    } else {
        $("#unblock").css("display", "none");
        $("#block").css("display", "");
    }
    contactId = contactid;
    email = email1;
}

function saveContact() {
    $("#editContactForm").ajaxSubmit({
        type: 'POST',
        url: 'mycontacts/edit.cyt',
        success: function (data) {
            window.location = "contactsHome.cyt";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}

function processMultipleContact(mode) {
    if (mode == 'Delete') {
        var selected = "";
        var checked = $("input[@name=" + 'numbers[]' + "]:checked");
        if (checked.length == 0) {
            alert("Please select at least one contact ");
            return false;
        } else {
            for (var i = 0; i < checked.length; i++) {
                if (checked[i].value != 'on')
                    if (selected == "")
                        selected = checked[i].value;
                    else
                        selected = selected + "," + checked[i].value;
            }
            var r = confirm("Are you sure you want to Delete the selected");
            if (r != true) {
                return false;
            }
            $("#ed_checked").val(selected);
            $("#ViewContactForm").ajaxSubmit({
                type: 'POST',
                url: 'mycontacts/deleteMultiple.cyt',
                success: function (data) {
                    window.location = "contactsHome.cyt";
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $("#communicator").css("display", "");
                    // setError("oldPwd", "You have entered an invalid password.");
                }
            });
        }
    }
    if (mode == 'Block') {
        var selected = "";

        var checked = $("input[@id=" + 'numbers[]' + "]:checked");
        if (checked.length == 0) {
            alert("Please select at least one contact ");
            return false;
        }
        else {
            for (var i = 0; i < checked.length; i++) {
                if (checked[i].value != 'on')
                    if (selected == "")
                        selected = checked[i].value;
                    else
                        selected = selected + "," + checked[i].value;
            }

            var r = confirm("Are you sure you want to Block the selected");
            if (r != true) {
                return false;
            }
            $("#ed_checked").val(selected);
            $("#ViewContactForm").ajaxSubmit({
                type: 'POST',
                url: 'mycontacts/BlockMultiple.cyt',
                success: function (data) {
                    window.location = "contactsHome.cyt";
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $("#communicator").css("display", "");
                    // setError("oldPwd", "You have entered an invalid password.");
                }
            });
        }
    }
    if (mode == 'Invite') {
        var selected = "";

        var checked = $("input[@id=" + 'numbers[]' + "]:checked");
        if (checked.length == 0) {

            alert("Please select at least one contact ");
            return false;
        } else {
            for (var i = 0; i < checked.length; i++) {
                if (checked[i].value != 'on')
                    if (selected == "")
                        selected = checked[i].value;
                    else
                        selected = selected + "," + checked[i].value;
            }
            
            var r = confirm("Are you sure you want to invite the selected");
            if (r != true) {
                return false;
            }
            $("#ed_checked").val(selected);
               $("#ViewContactForm").ajaxSubmit({
                    type: 'POST',
                    url: 'mycontacts/InviteMultiple.cyt',
                    success: function (data) {
                        alert("Your invite has been sent to your selected invites ");
                        window.location = "contactsHome.cyt";
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $("#communicator").css("display", "");
                        // setError("oldPwd", "You have entered an invalid password.");
                    }
                });
          
         
        }
    }
}
//iterates over form elements making sure input validation is good.
function validated() {
    // clear out all previous form errors.
    clearAllErrors();

    if (!isValidCurrentPassword()) {
        scroll(0, 0);    // scroll to top of page so user can see communicator message.
        return false;
    }
    if (!isValidNewPassword()) {
        scroll(0, 0);    // scroll to top of page so user can see communicator message.
        return false;
    }
    if (!isValidConfirmedPwd()) {
        scroll(0, 0);    // scroll to top of page so user can see communicator message.
        return false;
    }
    return true;
}

function isValidCurrentPassword() {
    var formGood = true;
    var pwdFilter = /^([0-9a-zA-Z])+$/;
    var min = 5;
    var max = 16;
    if ($("#oldPwd").val() == "") {
        $("#communicator").css("display", "");
        setError("oldPwd", "Enter required fields.");
        formGood = false;
        return formGood;
    }

    if (!pwdFilter.test($("#oldPwd").val())) {
        $("#communicator").css("display", "");
        setError("oldPwd", "You have entered an invalid password. use a mixture of numbers(0-9), lowercase & uppercase alphabets");
        formGood = false;
        return formGood;
    }

    if (!checkLength($("#oldPwd"), min, max)) {
        $("#communicator").css("display", "");
        setError("oldPwd", "Length must be between " + min + " and " + max + ".");
        formGood = false;
        return formGood;
    }
    return formGood;
}

function isValidNewPassword() {
    var formGood = true;
    var pwdFilter = /^([0-9a-zA-Z])+$/;
    var min = 5;
    var max = 16;
    if ($("#newPwd").val() == "") {
        $("#communicator").css("display", "");
        setError("newPwd", "Enter required fields.");
        formGood = false;
        return formGood;
    }

    if (!pwdFilter.test($("#oldPwd").val())) {
        $("#communicator").css("display", "");
        setError("newPwd", "You have entered an invalid password. use a mixture of numbers(0-9), lowercase & uppercase alphabets");
        formGood = false;
        return formGood;
    }

    if (!checkLength($("#newPwd"), min, max)) {
        $("#communicator").css("display", "");
        setError("newPwd", "Length must be between " + min + " and " + max + ".");
        formGood = false;
        return formGood;
    }

    return formGood;
}

function isValidConfirmedPwd() {
    var formGood = true;

    if ($("#confirm_newPwd").val() != $("#newPwd").val()) {
        $("#communicator").css("display", "");
        setError("confirm_newPwd", "Please confirm your new password. This must be the same as your new password");
        formGood = false;
        return formGood;
    }
    return formGood;
}

function checkLength(o, min, max) {
    if (o.val().length > max || o.val().length < min) {
        return false;
    } else {
        return true;
    }
}

//sets error status icon visibility, tooltip message, and input error style
function setError(inputField) {
    $("#errorMsg").addClass("error");
    $("#errorMsg").css("visibility", "visible");

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
    $("#communicator").removeClass("error");
    $("#communicator").css("visibility", "hidden");

    $('textarea,input:text,input:file,input:password').each(function () {
        var inputID = $(this).attr("id");
        if (inputID != "")
            clearError(inputID);
    });
}

// opposite of setError
function clearError(inputField) {
    var field = "#" + inputField;
    $(field).removeClass("inputError");

    var errorIcon = "#e_" + inputField;
    $(errorIcon).css("visibility", "hidden");
}
function selectallcontacts() {
    $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', false);
    return false;
}


//started the search functionality in list groups in form 
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
function userGroupListJsonCall(){
	 var table = document.getElementById('groupsTable');
	 var rowCount = table.rows.length;
     for(var i=0; i<rowCount; i++) {
             table.deleteRow(i);
             rowCount--;
             i--;
     }
    httpRequest.open("GET", "searchUserGroupList.cyt" , true);
 	httpRequest.onreadystatechange = getGroupsAfterSearch;
 	httpRequest.send(null);
}

function getGroupsAfterSearch(){
	groupsListDiv = document.getElementById("groupsTable");
	if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
		var resultText = httpRequest.responseText;
		var groups = json_parse(resultText);
		var groupList = groups.groups;

		for (var i = 0; i < groupList.length; i++) {				
		var spanValue  =  "<span id='group_1'>" 
						+ '<input type="radio"  class="contactList" name="selectedGroupList" onclick="setSelectedGrpRadioButton('+groupList[i].groupID +');" id="groupId'+ groupList[i].groupID +'"  value="' + groupList[i].groupID +'" /> ';
			
		groupsListDiv.innerHTML += "<tr>"
								   + '<td style="padding-left: 10px; padding-right: 10px;"> '
								   + spanValue
								   + '</span>'
								   + '</td>'
								   + '<td height="40" style="padding-right: 10px;">'
								   + '<img src="./ui/images/group_photo.png" alt="Group Photo" class="photoframe" width="36" height="35"></td>'
								   + '<td class="font_14px">'+ groupList[i].groupName  + '</td>'
								   + "</tr>" ; 
		};
	};
}
//ends the search in add contacts form
function setSelectedGrpRadioButton(selectedradio){
	$("#groupRadio").val(selectedradio);
	
	//hide new group text field
	textfdisplayed = false;
    $("#NewGrpRow").css('display', 'none');

}

function sharePollToGroup() {
    var groupId = $("input:radio[name='selectedGroupList']:checked").val();
    var pollId = document.getElementById("selectedPoll").value;
    var url = "sharePollToGroup.cyt?incyyteId=" +pollId + "&groupId=" + groupId;
    httpRequest.open("POST",  url, true);
    httpRequest.onreadystatechange = displayShareToGroupStatus;
    httpRequest.send(null);
}

function displayShareToGroupStatus() {
    if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
        var resultText = httpRequest.responseText;
        var status = resultText.trim();
        if (status == "success") {
            $.fn.colorbox.close();
        }
    }
}
