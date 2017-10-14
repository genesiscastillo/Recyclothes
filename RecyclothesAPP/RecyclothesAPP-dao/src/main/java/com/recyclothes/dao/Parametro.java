package com.recyclothes.dao;

/**
 * Created by Cesar on 10-09-2016.
 */
public class Parametro {

    int param1;
    String fecha;
    String listaString;
    TablaDTO tablaDTO;

    public int getParam1() {
        return param1;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public TablaDTO getTablaDTO() {
        return tablaDTO;
    }

    public void setTablaDTO(TablaDTO tablaDTO) {
        this.tablaDTO = tablaDTO;
    }

    public String getListaString() {
        return listaString;
    }

    public void setListaString(String listaString) {
        this.listaString = listaString;
    }
}
