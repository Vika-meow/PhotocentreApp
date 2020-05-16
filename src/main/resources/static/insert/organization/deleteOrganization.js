$(document).on("click", "#deleteAddress", function() {
    var id = $(this).attr('value');
    let isBoss = confirm("С этой организацией могут быть связаны поставки, чеки, работники, а так же другие организации. Вы уверены что хотите удалить организацию?");
    if(isBoss) {
        $.post('/insert/organization/delete', {
            address : id
        }, function (html) {
            jQuery('#printAdresses').html(html);
        });
    }
});