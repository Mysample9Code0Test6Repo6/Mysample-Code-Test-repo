var groupid;
var grpName;
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
        searchGroup();
    });
    
    $("#searchGrpContactsSubmit").click(function () {
        searchGroupContacts();
    });
   
    $("#add").click(function () {
        $("#addfrm").css("display", "");
        $("#searchfrm").css("display", "none");
    });
    $("#close").click(function () {
        $('#group_details').hide();
    });

    $("input[name=showAllContacts]:radio").change(function () {
    	if ($("#radio-1").attr("checked")) {
    		var grpid =  $("#groupId").val();
    		$("#view_incyyte").css("display", "");
    		$('#getGroupContacts').hide();
            $("#showAllContactsTbl").css("display", "");
            $("#showMembers").css("display", "none");
            $("#showAllContactsCSV").css("display", "");
            $("#showMembersCSV").css("display", "none");
		}
        if ($("#radio-2").attr("checked")) {
        	$("#getGroupContacts").css("display", "");
        	$('#view_incyyte').hide();
        	$("#showMembers").css("display", "");
            $("#showAllContactsTbl").css("display", "none");
            $("#showAllContactsCSV").css("display", "none");
            $("#showMembersCSV").css("display", "");
    	}
    });
}
function downloadCSV(tableId){
	$('#'+tableId).table2CSV();
}

function setDefaultInput() {
    $("#communicator").css("display", "none");

    var inputs = document.getElementsByTagName('input'), d, i = 0;
    while (d = inputs[i++]) {
        if (d.id.indexOf('contactId') != -1 && $("#polled").val() == 'true') {
            if ($("#" + d.id).checked) {
                $("#" + d.id).attr("disabled", true);
            } else {
                $("#" + d.id).removeAttr("disabled");
            }
        }
    }
}

function searchGroup() {
    search = $("#search").val();
    $("#searchForm").ajaxSubmit({
        type:'POST',
        url:'./grouphome.cyt?search=' + search
    });
}

function searchGroupContacts() {
    search = $("#search").val();
    grpId  =$("").val(grpid);
    $("#searchForm").ajaxSubmit({
        type:'POST',
        url:'./groupModify.cyt?search=' + search +'&grpid='+ grpId
    });
}

function submitGroupCreatePagination(pageValue) {
    var groupPaginate = document.getElementById('group');
    groupPaginate.action = './groupCreate.cyt?param=' + pageValue;
    groupPaginate.submit();
}

function addContact() {
    if (true) {//   if (validated())
        $("#addContactForm").ajaxSubmit({
            type:'POST',
            url:'mycontacts/submit.cyt',
            success:function (data) {
                $("#invite_poeple").css("display", "none");
                $("#invite_poeple_sucess").css("display", "");
            },
            error:function (jqXHR, textStatus, errorThrown) {
                $("#communicator").css("display", "");
                // setError("oldPwd", "You have entered an invalid password.");
            }
        });
    }
}

function processBlockContact(contactid) {
    $("#contactid").val(contactid);
    $("#group").ajaxSubmit({
        type:'POST',
        url:'mycontacts/block.cyt',
        success:function (data) {
            window.location = "contactsHome.cyt";
        },
        error:function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}

function processUnBlockContact(contactid) {
    $("#contactid").val(contactid);
    $("#group").ajaxSubmit({
        type:'POST',
        url:'mycontacts/unblock.cyt',
        success:function (data) {
            window.location = "contactsHome.cyt";
        },
        error:function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}

function processDeleteContact(contactid) {
    $("#contactid").val(contactid);
    $("#group").ajaxSubmit({
        type:'POST',
        url:'mycontacts/delete.cyt',
        success:function (data) {
            window.location = "contactsHome.cyt";
        },
        error:function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}

function processReinvite(contactid) {
    $("#contactid").val(contactid);
    $("#group").ajaxSubmit({
        type:'POST',
        url:'mycontacts/sendinvites.cyt',
        success:function (data) {
            window.location = "contactsHome.cyt";
        },
        error:function (jqXHR, textStatus, errorThrown) {
            $("#communicator").css("display", "");
            // setError("oldPwd", "You have entered an invalid password.");
        }
    });
}

function processGroup(grpid, grpname, grpdesc, grpsize, grploc) {
    $("#groupId").val(grpid);
    $("#groupName").val(grpname);
    $('#group_details').show();

    document.getElementById('vw_grpname').innerHTML = grpname;
    document.getElementById('vw_grpdesc').innerHTML = grpdesc;
    document.getElementById('vw_grpsize').innerHTML = grpsize;
    document.getElementById('vw_grploc').innerHTML = grploc;
    groupid = grpid;
    grpName = grpdesc;
}

function sendGroupIncyyte() {
	var groupId =  $("#groupId").val();
	sendGroupPollIncyyte(groupId, null);   
	}

function sendGroupPollIncyyte(groupId, groupName) {
   window.location = './create_grp_incyyte.cyt?groupName=' + groupName + '&groupId=' + groupId + '&source=GROUPS';
}

function EditGrp(grpid) {
    var selected = '';
    if (grpid == 'test')
        selected = $("#groupId").val();
    else
        selected = grpid;
    $("#ed_checked").val(selected);
    window.location = './groupModify.cyt?grpid=' + selected;

}

function processMultipleContact(mode) {
    if (mode == 'Delete') {
        var selected = "";
        var checked = $("input[@name=" + 'numbers[]' + "]:checked");
        if (checked.length == 0) {
            alert("Please select at least one group ");
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
            $("#group").ajaxSubmit({
                type:'POST',
                url:'grouphome/deleteMultiple.cyt',
                success:function (data) {
                    window.location = "grouphome.cyt";
                },
                error:function (jqXHR, textStatus, errorThrown) {
                    $("#communicator").css("display", "");
                    // setError("oldPwd", "You have entered an invalid password.");
                }
            });
        }
    }

    if (mode == 'Save') {
        var groupName =$("#groupName").val();
        var des= $("#description").val();
        //var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";       
        var iChars = "/^+*[]{}\|`~<>%#";

        for (var i = 0; i < des.length; i++) {
            if (iChars.indexOf(des.charAt(i)) != -1) {
                $("#groupError").attr("class", "invalid");
                $("#groupError").css("display", "");
                $("#groupError").text("Group description will not allow special characters");
                return false;
            }
        }

        if (groupName=='' || groupName.length==0) {
            $("#groupError").attr("class", "invalid");
            $("#groupError").css("display", "");
            $("#groupError").text("Please enter Group Name");
            return false;
        }
        if (des=='' ||  des.length==0) {
            $("#groupError").attr("class", "invalid");
            $("#groupError").css("display", "");
            $("#groupError").text("Please enter Group Description");
            return false;
        }
        var selected = "";
        var checked = $("input[@name=" + 'numbers[]' + "]:checked");
        if (checked.length == 0) {
            alert("Please select at least one contact ");
            return false;
        } else {
            for (var i = 0; i < checked.length; i++) {
                if (checked[i].value != 'on') {
                    if (selected == "") {                    	
                        selected = checked[i].value;
                        }else {
                        selected = selected + "," + checked[i].value;
                        }
                }
            }
            var r = confirm("Are you sure you want to Save the selected");
            if (r != true) {
                return false;
            }
            $("#ed_checked").val(selected);
            $("#group").ajaxSubmit({
                type:'POST',
                url:'editgroup.cyt',
                success:function (data) {
                    window.location = "grouphome.cyt";
                },
                error:function (jqXHR, textStatus, errorThrown) {
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
        } else {
            for (var i = 0; i < checked.length; i++) {
                if (checked[i].value != 'on')
                    if (selected == "")
                        selected = checked[i].value;
                    else
                        selected = selected + "," + checked[i].value;
            }
            alert("Are you sure you want to Block the selected");
            $("#ed_checked").val(selected);
            $("#group").ajaxSubmit({
                type:'POST',
                url:'mycontacts/BlockMultiple.cyt',
                success:function (data) {
                    window.location = "contactsHome.cyt";
                },
                error:function (jqXHR, textStatus, errorThrown) {
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
            alert("Are you sure you want to invite the selected");
            $("#ed_checked").val(selected);
            $("#group").ajaxSubmit({
                type:'POST',
                url:'mycontacts/InviteMultiple.cyt',
                success:function (data) {
                    window.location = "contactsHome.cyt";
                },
                error:function (jqXHR, textStatus, errorThrown) {
                    $("#communicator").css("display", "");
                    // setError("oldPwd", "You have entered an invalid password.");
                }
            });
        }
    }
    if (mode == 'ADD') {
        var groupName =$("#groupName").val();
        var des= $("#description").val();
        //var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
        var iChars = "/^+*[]{}\|`~<>%#";

        for (var i = 0; i < des.length; i++) {
            if (iChars.indexOf(des.charAt(i)) != -1) {
                $("#groupError").attr("class", "invalid");
                $("#groupError").css("display", "");
                $("#groupError").text("Group description will not allow special characters");
                return false;
            }
        }

        if (groupName=='' || groupName.length == 0) {
            $("#groupError").attr("class", "invalid");
            $("#groupError").css("display", "");
            $("#groupError").text("Please enter Group Name");
            return false;
        }
        if (des=='' ||  des.length == 0) {
            $("#groupError").attr("class", "invalid");
            $("#groupError").css("display", "");
            $("#groupError").text("Please enter Group Description");
            return false;
        }
        var selected = "";
        var checked = $("input[@name=" + 'numbers[]' + "]:checked");
        if (checked.length == 0) {
            $("#groupError").attr("class", "invalid");
            $("#groupError").css("display", "");
            $("#groupError").text("Please select at least one contact to add");
            return false;
        } else {
            for (var i = 0; i < checked.length; i++) {
                if (checked[i].value != 'on')
                    if (selected == "")
                        selected = checked[i].value;
                    else
                        selected = selected + "," + checked[i].value;
            }
            var r = confirm("Are you sure you want to Create the Group with selected Contact");
            if (r != true) {
                return false;
            }
            $("#ed_checked").val(selected);
            $("#group").ajaxSubmit({
                type:'POST',
                url:'grouphome/addgroup.cyt',
                success:function (data) {
                    if (data.search("Error") == 0) {
                        $("#groupError").attr("class", "invalid");
                        $("#groupError").css("display", "");
                        $("#groupError").text(data);
                    } else {
                        window.location = "grouphome.cyt";
                    }
                },
                error:function (jqXHR, textStatus, errorThrown) {
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
        //alert("isRequiredFields");
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

function navigate(submitUrl) {
    var obj = document.getElementById('group');
    obj.action = submitUrl;
    obj.submit();
}

function submitContact(contactId, checked) {
	var checkboxes = $("input[type='checkbox']");
    if(checkboxes.is(":checked")) {
        $('#ADDTOGROUP').unbind('click');
        $('#ADDTOGROUP').attr('class','button_green1_addtogroup');
    }
    else{
    	$('#ADDTOGROUP').bind('click', function(e){ e.preventDefault();});
        $('#ADDTOGROUP').attr('class','button_disabled_addtogroup');
    }
    $.ajax({
        type:"GET",
        url:"submitnewcontact.cyt?contactId=" + contactId + "&checked=" + checked,
        dataType:"text"
    });
}

function submitEditContact(contactId, checked, groupId) {
    $.ajax({
        type:"GET",
        url:"submiteditcontact.cyt?contactId=" + contactId + "&checked=" + checked + "&groupId=" + groupId,
        dataType:"text"
    });
}

function checked (xyz){
    // Select all
    if(xyz=='#selectall') {
        $("#group_1"  + " INPUT[type='checkbox']").attr('checked', true);
        $('#ADDTOGROUP').unbind('click');
        $('#ADDTOGROUP').attr('class','button_green1_addtogroup');
    }

    // Select none
    if(xyz=='#select_none') {
        $("#group_1" + " INPUT[type='checkbox']").attr('checked', false);
        $('#ADDTOGROUP').bind('click', function(e){ e.preventDefault();});
        $('#ADDTOGROUP').attr('class','button_disabled_addtogroup');
    }
}
