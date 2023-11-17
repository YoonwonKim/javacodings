$(document).ready(function() {
    let name = document.getElementById('name');
    name.addEventListener('focusout', function () {
        input_validate.common(name)
    });

    let email = document.getElementById('email');
    email.addEventListener('focusout', function () {
        let pattern = /^([A-Za-z0-9])+@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        let email_value = email.value;
        if (!email_value) {
            email.invalidText = "이메일을 입력해주세요";
            email.invalid = true;
        } else if (!pattern.test(email_value)) {
            email.invalidText = "올바르지 않은 형식의 이메일입니다";
            email.invalid = true;
        } else email.removeAttribute('invalid');
    });
});
