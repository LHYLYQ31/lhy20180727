var ticket = "";
var userId = "";
$(function() {
    //默认加载应用列表页；
    loadHtml("-1");
    loadUserInfo();
});
//加载用户信息
function loadUserInfo() {
	var params={
				url:basePath + '/user/getModel',
				};
	getDataByAjax(params,function(data){
		if (data != null) {
            //将用户信息显示在页面
            var name ='<i class="fa fa-user fa-fw"></i>'+data.userName;
            $("#userId").val(data.userId);
            $("#loginName").html(name);
        }
	},null);
}
//退出登录
function loginOut() {
	var params={
			url:basePath + '/login/loginOut',
			};
	getDataByAjax(params,function(){
		//做一个退出提示
	},null);
}
$(document).on('click', '#appManage',function() {
    $('.menu').removeClass('active');
    //加载一个其他页面作为进入的首页
});
//加载页面
function loadHtml(type,obj) {
	//切换菜单样式
	$('.menu').removeClass('active');
	$(obj).parent().parent().parent().parent().parent().addClass('active');
    if ("1" == type) { //加载用户管理的列表页
    	$('#rightTitle').text('用户管理');
        $("#context").empty().load("../user/userList.html", {
            number: Math.random()
        },
        function() {});
    } else if ("2" == type) { //加载用户管理的添加页
    	$('#rightTitle').text('用户管理');
        $("#context").empty().load("../user/userAdd.html", {
            number: Math.random()
        },
        function() {});
    } else if ("3" == type) { //加载会员管理的列表页
    	$('#rightTitle').text('会员管理');
        $("#context").empty().load("../member/memberList.html", {
            number: Math.random()
        },
        function() {});
    } else if ("4" == type) { //加载会员管理的添加页
    	$('#rightTitle').text('会员管理');
        $("#context").empty().load("../member/memberAdd.html", {
            number: Math.random()
        },
        function() {});
    } else if ("5" == type) { //订单管理的列表
    	$('#rightTitle').text('订单管理');
        $("#context").empty().load("../order/orderList.html", {
            number: Math.random()
        },
        function() {});
    } else if ("6" == type) { //订单管理的列表
    	$('#rightTitle').text('流水管理');
        $("#context").empty().load("../income/incomeList.html", {
            number: Math.random()
        },
        function() {});
    }
    else if ("7" == type) { //订单管理的列表
    	$('#rightTitle').text('菜品管理');
        $("#context").empty().load("../food/foodList.html", {
            number: Math.random()
        },
        function() {});
    }
    else if ("8" == type) { //订单管理的列表
    	$('#rightTitle').text('菜品管理');
        $("#context").empty().load("../food/foodAdd.html", {
            number: Math.random()
        },
        function() {});
    }else if ("9" == type) { //订单管理的列表
    	$('#rightTitle').text('活动管理');
        $("#context").empty().load("../activity/activityAdd.html", {
            number: Math.random()
        },
        function() {});
    }
    else{//加载首页
    	  $("#context").empty().load("./first.html", {
              number: Math.random()
          },
          function() {});
    } 
}

//判断是不是手机号
function isPoneAvailable(str) {  
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/; 
    console.log(myreg.test(str));
    if (myreg.test(str)) { 
    	
        return false;  
    } else {  
        return true;  
    }  
}