$(document).on("click", "#orderTypeButton", function() {
    $.get('/select/orders/addOrderType', function(html){
        jQuery('#addOrderType').html(html);
    });
});

$(document).on("click", "#orderTypeRemove", function() {
    $.get('/select/orders/removeOrderType', function(html){
        jQuery('#addOrderType').html(html);
    });
});