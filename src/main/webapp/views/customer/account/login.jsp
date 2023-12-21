<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>
	<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

	<title>자바코딩즈</title>
	<link rel="stylesheet" href="/resources/styles/customer/account.css" />
	<%-- Page Scripts --%>
	<script src="/resources/scripts/common/policies/validates.js"></script>
	<script src="/resources/scripts/customer/account.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>
	<div id="login-box">
		<h1 class="login_text">LOGIN</h1>
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
<%--				<a href="/account/find">아이디 및 비밀번호 찾기</a>--%>
			</cds-stack>
		</cds-stack>
		</cds-layer>
	</div>
</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>
