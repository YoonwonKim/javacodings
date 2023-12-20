const DEBUG_MODE = sessionStorage.getItem('DEBUG_MODE');

export let orderable = {};
let amount = 0;

function setItemAmount(card)
{
    const amountElement = card.querySelector("#order-amount .field");
    const price  = amountElement.getAttribute("price");
    const quantity = card.querySelector("#order-quantity").value;
    amount = price * quantity;

    amountElement.setAttribute("value", amount);
    amountElement.innerHTML = amount.toLocaleString('en-US') + ' 원';
}
