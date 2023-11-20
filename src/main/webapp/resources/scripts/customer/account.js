// Region Register
function register() {
	let form = document.getElementById("register");
	let inputs = form.querySelectorAll('.input');

	let resultSet = {};
	let result = true;

	for(let i = 0; i < inputs.length; i++) {
		let input = inputs[i];
		let func   = input_validate[input.id];
		if (!func) func = input_validate['common'];

		let r = func(input);
		result = (!r) ? false : true;
		resultSet[input.id] = r;
	}

	if (result) {
		$.ajax({
			data: resultSet,
			dataType: 'json',
			method: 'PUT',
			url: '/actions/account/register',
			complete: function(data) { location.href = '/'; }
		});
	}
}
function validate_proc(input) {
}

input_validate = [];
input_validate.common = function(input) {
	let func   = validate[input.id];
	if (!func) return input.value;
	let result = func(input.value);

	if (result < 1) {
		let text = (result == 0) ?
			input.placeholder+"을/를 입력해주세요" :
			input.helperText;
		if (text == "") text = input.label;
		input.invalidText = text;
		input.invalid = true;
		return -1;
	}

	input.removeAttribute('invalid');
	return input.value;
}
input_validate['password-repeat'] = function(input) {
	let result = validate.password(input.value);
	if (result < 1) {
		let text = (result == 0) ?
			"비밀번호를 한번 더 입력해주세요" :
			input.label;
		input.invalidText = text;
		input.invalid = true;
		return -1;
	}

	input.removeAttribute('invalid');
	return input.value;
}

input_validate.member_id = function(input) {
	let result = validate.member_id(input.value);
	if (result < 1) {
		let text = (result == 0) ?
			input.placeholder+"을/를 입력해주세요" :
			input.helperText;
		input.invalidText = text;
		input.invalid = true;
		return -1;
	}

	$.ajax({
		data: {member_id: input.value},
		method: 'POST',
		url: '/actions/account/duplicate',
		success: function(data) {
			if (data == 'duplicated') {
				input.invalidText = "이미 존재하는 아이디입니다";
				input.invalid = true;
				return -1;
			}
		}
	})

	input.removeAttribute('invalid');
	return input.value;
}
// End Region Register
// Region Login
function account() {
	let member_id = document.getElementsByName("member_id")[0];
	switch (validate.member_id(member_id.value)) {
		case  0: {
			alert("아이디를 입력해주세요.");
			member_id.focus();
			return;
		}
		case -1: {
			alert("아이디 형식이 올바르지 않습니다.");
			member_id.focus();
			return;
		}
		default: member_id = member_id.value;
	}

	let password  = document.getElementsByName("password")[0];
	switch (validate.password(password.value)) {
		case  0: {
			alert("비밀번호를 입력해주세요.");
			password.focus();
			return;
		}
		case -1: {
			alert("비밀번호가 올바르지 않습니다");
			password.focus();
			password.value = "";
			return;
		}
		default: password = password.value;
	}

	$.ajax({
		method: 'POST',
		url: '/actions/account/login',
		data: {member_id, password},
		success:  function(data) {
			if (data == 'failed') alert('아이디 또는 비밀번호가 올바르지 않습니다');
			else location.reload();
		}
	});
}
// End Region Login
// Region Search
function search() {
	let name   = document.getElementById("name").value;
	let email  = document.getElementById("email").value

	let email_split = email.split("@");
	let result = validate.name(name) * validate.email(email_split[0]) * validate['email-domain']('@'+email_split[1]);
	if (result != 1) return -1;

	$.ajax({
		data: {name, email},
		method: 'GET',
		url: '/actions/account/search/id',
		success: function(data) {
			if(data != 'error') alert('고객님의 아이디는 ' + data + '입니다');
		}
	});
}

function tempPassword() {
	let name   = document.getElementById("name").value;
	let email  = document.getElementById("email").value

	let email_split = email.split("@");
	let result = validate.name(name) * validate.email(email_split[0]) * validate['email-domain']('@'+email_split[1]);
	if (result != 1) return -1;

	$.ajax({
		data: {name, email},
		method: 'POST',
		url: '/actions/account/search/password',
		success: function(data) {
			alert('임시 비밀번호는 ' + data + '입니다')
		}
	});
}
// End Region Search
