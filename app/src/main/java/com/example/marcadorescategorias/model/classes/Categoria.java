package com.example.marcadorescategorias.model.classes;

public class Categoria {
    private long id;
    private long idusuario;
    private String nombre;

    public Categoria(){

    }

    public Categoria(long id, long idusuario, String nombre) {
        this.id = id;
        this.idusuario = idusuario;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", idusuario=" + idusuario +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
