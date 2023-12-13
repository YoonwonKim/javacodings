<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 장바구니</title>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<%-- Page Script and Styles --%>
	<script type="module" src="/resources/scripts/cart.js"></script>
	<link rel="stylesheet" href="/resources/styles/customer/cart.css" />
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>
	<div id="table">
		<cds-layer level="1">
		<cds-tile-group>
		<cds-stack gap="8px" use-custom-gap-value>
			<c:forEach var="item" items="${objectList}">
				<cds-selectable-tile item-id="${item.item_id}" class="order" selected>
				<div id="item-div">
					<img src="/resources/images/${item.path}">
					<div id="item-desc">
						<h1>${item.label}</h1>
						<p>${item.desc}</p>
						<cds-number-input class="cart-quantity" name="cart-quantity"
										  value="${item.amount/item.price}" min="1" max="${item.stock}"
										  inline ></cds-number-input>
						<div id="order-amount">
							<p class="field" price="${item.price}" value="${item.amount}">${item.amount} 원</p>
						</div>
						<div class="button-group">
							<cds-button kind="secondary" class="request-single"
							            item-id="${item.item_id}">구매하기</cds-button>
							<cds-button kind="ghost" onclick="deleteOne('${item.item_id}')">삭제하기</cds-button>
						</div>
					</div>
				</div>
				</cds-selectable-tile>
			</c:forEach>
		</cds-stack>
		</cds-tile-group>
		</cds-layer>
	</div>

	<cds-layer level="1" id="order">
	<cds-tile>
		<cds-stack gap="16px" use-custom-gap-value>
			<h1>결제 정보</h1>
			<div id="order-summary">
				<table>
					<tr id="order-summary-quantity"><td>상품수</td><td class="value">0 개</td></tr>
					<tr id="order-summary-price"><td>상품금액</td><td class="value">0 원</td></tr>
					<tr id="order-summary-discount"><td>할인금액</td><td class="value">-0 원</td></tr>
					<tr id="order-summary-delivery"><td>배송비</td><td class="value">0 원</td></tr>
				</table>
			</div>
			<div id="order-summary-total">
				<p>총 결제 금액</p>
				<p class="value">0 원</p>
			</div>
			<cds-button id="request-selected">구매하기</cds-button>
		</cds-stack>
	</cds-tile>
	<cds-button kind="ghost" onclick="deleteSelected()">선택 삭제하기</cds-button>
	</cds-layer>

<%--	<div id="button-group">--%>
<%--		<cds-button kind="ghost" onclick="deleteAll()">전체 삭제하기</cds-button>--%>
<%--	</div>--%>
</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>
