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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/merchandiser/fragments/init.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/merchandiser/fragments/header.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/merchandiser/fragments/footer.css" />

	<%-- Page Component --%>
	<script type="module" src="${pageContext.request.contextPath}/resources/scripts/manage_orders.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/manage_orders.css" />
</head>
<body>
<%@ include file="/views/merchandiser/fragments/header.jsp" %>
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
			<cds-table-toolbar slot="toolbar">
				<cds-table-toolbar-content>
					<cds-table-toolbar-search
							placeholder="Filter table"></cds-table-toolbar-search>
					<cds-button id="modify">수정하기</cds-button>
				</cds-table-toolbar-content>
				<cds-table-batch-actions>
					<cds-button tooltip-position="bottom" onclick="sendOrderUpdateRequest()">Save
						<svg focusable="false"
						     preserveAspectRatio="xMidYMid meet"
						     xmlns="http://www.w3.org/2000/svg"
						     fill="currentColor"
						     aria-hidden="true"
						     width="16"
						     height="16"
						     viewBox="0 0 16 16"
						     slot="icon">
							<path d="M13.9,4.6l-2.5-2.5C11.3,2.1,11.1,2,11,2H3C2.4,2,2,2.4,2,3v10c0,0.6,0.4,1,1,1h10c0.6,0,1-0.4,1-1V5  C14,4.9,13.9,4.7,13.9,4.6z M6,3h4v2H6V3z M10,13H6V9h4V13z M11,13V9c0-0.6-0.4-1-1-1H6C5.4,8,5,8.4,5,9v4H3V3h2v2c0,0.6,0.4,1,1,1  h4c0.6,0,1-0.4,1-1V3.2l2,2V13H11z"></path></svg>
					</cds-button>
				</cds-table-batch-actions>
			</cds-table-toolbar>
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
				<c:forEach var="order" items="${orderList}">
					<cds-table-row>
						<cds-table-cell>${order.order_id}</cds-table-cell>
						<cds-table-cell>${order.item_id}</cds-table-cell>
						<cds-table-cell>${order.member_id}</cds-table-cell>
						<cds-table-cell>${order.quantity}</cds-table-cell>
						<cds-table-cell>
							<cds-dropdown id="${order.order_id}" value="${order.state}">
								<c:forEach begin="${order.state}" end="8" varStatus="loop">
									<cds-dropdown-item value="${loop.index}">
										<c:choose>
											<c:when test="${loop.index == 1}">장바구니 담기</c:when>
											<c:when test="${loop.index == 2}">결제 완료</c:when>
											<c:when test="${loop.index == 3}">주문 확인</c:when>
											<c:when test="${loop.index == 4}">배송 시작</c:when>
											<c:when test="${loop.index == 5}">배송 중</c:when>
											<c:when test="${loop.index == 6}">배송 완료</c:when>
											<c:when test="${loop.index == 7}">반품</c:when>
											<c:when test="${loop.index == 8}">처리 완료</c:when>
										</c:choose>
									</cds-dropdown-item>
								</c:forEach>
							</cds-dropdown>
						</cds-table-cell>
						<cds-table-cell>${order.reg_date}</cds-table-cell>
					</cds-table-row>
				</c:forEach>
			</cds-table-body>
		</cds-table>

		<cds-pagination page-size="10" total-items="100" page="1" start="1">
			<cds-page-sizes-select>
				<cds-select-item value="10">10</cds-select-item>
				<cds-select-item value="20">20</cds-select-item>
				<cds-select-item value="30">30</cds-select-item>
				<cds-select-item value="40">40</cds-select-item>
				<cds-select-item value="50">50</cds-select-item>
			</cds-page-sizes-select>
		</cds-pagination>
	</cds-tile>
	<%-- End Region Order List ---------------------------------------------------------------------%>
</cds-stack>
</cds-layer>
</main>
<%@ include file="/views/merchandiser/fragments/footer.jsp" %>
</body>
</html>
