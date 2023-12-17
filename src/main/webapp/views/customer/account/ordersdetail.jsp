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

	<c:forEach var="order" items="${memberOrders.memberOrderOrders}">
    <c:if test="${order.order_id == param.order_id}">
        <article>
            <!-- 특정 order_id와 관련된 주문 정보를 표시합니다. -->
            <c:forEach var="item" items="${memberOrders.memberOrderItems}">
                <c:if test="${item.item_id == order.itemList[0].item_id}">
                    <img src="/resources/images/${item.path}" id="item-image">
                    <div id="item-metadata" class="order" item-id="${item.item_id}">
                        <div id="article">
                            <h1 id="item-label">${item.label}</h1>
                            <p id="item-desc">${item.desc}</p>
                            <cds-stack id="item-price" gap="8px" use-custom-gap-value orientation="vertical">
                                <label id="item-price-label">판매 가격</label>
                                <div>
                                    <p id="item-price">${item.price}</p>
                                    <p id="item-event"></p>
                                </div>
                            </cds-stack>
                            <div id="order">
                                <cds-layer level="1">
                                    <cds-stack orientation="vertical">
                                        <label>주문 수량</label>
                                        <cds-number-input value="${order.amount}" min="1" max="${order.amount}" id="order-quantity" inline></cds-number-input>
                                    </cds-stack>
                                </cds-layer>
                            </div>
                        </div>
                        <div id="order-amount">
                            <p>총 금액</p>
                            <p class="field" value="${order.amount * item.price}" price="${order.amount * item.price}">
                                ${order.amount * item.price}
                            </p>
                        </div>
                        <cds-stack orientation="horizontal" gap="8px" use-custom-gap-value>
                            <cds-button id="" kind="primary">주문 취소</cds-button>
                        </cds-stack>
                    </div>
                </c:if>
            </c:forEach>
        </article>
    </c:if>
</c:forEach>


</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>