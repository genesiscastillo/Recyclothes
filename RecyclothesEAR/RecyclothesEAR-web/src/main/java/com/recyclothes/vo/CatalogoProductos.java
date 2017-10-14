package com.recyclothes.vo;

import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.dao.entity.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 19-04-2017.
 */
public class CatalogoProductos {
    private CatalogoEnum catalogoEnum;
    private TallaEnum tallaEnum;
    private Long totalProductos;
    private Long paginas;
    private Long paginaActual;
    private List<Producto> productoList = new ArrayList<>();

    public Long getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(Long paginaActual) {
        this.paginaActual = paginaActual;
    }

    public CatalogoEnum getCatalogoEnum() {
        return catalogoEnum;
    }

    public void setCatalogoEnum(CatalogoEnum catalogoEnum) {
        this.catalogoEnum = catalogoEnum;
    }

    public TallaEnum getTallaEnum() {
        return tallaEnum;
    }

    public void setTallaEnum(TallaEnum tallaEnum) {
        this.tallaEnum = tallaEnum;
    }

    public Long getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(Long totalProductos) {
        this.totalProductos = totalProductos;
    }

    public Long getPaginas() {
        return paginas;
    }

    public void setPaginas(Long paginas) {
        this.paginas = paginas;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }
}
