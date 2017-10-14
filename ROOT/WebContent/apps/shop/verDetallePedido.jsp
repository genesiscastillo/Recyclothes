<div>
  <section id="cart_items">
    <div class="container">
      <div id="panel-productos-reservados">
      </div>
    </div>
  </section>

  <section id="panel-reserva-montototal">
    <div class="container">
      <div class="heading">
        <h3>&iquest;Est&aacute;s listo para reservar tus pedidos&#63;</h3>
      </div>
      <div class="row">
        <div class="col-sm-6">
          <div class="total_area">
            <ul>
              <li>
                <label>Despu&eacute;s de realizar la reserva de tus pedidos, cont&aacute;ctanos para coordinar la entrega en un plazo de 24-48 horas</label>
                <label>WhatsApp : +56 9 73415469</label>
                <textarea id="idComentariosPedidos" rows="5" placeholder="Comentarios" autocomplete="off"></textarea>
              </li>
            </ul>
          </div>
        </div>
        <div class="col-sm-6">
          <div class="total_area">
            <ul>
              <li>Total <span><strong id="id-monto-total-pedidos">$0</strong></span></li>
            </ul>
            <a id="id-confirmar-reserva" class="btn btn-default check_out" href="#">Confirmar Reserva</a>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>
<script type="application/javascript">
  $(document).ready(function() {
    $('#id-confirmar-reserva').bind('click', function(){
      serviceConfirmarReserva();
      return false;
    });
      serviceVerDetallePedidos();
  });


</script>