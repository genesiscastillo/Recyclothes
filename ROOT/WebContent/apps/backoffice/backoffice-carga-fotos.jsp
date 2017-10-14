<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <h2>Registro de Productos</h2>
        <form method="POST" action="uploadFotos" enctype="multipart/form-data" >
            File:
            <input type="file" name="file" id="file" />
            </br>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
    <div class="row" id="id-ul-paginacion">
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div id="id_features_items" class="features_items">
            </div>
        </div>
    </div>
</section>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg" style="width: 956px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Registro Productos</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 padding-right">
                        <div class="product-details"><!--product-details-->
                            <div class="col-sm-6">
                                <div id="id-view-product" class="view-product">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="login-form"><!--/product-information-->
                                    <form id="idFormProductoNuevo">
                                        <input id="idtxtdescripcion" type="text" placeholder="Descripcion Producto" required>
                                        <select id="idselcatalogo" style="padding-bottom: 10px" required>
                                            <option value="0" selected>Catalogo Ni&ntilde;o</option>
                                            <option value="1">Catalogo Ni&ntilde;a</option>
                                        </select>
                                        <br/>
                                        <br/>
                                        <select id="idseltalla" style="padding-bottom: 10px" required>
                                            <option value="0" selected> 0-3 meses</option>
                                            <option value="1"> 6 meses</option>
                                            <option value="2"> 9 meses</option>
                                            <option value="3">12 meses</option>
                                            <option value="4">18 meses</option>
                                            <option value="5">24 meses</option>
                                            <option value="6">Talla 2</option>
                                            <option value="7">Talla 3</option>
                                            <option value="8">Talla 4</option>
                                            <option value="9">Talla 5</option>
                                            <option value="10">Talla 6</option>
                                            <option value="11">Talla 7</option>
                                            <option value="12">Talla 8</option>
                                            <option value="13">Talla 10</option>
                                        </select>
                                        <br/>
                                        <br/>
                                        <input id="idtxtprecio" type="text" placeholder="Precio" required>
                                        <input type="hidden" id="idtxtidfotoproducto" value="15899663" disabled>
                                        <button id="idFormProductoNuevoGrabar" type="submit" class="btn btn-default cart" >GRABAR</button>
                                        <button id="idFormProductoNuevoEliminar" type="submit" class="btn btn-default cart" >ELIMINAR</button>
                                    </form>
                                </div><!--/product-information-->
                            </div>
                        </div><!--/product-details-->
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar Modal</button>
            </div>
        </div>
    </div>
</div>
    <button id="id-btn-open-modal" style="display:none" type="button" class="btn btn-default cart" data-toggle="modal" data-target="#myModal">Modo Modal</button>
    <input type="hidden" id="idtxtidfotoproductoseleccionado">
<script type="application/javascript">
    $(document).ready(function(){
        $('#idFormProductoNuevoGrabar').click( function(){
            serviceRegistrarProducto();
            return false;
        });
        $('#idFormProductoNuevoEliminar').click( function(){
            serviceEliminarFotoProducto();
            return false;
        });
        cargarFotosProductos('1');
    });
</script>