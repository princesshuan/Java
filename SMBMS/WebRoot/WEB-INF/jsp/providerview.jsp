<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>供应商管理页面 >> 信息查看</span>
	</div>
	<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
	<div id="innerdiv"style="position:absolute;">
	<img id="bigimg" style="border:5px solid #fff;" src="" />
	</div>
	</div>
	<div class="providerView">
		<p>
			<strong>营业执照</strong>
		</p>
		<img class="pimg" src="${pageContext.request.contextPath }/${provider.prolicence}"
			width=280px height=350px; style="float:left;">
		<div>
			<p>
				<strong>供应商编码：</strong><span>${provider.procode }</span>
			</p>
			<p>
				<strong>供应商名称：</strong><span>${provider.proname }</span>
			</p>
			<p>
				<strong>联系人：</strong><span>${provider.procontact }</span>
			</p>
			<p>
				<strong>联系电话：</strong><span>${provider.prophone }</span>
			</p>
			<p>
				<strong>传真：</strong><span>${provider.profax }</span>
			</p>
			<p>
				<strong>描述：</strong><span>${provider.prodesc}</span>
			</p>
			<p>
				<strong>订单：</strong>
				<c:if test="${count==0 }">
					<span>暂无订单</span>
					<br>
					<br>
					<br>
					<br>
				</c:if>
				<c:if test="${count>0 }">
					<ol style="margin-left:480px;">
						<c:forEach items="${bill}" var="bill">
							<li style="list-style:decimal">
							  <span>${bill.productname}</span>&nbsp;&nbsp;
						      <span>${bill.productcount}${bill.productunit}</span>&nbsp;&nbsp;
						      <span>${bill.totalprice}元</span>&nbsp;&nbsp; 
						      <span> 
						        <c:if test="${bill.ispayment==1}">未付款</c:if>
						        <c:if test="${bill.ispayment==2}">已付款</c:if>
							 </span>
							</li>
						</c:forEach>
					</ol>
					<br>
					<br>
				</c:if>
			</p>
		</div>
		<div class="providerAddBtn">
			<input type="button" id="back" name="back" value="返回">
		</div>
	</div>

</div>
<%@include file="./common/foot.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/providerview.js"></script>
