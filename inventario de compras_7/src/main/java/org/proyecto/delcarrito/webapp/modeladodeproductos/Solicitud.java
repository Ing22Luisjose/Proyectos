package org.proyecto.delcarrito.webapp.modeladodeproductos;

import java.util.Date;

public class


Solicitud {

    private Integer id;
    private Industria industria;
    private Producto producto;
    private Date fechaFinal;
    private Integer cantidad;

    public Solicitud() {
    }

    public Solicitud(Integer id, Industria industria, Producto producto, Date fechaFinal, Integer cantidad) {
        this.id = id;
        this.industria = industria;
        this.producto = producto;
        this.fechaFinal = fechaFinal;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Industria getIndustria() {
        return industria;
    }

    public void setIndustria(Industria industria) {
        this.industria = industria;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", industria=" + industria +
                ", producto=" + producto +
                ", fechaFinal=" + fechaFinal +
                ", cantidad=" + cantidad +
                '}';
    }
}
