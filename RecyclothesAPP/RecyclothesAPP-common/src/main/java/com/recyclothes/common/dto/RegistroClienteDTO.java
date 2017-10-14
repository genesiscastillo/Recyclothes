package com.recyclothes.common.dto;

import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 * Created by Cesar on 23-04-2016.
 */
public class RegistroClienteDTO implements Serializable {

    private ClienteDTO cliente;
    private Boolean isNuevo = Boolean.FALSE;

    public RegistroClienteDTO() {
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public Boolean isNuevo() {
        return isNuevo;
    }

    public void setIsNuevo(Boolean isNuevo) {
        this.isNuevo = isNuevo;
    }

    public JSONObject toJSONObject()    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cliente", cliente.toJSONObject());
        jsonObject.put("isNuevo", isNuevo);
        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistroClienteDTO that = (RegistroClienteDTO) o;

        if (cliente != null ? !cliente.equals(that.cliente) : that.cliente != null) return false;
        return !(isNuevo != null ? !isNuevo.equals(that.isNuevo) : that.isNuevo != null);

    }

    @Override
    public int hashCode() {
        int result = cliente != null ? cliente.hashCode() : 0;
        result = 31 * result + (isNuevo != null ? isNuevo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegistroClienteDTO{" +
                "cliente=" + cliente +
                ", isNuevo=" + isNuevo +
                '}';
    }
}
