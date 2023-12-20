<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 장바구니</title>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>

	<%-- Page Script and Styles --%>
	<script type="module" src="/resources/scripts/cart.js"></script>
	<link rel="stylesheet" href="/resources/styles/customer/cart.css" />
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>
	<div id="table">
		<cds-tile-group>
		<cds-stack gap="8px" use-custom-gap-value>
			<c:forEach var="item" items="${objectList}">
				<cds-selectable-tile id="${item.item_id}" class="order" selected>
				<div id="row">
					<img src="/resources/images/${item.path}">
					<data class="orderable" id="${item.item_id}">
						<div id="desc">
							<h1>${item.label}</h1>
							<p>${item.desc}</p>
							<data id="price" value="${item.price}"></data>
							<cds-number-input id="quantity" inline size="sm"
							                  value="${item.amount/item.price}" min="1" max="${item.stock}"></cds-number-input>
						</div>

						<data id="amount" value="${item.amount}"></data>
					</data>
				</div>
				</cds-selectable-tile>
			</c:forEach>
		</cds-stack>
		</cds-tile-group>
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
			<div>
				<cds-button id="order">구매하기</cds-button>
				<cds-button kind="ghost" id="delete">삭제하기</cds-button>
			</div>
		</cds-stack>
	</cds-tile>
	</cds-layer>

<%--	<div id="button-group">--%>
<%--		<cds-button kind="ghost" onclick="deleteAll()">전체 삭제하기</cds-button>--%>
<%--	</div>--%>
</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>
