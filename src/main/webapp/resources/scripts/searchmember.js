$(document).ready(function() {
    $(".btn").click(function(e) {
        e.preventDefault(); // 기본 이벤트를 중지시킵니다.

        // 아이디 찾기 버튼이 클릭되었을 경우
        if($(this).text() === "아이디 찾기") {
            var name = $("#name").val();
            var phone = $("#phone").val();

            // 입력 값을 검증합니다.
            if (!name || !phone) {
                alert("이름과 연락처를 모두 입력해주세요.");
                return;
            }
            
            $.ajax({
				type: "POST",
				url: "/actions/searchId",
				data: {name, phone},
				success: function(msg) {
				alert(msg);
				}
			})
        }
        // 비밀번호 변경 버튼이 클릭되었을 경우
        else if($(this).text() === "비밀번호 변경") {
            var id = $("#member_id").val();
            var phone = $("#pwphone").val();
            var password = $("#password").val();
            var password2 = $("#password2").val();

            // 입력 값을 검증합니다.
            if (!id || !phone || !password || !password2) {
                alert("아이디, 연락처, 비밀번호, 비밀번호 확인을 모두 입력해주세요.");
                return;
            }
            if (password !== password2) {
                alert("비밀번호가 일치하지 않습니다.");
                return;
            }
            
            $.ajax({
				type: "POST",
				url: "/actions/updatePasswd",
				data: {id, phone, password, password2},
				success: function(msg) {
					alert("비밀번호가 변경되었습니다.");
				}
			})
        }

        // 검증이 끝난 후 폼을 제출합니다.
        //$(this).closest("form").submit();
        
    });
});
