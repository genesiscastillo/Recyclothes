<%
  String rv = request.getParameter("rv");
  if("RESERVA".equals(rv) )    {
    %>
        <h2 class="title text-center">Codigo Reserva = <%=request.getParameter("codigoReserva") %> </h2>
        <input id="id-cod-reserva" type="hidden" value="<%=request.getParameter("codigoReserva")%>">
    <%
  }
%>
<section id="cart_items">
    <div class="container">
      <div id="panel-productos-reservados">
      </div>
    </div>
  </section> <!--/#cart_items-->

  <section id="panel-reserva-montototal">
    <div class="container">
      <div class="heading">
        <h3>Su Reserva esta <div id ="id-estado-reserva"></div></h3>
      </div>
      <div class="row">
        <div class="col-sm-6">
          <div class="total_area">
            <ul>
              <li>
                <label>Despu&eacute;s de realizar la reserva de tus pedidos, cont&aacute;ctanos para coordinar la entrega en un plazo de 24-48 horas</label>
                <label>WhatsApp : +56 9 73415469</label>
                <textarea id="id-comentario-reserva" rows="5" placeholder="Comentarios" readonly></textarea>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-sm-6">
          <div class="total_area">
            <ul>
              <li>Total <span><strong id="id-monto-total-reserva" /></span></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </section>
<script type="application/javascript">
    $(document).ready(function() {
        var codigoReserva = $('#id-cod-reserva').val();
        serviceVerReservaGenerada(codigoReserva);
        $('#idliCart').hide();
        $('#idliCatalogo').show();
    });
</script>