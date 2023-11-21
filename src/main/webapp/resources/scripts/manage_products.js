let modal;
let id;
$().ready(() => {
    modal = document.getElementById('item-modal');

    let tags = $('cds-table-cell.item-tags');
    for(let item of tags) {
        let id = item.parentNode.getAttribute('item-id');
        let result;
        $.ajax({
            url: "/admin/actions/get_tags?item_id=" + id,
            async: false,
            type: 'GET',
            success: function(data) { result = JSON.parse(data); }
        });
        item.innerHTML = result;
    }

    $('cds-modal-footer-button#update').on('click', () => {
        sendInfo();
    });

    document.getElementById('file').addEventListener('change', function() {
        let file = $("input#file").val();
        file = file.split('\\');
        document.getElementById('uploaded-file').innerHTML = file[file.length - 1];
    });
})

function modify(item) {
    id = item.closest('.row').getAttribute('item-id');
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

// Region Modal constructor
function setModal(id, data) {
    let inputs = modal.querySelectorAll('.input');
    for(let i = 0; i < inputs.length; i++) {
        let input = inputs[i];
        let value = input.getAttribute('name') ?? 'image';

        if (value === 'image')
            input.setAttribute('src', '/resources/images/'+data[value]+'.png');
            input.setAttribute('onerror', 'this.onerror=null;' +
                'this.src=\'/resources/images/z7aJr1675ceCG44iioek.png\'');
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

    let file = document.getElementById('file');
    let form = new FormData();
    form.append('file', file.files[0]);

    //! Send data to Database.
    $.ajax({
        url: "/admin/actions/set_item?item_id=" + id,
        type: 'PUT',
        data: result,
        async: false,
        success: function() {
            // Modal close
            document.getElementById('item-modal').open = false;
            location.reload();
        },
    });
    //! Send image to Database.
    $.ajax({
        url: "/admin/actions/set_image?item_id=" + id,
        type: 'PUT',
        async: false,
        data: form,
        processData: false,
        contentType: false
    });
}
// End Region Modal executor
