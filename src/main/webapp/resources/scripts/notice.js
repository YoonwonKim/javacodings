$().ready(function(){
	$('.noticeDel').on('click',function(){
		var notice_id = $('#notice_id').val();
		$.ajax({
			type:'post',
			url:"/admin/actions/deleteNotice",
			data:{ "notice_id":notice_id },
			success:function(data){
				if(data == 'error') alert("삭제 실패");
				location.href="/admin/noticelist";
			}
		});
	});
		
	$('.submit1').on('click',function(){
		var notice_id = $('#notice_id').val();
		var reg_date = $('#reg_date').val();
		var label = $('#label').val();
		var content = $('#content').val();
		var author_id = $('#author_id').val();
		var flen = $("form[name=noticeForm] .chk1").length;
		for(var i=0; i<flen; i++){
			if( $('.chk1').eq(i).val()=="" ||
	       		$('.chk1').eq(i).val()==null ||
	       		$('.chk1').eq(i).val().trim()=="")
       		{
		  		alert($('.chk1').eq(i).attr('title')+'를 입력하시오');
		  		$('.chk1').eq(i).focus();
		  	return false;
	  		}
		}
		$.ajax({
			type:'post',
			url:"/admin/actions/NoticeGenerate",
			data:{"notice_id":notice_id,
				  "reg_date":reg_date, 
				  "label":label, 
				  "content":content,
				  "author_id":author_id},
			success:function(data){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
				if(data == 'error') alert("등록 실패");
				location.href="/admin/noticelist";
			}
			
		});
		 alert(author_id)
	});
	$('.noticeUp').on('click',function(){
		alert(1)
		var notice_id = $('#notice_id').val();
		var label = $('#label').val();
		var content = $('#content').val();
		var author_id = $('#author_id').val();
		var flen = $("form[name=noticeForm] .chk1").length;

		for(var i=0; i<flen; i++){
			if( $('.chk1').eq(i).val()=="" ||
	       		$('.chk1').eq(i).val()==null ||
	       		$('.chk1').eq(i).val().trim()=="")
       		{
		  		alert($('.chk1').eq(i).attr('title')+'를 입력하시오');
		  		$('.chk1').eq(i).focus();
		  	return false;
	  		}
		}
		$.ajax({
			type:'post',
			url:"/admin/actions/updateNotice",
			data:{"notice_id":notice_id, 
				  "label":label, 
				  "content":content,
				  "author_id":author_id},
			success:function(data){
				if(data == 'error') alert("수정 실패");
				location.href="/admin/noticelist";
			}
		});
		
	});
});
