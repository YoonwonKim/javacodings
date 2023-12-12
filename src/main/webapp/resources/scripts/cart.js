sessionStorage.setItem("DEBUG_MODE", true);
const DEBUG_MODE = sessionStorage.getItem("DEBUG_MODE");

import {order} from '/resources/scripts/common/order.js';



$(document).ready(function(){
	// 문서 초기화
	orderSummary();

	// 주문 수량 변경
	let quantityInputs = document
			.getElementsByClassName('cart-quantity')
	for( let i = 0; i < quantityInputs.length; i++ ) {
		let input = quantityInputs[i];
		input.addEventListener('focusout', function() {
			calcPrice(this);
			updateOne(this.closest('#item'));
		});
	}

	// 주문 요약
	document.addEventListener('cds-current-selectable-tile-selections',
		function() {
		orderSummary();
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
// Region Order

$(document).ready(function() {

	let orderButtonList = document.querySelectorAll('.order-button');
	orderButtonList.forEach(function(value) {
		let itemId = value.getAttribute('item-id');
		value.addEventListener('click', function() {
			orderM(itemId);
		})
	})

});

function orderM(itemId)
{
	const tile = document.querySelector("cds-selectable-tile[name='" + itemId + "']");
	const amount = tile.querySelector("#cart-amount").getAttribute("amount");
	// const data = {item_id: itemId, amount: amount};

	order.set(itemId, amount);
	order.request();
}



function orderSelected() {
	let orderList = [];

	let selectedItems =
		document.querySelectorAll('#item[selected]');
	for (let i = 0; i < selectedItems.length; i++) {
		let item = selectedItems[i];
		let item_id = item.getAttribute('name');
		let quantity = item.querySelector('.cart-quantity').value;

		orderList.push({ item_id, quantity });
	}
}

// End Region Order
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


