package org.proyecto.delcarrito.webapp.almacen;

import org.proyecto.delcarrito.webapp.modeladodeproductos.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryImpl implements CrudRepository<Categoria> {

    private Connection conn;

    public CategoriaRepositoryImpl() {
    }

    public CategoriaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select * from categoria order by id ASC")) {
            while (rs.next()) {
                Categoria categoria= getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement("Select * from categoria WHERE id = 1")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;
        if (categoria.getId() != null && categoria.getId() > 0) {
            sql = "update categoria set nombre=? where id=?";
        } else {
            sql = "insert into categoria (nombre) values (?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            if (categoria.getId() != null && categoria.getId() > 0) {
                stmt.setInt(3, categoria.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from categoria where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("id"));
        categoria.setNombre(rs.getString("nombre"));
        return categoria;
    }
}
