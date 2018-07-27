//==================================================
//        数据分页JS_v1.0 2016-4-22
//================================================

/**
 * 加载分页信息
 * @param data  
 */
function initPage(data){
		var view = "";
		if(data.totalResult>0){
		view = view+"<ul class='pagination'>";
		if(data.currentPage==1){
			view = view+"<li class='previous'><a> 首页</a></li>"
			+"<li><a>&laquo;</a></li>";
		}else{						
			view = view+"<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\" class='previous'>首页</a></li>"
			+"<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(data.currentPage-1)+")\">&laquo;</a></li>";
		}
		var showTag = 5;//分页标签显示数量
		var startTag = 1;
		if(data.currentPage>showTag){
			startTag = data.currentPage-1;
		}
		var endTag = startTag+showTag-1;
		for(var i=startTag; i<=data.totalPage && i<=endTag; i++){
			if(data.currentPage==i){
				view = view+"<li class='active'><a><font style='color:#fff'>"+i+"</font></a></li>";
			}
				
			else{
				view = view+"<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+i+")\">"+i+"</a></li>";
			}
				
		}
		if(data.currentPage==data.totalPage){
			view = view+"	<li><a>&raquo;</a></li>"
			+"<li class='next'><a>尾页 </a></li>";
		}else{
			view = view+"	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(data.currentPage+1)+")\">&raquo;</a></li>"
			+"	<li style=\"cursor:pointer;\" class='next'><a onclick=\"nextPage("+data.totalPage+")\">尾页 </a></li>";
		}
		
		view = view+"</ul>";					
		}
		//弹出
		$("#pageDiv").html(view);  
}

//下一页
function nextPage(page){	
	selectAll(page);//页数页面回掉方法
}
//获取页数
function getPageNum(page){
	if(!page){
		page = 1;
	}
	return page;
}

