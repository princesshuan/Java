<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>商品管理页面 >> 商品出库页面</span>
        </div>
        <div class="providerAdd">
           <form id="providerForm" name="providerForm" method="post" 
           action="${pageContext.request.contextPath }/goods/outgoods">
			<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
                
                <div>
                    <label for="goodsName">商品名称：</label>
                    <select name="goodsname" id="goodsName"></select>             
					<font color="red"></font>
                </div>
                
                <div>
                    <label for="goodsCount">商品出库数量：</label>
                    <input type="text" name="goodscount" id="goodsCount" value=""> 
					<font color="red"></font>
                </div>               
                <div class="providerAddBtn">
                    <input type="button" name="add" id="add" value="确认出库">
					<input type="button" id="back" name="back" value="返回" >
                </div>               
            </form>
     </div>
</div>
</section>
<%@include file="./common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/goodsout.js"></script>
