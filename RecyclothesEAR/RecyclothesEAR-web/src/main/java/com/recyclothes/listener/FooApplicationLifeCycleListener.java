package com.recyclothes.listener;

import com.recyclothes.common.dto.ContactoDTO;
import com.recyclothes.common.utils.Utils;
import com.recyclothes.singleton.AdministracionService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Calendar;

/**
 * Created by Cesar on 07-02-2016.
 */
@WebListener
public class FooApplicationLifeCycleListener implements ServletContextListener {

    static final Logger LOGGER = Logger.getLogger(FooApplicationLifeCycleListener.class);

    @Inject
    AdministracionService administracionService;


    public void contextInitialized(ServletContextEvent event) {
        LOGGER.info("HOLAA INICIALIZANDO..!!");

        String nombre = "OPENSHIFT - Wildfly Server";
        String correo = "genesiscastillof@gmail.com";
        String asunto = "INICIANDO APP www.recyclothes.cl";
        String mensaje = "<h2>Se ha Iniciado la Aplicacion recyclothes.cl</h2><br/>" +
                "<h3>Fecha Hora  "+ Utils.getFechaHora(Calendar.getInstance().getTime())+"</h3><br/>"+
                "<h3>Atte. OpenShitf Console</h3>";
        ContactoDTO contactoDTO = new ContactoDTO(nombre , correo , asunto , mensaje);
        //administracionService.enviarCorreo(AccionEnum.CONTACTO_MENSAJE_USUARIO , "genesiscastillof@gmail.com" , contactoDTO);
        //bussinesService.cargarProductosDisponibles();
    }

    public void contextDestroyed(ServletContextEvent event) {
        LOGGER.info("CHAOOOOO .!!");
        String nombre = "OPENSHIFT - Wuldfly Server";
        String correo = "genesiscastillof@gmail.com";
        String asunto = "SHUTDOWN APP www.recyclothes.cl";
        String mensaje = "<h2>Se ha apagado por algun error inesperado</h2><br/>" +
                "<h3>favor revisar console ssh en openshift console.</h3><br/>"+
                "<h3>Fecha Hora  "+Utils.getFechaHora(Calendar.getInstance().getTime())+"</h3>";
        ContactoDTO contactoDTO = new ContactoDTO(nombre , correo , asunto , mensaje);
        //administracionService.enviarCorreo(AccionEnum.CONTACTO_MENSAJE_USUARIO, "genesiscastillof@gmail.com", contactoDTO);
    }
}