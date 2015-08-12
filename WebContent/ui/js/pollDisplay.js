
	    $(function() {
	        $('#gallery a').lightBox();
	    });



	    $(document).ready(function () {
	        //Examples of how to assign the ColorBox event to elements
	        $(".photos").colorbox({rel:'photos'});
	        $(".group2").colorbox({rel:'group2', transition:"fade"});
	        $(".group3").colorbox({rel:'group3', transition:"none", width:"75%", height:"75%"});
	        $(".group4").colorbox({rel:'group4', slideshow:true});
	        $(".ajax").colorbox();
	        $(".youtube").colorbox({iframe:true, innerWidth:425, innerHeight:344});
	        $(".vimeo").colorbox({iframe:true, innerWidth:500, innerHeight:409});
	        $(".iframe").colorbox({iframe:true, width:"80%", height:"80%"});
	        $(".inline").colorbox({inline:true});
	        $(".inline1").colorbox({inline:true, height:"100%"});
	        $(".callbacks").colorbox({
	            onOpen:function () {
	                alert('onOpen: colorbox is about to open');
	            },
	            onLoad:function () {
	                alert('onLoad: colorbox has started to load the targeted content');
	            },
	            onComplete:function () {
	                alert('onComplete: colorbox has displayed the loaded content');
	            },
	            onCleanup:function () {
	                alert('onCleanup: colorbox has begun the close process');
	            },
	            onClosed:function () {
	                alert('onClosed: colorbox has completely closed');
	            }
	        });

	        //Example of preserving a JavaScript event for inline calls.
	        $("#click").click(function () {
	            $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");
	            return false;
	        });
	    });
	    // placeholder polyfill
	    $(document).ready(function(){
	        function add() {
	            if($(this).val() == ''){
	                $(this).val($(this).attr('placeholder')).addClass('placeholder');
	            }
	        }

	        function remove() {
	            if($(this).val() == $(this).attr('placeholder')){
	                $(this).val('').removeClass('placeholder');
	            }
	        }

	        // Create a dummy element for feature detection
	        if (!('placeholder' in $('<input>')[0])) {

	            // Select the elements that have a placeholder attribute
	            $('input[placeholder], textarea[placeholder]').blur(add).focus(remove).each(add);

	            // Remove the placeholder text before the form is submitted
	            $('form').submit(function(){
	                $(this).find('input[placeholder], textarea[placeholder]').each(remove);
	            });
	        }
	        
	       /* $('#create_login_btn').click(function() {alert('click'); submitLoginForm(); });*/
	    	$('#create_signup_btn').click(function() { submitSignUpForm(); });
	    	$('#create_fgtpwd_btn').click(function() { submitFgtPwd(); });
	        
	        $( "#signup-user" ).click(function() {	
	    		parent.$.fn.colorbox({'href':'form#create_signupform', 'open':true, 'inline':true});
	    	});	    	
	    	$( "#login-user").click(function() {
	    		parent.$.fn.colorbox({'href':'form#create_loginForm', 'open':true, 'inline':true});
	    	});
	    	$( "#fgtpwd-user" ).click(function() {
               parent.$.fn.colorbox({'href':'form#create_fgtpwdform', 'open':true, 'inline':true});
	    	});
	    	
	    	$("input:radio[name='selectedAnswer']").change(function () {
	    		$("#ans_sel_error").text("");
				$("#ans_sel_error").hide();
	    	});
	    });
	    
	    function submitSignUpForm(){
	    	$("#create_signupform").ajaxSubmit({	
	    		type: 'POST',
	    		url: '../../signup/new_account.cyt',
	    		success: function(data) {
	    			window.location = "../../"+data+".cyt";
	    		},
	    		error: function(jqXHR, textStatus, errorThrown) {
	    			alert("error:" + textStatus + " exception:" + errorThrown);
	    		}		
	    	});	
	    }

	    function submitFgtPwd(){
	    	
	    	$("#create_fgtpwdform").ajaxSubmit({	
	    		type: 'POST',
	    		url: '../../votepassword.cyt',
	    		success: function(data) {
	    			if(data == 'success'){    				
	    				$.fn.colorbox.close();
	    				parent.$.fn.colorbox({'href':'div#create_fgtpwdmsg', 'open':true, 'inline':true});	
	    			}
	    			else{				
	    				$('#fgtpwdErrmsg').show('fast');
	    			}
	    		},
	    		error: function(jqXHR, textStatus, errorThrown) {
	    			alert("error:" + textStatus + " exception:" + errorThrown);
	    		}
	    	});	
	    }
	    $(function () {
	    	$(".fancybox-popup").fancybox({
	        maxWidth	: 800,
	    		maxHeight	: 600,
	    		fitToView	: false,
	    		width		: '70%',
	    		height		: '70%',
	    		autoSize	: false,
	    		closeClick	: false,
	    		openEffect	: 'none',
	    		closeEffect	: 'none'});
	    		});
	        
	    	$(function() {
	    	    var button = $('#dropBoxButton${status.index}');
	    	    var box = $('#dropBox${status.index}');
	    	    var form = $('#dropBoxContent${status.index}');
	    	    button.removeAttr('href');
	    	    button.mouseup(function(login) {
	    	        box.toggle();
	    	        button.toggleClass('active');
	    	    });
	    	    form.mouseup(function() { 
	    	        return false;
	    	    });
	    	    $(this).mouseup(function(login) {
	    	        if(!($(login.target).parent('#dropBoxButton${status.index}').length > 0)) {
	    	            button.removeClass('active');
	    	            box.hide();
	    	        }
	    	    });
	    	});
	 
