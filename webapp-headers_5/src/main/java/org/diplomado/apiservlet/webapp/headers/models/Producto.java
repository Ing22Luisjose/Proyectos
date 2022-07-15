package org.diplomado.apiservlet.webapp.headers.models;

public class Producto {


private long id;

private String nombre;

private String tipo;

private  int precio;

public Producto() {
}

    public Producto (long id, String nombre, String tipo, int precio) {

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

        public long getId() {
        return id;
    }

    public Producto setId(long id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Producto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Producto setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public int getPrecio() {
        return precio;
    }

    public Producto setPrecio(int precio) {
        this.precio = precio;
        return this;
    }

}
