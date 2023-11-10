/**
 * 
 */
$().ready(function(){
		
		
		$('#submitTop').on('click',function(){
			var userId = $('#user').val();
			var userPw = $('#pass').val();
			
			var flen = $("form[name=topForm] .chkt").length;
		for(var i=0; i<flen; i++){
			if($('.chkt').eq(i).val()=="" ||
		       $('.chkt').eq(i).val()==null ||
		       $('.chkt').eq(i).val().trim()==""){
			  alert($('.chkt').eq(i).attr('title')+'를 입력하시오');
			  $('.chkt').eq(i).focus();
			  return false;
			}
		}
			console.log(userId, userPw)
		$.ajax({
			
			type:'post',
			url:"/actions/login",
			data:{
				"member_id":userId,
				"password":userPw
			},
	
			success:function(data){
				if(data == 'failed'){
					alert("아이디/비밀번호를 다시 확인해주세요");
					location.href="/loginpage";
				}else{
					location.href="/";
				}
			}
		})
		}); 
		})