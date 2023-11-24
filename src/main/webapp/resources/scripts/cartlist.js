$(document).ready(function(){
	$(".updateCart").on('click', function(){
		var cartData = [];
		$("cds-table-row").each(function(){
			var row = $(this);
			var order_id = row.find('input[name=order_id]').val();
			var quantity = row.find('input[name=quantity]').val();
			let data = { "order_id": order_id, "quantity": quantity };
			cartData.push(data);
		});
		$.ajax({
			url: '/actions/updateCart',
			type: 'post',
			contentType: 'application/json',
			data: JSON.stringify(cartData),
			success: function(response){
				console.log(response);
				alert('장바구니가 성공적으로 업데이트되었습니다.');
			}
		
		});
	});
    updateTotalPrice();
    updateCheckedTotalPrice();	
});

function delete_item() {
	let items = document.getElementsByName("checkItem");
	let row  = [];
	for (let i of items) {
		if (i.hasAttribute('checked'))
		row.push({"order_id": i.value});
	}	

	$.ajax({
		url: '/actions/deleteCart',
		type: 'post',
		contentType: 'application/json', 
		data: JSON.stringify(row), 
		dataType: 'json',
		success: function(){
			alert('삭제완료');
		}
	});

}

function updateTotalPrice() {
	var checkedTotalPrice = 0;
	var totalPrice = 0;
	var rows = $("cds-table-row").toArray(); // jQuery 객체를 배열로 변환합니다.
	
	for(var i = 0; i < rows.length; i++) { // 배열에 대해 for 문을 사용합니다.
	    var row = $(rows[i]); // jQuery 객체로 변환합니다.
	    var totalPriceCell = row.find('cds-table-cell[name="totPrice"]');
	    var rowTotal = Number(totalPriceCell.text());
	    totalPrice += rowTotal;
	}
	
	$("#total").text("전체 상품 가격 : " + totalPrice); // 총 가격을 표시합니다.	 
}

function updateCheckedTotalPrice() {
	var checkedTotalPrice = 0;
	var checkboxes = document.querySelectorAll("cds-checkbox");
	
	checkboxes.forEach(function(checkbox) {
		checkbox.addEventListener('cds-checkbox-changed', function() {
			var row = checkbox.closest('cds-table-row');
			var totalPriceCell = row.querySelector('cds-table-cell[name="totPrice"]');
			var rowTotal = Number(totalPriceCell.textContent);
			if (checkbox.checked) {
			    checkedTotalPrice += rowTotal;
			} else {
			    checkedTotalPrice -= rowTotal;
			}
			document.getElementById("checkedTotal").textContent = "선택 상품 가격 : " + checkedTotalPrice;
		});
	});
}


