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
			<%-- <cds-table-batch-actions>
				<cds-button
				>선택 상품 주문
					<svg
							focusable="false"
							preserveAspectRatio="xMidYMid meet"
							xmlns="http://www.w3.org/2000/svg"
							fill="currentColor"
							aria-hidden="true"
							width="16"
							height="16"
							viewBox="0 0 32 32"
							slot="icon">
						<path d="M12 12H14V24H12zM18 12H20V24H18z"></path>
						<path
								d="M4 6V8H6V28a2 2 0 002 2H24a2 2 0 002-2V8h2V6zM8 28V8H24V28zM12 2H20V4H12z"></path>
					</svg>
				</cds-button>
				 <cds-button
						tooltip-position="bottom"
						tooltip-text="Save"
						onclick="alertFunction()"
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
				 <cds-button href="javascript:void 0" download="table-data.json">
					Download
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
								d="M13 7L12.3 6.3 8.5 10.1 8.5 1 7.5 1 7.5 10.1 3.7 6.3 3 7 8 12zM13 12v2H3v-2H2v2l0 0c0 .6.4 1 1 1h10c.6 0 1-.4 1-1l0 0v-2H13z"></path>
					</svg>
				</cds-button> 
			</cds-table-batch-actions> --%>
		</cds-table-toolbar>
		<cds-table-head>
			<cds-table-header-row hide-checkbox>
				<cds-table-header-cell></cds-table-header-cell>
				<cds-table-header-cell>이미지</cds-table-header-cell>
				<cds-table-header-cell>상품 이름</cds-table-header-cell>
				<cds-table-header-cell>수량</cds-table-header-cell>
				<cds-table-header-cell>가격</cds-table-header-cell>
				<cds-table-header-cell>총 가격</cds-table-header-cell>
				<cds-table-header-cell></cds-table-header-cell>
			</cds-table-header-row>
		</cds-table-head>
		<cds-table-body id="totalPrice">
			<c:forEach var="cartList" items="${cartLists.cartLists}">
				<cds-table-row>
					<cds-table-cell><cds-checkbox id="checkItem"></cds-checkbox></cds-table-cell>
					<cds-table-cell>${cartList.image}</cds-table-cell>
					<cds-table-cell>${cartList.label}</cds-table-cell>
					<cds-table-cell>
						<cds-form-item>
							<cds-text-input type="text" value="${cartList.quantity}"></cds-text-input>
						</cds-form-item>
					</cds-table-cell>
					<cds-table-cell>${cartList.price}</cds-table-cell>
					<cds-table-cell>${cartList.price * cartList.quantity}</cds-table-cell>
				</cds-table-row>
			</c:forEach>
			<cds>선택 상품 총 가격 : totalPrice</cds>
		</cds-table-body>
		<cds-button id="updateCartButton">저장</cds-button>
		<cds-button id="deleteCartButton">삭제</cds-button>
	</cds-table>
	</div>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
