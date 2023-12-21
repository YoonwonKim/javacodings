<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 상품 디테일 페이지</title>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>

	<%-- Page Scripts --%>
	<link rel="stylesheet" href="/resources/styles/customer/order/view.css" />
	<script src="/resources/scripts/memberinfo.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>
	<div id="order-data">
		<p id="order-id">${order_id}</p>
		<p id="order-amount" value="${order.amount}">${order.amount} 원</p>
		<p id="state">
			<c:choose>
				<c:when test="${order.state == 0}">결제 필요</c:when>
				<c:when test="${order.state == 1}">결제 완료</c:when>
				<c:when test="${order.state == 2}">주문 확인</c:when>
				<c:when test="${order.state == 3}">배송 시작</c:when>
				<c:when test="${order.state == 4}">배송 진행</c:when>
				<c:when test="${order.state == 5}">배송 완료</c:when>
				<c:when test="${order.state == 6}">환불 완료</c:when>
				<c:when test="${order.state == 7}">반품 완료</c:when>
				<c:when test="${order.state == 8}">처리 완료</c:when>
			</c:choose>
		</p>
		<div id="button-group">
			<c:choose>
			<c:when test="${order.state == 0}">
				<cds-button id="purchase" kind="primary" href="/order/purchase/${order_id}">결제</cds-button>
				<cds-button id="cancel"   kind="secondary">주문 취소</cds-button>
			</c:when>
			<c:otherwise>
				<cds-button id="cancel" kind="primary">주문 취소</cds-button>
			</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div id="order-items">
	<c:forEach var="item" items="${itemList}">
	<a class="item" href="/item/${item.item_id}">
		<img src="/resources/images/${item.path}" id="thumbnail">
		<data class="orderable" id="${item.item_id}">
			<data id="label">${item.label}</data>
			<data id="desc">${item.desc}</data>
			<data id="amount" value="${item.amount}">${item.amount} 원</data>
		</data>
	</a>
	</c:forEach>
	</div>
</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>