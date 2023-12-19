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

$(document).ready(function() {

	const orderRequest = document.querySelector("#order");
	orderRequest.addEventListener('click', function() {
		let orderList = [];
		const itemList = document.querySelectorAll(".order[selected]");
		for(let i = 0; i < itemList.length; i++)
		{
			let orderData = order.elementToOrder(itemList[i]);
			orderList.push(orderData);
		}
		order.request(orderList);
	});

});

// End Region 복수 상품
// Region 상품 요약 =================================================================

$(document).ready(function() {
	orderSummary();
	const itemList = document.querySelectorAll(".order");
	for(let i = 0; i < itemList.length; i++)
		itemList[i].addEventListener('click', () => {
			setTimeout( () => {orderSummary()}, 20 )
		});
});

function orderSummary() {
	let count = 0;
	let amount = 0;
	let totalDiscount = 0;
	let totalDelivery = 0;

	let itemList = document.querySelectorAll('.order[selected]');
	count = itemList.length;
	for( let i = 0; i < itemList.length; i++ ) {
		let item = itemList[i];
		let price = item.querySelector('#amount').value;
		amount += parseInt(price);
	}

	// Order Summary Write
	let summary = document.querySelector("#order-summary");
	summary.querySelector("#order-summary-quantity .value").innerHTML =
		count.toLocaleString() + ' 개';
	summary.querySelector("#order-summary-price    .value").innerHTML =
		amount.toLocaleString('en-US') + ' 원';
	summary.querySelector("#order-summary-discount .value").innerHTML =
		'-' + totalDiscount.toLocaleString('en-US') + ' 원';
	summary.querySelector("#order-summary-delivery .value").innerHTML =
		totalDelivery.toLocaleString('en-US') + ' 원';

	document.querySelector("#order-summary-total .value").innerHTML =
		(amount + totalDelivery - totalDiscount).toLocaleString('en-US') + ' 원';
}

// End Region 상품 요약


$(document).ready(function(){

	const priceList = document.querySelectorAll("#price");
	priceList.forEach(function(element) {
		let priceValue = element.value;
		element.value = Math.trunc(priceValue);
	});

});

function updatePrice(itemData) {
	const amountElement = itemData.querySelector("#amount");
	const priceValue = itemData.querySelector("#price").value;
	const quantityValue = itemData.querySelector("#quantity").value;

	let amountValue = Number(priceValue * quantityValue);
	amountElement.value = amountValue;
	amountElement.innerHTML = amountValue.toLocaleString() + " 원";
	orderSummary();
}

