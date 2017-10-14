package com.recyclothes.common.dto;

import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 * Created by Cesar on 18-07-2016.
 */
public class FotoProductoDTO implements Serializable {

    private Long idFotoProducto;
    private Long idProducto;
    private byte[] foto;

    public FotoProductoDTO(){
        super();
    }
    public FotoProductoDTO(JSONObject jsonObject)   {
        this.setIdFotoProducto((Long)jsonObject.get("idFotoProducto"));
        this.setIdProducto((Long)jsonObject.get("idProducto"));
    }
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdFotoProducto() {
        return idFotoProducto;
    }

    public void setIdFotoProducto(Long idFotoProducto) {
        this.idFotoProducto = idFotoProducto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FotoProductoDTO that = (FotoProductoDTO) o;

        return !(idFotoProducto != null ? !idFotoProducto.equals(that.idFotoProducto) : that.idFotoProducto != null);

    }

    @Override
    public int hashCode() {
        return idFotoProducto != null ? idFotoProducto.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FotoProductoDTO{" +
                "idFotoProducto=" + idFotoProducto +
                ", idProducto=" + idProducto +
                '}';
    }
    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idFotoProducto" , idFotoProducto);
        jsonObject.put("idProducto" , idProducto);
        return jsonObject;
    }
}
