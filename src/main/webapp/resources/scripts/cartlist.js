$(document).ready(function(){
	$("#updateCartButton").click(function(){
		$.ajax({
			url: '/actions/updateCart',
			type: 'post',
			success: function(response){
			    console.log(response);
			}
		});
	});
	
	$("#deleteCartButton").click(function(){
		var checkedId = $("#checkItem").val();
		$.ajax({
			url: '/actions/deleteCart',
			type: 'post',
			data: {id:checkedId}, 
			success: function(response){
			    console.log(response);
			}
		});
	});
});

function updateButton() {
	var checkedItems = $("input[name='checkItem']:checked").length; // "checkItem" 이름을 가진 체크박스 중 선택된 것의 수를 가져옵니다.
	if (checkedItems > 0) {
		$("#orderButton").text("선택 상품 주문"); // 체크박스가 선택되었을 경우 텍스트를 변경합니다.
	} else {
		$("#orderButton").text("전체 상품 주문"); // 체크박스가 선택되지 않았을 경우 텍스트를 변경합니다.
	}
}
// 체크박스의 선택 상태가 바뀔 때마다 updateButton 함수를 실행합니다.
$("input[name='checkItem']").change(updateButton);
    
function updateTotalPrice() {
	var totalPrice = 0;
	$("cds-table-row").each(function() {
		var checkbox = $(this).find("cds-checkbox#checkItem");
		if (checkbox.prop("checked")) { // 체크박스가 체크된 경우
			var priceCell = $(this).find("cds-table-cell").eq(6); // 가격을 표현하는 셀을 찾습니다.
			var price = Number(priceCell.text()); // 가격을 숫자로 변환합니다.
			totalPrice += price; // 총 가격에 더합니다.
		}
	});
	
	$("#totalPrice").text(totalPrice); // 총 가격을 표시합니다.
}
// 체크박스의 상태가 바뀔 때마다 updateTotalPrice 함수를 실행합니다.
$("cds-checkbox#checkItem").change(updateTotalPrice);