sessionStorage.setItem("DEBUG_MODE", true);
const DEBUG_MODE = sessionStorage.getItem("DEBUG_MODE");

import {orderable} from "/resources/scripts/customer/orderable.js";
import {order} from "/resources/scripts/common/order.js";


$(document).ready(function() {
	orderable.setEvents();

	// * 주문 요청
	const requestOrder = document.getElementById("request-order");
	requestOrder.addEventListener('click', function() {
		const article = document.getElementById("item-metadata");
		const requestBody = [orderable.getCartData(article)];
		order.request(requestBody);
	});

	// * 장바구니 담기
	const putCart = document.querySelector("#put-cart");
	putCart.addEventListener('click', function() {
		const article = document.getElementById("item-metadata");
		const requestBody = orderable.getCartData(article);
		orderable.putCart(requestBody);
	});
});

