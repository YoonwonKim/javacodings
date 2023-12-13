const DEBUG_MODE = sessionStorage.getItem("debugMode");
export let editor = [];

let editorElement = document.getElementById('editor');
let fields = editorElement.querySelectorAll('.field');

let itemId = "";
let itemData;
let imageList = [];
let imageGrid;


// Region Page -----------------------------------------------------

// * --------------------------------
// * 이벤트 설정
// * --------------------------------

editor.setEvents = function()
{
    // * 데이터 테이블 상품 열기

    let rows = document.querySelectorAll('.row');
    rows.forEach(row => {
        row.addEventListener('click', function(event) {
            if (!editor.proceed()) return;

            itemId = row.getAttribute('item-id');
            editor.getItem(itemId);
            editor.setItem();

            editor.getImages(itemId);
            editor.setImages();

            if(DEBUG_MODE) console.log(
                "%c에디터 화면 구성", "font-size: 16px; padding-bottom: 4px;",
                "\n제품 아이디 : ", itemId,
                "\n제품 정보 : ", itemData,
                "\n제품 사진 : ", imageList,
            );
            editor.open();
        });
    })

    // * 새 상품 추가
    const createButton = document.querySelector('#create');
    createButton.addEventListener('click', function() {
        if (!editor.proceed()) return;

        itemId = "";
        itemData = {};
        imageList = [];

        editor.setItem();
        editor.setImages();
        editor.open();
    });

    // * 수정한 내용 전송

    document.querySelector('#editor-submit')
        .addEventListener('click', () => editor.submit());
}

// * --------------------------------
// * 이벤트 동작
// * --------------------------------

editor.open  = function()
{
    editorElement.style.visibility = "visible";
    editorElement.style.display= "block";
}

editor.close = function()
{
    editorElement.style.visibility = "hidden";
    editorElement.style.display= "none";
}

editor.proceed = function()
{
    let isOpen = (editorElement.style.visibility == "visible");
    if (isOpen) return confirm("편집 중인 항목이 있습니다. 수정을 취소하시겠습니까?");
    return true;
}

editor.submit = function()
{
    if(DEBUG_MODE) console.log(
        "\n%c데이터 전송\n", "font-size: 16px",
        "\n상품 정보 : ", itemData,
        "\n상품 이미지 : ", imageList,
        "\n\n"
    );

    let itemId = editor.putItem();
    editor.putImages(itemId);
    location.reload();
}

// End Region Page
// Region Item -----------------------------------------------------

// * --------------------------------
// * 서버와 데이터 교환
// * --------------------------------

editor.getItem = function(itemId)
{
    $.ajax({
        type: 'GET',
        url: "/admin/actions/item/get",
        async: false,
        dataType: 'json',
        data: {itemId},
        success: function(data) { itemData = data; }
    });
}

editor.putItem = function()
{
    let item = {};
    let tags = {};

    for(let field of editorElement.querySelectorAll('.field'))
    {
        let data = field.getAttribute('data');
        if(data === "tags") { tags.tags = field.value.split(','); continue; }

        item[data] = field.getAttribute('value');
    }

    item["item_id"] = itemId;

    let item_id;
    let data = Object.assign(item, tags);
    $.ajax({
        type: 'PUT',
        url: "/admin/actions/item/put",
        async: false,
        data: data,
        success: (data) => item_id = data
    });

    if(DEBUG_MODE) console.log(
        "PUT ITEM",
        "\nParameter : ", data,
        "\nReturn : ", item_id
    );

    return item_id;
}

// * --------------------------------
// * 페이지 구성
// * --------------------------------

editor.setItem = function()
{
    let itemFields = editorElement.querySelectorAll('.field');
    for(let field of itemFields)
    {
        let data = field.getAttribute('data');
        let value = itemData[data];
        field.setAttribute('value', value);
    }

    editor.open();
}

// End Region Item
// Region Image -----------------------------------------------------

// * --------------------------------
// * 서버와 데이터 교환
// * --------------------------------

editor.getImages = function(itemId)
{
    $.ajax({
        type: 'GET',
        url: "/admin/actions/item/get/images",
        async: false,
        dataType: 'json',
        data: {itemId},
        success: function(data) { imageList = data; }
    });

    return imageList;
}

editor.putImages = function(itemId)
{
    let form = new FormData();
    let fileList = [];
    for(let image of imageList)
    {
        const isBlob = image.path.split("blob:").length > 1;
        if(isBlob){
            form.append("fileList", image["file"]);
            image["path"] = image["file"].name;
            delete image["file"];
        }
    }

    form.append("imageList", JSON.stringify(imageList));
    form.append("itemId", itemId);

    let returnData;
    $.ajax({
        type: 'PUT',
        enctype: 'multipart/form-data',
        url: "/admin/actions/item/put/images",
        data: form,
        processData: false,
        contentType: false,
        success: function(data) {returnData = data}
    });

    if(DEBUG_MODE) console.log(
        "PUT IMAGES",
        "\nReturn : ", returnData
    );
}

// * --------------------------------
// * 페이지 구성
// * --------------------------------

editor.setImages = function()
{
    imageGrid = editorElement.querySelector("#image-grid");
    let descImage = editorElement.querySelector("#desc");
    imageGrid.innerHTML = '';
    descImage.innerHTML = '';

    for( let image of imageList )
    {
        let imageElement = editor.imageElement(image);
        if(image["category"] == "desc") descImage.append(imageElement);
        else { imageGrid.append(imageElement); }
    }

    editor.addButton();
}

editor.addButton = function()
{
    try {editorElement.querySelector("#detail-image-upload").remove();}
    catch (error) {}
    if(imageGrid.children.length < 8)
    {
        imageGrid.append(editor.imageUploadButton());
        const file = document.getElementById("file");
        file.addEventListener('change', function() {
            editor.uploadImage();
        });
    }
}

// * --------------------------------
// * 이벤트 동작
// * --------------------------------

editor.setThumbnail = function(newThumbnail)
{
    let thumbnailPath = newThumbnail.src.split("/");
    thumbnailPath = thumbnailPath[thumbnailPath.length - 1];

    for(let image of imageList)
    {
        switch (image["category"])
        {
            case "desc": continue;
            case "thumbnail": image["category"] = "detail";
            default:
            {
                let imagePath = image["path"].split("/");
                imagePath = imagePath[imagePath.length - 1];

                if(thumbnailPath == imagePath) image["category"] = "thumbnail";
            }
        }
    }

    editor.setImages();
}

editor.uploadImage = function()
{
    const file = document.getElementById('file');
    const detailImages = editorElement.querySelector("#image-grid");

    const fileData = file.files[0];

    let imageData = {
        path: (window.url || window.webkitURL).createObjectURL(fileData),
        category: 'detail',
        file: fileData
    };
    let imageElement = editor.imageElement(imageData);
    imageList.push(imageData);

    if(DEBUG_MODE) console.log(
        "%c이미지 업로드", "font-size: 16px; line-height: 20px",
        "\n파일 데이터 : ", fileData
    );

    detailImages.append(imageElement);
    editor.addButton();
}

// End Region Image
// Region Element -----------------------------------------------------

editor.imageUploadButton = function()
{
    let button = document.createElement("input");
    button.id   = "file";
    button.type = "file";
    button.accept = "image/png"
    button.hidden = true;

    let label = document.createElement("label");
    label.id  = "image-upload";
    label.innerHTML = "<p>이미지 업로드</p>";
    label.setAttribute("for", "file");

    let imageUpload = document.createElement("div");
    imageUpload.id = "detail-image-upload"
    imageUpload.append(label);
    imageUpload.append(button);
    return imageUpload;
}

editor.imageElement = function(imageData)
{
    let imageElement = document.createElement("img");
    imageElement.className = imageData["category"];

    let isBlob = imageData.path.split("blob:").length > 1;
    imageElement.src = ((isBlob)? "" : "/resources/images/") + imageData["path"];

    imageElement.addEventListener("error", function(event) {
        event.target.src = "https://picsum.photos/400";
        event.onerror = null;
    });
    imageElement.addEventListener("click", function() {
        editor.setThumbnail(this);
    });

    return imageElement;
}

// End Region Element
