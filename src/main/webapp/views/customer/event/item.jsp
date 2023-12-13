<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 이벤트페이지</title>
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<%-- Fragment CSS --%>
	<link rel="stylesheet" href="https://1.www.s81c.com/common/carbon-for-ibm-dotcom/tag/v1/latest/plex.css" />
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

	<link rel="stylesheet" href="/resources/styles/customer/event-list.css" />
	

	<%-- Page Script and Styles --%>
	<script src="/resources/scripts/cart.js"></script>
	<link rel="stylesheet" href="/resources/css/customer/cart.css" />
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
	<div class="main">
		<c:forEach var="banner" items="${mainBanner}">
			<h1 style="font-size:40px; margin-top:60px; text-align:center;">${banner.label}</h1>
			<img src="/resources/images/${banner.image}" style="width:900px; height:400px;">
			
			<input type="hidden" name="event_id" value="${banner.event_id}">
			<input type="hidden" name="category" value="${banner.category}">
		</c:forEach>
	</div>	
	
	<div class="item">
		<table>
			<tr>
				<th style="font-size:40px; padding-top:100px; text-align:center;">MAIN ITEM</th>
			</tr>
			<tr>
			<c:choose>
				<c:when test="${fn:length(eventItem)>0}">
					<c:forEach var="items" items="${eventItem}" begin="0" end="3">
						<td>
							<ul>
								<li><a href="/event/item?item_id=${items.item_id}"><img src="/resources/images/${items.path}"></a></li>
								<li>${items.label}</li>
							</ul>
						</td>
					</c:forEach>
				</c:when>
			</c:choose>
			</tr>
		</table>
	</div>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
