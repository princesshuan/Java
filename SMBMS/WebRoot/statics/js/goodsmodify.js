var goodsPrice = null;
var goodsCount = null;
var saveBtn = null;
var backBtn = null;

$(function(){
	goodsPrice = $("#goodsPrice");
	goodsCount = $("#goodsCount");
	saveBtn = $("#save");
	backBtn = $("#back");
	
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	goodsPrice.next().html("*");
	goodsCount.next().html("*");
	
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	goodsPrice.on("focus",function(){
		validateTip(goodsPrice.next(),{"color":"#666666"},"* 请输入商品单价",false);
	}).on("blur",function(){
		if(goodsPrice.val() != null && goodsPrice.val() != ""){
			validateTip(goodsPrice.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(goodsPrice.next(),{"color":"red"},imgNo+" 单价不能为空，请重新输入",false);
		}
		
	});
	
	goodsCount.on("focus",function(){
		validateTip(goodsCount.next(),{"color":"#666666"},"* 请输入商品的库存量",false);
	}).on("blur",function(){
		if(goodsCount.val() != null && goodsCount.val() != ""){
			validateTip(goodsCount.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(goodsCount.next(),{"color":"red"},imgNo+" 库存不能为空，请重新输入",false);
		}
	});
	
	saveBtn.on("click",function(){
		goodsPrice.blur();
		goodsCount.blur();
		if(goodsPrice.attr("validateStatus") == "true" && 
				goodsCount.attr("validateStatus") == "true"){
			if(confirm("是否确认提交数据")){
				$("#providerForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
		//alert("modify: "+referer);
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