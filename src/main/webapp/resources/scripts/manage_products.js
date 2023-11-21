let modal;
let id;
$().ready(() => {
    modal = document.getElementById('item-modal');

    let tags = $('cds-table-cell.item-tags');
    for(let item of tags) {
        let id = item.parentNode.getAttribute('item-id');
        let result;
        $.ajax({
            url: "/admin/actions/item/tags?item_id=" + id,
            async: false,
            type: 'GET',
            success: function(data) { result = JSON.parse(data); }
        });
        item.innerHTML = result;
    }

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
        url: "/admin/actions/item/read?item_id=" + id,
        async: false,
        type: 'GET',
        success: function(data) { result = JSON.parse(data); }
    });
    $.ajax({
        url: "/admin/actions/item/tags?item_id=" + id,
        async: false,
        type: 'GET',
        success: function(data) { result.tags = JSON.parse(data); }
    });

    setModal(result, 'update');
    document.getElementById('item-modal').open = true;
}

function create() {
    let result;
    result = {
        item_id: '',
        label: '이름을 입력해주세요',
        desc:  '상품 설명을 입력해주세요',
        image: 'z7aJr1675ceCG44iioek',
        price: '10000',
        stock: '100',
        category: '',
        tags: []
    };

    setModal(result, 'create');
    document.getElementById('item-modal').open = true;
}

// Region Modal constructor
function setModal(data, mode) {
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

    let value;
    if (mode == 'create') value = 'createItem()';
    else value = 'updateItem()';
    modal.querySelector('#submit')
        .setAttribute('onclick', value);
}
// End Region Modal constructor
// Region Modal executor
function createItem() {
    let inputs = modal.querySelectorAll('.input');
    let file = document.getElementById('file');
    let form = new FormData();

    try {
        form.append('file', file.files[0]);
        for(let i = 0; i < inputs.length; i++) {
            let input = inputs[i];
            let value = input.getAttribute('name') ?? 'image';
            form.append(value, input.getAttribute('value'));
        }
    }
    catch (e) {}

    console.log("Attempting to create");
    //! Send data to Database.
    $.ajax({
        url: "/admin/actions/item/create",
        type: 'POST',
        data: form,
        async: false,
        processData: false,
        contentType: false,
        success: function() {
            document.getElementById('item-modal').open = false;
            location.reload();
        },
    });
}
function updateItem() {
    let inputs = modal.querySelectorAll('.input');
    let result = {};
    result.item_id = id;
    for(let i = 0; i < inputs.length; i++) {
        let input = inputs[i];
        let value = input.getAttribute('name') ?? 'image';
        result[value] = input.getAttribute('value');
    }

    //! Send data to Database.
    $.ajax({
        url: "/admin/actions/item/update",
        type: 'PUT',
        data: result,
        async: false,
        success: function() {
            // Modal close
            document.getElementById('item-modal').open = false;
            location.reload();
        },
    });

    try {
        let file = document.getElementById('file');
        let form = new FormData();
        form.append('file', file.files[0]);
        form.append('item_id', id);

        //! Send image to Database.
        $.ajax({
            url: "/admin/actions/item/image",
            type: 'PUT',
            async: false,
            data: form,
            processData: false,
            contentType: false
        });
    }
    catch (e) {};
}
// End Region Modal executor
