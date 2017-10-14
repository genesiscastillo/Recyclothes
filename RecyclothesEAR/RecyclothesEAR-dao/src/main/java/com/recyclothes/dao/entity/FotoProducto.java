package com.recyclothes.dao.entity;

import javax.persistence.*;

@Entity
@Table(name="tb_foto_producto")
public class FotoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_foto_producto")
    private Long idFotoProducto;

    @Column(name = "id_producto")
    private Long idProducto;

    @Lob
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Column(name = "foto")
    private byte[] foto;

    @PrePersist
    void onCreate() {
        setIdProducto(0L);
    }

    public byte[] getFoto() {
        return this.foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Long getIdFotoProducto() {
        return idFotoProducto;
    }

    public void setIdFotoProducto(Long idFotoProducto) {
        this.idFotoProducto = idFotoProducto;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "FotoProducto{" +
                "idFotoProducto=" + idFotoProducto +
                " , idProducto=" + idProducto +
                '}';
    }
}
