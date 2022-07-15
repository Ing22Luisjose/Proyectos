package org.proyecto.delcarrito.webapp.almacen;

import org.proyecto.delcarrito.webapp.modeladodeproductos.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements CrudRepository<Producto> {

    private Connection conn;

    public ProductoRepositoryImpl() {
    }

    public ProductoRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("Select * from producto order by id ASC")) {
            while (rs.next()) {
                Producto producto= getProducto(rs);
                productos.add(producto);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement("Select * from producto WHERE id = 1")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "update producto set id_categoria=?, nombre=?, descripcion=?, cantidad=? where id=?";
        } else {
            sql = "insert into producto (id_categoria,nombre,descripcion,cantidad) values (?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, producto.getCategoria().getId());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setInt(4, producto.getCantidad());
            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setInt(5, producto.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from producto where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        CategoriaRepositoryImpl categoriaRepository = new CategoriaRepositoryImpl();
        producto.setId(rs.getInt("id"));
        producto.setCategoria(categoriaRepository.porId(rs.getLong("id_categoria")));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setCantidad(rs.getInt("cantidad"));
        return producto;
    }
}
