<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%-- Framework --%>
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<%-- Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.rtl.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form-group.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/text-input.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form-group.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/stack.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/select.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/date-picker.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/layer.min.js"></script>
	<%-- Fragement CSS --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

	<title>자바 코딩즈 회원가입</title>
	<link rel="stylesheet" href="/resources/css/account.css" />
	<%-- Page Script --%>
	<script src="/resources/scripts/common/policies/validates.js"></script>
	<script src="/resources/scripts/common/postcode.js"></script>
	<script src="/resources/scripts/customer/account.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
	<h1>회원가입</h1>
	<cds-layer level="1" id="register">
	<cds-stack gap="16px" use-custom-gap-value>
		<cds-text-input placeholder="이름" invalid-text="" class="input"
		                label="이름은 한글 30자 이하입니다." hide-label
						id="name" name="name" type="text"></cds-text-input>
		<cds-text-input placeholder="아이디" invalid-text="" class="input"
		                helper-text="아이디는 영문대소문자로 구성되고 4자 이상 16자 이하입니다"
		                id="member_id" name="member_id" type="text"></cds-text-input>
		<cds-stack orientation="horizontal">
			<cds-text-input placeholder="비밀번호" invalid-text="" class="input"
			                helper-text="비밀번호는 영문대소문자, 숫자, 특수기호로 구성되는 8자 이상 16자 이하로 구성됩니다"
			                id ="password" name="password" type="password" show-password-visibility-toggle></cds-text-input>
			<cds-text-input placeholder="비밀번호 확인" invalid-text="" class="input"
			                label="비밀번호가 일치하지 않습니다." hide-label
							id="password-repeat" name="password-repeat" type="password" show-password-visibility-toggle></cds-text-input>
		</cds-stack>
		<cds-stack orientation="horizontal" gap="0" use-custom-gap-value>
			<cds-text-input placeholder="이메일" invalid-text=""
			                id="email" class="input"
			                name="email" type="text"></cds-text-input>
			<cds-text-input invalid-text="" value="@"
			                id="email-domain" class="input"
			                label="유효하지 않은 주소입니다" hide-label
			                name="email-domain" type="text"></cds-text-input>
			<cds-select hide-label placeholder="직접 입력"
			            id="email-prefix" name="email-prefix">
				<cds-select-item value="@naver.com">naver.com</cds-select-item>
				<cds-select-item value="@gmail.com">gmail.com</cds-select-item>
				<cds-select-item value="@skiff.com">skiff.com</cds-select-item>
			</cds-select>
		</cds-stack>
		<cds-stack orientation="horizontal" gap="0" use-custom-gap-value>
			<cds-select hide-label placeholder="통신사" name="telecom"
			            id="telecom" class="input">
				<cds-select-item value="lg">LG</cds-select-item>
				<cds-select-item value="kt">KT</cds-select-item>
				<cds-select-item value="skt">SKT</cds-select-item>
				<cds-select-item value="mvno">알뜰폰</cds-select-item>
			</cds-select>
			<cds-text-input invalid-text="" value="" max-count="8"
			                placeholder="전화번호"
			                id="phone" class="input"
			                helper-text="010을 제외한 숫자를 입력해주세요"
			                name="phone" type="phone"></cds-text-input>
		</cds-stack>
		<cds-date-picker date-format="Y/m/d">
			<cds-date-picker-input kind="single"
			                       placeholder="생년월일" hide-label
			                       id="birth" name="birth" class="input"></cds-date-picker-input>
		</cds-date-picker>
		<%--** Address **--%>
		<h4>주소지</h4>
		<cds-stack orientation="horizontal" gap="16px" use-custom-gap-value>
			<cds-text-input placeholder="우편번호" invalid-text="" readonly class="input"
							id="zipcode" name="zipcode" type="text"></cds-text-input>
			<cds-button type="button" kind="tertiary"
			            size="md" onclick="postcode()">우편번호 찾기</cds-button>
		</cds-stack>
		<cds-text-input placeholder="주소" invalid-text="" readonly class="input"
						id="address" name="address" type="text"></cds-text-input>
		<cds-text-input placeholder="상세 주소" invalid-text="" class="input"
						id="address2" name="address2" type="text"></cds-text-input>
		<%--** Controller **--%>
		<cds-button type="button" onclick="register()">회원가입</cds-button>
		<cds-stack orientation="horizontal">
			<a href="/account/login">로그인</a>
			<a href="/account/search">아이디 및 비밀번호 찾기</a>
		</cds-stack>
	</cds-stack>
	</cds-layer>
</main>

<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>