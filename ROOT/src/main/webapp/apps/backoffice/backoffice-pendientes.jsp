<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <h2>Modulo Pendientes</h2>
<div class="col-sm-3">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Publicados</h3>
        </div>
        <div class="panel-body">
            <ul class="list-group" id="id-list-group-disponibles">
            </ul>
        </div>
    </div>
</div>
<div class="col-sm-3">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">Pendientes</h3>
        </div>
        <div class="panel-body">
            <ul class="list-group" id="id-list-group-pendientes">
            </ul>
        </div>
        <div class="panel-footer"><button id="id-btn-publicar-productos-pendientes" class="btn btn-default">PUBLICAR</button></div>
    </div>
</div>
<div class="col-sm-6">
    <div id="id_features_items" class="features_items">
    </div>
</div>
</section>
<script type="application/javascript">
    $(document).ready(function() {
        $('#id-btn-publicar-productos-pendientes').bind('click', function(){
            servicePublicarProductosPendientes();
        });
        serviceCargaPendientesPublicados();
    });
</script>