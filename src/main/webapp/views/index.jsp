<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<link rel="stylesheet" href="/views/fragments/init.css" />
	<link rel="stylesheet" href="/views/fragments/header.css" />
	<link rel="stylesheet" href="/views/fragments/footer.css" />

	<title>자바코딩즈</title>
	<link rel="stylesheet" href="/resources/css/landing.css" />
</head>
<body>
<%@ include file="/views/fragments/header.jsp" %>
<main>
	<div id="banner">
		<ul>
			<li no="1" class="selected"></li>
			<li no="2"></li>
			<li no="3"></li>
			<li no="4"></li>
		</ul>
	</div>

	<div id="events">
		<div class="card">
		<c:forEach items="${banners}" var="banner">
			<p class="tag">${banners.item_id}</p>
			<h1>${banners.label}</h1>
			<p>${banners.desc}</p>
		</c:forEach>
		</div>
	</div>

	<div id="md-list">
		<div class="layer">
			<h1>태그 라벨입니다.</h1>
			<p>태그 설명입니다. 다소 깁니다.</p>
			<ul>
				<li class="card">
					<img src="/resources/Grey.png">
					<h1>상품 라벨입니다.</h1>
					<p>140,000원</p>
				</li>
			</ul>
		</div>
	</div>
</main>
<%@ include file="/views/fragments/footer.jsp" %>
</body>
</html>
