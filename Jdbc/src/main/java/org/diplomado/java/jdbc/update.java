package org.diplomado.java.jdbc;

import org.diplomado.java.jdbc.modelo.Categorias;
import org.diplomado.java.jdbc.modelo.Producto;
import org.diplomado.java.jdbc.repositorio.ProductoRepositoriolmpl;
import org.diplomado.java.jdbc.repositorio.Repositorio;
import org.diplomado.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class update {

    public static void main(String[] arg) throws SQLException
    {

        /*try (Connection conn = ConexionBaseDatos.getConnection())
        {

            Repositorio<Producto> repositorio = new ProductoRepositoriolmpl();

            System.out.println("======================= listar =========================");
            repositorio.listar().forEach(System.out::println);

            System.out.println("======================= obtener por id =========================");
            System.out.println(repositorio.porId(1L));

            System.out.println("======================= editar producto=========================");
            Producto producto = new Producto();
            producto.setId(5L);
            producto.setNombre("cambio1intellij");
            producto.setPrecio(500);
            Categorias categorias = new Categorias();
            categorias.setId(2L);
            producto.setCategorias(categorias);
            repositorio.guardar(producto);
            System.out.println("Producto editado con exito");
            repositorio.listar().forEach(System.out::println);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }*/
    }


}
