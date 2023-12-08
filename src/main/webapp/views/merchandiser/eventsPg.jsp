<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 상품 이벤트 관리</title>
	<link rel="stylesheet" href="/resources/css/manage_products.css" />
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

	<%--  Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/select.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/checkbox.min.js"></script>
	<%-- Fragment CSS --%>
	<link rel="stylesheet" href="/views/merchandiser/fragments/init.css" />
	<link rel="stylesheet" href="/views/merchandiser/fragments/header.css" />
	<link rel="stylesheet" href="/views/merchandiser/fragments/footer.css" />
	<script src="/resources/scripts/eventsPg.js"></script>
</head>
<body>
<%@ include file="/views/merchandiser/fragments/header.jsp" %>
<main>
	<div class="layer-01">
	<form action="/admin/actions/eventMgt" name="sub" method="post">
	<cds-table is-selectable is-sortable>
		<cds-table-header-title slot="title">상품 이벤트 관리</cds-table-header-title>
		<cds-table-header-description slot="description">
			상품 이벤트 관리용 테이블입니다.
		
		</cds-table-header-description>
		<cds-table-toolbar slot="toolbar">
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
				<cds-table-header-cell></cds-table-header-cell>
				<cds-table-header-cell>이벤트 아이디</cds-table-header-cell>
				<cds-table-header-cell>카테고리</cds-table-header-cell>
				<cds-table-header-cell>콘텐츠</cds-table-header-cell>
				<cds-table-header-cell>상품 수정</cds-table-header-cell>
			</cds-table-header-row>
		</cds-table-head>
		<cds-table-body>
			<c:forEach var="event" items="${event}">
				<cds-table-row>
					<%-- <cds-table-cell><cds-checkbox name="ck" value="${i.index}"></cds-checkbox></cds-table-cell> --%>
					<%-- <cds-table-cell><image src="/resources/images/${item.image}" width="80px"></image></cds-table-cell> --%>
					<cds-table-cell>
						<input type="hidden" name="category" value="${event.category }">
						<input type="hidden" name="content" value="${event.content }">
						<input type="hidden" name="event_id" value="${event.event_id }">
					</cds-table-cell>
					<cds-table-cell>${event.event_id}</cds-table-cell>
					<cds-table-cell>${event.category}
					<select name ="category" id="category">
							<option value="">선택하세요</option>
							<option value="세일">세일</option>
							<option value="증정">증정</option>
						</select>
						
					<!-- 
						카테고리 선택박스 첫화면 기본값
					<script type="text/javascript">
		               $(function(){
		            	   //배송관련 상태
		            	   $("#category").val('${event.category}')
		               }) 
		             </script> -->
		             </cds-table-cell>
					<cds-table-cell>${event.content}
					<select name ="content" id="content" >
							<option value="">선택하세요</option>
							<option value="10%">10%</option>
							<option value="20%">20%</option>
							<option value="1+1">1+1</option>
							<option value="2+1">2+1</option>
						</select>
					<!-- <script type="text/javascript">
		               $(function(){
		            	   //배송관련 상태
		            	   $("#content").val('${event.content}')
		               })
		             </script> -->
					</cds-table-cell>
					<cds-table-cell><cds-button kind="ghost" class="events" name="eventsUp" href="../admin/eventsUp">수정</cds-button></cds-table-cell>
				</cds-table-row>
			</c:forEach>
				<input type="button" value="상태수정" class="stateUpdate">
		</cds-table-body>
	</cds-table>
	</form>
	</div>

	<%@ include file="/views/merchandiser/components/product_modal.jsp" %>
</main>
<%@ include file="/views/merchandiser/fragments/footer.jsp" %>
</body>
</html>
