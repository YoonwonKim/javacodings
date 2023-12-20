<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

	<title>자바코딩즈</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/landing.css" />
	<script src="${pageContext.request.contextPath}/resources/scripts/landing.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>
	<div class="banner">
	<c:forEach items="${mainList}" var="banner">
		<a href="/event/${banner.event_id}">
		<img src="/resources/images/${banner.image}"></a>
	</c:forEach>
	</div>

	<div id="events">
		<c:forEach items="${eventList}" var="banner">
		<a class="card"
		   href="/event/${banner.event_id}">
			<h1>${banner.label}</h1>
			<img src="/resources/images/${banner.image}" width="370px;" height="400px;">
		</a>
		</c:forEach>
	</div>

	<div id="md-list" class="list">
	<c:forEach var="key" items="${mdList.keySet()}">
		<div class="layer">
			<h1>${key}</h1>
			<div class="grid">
			<c:forEach var="item" items="${mdList.get(key)}">
				<a href="/item/${item.item_id}" class="card">
					<img src="/resources/images/${item.path}">
					<h1>${item.getLabel()}</h1>
					<p>${item.getPrice()}원</p>
				</a>
			</c:forEach>
			</div>
		</div>
	</c:forEach>
	</div>
</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>
