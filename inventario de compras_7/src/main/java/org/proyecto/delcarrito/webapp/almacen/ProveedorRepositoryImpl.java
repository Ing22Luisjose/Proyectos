package org.proyecto.delcarrito.webapp.almacen;

import org.proyecto.delcarrito.webapp.modeladodeproductos.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorRepositoryImpl implements CrudRepository<Proveedor>{

    private Connection conn;

    public ProveedorRepositoryImpl() {
    }

    public ProveedorRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Proveedor> listar() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select * from proveedor order by id ASC")) {
            while (rs.next()) {
                Proveedor proveedor= getProveedor(rs);
                proveedores.add(proveedor);
            }
        }
        return proveedores;
    }

    @Override
    public Proveedor porId(Long id) throws SQLException {
        Proveedor proveedor = null;
        try (PreparedStatement stmt = conn.prepareStatement("Select * from proveedor WHERE id = 1")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    proveedor = getProveedor(rs);
                }
            }
        }
        return proveedor;
    }

    @Override
    public void guardar(Proveedor proveedor) throws SQLException {
        String sql;
        if (proveedor.getId() != null && proveedor.getId() > 0) {
            sql = "update proveedor set nombre=?, descripcion=? where id=?";
        } else {
            sql = "insert into proveedor (nombre, descripcion) values (?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getDescripcion());
            if (proveedor.getId() != null && proveedor.getId() > 0) {
                stmt.setInt(3, proveedor.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from proveedor where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Proveedor getProveedor(ResultSet rs) throws SQLException {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(rs.getInt("id"));
        proveedor.setNombre(rs.getString("nombre"));
        proveedor.setDescripcion(rs.getString("descripcion"));
        return proveedor;
    }
}
