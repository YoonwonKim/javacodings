<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>자바코딩즈 주문 관리</title>

	<%-- Frameworks --%>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<%-- Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/checkbox.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/layer.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/tile.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/stack.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/pagination.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/select.min.js"></script>
	<%-- Fragment CSS --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/merchandiser/fragments/init.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/merchandiser/fragments/header.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/merchandiser/fragments/footer.css" />
	<link rel="stylesheet" href="/resources/css/components/pagination.css" />
	<script src="/resources/scripts/common/components/pagination.js"></script>
	<script src="/resources/scripts/manage_members.js"></script>
	<%-- Page Component --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/manage_orders.css" />
</head>
<body>
<%@ include file="/views/merchandiser/fragments/header.jsp" %>
<main>
<cds-layer level="1">
<cds-stack class="main" gap="16px" use-custom-gap-value>
<%-- Region Order List ---------------------------------------------------------------------%>
	<cds-tile id="order-table" level="1">
		<cds-table is-selectable is-sortable use-zebra-styles>
			<cds-table-header-title slot="title">회원 관리</cds-table-header-title>
			<cds-table-header-description slot="description">회원 관리용 테이블입니다.</cds-table-header-description>
			<cds-table-toolbar slot="toolbar">
				<cds-table-toolbar-content>
					<cds-table-toolbar-search
							placeholder="Filter table"></cds-table-toolbar-search>
				</cds-table-toolbar-content>
			</cds-table-toolbar>
			<cds-table-head>
				<cds-table-header-row hide-checkbox>
					<cds-table-header-cell>MEMBER_ID</cds-table-header-cell>
					<cds-table-header-cell>NAME</cds-table-header-cell>
					<cds-table-header-cell>EMAIL</cds-table-header-cell>
					<cds-table-header-cell>BIRTH</cds-table-header-cell>
					<cds-table-header-cell>PHONE</cds-table-header-cell>
					<cds-table-header-cell>REG_DATE</cds-table-header-cell>
					<cds-table-header-cell>ROLE</cds-table-header-cell>
					<cds-table-header-cell>탈퇴</cds-table-header-cell>
				</cds-table-header-row>
			</cds-table-head>
			<cds-table-body>
				<c:forEach var="member" items="${objectList}">
					<cds-table-row class="row">						
						<cds-table-cell>${member.member_id}</cds-table-cell>
						<cds-table-cell>${member.name}</cds-table-cell>
						<cds-table-cell>${member.email}</cds-table-cell>
						<cds-table-cell>${member.birth}</cds-table-cell>
						<cds-table-cell>${member.phone}</cds-table-cell>
						<cds-table-cell>${member.reg_date}</cds-table-cell>
						<cds-table-cell>${member.role}</cds-table-cell>						
						<cds-table-cell><cds-button onclick="deleteMemebr(event)">회원 탈퇴</cds-button></cds-table-cell>						
					</cds-table-row>
				</c:forEach>
			</cds-table-body>
		</cds-table>
		<%@ include file="/resources/components/paginationmember.jsp" %>
	</cds-tile>
	<%-- End Region Order List ---------------------------------------------------------------------%>
</cds-stack>
</cds-layer>
</main>
<%@ include file="/views/merchandiser/fragments/footer.jsp" %>
</body>
</html>
