function deleteMemebr(event) {
	var member_id = $(event.target).parents('.row').children('cds-table-cell:first').text();
	console.log(member_id);
	
	$.ajax({
		url: '/admin/actions/deleteMember',
		method: 'POST',
		data: { 'member_id': member_id },
		success: function(response) {
		}
	});
	
}