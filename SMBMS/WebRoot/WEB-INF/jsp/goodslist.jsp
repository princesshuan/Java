<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>商品库存管理页面</span>
            </div>
             <div class="search">
        	<form method="get" action="${pageContext.request.contextPath }/goods/goodslist">
				<input name="method" value="query" type="hidden">
				<span>商品编码：</span>
				<input name="queryGoodsCode" type="text" value="${queryGoodsCode }">
				
				<span>商品名称：</span>
				<input name="queryGoodsName" type="text" value="${queryGoodsName }">
				
				<input type="hidden" name="pageIndex" value="1"/>
				<input value="查 询" type="submit" id="searchbutton">
				<a href="${pageContext.request.contextPath }/goods/toOut">商品出库</a>
			</form>
        </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">商品编码</th>
                    <th width="20%">商品名称</th>
                    <th width="10%">商品单价(元)</th>
                    <th width="10%">商品库存</th>
                    <th width="20%">入库时间</th>
                    <th width="30%">操作</th>
                </tr>
                   <c:forEach var="goods" items="${goodsList }" varStatus="status">
					<tr>
						<td>
						<span>${goods.goodscode }</span>
						</td>
						<td>
						<span>${goods.goodsname }</span>
						</td>						
						<td>
						<span>${goods.goodsprice}</span>
						</td>
						<td>
						<span>${goods.goodscount}</span>
						</td>
						<td>
						<span><fmt:formatDate value="${goods.creationdate}" pattern="yyyy-MM-dd"/></span>
						</td>						
						<td>
						<span><a class="viewGoods" href="javascript:;" gid=${goods.id } goodsname=${goods.goodsname }><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>
						<span><a class="modifyGoods" href="javascript:;" gid=${goods.id } goodsname=${goods.goodsname }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
						<span><a class="deleteGoods" href="javascript:;" gid=${goods.id } goodsname=${goods.goodsname }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span> 
						</td>
					</tr>
				</c:forEach>
			</table>			
			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
		  	<c:import url="rollpage.jsp">
	          	<c:param name="totalCount" value="${totalCount}"/>
	          	<c:param name="currPageNo" value="${currPageNo}"/>
	          	<c:param name="totalPageCount" value="${totalPageCount}"/>
          	</c:import>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeGoods">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="./common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/goodslist.js"></script>
