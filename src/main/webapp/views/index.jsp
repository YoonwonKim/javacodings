<%@ page contentType="text/html;charset=UTF-8" %>

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
