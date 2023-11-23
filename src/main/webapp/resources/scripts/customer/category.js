let grid;
let template;

let category = location.href.split('/');
category = category[category.length - 1];

let page = 1;
let page_total;
let row  = 8;

let reachedFoot = 0;

function getPage() {
    let result;
    $.ajax({
        data: {page, row},
        url: '/actions/product/list/' + category,
        method: 'POST',
        async: false,
        success: function (response) {
            result = response;
        }
    });

    return result;
}

function appendTiles(response) {
    if(response == 'outbound page') return 0;

    page_total = response['totalPages'];
    let itemList = response['objectList']
    for(let i = 0; i < itemList.length; i++) {
        let item = itemList[i];
        let item_tile = template.cloneNode(true);

        item_tile.href = '/product/' + item['item_id'];
        item_tile.querySelector('#item-image').src = '/resources/images/' + item.image + '.png';
        item_tile.querySelector('#item-label').innerText = item.label;
        item_tile.querySelector('#item-price').innerHTML = item.price.toLocaleString('en-US') + ' 원';

        grid.append(item_tile)
    }
}

$(document).ready(function() {
    grid = document.getElementById("grid")
    template = document.getElementById('item');
    template.remove()
    appendTiles(getPage());

    observer.observe(document.querySelector('footer'));
});

let observer = new IntersectionObserver(
    function() {
        reachedFoot += 1;
        if (reachedFoot%2 != 0) return;

        page += 1;
        appendTiles(getPage());
    },
    {
        root: null,
        threshold: .5
    }
);