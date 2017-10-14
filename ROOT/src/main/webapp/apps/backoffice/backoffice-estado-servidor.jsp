<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
  <h2>Modulo Estado Servidor</h2>
  <div class="col-sm-3">
    <div class="panel panel-primary">
      <div class="panel-heading">
        <h3 class="panel-title">Publicados</h3>
      </div>
      <div class="panel-body">
        <ul class="list-group" id="id-list-tables-spaces">
        </ul>
      </div>
    </div>
  </div>
</section>
<script type="application/javascript">
  $(document).ready(function() {
    serviceCargarEstadoServidor();
  });
</script>