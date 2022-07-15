package org.diplomado.java.jdbc.repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio <T>{

    void setConn(Connection conn);
    List<T> listar() throws SQLException;
    T porId (int id) throws SQLException;
    T guardar(T t) throws SQLException;
    void eliminar (int id) throws SQLException;

}
