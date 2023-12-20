<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 이벤트페이지</title>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>

<%--	<link rel="stylesheet" href="/resources/styles/customer/event-list.css" />--%>
		<link rel="stylesheet" href="/resources/styles/customer/event/view.css" />
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>
	<div class="main">
		<c:forEach var="banner" items="${mainBanner}">
			<img id="banner" src="/resources/images/${banner.image}">
		</c:forEach>
	</div>	
	
	<div class="item">
		<div id="grid">
			<c:choose>
			<c:when test="${fn:length(eventItem)>0}">
			<c:forEach var="item" items="${eventItem}">
				<cds-clickable-tile class="item" href="/item/${item.item_id}">
					<img src="/resources/images/${item.path}">
					<p>${item.label}</p>
				</cds-clickable-tile>
			</c:forEach>
			</c:when>
			</c:choose>
		</div>
	</div>
</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>
