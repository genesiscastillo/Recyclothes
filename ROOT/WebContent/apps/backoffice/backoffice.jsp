<section>
    <div class="row">
        <div class="col-sm-4 col-sm-offset-1">
            <img src="img/caprichitos2.jpg"/>
        </div>
    </div>
    <div class="row">
        <ul class="nav nav-tabs" id="id-menu-admin">
            <li ><a href="#">Reservados</a></li>
            <li ><a href="#">Publicados</a></li>
            <li ><a href="#">Pendientes</a></li>
            <li ><a href="#">Carga Fotos</a></li>
            <li ><a href="#">Estado Servidor</a></li>
        </ul>
    </div>
</section>
<div class="container" id="panel-central">
</div>
<script type="application/javascript">
    $(document).ready(function() {
        logger('iniciando..');
        $.each($('#id-menu-admin').find('a'), function () {
            $(this).bind('click', function () {
                //if( $(this).parent().attr('class') != 'active' ) {
                    $('#id-menu-admin').find('a').parent('.active').attr('class', '');
                    $(this).parent().attr('class', 'active');
                    switch ($(this).text()) {
                        case 'Reservados' :
                            $('#panel-central').load('apps/backoffice/backoffice-reservados.jsp?rv='+<%=java.lang.System.currentTimeMillis()%>);
                            break;
                        case 'Publicados' :
                            $('#panel-central').load('apps/backoffice/backoffice-publicados.jsp?rv='+<%=java.lang.System.currentTimeMillis()%>);
                            break;
                        case 'Pendientes' :
                            $('#panel-central').load('apps/backoffice/backoffice-pendientes.jsp?rv='+<%=java.lang.System.currentTimeMillis()%>);
                            break;
                        case 'Carga Fotos' :
                            $('#panel-central').load('apps/backoffice/backoffice-carga-fotos.jsp?rv='+<%=java.lang.System.currentTimeMillis()%>);
                            break;
                        case 'Estado Servidor' :
                            $('#panel-central').load('apps/backoffice/backoffice-estado-servidor.jsp?rv='+<%=java.lang.System.currentTimeMillis()%>);
                            break;
                        default :
                            logger('$(this).val() ' + $(this).text());
                    }
                //}
            });
        });
    });
</script>
</body>
</html>