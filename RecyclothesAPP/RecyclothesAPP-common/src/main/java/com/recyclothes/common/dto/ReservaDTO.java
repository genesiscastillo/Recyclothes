package com.recyclothes.common.dto;

import com.recyclothes.common.enums.EstadoReservaEnum;
import com.recyclothes.common.enums.HoraEntregaEnum;
import com.recyclothes.common.utils.Utils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Cesar on 18-07-2016.
 */
public class ReservaDTO implements Serializable {

    private Long idReserva;
    private ClienteDTO cliente;
    private Date fechaReserva;
    private String comentarios;
    private EstadoReservaEnum estadoReservaEnum;
    private Date fechaEntrega;
    private String codigoReserva;
    private Integer montoTotal;
    private Integer montoPagar;
    private String datosDeEntrega;
    private HoraEntregaEnum horaEntregaEnum;
    private List<DetalleReservaDTO> detalleReservaDTOs = new ArrayList<DetalleReservaDTO>();

    public void addDetalleReservaDTO(DetalleReservaDTO detalleReservaDTO) {
        this.detalleReservaDTOs.add(detalleReservaDTO);
    }

    public List<DetalleReservaDTO> getDetalleReservaDTOs() {
        return detalleReservaDTOs;
    }

    public ReservaDTO(){
        super();
    }

    public ReservaDTO(JSONObject jsonObject){
        this.setIdReserva((Long)    jsonObject.get("idReserva"));
        this.setCliente( new ClienteDTO( (JSONObject)jsonObject.get("cliente")  ) );
        this.setFechaReserva(Utils.getDate((String)jsonObject.get("fechaReserva")));
        this.setComentarios((String) jsonObject.get("comentarios"));
        this.setEstadoReservaEnum(EstadoReservaEnum.valueOf((String)jsonObject.get("estadoReservaEnum")));
        this.setFechaEntrega(Utils.getDate((String)  jsonObject.get("fechaEntrega")));
        this.setCodigoReserva((String)jsonObject.get("codigoReserva"));
        this.setMontoTotal( Integer.valueOf(((Long)jsonObject.get("montoTotal")).intValue()));
        this.setMontoPagar(Integer.valueOf(((Long)jsonObject.get("montoPagar")).intValue()));
        this.setDatosDeEntrega((String) jsonObject.get("datosDeEntrega"));
        this.setHoraEntregaEnum( HoraEntregaEnum.valueOfString((String)jsonObject.get("horaEntregaEnum")));
        try {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse((String) jsonObject.get("detalleReservas"));
            for(int i = 0 ; i < jsonArray.size() ; i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                DetalleReservaDTO detalleReservaDTO = new DetalleReservaDTO(jsonObject1);
                this.addDetalleReservaDTO(detalleReservaDTO);
            }
        }catch(Exception e){
            
        }
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public EstadoReservaEnum getEstadoReservaEnum() {
        return estadoReservaEnum;
    }

    public void setEstadoReservaEnum(EstadoReservaEnum estadoReservaEnum) {
        this.estadoReservaEnum = estadoReservaEnum;
    }

    public HoraEntregaEnum getHoraEntregaEnum() {
        return horaEntregaEnum;
    }

    public void setHoraEntregaEnum(HoraEntregaEnum horaEntregaEnum) {
        this.horaEntregaEnum = horaEntregaEnum;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(Integer montoPagar) {
        this.montoPagar = montoPagar;
    }

    public String getDatosDeEntrega() {
        return datosDeEntrega;
    }

    public void setDatosDeEntrega(String datosDeEntrega) {
        this.datosDeEntrega = datosDeEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservaDTO that = (ReservaDTO) o;

        return !(idReserva != null ? !idReserva.equals(that.idReserva) : that.idReserva != null);

    }

    @Override
    public int hashCode() {
        return idReserva != null ? idReserva.hashCode() : 0;
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idReserva",idReserva);
        jsonObject.put("nombres", String.valueOf("#" + cliente.getId() + "-" + cliente.getNombres() + " " + cliente.getApellidos()));
        jsonObject.put("cliente", cliente.toJSONObject());
        jsonObject.put("fechaReserva", Utils.getFecha(fechaReserva));
        jsonObject.put("comentarios",comentarios);
        jsonObject.put("estadoReservaEnum",estadoReservaEnum.name());
        jsonObject.put("fechaEntrega",Utils.getFecha(fechaEntrega));
        jsonObject.put("codigoReserva",codigoReserva);
        jsonObject.put("montoTotal",montoTotal);
        jsonObject.put("montoPagar",montoPagar);
        jsonObject.put("datosDeEntrega",datosDeEntrega);
        jsonObject.put("horaEntregaEnum", horaEntregaEnum == null ? null : horaEntregaEnum.toString());
        return jsonObject;
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "idReserva=" + idReserva +
                ", cliente=" + cliente +
                ", fechaReserva=" + fechaReserva +
                ", comentarios='" + comentarios + '\'' +
                ", estadoReservaEnum=" + estadoReservaEnum +
                ", fechaEntrega=" + fechaEntrega +
                ", codigoReserva='" + codigoReserva + '\'' +
                ", montoTotal=" + montoTotal +
                ", montoPagar=" + montoPagar +
                ", datosDeEntrega='" + datosDeEntrega + '\'' +
                ", horaEntregaEnum='" + horaEntregaEnum+ '\'' +
                '}';
    }
}
