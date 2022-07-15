package org.diplomado.java.jdbc.modelo;


import java.util.Date;

public class Producto {

//atributos .1
    private Long id;
    private String nombre;
    private Integer precio;
    private Date fechaRegistro;
    private Categorias categorias;

    private String sku;


    public Producto(){ //producto .2

    }
//constructor .3

    public Producto(Long id, String nombre, Integer precio, Date fechaRegistro, String sku) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
        this.sku = sku;
    }

    //metodos get y set .4


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override //sobre escribir

    public String toString(){
        return id +
                "|" +
                nombre +
                "|" +
                precio +
                "|" +
                fechaRegistro +
                "|" +
                categorias.getNombre() +
                "|" +
                categorias.getId() +
                "|" +
                sku;
   }
}
