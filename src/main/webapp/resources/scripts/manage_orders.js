$().ready(() => {
    let buttons = $('cds-table-toolbar-content');
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
    let data = [];
    $("cds-dropdown").each(function() {
        let column = {};
        column.order_id = $(this).attr('id').replace('state', '');
        column.state = $(this).attr('value');
        data.push(column);
    });
    data = JSON.stringify(data);
    console.log(data);

    // 각 주문에 대해 Ajax 요청을 보냄
    $.ajax({
        type: 'post',
        url: "/admin/actions/update_order",
        data: data,
        dataType: "json",
        contentType: 'application/json',
        success: function(response) {
            console.log("Order update successful");
            // 화면 업데이트
            $("#state" + response.id).val(response.state);
        },
        error: function(error) {
            console.log("Order update failed");
        }
    });
}
