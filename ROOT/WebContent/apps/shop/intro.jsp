<section id="slider"><!--slider-->
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#slider-carousel" data-slide-to="1"></li>
                        <li data-target="#slider-carousel" data-slide-to="2"></li>
                    </ol>

                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="col-sm-6">
                                <h1>RecyClothes</h1>
                                <h2>Moda Infantil</h2>
                                <p>Venta de ropa americana para bebes y ni&ntilde;os, categoria PREMIUN</p>
                                <div id="id-iniciar-button-1"></div>
                            </div>
                            <div class="col-sm-6">
                                <img src="img/ropa1.jpg" width="80%;" />
                            </div>
                        </div>
                        <div class="item">
                            <div class="col-sm-6">
                                <h1>RecyClothes</h1>
                                <h2>Moda Infantil</h2>
                                <p>Venta de ropa americana para bebes y ni&ntilde;os, categoria PREMIUN</p>
                                <div id="id-iniciar-button-2"></div>
                            </div>
                            <div class="col-sm-6">
                                <img src="img/ropa2.JPG" width="80%;" />
                            </div>
                        </div>
                        <div class="item">
                            <div class="col-sm-6">
                                <h1>RecyClothes</h1>
                                <h2>Moda Infantil</h2>
                                <p>Venta de ropa americana para bebes y ni&ntilde;os, categoria PREMIUN</p>
                                <div id="id-iniciar-button-3"></div>
                            </div>
                            <div class="col-sm-6">
                                <img src="img/ropa3.JPG" width="80%;" />
                            </div>
                        </div>
                    </div>
                    <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                        <i class="fa fa-angle-left"></i>
                    </a>
                    <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                        <i class="fa fa-angle-right"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section><!--/slider-->
<script type="application/javascript">
    $(document).ready(function()    {
        var buttonAcceder = $('<button>', {
            type: 'button',
            text: 'Ir al Catalogo Recyclothes',
            class: 'btn btn-default',
            on : {
                    click : function()  {
                        //<!--$('#id-menu-principal').html('<div class="shop-menu pull-right"><ul class="nav navbar-nav"><li><a id="idliCart" style="display: none" href="#"><i class="fa fa-shopping-cart"></i>Mis Pedidos</a></li>   <li><a id="idliLogin" href="#"><i class="fa fa-lock"></i> Iniciar Sesi&oacute;n</a></li>    <li><a id="idliCatalogo" href="#"><i class="fa fa-search"></i> Ir al Catalogo</a></li>    <li><a id="idliLogout" style="display: none" href="#"><i class="fa fa-power-off"></i> Cerrar Sesi&oacute;n</a></li>    </ul>    </div>');-->//
                        $('#id-menu-principal').html('<div class="shop-menu pull-right"><ul class="nav navbar-nav"><li><a id="idliLogin" href="#"><i class="fa fa-lock"></i> Iniciar Sesi&oacute;n</a></li></ul></div>');
                        $('#panel-central').load('apps/shop/catalogo.jsp');
                }
            }
        });
        $('#id-iniciar-button-1').html(buttonAcceder);
    });
</script>
