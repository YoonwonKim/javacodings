$(document).ready(function() {
    const amount = document.querySelector('#order-summary-total .value');
    const amountValue = Number(amount.getAttribute('value'));
    amount.innerHTML = amountValue.toLocaleString() + " 원";

    const quantityList = document.querySelectorAll("#quantity");
    for (let i = 0; i < quantityList.length; i++)
    {
        let quantity = quantityList[i];
        const value = parseInt(quantity.value);
        quantity.innerHTML = value + " 개";
    }

    const amountList = document.querySelectorAll("#amount");
    for (let i = 0; i < amountList.length; i++)
    {
        let amount = amountList[i];
        const value = parseInt(amount.value);
        amount.innerHTML = value.toLocaleString() + " 원";
    }
});

function jsf_pay() {
    try
    {
        let form = document.kcp_order_info;
        KCP_Pay_Execute( form );
    }
    catch (e)
    {
        /* IE 에서 결제 정상종료시 throw로 스크립트 종료 */
    }
}