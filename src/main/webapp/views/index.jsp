<%@ page contentType="text/html;charset=UTF-8" %>

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
		<div class="swiper-slide"><img src="/resources/images/demo.jpg"></div>
		<div class="swiper-slide"><img src="/resources/images/demo2.jpg"></div>
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
	<c:forEach var="list" items="${md-list}">
		<div class="layer">
			<h1>${list.label}</h1>
			<p>${list.desc}</p>
			<div class="grid">
			<c:forEach var="item" items="${list.itemList}">
				<a href="#" class="card">
					<img src="/resources/images/${item.image}.png">
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
