<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
	<!-- Javascript API and Frameworks -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.rtl.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form-group.min.js"></script>

	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

	<title>자바코딩즈</title>
	<link rel="stylesheet" href="/resources/css/landing.css" />
	<script src="/resources/scripts/landing.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
	<div>
	<form legend-text="로그인" action="/actions/login" method="post">
		<ul id="lage_login_input"><li><input type="text" name="member_id" id="id"  title="아이디" class="chkt"></li>
			<li><input type="password" name="password" id="pw" title="비밀번호" class="chkt"></li>
		</ul>
		<div id="submitTop">
		<button type="submit"> 로그인 </button>
		</div>
	</form>
	</div>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
