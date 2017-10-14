package com.recyclothes.fx.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.json.simple.JSONObject;

/**
 * Created by Cesar on 29-08-2016.
 */
public class UsuaroProperty {
    private StringProperty idSession;
    private StringProperty clienteProperty;
    private StringProperty fechaAccesoIn;
    private StringProperty fechaAccesoOut;

    public UsuaroProperty(JSONObject jsonObject){
        JSONObject jsonObjectCliente = (JSONObject) jsonObject.get("cliente");
        if( jsonObjectCliente != null )  {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((Long)jsonObjectCliente.get("id"));
            stringBuilder.append(" - ");
            stringBuilder.append((String)jsonObjectCliente.get("nombres"));
            stringBuilder.append(" - ");
            stringBuilder.append((String)jsonObjectCliente.get("apellidos"));
            stringBuilder.append(" - ");
            stringBuilder.append((String)jsonObjectCliente.get("correo"));
            setClienteProperty(stringBuilder.toString());
        }
        setIdSession((String)jsonObject.get("session"));
        setFechaAccesoIn((String)jsonObject.get("fechaAccesoIn"));
        setFechaAccesoOut((String)jsonObject.get("fechaAccesoOut"));
    }

    public String getIdSession() {
        return idSessionProperty().get();
    }

    public StringProperty idSessionProperty() {
        if(idSession == null){
            idSession = new SimpleStringProperty();
        }
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSessionProperty().set(idSession);
    }

    public String getClienteProperty() {
        return clientePropertyProperty().get();
    }

    public StringProperty clientePropertyProperty() {
        if(clienteProperty == null){
            clienteProperty = new SimpleStringProperty();
        }
        return clienteProperty;
    }

    public void setClienteProperty(String clienteProperty) {
        this.clientePropertyProperty().set(clienteProperty);
    }

    public String getFechaAccesoIn() {
        return fechaAccesoInProperty().get();
    }

    public StringProperty fechaAccesoInProperty() {
        if(fechaAccesoIn == null){
            fechaAccesoIn = new SimpleStringProperty();
        }
        return fechaAccesoIn;
    }

    public void setFechaAccesoIn(String fechaAccesoIn) {
        this.fechaAccesoInProperty().set(fechaAccesoIn);
    }

    public String getFechaAccesoOut() {
        return fechaAccesoOutProperty().get();
    }

    public StringProperty fechaAccesoOutProperty() {
        if(fechaAccesoOut == null ){
            fechaAccesoOut = new SimpleStringProperty();
        }
        return fechaAccesoOut;
    }

    public void setFechaAccesoOut(String fechaAccesoOut) {
        this.fechaAccesoOutProperty().set(fechaAccesoOut);
    }

    @Override
    public String toString() {
        return "UsuaroProperty{" +
                "idSession=" + idSession +
                ", clienteProperty=" + clienteProperty +
                ", fechaAccesoIn=" + fechaAccesoIn +
                ", fechaAccesoOut=" + fechaAccesoOut +
                '}';
    }
}
