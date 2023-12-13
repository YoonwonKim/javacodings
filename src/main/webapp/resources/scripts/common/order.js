export let order = {};
const DEBUG_MODE = sessionStorage.getItem('DEBUG_MODE');

import {required} from "/resources/scripts/common/policies/required.js";

order.request = function(cartList)
{
    let data = {itemList: cartList};
    if (DEBUG_MODE) console.log(
        "%c주문 정보", "font-size: 16px; padding: 4px 0;",
        "\n주문 데이터 : ", data
    );

    $.ajax({
        url: '/actions/order',
        method: 'POST',
        async: false,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success: function(response)
        {
            if (required.login(response)) return;
            location.href = "/order/purchase/" + response;
        }
    });
}
