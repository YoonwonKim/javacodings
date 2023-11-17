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
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/tile.min.js"></script>
	<%-- Fragements --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

	<title>자바코딩즈</title>
	<link rel="stylesheet" href="/resources/css/account.css" />
	<%-- Page Scripts --%>
	<script src="/resources/scripts/common/policies/validates.js"></script>
	<script src="/resources/scripts/customer/account.js"></script>
	<script src="/resources/scripts/customer/search.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
<cds-layer level="1" id="search">
	<cds-stack gap="16px" use-custom-gap-value orientation="vertical">
		<h2 class="search_text">아이디 찾기<br>임시 비밀번호 발행</h2>
		<cds-stack gap="0" use-custom-gap-value>
			<cds-text-input placeholder="이름" invalid-text=""
			                label="이름은 30자 이하의 한글만 입력하실 수 있습니다" hide-label
							id="name" name="name" type="text"></cds-text-input>
			<cds-text-input placeholder="이메일" invalid-text=""
							id="email" name="email" type="email"></cds-text-input>
		</cds-stack>
		<cds-stack gap="8px" use-custom-gap-value orientation="horizontal">
			<cds-button link-role="a" href="/account/login"
						kind="secondary">로그인 하기</cds-button>
			<cds-button type="button" onclick="search()">아이디 찾기</cds-button>
			<cds-button type="button" onclick="tempPassword()">임시 비밀번호 발행</cds-button>
		</cds-stack>
	</cds-stack>
</cds-layer>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
