<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">Modulo de Reservas</div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-2">
                        <p>Tipo Reserva</p>
                    </div>
                    <div class="col-sm-4">
                        <select id="idSelectEstadoReserva">
                            <option value="0" selected>Pendiente</option>
                            <option value="1">Pagado</option>
                            <option value="2">Entregado</option>
                            <option value="3">Anulado</option>
                        </select>
                    </div>
                    <div class="col-sm-2">
                    </div>
                    <div class="col-sm-4" id="id-input-cod-reserva">
                        <input id="id-txt-cod-reserva" type="text" placeholder="Codigo Reserva"><a href="#" id="id-btn-buscar-codigo-reserva" class="btn btn-default">BUSCAR</a>
                    </div>
                </div>
                <div class="row">
                    <form class="form-horizontal" >
                    <div class="table-responsive cart_info">
                        <table class="table-bordered">
                            <thead>
                            <tr>
                                <th>Codigo Reserva</th>
                                <th>Fecha Reserva</th>
                                <th>Fecha Vencimiento</th>
                                <th>Nombres</th>
                                <th>Monto Total</th>
                                <th>Monto a Pagar</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="table-reservas" >
                            </tbody>
                        </table>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <p>.</p>
    </div>
    <div class="panel panel-default" id="id-form-horizontal-pendiente" style="display: none;">
        <div class="panel-heading">Registro Reserva Pendiente</div>
        <div class="panel-body">
            <form class="form-horizontal" >
                <table class="table-bordered">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Nombres</th>
                        <th>Fecha Entrega</th>
                        <th>Monto Total</th>
                        <th>Monto a Pagar</th>
                        <th>Datos Entrega</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="hidden" id="id-txt-reserva-id"></td>
                        <td><input type="text" id="id-txt-reserva-nombres" disabled></td>
                        <td><input type="text" id="id-txt-reserva-fechaEntrega"></td>
                        <td><input type="text" id="id-txt-reserva-montoTotal" disabled></td>
                        <td><input type="text" id="id-txt-reserva-montoAPagar"></td>
                        <td><textarea id="id-txt-reserva-datosEntrega" style="width: 300px;height: 26px"></textarea></td>
                        <td><button type="button">PAGADO</button></td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <div class="features_items">
            </div>
        </div>
    </div>
</section>
<link rel="stylesheet" href="../../css/jquery-ui.min.css">
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<script src="../../js/jquery-1.12.0.min.js"></script>
<script src="../../js/jquery-ui.min.js"></script>
<script type="application/javascript">
    $(document).ready(function() {
        $('#id-btn-buscar-codigo-reserva').bind('click',function(){
                if( $('#id-txt-cod-reserva').val()){
                    serviceCargarClienteReserva('CODIGO_RESERVA');
                }
        });
        $('#id-txt-reserva-fechaEntrega').datepicker({
            dateFormat: 'dd-mm-yy',
            onSelect : function(date){
                var idReserva = $('#id-txt-reserva-id').val();
                var fechaActualizada = date;
                var montoTotal  = $('#id-txt-reserva-montoTotal').val();
                serviceActualizarReserva(idReserva , fechaActualizada , montoTotal);
            }
        });
        $('#id-txt-reserva-montoAPagar').on('keyup' , function(){
            if(! ($('#id-txt-reserva-montoAPagar').val() == $('#id-txt-reserva-montoTotal').val())){
                var idReserva = $('#id-txt-reserva-id').val();
                var fechaActualizada = $('#id-txt-reserva-fechaEntrega').val()
                var montoTotal  = $('#id-txt-reserva-montoTotal').val();
                serviceActualizarReserva(idReserva , fechaActualizada , montoTotal );
            }
        });
        $('#idSelectEstadoReserva').change(function(){
            if($('#idSelectEstadoReserva option:selected').text() != 'Pendiente'){
                $('#id-input-cod-reserva').hide();
            }else{
                $('#id-input-cod-reserva').show();
            }
            $('.features_items').html('');
            $('#table-reservas').html('');
            $('#id-form-horizontal-pendiente').hide();
            serviceCargarClienteReserva('ESTADO_RESERVA');
        });
        $('#table-reservas tr').hover(function() {
            $(this).addClass('hover');
        }, function() {
            $(this).removeClass('hover');
        });
        $('#btn-aceptar').bind('click',function(){
            var service = {
                    action : 'RESERVADOS'
                    };
            socket.send(JSON.stringify(service));
        });
        serviceCargarClienteReserva('ESTADO_RESERVA');
    });
</script>