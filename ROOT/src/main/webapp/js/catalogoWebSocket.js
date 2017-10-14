window.onload = init;
var url = ((window.location.protocol == "https:") ? "wss:" : "ws:")
    + "//" + window.location.host
    + "/YamiletStoreWeb-1.0-SNAPSHOT/catalogo";
console.log('url = '+url );
var socket = new WebSocket(url);
socket.onclose = function(){
    var myDate = new Date();
    document.getElementById("idConsoleClose").innerHTML += "<br />WebSocket status: " + GetSocketStateName(socket.readyState)+" "+myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds() +":" + myDate.getMilliseconds();
    //@TODO
    // En caso de perder la session de 10 minutos. se configura en web.xml
    // se tiene que ir al login o recargar la pagina principal.
    // location.reload();
}
socket.onopen = function(){
    var myDate = new Date();
    document.getElementById("idConsoleOpen").innerHTML += "<br />WebSocket status: " + GetSocketStateName(socket.readyState)+" "+myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds() +":" + myDate.getMilliseconds();
}
socket.onmessage = function(message) {
    var producto = JSON.parse(message.data);

    if(producto.action === 'add'){
        console.log('onMessage = {'+producto.action+'->'+producto.idProducto+'}');
        var elementProducto = $('<div>',{id:'idDiv'+producto.idProducto , class:'divDetalle'}).append( $('<button onclick=\"reservarProducto('+producto.idProducto+')\" id=\"idBtn'+producto.idProducto+'\">').text('RESERVAR '+producto.idProducto));
        $("#idPanel").append(elementProducto);
        $('#idTotalProducto').text($('.divDetalle').length);
    }else if(producto.action === 'reservado')   {
        console.log('onMessage = {'+producto.action+'->'+producto.idProducto+'}');
        $('#idBtn'+producto.idProducto).prop('disabled', true);
        $('#idBtn'+producto.idProducto).html('RESERVADO '+producto.idProducto);
        console.log('Deshabilitar idProducto = '+producto.idProducto);
    }else if(producto.action === 'contador') {
        console.log('onMessage = {'+producto.action+'->'+producto.totalPrendas+'}');
        $('#idTotalProducto').text(producto.totalPrendas+' prendas reservadas');
    }
}

function GetSocketStateName(state){
    var strSocketState;
    if(socket.readyState == 0){
        strSocketState = "Connecting";
    }else if(socket.readyState == 1){
        strSocketState = "Open";
    }else if(socket.readyState == 2){
        strSocketState = "Closing";
    }else if(socket.readyState == 3){
        strSocketState = "Closed";
    }

    return strSocketState;
}
function reservarProducto(idProducto) {
    var ProductoAction = {
        action: "RESERVAR_PRODUCTO",
        idProducto: idProducto
    };
    console.log('action '+ProductoAction.action+'reservar producto '+ProductoAction.idProducto);
    socket.send(JSON.stringify(ProductoAction));

}

function init(){
    console.log('Asincronizando con hosting...!!');
}