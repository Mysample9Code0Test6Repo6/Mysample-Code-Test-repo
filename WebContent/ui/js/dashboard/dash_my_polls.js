//JavaScript Document
$(document).ready(function() {  
	$('#modal_datepicker_btn').click(function() { closeDatePicker(); });
});

var indx;
function preview_poll(incyyteId) {		
	parent.$.fn.colorbox({href:'previewpoll.cyt?code='+incyyteId, open:true});
}

function publish_poll(incyyteId) {
	window.location = "publishPoll.cyt?code="+incyyteId;
}

function unpublish_poll(incyyteId) {
	window.location = "unpublishPoll.cyt?code="+incyyteId;
}

function delete_incyyte(incyyteId, pageName) {
	window.location = "deleteincyyte.cyt?code="+incyyteId+"&pageName="+pageName;
}

function set_datepicker(incyyteId){
	indx=incyyteId;
	var closingDate = $('#closingDateVal_'+indx).text();
	var createdDate = $('#createdDateVal_'+indx).text();
	var maximumDate = new Date(createdDate);
    maximumDate.setMonth(maximumDate.getMonth() + 3);
    
	$("#datepicker").datepicker({
        changeMonth: true,
        changeYear: true, yearRange: '1950:+10'
    }).datepicker("setDate", new Date (closingDate)).datepicker('option', {maxDate: maximumDate});
	
	if(closingDate != null){
		var dArry = closingDate.split(' ');	
		if(dArry.length > 1)
		$('#selecttime').val(dArry[1]);
	}
	
}

function display_datepicker(incyyteId){
	set_datepicker(incyyteId);
	parent.$.fn.colorbox({'href':'div#modal_datepicker', 'open':true, 'inline':true});
}

function closeDatePicker(){
	$('#closingDateVal_'+indx).text($('#alternate').val());
	$('#selecttimeVal_'+indx).text($('#selecttime').val());
	$('#closingDateValX_'+indx).text($('#alternate').val());
	
	$('#inc_id').val(indx);
	
	var clsDate = $('#alternate').val();
	var clsTime = $('#selecttime').val();
	$('#closureDate').val(clsDate +" "+ clsTime);
	
	parent.$.fn.colorbox.close();
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'updatedatetime.cyt?page=mypolls',
		success:function (data) {
			
		},
		error:function (jqXHR, textStatus, errorThrown) {
			//alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});

	
}