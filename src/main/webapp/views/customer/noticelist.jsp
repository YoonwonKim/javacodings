<%@ page contentType="text/html;charset=UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
	<!-- Javascript API and Frameworks -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
	<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	
	<%--  Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>

	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

                                                                                                      
	<title>자바코딩즈</title>
	<link rel="stylesheet" href="/resources/css/landing.css" />
	<script src="/resources/scripts/landing.js"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<main>
	<table class="noticelist">
		<cds-table-head>
			<cds-table-header-row hide-checkbox>
				<cds-table-header-cell></cds-table-header-cell>
				<cds-table-header-cell>번호</cds-table-header-cell>
				<cds-table-header-cell>제목</cds-table-header-cell>
				<cds-table-header-cell>작성자</cds-table-header-cell>
				<cds-table-header-cell>등록일</cds-table-header-cell>

			</cds-table-header-row>
		</cds-table-head>
				<cds-table-body>
					<cds-table-row>
	<c:choose>
		<c:when test="${fn:length(notice)>0}">
			<c:forEach var="notice" items="${notice}">
				<tr>
					<cds-table-cell><td class="col1">${notice.rr}
					<input type="hidden" value="${notice.notice_id}"></td></cds-table-cell>
					<cds-table-cell><td><a href="/noticepage?notice_id=${notice.notice_id}">${notice.label}</a></td></cds-table-cell>
					<cds-table-cell><td>${notice.author_id}</td></cds-table-cell>
					<cds-table-cell><td>${notice.reg_date}</td></cds-table-cell>
				</tr>
			</c:forEach>
		</c:when>
		<c:when test="${fn:length(notice)==0}">
		<cds-table-cell><tr style="text-align: center; height: 30px;"><th colspan="5">등록된 공지사항이 없습니다</th></tr>	</cds-table-cell>		
		</c:when>
	</c:choose>
		</cds-table-cell>
</cds-table-row>
		</cds-table-body>
	</table>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
