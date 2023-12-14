<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<head>
	<title>자바 코딩즈 주문 관리</title>
	<%-- Framework --%>
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<%-- Web Component --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/date-picker.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/layer.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/breadcrumb.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/tile.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/stack.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/checkbox.min.js"></script>
	<link rel="stylesheet" href="https://1.www.s81c.com/common/carbon-for-ibm-dotcom/tag/v1/latest/plex.css" />
	
	<%-- Fragement CSS --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

	<%-- Page Script --%>
	<script src="/resources/scripts/register.js"></script>
	<script src="/resources/scripts/zipCheck.js"></script>
	<%-- Page Style --%>
	<link rel="stylesheet" href="/resources/styles/customer/account.css" />
</head>
<body>
<c:import url="../fragments/header.jsp" />
<main>
<cds-layer>

	<cds-breadcrumb no-trailing-slash>
		<cds-breadcrumb-item>
			<cds-breadcrumb-link href="/">홈</cds-breadcrumb-link>
		</cds-breadcrumb-item>
		<cds-breadcrumb-item>
			<cds-breadcrumb-link href="/account">계정 정보</cds-breadcrumb-link>
		</cds-breadcrumb-item>
		<cds-breadcrumb-item>주문 관리</cds-breadcrumb-item>
	</cds-breadcrumb>

	<h1>주문 관리</h1>

	<cds-layer leve="1">
	<div id="main-div">
		<div id="navigation">
			<a href="/account">계정 관리</a>
			<a href="/account/profile" >프로필 관리</a>
			<a href="/account/orders" current>주문 관리</a>
			<a href="/account/location">배송지 관리</a>
		</div>

		<div id="content">
			<cds-stack orientation="horizontal" gap="16px" use-custom-gap-value level="1">
				<cds-tile id="summary">
					<cds-stack gap="0" use-custom-gap-value>
						<c:if test="${not empty countMemberOrders}">
							<cds-table>
								<cds-table-header-title slot="title">주문 관리</cds-table-header-title>
								<cds-table-head>								
								<cds-table-header-row>
									<cds-table-header-row hide-checkbox></cds-table-header-row>
									<cds-table-header-cell>상품 이름</cds-table-header-cell>
									<cds-table-header-cell>상품 이미지</cds-table-header-cell>
									<cds-table-header-cell>상품 가격</cds-table-header-cell>
									<cds-table-header-cell>상품 수량</cds-table-header-cell>
									<cds-table-header-cell>결제 금액</cds-table-header-cell>
									<cds-table-header-cell>결제일</cds-table-header-cell>
									<cds-table-header-cell>주문 상태</cds-table-header-cell>
								</cds-table-header-row>
								</cds-table-head>
								<cds-table-body>
									<c:forEach var="order" items="${countMemberOrders}">
										<cds-table-row>
											<cds-table-cell><cds-checkbox></cds-checkbox></cds-table-cell>
											<cds-table-cell>이름</cds-table-cell>
											<cds-table-cell>사진</cds-table-cell>
											<cds-table-cell>가격</cds-table-cell>
											<cds-table-cell>수량</cds-table-cell>
											<cds-table-cell>금액</cds-table-cell>
											<cds-table-cell>결제일</cds-table-cell>
											<cds-table-cell>상태</cds-table-cell>
										</cds-table-row>
									</c:forEach>
								</cds-table-body>
							</cds-table>
						</c:if>
					</cds-stack>
				</cds-tile>			
			</cds-stack>
			<div class="right">			
				<cds-button kind="ghost" onclick="">???</cds-button>
			</div>
		</div>
		</div>
	</div>
	</cds-layer>

</cds-layer>
</main>
<c:import url="../fragments/footer.jsp" />
</body>