$().ready(() => {
    let buttons = $('cds-table-cell.button-cell');
    for(let item of buttons) {
        let button = item
            .querySelector('#item-modal-button')
            .shadowRoot
            .querySelector('button');
        button.onclick = () => {
            let id = item.getAttribute('item-id');
            setItemInfo(id);
        }
    }
})

function setItemInfo(id) {
    let modal = document.getElementById('item-modal');
    let item;
    $.ajax({
        url: "/manager/action/get_item?item_id=" + id,
        async: false,
        type: 'POST',
        success: function(data) { item = JSON.parse(data); }
    });

    modal.querySelector('cds-modal-heading').innerHTML = item.label;
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

function setModal() {
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