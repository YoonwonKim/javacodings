sessionStorage.setItem("DEBUG_MODE", true);
const DEBUG_MODE = sessionStorage.getItem("DEBUG_MODE");

import {order} from "/resources/scripts/common/data/order.js";
import {cart} from "/resources/scripts/common/data/cart.js";

const itemData = document.querySelector(".orderable");
function updatePrice() {
	const amountElement = itemData.querySelector("#amount");
	const priceValue = itemData.querySelector("#price").value;
	const quantityValue = itemData.querySelector("#quantity").value;

	let amountValue = Number(priceValue * quantityValue);
	amountElement.value = amountValue;
	amountElement.innerHTML =
		amountValue.toLocaleString() + " 원"
		+ "<b>(1개 당 " + Number(priceValue).toLocaleString() + " 원)</b>";
}


$(document).ready(function() {
	updatePrice();
	const quantityInput = itemData.querySelector("#quantity");
	quantityInput.addEventListener('input', () => updatePrice());
	quantityInput.addEventListener('click', () => updatePrice());

	// * 주문 요청
	const requestOrder = document.getElementById("request-order");
	requestOrder.addEventListener('click', function() {
		let requestBody = [];
		requestBody[0] = order.elementToOrder(itemData)
		order.request(requestBody);
	});

	// * 장바구니 담기
	const putCart = document.querySelector("#put-cart");
	putCart.addEventListener('click', function() {
		const requestBody = order.elementToOrder(itemData)
		cart.put(requestBody);
	});

});
