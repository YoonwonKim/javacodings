<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>자바코딩즈 주문 관리</title>

	<%-- Frameworks --%>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<%-- Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/checkbox.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/dropdown.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/layer.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/tile.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/stack.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/pagination.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/select.min.js"></script>
	<%-- Fragment CSS --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/merchandiser/fragments/global/init.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/merchandiser/fragments/global/header.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/merchandiser/fragments/global/footer.css" />

	<%-- Page Component --%>
	<script type="module" src="${pageContext.request.contextPath}/resources/scripts/manage_orders.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/manage_orders.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/components/pagination.css" />
</head>
<body>
<%@ include file="/views/merchandiser/fragments/global/header.jsp" %>
<main>
<cds-layer level="1">
<cds-stack class="main" gap="16px" use-custom-gap-value>
	<cds-tile>
		<h5>주문 상태 요약</h5>
		<cds-stack gap="8px" orientation="horizontal" use-custom-gap-value class="main">
		<c:forEach var="item" items="${stateList}">
			<div class="value">
				<p class="label">${item.order_id}</p>
				<cds-stack orientation="horizontal">
					<h1>${item.quantity}</h1>
					<p class="measure">개</p>
				</cds-stack>
			</div>
		</c:forEach>
		</cds-stack>
	</cds-tile>
<%-- Region Order List ---------------------------------------------------------------------%>
	<cds-tile id="order-table" level="1">
		<cds-table is-selectable is-sortable use-zebra-styles>
			<cds-table-header-title slot="title">주문 관리</cds-table-header-title>
			<cds-table-header-description slot="description">주문 관리용 테이블입니다.</cds-table-header-description>
			<cds-table-head>
				<cds-table-header-row hide-checkbox>
					<cds-table-header-cell>주문 ID</cds-table-header-cell>
					<cds-table-header-cell>상품 ID</cds-table-header-cell>
					<cds-table-header-cell>구매자</cds-table-header-cell>
					<cds-table-header-cell>주문량</cds-table-header-cell>
					<cds-table-header-cell>주문상태</cds-table-header-cell>
					<cds-table-header-cell>주문일</cds-table-header-cell>
				</cds-table-header-row>
			</cds-table-head>
			<cds-table-body>
				<c:forEach var="order" items="${objectList}">
					<cds-table-row>
						<cds-table-cell>${order.order_id}</cds-table-cell>
						<cds-table-cell>${order.item_id}</cds-table-cell>
						<cds-table-cell>${order.member_id}</cds-table-cell>
						<cds-table-cell>${order.quantity}</cds-table-cell>
						<cds-table-cell>
							<cds-select inline class="order-state" id="${order.order_id}" value="${order.state}">
								<c:forEach var="state" items="${stateList}">
								<c:if test="${state.state >= order.state}">
									<cds-select-item value="${state.state}">
											${state.order_id}
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
	<%-- End Region Order List ---------------------------------------------------------------------%>
</cds-stack>
</cds-layer>
</main>
<%@ include file="/views/merchandiser/fragments/global/footer.jsp" %>
</body>
</html>
