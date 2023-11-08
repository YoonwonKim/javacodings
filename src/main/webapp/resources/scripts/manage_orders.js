$().ready(() => {
    let buttons = $('cds-table-toolbar-content');
    console.log(buttons)
    for(let item of buttons) {
        let button = item
            .querySelector('#modify')
            .shadowRoot
            .querySelector('button');
        button.onclick = () => {
            sendOrderUpdateRequest();
        }
    }
})

function sendOrderUpdateRequest() {
    // 모든 드롭다운에 대해
    $("cds-dropdown").each(function() {
        var state = $(this).attr('value');
        var order_id = $(this).attr('id').replace('state', '');
        console.log(state, order_id);

        // 각 주문에 대해 Ajax 요청을 보냄
        $.ajax({
            type: 'post',
            url: "/manager/orderUpdate",
            data: JSON.stringify({ id: order_id, state: state }),
            dataType: "json",
            success: function(response) {
                console.log("Order update successful");
                // 화면 업데이트
                $("#state" + response.id).val(response.state);
            },
            error: function(error) {
                console.log("Order update failed");
            }
        });
    });
}
