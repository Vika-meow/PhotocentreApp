$(document).on("click", "#deleteCustomer", function() {
    var id = $(this).attr('value');
    let isBoss = confirm("С этим клиентом связаны некоторые покупки, вы уверены, что хотите удалить его?");
    if(isBoss) {
        $.post('/insert/customer/delete', {
            name : id
        }, function (html) {
            jQuery('#tableId').html(html);
        });
    }
});