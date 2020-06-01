package com.example.marcadorescategorias.model.classes;

public class Marcadores {
    private long id;
    private long idusuario;
    private long idcategoria;
    private String url;
    private String descripcion;

    public Marcadores(){

    }

    public Marcadores(long id, long idusuario, long idcategoria, String url, String descripcion) {
        this.id = id;
        this.idusuario = idusuario;
        this.idcategoria = idcategoria;
        this.url = url;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    public long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Marcadores{" +
                "id=" + id +
                ", idusuario=" + idusuario +
                ", idcategoria=" + idcategoria +
                ", url='" + url + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
