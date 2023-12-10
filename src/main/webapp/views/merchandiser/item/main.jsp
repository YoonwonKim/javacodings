<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 상품 관리</title>
	<%@ include file="/views/merchandiser/fragments/dependencies.jsp" %>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

	<link rel="stylesheet" href="/resources/styles/components/pagination.css" />
	<link rel="stylesheet" href="/resources/styles/components/summary.css" />

	<link rel="stylesheet" href="/resources/styles/manage_products.css"/>
	<script type="module" src="/resources/scripts/merchandiser/products/manage.js"></script>
</head>
<body>
<%@ include file="/views/merchandiser/fragments/global/header.jsp" %>

<main>
	<div id="data">
		<cds-stack orientation="horizontal" gap="16px" use-custom-gap-value>
		<c:forEach var="summary" items="${summaryList}">
			<%@ include file="/views/merchandiser/fragments/summary.jsp" %>
		</c:forEach>
		</cds-stack>
		<%@ include file="/views/merchandiser/item/list.jsp" %>
	</div>
	<%@ include file="/views/merchandiser/item/modal.jsp" %>
</main>

<%@ include file="/views/merchandiser/fragments/global/footer.jsp" %>
</body>
</html>
