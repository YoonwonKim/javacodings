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
	<script src="/resources/scripts/searchmember.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
	<div>
		<!-- 아이디 찾기 -->
		<form class="form-horizontal" role="form" method="POST">
			<div class="col-md-10 inputbb">
				<div class="form-group has-danger">
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">
						<h2>아이디 찾기</h2>
						<input type="text" name="name" class="form-control" id="name" placeholder="이름">
						<br>
						<input type="number" name="phone" class="form-control" id="phone" placeholder="연락처">							
					</div>
					<div class="button-container">
						<button action="actions/searchId" class="btn btn-outline-secondary">아이디 찾기</button>
					</div>
				</div>
			</div>
		</form>
		<!-- 비밀번호 변경 -->
		<form class="form-horizontal" role="form" method="POST">
			<div class="col-md-10 inputbb">
				<div class="col-md-12 h2m">
					<h2>비밀번호 변경</h2>
					<input type="text" name="member_id" class="form-control" id="member_id" placeholder="아이디">
					<br>
					<input type="number" name="phone" class="form-control" id="pwphone" placeholder="연락처">
					<br>
					<input type="password" name="password" class="form-control" id="password" placeholder="비밀번호">
					<br>
					<input type="password" name="password2" class="form-control" id="password2" placeholder="비밀번호 확인">
					<br>
				</div>
				<div class="button-container">
					<button action="actions/updatePasswd" class="btn btn-outline-secondary searchPasswdbtn">비밀번호 변경</button>
 					<!-- <button type="submit" class="btn btn-outline-secondary loginbtn" onclick="backToLogin()">로그인 화면으로 돌아가기</button> -->				
				</div>
			</div>
		</form>
	</div>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
