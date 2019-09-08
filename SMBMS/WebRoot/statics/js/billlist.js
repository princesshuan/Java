var billObj;

//订单管理页面上点击删除按钮弹出删除框(billlist.jsp)
function deleteBill(obj){
	$.ajax({
		type:"GET",
		url:path+"/bill/delet",
		data:{billid:obj.attr("billid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
				window.location.reload();
			}else if(data.delResult == "false"){//删除失败
				//alert("对不起，删除订单【"+obj.attr("billcc")+"】失败");
				changeDLGContent("对不起，删除订单【"+obj.attr("billcc")+"】失败");
			}else if(data.delResult == "no"){
				//alert("对不起，订单【"+obj.attr("billcc")+"】不存在");
				changeDLGContent("对不起，订单【"+obj.attr("billcc")+"】尚未付款，不能删除");
			}
		},
		error:function(data){
			changeDLGContent("对不起，删除订单【"+obj.attr("billcc")+"】失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	$(".viewBill").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/bill/billview?billid="+ obj.attr("billid");
	});
	
	$(".modifyBill").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/bill/findById?billid="+ obj.attr("billid");
	});
	
	$(".modify").on("click",function(){
		var obj = $(this);
		//console.log(obj.attr("billcc"));
		changeDLGContent("对不起，此订单【"+obj.attr("billcc")+"】已付款，无法修改");
		openYesOrNoDLG();
	});
	
	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteBill(billObj);
	});

	$(".deleteBill").on("click",function(){
		billObj = $(this);
		changeDLGContent("你确定要删除订单【"+billObj.attr("billcc")+"】吗？");
		openYesOrNoDLG();
	});
	var totalCount = $(this).find("#totalCount").attr("value");
	var currentPageNo = $(this).find("#currentPageNo").attr("value");
	var totalPageCount = $(this).find("#totalPageCount").attr("value");
	if(totalCount>0 && currentPageNo>totalPageCount){
		window.location.href="javascript:page_nav(document.forms[0],$('#currentPageNo').val()-1);";
 	}




});