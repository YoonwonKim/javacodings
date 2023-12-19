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

	<article item-id="${item.item_id}">
		<img src="/resources/images/${item.path}" id="item-image">

		<data class="orderable" id="${item.item_id}">
			<h1>${item.label}</h1>
			<p>${item.desc}</p>

			<label for="amount">총 금액</label>
			<data id="amount" value="${item.price}"></data>
			<data id="price" value="${item.price}"></data>

			<cds-number-input id="quantity"
					value="1" min="1" max="${item.stock}">
			</cds-number-input>

			<cds-stack orientation="horizontal" gap="8px" use-custom-gap-value>
				<cds-button id="request-order" kind="primary">구매하기</cds-button>
				<cds-button id="put-cart" kind="secondary">장바구니</cds-button>
			</cds-stack>
		</data>
	</article>

</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>