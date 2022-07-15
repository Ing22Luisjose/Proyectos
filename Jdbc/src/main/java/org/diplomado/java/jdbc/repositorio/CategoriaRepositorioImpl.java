package org.diplomado.java.jdbc.repositorio;

import org.diplomado.java.jdbc.modelo.Categorias;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImpl implements Repositorio<Categorias> {

    private Connection conn ;
    public CategoriaRepositorioImpl(Connection conn) {
        this.conn = conn;
    }

    private Categorias CrearObjetoCategoria(ResultSet rs) throws SQLException{
        Categorias c = new Categorias();
        c.setId(rs.getLong("idCategorias"));
        c.setNombre(rs.getString("nombrecat"));
        return c;
    }

    @Override
    public List<Categorias> listar() throws SQLException {
        List<Categorias> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM categorias")) {
            while (rs.next()) {
                categorias.add(CrearObjetoCategoria(rs));
            }
        }
        return categorias;
    }

    @Override
    public Categorias porId(Long id) throws SQLException {
        Categorias categorias = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias as c WHERE c.idcategorias")) {
            stmt.setLong(1,id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()){
                    categorias = CrearObjetoCategoria(rs);
                }
            }
        }
        return categorias;
    }

    @Override
    public Categorias guardar(Categorias categorias) throws SQLException {

        String sql = null;
        if (categorias.getId() != null && categorias.getId() > 0) {
            sql = "UPDATE categorias SET nombrecat=? WHERE idcategorias=?";
        } else {
            sql = "INSERT INTO categorias(nombrecat) VALUES(?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) ){
            stmt.setString(1, categorias.getNombre());
            if (categorias.getId() != null && categorias.getId() > 0) {
                stmt.setLong(2, categorias.getId());
            }
            stmt.executeUpdate();
            if (categorias.getId()== null){
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()){
                        categorias.setId(rs.getLong(1));
                    }
                }
            }

        }
        return categorias;
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM categorias WHERE id =?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}


