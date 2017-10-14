package com.recyclothes.common.dto;

import java.io.Serializable;

/**
 * Created by Cesar on 19-08-2016.
 */
public class CargaFotoProductoDTO implements Serializable   {

    String nameFile;
    int ancho;
    int alto;
    long size;

    FotoProductoDTO fotoProductoDTO;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public FotoProductoDTO getFotoProductoDTO() {
        return fotoProductoDTO;
    }

    public void setFotoProductoDTO(FotoProductoDTO fotoProductoDTO) {
        this.fotoProductoDTO = fotoProductoDTO;
    }

    @Override
    public String toString() {
        return "CargaFotoProductoDTO{" +
                "nameFile='" + nameFile + '\'' +
                ", ancho=" + ancho +
                ", alto=" + alto +
                ", size=" + size +
                ", fotoProductoDTO=" + fotoProductoDTO +
                '}';
    }
}
