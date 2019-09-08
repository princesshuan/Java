<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>

  <div class="right">
      <div class="location">
          <strong>你现在所在的位置是:</strong>
          <span>商品库存管理页面 >> 商品修改页</span>
      </div>
      <div class="providerAdd">
          
          <form id="providerForm" name="providerForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/goods/updategoods">  
              <input type="hidden" name="id" value="${goods.id }">
              <!--div的class 为error是验证错误，ok是验证成功-->
              <div class="">
                  <label for="goodsCode">商品编码：</label>
                  <input type="text" name="goodscode" id="goodsCode" value="${goods.goodscode }" readonly="readonly"> 
              </div>
              <div>
                  <label for="goodsName">商品名称：</label>
                 <input type="text" name="goodsname" id="goodsName" value="${goods.goodsname }"> 
			<font color="red"></font>
              </div>
              
              <div>
                  <label for="goodsPrice">商品单价：</label>
                  <input type="text" name="goodsprice" id="goodsPrice" value="${goods.goodsprice }"> 
			<font color="red"></font>
              </div>
              
              <div>
                  <label for="goodsCount">商品库存：</label>
                  <input type="text" name="goodscount" id="goodsCount" value="${goods.goodscount }"> 
			<font color="red"></font>
              </div>                         
              <div>
                	<input type="hidden" id="errorinfo" value="${uploadFileError}"/>
                    <label for="goodsPicture">商品图片：</label>
                   	<input type="file" name="attachs" id="goodsPicture" value="${goods.goodspicture }"/>
                   	<font color="red"></font>
                </div>
              <div class="providerAddBtn">
                  <input type="button" name="save" id="save" value="保存">
				  <input type="button" id="back" name="back" value="返回" >
              </div>
          </form>
      </div>
  </div>
</section>
<%@include file="./common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/goodsmodify.js"></script>