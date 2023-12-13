const DEBUG_MODE = sessionStorage.getItem('DEBUG_MODE');

import {required} from "/resources/scripts/common/policies/required.js";

export let orderable = {};
let amount = 0;


orderable.setEvents = function()
{
    // * 주문 수량 변경
    const orderQuantity = document.getElementById('order-quantity');
    orderQuantity.addEventListener('input', function() {
        setItemAmount(this.closest(".order"));
    });
}

orderable.getCartData = function(element)
{
    let itemId = element.getAttribute('item-id');
    let amount = element.querySelector("#order-amount .field").getAttribute('value');
    return {item_id: itemId, amount};
}

orderable.putCart = function(requestBody)
{
    if (DEBUG_MODE) console.log(
        "%c장바구니 담기", "font-size: 16px; padding: 4px 0;",
        "\n요청 전문 : ", requestBody
    );

    $.ajax({
        url: '/actions/cart/put',
        method: 'PUT',
        data: requestBody,
        success: function(response)
        {
            if (required.login(response)) return;
            if (confirm('장바구니에 담았습니다.\n장바구니로 이동하시겠습니까?'))
                location.href = '/cart';
        }
    });
}

function setItemAmount(card)
{
    const amountElement = card.querySelector("#order-amount .field");
    const price  = amountElement.getAttribute("price");
    const quantity = card.querySelector("#order-quantity").value;
    amount = price * quantity;

    amountElement.setAttribute("value", amount);
    amountElement.innerHTML = amount.toLocaleString('en-US') + ' 원';
}
