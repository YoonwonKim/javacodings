sessionStorage.setItem("DEBUG_MODE", true);
const DEBUG_MODE = sessionStorage.getItem("DEBUG_MODE");

import {order} from '/resources/scripts/common/data/order.js';

// Region 단일 상품 =================================================================

$(document).ready(function() {
	// * 최초 총액 설정
	const amountElements = document.querySelectorAll('.orderable');
	for (let i = 0; i < amountElements.length; i++) updatePrice(amountElements[i]);

	// * 총액 변경
	const quantityInputs = document.querySelectorAll('#quantity');
	for (let i = 0; i < quantityInputs.length; i++) {
		const input = quantityInputs[i];
		const itemElement = input.closest(".orderable");
		input.addEventListener('click', () => updatePrice(itemElement));
		input.addEventListener('input', () => updatePrice(itemElement));
		orderSummary();
	}
});

// End Region 단일 상품
// Region 복수 상품 =================================================================


// End Region 복수 상품
// Region 상품 요약 =================================================================

$(document).ready(() => orderSummary());

// End Region 상품 요약



$(document).ready(function(){

	// * 단일 주문 요청
	const requestSingleButtonList = document.querySelectorAll(".request-single");
	requestSingleButtonList.forEach(function(element) {
		element.addEventListener('click', function() {
			const orderElement = element.closest(".order");
			const orderData = orderable.getCartData(orderElement);
			order.request([orderData]);
		})
	});

	// * 선택 주문 요청
	const requestSelectedButton = document.getElementById("request-selected");
	requestSelectedButton.addEventListener('click', function() {
		const orderElementList = document.querySelectorAll(".order[selected]");
		let cartList = [];
		for(let orderElement of orderElementList) {
			let cartData = orderable.getCartData(orderElement);
			cartList.push(cartData);
		}
		order.request(cartList);
	});
});

// Region Cart

function calcPrice(item) {
	let row = item.closest('#item-div');
	let quantity = item.value;
	let price = row.querySelector("#cart-amount").getAttribute('price');
	let amount = price * quantity;

	let column = row.querySelector('#cart-amount');
	column.setAttribute("amount", amount);
	column.innerHTML = amount.toLocaleString('en-US') + ' 원';
	item.value = quantity;
}

function updateOne() {}

function deleteOne(item_id) {
	let item = document.querySelector('[name="'+item_id+'"]');
	let quantity = item.querySelector('.cart-quantity').value;

	$.ajax({
		url: '/actions/cart/delete/' + item_id,
		type: 'POST',
		data: {item_id, quantity},
		success: function(){
			alert('삭제완료');
			location.reload();
		}
	});
}

function deleteSelected() {
	let orderList = [];

	let selectedItems =
		document.querySelectorAll('#item[selected]');
	for (let i = 0; i < selectedItems.length; i++) {
		let item = selectedItems[i];
		let item_id = item.getAttribute('name');
		let quantity = item.querySelector('.cart-quantity').value;

		orderList.push({ item_id, quantity });
	}

	$.ajax({
		url: '/actions/cart/delete',
		method: 'POST',
		data: orderList,
		success: function() {
			console.log('Delete Selected');
			location.reload();
		}
	});
}

// End Region Cart
// Region Order Summary

function orderSummary() {
	let totalItems = 0;
	let totalPrice = 0;
	let totalDiscount = 0;
	let totalDelivery = 0;

	let itemList = document.querySelectorAll('#item[selected]');
	totalItems = itemList.length;
	for( let i = 0; i < itemList.length; i++ ) {
		let item = itemList[i];

		let price = item.querySelector('#cart-amount').innerHTML.split(' ')[0];
		totalPrice += parseInt(price);

		// let discount = item.querySelector('#item-discount').innerHTML.split(' ')[0];
		// totalDiscount += parseInt(discount);
		//
		// let delivery = item.querySelector('#item-delivery').innerHTML.split(' ')[0];
		// totalDelivery += parseInt(delivery);
	}

	// Order Summary Write
	let summary = document.querySelector("#order-summary");
	summary.querySelector("#order-summary-quantity .value").innerHTML =
		totalItems.toLocaleString('en-US') + ' 개';
	summary.querySelector("#order-summary-price    .value").innerHTML =
		totalPrice.toLocaleString('en-US') + ' 원';
	summary.querySelector("#order-summary-discount .value").innerHTML =
		'-' + totalDiscount.toLocaleString('en-US') + ' 원';
	summary.querySelector("#order-summary-delivery .value").innerHTML =
		totalDelivery.toLocaleString('en-US') + ' 원';

	document.querySelector("#order-summary-total .value").innerHTML =
		(totalPrice + totalDelivery - totalDiscount).toLocaleString('en-US') + ' 원';
}

// End Region Order Summary


function updatePrice(itemData) {
	const amountElement = itemData.querySelector("#amount");
	const priceValue = itemData.querySelector("#price").value;
	const quantityValue = itemData.querySelector("#quantity").value;

	let amountValue = Number(priceValue * quantityValue);
	amountElement.innerHTML =
		amountValue.toLocaleString() + " 원"
		+ "<b>(1개 당 " + Number(priceValue).toLocaleString() + " 원)</b>";
}




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
			location.reload();
		}

	});
});


