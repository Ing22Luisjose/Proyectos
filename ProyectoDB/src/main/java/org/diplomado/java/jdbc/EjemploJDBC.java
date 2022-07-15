package org.diplomado.java.jdbc;

import org.diplomado.java.jdbc.models.Categoria;
import org.diplomado.java.jdbc.models.Producto;
import org.diplomado.java.jdbc.servicio.CatalogoServicio;
import org.diplomado.java.jdbc.servicio.Servicio;

import java.sql.*;
import java.util.Date;

public class EjemploJDBC {
    public static void main(String[] args) throws SQLException {
        Servicio servicio = new CatalogoServicio();
        System.out.println("----------------Listar---------------");
        servicio.listar().forEach(System.out::println);
        Categoria categoria = new Categoria();
        categoria.setNombre("Iluminción");
        Producto producto = new Producto();
        producto.setNombre("Lampara de escritorio");
        producto.setPrecio(900);
        producto.setFechaRegistro(new Date());
        producto.setSku("asdasd14");
        servicio.guardarProductoConCategoria(producto,categoria);
        System.out.println("Producto guardado " + producto.getId());
        servicio.listar().forEach(System.out::println);
    }
}
