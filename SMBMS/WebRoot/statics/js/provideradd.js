var proCode = null;
var proName = null;
var proContact = null;
var proPhone = null;
var addBtn = null;
var backBtn = null;
var proLicence = null;
var errorinfo = null;

$(function(){
	proCode = $("#proCode");
	proName = $("#proName");
	proContact = $("#proContact");
	proPhone = $("#proPhone");
	addBtn = $("#add");
	backBtn = $("#back");
	proLicence = $("#proLicence");
	errorinfo = $("#errorinfo");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	proCode.next().html("*");
	proName.next().html("*");
	proContact.next().html("*");
	proPhone.next().html("*");
	if(errorinfo.val() == null || errorinfo.val() == ""){
		proLicence.next().html("* 上传大小不能超过500K * 上传文件类型必须为：jpg、jpeg、png、pneg");
	}else{
		proLicence.next().html(errorinfo.val());
	}
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	proCode.on("blur",function(){
		if(proCode.val() != null && proCode.val() != ""){
			validateTip(proCode.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proCode.next(),{"color":"red"},imgNo+" 编码不能为空，请重新输入",false);
		}
		
		$.ajax({
			type:"GET",//请求类型
			url:path+"/provider/checkCode",//请求的url
			data:{proCode:proCode.val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.proCode == "exist"){//账号已存在，错误提示
					validateTip(proCode.next(),{"color":"red"},imgNo+ " 该编码已存在",false);
				}else if(data.proCode == "noexist"){//账号可用，正确提示
					validateTip(proCode.next(),{"color":"green"},imgYes+" 该编码可以使用",true);
				}else{
					validateTip(proCode.next(),{"color":"red"},imgNo+" 编码不能为空，请重新输入",false);
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				validateTip(proCode.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
			}
		});
		
	}).on("focus",function(){
		//显示友情提示
		validateTip(proCode.next(),{"color":"#666666"},"* 请输入供应商编码",false);
	}).focus();
	
	proName.on("focus",function(){
		validateTip(proName.next(),{"color":"#666666"},"* 请输入供应商名称",false);
	}).on("blur",function(){
		if(proName.val() != null && proName.val() != ""){
			validateTip(proName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proName.next(),{"color":"red"},imgNo+" 供应商名称不能为空，请重新输入",false);
		}
		$.ajax({
			type:"GET",//请求类型
			url:path+"/provider/checkName",//请求的url
			data:{proName:proName.val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.proName == "exist"){//账号已存在，错误提示
					validateTip(proName.next(),{"color":"red"},imgNo+ " 该名称已存在",false);
				}else if(data.proName == "noexist"){//账号可用，正确提示
					validateTip(proName.next(),{"color":"green"},imgYes+" 该名称可以使用",true);
				}else{
					validateTip(proName.next(),{"color":"red"},imgNo+" 供应商名称不能为空，请重新输入",false);
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				validateTip(proName.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
			}
		});
		
		
	});
	
	proContact.on("focus",function(){
		validateTip(proContact.next(),{"color":"#666666"},"* 请输入联系人",false);
	}).on("blur",function(){
		if(proContact.val() != null && proContact.val() != ""){
			validateTip(proContact.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proContact.next(),{"color":"red"},imgNo+" 联系人不能为空，请重新输入",false);
		}
		
	});
	
	proPhone.on("focus",function(){
		validateTip(proPhone.next(),{"color":"#666666"},"* 请输入手机号",false);
	}).on("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(proPhone.val().match(patrn)){
			validateTip(proPhone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(proPhone.next(),{"color":"red"},imgNo + " 您输入的手机号格式不正确",false);
		}
	});
	
	addBtn.bind("click",function(){
		if(proCode.attr("validateStatus") != "true"){
			proCode.blur();
		}else if(proName.attr("validateStatus") != "true"){
			proName.blur();
		}else if(proContact.attr("validateStatus") != "true"){
			proContact.blur();
		}else if(proPhone.attr("validateStatus") != "true"){
			proPhone.blur();
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