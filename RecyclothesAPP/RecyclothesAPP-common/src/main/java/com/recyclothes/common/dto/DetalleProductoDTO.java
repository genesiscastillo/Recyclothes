package com.recyclothes.common.dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 23-04-2016.
 */
public class DetalleProductoDTO implements Serializable {

    private ProductoDTO productoDTO;
    private Boolean isPrimero = Boolean.FALSE;
    private Boolean isUltimo = Boolean.FALSE;
    private Long idProductoAnterior = 0L;
    private Long idProductoSiguiente= 0L;
    private List<FotoProductoDTO> fotoProductoDTOs = new ArrayList<FotoProductoDTO>();

    public void addFotoProductoDTO(FotoProductoDTO fotoProductoDTO){
        this.fotoProductoDTOs.add(fotoProductoDTO);
    }
    public List<FotoProductoDTO> getFotoProductoDTOs() {
        return fotoProductoDTOs;
    }

    public void setFotoProductoDTOs(List<FotoProductoDTO> fotoProductoDTOs) {
        this.fotoProductoDTOs = fotoProductoDTOs;
    }

    public ProductoDTO getProductoDTO() {
        return productoDTO;
    }

    public void setProductoDTO(ProductoDTO productoDTO) {
        this.productoDTO = productoDTO;
    }

    public Boolean getIsPrimero() {
        return isPrimero;
    }

    public void setIsPrimero(Boolean isPrimero) {
        this.isPrimero = isPrimero;
    }

    public Boolean getIsUltimo() {
        return isUltimo;
    }

    public void setIsUltimo(Boolean isUltimo) {
        this.isUltimo = isUltimo;
    }

    public Long getIdProductoAnterior() {
        return idProductoAnterior;
    }

    public void setIdProductoAnterior(Long idProductoAnterior) {
        this.idProductoAnterior = idProductoAnterior;
    }

    public Long getIdProductoSiguiente() {
        return idProductoSiguiente;
    }

    public void setIdProductoSiguiente(Long idProductoSiguiente) {
        this.idProductoSiguiente = idProductoSiguiente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetalleProductoDTO that = (DetalleProductoDTO) o;

        if (productoDTO != null ? !productoDTO.equals(that.productoDTO) : that.productoDTO != null) return false;
        if (isPrimero != null ? !isPrimero.equals(that.isPrimero) : that.isPrimero != null) return false;
        if (isUltimo != null ? !isUltimo.equals(that.isUltimo) : that.isUltimo != null) return false;
        if (idProductoAnterior != null ? !idProductoAnterior.equals(that.idProductoAnterior) : that.idProductoAnterior != null)
            return false;
        return !(idProductoSiguiente != null ? !idProductoSiguiente.equals(that.idProductoSiguiente) : that.idProductoSiguiente != null);

    }

    @Override
    public int hashCode() {
        int result = productoDTO != null ? productoDTO.hashCode() : 0;
        result = 31 * result + (isPrimero != null ? isPrimero.hashCode() : 0);
        result = 31 * result + (isUltimo != null ? isUltimo.hashCode() : 0);
        result = 31 * result + (idProductoAnterior != null ? idProductoAnterior.hashCode() : 0);
        result = 31 * result + (idProductoSiguiente != null ? idProductoSiguiente.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DetalleProductoDTO{" +
                "productoDTO=" + productoDTO +
                ", isPrimero=" + isPrimero +
                ", isUltimo=" + isUltimo +
                ", idProductoAnterior=" + idProductoAnterior +
                ", idProductoSiguiente=" + idProductoSiguiente +
                ", fotoProductoDTOs=" + fotoProductoDTOs +
                '}';
    }

    public JSONObject toJSONObject()    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("productoDTO", productoDTO.toJSONObject());
        jsonObject.put("isPrimero", isPrimero);
        jsonObject.put("isUltimo", isUltimo);
        jsonObject.put("idProductoAnterior", idProductoAnterior);
        jsonObject.put("idProductoSiguiente", idProductoSiguiente);
        JSONArray jsonArray = new JSONArray();
        for( FotoProductoDTO fotoProductoDTO : fotoProductoDTOs){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("idFotoProducto" , fotoProductoDTO.getIdFotoProducto());
            jsonArray.add(jsonObject1);
        }
        jsonObject.put("fotoProductoDTOs" , jsonArray.toJSONString());
        return jsonObject;
    }

}
