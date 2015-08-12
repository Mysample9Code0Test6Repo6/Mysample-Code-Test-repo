//JavaScript Document
$(document).ready(function () {
    $("#displaySharedEmailList").click(function () {
   	  $('#search_share_contacts').val('');
        parent.$.fn.colorbox({'href':'div#sharedEmailList', 'open':true, 'inline':true});
        sharedContactsJsonCall();
    });
});

function postcomment() {
    var comment = document.getElementById("textarea").value;
	comment = comment.replace(/�/g, "'");
    if (comment == '') {
        $("#error").css("display", "none");
        $("#error").text("Please enter your comment.");
        $("#error").css("display", "");
    } else {
        $("#error").css("display", "none");
        var quesid = document.getElementById("questionid").value;
        $("#comment").val(comment);
        $("#quesid").val(quesid);
        $("#CommentsModel").ajaxSubmit({
            type:'POST',
            url:'./addComments.cyt',
            success:function (data) {
                var code = document.getElementById("code").value;
                var returnpage = document.getElementById("returnpage").value;
                window.location = "dashdetails.cyt?code=" + code + "&frm=" + returnpage + "";
            },
            error:function (jqXHR, textStatus, errorThrown) {
                $("#communicator").css("display", "");
            }
        });
    }
}

function sendFriendRequest(username, counter) {
    $("#commentby").val(username);
    $("#friendRequest").ajaxSubmit({
        type:'POST',
        url:'./friendRequest.cyt',
        success:function (data) {
            var button = $('#dropBoxButton2' + counter);
            var box = $('#dropBox2' + counter);
            button.removeClass('active');
            box.hide();

            //display message
            if (data.search("error") == 0) {
                $("#message_" + counter).text(data.substring(5));
                $("#message_" + counter).css("color", "fireBrick");
                $("#message_" + counter).css("display", "");
            } else {
                $("#message_" + counter).text("request sent to " + username);
                $("#message_" + counter).css("display", "");
            }
        },
        error:function (jqXHR, textStatus, errorThrown) {
            $("#message_" + counter).text("There was an ERROR with processing this request.");
            $("#message_" + counter).css("color", "fireBrick");
            $("#message_" + counter).css("display", "");
        }
    });
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

function removeEmail(cEmail) {
    var mailAddrs = $("#emailArr").val();
    var re = /\s*[,;]\s*/;
    var maillAdrArr = mailAddrs.split(re);
    $("#emailArr").val('');

    for (var x = 0; x < maillAdrArr.length; x++) {
        var addr = maillAdrArr[x].trim();

        if (addr != '' && addr != ' ') {
            if (addr.indexOf(cEmail) == -1) {
                if ($('#emailArr').val() != "") {
                    var emailArrVal = $('#emailArr').val();
                    $('#emailArr').val(emailArrVal + '; ' + addr);
                }
                else {
                    $('#emailArr').val(addr);
                }
            }
        }
    }
}

function validateEmail() {
    var mailAddrs = $("#emailArr").val();
    var re = /\s*[,;]\s*/;
    var maillAdrArr = mailAddrs.split(re);
    for (var x = 0; x < maillAdrArr.length; x++) {
        var addr = maillAdrArr[x].trim();
        if (addr != '' && addr != ' ') {
            if (!isValidateEmailStr(addr)) {
                $("#email_error").text("You have entered an invalid email address.");
                $("#email_error").show();
                return false;
            }
            $("#email_error").text("");
            $("#email_error").hide();
        }
    }

    if (hasDuplicate(maillAdrArr)) {
        $("#email_error").text("You have entered duplicate email address.");
        $("#email_error").show();
        return false;
    } else {
        $("#email_error").text("");
        $("#email_error").hide();
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

function CommentIconAvailability(counter) {
    $("#commenticonsuccess" + counter).text("AVAILABLE SOON!");
    $("#commenticonsuccess" + counter).css("display", "");
}

function isValidateEmailStr(emailStr) {
    var checkRegexp = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
    if (emailStr != "") {
        if (!checkRegexp.test(emailStr)) {
            return false;
        }
    }
    return true;
}