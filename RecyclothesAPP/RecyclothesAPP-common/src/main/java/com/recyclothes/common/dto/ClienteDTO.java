package com.recyclothes.common.dto;

import com.recyclothes.common.utils.Utils;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cesar on 18-07-2016.
 */
public class ClienteDTO implements Serializable {

    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String token;
    private String password;
    private Date fechaRegistroCreacion;
    private Date fechaUltimaVisita;

    public ClienteDTO(){
        super();
    }
    public ClienteDTO(JSONObject jsonObject){
        this.setId((Long)jsonObject.get("id"));
        this.setNombres((String)jsonObject.get("nombres"));
        this.setApellidos((String)jsonObject.get("apellidos"));
        this.setCorreo((String)jsonObject.get("correo"));
        this.setTelefono((String)jsonObject.get("telefono"));
        this.setToken((String)jsonObject.get("token"));
        this.setFechaRegistroCreacion( Utils.getDate((String)jsonObject.get("fechaRegistroCreacion")));
        this.setFechaUltimaVisita(Utils.getDate((String)jsonObject.get("fechaUltimaVisita")));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaRegistroCreacion() {
        return fechaRegistroCreacion;
    }

    public void setFechaRegistroCreacion(Date fechaRegistroCreacion) {
        this.fechaRegistroCreacion = fechaRegistroCreacion;
    }

    public Date getFechaUltimaVisita() {
        return fechaUltimaVisita;
    }

    public void setFechaUltimaVisita(Date fechaUltimaVisita) {
        this.fechaUltimaVisita = fechaUltimaVisita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteDTO that = (ClienteDTO) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("nombres",nombres);
        jsonObject.put("apellidos",apellidos);
        jsonObject.put("correo",correo);
        jsonObject.put("telefono",telefono);
        jsonObject.put("token",token);
        jsonObject.put("fechaRegistroCreacion", Utils.getFecha(fechaRegistroCreacion));
        jsonObject.put("fechaUltimaVisita",Utils.getFecha(fechaUltimaVisita));
        return jsonObject;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", token='" + token + '\'' +
                ", fechaRegistroCreacion=" + fechaRegistroCreacion +
                ", fechaUltimaVisita=" + fechaUltimaVisita +
                '}';
    }
}
