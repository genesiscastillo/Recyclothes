<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <h2>Modulo Publicados</h2>
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
<div class="col-sm-9">
    <div id="id_features_items" class="features_items">
    </div>
</div>
</section>
<script type="application/javascript">
    $(document).ready(function() {
        serviceCargaPendientesPublicados();
    });
</script>