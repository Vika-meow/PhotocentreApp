$(document).on("click", "#deleteButton", function() {
    var id = $(this).attr('value');
    let isBoss = confirm("С этим чеком связаны элементы, вы уверены, что хотите удалить его?");
    if(isBoss) {
        $.post('/insert/check/delete', {
            checkId: id
        }, function (html) {
            jQuery('#tableId').html(html);
        });
    }
});