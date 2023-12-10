sessionStorage.setItem("debugMode", true);
const DEBUG_MODE = sessionStorage.getItem("debugMode");
import {editor} from './editor.js';

$().ready(() => {
    // * Set Image Slide
    // $('#images').slick({
    //     slidesToShow: 1,
    //     slidesToScroll: 1,
    //     arrows: false,
    //     fade: true,
    //     asNavFor: '#image-nav'
    // });
    // $('#image-nav').slick({
    //     slidesToShow: 3,
    //     slidesToScroll: 1,
    //     asNavFor: '#images',
    //     dots: true,
    //     centerMode: true,
    //     focusOnSelect: true
    // });

    // * Set Modal by Item info
    let rows = document.querySelectorAll('.row');
    rows.forEach(row => {
        row.addEventListener('click', function(event) {
            if (editor.proceed())
            {
                let itemId = row.getAttribute('item-id');
                let itemData  = editor.getItem(itemId);
                let imageList = editor.getImages(itemId);

                if(DEBUG_MODE) console.log(
                    "%c에디터 화면 구성", "font-size: 16px; padding-bottom: 4px;",
                    "\n제품 아이디 : ", itemId,
                    "\n제품 정보 : ", itemData,
                    "\n제품 사진 : ", imageList,
                );

                editor.setItem(itemData);
                editor.setImages(imageList);
            }
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

            editor.action = 'add';
            editor.set(data);
        });

    editor.setEvents();
})
