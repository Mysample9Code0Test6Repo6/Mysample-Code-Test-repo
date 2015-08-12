//JavaScript Document
$(document).ready(function() {  
	$("#searchSubmit").click(function () {
		searchRegionIncyyte();
    });
});

function searchRegionIncyyte() {
    var search = $("#search").val();
    $("#searchForm").ajaxSubmit({
        type:'POST',
        url:'./dashregion.cyt?search=' + search
    });
}


function deleteRegion(code){
	
	var deleteFlag = confirm("Are you sure you want to delete this Incyyte?");
	if(deleteFlag){
		$("#inCyyteForm").ajaxSubmit({
			type:'GET',
			url:'deleteRegionIncyyte.cyt?code='+code,
			success:function (data) {
				//alert("deleted - " + code);
				window.location = "dashregion.cyt";
			},
			error:function (jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});
	}
}