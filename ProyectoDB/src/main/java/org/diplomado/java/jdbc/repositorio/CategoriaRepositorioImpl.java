package org.diplomado.java.jdbc.repositorio;

import org.diplomado.java.jdbc.models.Categoria;
import org.diplomado.java.jdbc.models.Producto;
import org.diplomado.java.jdbc.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImpl implements Repositorio<Categoria> {

    private Connection conn;

    public CategoriaRepositorioImpl(Connection conn) {
        this.conn = conn;
    }

    public CategoriaRepositorioImpl() {
    }
    private Categoria crearObjetoCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getString("nombre"));
        return c;
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet resl = stmt.executeQuery("Select * from categorias")) {
            while (resl.next()) {
                categorias.add(crearObjetoCategoria(resl));
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(int id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement("Select * from categorias c where c.id= ?")) {
            stmt.setInt(1, id);
            try (ResultSet resl = stmt.executeQuery()) {
                if (resl.next()) {
                    categoria = crearObjetoCategoria(resl);
                }
            }
        }
        return categoria;
    }

    @Override
    public Categoria guardar(Categoria categoria) throws SQLException {
        String sql;
        if (categoria != null && categoria.getId() != null && categoria.getId() > 0) {
            sql = "update categorias set nombre = ? where id = ?";
        } else {
            sql = "insert into categorias(nombre) values (?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categoria.getNombre());
            if (categoria != null && categoria.getId() != null && categoria.getId() > 0) {
                stmt.setInt(2, categoria.getId());
            }
            stmt.executeUpdate();
            if (categoria.getId() == null) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        categoria.setId(rs.getInt(1));
                    }
                }
            }
        }
        return categoria;
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("Delete from categorias where id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
