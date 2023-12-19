const DEBUG_MODE = sessionStorage.getItem('DEBUG_MODE');
import {required} from "/resources/scripts/common/policies/required.js";

export let order = {};

/*
 * HTML 요소에서 주문 정보 가져오기
 * 주문 정보를 담은 요소는 아래와 같이 구성된다 :
 * <data class="orderable" id="${item_id}">
 *      <data id="price" value="${price}">
 *      <data id="amount" value="${amount}">
 * </data>
 */

order.elementToOrder = function(element)
{
    let orderData = {};
    orderData["item_id"] = element.getAttribute("id");
    orderData["amount"]  = element.querySelector("#amount").value;
    return orderData;
}

// * 주문 요청하기
order.request = function(orderList)
{
    let url = '/actions/order/request';
    let data = {itemList: orderList};
    if (DEBUG_MODE) console.log(
        "%c주문 요청", "font-size: 16px; padding: 4px 0;",
        "\n요청 주소 : ", url,
        "\n주문 데이터 : ", data
    );

    $.ajax({
        method: 'POST',
        url: url,
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
