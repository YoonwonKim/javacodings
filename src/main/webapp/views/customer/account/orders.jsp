<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<head>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>
	<title>자바 코딩즈 주문 관리</title>

	<%-- Page Script --%>
	<script src="/resources/scripts/register.js"></script>
	<script src="/resources/scripts/zipCheck.js"></script>
	<%-- Page Style --%>
	<link rel="stylesheet" href="/resources/styles/customer/account.css" />
</head>
<body>
<c:import url="../fragments/global/header.jsp" />
<main>
<cds-layer>

	<cds-breadcrumb no-trailing-slash>
		<cds-breadcrumb-item>
			<cds-breadcrumb-link href="/">홈</cds-breadcrumb-link>
		</cds-breadcrumb-item>
		<cds-breadcrumb-item>
			<cds-breadcrumb-link href="/account">계정 정보</cds-breadcrumb-link>
		</cds-breadcrumb-item>
		<cds-breadcrumb-item>주문 관리</cds-breadcrumb-item>
	</cds-breadcrumb>

	<h1>주문 관리</h1>

	<cds-layer leve="1">
	<div id="main-div">
		<div id="navigation">
			<a href="/account">계정 관리</a>
			<a href="/account/profile" >프로필 관리</a>
			<a href="/account/orders" current>주문 관리</a>
			<a href="/account/location">배송지 관리</a>
		</div>

		<div id="content">
			<cds-stack orientation="horizontal" gap="16px" use-custom-gap-value level="1">
				<cds-tile id="summary">
					<cds-stack gap="0" use-custom-gap-value>
						<c:if test="${not empty countMemberOrders}">
							<cds-table>
								<cds-table-header-title slot="title">주문 관리</cds-table-header-title>
								<cds-table-head>								
								<cds-table-header-row>
									<cds-table-header-cell></cds-table-header-cell>
									<cds-table-header-cell>상품 이름</cds-table-header-cell>
									<cds-table-header-cell>상품 이미지</cds-table-header-cell>
									<cds-table-header-cell>상품 가격</cds-table-header-cell>
									<cds-table-header-cell>상품 수량</cds-table-header-cell>
									<cds-table-header-cell>결제 금액</cds-table-header-cell>
									<cds-table-header-cell>결제일</cds-table-header-cell>
									<cds-table-header-cell>주문 상태</cds-table-header-cell>
								</cds-table-header-row>
								</cds-table-head>
								<cds-table-body>
									<c:forEach var="orderItem" items="${memberOrders.memberOrderItems}" varStatus="i">
										<cds-table-row onclick="location.href='/account/orders/${memberOrders.memberOrderOrders[i.index].order_id}'">
											<cds-table-cell> </cds-table-cell>
											<cds-table-cell><img src="/resources/images/${orderItem.path}"></cds-table-cell>
											<cds-table-cell>${orderItem.label}</cds-table-cell>
											<cds-table-cell>${orderItem.price}</cds-table-cell>
											<cds-table-cell>${memberOrders.memberOrderOrders[i.index].amount/orderItem.price}</cds-table-cell>
										 	<cds-table-cell>${memberOrders.memberOrderOrders[i.index].amount}</cds-table-cell>
											<cds-table-cell>${memberOrders.memberOrderOrders[i.index].reg_date}</cds-table-cell>
											<cds-table-cell>
												<c:choose>
													<c:when test="${memberOrders.memberOrderOrders[i.index].state == 0}">
														결제 필요
													</c:when>
													<c:when test="${memberOrders.memberOrderOrders[i.index].state == 1}">
														결제 완료
													</c:when>
													<c:when test="${memberOrders.memberOrderOrders[i.index].state == 2}">
														주문 확인
													</c:when>
													<c:when test="${memberOrders.memberOrderOrders[i.index].state == 3}">
														배송 시작
													</c:when>
													<c:when test="${memberOrders.memberOrderOrders[i.index].state == 4}">
														배송 중
													</c:when>
													<c:when test="${memberOrders.memberOrderOrders[i.index].state == 5}">
														배송 완료
													</c:when>
													<c:when test="${memberOrders.memberOrderOrders[i.index].state == 6}">
														환불
													</c:when>
													<c:when test="${memberOrders.memberOrderOrders[i.index].state == 7}">
														반품
													</c:when>
													<c:when test="${memberOrders.memberOrderOrders[i.index].state == 8}">
														처리 완료
													</c:when>
												</c:choose>
											</cds-table-cell>
										</cds-table-row>
									</c:forEach>
								</cds-table-body>
							</cds-table>
						</c:if>
					</cds-stack>
				</cds-tile>			
			</cds-stack>
		</div>
		</div>
	</div>
	</cds-layer>

</cds-layer>
</main>
<c:import url="../fragments/global/footer.jsp" />
</body>