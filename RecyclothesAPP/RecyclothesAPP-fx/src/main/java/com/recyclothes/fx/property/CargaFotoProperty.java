package com.recyclothes.fx.property;

import com.recyclothes.common.dto.CargaFotoProductoDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Cesar on 19-08-2016.
 */
public class CargaFotoProperty {

    private CargaFotoProductoDTO cargaFotoProductoDTO;
    private StringProperty idFotoProducto;
    private StringProperty nombreFichero;
    private StringProperty ancho;
    private StringProperty alto;
    private StringProperty size;

    public CargaFotoProperty(CargaFotoProductoDTO cargaFotoProductoDTO){
        this.cargaFotoProductoDTO =cargaFotoProductoDTO;
        this.setIdFotoProducto(String.valueOf(cargaFotoProductoDTO.getFotoProductoDTO().getIdFotoProducto()));
        this.setNombreFichero(cargaFotoProductoDTO.getNameFile());
        this.setAlto( String.valueOf(cargaFotoProductoDTO.getAlto()));
        this.setAncho(String.valueOf(cargaFotoProductoDTO.getAncho()));
        this.setSize( String.valueOf(cargaFotoProductoDTO.getSize()));
    }

    public String getIdFotoProducto() {
        return idFotoProductoProperty().get();
    }

    public StringProperty idFotoProductoProperty() {
        if(idFotoProducto == null){
            idFotoProducto = new SimpleStringProperty();
        }
        return idFotoProducto;
    }

    public void setIdFotoProducto(String idFotoProducto) {
        this.idFotoProductoProperty().set(idFotoProducto);
    }

    public CargaFotoProductoDTO getCargaFotoProductoDTO() {
        return cargaFotoProductoDTO;
    }

    public void setCargaFotoProductoDTO(CargaFotoProductoDTO cargaFotoProductoDTO) {
        this.cargaFotoProductoDTO = cargaFotoProductoDTO;
    }

    public String getNombreFichero() {
        return nombreFicheroProperty().get();
    }

    public StringProperty nombreFicheroProperty() {
        if(nombreFichero == null){
            nombreFichero = new SimpleStringProperty();
        }
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFicheroProperty().set(nombreFichero);
    }

    public String getAncho() {
        return anchoProperty().get();
    }

    public StringProperty anchoProperty() {
        if(ancho == null){
            ancho  = new SimpleStringProperty();
        }
        return ancho;
    }

    public void setAncho(String ancho) {
        this.anchoProperty().set(ancho);
    }

    public String getAlto() {
        return altoProperty().get();
    }

    public StringProperty altoProperty() {
        if(alto == null){
            alto = new SimpleStringProperty();
        }
        return alto;
    }

    public void setAlto(String alto) {
        this.altoProperty().set(alto);
    }

    public String getSize() {
        return sizeProperty().get();
    }

    public StringProperty sizeProperty() {
        if(size == null){
            size = new SimpleStringProperty();
        }
        return size;
    }

    public void setSize(String size) {
        this.sizeProperty().set(size);
    }
}
