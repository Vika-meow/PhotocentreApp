function makeRequest(url) {
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

    httpRequest.onreadystatechange = function() { alertContents(httpRequest, url); };
    httpRequest.open('GET', url, true);
    httpRequest.send(null);

}

function alertContents(httpRequest) {
    if (httpRequest.readyState == 4) {
        if (httpRequest.status == 200) {
            $('#printAdresses').html(httpRequest.responseText);
        } else {
            alert('С запросом возникла проблема. ' + httpRequest.status);
        }
    }
}