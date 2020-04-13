
$(document).ready(function() {
	flushVerifyImg();
});

function flushVerifyImg() {
	var timenow = new Date().getTime(); 
    $("#VerifyImg").attr("src", '/servlet/Validate?d=' + timenow);
    return false;
}


