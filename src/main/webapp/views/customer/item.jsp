<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 상품 페이지</title>
	<!-- <link rel="stylesheet" href="/resources/css/manage_products.css" /> -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

	<%--  Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/text-input.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<%-- Fragment CSS --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
<div class="layer-01">
	<cds-table is-selectable is-sortable>
		<cds-table-header-title slot="title">상품 리스트</cds-table-header-title>
		<cds-table-header-description slot="description">
			고객이 보는 상품 리스트 페이지 입니다.
		</cds-table-header-description>
		
		<cds-table-toolbar slot="toolbar">
			<cds-table-toolbar-content>
				<cds-table-toolbar-search
						placeholder="Filter table"></cds-table-toolbar-search>
				<cds-button>상품 추가</cds-button>
			</cds-table-toolbar-content>
			<cds-table-batch-actions>
				<cds-button>Delete
					<svg focusable="false" preserveAspectRatio="xMidYMid meet" xmlns="http://www.w3.org/2000/svg" fill="currentColor"
							aria-hidden="true" width="16" height="16" viewBox="0 0 32 32" slot="icon">
						<path d="M12 12H14V24H12zM18 12H20V24H18z"></path>
						<path d="M4 6V8H6V28a2 2 0 002 2H24a2 2 0 002-2V8h2V6zM8 28V8H24V28zM12 2H20V4H12z"></path>
					</svg>
				</cds-button>
			</cds-table-batch-actions>
		</cds-table-toolbar>
		<cds-table-head>
			<cds-table-header-row hide-checkbox>
				<cds-table-header-cell>사진</cds-table-header-cell>
				<cds-table-header-cell>상품명</cds-table-header-cell>
				<cds-table-header-cell>가격</cds-table-header-cell>
				<cds-table-header-cell>설명</cds-table-header-cell>
				<cds-table-header-cell>재고</cds-table-header-cell>
			</cds-table-header-row>
		</cds-table-head>
		<cds-table-body>
			<c:forEach var="item" items="${itemList}">
				<cds-table-row class="itemDt" onClick="location.href='/listItemDt?item_id=${item.item_id}'">
					<cds-table-cell><img src="/resources/images/${item.image}" width="200px;"></cds-table-cell>
					<cds-table-cell>${item.label}</cds-table-cell>
					<cds-table-cell>${item.price}</cds-table-cell>
					<cds-table-cell>${item.desc}</cds-table-cell>
					<cds-table-cell>${item.stock}</cds-table-cell>
					<cds-table-cell><cds-checkbox></cds-checkbox></cds-table-cell>
				</cds-table-row>
			</c:forEach>
		</cds-table-body>
		<%-- <cds-table-footer>
		<tr>
	   <td colspan="6" style="text-align: center; border: 1px solid #ffffff";>
	     <c:if test="${pageDto.startPg>pBlock}">
	       <a href="notice?curPage=${pageDto.startPg-pBlock}&curBlock=${pageDto.curBlock-1}">[이전]</a>
	     </c:if>
	     <c:forEach begin="${pageDto.startPg}" end="${pageDto.endPg}" var="p" step="1">
	       <a href="notice?curPage=${p}&curBlock=${pageDto.curBlock}"> 
	         <span><c:out value="${p}"/></span>
	       </a>&nbsp;&nbsp;
	     </c:forEach>
	       <c:if test="${pageDto.endPg<pageDto.pgCnt}">
	         <a href="notice?curPage=${pageDto.startPg+pBlock}&curBlock=${pageDto.curBlock+1}">[다음]</a>
	       </c:if>
	   </td>
	</tr>
		</cds-table-footer> --%>
</cds-table>
	</div> 
	
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>