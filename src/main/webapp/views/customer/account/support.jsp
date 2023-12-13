<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>
	<title>자바코딩즈 FAQ</title>

	<%-- Page Component --%>
	<script type="module" src="${pageContext.request.contextPath}/resources/scripts/manage_orders.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/components/pagination.css" />
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>
<cds-layer level="1">
<cds-stack class="main" gap="16px" use-custom-gap-value>
<%-- Region Order List ---------------------------------------------------------------------%>
	<cds-tile id="order-table" level="1">
		<cds-table is-selectable is-sortable use-zebra-styles>
			<cds-table-header-title slot="title">FAQ</cds-table-header-title>
			<cds-table-header-description slot="description">자주 묻는 질문</cds-table-header-description>			
			<cds-table-toolbar slot="toolbar">
				<cds-table-toolbar-content>
					<cds-table-toolbar-search
							placeholder="검색"></cds-table-toolbar-search>
				</cds-table-toolbar-content>
			</cds-table-toolbar>
			<cds-table expandable>
				<cds-table-head>
					<cds-table-header-row>
						<cds-table-header-cell>번호</cds-table-header-cell>
						<cds-table-header-cell>분류</cds-table-header-cell>
						<cds-table-header-cell>제목</cds-table-header-cell>
					</cds-table-header-row>
				</cds-table-head>
				<cds-table-body>
					<cds-table-row>
						<cds-table-cell>1</cds-table-cell>
						<cds-table-cell>주문 및 결제</cds-table-cell>
						<cds-table-cell>모바일 주문이 가능한가요?</cds-table-cell>
					</cds-table-row>
					<cds-table-expanded-row>
						<h6>모바일 주문이 가능한가요?</h6>
						<div>자바코딩즈 온라인 쇼핑몰은 모바일로도 주문 및 결제가 가능합니다. 
						웹(PC) 접속 시 URL: http://localhost:6060/
						위의 주소로 접속하실 수 있습니다.</div>
					</cds-table-expanded-row>
					<cds-table-row>
						<cds-table-cell>2</cds-table-cell>
						<cds-table-cell>주문 및 결제</cds-table-cell>
						<cds-table-cell>주문은 어떻게 하나요?</cds-table-cell>
					</cds-table-row>
					<cds-table-expanded-row>
						<h6>주문은 어떻게 하나요?</h6>
						<div>1) 각 코너를 클릭하셔서 들어갑니다. 2) "바로가기" 메뉴 또는 사진이나 상품명을 클릭하세요! 3) "장바구니 담기"를 클릭하세요! 
						4) "장바구니에 넣었습니다" 메시지가 나오면, 주문상품을 확인한 후 "주문버튼"을 클릭하세요! 
						5) 주문버튼을 누르면, "주문서"가 나옵니다! 
						주문서를 작성 후 "주문"을 클릭하면 주문이 완료됩니다!</div>
					</cds-table-expanded-row>
					<cds-table-row>
						<cds-table-cell>3</cds-table-cell>
						<cds-table-cell>주문 및 결제</cds-table-cell>
						<cds-table-cell>발송 마감 시간은 언제인가요?</cds-table-cell>
					</cds-table-row>
					<cds-table-expanded-row>
						<h6>발송 마감 시간은 언제인가요?</h6>
						<div>당일(영업일 기준) 오전 11시 주문 건을 기준으로 발송을 마감합니다. 
						주문량에 따라 발송 마감시간 및 출고 업무 일정은 변경될 수 있습니다. (주문일 기준 약 2~5일 소요)</div>
					</cds-table-expanded-row>
					<cds-table-row>
						<cds-table-cell>4</cds-table-cell>
						<cds-table-cell>주문 및 결제</cds-table-cell>
						<cds-table-cell>배송 전 주문 취소는 어떻게 하나요?</cds-table-cell>
					</cds-table-row>
					<cds-table-expanded-row>
						<h6>배송 전 주문 취소는 어떻게 하나요?</h6>
						<div>고객 센터 상담이 종료된 (오후 6시 ~ 오전 10시) 시간 동안 배송 전 주문 취소를 원하실 경우 고객 센터 게시판을 통해 취소할 상품을 문의 글로 남겨주세요. 
						남겨주신 문의 글 확인 후 취소 처리해 드립니다. 
						오후 2시 이후 상품 발송이 시작되어 해당 시간 이후 취소 요청의 경우 불가할 수 있으니 참고 바랍니다.</div>
					</cds-table-expanded-row>
					<cds-table-row>
						<cds-table-cell>5</cds-table-cell>
						<cds-table-cell>주문 및 결제</cds-table-cell>
						<cds-table-cell>신용카드 결제가 되지 않을 때는 어떻게하나요?</cds-table-cell>
					</cds-table-row>
					<cds-table-expanded-row>
						<h6>신용카드 결제가 되지 않을 때는 어떻게하나요?</h6>
						<div>신용카드 결제 오류의 경우 전자결제 모듈의 문제일 수 있습니다. 
						결제에 문제가 있을 경우 해당 결제 모듈을 별도로 다운받아 설치해주시기 바랍니다. 
						[KG이니시스 전자결제] https://www.inicis.com/ini-manual</div>
					</cds-table-expanded-row>
					<cds-table-row>
						<cds-table-cell>6</cds-table-cell>
						<cds-table-cell>배송</cds-table-cell>
						<cds-table-cell>배송비는 어떻게 되나요?</cds-table-cell>
					</cds-table-row>
					<cds-table-expanded-row>
						<h6>배송비는 어떻게 되나요?</h6>
						<div>최종 주문 결제금액 기준 5만 원 미만은 3,000원의 배송비가 청구되며 5만원 이상 주문 시 무료배송입니다.</div>
					</cds-table-expanded-row>
				</cds-table-body>
			</cds-table>
		</cds-table>
	</cds-tile>
	<%-- End Region Order List ---------------------------------------------------------------------%>
</cds-stack>
</cds-layer>
</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>
