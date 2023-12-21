<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/views/merchandiser/fragments/dependencies.jsp" %>
	<title>자바코딩즈 상품 이벤트 관리</title>
	<script src="/resources/scripts/eventsPg.js"></script>
	<link rel="stylesheet" href="/resources/styles/merchandiser/item.css"/>
</head>
<body>
<%@ include file="/views/merchandiser/fragments/global/header.jsp" %>
<main>
	<div class="layer-01">
		<p>이벤트 아이디</p>
		<h1>${eventId}</h1>

	<cds-table is-selectable is-sortable>
		<cds-table-header-title slot="title">상품 이벤트 관리</cds-table-header-title>
		<cds-table-header-description slot="description">
			상품 이벤트 관리용 테이블입니다.
		</cds-table-header-description>
		<cds-table-toolbar slot="toolbar">
			<cds-table-toolbar-content>
				<cds-table-toolbar-search
						placeholder="Filter table"></cds-table-toolbar-search>
				<cds-button class="eventAdd">상품(이벤트) 추가</cds-button>
				<cds-button id="check">삭제</cds-button>
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
				<cds-table-header-cell></cds-table-header-cell>
				<cds-table-header-cell>이미지</cds-table-header-cell>
				<cds-table-header-cell>이름</cds-table-header-cell>
				<cds-table-header-cell>설명</cds-table-header-cell>
			</cds-table-header-row>
		</cds-table-head>
		<cds-table-body>
			<c:forEach var="item" items="${itemList}">
				<cds-table-row>
					<cds-table-cell><input type="checkbox" class="itemCheckbox"></cds-table-cell>
					<cds-table-cell><image src="/resources/images/${item.path}" width="80px"></image></cds-table-cell>
					<cds-table-cell name="label">${item.label}</cds-table-cell>
					<cds-table-cell name="desc">${item.desc}</cds-table-cell>
				</cds-table-row>
			</c:forEach>
		</cds-table-body>
	</cds-table>
	</div>
</main>
<%@ include file="/views/merchandiser/fragments/global/footer.jsp" %>
</body>
</html>
