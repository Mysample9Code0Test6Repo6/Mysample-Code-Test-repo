$(function(){

	var input = $('input#search');
	var divInput = $('div.input');
	var width = divInput.width();
	var outerWidth = divInput.parent().width() - (divInput.outerWidth() - width) - 35;
	var submit = $('#searchSubmit');
	var txt = input.val();

	input.bind('focus', function() {
		if(input.val() === txt) {
			input.val('');
		}
		$(this).animate({color: '#1b303f'}, 300); // text color
		$(this).parent().animate({
			width: outerWidth + 'px',
			backgroundColor: '#fff', // background color
			paddingRight: '43px'
		}, 300, function() {
			if(!(input.val() === '' || input.val() === txt)) {
				if(!($.browser.msie && $.browser.version < 9)) {
					submit.fadeIn(300);
				} else {
					submit.css({display: 'block'});
				}
			}
		}).addClass('focus');
	}).bind('blur', function() {
		$(this).animate({color: '#1b303f'}, 300); // text color
		$(this).parent().animate({
			width: width + 'px',
			backgroundColor: '#fff', // background color
			paddingRight: '15px'
		}, 300, function() {
			if(input.val() === '') {
				input.val(txt)
			}
		}).removeClass('focus');
		if(!($.browser.msie && $.browser.version < 9)) {
			submit.fadeOut(100);
		} else {
			submit.css({display: 'none'});
		}
	}).keyup(function() {
		if(input.val() === '') {
			if(!($.browser.msie && $.browser.version < 9)) {
				submit.fadeOut(300);
			} else {
				submit.css({display: 'none'});
			}
		} else {
			if(!($.browser.msie && $.browser.version < 9)) {
				submit.fadeIn(300);
			} else {
				submit.css({display: 'block'});
			}
		}
	});
});


function SearchContact(){

	var fname =  $("#sc_firstname").val()   ; 
	var lname=    $("#sc_lastname").val()   ;
	var pcode=   $("#sc_postalcode").val()   ;
	var city=    $("#sc_city").val()   ;
	var country   = $("#sc_country").val()  ; 
	var phone = $("#sc_mobile").val()  ;
	var gender = $("#sc_gender").val()  ;

	var keywords = $("#sc_keywords").val()  ;

	if(fname=="" && lname==""&& pcode=="" && city =="" && country=="" && phone =="" && gender==""&& keywords=="") {
		alert("Please enter atleast one search criteria") ;
		return false ;
	}	

	if (true)  //   if (validated()) 
	{
		$("#SearchContactForm").attr("action", "search.cyt");
		    $("#SearchContactForm").submit();
		/*$("#SearchContactForm").ajaxSubmit({	
			type: 'GET',
			url: './search.cyt',
			success: function(data) {
				alert("success");
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$("#communicator").css("display", "");
			}
		});*/
	}
}

