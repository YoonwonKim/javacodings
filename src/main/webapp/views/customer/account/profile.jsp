<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<head>
	<title>자바 코딩즈 프로필 관리</title>
	<%-- Framework --%>
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<%-- Web Component --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/date-picker.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/layer.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/breadcrumb.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/tile.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/stack.min.js"></script>
	<link rel="stylesheet" href="https://1.www.s81c.com/common/carbon-for-ibm-dotcom/tag/v1/latest/plex.css" />
	<%-- Fragement CSS --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

	<%-- Page Script --%>
	<script src="/resources/scripts/register.js"></script>
	<script src="/resources/scripts/zipCheck.js"></script>
	<%-- Page Style --%>
	<link rel="stylesheet" href="/resources/css/customer/account.css" />
</head>
<body>
<c:import url="../fragments/header.jsp" />
<main>
<cds-layer>

	<cds-breadcrumb no-trailing-slash>
		<cds-breadcrumb-item>
			<cds-breadcrumb-link href="/">홈</cds-breadcrumb-link>
		</cds-breadcrumb-item>
		<cds-breadcrumb-item>
			<cds-breadcrumb-link href="/account">계정 정보</cds-breadcrumb-link>
		</cds-breadcrumb-item>
		<cds-breadcrumb-item>프로필 관리</cds-breadcrumb-item>
	</cds-breadcrumb>

	<h1>프로필 관리</h1>

	<cds-layer leve="1">
	<div id="main-div">
		<div id="navigation">
			<a href="/account">계정 관리</a>
			<a href="/account/profile" current>프로필 관리</a>
			<a href="/account/orders">주문 관리</a>
			<a href="/account/location">배송지 관리</a>
		</div>

		<div id="content">

			<cds-stack orientation="horizontal" gap="16px" use-custom-gap-value level="1">
				<cds-clickable-tile id="summary" href="/account/profile">
					<cds-stack gap="0" use-custom-gap-value>
						<h1>${ssKey.name} 님</h1>
						<p>회원 아이디: ${ssKey.member_id}</p>
						<p>이메일 : ${ssKey.email}</p>
						<p>연락처 : 010-${ssKey.phone.substring(0, 4)}-${ssKey.phone.substring(4,8)}</p>
						<p class="link">프로필 수정</p>
					</cds-stack>
				</cds-clickable-tile>

				<cds-clickable-tile href="/account/location">
					<cds-stack gap="0" use-custom-gap-value>
						<h1>배송지 관리</h1>
						<p>${address.zipcode}</p>
						<p>${address.address} ${address.address2}</p>
						<p class="link">배송지 변경</p>
					</cds-stack>
				</cds-clickable-tile>
			</cds-stack>

			<cds-tile href="/account/orders" id="orders">
			<cds-stack gap="0" use-custom-gap-value orientation="vertical">
				<h1>주문 관리</h1>
				<div id="order-summary">
					<cds-clickable-tile inline href="/account/orders?paid">
						<c:import url="/resources/css/icons/price.svg" />
						<cds-stack>
							<p>결제 완료</p>
						</cds-stack>
					</cds-clickable-tile>
				</div>
			</cds-stack>
			</cds-tile>

			<div class="right">
				<cds-button kind="ghost">회원 탈퇴</cds-button>
			</div>
		</div>
	</div>
	</cds-layer>

</cds-layer>
</main>
<c:import url="../fragments/footer.jsp" />
</body>