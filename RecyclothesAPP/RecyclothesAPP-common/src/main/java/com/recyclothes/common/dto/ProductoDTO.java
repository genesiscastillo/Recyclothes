package com.recyclothes.common.dto;

import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.common.utils.Utils;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cesar on 18-07-2016.
 */
public class ProductoDTO implements Serializable {

    private Long idProducto;
    private String descripcion;
    private EstadoProductoEnum estadoProductoEnum;
    private Date fecIngreso;
    private CatalogoEnum catalogoEnum;
    private TallaEnum tallaEnum;
    private Long precioProducto;
    private Long idFotoProducto;

    public ProductoDTO(){
        super();
    }
    public ProductoDTO(JSONObject jsonObject){
        this.setIdProducto((Long)jsonObject.get("idProducto"));
        this.setDescripcion((String)jsonObject.get("descripcion"));
        this.setEstadoProductoEnum(EstadoProductoEnum.valueOf((String)jsonObject.get("estadoProductoEnum")));
        this.setFecIngreso( Utils.getDate((String)jsonObject.get("fecIngreso")));
        this.setCatalogoEnum(CatalogoEnum.valueOf((String)jsonObject.get("catalogo")));
        this.setTallaEnum(TallaEnum.valueOf((String)jsonObject.get("talla")));
        this.setPrecioProducto((Long)jsonObject.get("precioProducto"));
        this.setIdFotoProducto((Long)jsonObject.get("idFotoProducto"));
    }
    public Long getIdFotoProducto() {
        return idFotoProducto;
    }

    public void setIdFotoProducto(Long idFotoProducto) {
        this.idFotoProducto = idFotoProducto;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoProductoEnum getEstadoProductoEnum() {
        return estadoProductoEnum;
    }

    public void setEstadoProductoEnum(EstadoProductoEnum estadoProductoEnum) {
        this.estadoProductoEnum = estadoProductoEnum;
    }

    public Date getFecIngreso() {
        return fecIngreso;
    }

    public void setFecIngreso(Date fecIngreso) {
        this.fecIngreso = fecIngreso;
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

    public Long getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Long precioProducto) {
        this.precioProducto = precioProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductoDTO productoDTO = (ProductoDTO) o;

        return !(idProducto != null ? !idProducto.equals(productoDTO.idProducto) : productoDTO.idProducto != null);

    }

    @Override
    public int hashCode() {
        return idProducto != null ? idProducto.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "idProducto=" + idProducto +
                ", descripcion='" + descripcion + '\'' +
                ", estadoProductoEnum=" + estadoProductoEnum.name() +
                ", fecIngreso=" + Utils.getFecha( fecIngreso )+
                ", catalogoEnum=" + catalogoEnum.name() +
                ", tallaEnum=" + tallaEnum.name() +
                ", precioProducto=" + precioProducto +
                ", idFotoProducto=" + idFotoProducto +
            //    ", totalFotoProductos =" + fotoProductoDTOs.size() +
                '}';
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idProducto",idProducto );
        jsonObject.put("descripcion",descripcion);
        jsonObject.put("estadoProductoEnum",estadoProductoEnum.name());
        jsonObject.put("fecIngreso",Utils.getFecha(fecIngreso));
        jsonObject.put("catalogoEnum", catalogoEnum.toJSON());
        jsonObject.put("catalogo", catalogoEnum.name());
        jsonObject.put("tallaEnum",tallaEnum.toJSON());
        jsonObject.put("talla",tallaEnum.name());
        jsonObject.put("precioProducto",precioProducto );
        jsonObject.put("idFotoProducto",idFotoProducto );
        return jsonObject;
    }
}
