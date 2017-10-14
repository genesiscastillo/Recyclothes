<section id="panel-catalogo-productos" >
  <div class="container">
    <div class="row">
      <div class="col-sm-3">
        <div class="left-sidebar" id="id-panel-menu-categoria">
          <h2>Catalogo</h2>
          <div class="panel-group category-products" id="accordian"><!--category-productsr-->
            <div class="panel panel-default"><!-- AQUI INICIO -->
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a data-toggle="collapse" data-parent="#accordian" href="#sportswear1">
                    <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                    Ropitas para Ni&ntilde;os
                  </a>
                </h4>
              </div>
              <div id="sportswear1" class="panel-collapse in" style="height: auto;">
                <div class="panel-body"  id="id-panel-catalogo-nino">
                  <ul>
                    <li><a href="#" value="0"> 0-3 meses</a></li>
                    <li><a href="#" value="1"> 6 meses</a></li>
                    <li><a href="#" value="2"> 9 meses</a></li>
                    <li><a href="#" value="3">12 meses</a></li>
                    <li><a href="#" value="4">18 meses</a></li>
                    <li><a href="#" value="5">24 meses</a></li>
                    <li><a href="#" value="6">Talla 2</a></li>
                    <li><a href="#" value="7">Talla 3</a></li>
                    <li><a href="#" value="8">Talla 4</a></li>
                    <li><a href="#" value="9">Talla 5</a></li>
                    <li><a href="#" value="10">Talla 6</a></li>
                    <li><a href="#" value="11">Talla 7</a></li>
                    <li><a href="#" value="12">Talla 8</a></li>
                    <li><a href="#" value="13">Talla 10</a></li>
                  </ul>
                </div>
              </div>
            </div><!-- AQUI FIN-->
            <div class="panel panel-default"><!-- AQUI INICIO -->
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a data-toggle="collapse" data-parent="#accordian" href="#sportswear2">
                    <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                    Ropitas para Ni&ntilde;as
                  </a>
                </h4>
              </div>
              <div id="sportswear2" class="panel-collapse in" style="height: auto;">
                <div class="panel-body" id="id-panel-catalogo-nina">
                  <ul>
                    <li><a href="#" value="0"> 0-3 meses</a></li>
                    <li><a href="#" value="1"> 6 meses</a></li>
                    <li><a href="#" value="2"> 9 meses</a></li>
                    <li><a href="#" value="3">12 meses</a></li>
                    <li><a href="#" value="4">18 meses</a></li>
                    <li><a href="#" value="5">24 meses</a></li>
                    <li><a href="#" value="6">Talla 2</a></li>
                    <li><a href="#" value="7">Talla 3</a></li>
                    <li><a href="#" value="8">Talla 4</a></li>
                    <li><a href="#" value="9">Talla 5</a></li>
                    <li><a href="#" value="10">Talla 6</a></li>
                    <li><a href="#" value="11">Talla 7</a></li>
                    <li><a href="#" value="12">Talla 8</a></li>
                    <li><a href="#" value="13">Talla 10</a></li>
                  </ul>
                </div>
              </div>
            </div><!-- AQUI FIN-->
          </div><!--/category-productsr-->
        </div>
      </div>

      <div id="panel-contenedor" class="col-sm-9 padding-right">
        <div id="panel-productos">
          <div class="features_items"><!--features_items-->
            <h2 class="title text-center">Items</h2>
          </div><!--features_items-->
        </div>
        <div id="panel-descripcion-productoDTO" style="display: none">
        </div>
      </div>
    </div>
  </div>
</section>
<script type="application/javascript">
  $(document).ready(function() {
    $.each($('#id-panel-catalogo-nino').find('a'), function () {
      $(this).bind('click', function () {
        $('#idInputCatalogo').val('0');
        $('#idInputTalla').val($(this).attr('value'));
        cargarCatalogo('1');
      });
    });
    $.each($('#id-panel-catalogo-nina').find('a'), function () {
      $(this).bind('click', function () {
        $('#idInputCatalogo').val('1');
        $('#idInputTalla').val($(this).attr('value'));
        cargarCatalogo('1');
      });
    });
    cargarCatalogo( '1' );
  });
</script>