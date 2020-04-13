
$(document).ready(function() {
	flushVerifyImg();
});

function flushVerifyImg() {
	var timenow = new Date().getTime(); 
    $("#VerifyImg").attr("src", '/ValidateAlphanumeric?d=' + timenow);
    return false;
}


