// Region Data processing
function get_url() {
    let url = window.location.href;
    let params = {page: 1, row: 15};

    // Get Page
    let page = 1;
    try {
        let page_assume = url.split('page=');
        page = page_assume[1].split('&')[0];
    }
    catch (e) {};

    // Get Row
    let row = 15;
    try {
        let row_assume = url.split('row=');
        row = row_assume[1].split('&')[0];
    }
    catch (e) {};

    // Get Actual URL
    try {
        url = url.split('?')[0];
    }
    catch (e) {};

    params = {page, row}
    return [url, params];
}
// End Region Data processing
// Region Event
function prev_page() {
    let url = get_url();
    location.href = url[0] + '?page=' + (parseInt(url[1].page) - 1) + '&row=' + url[1].row;
}
function next_page() {
    let url = get_url();
    location.href = url[0] + '?page=' + (parseInt(url[1].page) + 1) + '&row=' + url[1].row;
}
$(document).ready(function() {
    document.getElementById('page')
        .addEventListener('input',
            function () {
                let url = get_url();
                let page = document.getElementById('page').value;
                location.href = url[0] + '?page=' + page + '&row=' + url[1].row;
            });

    document.getElementById('row')
        .addEventListener('input',
            function () {
                let url = get_url();
                let row = document.getElementById('row').value;
                location.href = url[0] + '?page=' + url[1].page + '&row=' + row;
            });
});
// End Region Event
