<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<!-- Javascript API and Frameworks -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	<%-- Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.rtl.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form-group.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/text-input.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/form-group.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/stack.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/tile.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/layer.min.js"></script>
	<%-- Fragements --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

	<title>자바코딩즈</title>
	<link rel="stylesheet" href="/resources/css/customer/category.css" />
	
	<%-- Page Scripts --%>
	<script src="/resources/scripts/common/policies/validates.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/customer/category.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
	<h1>${category}</h1>

	<cds-layer>
	<div id="grid">
		<cds-clickable-tile href="/product/" id="item">
		<cds-stack>
			<img id="item-image">
			<h1 id="item-label"></h1>
			<p id="item-price"></p>
			<p class="fake-link">구매하기</p>
		</cds-stack>
		</cds-clickable-tile>
	</div>
	</cds-layer>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>