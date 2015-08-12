 /* <![CDATA[ */
            jQuery(function(){
                jQuery("#pin").validate({
                    expression: "if (!isNaN(VAL) && VAL) return true; else return false;",
                    message: "Should be a number"
                });
                jQuery("#email").validate({
                    expression: "if (VAL.match(/^[^\\W][a-zA-Z0-9\\_\\-\\.]+([a-zA-Z0-9\\_\\-\\.]+)*\\@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*\\.[a-zA-Z]{2,4}$/)) return true; else return false;",
                    message: "Should be a valid Email id"
                });
                jQuery("#ValidNumber").validate({
                    expression: "if (VAL > 100) return true; else return false;",
                    message: "Should be greater than 100"
                });
                jQuery("#mobile").validate({
                    expression: "if (VAL.match(/^[9][0-9]{9}$/)) return true; else return false;",
                    message: "Should be a valid Mobile Number"
                });
            });
			
			
            /* ]]> */