<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 이벤트페이지</title>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>

	<link rel="stylesheet" href="/resources/styles/customer/event-list.css" />
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>
		<h1 style="font-size:40px; margin-top:60px;">이벤트</h1>
	<div class="tabmenu">
		<ul>
			<li id="tab1" class="btnCon">
				<a class="btn first" href="#tab1" id="t1">진행중인 이벤트</a>
					<div class="tabCon">
						<div id="content">
							<form name="topForm1">
							<table class="conimg">
								<c:forEach var="event" items="${objectList}">
								<tr class="row" event-id="${event.event_id}">
									<th><img src="/resources/images/${event.image}" onclick="location.href='/event/item?event_id=${event.event_id}'"></th>								
								</tr>
								<tr>
									<td>${event.label}</td>
								</tr>
								</c:forEach>
							</table>
							</form>
						</div>
					</div>
			</li>
			
			
			<li id="tab2" class="btnCon">
				<a class="btn first" href="#tab2">종료된 이벤트</a>
					<div class="tabCon">
						<div id="content">
							<form name="topForm2">
							<table>
								<c:forEach var="event" items="${objectList}" varStatus="status">
								<tr class="row" event-id="${event.event_id}">
									
									<th><img src="/resources/images/${event.image}"></th>								
								</tr>
								<tr>
									<td>${event.label}</td>
								</tr>
								</c:forEach>
							</table>
							</form>
						</div>
					</div>
			</li>
		</ul>
	</div>	
</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>
