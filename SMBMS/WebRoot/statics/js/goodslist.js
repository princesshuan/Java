var goodsObj;

//供应商管理页面上点击删除按钮弹出删除框(providerlist.jsp)
function deleteGoods(obj){
	$.ajax({
		type:"GET",
		url:path+"/goods/delet",
		data:{gid:obj.attr("gid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
				window.location.reload();

			}else if(data.delResult == "false"){//删除失败
				//alert("对不起，删除供应商【"+obj.attr("proname")+"】失败");
				changeDLGContent("对不起，商品【"+obj.attr("goodsname")+"】还有库存，无法删除");
			}else{
				//alert("对不起，供应商【"+obj.attr("proname")+"】不存在");
				changeDLGContent("对不起，该商品【"+obj.attr("goodsname")+"】不存在");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeGoods').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeGoods').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}
$(function(){
	$(".viewGoods").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/goods/goodsview?gid="+ obj.attr("gid");
	});
	
	$(".modifyGoods").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/goods/findById?gid="+ obj.attr("gid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteGoods(goodsObj);
	});

	$(".deleteGoods").on("click",function(){
		goodsObj = $(this);
		changeDLGContent("你确定要删除商品【"+goodsObj.attr("goodsname")+"】吗？");
		openYesOrNoDLG();
	});
	var totalCount = $(this).find("#totalCount").attr("value");
	var currentPageNo = $(this).find("#currentPageNo").attr("value");
	var totalPageCount = $(this).find("#totalPageCount").attr("value");
	if(totalCount>0 && currentPageNo>totalPageCount){
		window.location.href="javascript:page_nav(document.forms[0],$('#currentPageNo').val()-1);";
 	}

});