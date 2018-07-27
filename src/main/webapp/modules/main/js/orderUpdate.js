
//删除菜单详情某个菜
function deleteOrderInfo(id,obj){
//	$(obj).parent().parent().find("input[type=text]").val(0);
//	editOrderInfo(id,obj);
	var params={
			url:basePath + '/order/delete',
			data:{
				ovtId:id
				}
			};
getDataByAjax(params,function(data){
	if (data != null&&data.status=="1") {
		showToast("删除成功",'success');
		console.log($(obj).parent().parent());
        $(obj).parent().parent().remove();
        var preTotalPrice= $(obj).parent().parent().find('.totalPrice').text();
        getTotalPrice(preTotalPrice,"0","0");
    }else{
    	showToast("删除失败",'error');
    }
},null);
};
//修改菜单下的某个菜的信息
function editOrderInfo(id,obj){
	var num=$(obj).parent().parent().find("input[type=text]").val();
	if(!num||num=="0"){
		deleteOrderInfo(id,obj);
		return;
	}
	var price= $(obj).parent().parent().find('.price').text();
	var params={
			url:basePath + '/order/update',
			data:{
				ovtId:id,
				type:"2",
				num:num,
				price:price
				}
			};
getDataByAjax(params,function(data){
	if (data&&data.status=="1") {
		showToast("修改成功",'success');
		var totalPrice=Number(num)*Number(price);
		var preTotalPrice= $(obj).parent().parent().find('.totalPrice').text();
//		var priceDifferences=Number(preTotalPrice)-Number(totalPrice);
//		var tp=Number($("#totalPrice").val())-Number(priceDifferences);
//		$("#totalPrice").val(tp);
		$(obj).parent().parent().find('.totalPrice').text(totalPrice);
		getTotalPrice(preTotalPrice,num,price);
    }else{
    	showToast("删除失败",'error');
    }
},null);
};
//根据现在单个菜品的总价计算订单的现在总价
function getTotalPrice(preTotalPrice,num,price){
	var totalPrice=Number(num)*Number(price);
	var priceDifferences=Number(preTotalPrice)-Number(totalPrice);
	var tp=Number($("#totalPrice").val())-Number(priceDifferences);
	$("#totalPrice").val(tp);
}
//修改订单信息
function updateOrder(id){
	
	var params={
			url:basePath + '/order/update',
			data:{
				orderId:id,
				type:"1",
				tableNumber:$("#tableNumber").val()
				}
			};
getDataByAjax(params,function(){
	if (data&&data.status=="1") {
		showToast("修改成功",'success');
        $(obj).parent().parent().remove();
    }else{
    	showToast("修改失败",'error');
    }
},null);
};

//打印订单
function printOrder(){

	var params={
			url:basePath + '/print/print',
			data:{
				orderId:$("orderId").val()
				}
			};
getDataByAjax(params,function(data){
	
},null);
}