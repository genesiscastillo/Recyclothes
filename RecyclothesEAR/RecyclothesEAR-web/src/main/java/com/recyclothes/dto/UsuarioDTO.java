package com.recyclothes.dto;

import com.recyclothes.common.dto.ClienteDTO;
import com.recyclothes.common.utils.Utils;
import org.json.simple.JSONObject;

import javax.websocket.Session;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cesar on 20-04-2016.
 */
public class UsuarioDTO implements Serializable {

    private ClienteDTO cliente;
    private Session session;
    private Date fechaAccesoIn;
    private Date fechaAccesoOut;

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Date getFechaAccesoIn() {
        return fechaAccesoIn;
    }

    public void setFechaAccesoIn(Date fechaAccesoIn) {
        this.fechaAccesoIn = fechaAccesoIn;
    }

    public Date getFechaAccesoOut() {
        return fechaAccesoOut;
    }

    public void setFechaAccesoOut(Date fechaAccesoOut) {
        this.fechaAccesoOut = fechaAccesoOut;
    }

    public JSONObject toJSONObject()    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("session" , session.getId());
        jsonObject.put("cliente" , cliente == null ? org.codehaus.jettison.json.JSONObject.NULL :  cliente.toJSONObject());
        jsonObject.put("fechaAccesoIn" , Utils.getFecha(fechaAccesoIn));
        jsonObject.put("fechaAccesoOut" , fechaAccesoOut == null ? "" : Utils.getFecha(fechaAccesoOut));
        return jsonObject;

    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "cliente=" + cliente +
                ", session=" + session +
                ", fechaAccesoIn=" + fechaAccesoIn +
                ", fechaAccesoOut=" + fechaAccesoOut +
                '}';
    }
}
