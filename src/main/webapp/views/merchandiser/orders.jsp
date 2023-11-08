<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>자바코딩즈 주문 관리</title>
	<link rel="stylesheet" href="/resources/css/manage_orders.css" />

	<%-- Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/checkbox.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/dropdown.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
	<%-- Fragment CSS --%>
	<link rel="stylesheet" href="/views/merchandiser/fragments/init.css" />
	<link rel="stylesheet" href="/views/merchandiser/fragments/header.css" />
	<link rel="stylesheet" href="/views/merchandiser/fragments/footer.css" />
	
	<style type="text/css">
		cds-dropdown:not(:defined),
		cds-dropdown-item:not(:defined) {
			visibility: hidden;
		}
	</style>
	<script type="text/javascript" src="/resources/scripts/manage_orders.js"></script>
</head>
<body>
<%@ include file="/views/merchandiser/fragments/header.jsp" %>
<main>
	<div class="layer-01">
	<cds-table is-selectable is-sortable>
		<cds-table-header-title slot="title">주문 관리</cds-table-header-title>
		<cds-table-header-description slot="description">
			주문 관리용 테이블입니다.
		</cds-table-header-description>
		<cds-table-toolbar slot="toolbar">
			<cds-table-toolbar-content>
				<cds-table-toolbar-search
						placeholder="Filter table"></cds-table-toolbar-search>
				<cds-button id="modify">수정하기</cds-button>
			</cds-table-toolbar-content>
			<cds-table-batch-actions>				
				<cds-button
						tooltip-position="bottom"
						onclick="sendOrderUpdateRequest()"
				>Save
					<svg
							focusable="false"
							preserveAspectRatio="xMidYMid meet"
							xmlns="http://www.w3.org/2000/svg"
							fill="currentColor"
							aria-hidden="true"
							width="16"
							height="16"
							viewBox="0 0 16 16"
							slot="icon">
						<path
								d="M13.9,4.6l-2.5-2.5C11.3,2.1,11.1,2,11,2H3C2.4,2,2,2.4,2,3v10c0,0.6,0.4,1,1,1h10c0.6,0,1-0.4,1-1V5  C14,4.9,13.9,4.7,13.9,4.6z M6,3h4v2H6V3z M10,13H6V9h4V13z M11,13V9c0-0.6-0.4-1-1-1H6C5.4,8,5,8.4,5,9v4H3V3h2v2c0,0.6,0.4,1,1,1  h4c0.6,0,1-0.4,1-1V3.2l2,2V13H11z"></path></svg
					></cds-button>
			</cds-table-batch-actions>
		</cds-table-toolbar>
		<cds-table-head>
			<cds-table-header-row hide-checkbox>				
				<cds-table-header-cell></cds-table-header-cell>
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
					<cds-table-cell></cds-table-cell>
					<cds-table-cell>${order.order_id}</cds-table-cell>
					<cds-table-cell>${order.item_id}</cds-table-cell>
					<cds-table-cell>${order.member_id}</cds-table-cell>
					<cds-table-cell>${order.quantity}</cds-table-cell>
					<cds-table-cell>
						<div id="app">
							<cds-dropdown trigger-content="Select an item" id="${order.order_id}" value="${order.state}">
	                            <cds-dropdown-item value="0">장바구니 담기</cds-dropdown-item>
	                            <cds-dropdown-item value="1">결제 완료</cds-dropdown-item>
	                            <cds-dropdown-item value="2">주문 확인</cds-dropdown-item>
	                            <cds-dropdown-item value="3">배송 시작</cds-dropdown-item>
	                            <cds-dropdown-item value="4">배송 중</cds-dropdown-item>
	                            <cds-dropdown-item value="5">배송 완료</cds-dropdown-item>
	                            <cds-dropdown-item value="6">환불</cds-dropdown-item>
	                            <cds-dropdown-item value="7">반품</cds-dropdown-item>
	                            <cds-dropdown-item value="8">처리 완료</cds-dropdown-item>
	                        </cds-dropdown>
                        </div>
					</cds-table-cell>
					<cds-table-cell>${order.reg_date}</cds-table-cell>
				</cds-table-row>
			</c:forEach>
		</cds-table-body>
	</cds-table>
	</div>
</main>
<%@ include file="/views/merchandiser/fragments/footer.jsp" %>
</body>
</html>
