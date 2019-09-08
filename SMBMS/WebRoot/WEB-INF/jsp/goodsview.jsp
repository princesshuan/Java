<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>商品库存管理页面 >> 信息查看</span>
        </div>
        <div class="providerView">
        <p><strong>商品图片</strong></p>
        <img src="${pageContext.request.contextPath }/${goods.goodspicture}" width=320px height=180px; style="float:left;">
        <div>
            <p><strong>商品编码：</strong><span>${goods.goodscode }</span></p>
            <p><strong>商品名称：</strong><span>${goods.goodsname }</span></p>
            <p><strong>商品单价：</strong><span>${goods.goodsprice }</span></p>
            <p><strong>商品库存量：</strong><span>${goods.goodscount }</span></p>
            <p><strong>入库时间：</strong><span><fmt:formatDate value="${goods.creationdate}" pattern="yyyy-MM-dd"/></span></p>         
            <br><br><br>  		
        </div>            
        </div>
        <div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="返回" >
            </div>
    </div>
<%@include file="./common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/goodsview.js"></script>
