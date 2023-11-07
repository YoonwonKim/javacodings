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
            // setModal(id, 'data');
            setItemInfo(id);
        }
    }

    $('cds-modal-footer-button#update').on('click', () => {
        updateItem();
    });
})

// Region Modal executor
function updateItem() {
    let modal = document.getElementById('item-modal');

    //? Get Item metadata
    let item = {};
    item.label = modal.querySelector('[name="label"]').getAttribute('value');
    item.desc  = modal.querySelector('[name="desc" ]').getAttribute('value');
    item.price = modal.querySelector('[name="price"]').getAttribute('value');
    item.stock = modal.querySelector('[name="stock"]').getAttribute('value');

    //? Get Tags
    let tags = {};
    tags.tags = modal
        .querySelector('cds-multi-select')
        .getAttribute('value');

    //! Send data to Database.
    $.ajax({
        url: "/admin/actions/set_item?item_id=" + id,
        type: 'POST',
        dataType: 'json',
        data: Object.assign(item, tags),
        success: function(data) {
            // Modal close
            document.getElementById('item-modal').open = false;
        }
    });
}
// End Region Modal executor
// Region Modal constructor
function setModal(id, data) {
    let modal = document.getElementById('item-modal');

    let inputs = modal.querySelectorAll('.input');
    for(let input of inputs) {
        let category = input.getAttribute('category');
        let name = input.getAttribute('name');
        console.log(category, name);
        input.setAttribute('value', data[category][name]);
    }
    // let image = modal.querySelector('img');
    // let url = '/resources/images/';
    // let category = image.getAttribute('category');
    // image.setAttribute('src', url+data[category]['image']);

    // modal.querySelector('cds-number-input[name="price"]')
    //     .setAttribute('value', item.price);
    // modal.querySelector('cds-number-input[name="stock"]')
    //     .setAttribute('value', item.stock);
    // modal.querySelector('cds-text-input[name="label"]')
    //     .setAttribute('value', item.label);
    // modal.querySelector('cds-text-input[name="desc"]')
    //     .setAttribute('value', item.desc);
    //
    // modal.querySelector('cds-select-item[value="'+item.category+'"]')
    //     .setAttribute('selected', true);

    // modal.querySelector('cds-multi-select')
    //     .setAttribute('value', tags);

    // Open Modal
    document.getElementById('item-modal').open = true;
}


function setItemInfo(id) {
    //? Get Item metadata from Database.
    let item;
    $.ajax({
        url: "/admin/actions/get_item?item_id=" + id,
        async: false,
        type: 'POST',
        success: function(data) { item = JSON.parse(data); }
    });

    let tags;
    $.ajax({
        url: "/admin/actions/get_tags?item_id=" + id,
        async: false,
        type: 'POST',
        success: function(data) { tags = JSON.parse(data); }
    });

    setModal(id, {item, tags});
}
function setMosdal() {
    let modal = document.getElementById('item-modal');

    modal.querySelector('cds-modal-heading').innerHTML = 'THIS';
    modal.querySelector('cds-modal-body-content').innerHTML = item.desc;

    modal.querySelector('cds-number-input[name="price"]')
        .setAttribute('value', item.price);
    modal.querySelector('cds-number-input[name="stock"]')
        .setAttribute('value', item.stock);
    modal.querySelector('cds-text-input[name="label"]')
        .setAttribute('value', item.label);
    modal.querySelector('cds-text-input[name="desc"]')
        .setAttribute('value', item.desc);
    let image = modal.querySelector('img')
    let url = '/resources/images/';
    image.setAttribute('src', url+item.image);

    document.getElementById('item-modal').open = true;
}
// End Region Modal constructor
