function copyItems(rightBox,leftBox) {
	var rightBox = document.getElementById(rightBox);
    var leftBox = document.getElementById(leftBox);
    
    for (var i = 0; i < rightBox.options.length; i++) {
    	if (rightBox.options[i].selected) {
    		var newOption = document.createElement("option");
            newOption.text = rightBox.options[i].text;
            newOption.value = rightBox.options[i].value;
            
            rightBox.remove(rightBox.options[i]);
            	
            leftBox.options[leftBox.options.length] = newOption;
            rightBox.options[i].selected = false;
    	}
    }
    return false;
}

function selValues(url) {
	 var leftBox = document.getElementById('leftBox');
	 var leftBoxad = document.getElementById('adminleftBox');
	 var grpname= document.getElementById('gName').value;
	 var grpnamesexisting= 	 document.getElementById('exists').value;
	 
	// alert(grpname) ;
	// alert(grpnamesexisting) ;
	 for (var i = 0; i < leftBox.options.length; i++) {
		 leftBox.options[i].selected = true;
	 }
	 
	 for (var i = 0; i < leftBoxad.options.length; i++) {
		 leftBoxad.options[i].selected = true;
	 }
	 
	// alert('Group Name Already Exists.Please choose a new name') ;
	 if(grpname.indexOf(grpnamesexisting) !== -1)
	 {
		 alert('Group Name Already Exists.Please choose a new name') ;
		 return ;
	 }
	 
	
	 document.forms[0].action = url;
	 document.forms[0].submit();
	 return false;
}

function displayGroupInfo(url,groupId) {
	
	//alert(url);
	//alert(groupId);
	url = "/inCyyte/"+url+"?groupId="+groupId;
	window.location=url;
}

function addComment(url) { 
	 document.forms[1].action = url;
	 document.forms[1].submit();  
	 return false;
}

function editGroup(url,groupId) {
	
	//alert(url);
	//alert(groupId);
	url = "/inCyyte/"+url+"?groupId="+groupId;
	//alert(url);
	window.location=url;
//	document.forms[0].action = url;
//	document.forms[0].groupId.value = groupId;
//	document.forms[0].submit();
	
	//return false;
}

function removeGroup(url,groupId) {
	
	url = "/inCyyte/"+url+"?groupId="+groupId;
	//alert(url);
	window.location=url;
}

function deleteGroups(url) {
	 document.forms[0].action = url;
	 document.forms[0].submit();
	 return false;
}