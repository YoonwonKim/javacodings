<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<!-- Javascript API and Frameworks -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	<%-- Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.rtl.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form-group.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/text-input.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form-group.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/stack.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/layer.min.js"></script>
	<%-- Fragements --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />
	<link rel="stylesheet" href="/resources/css/account.css" />
<%--	<link rel="stylesheet" href="/views/customer/login.css" />--%>

	<title>자바코딩즈</title>
	<link rel="stylesheet" href="/resources/css/landing.css" />
	<script src="/resources/scripts/landing.js"></script>
	<%-- Page Scripts --%>
	<script src="/resources/scripts/common/policies/validates.js"></script>
	<script src="/resources/scripts/customer/account.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
	<div class="loginform">
		<h1 class="login_text">LOGIN</h1>

		<form method="post" action="/actions/account/login">
		<cds-layer level="1" id="login">
		<cds-stack gap="16px" use-custom-gap-value>
			<cds-stack gap="0" use-custom-gap-value>
				<cds-text-input placeholder="아이디" invalid-text=""
								name="member_id" type="text"></cds-text-input>
				<cds-text-input placeholder="비밀번호" invalid-text=""
								name="password" type="password" show-password-visibility-toggle></cds-text-input>
			</cds-stack>
			<cds-button type="button" onclick="account()">로그인</cds-button>
			<cds-stack orientation="horizontal">
				<a href="/account/register">회원가입</a>
				<a href="/account/search">아이디 및 비밀번호 찾기</a>
			</cds-stack>
		</cds-stack>
		</cds-layer>
		</form>
	</div>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
