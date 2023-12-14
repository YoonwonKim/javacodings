/*$(document).ready(function() {
	let emailPrefix = document.getElementById('email-prefix');
	let emailDomain = document.getElementById('email-domain');
	emailPrefix.addEventListener('input',
	function() {
		emailDomain.value = emailPrefix.value;
	});
	emailDomain.addEventListener('input',
	function() {
	    emailPrefix.value = '';
	    console.log(emailDomain.value);
	});
	
	let memberID = document.getElementById('member_id');
	memberID.addEventListener('focusout', function() {input_validate.member_id(memberID)});
	
	let form = document.getElementById("register");
	let inputs = form.querySelectorAll('.input');
	for(let i = 0; i < inputs.length; i++) {
		let input = inputs[i];
		let func   = input_validate[input.id];
		if (!func) func = input_validate['common'];
		
		input.addEventListener('focusout', function() {func(input)});
	}
});*/


function editMemberInfoByMemberId() {
	var memberData = {
		member_id: $("#member_id").val(),
		name: $("#name").val(),
		birth: $("#birth").val(),
		email: $("#emailPrefix").val() + "@" + $("#emailDomain").val(),
		phone: $("#phone1").val() + $("#phone2").val()
	};
	$.ajax({
		contentType: 'application/json',
		data: JSON.stringify(memberData),
		url: '/actions/account/editMemberInfoByMemberId',
		method: 'POST',
		success: function(response) {
		    console.log("프로필이 성공적으로 수정되었습니다.");
		}
	});
}

function archiveByMemberId() {	
	var member_id = document.getElementById('member_id').value;  // .value 추가
			console.log(member_id);
	$.ajax({
		contentType: 'application/json',
		data: JSON.stringify({member_id: member_id}),  // JSON 객체 형태로 수정
		url: '/actions/account/archiveByMemberID',
		method: 'POST',
		success: function(response) {
			console.log("성공");
		}
	});
}

//배송지변경
function updateDeliveryAddress() {
	var member_id = document.getElementById('member_id').value;
	var newZipcode = document.getElementById('sample6_postcode').value;
	var newAddress = document.getElementById('sample6_address').value;
	var newAddress2 = document.getElementById('sample6_detailAddress').value;
//	var priority = document.getElementById('priority').value;
	var priority = parseInt(document.getElementById('priority').value, 10);
	var oldZipcode = document.getElementById('oldZipcode').value;
	var oldAddress = document.getElementById('oldAddress').value;
	var oldAddress2 = document.getElementById('oldAddress2').value;
	
	// 우편번호 또는 주소가 비어있는 경우
	if (!newZipcode || !newAddress) {		
		alert("우편번호와 주소는 필수 입력 항목입니다.");
		return;
	}
	var oldAddressData = {
		oldzipcode: oldZipcode,
		oldaddress: oldAddress,
		oldaddress2: oldAddress2
	}
	
	var addressData = {        
		zipcode: newZipcode,
		address: newAddress,
		address2: newAddress2        
	};
	
	var requestData = {
        addressData: addressData,
        oldAddressData: oldAddressData,
        member_id: member_id,
        priority: priority.toString()
    };
    
	console.log(requestData);
	
	$.ajax({
		contentType: 'application/json',
		data: JSON.stringify(requestData),
		url: '/actions/account/updateDeliveryAddress',
		method: 'POST',
		success: function(response) {
			console.log("배송지가 성공적으로 변경되었습니다.");
		},
		error: function(error) {
			console.error("배송지 변경 중 오류 발생:", error);
		}
	});
}