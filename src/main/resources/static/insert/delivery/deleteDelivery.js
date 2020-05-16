$(document).on("click", "#deleteDelivery", function() {
    var id = $(this).attr('value');
    let isBoss = confirm("С этой поставкой могут быть связаны некоторые элементы, вы уверены, что хотите удалить поставку?");
    if(isBoss) {
        $.post('/insert/delivery/delete', {
            deliveryId : id
        }, function (html) {
            jQuery('#deliveryTable').html(html);
        });
    }
});