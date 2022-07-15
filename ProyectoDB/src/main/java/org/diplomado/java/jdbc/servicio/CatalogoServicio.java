package org.diplomado.java.jdbc.servicio;

import org.diplomado.java.jdbc.models.Categoria;
import org.diplomado.java.jdbc.models.Producto;
import org.diplomado.java.jdbc.repositorio.CategoriaRepositorioImpl;
import org.diplomado.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.diplomado.java.jdbc.repositorio.Repositorio;
import org.diplomado.java.jdbc.util.ConexionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CatalogoServicio implements Servicio{

    private Repositorio<Producto> productoRepositorio;
    private Repositorio<Categoria> categoriaRepositorio;

    public CatalogoServicio() {
        this.productoRepositorio = new ProductoRepositorioImpl();
        this.categoriaRepositorio = new CategoriaRepositorioImpl();
    }

    @Override
    public List<Producto> listar() throws SQLException {
        try(Connection conn = ConexionDB.getconnection()){
            productoRepositorio.setConn(conn);
            return  productoRepositorio.listar();
        }
    }

    @Override
    public Producto proId(int id) throws SQLException {
        try(Connection conn = ConexionDB.getconnection()){
            productoRepositorio.setConn(conn);
            return  productoRepositorio.porId(id);
        }
    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        try(Connection conn = ConexionDB.getconnection()){
            productoRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            Producto nuevoProducto = null;
            try {
                nuevoProducto = productoRepositorio.guardar(producto);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
            return nuevoProducto;
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try(Connection conn = ConexionDB.getconnection()){
            productoRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                productoRepositorio.eliminar(id);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Categoria> listarCategoria() throws SQLException {
        try(Connection conn = ConexionDB.getconnection()){
            categoriaRepositorio.setConn(conn);
            return categoriaRepositorio.listar();
        }
    }

    @Override
    public Categoria proIdCategoria(int id) throws SQLException {
        try(Connection conn = ConexionDB.getconnection()){
            categoriaRepositorio.setConn(conn);
            return  categoriaRepositorio.porId(id);
        }
    }

    @Override
    public Categoria guardar(Categoria categoria) throws SQLException {
        try(Connection conn = ConexionDB.getconnection()){
            categoriaRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            Categoria nuevoCategoria = null;
            try {
                nuevoCategoria = categoriaRepositorio.guardar(categoria);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
            return nuevoCategoria;
        }
    }

    @Override
    public void eliminarCategoria(int id) throws SQLException {
        try(Connection conn = ConexionDB.getconnection()){
            categoriaRepositorio.setConn(conn);
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                categoriaRepositorio.eliminar(id);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void guardarProductoConCategoria(Producto producto, Categoria categoria) throws SQLException {
        try(Connection conn = ConexionDB.getconnection()){
            productoRepositorio.setConn(conn);
            categoriaRepositorio.setConn(conn);

            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try {
                Categoria nuevoCategoria = categoriaRepositorio.guardar(categoria);
                producto.setCategoria(nuevoCategoria);
                productoRepositorio.guardar(producto);
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                e.printStackTrace();
            }
        }
    }
}
