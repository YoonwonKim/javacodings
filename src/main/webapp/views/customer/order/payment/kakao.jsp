<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>자바코딩즈 카카오결제</title>
</head>
<body>
	<%-- 실제 결제 스크립트 --%>
	<%--<script type="text/javascript" src="https://pay.kcp.co.kr/plugin/payplus_web.jsp"></script>--%>
	<%-- 테스트용 결제 스크립트 --%>
	<script type="text/javascript" src="https://testpay.kcp.co.kr/plugin/payplus_web.jsp"></script>

	<form name="kcp_order_info" id="kcp_order_info"
	      method="post" accept-charset="euc-kr" action="/order/confirm/kakao/${ordr_idxx}">
		<input type="hidden" name="ordr_idxx" value="${ordr_idxx }">
		<input type="hidden" name="good_name" value="${good_name }">
		<input type="hidden" name="good_mny" value="${good_mny }">
		<input type="hidden" name="buyr_name" value="${buyr_name }">
		<input type="hidden" name="site_cd" value="${site_cd }">
		<!-- 고정값 -->
		<input type="hidden" name="req_tx" value="pay">
		<input type="hidden" name="pay_method" value="100000000000">
		<input type="hidden" name="req_tx" value="pay">
		<input type="hidden" name="pay_method" value="100000000000">
		<input type="hidden" name="currency" value="410" />
		<input type="hidden" name="kakaopay_direct" value="Y" />
		<input type="hidden" name="module_type" value="01" />

		<input type="hidden" name="ordr_chk" value=""/>
		<!--
		※ 필 수
		필수 항목 : 표준웹에서 값을 설정하는 부분으로 반드시 포함되어야 합니다
		값을 설정하지 마십시오
		-->
		<input type="hidden" name="res_cd" value="" />
		<input type="hidden" name="res_msg" value="" />
		<input type="hidden" name="enc_info" value="" />
		<input type="hidden" name="enc_data" value="" />
		<input type="hidden" name="ret_pay_method" value="" />
		<input type="hidden" name="tran_cd" value="" />
		<input type="hidden" name="use_pay_method" value="" />
		<input type="hidden" name="card_pay_method" value="" />
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

	<script type="text/javascript">
        function m_Completepayment(FormOrJson, closeEvent) {
            let frm = document.kcp_order_info;
            GetField(frm, FormOrJson);

            if (frm.res_cd.value == "0000") {
                /*
				[가맹점 리턴값 처리 영역]
				인증이 완료되면 frm에 인증값이 들어갑니다. 해당 데이터를 가지고
				승인요청을 진행 해주시면 됩니다.
				*/
	            console.log(frm);
                let form = document.getElementById("kcp_order_info");
                form.submit();
                closeEvent();
            } else {
                alert("[" + frm.res_cd.value + "]" + frm.res_msg.value);
                closeEvent();
            }
        }

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

	<script>
		jsf_pay();
	</script>
</body>
</html>
