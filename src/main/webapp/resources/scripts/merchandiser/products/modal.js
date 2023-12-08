const DEBUG_MODE = sessionStorage.getItem("debugMode");
export let modal = [];

let template = document.getElementById('modal-template');
let fields = template.querySelectorAll('.field');

modal.action = 'edit';

modal.set = function(data)
{
    let value_str;
    for( let field of fields )
    {
        let value = field.getAttribute('data').split('.');
        let table  = value[0];

        if (table === "images")
        {
            field.innerHTML = '';
            for(let image of data[table])
            {
                if (image['category'] != "detail") continue;

                let pwd = document.createElement("img");
                pwd.src = "/resources/images/" + image['path'];
                field.appendChild(pwd);
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

    template.open = true;
}

modal.submit = function()
{
    let data = modal.putItem(modal.action);
    let form = modal.putImages(data.item_id);

    if(DEBUG_MODE) console.log(
        "\n%cSUBMIT MODIFIED ITEM INFO\n", "font-size: 16px",
        "\naction : ", modal.action,
        "\nfields : ", fields,
        "\nmodify : ", data,
        "\nimages : ", form,
        "\n\n"
    );

    template.open = false;
}

modal.putImages = function(itemId)
{
    console.log(itemId);
    try {
        let file = document.getElementById('file');
        let form = new FormData();
        console.log(file)
        form.append('file', file.files[0]);
        form.append('itemId', itemId);

        //! Send image to Database.
        $.ajax({
            url: "/admin/actions/item/image/add",
            type: 'PUT',
            async: false,
            data: form,
            processData: false,
            contentType: false
        });
        return form;
    }
    catch (e) {};
}

modal.putItem = function(action)
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