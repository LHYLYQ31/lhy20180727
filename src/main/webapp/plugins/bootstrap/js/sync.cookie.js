//==========================
//  kjx 20160623
//  跨越问题cookie获取信息
//==========================

var syncCookieFlag = false;//是否同步完成

$(document).ready(function () { 
	getCookieInfo(); 
}); 
function getCookieInfo(){
	$.getScript(Config.getUserCookieURL, function(){ 
	            syncCookie(usercookie.status,usercookie.Cookie);        
	}); 
}
	


/* 通过后台同步cookie
*key cookie的name
*val cookie的value
成功返回true  失败返回false
*/
function syncCookie(status,val){
	$.ajax({
			type: 'post',
			url: basePath + '/cookie/sync',
			async: false,
			data:{
				status:status,
				cookie:val
			},
			success: function() {
				syncCookieFlag = true;
			},
			error:function(){
				console.log("cookie sync error!");    
			}
	});
}