<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<head>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>
	<link rel="stylesheet" href="//t1.daumcdn.net/postcode/cssjs/guide/1695281969613/guide.v2.min.css"/>

	<title>자바 코딩즈 계정 관리</title>

	<script src="/resources/scripts/register.js"></script>
	<script src="/resources/scripts/zipCheck.js"></script>
	<script src="/resources/scripts/memberinfo.js"></script>
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
		<cds-breadcrumb-item>계정 정보</cds-breadcrumb-item>
	</cds-breadcrumb>

	<h1>계정 정보</h1>

	<cds-layer leve="1">
	<div id="main-div">
		<div id="navigation">
			<a href="/account" current>계정 관리</a>
			<a href="/account/profile" >프로필 관리</a>
			<a href="/account/orders">주문 관리</a>
			<a href="/account/location">배송지 관리</a>
		</div>

		<div id="content">

			<cds-stack orientation="horizontal" gap="16px" use-custom-gap-value level="1">
				<cds-clickable-tile id="summary" href="/account/profile">
					<cds-stack gap="0" use-custom-gap-value>
						<h1>${ssKey.name} 님</h1>
						<p>회원 아이디: ${ssKey.member_id}</p>
						<p>이름: ${ssKey.name}</p>
						<p>생년월일: ${ssKey.birth}</p>
						<p>이메일 : ${ssKey.email}</p>
						<p>연락처 : 010-${ssKey.phone.substring(0, 4)}-${ssKey.phone.substring(4,ssKey.phone.length())}</p>
						<p class="link">프로필 수정</p>
					</cds-stack>
				</cds-clickable-tile>

				<cds-clickable-tile href="/account/location">
					<cds-stack gap="0" use-custom-gap-value>
						<h1>배송지 관리</h1>
						<p>우편번호: ${address.zipcode}</p>
						<p>주소: ${address.address} ${address.address2}</p>
					</cds-stack>
				</cds-clickable-tile>
			</cds-stack>

<%--			<cds-tile href="/account/orders" id="orders">--%>
<%--			<cds-stack gap="0" use-custom-gap-value orientation="vertical">--%>
<%--				<h1>주문 관리</h1>--%>
<%--				<div id="order-summary">--%>
<%--					<cds-clickable-tile inline href="/account/orders?paid">--%>
<%--						<c:import url="/resources/css/icons/price.svg" />--%>
<%--						<cds-stack>--%>
<%--							<p>결제 완료</p>--%>
<%--						</cds-stack>--%>
<%--					</cds-clickable-tile>--%>
<%--				</div>--%>
<%--			</cds-stack>--%>
<%--			</cds-tile>--%>

<%--			<div class="right">--%>
<%--				<cds-button kind="ghost" a href="/account/profile">회원 탈퇴</cds-button>--%>
<%--			</div>--%>
		</div>
	</div>
	</cds-layer>

</cds-layer>
</main>
<c:import url="../fragments/global/footer.jsp" />
</body>