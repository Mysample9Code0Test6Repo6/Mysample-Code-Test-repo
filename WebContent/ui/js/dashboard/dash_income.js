//JavaScript Document
$(document).ready(function() {  
	$("#searchSubmit").click(function () {
		searchIncomeIncyyte();
    });
});

function searchIncomeIncyyte() {
    var search = $("#search").val();
    $("#searchForm").ajaxSubmit({
        type:'POST',
        url:'./dashincomming.cyt?search=' + search
    });
}

function deleteIncomming(code){
	
	var deleteFlag = confirm("Are you sure you want to delete this Incyyte?");
	if(deleteFlag){
		$("#inCyyteForm").ajaxSubmit({
			type:'GET',
			url:'deleteincommingincyyte.cyt?code='+code,
			success:function (data) {
				//alert("deleted - " + code);
				window.location = "dash.cyt";
				//window.location.reload(true);
			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});
	}
}