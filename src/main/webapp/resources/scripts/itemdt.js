/**
 * 
 */
$().ready(function(){
	var plen = $('.price').length;
 	for(var i=0; i<plen; i++){
	 var p=$('.price').eq(i).text();
	 var to_p = 
	     p.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,",");
	 $('.price').eq(i).text(to_p);
   }
   
    
  $('.cartAdd').on('click', function(){
	 var q = $('input[name=quantity]').val();
	 var s = $('input[name=stock]').val();
	 //숫자가 아닐때
	 if(!$.isNumeric(q)) {
		 alert('숫자를 입력하시오')
		 $('input[name=quantity]').focus()
		 return false;
	 } 
	 //구매수량이 재고보다 많으면
	 if(parseInt(q)>parseInt(s)){
		 alert('재고가 부족합니다.')
		 return false;
	 }
	 $('form[name=itemForm]').submit();
  });
  
  $('itemDt').on('click', function(){
	  
  })
});

