/*function addOrganizations(url) {
    //let url = "/select/orders/addDates";
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

    httpRequest.onreadystatechange = function() { smth(httpRequest); };
    httpRequest.open('GET', url, true);
    httpRequest.send(null);

}

function smth(httpRequest) {
    if (httpRequest.readyState == 4) {
        if (httpRequest.status == 200) {
            $('#hehe').html(httpRequest.responseText);
        } else {
            alert('С запросом возникла проблема. ' + httpRequest.url + " " + httpRequest.status);
        }
    }
}*/

$(document).on("click", "#organizationsButton", function() {
    $.get('/select/orders/addOrganizations', function(html){
        jQuery('#addOrganizations').html(html);
    });
});

$(document).on("click", "#removeOrganizationsButton", function() {
    $.get('/select/orders/removeOrganizations', function(html){
        jQuery('#addOrganizations').html(html);
    });
});

$(document).on("change", "#organizationFilter", function () {
    var selected = $(this).val();
    $.get('/select/orders/putOrganizations',
        {
            typeOfOrganizations : selected
        }, function(html){
        jQuery('#putOrganizationsSelect').html(html);
    });
})

/*$(document).ready(function() {
    $('#putOrganizationsSelect').multiselect();
});*/
/*$('#putOrganizationsSelect').multiSelect()*/