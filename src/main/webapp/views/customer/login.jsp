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
	<link rel="stylesheet" href="/views/customer/login.css" />

	<title>자바코딩즈</title>
	<link rel="stylesheet" href="/resources/css/landing.css" />
	<script src="/resources/scripts/landing.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
	<div class="loginform">
	<h1 class="test"><span class="entypo-login"><i class="fa fa-sign-in"></i></span>LOGIN</h1>
	<form legend-text="로그인" action="/actions/login" method="post" class="loginform_text">
		<ul id="lage_login_input"><li><input type="text" name="member_id"  title="아이디" class="user" placeholder="user_id">
		<span class="entypo-user inputUserIcon">
			<i class="fa fa-user"></i>
		</span></li>
			<li><input type="password" name="password" class="pass" title="비밀번호" placeholder="password">
			 <span class="entypo-key inputPassIcon">
   			  <i class="fa fa-key"></i>
			</span></li>
		</ul>
		<button type="submit"><span class="entypo-lock"><i class="fa fa-lock">로그인</i></span></button>
	<div class="col1"><a href="#">회원가입</a></div>
	<div class="col2"><a href="#">아이디/비밀번호 찾기</a></div>
	</form>
	</div>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
