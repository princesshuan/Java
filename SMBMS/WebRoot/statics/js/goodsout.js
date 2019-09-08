var goodsName = null;
var goodsCount = null;
var addBtn = null;
var backBtn = null;

$(function(){
	goodsName = $("#goodsName");
	goodsCount = $("#goodsCount");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	goodsName.next().html("*");
	goodsCount.next().html("*");
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	
		$.ajax({
			type:"GET",//请求类型
			url:path+"/goods/Goodslist",//请求的url
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data != null){
					console.log(data);
					$("select").html("");
					var options = "<option value=\"0\">--请选择--</option>";
					for(var i = 0; i < data.length; i++){
										
						options += "<option value=\""+data[i].id+"\" >"+data[i].goodsname+"</option>";
					}
					$("select").html(options);
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				validateTip(goodsName.next(),{"color":"red"},imgNo+" 获取商品列表error",false);
			}
		});
		goodsName.bind("focus",function(){
			validateTip(goodsName.next(),{"color":"#666666"},"* 请选择要出库的商品",false);
		}).bind("blur",function(){
			if(goodsName.val() != null && goodsName.val() !="" && goodsName.val()!=0){
				validateTip(goodsName.next(),{"color":"green"},imgYes,true);
			}else{
				validateTip(goodsName.next(),{"color":"red"},imgNo+"出库商品不能为空，请重新选择！",false);
			}
			
		});
		
	
	goodsCount.on("focus",function(){
		validateTip(goodsCount.next(),{"color":"#666666"},"* 请输入商品出库数量",false);
	}).on("blur",function(){
		if(goodsCount.val() != null && goodsCount.val() != ""){
			$.ajax({
				type:"GET",//请求类型
				url:path+"/goods/checkCount",//请求的url
				data:{goodsCount:goodsCount.val(),goodsName:goodsName.val()},//请求参数
				dataType:"json",//ajax接口（请求url）返回的数据类型
				success:function(data){//data：返回数据（json对象）
					if(data.goodsCount == "exist"){//账号已存在，错误提示
						validateTip(goodsCount.next(),{"color":"green"},imgYes+" 库存充足~",true);
					}else if(data.goodsCount == "noexist"){//账号可用，正确提示
						validateTip(goodsCount.next(),{"color":"red"},imgNo+ " 库存不足！",false);
					}
				},
				error:function(data){//当访问时候，404，500 等非200的错误状态码
					validateTip(goodsCount.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
				}
			});
		
		}else{
			validateTip(goodsCount.next(),{"color":"red"},imgNo+" 商品出库数量不能为空，请重新输入",false);
		}
		
	});
	addBtn.bind("click",function(){
		if(goodsCount.attr("validateStatus") != "true"){
			goodsCount.blur();
		}else if(goodsName.attr("validateStatus") != "true"){
			goodsName.blur();
		}else{
			if(confirm("是否确认提交数据")){
				$("#providerForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
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