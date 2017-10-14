package com.recyclothes.fx.enums;

/**
 * Created by Cesar on 19-07-2016.
 */
public enum ScreenEnum {

    INTRO("","/fxml/intro.fxml"),
    CARGAR_FOTOS("Cargar Fotos", "/fxml/productos/CargarFotos.fxml"),
    REGISTRAR_PRODUCTO("Registrar Producto", "/fxml/productos/CargaFotosRegistroProducto.fxml"),
    MODIFICAR_PRODUCTO("Modificar Producto" , "/fxml/productos/ModificarProducto.fxml"),
    AGENDAR_RESERVA("Agendar Reserva" , "/fxml/productos/AgendarReservaProducto.fxml"),
    ESTADISTICAS("Estadisticas" , ""),
    REGISTRO_CLIENTE("Regsitro Cliente", "/fxml/productos/RegistroCliente.fxml");

    private String descripcionItem;
    private String resource;


    ScreenEnum(String descripcionItem , String ressource){
        this.descripcionItem = descripcionItem;
        this.resource = ressource;
    }

    public String getDescripcionItem(){
        return this.descripcionItem;
    }
    public String getResource(){
        return this.resource;
    }
}
