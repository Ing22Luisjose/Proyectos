package org.diplomado.java.jdbc;

import org.diplomado.java.jdbc.modelo.Categorias;
import org.diplomado.java.jdbc.modelo.Producto;
import org.diplomado.java.jdbc.repositorio.CategoriaRepositorioImpl;
import org.diplomado.java.jdbc.repositorio.ProductoRepositoriolmpl;
import org.diplomado.java.jdbc.repositorio.Repositorio;
import org.diplomado.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;


//import java.sql.*;

//conectado a conexion base de datos
public class ConexionBaseDatos2 {

    public static void main(String[] args) throws SQLException {

        try (Connection conn = ConexionBaseDatos.getConnection()) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                Repositorio<Categorias> categoriasRepositorio = new CategoriaRepositorioImpl(conn);
                System.out.println("========== insertar nuevo producto=============");
                Categorias categorias = new Categorias();
                categorias.setNombre("electrohogar");
                Categorias nuevaCategoria = categoriasRepositorio.guardar(categorias);
                System.out.println("Categoria guardada con exito: " + nuevaCategoria.getId());

                Repositorio<Producto> repositorio = new ProductoRepositoriolmpl(conn);
                System.out.println("=============== listar ==================");
                repositorio.listar().forEach(System.out::println);

                System.out.println("=============== obtener por id ==================");
                System.out.println(repositorio.porId(1L));

                System.out.println("=============== Insertar nuevos productos ==================");

                Producto producto = new Producto();
                producto.setNombre("refrigerador3");
                producto.setPrecio(900);
                producto.setFechaRegistro(new Date());
                producto.setSku("abcdefg");

                producto.setCategorias(nuevaCategoria);
                repositorio.guardar(producto);
                System.out.println("Producto guardado con exito");
                repositorio.listar().forEach(System.out::println);

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }


        }
    }

}