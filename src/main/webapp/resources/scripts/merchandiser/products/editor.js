const DEBUG_MODE = sessionStorage.getItem("debugMode");
export let editor = [];

let editorElement = document.getElementById('editor');
let fields = editorElement.querySelectorAll('.field');

let itemId = "";
let imageList = [];
let imageGrid;

editor.setEvents = function()
{
    // * Submit Modal
    document.querySelector('#editor-submit')
        .addEventListener('click', () => editor.submit());
}











editor.proceed = () => {
    let isOpen = (editorElement.style.visibility == "visible");
    if (isOpen) {
        let proceed = confirm("편집 중인 항목이 있습니다. 수정을 취소하시겠습니까?");
        return proceed;
    }
    return true;
}
editor.open  = () => {

    editorElement.style.visibility = "visible";
    editorElement.style.display= "block";
}
editor.close = () => {
    editorElement.style.visibility = "hidden";
    editorElement.style.display= "none";
}

editor.action = 'edit';

editor.set = function(data)
{
    let value_str;
    for( let field of fields )
    {
        let value = field.getAttribute('data').split('.');
        let table  = value[0];

        if (table === "images")
        {
            field.innerhtml = '';
            for(let image of data[table])
            {
                if (image['category'] != "detail") continue;

                let pwd = document.createelement("img");
                pwd.src = "/resources/images/" + image['path'];
                field.appendchild(pwd);
            }
        }
        else if(table === "tags")
        {
            field.setAttribute("value", data["tags"].toString());
        }
        else
        {
            let column = value[1];
            value = data[table][column];
            field.setAttribute('value', value);
        }
    }

    if(DEBUG_MODE) console.log(
        "\n%cCONSTRUCT MODAL BY ITEM INFO\n", "font-size: 16px",
        "\nfields : ", fields,
        "\nparams : ", data,
        "\n\n"
    );

    editor.open();
}

editor.submit = function()
{
    let data = editor.putItem(editor.action);
    let imageForm = editor.putImages();

    if(DEBUG_MODE) console.log(
        "\n%c데이터 전송\n", "font-size: 16px",
        "\naction : ", editor.action,
        "\n상품 정보 : ", data,
        "\n상품 이미지 : ", form,
        "\n\n"
    );

    //! Send image to Database.
    // $.ajax({
    //     url: "/admin/actions/item/image/add",
    //     type: 'PUT',
    //     async: false,
    //     data: form,
    //     processData: false,
    //     contentType: false
    // });
    //
    //
    // editor.close();
}



// Region Item -----------------------------------------------------

editor.getItem = function(itemId)
{
    let itemData;
    $.ajax({
        type: 'GET',
        url: "/admin/actions/item/get",
        async: false,
        dataType: 'json',
        data: {itemId},
        success: function(data) { itemData = data; }
    });

    return itemData;
}

editor.setItem = function(itemData)
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

editor.putItem = function()
{

}
editor.putItem = function(action)
{
    let item = {};
    let tags = {};

    for(let field of fields)
    {
        let value = field.getAttribute('data').split('.');
        let table  = value[0];

        if(table === "images") continue;
        if(table === "tags") { tags.tags = field.value.split(','); continue; }

        let column = value[1];
        item[column] = field.getAttribute('value');
    }

    let data = Object.assign(item, tags);
    let item_id;
    $.ajax({
        type: 'PUT',
        url: "/admin/actions/item/" + action,
        async: false,
        data: data,
        success: (data) => item_id = data
    });

    if (item_id != "error") data.item_id = item_id;
    return data;
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

editor.putImages = function()
{
    console.log(imageList);

    $.ajax({

    });
}

// * --------------------------------
// * 페이지 구성
// * --------------------------------

editor.setImages = function()
{
    imageGrid = editorElement.querySelector("#image-grid");
    imageGrid.innerHTML = '';

    for( let image of imageList )
    {
        let imageElement = editor.imageElement(image);
        if(image["category"] == "desc")
        {
            let descImage = editorElement.querySelector(".desc");
            descImage.replaceWith(imageElement);
        }
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
