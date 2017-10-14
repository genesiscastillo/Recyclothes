package com.recyclothes.common.dto;

import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 * Created by Cesar on 18-07-2016.
 */
public class DetalleReservaDTO implements Serializable {

    private Long idDetalleReserva;
    private ReservaDTO reservaDTO;
    private ProductoDTO productoDTO;

    public DetalleReservaDTO(){
        super();
    }
    public DetalleReservaDTO(JSONObject jsonObject){
        this.setIdDetalleReserva((Long)jsonObject.get("idDetalleReserva"));
        this.setReservaDTO(new ReservaDTO((JSONObject) jsonObject.get("reserva")));
        this.setProductoDTO(new ProductoDTO((JSONObject) jsonObject.get("producto")));
    }

    public ReservaDTO getReservaDTO() {
        return reservaDTO;
    }

    public void setReservaDTO(ReservaDTO reservaDTO) {
        this.reservaDTO = reservaDTO;
    }

    public ProductoDTO getProductoDTO() {
        return productoDTO;
    }

    public void setProductoDTO(ProductoDTO productoDTO) {
        this.productoDTO = productoDTO;
    }

    public Long getIdDetalleReserva() {
        return idDetalleReserva;
    }

    public void setIdDetalleReserva(Long idDetalleReserva) {
        this.idDetalleReserva = idDetalleReserva;
    }


    @Override
    public String toString() {
        return "DetalleReservaDTO{" +
                "idDetalleReserva=" + idDetalleReserva +
                ", reservaDTO=" + reservaDTO+
                ", productoDTO=" + productoDTO +
                '}';
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idDetalleReserva" , idDetalleReserva);
        jsonObject.put("producto", productoDTO.toJSONObject());
        jsonObject.put("reserva", reservaDTO.toJSONObject());
        return jsonObject;
    }
}
