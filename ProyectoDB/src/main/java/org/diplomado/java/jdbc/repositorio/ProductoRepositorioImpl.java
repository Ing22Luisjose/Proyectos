package org.diplomado.java.jdbc.repositorio;

import org.diplomado.java.jdbc.models.Categoria;
import org.diplomado.java.jdbc.models.Producto;
import org.diplomado.java.jdbc.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

    private  Connection conn;

    public ProductoRepositorioImpl(Connection conn) {
        this.conn = conn;
    }

    public ProductoRepositorioImpl() {
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }


    private Producto crearObjetoProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        Categoria c = new Categoria();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro"));
        c.setId(rs.getInt("categoria_id"));
        c.setNombre(rs.getString("categoria_nombre"));
        p.setCategoria(c);
        return p;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet resl = stmt.executeQuery("Select p.*, c.nombre as categoria_nombre from productos p " +
                     "inner join categorias c on (p.categoria_id = c.id)")) {
            while (resl.next()) {
                productos.add(crearObjetoProducto(resl));
            }
        }
        return productos;
    }

    @Override
    public Producto porId(int id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement("Select p.*, c.nombre as categoria_nombre from productos p " +
                "inner join categorias c on (p.categoria_id = c.id) where p.id= ?")) {
            stmt.setInt(1, id);
            try (ResultSet resl = stmt.executeQuery()) {
                if (resl.next()) {
                    producto = crearObjetoProducto(resl);
                }
            }
        }
        return producto;
    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        String sql;
        if (producto != null && producto.getId() != null && producto.getId() > 0) {
            sql = "update productos set nombre = ?, precio = ?, categoria_id = ?, sku = ? where id = ?";
        } else {
            sql = "insert into productos(nombre,precio,categoria_id,sku,fecha_registro) values (?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1,producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setInt(3,producto.getCategoria().getId());
            stmt.setString(4,producto.getSku());
            if (producto != null && producto.getId() != null && producto.getId() > 0){
                stmt.setInt(5,producto.getId());
            }else {
                stmt.setDate(5,new Date(producto.getFechaRegistro().getTime()));
            }
            stmt.executeUpdate();
            if(producto.getId() == null){
                try(ResultSet rs = stmt.getGeneratedKeys()){
                    if(rs.next()){
                        producto.setId(rs.getInt(1));
                    }
                }
            }
            return producto;
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("Delete from productos where id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
