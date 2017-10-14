<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>recyclothes.cl</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon" sizes="57x57" href="images/ico/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="images/ico/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="images/ico/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="images/ico/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="images/ico/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="images/ico/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="images/ico/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="images/ico/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="images/ico/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192"  href="images/ico/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="images/ico/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="images/ico/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="images/ico/favicon-16x16.png">
    <link rel="manifest" href="images/ico/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="images/ico/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
</head><!--/head-->

<body>
<header id="header"><!--header-->
    <%
        String action = request.getParameter("rv");
        if("contacto".equals(action)){
    %>
            <input id="idaction" type="hidden" value="contacto">
    <%
        }
    %>
    <div class="header_top"><!--header_top-->
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="contactinfo">
                        <ul class="nav nav-pills">
                            <li><a href="#"><i class="fa fa-phone"></i>+56 9 73415469</a></li>
                            <li><a href="#"><i class="fa fa-envelope"></i> c.vanessavillanueva@gmail.com</a></li>
                            <li><a id="idliContact" href="#"  ><i class="fa fa-crosshairs"></i>Contacto</a></li>
                            <li><a id="idliComoComprar" href="#"><i class="fa fa-crosshairs"></i>Como Comprar</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="social-icons pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="https://www.facebook.com/caprichitos.modainfantil.7?fref=ts" target="_blank"><i class="fa fa-facebook"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header_top-->
    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a id="idIrCatalogo" href="#">
                            <img src="img/caprichitos2.jpg" alt="" />
                        </a>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div id="id-nombre-usuarioDTO"></div>
                </div>
                <div class="col-sm-4">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <div id="id-menu-principal">
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-middle-->
</header>


<div class="container" id="panel-central">
</div>
<footer id="footer"><!--Footer-->
    <div class="footer-top">
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="single-widget">
                        <h2>Puntos de Entrega</h2>
                        <ul class="nav nav-pills nav-stacked">
                            <li>Metro Plaza de Armas</li>
                            <li>Metro Cal y Canto</li>
                            <li>Metro Santa Ana</li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="single-widget">
                        <h2>Envios a todas las Regiones via:</h2>
                        <ul class="nav nav-pills nav-stacked">
                            <li>Tur Bus</li>
                            <li>Cruz del Sur</li>
                            <li>ChileExpress</li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="single-widget">
                        <h2>Servicios</h2>
                        <ul class="nav nav-pills nav-stacked">
                            <li><a id="idRefComoComprar" href="#">Como Comprar</a></li>
                            <li><a id="idRefContacto" href="#">Contacto</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="footer-bottom">
        <div class="container">
            <div class="row">
                <p class="pull-left">Copyright &copy; 2016 Recyclothes. Todos los derechos Reservados.</p>
                <p class="pull-right">Desarrollado por <span>Cesar Castillo &#8212; Software Developer &#8212; <i class="fa fa-envelope"></i> genesiscastillo&#64;hotmail.com</span></p>
            </div>
        </div>
    </div>

</footer><!--/Footer-->
<input type="hidden" id="idInputTalla" value="14"/>
<input type="hidden" id="idInputCatalogo" value="0"/>

<script src="js/jquery-1.12.0.min.js"></script>
<script src="js/price-range.js"></script>
<script src="js/jquery.scrollUp.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/main.js"></script>
<script src="js/bootbox.min.js"></script>
<script src="js/bootstrap-combobox.js"></script>
<script type="application/javascript">
    function getComponentI( idli , fafa, nombreOpcion ){
        var idliopcion = $('<li>',{
            id : idli
        });
        var ahref = $('<a>',{
            href : '#',
            text : nombreOpcion,
        });

        var iComp = $('<i>', {
            class : fafa
        });
        ahref.prepend( iComp );
        idliopcion.append(ahref);
        return idliopcion;
    }
    var opcionIrCatalogo    = getComponentI("idliCatalogo" , "fa fa-search" , "Ir al Catalogo" );
    var opcionIrCatalogo2    = getComponentI("idliCatalogo2" , "fa fa-search" , "Ir al Catalogo" );
    var opcionMisPedidos    = getComponentI("idliCart", "fa fa-shopping-cart","Mis Pedidos");
    var opcionCerrarSession = getComponentI("idliLogout","fa fa-power-off","Cerrar Sessión");
    var opcionIniciarSession= getComponentI("idliLogin","fa fa-lock","Iniciar Sessión");

    opcionMisPedidos.bind('click',function()    {
        $('#id-menu-principal').append(opcionIrCatalogo);
        this.remove();
        $('#panel-central').load('apps/shop/verDetallePedido.jsp');
    })
    opcionIrCatalogo.bind('click', function(){
        $('#id-menu-principal').append(opcionMisPedidos);
        this.remove();
        $('#panel-central').load('apps/shop/catalogo.jsp');
    });
    opcionIrCatalogo2.bind('click', function(){
        $('#id-menu-principal').append(opcionIniciarSession);
        this.remove();
        $('#panel-central').load('apps/shop/catalogo.jsp');
    });
    opcionIniciarSession.bind('click', function()   {
        $('#id-menu-principal').append(opcionIrCatalogo2);
        this.remove();
        $('#panel-central').load('apps/shop/login.jsp');
    });
    opcionCerrarSession.bind('click',function() {
        location.reload();
    });

    var statusLoggin = false;
    logger('document.location.host     = '+document.location.host);
    logger('document.location.hostname = '+document.location.hostname);
    var wsUri = 'ws://' + document.location.host + '/RecyclothesEAR-web/clienteWebSocket';

    if(  document.location.hostname === 'www.recyclothes.cl' || document.location.hostname === 'web-babycaprichitos.rhcloud.com')   {
        if( window.location.protocol === 'http:'){
            wsUri = 'ws://web-babycaprichitos.rhcloud.com:8000/RecyclothesEAR-web/clienteWebSocket';
        }else{
            wsUri = 'wss://web-babycaprichitos.rhcloud.com:8443/RecyclothesEAR-web/clienteWebSocket';
        }
    }
    logger('connect to websocket Uri ='+wsUri);
    var socket = new WebSocket(	wsUri	);
    socket.onopen = function () {
        logger("Openened connection to websocket");
        logger("Welcome to Recyclothes");
        if( $('#idaction').length ) {
            $('#panel-central').load('apps/shop/contacto.jsp');
        }else{
            $('#panel-central').load('apps/shop/catalogo.jsp');
        }
        serviceIsLogginOn();
    };
    socket.onclose = function() {
        logger("Closed connection to websocket ");
        bootbox.dialog({
            message: "Su session se ha expirado reingrese nuevamente",
            title: "Advertencia!",
            onEscape: function() {},
            closeButton: false,
            buttons: {
                success: {
                    label: "OK",
                    className: "btn btn-default add-to-cart",
                    callback: function() {
                        window.location.replace(document.location.host);
                    }
                }
            }
        });

    };
    socket.onerror = function(evt){
        logger("The following error occurred: " + evt.data);
        //window.location.replace(webUriError);
    };
    function checkKeyCode(evt) {
        var evt = (evt) ? evt : ((event) ? event : null);
        var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
        if(event.keyCode==116)        {
            evt.keyCode=0;
            return false
        }
    }
    document.onkeydown=checkKeyCode;
    var catalogoArray = ['Catalogo Niño','Catalogo Niña'];
    var tallaArray = ["0-3 meses","6 meses","9 meses","12 meses","18 meses","24 meses","Talla 2","Talla 3","Talla 4","Talla 5","Talla 6","Talla 7","Talla 8","Talla 10","Varios"];
    function val(iddeclarado){
        return $('#'+iddeclarado).val();
    }
    function limpiarCampos() {
        for (var i = 0; i < arguments.length; i++) {
            $('#'+arguments[i]).val('');
        }
    }
    $(document).ready(function() {
        logger("INCICIO PRINCIPAL.js...");

        //$('#id-menu-principal').append(opcionIniciarSession);

        $('#idRefComoComprar').bind('click',function(){
            $('#panel-central').load('apps/shop/comoComprar.jsp?rv=<%= System.currentTimeMillis()%>');
        });
        $('#idliComoComprar').bind('click',function(){
            $('#panel-central').load('apps/shop/comoComprar.jsp?rv=<%= System.currentTimeMillis()%>');
        });
        $('#idliContact').bind('click',function(){
            $('#panel-central').load('apps/shop/contacto.jsp?rv=<%= System.currentTimeMillis()%>');
        });
        $('#idRefContacto').bind('click',function(){
            $('#panel-central').load('apps/shop/contacto.jsp?rv=<%= System.currentTimeMillis()%>');
        });

        logger("FIN PRINCIPAL.js...");
    });
    function alertRecuperarClave(){
        bootbox.prompt({
            title: 'Ingrese su email registrado',
            type : 'email',
            callback: function(result) {
                if (result === null) {
                    alert('vacio.!!');
                } else {
                    serviceRecuperarClaveUsuario(result);
                }
            }
        });
    }

    function logger( stringLog )	{
        console.log( stringLog );
    }
    socket.onmessage = function(event) {
        var service = JSON.parse(event.data);
        logger('-->receiver service='+JSON.stringify(service));
        if(service.action === 'ADMIN_LOGIN_ON_FOR_USUARIO' ) {
            logger('-->***********  service.status='+service.status);
            if(service.status) {
                $('#id-menu-principal').append(opcionIniciarSession);
                opcionIniciarSession.bind('click', function()   {
                    $('#id-menu-principal').append(opcionIrCatalogo2);
                    this.remove();
                    $('#panel-central').load('apps/shop/login.jsp');
                });
            }else   {
                if(document.getElementById('idliLogin'))    {
                    opcionIniciarSession.remove();
                }
            }
        }
        else  if(service.action === 'GENERAR_RESERVA_PEDIDOS' ) {
            $('#id-nombre-usuarioDTO').html('');
            $('html, body').animate({ scrollTop: 0 }, 'slow');
            var reservas = JSON.parse(	service.reservas );
            var totalReservas = reservas.length;
            var saludo = $('<h4>',{
                text : 'HOLA '+service.cliente.nombres
            });
            $('#id-nombre-usuarioDTO').append(saludo);
            if( totalReservas > 0 ) {
                var listResr = $('<p>', {
                    text: 'Tienes ' + totalReservas + ' Reserva(s) por entregar'
                });
                $('#id-nombre-usuarioDTO').append( listResr );
                jQuery.each( reservas , function (i, reserva) {
                    var acodigoRe = $('<a>',{
                        href : '#',
                        text : 'Codigo : '+reserva.codigoReserva+' - Vence '+reserva.fechaEntrega+' - Total $ '+reserva.montoPagar,
                        on : {
                            click : function(){
                                $('#panel-central').load('apps/shop/verReservaDetalle.jsp?rv=RESERVA&codigoReserva='+reserva.codigoReserva);
                                //serviceVerReservaPedidos(reserva.codigoReserva);
                            }
                        }
                    });
                    var brs = $('<br>')
                    $('#id-nombre-usuarioDTO').append( acodigoRe).append( brs);
                });
            }
            $('#idliCatalogo').click();
            var email = service.cliente.correo;
            bootbox.dialog({
                message: "Revise su email en <br/><strong>"+email+"</strong></br>y contactate...",
                title: "Gracias por tu Reserva con www.recyclothes.cl ...!",
                onEscape: function() {},
                closeButton: false,
                buttons: {
                    success: {
                        label: "OK",
                        className: "btn btn-default add-to-cart",
                        callback: function() {
                            bootbox.hideAll();
                        }
                    }
                }
            });

        }else if(service.action === 'REGISTRAR_CLIENTE' ) {
            var email = service.cliente.correo;
            if( service.isNuevo){
                limpiarCampos('txtregnombre','txtregemail','txtregpassword','txtregrepassword','txtregtelefono');
                bootbox.alert('<h2>Hola ' +service.cliente.nombres+'<br/></h2>'+
                        '<h3>Revise su email para activar su cuenta<br/>' +
                        '<strong>'+email+'</strong></h3>');
                $('#idliCatalogo').click();
            }else{
                bootbox.alert('<h2>Advertencia!<br/></h2>'+
                        '<h3>Ya se encuentra registrado solo debes<br/>' +
                        'revisar su email para activar su cuenta<br/>' +
                        '<strong>'+email+'</strong></h3>');
            }
        }
        else if(service.action === 'VER_DETALLE_PEDIDOS' ) {
            var productos = JSON.parse(service.productos);
            jQuery.each(productos, function (i, productoDTO) {
                logger('-->VER_DETALLE_PEDIDOS productoDTO=' + JSON.stringify(productoDTO));
                agregarProductoPedido(productoDTO , 'PEDIDOS');
            });
            var montoTotal = service.montoTotal;
            if(montoTotal === 0)	{
                var trProdReservado = '<div class="panel panel-default"><div class="panel-body"><br/><br/><br/><br/>Actualmente, tu bolsa de productos est&aacute vac&iacutea.<br/><br/><br/><br/><br/><br/><br/></div></div>';
                $('#panel-productos-reservados').append(trProdReservado);
                $('#panel-reserva-montototal').hide();
            }else{
                $('#idComentariosPedidos').val('');
                $('#id-monto-total-pedidos').html(montoTotal);
                $('#panel-reserva-montototal').show();
            }
/*
            $('#idliCart').hide();
            $('#idliCatalogo').show();
*/
        }
        else if(service.action === 'VER_RESERVA_PEDIDOS' ) {
            var reserva = service.reserva;
            $('#id-estado-reserva').append(reserva.estadoReservaEnum);
            $('#id-comentario-reserva').val(reserva.comentarios);
            $('#id-monto-total-reserva').append('$ '+reserva.montoTotal);
            var productos = JSON.parse(service.productos);
            jQuery.each( productos , function( i , productoDTO) {
                agregarProductoPedido(productoDTO , 'RESERVADOS');
            });
        }else if(service.action === 'PRODUCTO_HABILITADO' ) {
            var productoDTO = service.productoDTO;
            logger('PRODUCTO_HABILITADO = ' + JSON.stringify(productoDTO));
            var isLoggeado = service.isLoggeado;
            habilitarProducto(productoDTO, isLoggeado);
        }else if(service.action === 'PRODUCTO_RESERVADO' ) {
            var productoDTO = service.productoDTO;
            logger('PRODUCTO_RESERVADO = ' + JSON.stringify(productoDTO));
            cssProductoReservado(productoDTO);
            quitarBotonAddDetalleReservado(productoDTO);
        }else if(service.action === 'RESERVAR_PRODUCTO' ) {
            var productoDTO = service.productoDTO;
            logger('RESERVAR_PRODUCTO = ' + JSON.stringify(productoDTO));
            //quitarBotonAdd(productoDTO);
            cssReservarProducto(productoDTO)
        }
        else if(service.action === 'RESERVAR_PRODUCTO_DETALLE' ) {
            var productoDTO = service.productoDTO;
            logger('RESERVAR_PRODUCTO_DETALLE = ' + JSON.stringify(productoDTO));
            quitarBotonAddDetalle(productoDTO);
            cssReservarProducto(productoDTO);
        }
        else if(service.action === 'VER_DETALLE_PRODUCTO' ) {
            var detalleProductoDTO = service.detalleProductoDTO;
            logger('VER_DETALLE_PRODUCTO = ' + JSON.stringify(detalleProductoDTO));
            mostrarDetalleProducto(detalleProductoDTO);
        }
        else if(service.action === 'LOGEAR_USUARIO' )	{
            if (! service.isLoggeado ) {
                alertUsuarioInvalido(service.mensaje);
            }else{
                statusLoggin = service.isLoggeado;
                $('#id-nombre-usuarioDTO').html('');
                $('html, body').animate({ scrollTop: 0 }, 'slow');
                var reservas = JSON.parse(	service.reservas );
                var totalReservas = reservas.length;
                logger('avisoUsuarioEntrada length = '+totalReservas);
                var saludo = $('<h4>',{
                    text : 'HOLA '+service.cliente.nombres
                });
                $('#id-nombre-usuarioDTO').append(saludo);
                if( totalReservas > 0 ) {
                    var listResr = $('<p>', {
                        text: 'Tienes ' + totalReservas + ' Reserva(s) por entregar'
                    });
                    $('#id-nombre-usuarioDTO').append( listResr );
                    jQuery.each( reservas , function (i, reserva) {
                        var acodigoRe = $('<a>',{
                            href : '#',
                            text : 'Codigo : '+reserva.codigoReserva+' - Vence '+reserva.fechaEntrega+' - Total $ '+reserva.montoPagar,
                            on : {
                                click : function(){
                                    $('#panel-central').load('apps/shop/verReservaDetalle.jsp?rv=RESERVA&codigoReserva='+reserva.codigoReserva);
                                    //serviceVerReservaPedidos(reserva.codigoReserva);
                                }
                            }
                        });
                        var brs = $('<br>')
                        $('#id-nombre-usuarioDTO').append( acodigoRe).append( brs);
                    });
                }
                $('#id-menu-principal').append(opcionCerrarSession);
                $('#id-menu-principal').append(opcionMisPedidos);
                opcionIniciarSession.remove();
                opcionIrCatalogo2.remove();
/*
                $('#idliCart').show();
                $('#idliLogin').hide();
                $('#idliLogout').show();
                $('#idliCatalogo').hide();
*/
                $('#panel-login-usuarioDTO').hide();
                $('#panel-catalogo-productos').show();
                $('#idInputTalla').val('0');
                $('#panel-central').load('apps/shop/catalogo.jsp');
            }
        }
        else if(service.action == 'AGREGAR_PRODUCTOS'  )	{
            var isLoggeado = service.isLoggeado;
            var productos = JSON.parse(service.productos);
            logger('-->service.numeroPaginas ='+service.numeroPaginas);
            if(service.numeroPaginas === 0)	{
                agregarMensajeNoHayProductos();
            }else{
                jQuery.each( productos , function( i , productoDTO) {
                    agregarProductoDisponible( productoDTO , isLoggeado );
                });
                if(service.numeroPaginas > 1){
                    agregarPaginacion(service.numeroPaginas);
                }
            }
        }else if(service.action === 'HABILITAR_PRODUCTO' ) {
            $('#id_producto_reservado_'+service.idProducto).remove();
            var montoTotal = service.montoTotal;
            if(montoTotal > 0){
                $('#id-monto-total-pedidos').html(montoTotal);
            }else{
                var trProdReservado = '<table><tr id="trProdReservado_0"><td class="cart_description"><h4>Actualmente, tu bolsa de productos est&aacute vac&iacutea.</h4><p>Agregar productos ir al catalogo</p></td></tr></table>';
                $('#panel-productos-reservados').append(trProdReservado);
                $('#id-monto-total-pedidos').html(montoTotal);
                $('#panel-reserva-montototal').hide();
            }
        }else if(service.action === 'RECUPERAR_CLAVE_USUARIO' ) {
            if(service.existe ){
                var h2RecU = $('<h2>',{
                    text : 'Hola '+service.cliente.nombres+' '+'; revise su correo, para que recuperes su contraseña.'
                });
                bootbox.alert(h2RecU.html());
            }else{
                var h2RecU = $('<h2>',{
                    text : 'No existe email registrado!'
                });
                bootbox.alert(h2RecU.html());
            }
        }
        else if(service.action === 'GENERAR_TOKEN_CONTACTO' ) {
            var token = service.token;
            $('#id-contact-tokenGenerado').val(token);
        }
        else {
            logger('NO EXISTE ACTION '+service.action);
        }
        logger('FIN DE RECEPCION');
    };
    function serviceGenerarTokenContacto(){
        var service = {
            action: 'GENERAR_TOKEN_CONTACTO'
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function agregarMensajeNoHayProductos(){
        var atable = $('<table>');
        var atr = $('<tr>');
        var atd = $('<td>',{
            style : 'text-align: center',
            html : '<h3>No hay productos para esta categoria</h3>'
        });
        atr.append(atd);
        atable.append(atr);
        $('.features_items').append(atable);
    }
    function serviceRecuperarClaveUsuario(result){
        var service = {
            action: 'RECUPERAR_CLAVE_USUARIO',
            email : result
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function serviceSendContacto(nombre, correo , asunto , mensaje , token){
        var service = {
            action: 'CONTACTO_MENSAJE_USUARIO',
            name : nombre,
            email : correo,
            subject : asunto,
            message : mensaje,
            token : token
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function serviceVerDetallePedidos()	{
        var service = {
            action: 'VER_DETALLE_PEDIDOS'
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function serviceVerReservaGenerada(codigoReserva)	{
        var service = {
            action: 'VER_RESERVA_PEDIDOS',
            codigoReserva : codigoReserva
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function serviceIsLogginOn()	{
        var service = {
            action: 'ADMIN_LOGIN_ON_FOR_USUARIO'
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function serviceConfirmarReserva()	{
        var service = {
            action: 'GENERAR_RESERVA_PEDIDOS',
            comentarios: $('#idComentariosPedidos').val()
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function habilitarProducto (productoDTO , isLogeado ) {
        logger('habilitarProducto '+isLogeado);
        if ($('#id-img-nuevo_' + productoDTO.idProducto).length > 0 && productoDTO.nuevo	) {
            $('#id-img-nuevo_' + productoDTO.idProducto).attr('src', 'img/New.png');
        } else {
            $('#id-img-nuevo_' + productoDTO.idProducto).remove();
        }
        if($('#id-img-nuevo-det_'+productoDTO.idProducto).length > 0 && productoDTO.nuevo	) {
            $('#id-img-nuevo-det_'+productoDTO.idProducto).attr('src', 'img/Nuevo.png');
        } else{
            $('#id-img-nuevo-det_' + productoDTO.idProducto).remove();
        }
        if( isLogeado ) {
            var aBottonCart = $('<a>', {
                id : 'id-add-to-cart_'+productoDTO.idProducto,
                href: '#',
                class: 'btn btn-default add-to-cart',
                text: 'Add to cart',
                on: {
                    click: function () {
                        serviceAgregarProductoAReservar(productoDTO.idProducto);
                        return false;
                    }
                }
            });
            var iShoppingCart = $('<i>', {
                class: 'fa fa-shopping-cart'
            });
            aBottonCart.prependTo(iShoppingCart);
            $('#spanSuperDetProd_'+productoDTO.idProducto).append(aBottonCart);
        }
    }
    function quitarBotonAddDetalleReservado(productoDTO)	{
        $('#id-add-det-to-cart_'+productoDTO.idProducto).remove();
        if($('#id-img-nuevo-det_'+productoDTO.idProducto).length > 0)	{
            $('#id-img-nuevo-det_'+productoDTO.idProducto).attr('src', 'img/NoDisponible.png');
        }else	{
            var imgReservado = $('<img>',{
                src : 'img/NoDisponible.png',
                class : 'newarrival',
                alt : ''
            });
            $('#id-det-product-information_'+productoDTO.idProducto).append(imgReservado);
        }
    }
    function quitarBotonAddDetalle(productoDTO)	{
        $('#id-add-det-to-cart_'+productoDTO.idProducto).remove();
        if($('#id-img-nuevo-det_'+productoDTO.idProducto).length > 0)	{
            $('#id-img-nuevo-det_'+productoDTO.idProducto).attr('src', 'img/Reserver.png');
        }else	{
            var imgReservado = $('<img>',{
                src : 'img/Reserver.png',
                class : 'newarrival',
                alt : ''
            });
            $('#id-det-product-information_'+productoDTO.idProducto).append(imgReservado);
        }
    }
    function cssReservarProducto(productoDTO)	{
        $('#idProd_'+productoDTO.idProducto).off();
        $('#idProd_'+productoDTO.idProducto).css('cursor', 'default');
        $('#id-add-to-cart_'+productoDTO.idProducto).css('background-color', 'black');
        $('#id-add-to-cart_'+productoDTO.idProducto).text('RESERVADO');
        $('#id-add-to-cart_'+productoDTO.idProducto).off();
        if($('#id-img-nuevo_'+productoDTO.idProducto).length > 0){
            $('#id-img-nuevo_'+productoDTO.idProducto).attr('src', 'img/Reservado.png');
        }else{
            var imgReservado = $('<img>',{
                src : 'img/Reservado.png',
                class : 'new'
            });
            $('#id-single-products_'+productoDTO.idProducto).append(imgReservado);
        }
    }
    function cssProductoReservado(productoDTO)	{
        $('#idProd_'+productoDTO.idProducto).off();
        $('#idProd_'+productoDTO.idProducto).css('cursor', 'default');
        $('#id-add-to-cart_'+productoDTO.idProducto).css('background-color', 'red');
        $('#id-add-to-cart_'+productoDTO.idProducto).text('NO DISPONIBLE');
        $('#id-add-to-cart_'+productoDTO.idProducto).off();
        if($('#id-img-nuevo_'+productoDTO.idProducto).length > 0){
            $('#id-img-nuevo_'+productoDTO.idProducto).attr('src', 'img/NoDisponible.png');
        }else{
            var imgNoDisponible = $('<img>',{
                src : 'img/NoDisponible.png',
                class : 'new'
            });
            $('#id-single-products_'+productoDTO.idProducto).append(imgNoDisponible);
        }
    }
    function serviceLogout() {
        var webUriShop = window.location.protocol+'//' + document.location.host;
        logger('webUriShop = '+webUriShop);
        window.location.replace(webUriShop);
    }
    function serviceRegistrarUsuario(){
        var service = {
            action: 'REGISTRAR_CLIENTE',
            regnombre:	val('txtregnombre'),
            regemail : val('txtregemail'),
            regpassword : val('txtregpassword'),
            regtelefono : val('txtregtelefono')
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function usuarioRegistrado(){
        limpiarCampos('txtnombre','txtemail','txtpassword','txtrepassword');
        //@Service: registraUsuario
        var some_html = '';
        some_html += '<h2>Active su cuenta!</h2><br />';
        some_html += '<h5>Revise su email que hemos enviado a su cuenta <div id="idEmailConfirmacion">genesiscastillo@hotmail.com</div></h5>';
        bootbox.alert(some_html);
    }
    function serviceValidarUsuario()	{
        var service = {
            action: 'LOGEAR_USUARIO',
            email : val('txtemailuser'),
            password : val('txtpassworduser')
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function alertUsuarioInvalido(mensaje) {
        var some_html = '';
        some_html += '<h2>Advertencia!</h2><br />';
        some_html += '<h3>'+mensaje+'</h3>';
        bootbox.alert(some_html);
        limpiarCampos('txtemailuser' , 'txtpassworduser');
    }
    function usuarioValido( nombre )	{
        $('#id-nombre-usuarioDTO').html('');
        $('#id-nombre-usuarioDTO').html('<h4>Hola '+nombre+'</h4>');
/*
        $('#idliCart').show();
        $('#idliLogin').hide();
        $('#idliLogout').show();
*/
        $('#panel-login-usuarioDTO').hide();
        $('#panel-catalogo-productos').show();
        $('#idInputTalla').val('0');
        cargarCatalogo( '1' );
    }
    function cargarCatalogo( pagina )	{
        logger('cargarCatalogo  pagina = '+pagina);
        $('#panel-productos-reservados').html('');
        $('#panel-descripcion-productoDTO').html('');
        var catalogo = $('#idInputCatalogo').val();
        var talla = $('#idInputTalla').val();
        servicePrepararCatalogo(catalogo , talla , pagina);
        return false;
    }
    function servicePrepararCatalogo(catalogo , talla , pagina)	{
        $('#panel-productos').html('');
        var divFeatures = $('<div>',{
            class : 'features_items'
        });
        var h2Title = $('<h2>',{
            class : 'title text-center',
            text : ''+catalogoArray[catalogo] +' - '+tallaArray[talla]
        });
        divFeatures.append(h2Title);
        $('#panel-productos').append(divFeatures);
        $('#panel-productos').show();
        var service = {
            action: 'AGREGAR_PRODUCTOS',
            catalogo : catalogo,
            talla  :  talla,
            pagina : pagina
        };
        logger('send socket-->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function agregarPaginacion( numeroPaginas )	{
        var divRow = $('<div>',{
            class : 'row'
        })
        var nav = $('<nav>');
        var ulnav = $('<ul>',{
            class : 'pager'
        });
        var aPag = new Array(numeroPaginas)
        for( var i = 0 ; i < numeroPaginas ; i++) {
            aPag[i] = $('<a>', {
                href: '',
                class: 'btn btn-default',
                text: String(i+1)
            });
            logger('[a]->'+aPag[i].html());
            ulnav.append(aPag[i]);
        }
        ulnav.on('click', 'a', function () {
                    cargarCatalogo($(this).html());
                    return false;
                }
        );
        nav.append(ulnav);
        divRow.append(nav);
        $('#panel-productos').append(divRow);
    }
    function agregarProductoDisponible( productoDTO , isLogeado){
        logger('productoDTO ->'+productoDTO );
        var imgProducto = $('<img>', {
            src : '/RecyclothesEAR-web/imageServlet?id='+productoDTO.idFotoProducto
        });
        var divImgProducto = '';
        if( isLogeado )
        {
            divImgProducto = $('<div>', {
                id: 'idProd_'+productoDTO.idProducto,
                on: {
                    click: function (event) {
                        serviceMostrarDetalleProducto(productoDTO.idProducto);
                        return false;
                    },
                }
            });
            divImgProducto.css('cursor', 'pointer');
            divImgProducto.append(imgProducto);
        }
        var h2PrecioProducto = $('<h2>',{
            text : '$'+productoDTO.precioProducto
        });
        var pDescripcionProducto = $('<p>',{
            text : productoDTO.descripcion+' - Talla :'+productoDTO.tallaEnum.name
        });
        if( isLogeado ) {
            var aBottonCart = $('<a>', {
                id : 'id-add-to-cart_'+productoDTO.idProducto,
                href: '#',
                class: 'btn btn-default add-to-cart',
                text: 'Add to cart',
                on: {
                    click: function () {
                        serviceAgregarProductoAReservar(productoDTO.idProducto);
                        return false;
                    }
                }
            });
            var iShoppingCart = $('<i>', {
                class: 'fa fa-shopping-cart'
            });
            aBottonCart.prependTo(iShoppingCart);
        }
        var divInfoProducto = $('<div>',{
            class : 'productinfo text-center',
            style : 'border: Thin solid #696763;'
        });
        if( divImgProducto.length > 0 )	{
            divInfoProducto.append( divImgProducto ).append(h2PrecioProducto).append(pDescripcionProducto).append(aBottonCart);
        }else{
            divInfoProducto.append( imgProducto ).append(h2PrecioProducto).append(pDescripcionProducto);
        }

        var divSingleProducto = $('<div>',{
            id : 'id-single-products_'+productoDTO.idProducto,
            class : 'single-products'
        });
        /*
         if( 	productoDTO.nuevo	) 	{
         var imgNew = $('<img>', {
         id : 'id-img-nuevo_'+productoDTO.idProducto,
         src: 'img/New.png',
         class: 'new',
         alt: ''
         });
         divSingleProducto.append(divInfoProducto).append(imgNew);
         }else{
         */
        divSingleProducto.append(divInfoProducto);
//			}
        var divProductImageWrapper = $('<div>',{
            class : 'product-image-wrapper'
        });
        divProductImageWrapper.append(divSingleProducto);
        var divProducto = $('<div>',{
            class : 'col-sm-4'
        });
        divProducto.append(divProductImageWrapper);

        $('.features_items').append(divProducto);
    }
    function serviceMostrarDetalleProducto(idProducto){
        var service = {
            action: 'VER_DETALLE_PRODUCTO',
            idProducto : idProducto
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function mostrarDetalleProducto( detalleProductoDTO ) {
        var productoDTO = detalleProductoDTO.productoDTO;
        var fotoProductoDTOs= JSON.parse(	detalleProductoDTO.fotoProductoDTOs );

        $('#panel-descripcion-productoDTO').html('');
        $('#panel-descripcion-productoDTO').hide();
        var imgDetProd = $('<img>', {
            src : '/RecyclothesEAR-web/imageServlet?id='+productoDTO.idFotoProducto,
            alt: '',
            width : '500px'
        });
        var divViewProd = $('<div>', {
            class: 'view-product'
        });
        divViewProd.append(imgDetProd);

        var divCol5detProd = $('<div>', {
            class: 'col-sm-7'
        });
        divCol5detProd.append(divViewProd)
        var pagerBtn = $('<ul>',{
            class : 'pager'
        });

        if (!(detalleProductoDTO.isPrimero)) {
            var aPrimero = $('<a>',{
                href : '',
                class: 'btn btn-default',
                text: '<< Ver Anterior',
                on: {
                    click: function () {
                        serviceMostrarDetalleProducto(detalleProductoDTO.idProductoAnterior);
                        return false;
                    }
                }
            });
            var liAnterior = $('<li>', {
                class: 'previous'
            });
            liAnterior.append(aPrimero);
            pagerBtn.append(liAnterior);
        }
        if (!(detalleProductoDTO.isUltimo)) {
            var aUltimo = $('<a>',{
                href : '',
                class: 'btn btn-default',
                text: 'Ver Siguiente >>',
                on: {
                    click: function () {
                        serviceMostrarDetalleProducto(detalleProductoDTO.idProductoSiguiente);
                        return false;
                    }
                }
            });
            var liSiguiente = $('<li>', {
                class: 'next'
            });
            liSiguiente.append(aUltimo);
            pagerBtn.append(liSiguiente);
        }
        var divBtn = $('<div>',{
            class : 'row'
        });
        divBtn.append(pagerBtn);
        divCol5detProd.append( divBtn );

        var h2DetProd = $('<h2>', {
            text: productoDTO.descripcion
        });
        var h2DetProd2 = $('<h2>', {
            text: 'Talla :'+productoDTO.tallaEnum.name
        });
        var p2DetProd = $('<p>', {
            text: 'Web ID: ' + productoDTO.idProducto
        });
        if(	productoDTO.estadoProductoEnum === 'DISPONIBLE'	) {
            var iBtnDetProd = $('<i>', {
                class: 'fa fa-shopping-cart'
            });
            var buttonDetProd = $('<button>', {
                id : 'id-add-det-to-cart_'+productoDTO.idProducto,
                type: 'button',
                class: 'btn btn-fefault cart',
                text: 'Add to Cart',
                on: {
                    click: function () {
                        serviceAgregarProductoAReservarDesdeDetalle(productoDTO.idProducto);
                        return false;
                    }
                }
            });
            buttonDetProd.prependTo(iBtnDetProd);
        }

        var spanPreDetProd = $('<span>', {
            text: 'CLP $' + productoDTO.precioProducto
        });

        var spanSuperDetProd = $('<span>',{
            id : 'spanSuperDetProd_'+productoDTO.idProducto
        });
        if(	productoDTO.estadoProductoEnum === 'DISPONIBLE'	) {
            spanSuperDetProd.append(spanPreDetProd).append(buttonDetProd);
        }else{
            spanSuperDetProd.append(spanPreDetProd);
        }

        $('#panel-productos').html('');

        var divSimilarProduct = $('<div>',{
            id : 'similar-product',
            class : 'carousel slide'
        });
        var divCarouselInner = $('<div>',{
            class : 'carousel-inner'
        });
        var divItem = $('<div>',{
            class : 'item active'
        });
        if( fotoProductoDTOs.length > 1 ) {
            $.each(fotoProductoDTOs.reverse(), function (i, fotoProductoDTO) {
                logger('Cargandooo idFotoProducto = ' + fotoProductoDTO.idFotoProducto);
                var imgAdjFoto = $('<img>', {
                    src: '/RecyclothesEAR-web/imageServlet?id=' + fotoProductoDTO.idFotoProducto,
                    padding: '0px 2px 0px 2px',
                    alt: '',
                    width: '100px',
                    height: '100px'
                });
                var aHrefAdjFoto = $('<div>', {
                    on: {
                        click: function () {
                            var imageAd = $('<img>', {
                                src: '/RecyclothesEAR-web/imageServlet?id=' + fotoProductoDTO.idFotoProducto,
                                alt: '',
                                width: '500px'
                            });
                            $('.view-product').html('');
                            $('.view-product').append(imageAd);
                            return false;
                        }
                    }
                });
                aHrefAdjFoto.css('cursor', 'pointer');
                aHrefAdjFoto.append(imgAdjFoto);
                divItem.append(aHrefAdjFoto);
            });
        }
        divCarouselInner.append(divItem);

        divSimilarProduct.append(divCarouselInner);

        var divProdInfo = $('<div>', {
            id : 'id-det-product-information_'+productoDTO.idProducto,
            class: 'product-information'
        });
        if( productoDTO.estadoProductoEnum === 'RESERVADO' ) {
            var imgNewNig = $('<img>',{
                src : 'img/Reserver.png',
                class : 'newarrival',
                alt : ''
            });
            divProdInfo.append(imgNewNig).append(h2DetProd).append(h2DetProd2).append(p2DetProd).append(spanSuperDetProd);
        }
        else if(	productoDTO.nuevo	) {
            var imgNewNig = $('<img>', {
                id : 'id-img-nuevo-det_'+productoDTO.idProducto,
                src: 'img/Nuevo.png',
                class: 'newarrival',
                alt: ''
            });
            divProdInfo.append(imgNewNig).append(h2DetProd).append(h2DetProd2).append(p2DetProd).append(spanSuperDetProd);
        }
        else	{
            divProdInfo.append(h2DetProd).append(h2DetProd2).append(p2DetProd).append(spanSuperDetProd);
        }

        divProdInfo.append(divSimilarProduct);

        var divCol7detProd = $('<div>', {
            class: 'col-sm-5'
        });
        divCol7detProd.append(divProdInfo);
        var divProdDeta = $('<div>', {
            class: 'product-details'
        })
        divProdDeta.append(divCol5detProd).append(divCol7detProd);
        $('#panel-descripcion-productoDTO').append(divProdDeta);
        $('#panel-descripcion-productoDTO').show();
    }

    function serviceEliminarProductoReservado(idProducto){
        var service = {
            action: 'HABILITAR_PRODUCTO',
            idProducto : idProducto
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function serviceAgregarProductoAReservarDesdeDetalle(idProducto){
        var service = {
            action: 'RESERVAR_PRODUCTO_DETALLE',
            idProducto : idProducto
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }
    function serviceAgregarProductoAReservar( idProducto ) {
        var service = {
            action: 'RESERVAR_PRODUCTO',
            idProducto : idProducto
        };
        logger('socket ->'+JSON.stringify(service));
        socket.send(JSON.stringify(service));
        return false;
    }

    function agregarProductoPedido( productoDTO , opcion ){
        var imgProducto = $('<img>', {
            src : '/RecyclothesEAR-web/imageServlet?id='+productoDTO.idFotoProducto
        });
        var divImgProducto = $('<div>', {
            id: 'idProd_'+productoDTO.idProducto,
            on: {
                click: function (event) {
                    //pendiente levantar en una modal
                    return false;
                },
            }
        });
        divImgProducto.css('cursor', 'pointer');
        divImgProducto.append(imgProducto);
        var h2PrecioProducto = $('<h2>',{
            text : '$'+productoDTO.precioProducto
        });
        var pDescripcionProducto = $('<p>',{
            text : productoDTO.descripcion+' - Talla :'+productoDTO.tallaEnum.name
        });
        var divInfoProducto = $('<div>',{
            class : 'productinfo text-center',
            style : 'border: Thin solid #696763;'
        });
        divInfoProducto.append( imgProducto ).append(h2PrecioProducto).append(pDescripcionProducto);
        var divSingleProducto = $('<div>',{
            id : 'id-single-products_'+productoDTO.idProducto,
            class : 'single-products'
        });
        divSingleProducto.append(divInfoProducto);
        if('PEDIDOS' === opcion) {
            var imgNew = $('<img>', {
                id: 'id-img-nuevo_' + productoDTO.idProducto,
                src: 'img/close_box.png',
                class: 'new',
                alt: ''
            });
            var aRemove = $('<a>', {
                href: '',
                on: {
                    click: function () {
                        serviceEliminarProductoReservado(productoDTO.idProducto);
                        return false;
                    }
                }
            });
            aRemove.append(imgNew);
            divSingleProducto.append(aRemove);
        }
        var divProductImageWrapper = $('<div>',{
            class : 'product-image-wrapper'
        });
        divProductImageWrapper.append(divSingleProducto);
        var divProducto = $('<div>',{
            class : 'col-sm-3',
            id : 'id_producto_reservado_'+productoDTO.idProducto
        });
        divProducto.append(divProductImageWrapper);
        $('#panel-productos-reservados').append(divProducto);
    }

    function addZero(i) {
        if (i < 10) {
            i = "0" + i;
        }
        return i;
    }
    function gethhmmss() {
        var d = new Date();
        var h = addZero(d.getHours());
        var m = addZero(d.getMinutes());
        var s = addZero(d.getSeconds());
        return h + ":" + m + ":" + s;
    }
    function getCodigoFicticio() {
        var d = new Date();
        var h = addZero(d.getHours());
        var m = addZero(d.getMinutes());
        var s = addZero(d.getSeconds());
        return h + m + s;
    }
</script>
</body>
</html>