<script type="text/javascript">
$(window).load(function () {
        $("#reportChanges").click(function () {
            var contextVar = document.getElementById("contextPathVar").value;
            var questionId  = $("#reportQuestionId").val();
            var reportType  = $("#reportType").val();
            var comments  = $("#comments").val();
            var params = "?questionId=" + questionId + "&reportType=" + reportType + "&comments=" + comments;
            $("#ReportPollForm").ajaxSubmit({
                type: 'POST',
                url:contextVar + "/reportThisPoll.cyt" + params,
                success:function (data) {
                    if (data == "inserted") {
                        $("#redirectRequired").val("no");
                        $("#dynamicText").text("You have successfully Reported This Poll.");
                    } else if (data == "limitReached") {
                        $("#redirectRequired").val("yes");
                        $("#dynamicText").text("You have successfully Reported This Poll.");
                    } else if (data == "alreadyReported") {
                        $("#redirectRequired").val("no");
                        $("#dynamicText").text("You have already Reported This Poll.");
                    }
                    parent.$.fn.colorbox({'href':'div.popUpForSuccessOfReporting_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false, 'closeBtn':false});
                }
            });
        });
        $("#cancelReport").click(function () {
            $.fn.colorbox.close();
        });
        $("#thankYouButton").click(function () {
            $.fn.colorbox.close();
            var redirectRequired = $("#redirectRequired").val();
            if (redirectRequired == "yes") {
                var redirectPath = $("#reportAbuseRedirectPath").val();
                window.location = redirectPath;
            }
        });
    });

function submitReportAbuse(questionId, redirectPath) {
    $("#reportQuestionId").val(questionId);
    $("#reportAbuseRedirectPath").val(redirectPath);
    parent.$.fn.colorbox({'href':'div.report_abuse_popup', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
}
</script>
<div style="display:none;">
    <div class="report_abuse_popup">
        <form id="ReportPollForm" name="ReportPollForm" method="post">
            <input type="hidden"  id="redirectRequired"/>
            <input type="hidden"  id="reportAbuseRedirectPath"/>
            <input type="hidden"  id="reportQuestionId"/>
            <input type="hidden"  id="userId"/>
            <h4 class="heading1">Report Abuse</h4>
            <div class="rowset">
                <label>Type </label>
                <div class="right">
                    <select id="reportType">
                        <option value="Obscenity/Vulgarity" >Obscenity/Vulgarity</option>
                        <option value="Instigating Hate" >Instigating Hate</option>
                        <option value="Spam" >Spam</option>
                        <option value="Plagiarism" >Plagiarism</option>
                        <option value="Others" >Others</option>
                    </select>
                </div>
            </div>
            <div class="rowset" style="margin-top: 10px;">
                <label>Comments<br>
                    (optional)
                </label>
                <div class="right">
                    <textarea class="questionbox"  id="comments"></textarea>
                </div>
            </div>
            <input type="hidden" id="contextPathVar" value="${pageContext.request.contextPath}">
            <div class="rowset">
                <a class="poll_button1" style="width:105px;float: right;" id="cancelReport"><span class="poll_button_red">CANCEL</span></a>
                <a class="poll_button1" style="width:105px;float: right;" id="reportChanges"><span class="poll_button_green">SEND</span></a>
            </div>
        </form>
    </div>
</div>
<div class="popUpForSuccessOfReporting" style="display: none;">
    <div class="popUpForSuccessOfReporting_bg">
        <div class="popUpForSuccessOfReporting_txt">
            <span id="dynamicText"></span>
        </div>
        <div style="height:100px; margin-top: 25px;">
            <a class="poll_button1" style="width:180px;" id="thankYouButton"><span class="poll_button_red ">Thank You</span></a></div>

</div>
</div>