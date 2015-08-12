(function($){
    $.fn.limit  = function(options) {
        var defaults = {
        limit: 150,
        id_result: false,
        alertClass: false
        }
        var options = $.extend(defaults,  options);
        return this.each(function() {
            var characters = options.limit;
            if(options.id_result != false)
            {
                $("#"+options.id_result).append("You've used <strong>"+  0+"</strong> of 150 characters");
            }
            $(this).keyup(function(){
                if($(this).val().length > characters){
                    $(this).val($(this).val().substr(0, characters));
                }
                if(options.id_result != false)
                {
                    var remaining = $(this).val().length ;
					//document.writeln("remaining : "+$(this).val().length);
                    $("#"+options.id_result).html("You've used <strong>"+  remaining+"</strong> of 150 characters");
                    if(remaining >= 150)
                    {
                        $("#"+options.id_result).addClass(options.alertClass);
                    }
                    else
                    {
                        $("#"+options.id_result).removeClass(options.alertClass);
                    }
                }
            });
        });
    };
})(jQuery);