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

	document.querySelector("#cart").addEventListener('click', function() {
		cart();
	});
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
			if (response == 'auth error') {
				alert('로그인이 필요한 항목입니다');
				location.href = '/account/login';
			}
		}
	});
}

function cart(state) {
	let url = location.href.split('/');
	let item_id  = url[url.length - 1];

	const quantity = document.getElementById("order-quantity").value;
	const price = document.getElementById("item-price-value").innerText;

	let data = {item_id: item_id, amount: quantity * price};

	$.ajax({
		method: 'PUT',
		url: '/actions/cart/put',
		data: data,
		success: function(response) {
			if (response == 'auth error') {
				alert('로그인이 필요한 항목입니다');
				location.href = '/account/login';
			} else {
				if (confirm('장바구니에 담았습니다.\n장바구니로 이동하시겠습니까?'))
					location.href = '/cart';
			}
		}
	});
}
