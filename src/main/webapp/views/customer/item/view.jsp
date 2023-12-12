<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 상품 디테일 페이지</title>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>

	<%-- Page Scripts --%>
	<script type="module" src="/resources/scripts/customer/product-view.js"></script>
	<link rel="stylesheet" href="/resources/styles/customer/product-view.css" />
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>

	<article>
		<img src="/resources/images/${item.path}" id="item-image">
		<div id="item-metadata">
			<div id="article">
				<h1 id="item-label">${item.label}</h1>
				<p  id="item-desc" >${item.desc}</p>
				<cds-stack id="item-price"
				           gap="8px" use-custom-gap-value orientation="vertical">
					<label id="item-price-label">판매 가격</label>
					<div>
						<p id="item-price-value">${item.price}</p>
						<p id="item-event"></p>
					</div>
				</cds-stack>
				<div id="order">
					<cds-layer level="1">
					<cds-stack orientation="vertical">
						<label>주문 수량</label>
						<cds-number-input
								value="1" min="1" max="${item.stock}"
								id="order-quantity" inline>
						</cds-number-input>
					</cds-stack>
					</cds-layer>
				</div>
			</div>
			<div>
				<p>총 금액</p>
				<p id="order-price"></p>
			</div>
			<cds-stack orientation="horizontal" gap="8px" use-custom-gap-value>
				<cds-button onclick="order(1)" kind="primary">구매하기</cds-button>
				<cds-button id="cart" kind="secondary">장바구니</cds-button>
			</cds-stack>
		</div>
	</article>

</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>