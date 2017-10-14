package com.recyclothes.dao;

/**
 * Created by Cesar on 10-09-2016.
 */
public class TablaDTO {

    private Long id;
    private String key;
    private Integer nivel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "TablaDTO{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", nivel=" + nivel +
                '}';
    }
}
