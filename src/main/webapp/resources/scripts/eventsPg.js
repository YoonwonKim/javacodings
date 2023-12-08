$().ready(() =>{
	let rows = document.querySelectorAll('.row');
	for (let i = 0; i < rows.length;)
	{
		rows[i].addEventListener('click', function()
		{

		});
	}
});



$(document).ready(function() {
	$("select[name='category']").change(function(){
	   
	   //selectedValue = $(this).val();
	   selectedCategory = $("select[name=category] option:selected").val();
	   
	   //값 받아오는지 확인
	   //console.log($(this).val());	
	   console.log($("select[name=category] option:selected").val());
	    
	});
	$("select[name='content']").change(function(){
	   
	   //selectedValue = $(this).val();
	   selectedContent = $("select[name=content] option:selected").val();
	   
	   //값 받아오는지 확인
	   //console.log($(this).val());	
	   console.log($("select[name=content] option:selected").val());
	    
	});
	
	$(".stateUpdate").click(function(){			
		 
	var selectedCategory = $("select[name='category'] option:selected").text(); //선택한 option의 텍스트 값 가져오기
	var selectedContent = $("select[name='content'] option:selected").text(); //선택한 option의 텍스트 값 가져오기
    var event_id = $("input[name='event_id']").val();//인풋박스의 event_id값을 가져온다
	
	console.log("selectedCategory: " + selectedCategory);
	console.log("selectedContent: " + selectedContent);
	console.log("event_id : " + event_id);	
	
	$.ajax({
    url:"/admin/actions/eventMgt",
    type:'POST',
    dataType :'JSON',
    data:{
        category: selectedCategory,
		content : selectedContent,
        event_id : event_id
    },
     success: function(response) {
            // 전송 성공 시 실행할 코드
            console.log("값이 성공적으로 전송되었습니다.");
        },
        error: function(error) {
            // 전송 중 에러 발생 시 실행할 코드
            console.log("값 전송 중 에러가 발생했습니다.");
        }
    });
	
	});


	 
}); 









 
      
      
      
    



/*function selectBoxChange(id,th){
	
	if(confirm("변경 하시겠습니까?")){
		$.ajax({
			url: "/admin/actions/eventMgt",
			type : "POST"
			data : {"category" : content, "btnValue":th} ,
			success : function(data, textStatus, xhr){
				alert("변경 성공!");
				document.location.reload(true);
				console.log(data);
			},
			error: function(xhr, status, error){
				console.log(error);
				alert("실패");
			}
		})
	}
}*/

/*$(document).ready(function() {
    $("select[name='category']").change(function() {
        var selectedValue = $(this).val();
        var selectedText = $("select[name=category] option:selected").text();
        
        $.ajax({
            url: "/admin/actions/eventMgt",
            type: 'POST',
            data: {
                selectedValue: selectedValue,
                selectedText: selectedText
            },
            success: function(response) {
                console.log("값이 성공적으로 전송되었습니다.");
            },
            error: function(error) {
                console.log("값 전송 중 에러가 발생했습니다.");
            }
        });
    });
    
    $(".stateUpdate").click(function() {
        var categoryValue = $("input[name='category']").val();
        var contentValue = $("input[name='content']").val();
        
        console.log("category: " + categoryValue);
        console.log("content: " + contentValue);
    });
});*/






	 
	 //var category = tr.find(td).find("select[name=category]").val();
	 //console.log(category)



























/*$(document).ready(function(){
	
	
	
	// eventAdd 버튼 요소를 선택합니다.
const eventAddButton = document.querySelector('.eventAdd');

// eventAdd 버튼에 클릭 이벤트 리스너를 추가합니다.
eventAddButton.addEventListener('click', function() {
	
	
	const itemCheckboxes = document.querySelectorAll('.itemCheckbox:checked');
	console.log(itemCheckboxes)
  // 체크된 아이템의 ID 값을 저장할 배열을 생성합니다.
  const selectedItems = [];

// 선택된 상품의 정보를 배열에 추가합니다.
  itemCheckboxes.forEach(function(checkbox) {
    const row = checkbox.closest('cds-table-row');
    const cells = row.querySelectorAll('cds-table-cell');
	
    console.log(cells);
	
	selectedItems.push(item);
	});
  // 선택된 아이템의 ID 값을 이벤트 테이블에 추가하는 AJAX 요청 등의 로직을 작성합니다.
  // 예를 들어, jQuery를 사용한 AJAX 요청을 하는 경우 아래와 같이 작성할 수 있습니다.
  $.ajax({
    url: "/admin/actions/eventAdd",
    method: 'POST',
    contentType: 'application/json',
    data: JSON.stringify(selectedItems),
    success: function() {
      // 요청이 성공적으로 처리된 경우의 동작을 작성합니다.
      console.log('아이템이 성공적으로 추가되었습니다.');
    },
    error: function() {
      // 요청이 실패한 경우의 동작을 작성합니다.
      console.error('아이템 추가에 실패했습니다.');
    }
  });
});
});*/