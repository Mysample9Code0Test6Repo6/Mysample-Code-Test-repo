//JavaScript Document
$(document).ready(function() {  
	$('#modal_datepicker_group_btn').click(function() { closeDatePickerGroup(); });
	$('#modal_datepicker_region_btn').click(function() { closeDatePickerRegion(); });
	
	$("#searchSubmit").click(function () {
		searchSentIncyyte();
    });
});


function searchSentIncyyte() {
    var search = $("#search").val();
    $("#searchForm").ajaxSubmit({    	
        type:'POST',
        url:'./dashsent.cyt?search=' + search
    });
}


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

function change_incyyte_type(incyyteId, value) {
	window.location = "updtincyyte.cyt?code="+incyyteId+"&type="+value;
}


function delete_incyyte(incyyteId, pageName) {
	window.location = "deleteincyyte.cyt?code="+incyyteId+"&pageName="+pageName;
}


function set_datepickerGroup(incyyteId){
	indx=incyyteId;
	var closingDate = $('#closureDateVal_group'+indx).text();
    var Cdate = new Date( closingDate.replace( /(\d{4})-(\d{2})-(\d{2})/, "$1/$2/$3") );
  	var createdDate = $('#createdDateVal_group'+indx).text();
    var max_Date = new Date( createdDate.replace( /(\d{4})-(\d{2})-(\d{2})/, "$1/$2/$3") );
    var maximumDate = new Date(max_Date);
    maximumDate.setMonth(maximumDate.getMonth() + 3);
    $("#datepickerGroup").datepicker({
        changeMonth: true,
        changeYear: true, yearRange: '1950:+10'
    }).datepicker("setDate", new Date (Cdate)).datepicker('option', {maxDate: maximumDate});

	if(closingDate != null){
		var dArry = closingDate.split(' ');	
		if(dArry.length > 1)
			$('#selectClosureTimeGroup').val(dArry[1]);
	}
	
}

function set_datepickerRegion(incyyteId){
	indx=incyyteId;
	var closingDate = $('#closureDateVal_region'+indx).text();
    var Cdate = new Date( closingDate.replace( /(\d{4})-(\d{2})-(\d{2})/, "$1/$2/$3") );
  	var createdDate = $('#createdDateVal_region'+indx).text();
    var max_Date = new Date( createdDate.replace( /(\d{4})-(\d{2})-(\d{2})/, "$1/$2/$3") );
    var maximumDate = new Date(max_Date);
    maximumDate.setMonth(maximumDate.getMonth() + 3);
    $("#datepickerRegion").datepicker({
        changeMonth: true,
        changeYear: true, yearRange: '1950:+10'
    }).datepicker("setDate", new Date (Cdate)).datepicker('option', {maxDate: maximumDate});

	if(closingDate != null){
		var dArry = closingDate.split(' ');	
		if(dArry.length > 1)
			$('#selectClosureTimeRegion').val(dArry[1]);
	}
	
}

function display_datepicker(incyyteId){
	set_datepicker(incyyteId);
	parent.$.fn.colorbox({'href':'div#modal_datepicker', 'open':true, 'inline':true});
}


function closeDatePickerGroup(){
	$('#closureDateVal_group'+indx).text($('#alternateGroup').val());
	$('#selectClosureTimeVal_group'+indx).text($('#selectClosureTimeGroup').val());
	$('#closureDateValX_group'+indx).text($('#alternateGroup').val());
	
	$('#inc_id').val(indx);
	
	var clsDate = $('#alternateGroup').val();
	var clsTime = $('#selectClosureTimeGroup').val();
	$('#closureDate').val(clsDate +" "+ clsTime);
	parent.$.fn.colorbox.close();
	
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'updatedatetime.cyt?page=sent',
		success:function (data) {
			
		},
		error:function (jqXHR, textStatus, errorThrown) {
			//alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});

	
}


function closeDatePickerRegion(){
	$('#closureDateVal_region'+indx).text($('#alternateRegion').val());
	$('#selectClosureTimeVal_region'+indx).text($('#selectClosureTimeRegion').val());
	$('#closureDateValX_region'+indx).text($('#alternateRegion').val());
	
	$('#inc_id').val(indx);
	
	var clsDate = $('#alternateRegion').val();
	var clsTime = $('#selectClosureTimeRegion').val();
	$('#closureDate').val(clsDate +" "+ clsTime);
	parent.$.fn.colorbox.close();
	
	$("#inCyyteForm").ajaxSubmit({
		type:'POST',
		url:'updatedatetime.cyt?page=sent',
		success:function (data) {
			
		},
		error:function (jqXHR, textStatus, errorThrown) {
			//alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});

	
}