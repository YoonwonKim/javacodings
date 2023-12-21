<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<%@ include file="/views/merchandiser/fragments/dependencies.jsp" %>
	<title>자바코딩즈 주문 관리</title>

	<link rel="stylesheet" href="/resources/styles/components/summary.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/components/pagination.css" />

	<%-- Page Component --%>
	<script type="module" src="${pageContext.request.contextPath}/resources/scripts/manage_orders.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/merchandiser/order.css" />
</head>
<body>
<%@ include file="/views/merchandiser/fragments/global/header.jsp" %>
<main>
	<div id="data">
		<%@ include file="/views/merchandiser/fragments/summary.jsp" %>

		<cds-tile id="list">
			<cds-table is-selectable is-sortable use-zebra-styles>
				<cds-table-header-title slot="title">주문 관리</cds-table-header-title>
				<cds-table-header-description slot="description">주문 관리용 테이블입니다.</cds-table-header-description>
				<cds-table-head>
					<cds-table-header-row hide-checkbox>
						<cds-table-header-cell>주문 ID</cds-table-header-cell>
						<cds-table-header-cell>구매자</cds-table-header-cell>
						<cds-table-header-cell>주문상태</cds-table-header-cell>
						<cds-table-header-cell>주문일</cds-table-header-cell>
					</cds-table-header-row>
				</cds-table-head>
				<cds-table-body>
					<c:forEach var="order" items="${objectList}">
						<cds-table-row onclick="location.href = '/admin/order/${order.order_id}'">
							<cds-table-cell>${order.order_id}</cds-table-cell>
							<cds-table-cell>${order.member_id}</cds-table-cell>
							<cds-table-cell>
								<cds-select inline class="order-state" id="${order.order_id}" value="${order.state}">
									<c:forEach var="state" items="${summary.stateList}" varStatus="i">
									<c:if test="${i.index >= order.state}">
										<cds-select-item value="${i.index}">
												${state}
										</cds-select-item>
									</c:if>
									</c:forEach>
								</cds-select>
							</cds-table-cell>
							<cds-table-cell>${order.reg_date}</cds-table-cell>
						</cds-table-row>
					</c:forEach>
				</cds-table-body>
			</cds-table>
			<%@ include file="/resources/components/pagination.jsp" %>
		</cds-tile>
	</div>
</main>
<%@ include file="/views/merchandiser/fragments/global/footer.jsp" %>
</body>
</html>
