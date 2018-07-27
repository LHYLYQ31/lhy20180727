var Util = {
    getTarget : function() {// 获取触发事件的元素
		var evt = Util.getEvent();
		if (!evt)
			evt = window.event;
		var obj = evt.srcElement ? evt.srcElement : evt.target;
		return obj;
	},
	getEvent : function() {// 获取event对象
		if (document.all) {
			return window.event;
		}
		func = Util.getEvent.caller;
		while (func != null) {
			var arg0 = func.arguments[0];
			if (arg0) {
				if (arg0.constructor == Event
						|| arg0.constructor == MouseEvent
						|| (typeof (arg0) == "object" && arg0.preventDefalut && arg0.stopPropagation)) {
					return arg0;
				}
			}
			func = func.caller;
		}
		return null;
	},
	getUrlParamByName : function(paramName) {// 根据参数名获取url中？的参数值
		var uri = decodeURI(window.location.search, "UTF-8");
		var re = new RegExp("" + paramName + "=([^&?]*)", "ig");
		return ((uri.match(re)) ? (uri.match(re)[0]
				.substr(paramName.length + 1)) : null);
	}
}


/**
 * ajax请求方法
 * @param (object) setting ajax请求设置,若不需要设置 参数为{}
 * @param (function) callback ajax请求成功后，执行callback回调函数
 * @param (object) loadingObj 预留参数,设置在请求发起之前显示“加载中”等提示
 * @returns (object) 若ajax请求设置为同步，返回请求的响应数据 
 */
function getDataByAjax(setting,callback,loadingObj){
    //默认参数
	var options = {
		async : true,
		cache : false,
		type : "post",
		url : '',
		data : {},
		dataType : 'json'
	};

  // 自定义参数后合并对象
  jQuery.extend(options, setting);

  var returnVal = {};
  $.when(
    $.ajax(options)
  ).then(function(data){//请求响应成功
    returnVal = data;
    if(callback){
    	if(data!=null&&data.status=="IsAjax"){
    		location.href="../login/login.html";
    	}
      callback(data);
    }
  },function(data){//请求异常
    returnVal = data;
  });

  setTimeout(function () {
  },null);//若是请求没有响应 则关闭加载框

  if(!options.async){
    return returnVal;
  }
}

/**
 * 获取项目名 形如：/trade
 */
function getBasePath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}

/**
 * 获取路径加项目名 形如：http://localhost:8080/trade
 */
function getPath() {
	return window.location.protocol + "//" + window.location.host
			+ getBasePath();
}
/*********************
 * 咆哮框
 * 弹出提示框
 * 
 *********************/  
function showToast(text,option){
	
	var defaultOption = {
		ele: $("body"), // which element to append to
        type: 'success', // (null, 'info', 'error', 'success')
        offset: {from: 'top', amount: 150}, // 'top', or 'bottom'
        align: 'right', // ('left', 'right', or 'center')
        width: 300, // (integer, or 'auto')
        delay: 2000,
        allow_dismiss: true,
        stackup_spacing: 20 // spacing between consecutively stacked growls.
	}
	
	var _option = $.extend(true, defaultOption, option);
	
	$.bootstrapGrowl(text,_option); 
}