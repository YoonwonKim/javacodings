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
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form.min.js"></script>
	<link rel="stylesheet" href="https://1.www.s81c.com/common/carbon-for-ibm-dotcom/tag/v1/latest/plex.css" />
	<%-- Fragement CSS --%>
	<link rel="stylesheet" href="/views/customer/fragments/global/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/global/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/global/footer.css" />

	<%-- Page Script --%>
	<script src="/resources/scripts/register.js"></script>
	<script src="/resources/scripts/zipCheck.js"></script>
	<script src="/resources/scripts/memberinfo.js"></script>
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
				<cds-tile id="summary">
					<cds-stack gap="0" use-custom-gap-value>					
						<h1>${ssKey.name} 님</h1>
						<p class="subject">회원 아이디</p>
						<p>
							<input id="member_id" style="background-color: transparent; border: none; border-bottom: 1px solid #000;"
								value="${ssKey.member_id}" readonly>
						</p>
						<p class="subject">이름</p>
						<p>
							<input id="name" style="background-color: transparent; border: none; border-bottom: 1px solid #000;"
								value="${ssKey.name}">
						</p>
						<p class="subject">생년월일</p>
						<p>
							<input id="birth" style="background-color: transparent; border: none; border-bottom: 1px solid #000;"
								value="${ssKey.birth}" readonly>
						</p>
						<p class="subject">이메일</p>
						<p>
							<cds-stack orientation="horizontal" gap="5px" use-custom-gap-value>
								<input style="background-color: transparent; border: none; border-bottom: 1px solid #000;"
									id="emailPrefix" value="${ssKey.email.substring(0, ssKey.email.indexOf('@'))}" required>
								@
								<input style="background-color: transparent; border: none; border-bottom: 1px solid #000;" list="user_email_address"
									id="emailDomain" value="${ssKey.email.substring(ssKey.email.indexOf('@') + 1)}">
								<datalist id="user_email_address" >
									<option value="naver.com"></option>
									<option value="daum.net"></option>
									<option value="gmail.com"></option>
								</datalist>
							</cds-stack>
						</p>
						<p class="subject">연락처</p>
						<p>
							<select name="telecom" style="background-color: transparent; border: none; border-bottom: 1px solid #000; required">
								<option>LG</option>
								<option>SKT</option>
								<option>KT</option>
								<option>알뜰폰</option>
							</select>
							<input style="background-color: transparent; border: none; border-bottom: 1px solid #000; text-align: center;"
								value="010" readonly>
							<input id="phone1"style="background-color: transparent; border: none; border-bottom: 1px solid #000; text-align: center;"
								value="${ssKey.phone.substring(0,4)}" required>
							<input id="phone2"style="background-color: transparent; border: none; border-bottom: 1px solid #000; text-align: center;"
								value="${ssKey.phone.substring(4,8)}" required>
						</p>
					</cds-stack>
				</cds-tile>			
			</cds-stack>
			<div class="right">			
				<cds-button kind="ghost" onclick="archiveByMemberId()">회원 탈퇴</cds-button>
				<cds-button kind="ghost" onclick="editMemberInfoByMemberId()">프로필 수정</cds-button>
			</div>
		</div>
	</div>
	</cds-layer>

</cds-layer>
</main>
<c:import url="../fragments/global/footer.jsp" />
</body>