<section id="panel-login-usuarioDTO" ><!--form-->
  <div class="container">
    <div class="row">
      <div class="col-sm-4 col-sm-offset-1">
        <div class="login-form">
          <h2>Iniciar Sesi&oacute;n</h2>
          <form id="idFormLoginUser" autocomplete="off" >
            <input id="txtemailuser" type="email" placeholder="Correo Electronico" required  autocomplete="off" />
            <input id="txtpassworduser" type="password" placeholder="Password" required  autocomplete="off" />
            <button type="submit" class="btn btn-default">Ingresar</button>
            <br/>
          </form>
          <span>
              <a id="id-href-recuperar-clave" href="#">recuperar mi clave</a>
          </span>
        </div>
      </div>
      <div class="col-sm-1">
        <h2 class="or">&Oacute;</h2>
      </div>
      <div class="col-sm-4">
        <div class="signup-form">
          <h2>&iquest;Eres Nuevo&#63; &iexcl;Inscribete&#33;</h2>
          <form id="idFormRegistrarme" autocomplete="off">
            <input id="txtregnombre" type="text" placeholder="Nombre" required/>
            <input id="txtregemail" type="email" placeholder="Correo Electronico" required/>
            <input id="txtregpassword" type="password" placeholder="Password" required/>
            <input id="txtregrepassword" type="password" placeholder="ReIngrese Password" required/>
            <input id="txtregtelefono" type="tel" placeholder="Telefono Celular" />
            <button type="submit" class="btn btn-default">Guardar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</section><!--/form-->
<script src="js/bootbox.min.js"></script>
<script type="application/javascript">
  $(document).ready(function() {
    $('#idFormLoginUser').submit(function(){
      serviceValidarUsuario();
      return false;
    });
    $('#idFormRegistrarme').submit(function(){
      if(val('txtregrepassword') === val('txtregpassword'))	{
        serviceRegistrarUsuario();
      }else{
        var alertCabecera  = '<h3>Estimado Cliente:</h3>';
        var alertPassword  = '<h2>Debe reigresar sus password, ya que no coinciden.</h2>';
        bootbox.alert( alertCabecera+alertPassword);
        $('#txtpassword').val('');
        $('#txtrepassword').val('');
        $('#txtpassword').focus();
      }
      return false;
    });
    $('#id-href-recuperar-clave').bind('click' , function (){
      alertRecuperarClave();
    });
    $('#idliLogin').hide();
    $('#idliCatalogo').show();
  });
</script>