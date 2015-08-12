$(document).ready(function () {
    setActive();
});

function setActive() {
    aObj = document.getElementById('left_mainmenu').getElementsByTagName('a');
    for (var i = 0; i < aObj.length; i++) {
        if (document.location.href.indexOf(aObj[i].href) >= 0) {
            aObj[i].className = 'activeBar';
        }
    }
}
window.onload = setActive;