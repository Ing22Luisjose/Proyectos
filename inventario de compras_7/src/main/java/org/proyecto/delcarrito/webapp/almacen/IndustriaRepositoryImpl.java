package org.proyecto.delcarrito.webapp.almacen;

import org.proyecto.delcarrito.webapp.modeladodeproductos.Industria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndustriaRepositoryImpl implements CrudRepository<Industria> {

    private Connection conn;

    public IndustriaRepositoryImpl() {
    }

    public IndustriaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Industria> listar() throws SQLException {
        List<Industria> industrias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select * from proveedor order by id ASC")) {
            while (rs.next()) {
                Industria industria= getIndustria(rs);
                industrias.add(industria);
            }
        }
        return industrias;
    }

    @Override
    public Industria porId(Long id) throws SQLException {
        Industria industria = null;
        try (PreparedStatement stmt = conn.prepareStatement("Select * from industria WHERE id = 1")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    industria = getIndustria(rs);
                }
            }
        }
        return industria;
    }

    @Override
    public void guardar(Industria industria) throws SQLException {
        String sql;
        if (industria.getId() != null && industria.getId() > 0) {
            sql = "update industria set nombre=?, detalle=? where id=?";
        } else {
            sql = "insert into industria (nombre, detalle) values (?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, industria.getNombre());
            stmt.setString(2, industria.getDetalle());
            if (industria.getId() != null && industria.getId() > 0) {
                stmt.setInt(3, industria.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from industria where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Industria getIndustria(ResultSet rs) throws SQLException {
        Industria industria = new Industria();
        industria.setId(rs.getInt("id"));
        industria.setNombre(rs.getString("nombre"));
        industria.setDetalle(rs.getString("detalle"));
        return industria;
    }

}
