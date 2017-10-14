<div class="row">
  <div class="col-sm-8">
    <div class="contact-form">
      <h2 class="title text-center">Contacto con Supervisor</h2>
      <div class="status alert alert-success" style="display: none"></div>
      <form id="id-main-contact-form" autocomplete="off" >
        <div class="form-group col-md-6">
          <input type="text" id="id-contact-name" class="form-control" required="required" placeholder="Nombre">
        </div>
        <div class="form-group col-md-6">
          <input type="email" id="id-contact-email" class="form-control" required="required" placeholder="Email">
        </div>
        <div class="form-group col-md-12">
          <input type="text" id="id-contact-subject" class="form-control" required="required" placeholder="Asunto">
        </div>
        <div class="form-group col-md-12">
          <textarea id="id-contact-message" required="required" class="form-control" rows="8" placeholder="Sus comentarios aqui"></textarea>
        </div>
        <div class="form-group col-md-6">
          <input type="text" id="id-contact-token" class="form-control" required="required" placeholder="Ingrese Codigo Generado">
        </div>
        <div class="form-group col-md-6">
          <input type="text" id="id-contact-tokenGenerado" class="form-control" disabled >
        </div>
        <div class="form-group col-md-12">
          <input type="submit" class="btn btn-primary pull-right" value="Enviar">
        </div>
      </form>
    </div>
  </div>
  <div class="col-sm-4">
    <div class="contact-info">
      <h2 class="title text-center">Informaci&oacute;n Contacto</h2>
      <address>
        <p>Recyclothes.cl</p>
        <p>Santiago de Chile</p>
        <p>Region Metropolitana</p>
        <p>CHILE</p>
        <p>Mobile: +56 9 73415469</p>
        <p>Email: c.vanessavillanueva@gmail.com</p>
      </address>
      <div class="social-networks">
        <h2 class="title text-center">Social Networking</h2>
        <ul>
          <li>
            <a href="https://www.facebook.com/caprichitos.modainfantil.7" target="_blank"><i class="fa fa-facebook"></i></a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<script type="application/javascript">
    $(document).ready(function()  {
      $('#id-main-contact-form').submit(function()  {
        var tokenIngresado = $('#id-contact-token').val();
        var tokenGenerado  = $('#id-contact-tokenGenerado').val();
          logger('tokenIngresado = '+tokenIngresado );
          logger('tokenGenerado  = '+tokenGenerado );
        if(tokenIngresado === tokenGenerado ) {
            var nombre = $('#id-contact-name').val();
            var correo = $('#id-contact-email').val();
            var asunto = $('#id-contact-subject').val();
            var mensaje = $('#id-contact-message').val();
            var token= $('#id-contact-token').val();
            serviceSendContacto(nombre, correo, asunto, mensaje, token);
            bootbox.alert('Gracias por enviar sus comentarios, pronto les responderemos a la brevedad');
            if($('#idIrCatalogo').length > 0){
              $('#idIrCatalogo').click();
            }else{
              $('#id-panel-central').html('');
            }
        }else {
            bootbox.alert('Ingrese el codigo correcto');
            $('#id-contact-token').val('');
        }
        return false;
      });
      serviceGenerarTokenContacto();
    });
</script>