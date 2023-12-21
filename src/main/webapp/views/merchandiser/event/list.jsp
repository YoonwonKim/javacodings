<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/views/merchandiser/fragments/dependencies.jsp" %>
	<title>자바코딩즈 상품 이벤트 관리</title>
	<%-- Widgets --%>
	<link rel="stylesheet" href="/resources/styles/components/pagination.css" />
	<script src="/resources/scripts/common/components/pagination.js"></script>
</head>
<body>
<%@ include file="/views/merchandiser/fragments/global/header.jsp" %>

<main>
<cds-layer level="1">
<cds-tile>
	<cds-table is-selectable is-sortable>
		<cds-table-header-title slot="title">상품 이벤트 관리</cds-table-header-title>
		<cds-table-header-description slot="description">
			상품 이벤트 관리용 테이블입니다.

		</cds-table-header-description>
		<cds-table-head>
			<cds-table-header-row hide-checkbox>
				<cds-table-header-cell>이벤트 아이디</cds-table-header-cell>
				<cds-table-header-cell>이벤트 명칭</cds-table-header-cell>
				<cds-table-header-cell>종류</cds-table-header-cell>
				<cds-table-header-cell>내용</cds-table-header-cell>
				<cds-table-header-cell>시작 일자</cds-table-header-cell>
				<cds-table-header-cell>종료 일자</cds-table-header-cell>
				<cds-table-header-cell>적용 아이템</cds-table-header-cell>
			</cds-table-header-row>
		</cds-table-head>
		<cds-table-body>
			<c:forEach var="event" items="${objectList}" varStatus="status">
				<cds-table-row class="row" event-id="${event.event_id}"
				               onclick="location.href = '/admin/event/${event.event_id}'">
					<cds-table-cell>${event.event_id}</cds-table-cell>
					<cds-table-cell>${event.label}</cds-table-cell>
					<cds-table-cell>${event.category}</cds-table-cell>
					<cds-table-cell>${event.content}</cds-table-cell>
					<cds-table-cell>${event.start_date}</cds-table-cell>
					<cds-table-cell>${event.end_date}</cds-table-cell>
					<cds-table-cell>${itemList[status.index]}</cds-table-cell>
				</cds-table-row>
			</c:forEach>
		</cds-table-body>
	</cds-table>
	<%@ include file="/resources/components/pagination.jsp" %>
</cds-tile>
</cds-layer>
</main>

<%@ include file="/views/merchandiser/fragments/global/footer.jsp" %>
</body>
</html>
