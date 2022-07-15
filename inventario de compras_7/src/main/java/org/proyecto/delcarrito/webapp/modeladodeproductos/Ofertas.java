package org.proyecto.delcarrito.webapp.modeladodeproductos;

public class Ofertas {

    private Integer id;
    private Proveedor proveedor;
    private Solicitud solicitud;
    private Integer adjudicado;
    private Long precio;

    public Ofertas() {
    }

    public Ofertas(Integer id) {
        this.id = id;
    }

    public Ofertas(Integer id, Proveedor proveedor, Solicitud solicitud, Integer adjudicado, Long precio) {
        this.id = id;
        this.proveedor = proveedor;
        this.solicitud = solicitud;
        this.adjudicado = adjudicado;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Integer getAdjudicado() {
        return adjudicado;
    }

    public void setAdjudicado(Integer adjudicado) {
        this.adjudicado = adjudicado;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Ofertas{" +
                "id=" + id +
                ", proveedor=" + proveedor +
                ", solicitud=" + solicitud +
                ", adjudicado=" + adjudicado +
                ", precio=" + precio +
                '}';
    }
}
