<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<!-- Javascript API and Frameworks -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

	<link rel="stylesheet" href="/views/fragments/init.css" />
	<link rel="stylesheet" href="/views/fragments/header.css" />
	<link rel="stylesheet" href="/views/fragments/footer.css" />

	<title>자바코딩즈</title>
	<link rel="stylesheet" href="/resources/css/landing.css" />
	<script src="/resources/scripts/landing.js"></script>
</head>
<body>
<%@ include file="/views/fragments/header.jsp" %>
<main>
	<div class="banner">
	<c:forEach items="${mainList}" var="banner">
		<a href="#"><img src="/resources/images/${banner.image}"></a>
	</c:forEach>
	</div>

	<div id="events">
		<c:forEach items="${eventList}" var="banner">
		<a class="card"
		   href="#">
			<p class="tag">${banner.item_id}</p>
			<h1>${banner.label}</h1>
			<p>${banner.desc}</p>
			<img src="/resources/images/${banner.image}">
		</a>
		</c:forEach>
	</div>

	<div id="md-list" class="list">
	<c:forEach var="list" items="${mdList}">
		<div class="layer">
			<h1>${list.label}</h1>
			<p>${list.desc}</p>
			<div class="grid">
			<c:forEach var="item" items="${list.itemList}">
				<a href="#" class="card">
					<img src="/resources/images/${item.image}">
					<h1>${item.label}</h1>
					<p>${item.price}원</p>
				</a>
			</c:forEach>
			</div>
		</div>
	</c:forEach>
	</div>
</main>
<%@ include file="/views/fragments/footer.jsp" %>
</body>
</html>
