<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<head>
	<title>자바 코딩즈 회원가입</title>
	<%-- Framework --%>
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"/>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<%-- Fragement CSS --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />

	<%-- Page Script --%>
	<script src="/resources/scripts/register.js"></script>
	<script src="/resources/scripts/zipCheck.js"></script>
</head>
<body>
<c:import url="../fragments/header.jsp" />
<form name="info" method="post">
	<table>
		<thead>
		<tr>
			<th colspan="3">마이페이지</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td >아이디</td>
			<td><input type="text" id="idchk" name="member_id"
			           title="아이디" value="${member_info.member_id }" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td >이름</td>
			<td><input type="text" name="name" title="이름" value="${member_info.name}"></td>
			<td>한글, 20자 이하</td>
		</tr>
		<tr>
			<td >비밀번호</td>
			<td><input type="password" name="password" id="check1" class="chk" title="패스워드"
			           placeholder="비밀번호를 입력하시오.">
				<font id="check" size="2" color="blue"></font>
			</td>
			<td>&nbsp;&nbsp;영문 대소문자 / 숫자 / 특수문자 중 2가지 이상 조합, 8자 ~ 16자</td>
		</tr>
		<tr>
			<td >비밀번호 확인</td>
			<td><input type="password" name="repassword" id="check2" class="chk" title="패스워드확인"
			           placeholder="비밀번호를 입력하시오.">
			</td>
			<td>&nbsp;&nbsp;비밀번호를 확인하시오.</td>
		</tr>
		<tr>
			<td >이메일</td>
			<td>
				<input type="text" name="email">@
				<input type="email" id="email_address" list="user_email_address" name="email2" value="${member_info.email}">
				<datalist id="user_email_address" >
					<option value="naver.com"></option>
					<option value="daum.net"></option>
					<option value="email.com"></option>
				</datalist>

				<input type="hidden" id="totalemail" name="email" value="">
		</tr>
		<tr>
			<td >생년월일</td>
			<td>
				<select name="birth1" id="year">
				</select>
				<select name="birth2" id="month">
				</select>
				<select name="birth3" id="day">
				</select>
			</td>
		</tr>

		<tr>
			<td class="col1">우편번호</td>
			<td class="col2">
				<input type="text" name="zipcode" id="sample6_postcode"
				       readonly="readonly"
				       class="chk"  title="우편번호"
				       value="${address.zipcode}">
				<button type="button" onclick="zipCheck()">우편번호찾기</button>
			</td>
			<td class="col3">우편번호를 검색하시오</td>
		</tr>
		<tr>
			<td >주소</td>
			<td><input type="text" name="address" class="chk" readonly="readonly"
			           id="sample6_address" placeholder="주소" title="주소" value="${address.address}"><br>
				<input type="text" name="address2" class="chk" id="sample6_detailAddress"
				       placeholder="상세주소" title="상세주소" value="${address.address2}">
				<input type="hidden" id="sample_extraAddress" placeholder="참고항목">
			</td>
			<td>주소를 입력하시오.</td>
		</tr>
		<tr>
			<td >휴대전화</td>
			<td>
				<select name="telecom">
					<option>LG</option>
					<option>SKT</option>
					<option>KT</option>
					<option>알뜰폰</option>
				</select>

				<input type="text" class="chk" value="010" readonly="readonly">-
				<input type="text" name="phone1" class="chk">-
				<input type="text" name="phone2" class="chk">
			</td>
			<td>010을 제외한 나머지 번호를 입력해주십시오.</td>
		</tr>


		</tbody>
		<tfoot>
		<tr>
			<td colspan="3">
				<button type="button" onclick="updateMemberInfo">회원 정보 수정</button>
			</td>
			<td colspan="3">
				<button type="button" onclick="deleteMember">회원 탈퇴</button>
			</td>
		</tr>
		</tfoot>
	</table>
</form>
<c:import url="../fragments/footer.jsp" />
</body>