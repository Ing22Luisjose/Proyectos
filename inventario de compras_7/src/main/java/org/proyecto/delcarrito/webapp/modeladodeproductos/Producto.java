package org.proyecto.delcarrito.webapp.modeladodeproductos;

public class Producto {

    private Integer id;
    private Categoria categoria;
    private String nombre;
    private String descripcion;
    private Integer cantidad;

    public Producto() {
    }

    public Producto(Integer id, Categoria categoria, String nombre, String descripcion, Integer cabtidad) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cabtidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", categoria=" + categoria +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cabtidad=" + cantidad +
                '}';
    }
}
