package org.diplomado.java.jdbc.servicio;

import org.diplomado.java.jdbc.models.Categoria;
import org.diplomado.java.jdbc.models.Producto;

import java.sql.SQLException;
import java.util.List;

public interface Servicio {

    List<Producto> listar() throws SQLException;
    Producto proId(int id) throws SQLException;
    Producto guardar(Producto producto) throws SQLException;
    void eliminar(int id) throws SQLException;
    List<Categoria> listarCategoria() throws SQLException;
    Categoria proIdCategoria(int id) throws SQLException;
    Categoria guardar(Categoria categoria) throws SQLException;
    void eliminarCategoria(int id) throws SQLException;
    void guardarProductoConCategoria(Producto producto,Categoria categoria) throws SQLException;
}
