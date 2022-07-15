package org.proyecto.delcarrito.webapp.modeladodeproductos;

public class Industria {

    private Integer id;
    private String nombre;
    private String detalle;

    public Industria() {
    }

    public Industria(Integer id, String nombre, String detalle) {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Industria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", detalle='" + detalle + '\'' +
                '}';
    }
}
