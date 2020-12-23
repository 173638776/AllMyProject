var backBtn = null;

$(function(){
	backBtn = $("#back");
	backBtn.on("click",function(){
		//alert("view : "+referer);
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});

function xiugai() {
	var uid = location.search.split("=")[1].trim()
	location.href = path+"/jsp/headportrait.jsp?uid="+uid
}

function goImg() {
    var uid = location.search.split("=")[1].trim()
    location.href = path+"/jsp/headportrait.jsp?uid="+uid
}