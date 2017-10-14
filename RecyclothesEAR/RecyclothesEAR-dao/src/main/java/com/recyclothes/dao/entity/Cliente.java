package com.recyclothes.dao.entity;

import javax.persistence.*;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "password")
    private String password;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "token")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaRegistroCreacion")
    private Date fechaRegistroCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaUltimaVisita")
    private Date fechaUltimaVisita;

    @PrePersist
    void onCreate() {
        this.setToken(  generateToken() );
        this.setFechaRegistroCreacion(Calendar.getInstance().getTime());
    }

    protected String generateToken() {
        StringBuffer hexString = new StringBuffer();
        try {
            String datatoken = this.toString();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(datatoken.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
        }
        return hexString.append(""+Calendar.getInstance().getTimeInMillis()+Calendar.getInstance().getTimeInMillis()).toString();
    }

    public Date getFechaRegistroCreacion() {
        return fechaRegistroCreacion;
    }

    public void setFechaRegistroCreacion(Date fechaRegistroCreacion) {
        this.fechaRegistroCreacion = fechaRegistroCreacion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaUltimaVisita() {
        return fechaUltimaVisita;
    }

    public void setFechaUltimaVisita(Date fechaUltimaVisita) {
        this.fechaUltimaVisita = fechaUltimaVisita;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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



    @Override
    public String toString() {
        return "Cliente{" +
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
