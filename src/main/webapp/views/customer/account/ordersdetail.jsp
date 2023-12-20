<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 상품 디테일 페이지</title>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>

	<%-- Page Scripts --%>
	<link rel="stylesheet" href="/resources/styles/customer/product-view.css" />
	<script src="/resources/scripts/memberinfo.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>

	<c:forEach var="orders" items="${orders}" varStatus="i">
		<c:if test="${memberOrders.items[i.index].category == 'thumbnail'}">
			<article>
				<img src="/resources/images/${memberOrders.items[i.index].path}" id="item-image">
				<div id="item-metadata" class="order" item-id="${memberOrders.items[i.index].item_id}">
					<div id="article">
						<h1 id="item-label">${memberOrders.items[i.index].label}</h1>
						<p id="item-desc">${memberOrders.items[i.index].desc}</p>
						<cds-stack id="item-price" gap="8px" use-custom-gap-value orientation="vertical">
							<label id="item-price-label">판매 가격</label>
							<div>
								<p id="item-price">${memberOrders.items[i.index].price}</p>
							</div>
						</cds-stack>
					</div>
					<div id="order-amount">
						<p>결제 금액</p>
						<p class="field">${orders.amount}</p>
					</div>
					<cds-stack orientation="horizontal" gap="8px"use-custom-gap-value>
						<label id="item-price-label">주문 상태</label>
						<div>
							<p id="item-price">
								<c:choose>
									<c:when test="${orders.state == 0}">
										주문 취소
									</c:when>
									<c:when test="${orders.state == 1}">
										결제 완료
									</c:when>
									<c:when test="${orders.state == 2}">
										주문 확인
									</c:when>
									<c:when test="${orders.state == 3}">
										배송 시작
									</c:when>
									<c:when test="${orders.state == 4}">
										배송 중
									</c:when>
									<c:when test="${orders.state == 5}">
										배송 완료
									</c:when>
									<c:when test="${orders.state == 6}">
										환불
									</c:when>
									<c:when test="${orders.state == 7}">
										반품
									</c:when>
									<c:when test="${orders.state == 8}">
										처리 완료
									</c:when>
								</c:choose>
							</p>
						</div>
						<cds-button id="cancleorder" kind="primary">주문 취소</cds-button> 
					</cds-stack>
				</div>
			</article>
		</c:if>
	</c:forEach>

</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>