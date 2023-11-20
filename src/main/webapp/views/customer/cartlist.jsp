<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 장바구니</title>
	<link rel="stylesheet" href="/resources/css/manage_products.css" />
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="/resources/scripts/cartlist.js"></script>

	<%--  Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/checkbox.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/text-input.min.js"></script>
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
		<cds-table-header-title slot="title">장바구니</cds-table-header-title>
		<cds-table-header-description slot="description">
			고객 장바구니입니다.
		</cds-table-header-description>
		<cds-table-toolbar slot="toolbar">
			<cds-table-toolbar-content>
				<cds-table-toolbar-search
						placeholder="Filter table"></cds-table-toolbar-search>
				<cds-button id="orderButton" onclick="location.href='/order'">전체 상품 주문</cds-button>
			</cds-table-toolbar-content>
			<cds-table-batch-actions>
				<cds-button
						tooltip-position="bottom"
						tooltip-text="선택 상품 주문"
						onclick="location.href='/order'"
				>선택 상품 주문
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
					</svg>
				</cds-button>				 
			</cds-table-batch-actions> 
		</cds-table-toolbar>
		<cds-table-head>
			<cds-table-header-row hide-checkbox>
				<cds-table-header-cell></cds-table-header-cell>
				<cds-table-header-cell>이미지</cds-table-header-cell>
				<cds-table-header-cell>상품 이름</cds-table-header-cell>
				<cds-table-header-cell>수량</cds-table-header-cell>
				<cds-table-header-cell>가격</cds-table-header-cell>
				<cds-table-header-cell>총 가격</cds-table-header-cell>
			</cds-table-header-row>
		</cds-table-head>
		<cds-table-body id="updateTotalPrice">
			<c:forEach var="cartList" items="${cartLists.cartLists}">
				<cds-table-row>
					<cds-table-cell><cds-checkbox value="${cartList.order_id}"></cds-checkbox></cds-table-cell>
					<cds-table-cell>${cartList.image}</cds-table-cell>
					<cds-table-cell>${cartList.label}</cds-table-cell>
					<cds-table-cell>
						<cds-form-item>
							<cds-text-input type="text" name="quantity" value="${cartList.quantity}"></cds-text-input>
						</cds-form-item>
					</cds-table-cell>
					<cds-table-cell name="price">${cartList.price}</cds-table-cell>
					<cds-table-cell name="totPrice">${cartList.price * cartList.quantity}</cds-table-cell>
					<cds-table-cell="hidden" name="order_id" value="${cartList.order_id}"></cds-table-cell>
				</cds-table-row>
			</c:forEach>
			<cds id="total"></cds> <br>
			<cds id="checkedTotal">선택 상품 가격 : </cds>
		</cds-table-body>
		<cds-button class="updateCart">저장</cds-button>
		<cds-button onclick="delete_item()">삭제</cds-button>
	</cds-table>
	</div>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
