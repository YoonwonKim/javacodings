/**
 * 
 */
$().ready(function(){
	var idchk=1;
	var pcheck = true;
/*	
	$('#btn-id').on('click', function() {
		id_check();
	});*/
	
	$('#submit11').on("click", function(){
		
			if(validate()){
				if(idchk==1){
					alert('아이디 중복\n 다시 해주세요')
					$('#idchk').focus();
					return false;
				}
				if(pcheck){
					alert('패스워드가 다릅니다.');
					return false;
				}
				
				$('form').submit();
				}
		});
		
		
	
	$('#idchk').on('propertychange change input paste',function(){
		$.ajax({
			async:true,
			type:'post',
			url:'idCheck',
			data:{'member_id':$('#idchk').val()},
			dataType:"json",
			success:function(data){
				if(data>0){//이미 존재하는 id
					$('font[id=warning]').text('');
					$('font[id=warning]').attr('color','red');
					$('font[id=warning]').text('이미 존재하는 아이디 입니다.');
					$('#idchk').focus();
					idchk =1; //submit 불가
				}else{//사용가능한 id
					$('font[id=warning]').text('');
					$('font[id=warning]').attr('color','blue');
					$('font[id=warning]').text('사용가능한 아이디 입니다..');
					$('#idchk').focus();
					idchk=0; //전송가능
				}
			}
		});
		
	});
	
	$('#check1 , #check2').keyup(function() {
		$('font[id=check]').text('');
		if($('#check1').val()!=$('#check2').val()){
			$('font[id=check]').text('');
			$('font[id=check]').attr('color', 'red');
			$('font[id=check]').text('패스워드 다름');
			pcheck=true;
		}else{
			$('font[id=check]').text('');
			$('font[id=check]').attr('color', '#008000');
			$('font[id=check]').text('패스워드 같음');
			pcheck=false;
		}
	});
	//출생년도 코드
	setDateSelectBox();
})

function validate(){
		var flen = $("form[name=form1] .chk").length;
		for(var i=0; i<flen; i++){
			if($('.chk').eq(i).val()=="" ||
		       $('.chk').eq(i).val()==null ||
		       $('.chk').eq(i).val().trim()==""){
			  alert($('.chk').eq(i).attr('title')+'은/는 필수입력');
			  $('.chk').eq(i).focus();
			  return false;
			}
		}
		
		return true;
		/*var str = document.form1.email.value;
		var atPos = str.indexOf('@');
		var atLastPos = str.lastIndexOf('@');
		var dotPos = str.indexOf('.');
		var spacePos = str.indexOf(' ');
		var commaPos = str.indexOf(',');
		var eMailSize = str.length;
		if(!(atPos > 1 && atPos==atLastPos &&
		   dotPos > 3 && spacePos==-1 && commaPos ==-1 &&
		   atPos+1<dotPos && dotPos+1 < eMailSize)){
			alert('Email 주소 형식이 잘못되었습니다.\n다시 입력해 주시오.')
		    document.form1.email.focus();
		    return false;
		   }
		return true;*/
	} 

function setDateSelectBox(){
	var now = new Date();
	var now_year = now.getFullYear();
	var month = now.getMonth()+1;
	var day = now.getDay;
	
	// 1950년 부터 올해까지
	$("#year").append("<option value=''>년도</option>");
	for(var i = now_year; i >= 1950; i--){
	$("#year").append("<option value='"+ i +"'>"+ i + " 년" +"</option>");
	}
	
	$("#month").append("<option value=''>월</option>");
	for (var i = 1; i <= 12; i++) {
      $("#month").append("<option value='" + i + "'>" + i + " 월" + "</option>");
    }
    
    $("#day").append("<option value=''>일</option>");
    for (var i = 1; i <= 31; i++) {
      $("#day").append("<option value='" + i + "'>" + i + " 일" + "</option>");
    }
}


