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
// Region Events
$(document).ready(function() {
    let inputs = document.getElementsByClassName("order-state");
    for( let item of inputs) {
        item.addEventListener("input", function() {
            updateOrder(this);
        });
    };
});
// End Region Events
// Region Processing
function updateOrder(selectBox) {
    let order_id = selectBox.getAttribute("id");
    let state = selectBox.getAttribute("value");
    let order = {order_id, state};

    $.ajax({
        type: 'PUT',
        url: "/admin/actions/update_order",
        data: order,
        success: function(response) {
        }
    });
}
// End Region Processing
