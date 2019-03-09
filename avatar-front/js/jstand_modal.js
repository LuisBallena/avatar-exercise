/** MODAL  **/
var modal = (function() {
    var
            method = {},
            $overlay,
            $modal,
            $content,
            $close;
    // Center the modal in the viewport
    method.center = function() {
        var top, left;
        top = Math.max($(window).height() - $modal.outerHeight(), 0) / 2;
        left = Math.max($(window).width() - $modal.outerWidth(), 0) / 2;
        $modal.css({
            top: top + $(window).scrollTop(),
            left: left + $(window).scrollLeft()
        });
    };

    // Open the modal
    method.open = function(settings) {
        $modal.css({
            width: settings.width || 'auto',
            height: settings.height || 'auto'
        });

        method.center();
        $(window).bind('resize.modal', method.center);
        $modal.show();
        $overlay.show();
    };

    // Close the modal
    method.close = function() {
        $modal.hide();
        $overlay.hide();
        $(window).unbind('resize.modal');
    };

    // Generate the HTML and add it to the document
    $overlay = $('<div id="overlay"></div>');
    $modal = $('<div id="modal"></div>');
    $content = $('<div id="content" style="float:left;"><div id="image_overlay"/> <div style="float:left;line-height: 2;" >Espere ...</div></div>');
    $modal.hide();
    $overlay.hide();
    $modal.append($content, $close);
    $(document).ready(function() {
        $('body').append($overlay, $modal);
    });
    return method;
}());
// Wait until the DOM has loaded before querying the document
function ajaxStatus(data) {
    var status = data.status;
    switch (status) {
        case "begin":
            modal.open({content: ""});
            break;
        case "complete":
            modal.close();
            break;
    }
}

// AjaxStatus para que al finalizar pueda abrir un dialog Primefaces
function ajaxStatusPrimeDialog(data,widgetVar) {
    var status = data.status;
    switch (status) {
        case "begin":
            modal.open({content: ""});
            break;
        case "complete":
            modal.close();
            break;
        case "success":
            PF(widgetVar).show();
            break;
    }
}

function openAjaxStatus() {
    modal.open({content: ""});
}

function closeAjaxStatus() {
    modal.close();
}