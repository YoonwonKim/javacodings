// Region Style
$(document).ready(function() {
	let event = document.getElementById("item-event").value;
	if (event != null) {
		document.getElementById("item-price-value")
			.style.textDecorationLine = "line-through";
	}

	document.getElementById("order-quantity")
		.addEventListener("input", function() {
			orderPrice();
		});

	orderPrice();
});
// End Region Style

function orderPrice() {
	let item_price = document.getElementById("item-price-value");
	let order_quantity = document.getElementById("order-quantity");
	let order_price    = document.getElementById("order-price");

	let order_price_value = (item_price.innerText * order_quantity.value)
		.toLocaleString('en-US');
	order_price.innerText = order_price_value + ' 원';
}

function order(state) {
	let url = location.href.split('/');

	let order = {};
	order.item_id  = url[url.length - 1];
	order.quantity = document.getElementById("order-quantity").value;
	order.state    = state;

	$.ajax({
		data: order,
		method: 'POST',
		url: '/actions/order',
		success: function(response) {
			console.log(response);
		}
	});
}


// $().ready(function(){
// 	 var q = $('input[name=quantity]').val();
// 	 var s = $('input[name=stock]').val();
// 	 //숫자가 아닐때
// 	 if(!$.isNumeric(q)) {
// 		 alert('숫자를 입력하시오')
// 		 $('input[name=quantity]').focus()
// 		 return false;
// 	 }
// 	 //구매수량이 재고보다 많으면
// 	 if(parseInt(q)>parseInt(s)){
// 		 alert('재고가 부족합니다.')
// 		 return false;
// 	 }
// 	 $('form[name=itemForm]').submit();
//   });
//
//   $('itemDt').on('click', function(){
//
//   });

