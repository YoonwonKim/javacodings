$(document).ready(function(){
	$("#updateCartButton").click(function(){
		$.ajax({
			url: '/actions/updateCart',
			type: 'post',
			success: function(response){
			    console.log(response);
			}
		});
	});
	
	$("#deleteCartButton").click(function(){
		var checkedId = $("#checkItem").val();
		
		$.ajax({
			url: '/actions/deleteCart',
			type: 'post',
			data: {id:checkedId}, 
			success: function(response){
			    console.log(response);
			}
		});
	});
});
