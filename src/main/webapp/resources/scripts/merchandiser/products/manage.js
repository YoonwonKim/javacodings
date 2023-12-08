sessionStorage.setItem("debugMode", true);
const DEBUG_MODE = sessionStorage.getItem("debugMode");
import {modal} from './modal.js';

$().ready(() => {

    // * Set Image Slide
    $('#images').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        fade: true,
        asNavFor: '#image-nav'
    });
    $('#image-nav').slick({
        slidesToShow: 3,
        slidesToScroll: 1,
        asNavFor: '#images',
        dots: true,
        centerMode: true,
        focusOnSelect: true
    });

    // * Set Modal by Item info
    let rows = document.querySelectorAll('.row');
    rows.forEach(row => {
        row.addEventListener('click', function(event) {
            let itemId = row.getAttribute('item-id');
            let item;

            $.ajax({
                type: 'GET',
                url: "/admin/actions/item/get",
                async: false,
                dataType: 'json',
                data: {itemId},
                success: function(data) { item = data; }
            });

            modal.action = 'edit';
            modal.set(item);
        });
    })

    // * Initialized Modal
    document.querySelector('#create')
        .addEventListener('click', function() {
            let data = {};
            data.item = {};
            data.item.label = '';
            data.item.desc  = '';
            data.item.price =  0;
            data.item.stock =  0;
            data.tags   = [];
            data.images = [];
            data.item.category = '';

            modal.action = 'add';
            modal.set(data);
        });

    // * Submit Modal
    document.querySelector('#submit')
        .addEventListener('click', () => modal.submit());
})
