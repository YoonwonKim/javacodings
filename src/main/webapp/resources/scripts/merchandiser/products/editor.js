const DEBUG_MODE = sessionStorage.getItem("debugMode");
export let editor = [];

let object = document.getElementById('editor');
let fields = object.querySelectorAll('.field');

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
    let isOpen = (object.style.visibility == "visible");
    if (isOpen) {
        let proceed = confirm("편집 중인 항목이 있습니다. 수정을 취소하시겠습니까?");
        return proceed;
    }
    return true;
}
editor.open  = () => {

    object.style.visibility = "visible";
    object.style.display= "block";
}
editor.close = () => {
    object.style.visibility = "hidden";
    object.style.display= "none";
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
    let itemFields = object.querySelectorAll('.field');
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

editor.getImages = function(itemId)
{
    let imageList;
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

editor.setImages = function(imageList)
{

    imageGrid = object.querySelector("#image-grid");
    imageGrid.innerHTML = '';

    for( let image of imageList )
    {
        let imageObject = document.createElement("img");
        imageObject.className = image["category"];
        imageObject.src = "/resources/images/" + image['path'];
        imageObject.addEventListener("error", function(event) {
            event.target.src = "https://picsum.photos/400";
            event.onerror = null;
        })

        if(image["category"] == "desc")
        {
            let descImage = object.querySelector("#desc-image");
            descImage.replaceWith(imageObject);
        }
        else { imageGrid.append(imageObject); }
    }

    editor.addButton();
}

editor.addImage = function()
{
    const file = document.getElementById('file');
    const detailImages = object.querySelector("#image-grid");

    const fileData = file.files[0];
    imageList.push(fileData);

    let image = document.createElement("img");
    image.src = (window.url || window.webkitURL).createObjectURL(fileData);
    object.addEventListener("error", function(event) {
        event.target.src = "https://picsum.photos/400";
        event.onerror = null;
    })

    detailImages.querySelector("#detail-image-upload").remove();
    detailImages.append(image);
    if(detailImages.children.length < 8)
    {
        detailImages.append(editor.button());
        const file = document.getElementById("file");
        file.addEventListener('input', function() { editor.addImage() });
    }

    if(DEBUG_MODE) console.log(
        "%c이미지 업로드", "font-size: 16px; line-height: 20px",
        "\n파일 데이터 : ", fileData
    );
}

editor.putImages = function()
{
    let thumbnail = object.querySelector("#thumbnail").src.split("/");
    thumbnail = thumbnail[thumbnail.length - 1];
    thumbnail = {path: thumbnail, category: "thumbnail"};

    $.ajax({

    });
}



editor.addButton = function()
{
    if(imageGrid.children.length < 8)
    {
        imageGrid.append(editor.button());
        const file = document.getElementById("file");
        file.addEventListener('change', function() { editor.addImage() });
    }
}

// End Region Image
// Region Element -----------------------------------------------------

editor.button = function()
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

// End Region Element
