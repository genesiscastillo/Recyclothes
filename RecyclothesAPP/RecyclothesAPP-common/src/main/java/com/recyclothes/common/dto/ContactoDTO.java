package com.recyclothes.common.dto;

import java.io.Serializable;

/**
 * Created by Cesar on 03-06-2016.
 */
public class ContactoDTO  implements Serializable {

    private String nombre;
    private String correo;
    private String asunto;
    private String mensaje;

    public ContactoDTO(String nombre, String correo, String asunto, String mensaje) {
        this.nombre = nombre;
        this.correo = correo;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactoDTO that = (ContactoDTO) o;

        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (correo != null ? !correo.equals(that.correo) : that.correo != null) return false;
        if (asunto != null ? !asunto.equals(that.asunto) : that.asunto != null) return false;
        return !(mensaje != null ? !mensaje.equals(that.mensaje) : that.mensaje != null);

    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (asunto != null ? asunto.hashCode() : 0);
        result = 31 * result + (mensaje != null ? mensaje.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactoDTO{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", asunto='" + asunto + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
