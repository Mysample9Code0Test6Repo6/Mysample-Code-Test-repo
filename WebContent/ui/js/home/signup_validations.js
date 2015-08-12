/* <![CDATA[ */

jQuery(function () {
    jQuery("#su_email").validate({
        expression:"if (VAL.match(/^[^\\W][a-zA-Z0-9\\_\\-\\.]+([a-zA-Z0-9\\_\\-\\.]+)*\\@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*\\.[a-zA-Z]{2,4}$/)) return true; else return false;",
        message:"Should be a valid Email - from field"
    });

});

/* ]]> */

function isSpclCharUserName(username) {
    var iChars = "!@#$%^&*() +=_`~-[]\\\';,./{}|\":<>?";
    for (var i = 0; i < username.length; i++) {
        if (iChars.indexOf(username.charAt(i)) != '-1') {
            return false;
        }
    }
    return true;
}

function isSpclCharEmail(su_email) {
    var iChars = "/^&?+*[]{}\|`~<>%#$!='";
    for (var i = 0; i < su_email.length; i++) {
        if (iChars.indexOf(su_email.charAt(i)) != '-1') {
            return false;
        }
    }
    return true;
}
