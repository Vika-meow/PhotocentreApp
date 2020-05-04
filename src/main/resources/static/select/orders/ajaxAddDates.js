/*function addDates(url) {
    var httpRequest = false;

    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        httpRequest = new XMLHttpRequest();
        if (httpRequest.overrideMimeType) {
            httpRequest.overrideMimeType('text/xml');
            // Читайте ниже об этой строке
        }
    } else if (window.ActiveXObject) { // IE
        try {
            httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
        }
    }

    if (!httpRequest) {
        alert('Не вышло :( Невозможно создать экземпляр класса XMLHTTP ');
        return false;
    }

    httpRequest.onreadystatechange = function() { addition(httpRequest); };
    httpRequest.open('GET', url, true);
    httpRequest.send(null);

}

function addition(httpRequest) {
    if (httpRequest.readyState == 4) {
        if (httpRequest.status == 200) {
            $('#addDates').html(httpRequest.responseText);
        } else {
            alert('С запросом возникла проблема. ' + httpRequest.url + " " + httpRequest.status);
        }
    }
}*/
/*
$.ajax( {
    url: '/select/orders/addDates',
    type: "get",
    success: function(){
        $('#addDates').html(httpRequest.responseText);
    }
});*/

$(document).on("click", "#datesButton", function() {
    $.get('/select/orders/addDates', function(html){
        jQuery('#addDates').html(html);
    });
});

$(document).on("click", "#removeDatesButton", function() {
    $.get('/select/orders/removeDates', function(html){
        jQuery('#addDates').html(html);
    });
});
