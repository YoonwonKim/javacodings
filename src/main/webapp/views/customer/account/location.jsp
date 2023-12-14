<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<head>
	<title>자바 코딩즈 배송지 관리</title>
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
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form.min.js"></script>
	<link rel="stylesheet" href="https://1.www.s81c.com/common/carbon-for-ibm-dotcom/tag/v1/latest/plex.css" />
	<link rel="stylesheet" href="//t1.daumcdn.net/postcode/cssjs/guide/1695281969613/guide.v2.min.css"/>
	<%-- Fragement CSS --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

	<%-- Page Script --%>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/resources/scripts/join.js"></script>
	<script src="/resources/scripts/zipCheck.js"></script>
	<script src="/resources/scripts/memberinfo.js"></script>
	<%-- Page Style --%>
	<link rel="stylesheet" href="/resources/styles/customer/account.css" />
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
		<cds-breadcrumb-item>배송지 관리</cds-breadcrumb-item>
	</cds-breadcrumb>

	<h1>배송지 관리</h1>

	<cds-layer leve="1">
	<div id="main-div">
		<div id="navigation">
			<a href="/account">계정 관리</a>
			<a href="/account/profile" >프로필 관리</a>
			<a href="/account/orders">주문 관리</a>
			<a href="/account/location" current>배송지 관리</a>
		</div>
		
		<div id="content">
			<cds-stack orientation="horizontal" gap="16px" use-custom-gap-value level="1">
				<cds-tile id="summary">
					<cds-stack gap="0" use-custom-gap-value>
						<h1>배송지 관리</h1>				
						<p class="subject">${ssKey.name}님 현재 배송지</p>
						<p>
							<input type="text" id="oldZipcode"class="d_form mini" value="${address.zipcode}" readonly>
						</p>
						<p>
							<input type="text" id="oldAddress" class="d_form large" value="${address.address}" readonly>
						</p>
						<p>
							<input type="text" id="oldAddress2" class="d_form" value="${address.address2}" readonly>
						</p>						
						
						<p class="subject">변경할 배송지</p>
						<p>
							<input type="text" id="sample6_postcode" name="zipcode" class="d_form mini" name="zipcode" placeholder="우편번호">
							<input type="button" onclick="zipCheck()" value="우편번호 찾기" class="d_btn">
						</p>
						<p>
							<input type="text" id="sample6_address" name="address" class="d_form large" name="address" placeholder="주소">
						</p>
						<p>
							<input type="text" id="sample6_detailAddress" name="address2" class="d_form" name="address2" placeholder="상세주소">
						</p>
						<p>
							<input type="hidden" id="member_id" name="member_id" value="${ssKey.member_id}" readonly>
							<input type="hidden" id="priority" name="priority" value="${address.priority}" readonly>
							<input type="hidden" id="newPriority" name="newPriority" value="${address.priority+1}" readonly>
						</p>
					</cds-stack>
				</cds-tile>			
			</cds-stack>
			<div class="right">			
				<cds-button kind="ghost" onclick="updateDeliveryAddress()">배송지 변경</cds-button>
			</div>
		</div>
	</div>
	</cds-layer>

</cds-layer>
</main>
<c:import url="../fragments/footer.jsp" />
</body>