<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>자바코딩즈 상품 디테일 페이지</title>
	<!-- <link rel="stylesheet" href="/resources/css/manage_products.css" /> -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"
	        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

	<%--  Web Components --%>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/text-input.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/data-table.min.js"></script>
	<script type="module" src="https://1.www.s81c.com/common/carbon/web-components/tag/v2/latest/button.min.js"></script>
	<script src="/resources/scripts/itemdt.js"></script>
	
	<%-- Fragment CSS --%>
	<link rel="stylesheet" href="/views/customer/fragments/init.css" />
	<link rel="stylesheet" href="/views/customer/fragments/header.css" />
	<link rel="stylesheet" href="/views/customer/fragments/footer.css" />
</head>
<body>
<%@ include file="/views/customer/fragments/header.jsp" %>
<div class="layer-01">	
<h2 style="height: 40px; text-align: center; 
  margin-bottom: 10px; margin-top: 5px;">${items.label}</h2>
  <table class="outTbl">
     <tr>
     <td>
     <img alt="이미지" src="resources/images/${item.image}" height="150" width="150">
      
     </td>
     <td>
      <form action="cartProc?flag=add" name="itemForm" method="post">
       <table>
          <tr>
            <th>상품명:</th>
            <td>${item.label}
             <input type="hidden" 
                value="${item.label}"name="label">
             <input type="hidden"  
                value="${item.price}" name="price">
            </td>
          </tr>
          <tr>
            <th>가 격</th>
            <td class="price">${item.price}</td>
          </tr>
          <tr>
            <th>구매수량</th>
            <td><input type="text" name="quantity" size="5"
                 value="" class="chk price" title="구매수량"> </td>
          </tr>
          <tr>
            <th>재고</th>	
            <td><input class="price disable" type="text" name="stock"
               value="${item.stock}" readonly="readonly">
               </td>
          </tr>
          
          <tr>
            <th colspan="2" class="tableBtn">
              <input type="button" class="cartAdd" value="장바구니 담기">
               &nbsp;<input type="button" value="상품목록" 
                  onclick="javascript:location.href='listItem'">
              <input type="hidden" name="item_id" value="${item.item_id}">
            </th>
          </tr>
       </table>
      </form>
     </td>
     </tr>
  </table>



	<%-- <div class="contentsleft">
		<ul>
			<li>
				<img alt="이미지" src="/resources/images/Event1-Demo.png">
			</li>
		</ul>
	</div>
	<div class="clear"></div>
	
	<div class="contentslight">
		<ul>
			<li>Home<a href="/"></a></li>
			<li>의류<a href="#"></a></li>
			<li>자켓<a href="#"></a></li>
			<li>클릭한 상품명<a href="#"></a></li>
		</ul>
		<br>
		
		<table>
			<thead>${item.item_id}</thead>
			<tbody>
				<tr>
					<th colspan="2">판매가</th>
					<td>${item.price}원</td>
				</tr>
				
				<tr>
					<th colspan="2">브랜드</th>
					<td>javacodings</td>
				</tr>
				<tr>
					<th colspan="2">원산지</th>
					<td>korea</td>
				</tr>
				<tr>
					<th>사이즈</th>
					<select>
						<option>90(S)</option>
						<option>95(M)</option>
						<option>100(L)</option>
						<option>105(XL)</option>
						<option>110(2XL)</option>
						<option>120(4XL)</option>
					</select>
				</tr>
				<tr>
					<th>수량</th>
					<td><input type="text"></td>
				</tr>
				<button>찜하기</button>
				<button href="/cartLists">장바구니</button>
				<button href="/order" class="btn_buy">바로구매</button>
				
				<!-- 주문 form -->
				<form action="order/${member.member_id}" method="get" class="order_form">
					<input type="hidden" name="orders[0].order_id" value="${order.order_id}">
					<input type="hidden" name="order[0].orderCount" value="">
				
				</form>

				
			</tbody>
		</table>
	</div>
	<div class="contents">
		<nav class="contentnenu">
			<ul>
				<li>상품상세정보</li>
				<li>배송/교환 안내</li>
				<li>관련상품</li>
				<li>이용후기</li>
				<li>상품문의</li>
			</ul>
		</nav>
	
	</div> --%>
	
	
		
	
	</div> 
	
<%@ include file="/views/customer/fragments/footer.jsp" %>
</body>
</html>