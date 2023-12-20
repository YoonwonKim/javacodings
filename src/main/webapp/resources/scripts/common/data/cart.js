const DEBUG_MODE = sessionStorage.getItem('DEBUG_MODE');
import {required} from "/resources/scripts/common/policies/required.js";

export let cart = {};

cart.put = function(orderData)
{
    let url = '/actions/cart/put';
    let data = orderData;
    if (DEBUG_MODE) console.log(
        "%c장바구니 담기", "font-size: 16px; padding: 4px 0;",
        "\n요청 주소 : ", url,
        "\n주문 데이터 : ", data
    );

    $.ajax({
        method: 'PUT',
        url: url,
        data: data,
        success: function(response)
        {
            if (required.login(response)) return;
            if (confirm('장바구니에 담았습니다.\n장바구니로 이동하시겠습니까?'))
                location.href = '/cart';
        }
    });
}

cart.delete = function(orderList) {
    let url = '/actions/cart/delete';
    let data = orderList;
    if (DEBUG_MODE) console.log(
        "%c장바구니 - 상품 삭제", "font-size: 16px; padding: 4px 0;",
        "\n요청 주소 : ", url,
        "\n주문 데이터 : ", data
    );

    $.ajax({
        method: 'POST',
        url: url,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(response)
        {
            if (required.login(response)) return;
            location.reload();
        }
    });
}
