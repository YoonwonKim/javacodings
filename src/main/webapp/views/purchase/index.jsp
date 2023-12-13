<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 장바구니</title>
	<%@ include file="/views/customer/fragments/dependencies.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<%-- Page Script and Styles --%>
	<script type="module" src="/resources/scripts/cart.js"></script>
	<link rel="stylesheet" href="/resources/styles/customer/cart.css" />

	<script type="text/javascript">
        function jsf_pay() {
            try
            {
                let form = document.kcp_order_info;
                KCP_Pay_Execute( form );
            }
            catch (e)
            {
                /* IE 에서 결제 정상종료시 throw로 스크립트 종료 */
            }
        }
	</script>

	<%-- 실제 결제 스크립트 --%>
	<%--<script type="text/javascript" src="https://pay.kcp.co.kr/plugin/payplus_web.jsp"></script>--%>
	<%-- 테스트용 결제 스크립트 --%>
	<script type="text/javascript" src="https://testpay.kcp.co.kr/plugin/payplus_web.jsp"></script>
</head>
<body>
<%@ include file="/views/customer/fragments/global/header.jsp" %>
<main>
	<div id="table">
		<cds-layer level="1">
			<cds-tile-group>
				<cds-stack gap="8px" use-custom-gap-value>
					<c:forEach var="item" items="${itemList}">
						<cds-selectable-tile item-id="${item.item_id}" class="order" selected>
							<div id="item-div">
								<img src="/resources/images/${item.path}">
								<div id="item-desc">
									<h1>${item.label}</h1>
									<p>${item.desc}</p>
									<cds-number-input class="cart-quantity" name="cart-quantity"
									                  value="${item.amount/item.price}" min="1" max="${item.stock}"
									                  inline ></cds-number-input>
									<div id="order-amount">
										<p class="field" price="${item.price}" value="${item.amount}">${item.amount} 원</p>
									</div>
									<div class="button-group">
										<cds-button kind="secondary" class="request-single"
										            item-id="${item.item_id}">구매하기</cds-button>
										<cds-button kind="ghost" onclick="deleteOne('${item.item_id}')">삭제하기</cds-button>
									</div>
								</div>
							</div>
						</cds-selectable-tile>
					</c:forEach>
				</cds-stack>
			</cds-tile-group>
		</cds-layer>
	</div>

	<cds-layer level="1" id="order">
		<cds-tile>
			<cds-stack gap="16px" use-custom-gap-value>
				<h1>결제 정보</h1>
				<div id="order-summary">
					<table>
						<tr id="order-summary-quantity"><td>상품수</td><td class="value">${itemList.size()} 개</td></tr>
						<tr id="order-summary-price"><td>상품금액</td><td class="value">${amount} 원</td></tr>
						<tr id="order-summary-discount"><td>할인금액</td><td class="value">-0 원</td></tr>
						<tr id="order-summary-delivery"><td>배송비</td><td class="value">0 원</td></tr>
					</table>
					<form name="kcp_order_info" id="kcp_order_info"
					      accept-charset="euc-kr"
					      method="post" action="/order/confirm/${order_id}">
						<input type="hidden" name="ordr_idxx" value="${ordr_idxx }">
						<input type="hidden" name="good_name" value="${good_name }">
						<input type="hidden" name="good_mny" value="${good_mny }">
						<input type="hidden" name="buyr_name" value="${buyr_name }">
						<input type="hidden" name="site_cd" value="${site_cd }">
						<!-- 고정값 -->
						<input type="hidden" name="req_tx" value="pay">
						<input type="hidden" name="pay_method" value="100000000000">
						<input type="hidden" name="site_name" value="payup" />
						<!--
						※ 필 수
						필수 항목 : 표준웹에서 값을 설정하는 부분으로 반드시 포함되어야 합니다
						값을 설정하지 마십시오
						-->
						<input type="hidden" name="res_cd"
						       value=""/>
						<input type="hidden" name="res_msg"
						       value=""/>
						<input type="hidden" name="enc_info"
						       value=""/>
						<input type="hidden" name="enc_data"
						       value=""/>
						<input type="hidden" name="ret_pay_method" value=""/>
						<input type="hidden" name="tran_cd"
						       value=""/>
						<input type="hidden" name="use_pay_method" value=""/>
						<input type="hidden" name="buyr_mail" value="">
						<input type="hidden" name="ordr_chk"
						       value=""/>
						<!-- 2012년 8월 18일 전자상거래법 개정 관련 설정 부분 -->
						<!-- 제공 기간 설정 0:일회성 1:기간설정(ex 1:2012010120120131)
						<input type="hidden" name="good_expr" value="0">
						페이지 11 / 17
						-->
						<!-- 표준웹 설정 정보입니다(변경 불가) -->
						<input type="hidden" name="module_type"
						       value="01"/>
						<!-- 필수 항목 : 결제 금액/화폐단위 -->
						<input type="hidden" name="currency">
					</form>
				</div>
				<div id="order-summary-total">
					<p>총 결제 금액</p>
					<p class="field">${amount} 원</p>
				</div>
				<cds-button onclick="jsf_pay()">결제하기</cds-button>
			</cds-stack>
		</cds-tile>
	</cds-layer>
</main>
<%@ include file="/views/customer/fragments/global/footer.jsp" %>
</body>
</html>
