export let order = {};
const DEBUG_MODE = sessionStorage.getItem('DEBUG_MODE');

let item_id = "";
let amount  = "";

order.set = function(a, c)
{
    item_id = a;
    amount  = c;
}

order.request = function()
{
    if (!(item_id || amount)) return;

    let data = {item_id, amount};
    if (DEBUG_MODE) console.log(
        "%c주문 정보", "font-size: 16px; padding: 4px 0 4px 0;",
        "\n주문 데이터 : ", data
    );

    $.ajax({
        url: '/actions/order',
        method: 'POST',
        data: data,
        success: function(response)
        {
            if (response == "auth error")
            {
                alert("유효하지 않은 사용자입니다");
                location.reload();
            } else location.href = "/order/purchase/" + response;
        }
    });
}

