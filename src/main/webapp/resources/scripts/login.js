function login() {
	let member_id = document.getElementsByName("member_id")[0];
	switch (validate_id(member_id.value)) {
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
	switch (validate_pw(password.value)) {
		case  0: {
			alert("비밀번호를 입력해주세요.");
			password.focus();
			return;
		}
		case -1: {
			alert("비밀번호가 올바르지 않습니다.");
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
		dataType: 'json',
		complete: function(data) { location.reload(); }
	})
}