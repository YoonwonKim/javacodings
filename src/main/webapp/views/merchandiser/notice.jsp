<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 상품 관리</title>
	<link rel="stylesheet" href="/resources/css/manage_products.css" />
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
<form action="noticeProc?flag=insert" name="noticeForm" method="post">
<table>

	<thead>
		<tr>
			<th colspan="2">공지사항</th>
		</tr>
	</thead>
	
	<tbody>
		<tr>
			<td class="col1">공지사항번호</td>
			<td class="col2">
			<input type="text" id="notice_id" name="notice_id" readonly="readonly" value="${notice.notice_id}">
			</td>
		</tr>
		<tr>
			<td class="col1">제목</td>
			<td class="col2">
			<input type="text" name="label" readonly="readonly" value="${notice.label}">
			</td>
		</tr>
		<tr>
			<td class="col1">내용</td>
			<td class="col2">
			<textarea readonly="readonly" rows="10" cols="30" name="content" style="resize: none;">${notice.content}</textarea>
		</td>
		</tr>
		<tr>
			<td class="col1">작성일자</td>
			<td class="col2"><input type="text" name="regdate" readonly="readonly" value="${notice.reg_date}">
		</td>
			
		</tr>
	
	</tbody>
	<tfoot>
		<tr>
			<td colspan="3">
				<button type="button" onclick="location.href='/admin/noticelist'">목록</button>
				<button type="button" class="noticeUp" onclick="location.href='updateformnotice?notice_id=${notice.notice_id}'">수정</button>
				<button type="button" class="noticeDel">삭제</button>
			</td>
		
		</tr>
	</tfoot>

</table>

</form>
</main>
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>
