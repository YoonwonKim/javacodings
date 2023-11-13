let modal = document.getElementById('item-modal');
let id;
$().ready(() => {
    let buttons = $('cds-table-cell.button-cell');
    for(let item of buttons) {
        let button = item
            .querySelector('#item-modal-button')
            .shadowRoot
            .querySelector('button');
        button.onclick = () => {
            id = item.getAttribute('item-id');
            let result;
            $.ajax({
                url: "/admin/actions/get_item?item_id=" + id,
                async: false,
                type: 'GET',
                success: function(data) { result = JSON.parse(data); }
            });
            $.ajax({
                url: "/admin/actions/get_tags?item_id=" + id,
                async: false,
                type: 'GET',
                success: function(data) { result.tags = JSON.parse(data); }
            });

            setModal(id, result);
            document.getElementById('item-modal').open = true;
        }
    }

    $('cds-modal-footer-button#update').on('click', () => {
        sendInfo();
    });
})

// Region Modal constructor
function setModal(id, data) {
    let inputs = modal.querySelectorAll('.input');
    for(let i = 0; i < inputs.length; i++) {
        let input = inputs[i];
        let value = input.getAttribute('name') ?? 'image';

        if (value === 'image')
            input.setAttribute('src', '/resources/images/'+data[value]);
        input.setAttribute('value', data[value]);
    }
}
// End Region Modal constructor
// Region Modal executor
function sendInfo() {
    let inputs = modal.querySelectorAll('.input');
    let result = {};
    for(let i = 0; i < inputs.length; i++) {
        let input = inputs[i];
        let value = input.getAttribute('name') ?? 'image';
        result[value] = input.getAttribute('value');
    }

    //! Send data to Database.
    $.ajax({
        url: "/admin/actions/set_item?item_id=" + id,
        type: 'PUT',
        dataType: 'json',
        data: result,
        success: function() {
            // Modal close
            document.getElementById('item-modal').open = false;
        }
    });
}
// End Region Modal executor
