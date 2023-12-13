$(document).ready(function() {
    $('#logout').click(function() {
        $.ajax({
            type:'POST',
            url:"/actions/account/logout",
            success: function(data) {
                location.reload();
            }
        });
    });
});