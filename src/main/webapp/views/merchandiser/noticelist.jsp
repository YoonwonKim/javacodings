<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 상품 관리</title>
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

	<%--  Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/checkbox.min.js"></script>
	<%-- Fragment CSS --%>
	<link rel="stylesheet" href="/views/merchandiser/fragments/init.css" />
	<link rel="stylesheet" href="/views/merchandiser/fragments/header.css" />
	<link rel="stylesheet" href="/views/merchandiser/fragments/footer.css" />
	
	<script src="/resources/scripts/notice.js"></script>
</head>
<body>
<%@ include file="/views/merchandiser/fragments/header.jsp" %>
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
					<cds-table-cell><td><a href="/admin/noticepage?notice_id=${notice.notice_id}">${notice.label}</a></td></cds-table-cell>
					<cds-table-cell><td>${notice.author_id}</td></cds-table-cell>
					<cds-table-cell><td>${notice.reg_date}</td></cds-table-cell>
				</tr>
			</c:forEach>
		</c:when>
		<c:when test="${fn:length(notice)==0}">
		<cds-table-cell><tr style="text-align: center; height: 30px;"><th colspan="5">등록된 공지사항이 없습니다</th></tr>	</cds-table-cell>		
		</c:when>
	</c:choose>
	<tr style="text-align: right; height: 50px;">
			<th colspan="5" style="text-align: right; border: 1px solid #ffffff;">
			<input type="button" value="공지사항등록" onclick="location.href='/admin/noticeganerate'">
			</th>
		</tr>
</cds-table-row>
		</cds-table-body>
	</table>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
