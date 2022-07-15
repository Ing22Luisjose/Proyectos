package org.diplomado.java.jdbc.repositorio;

import com.mysql.cj.protocol.Resultset;
import org.diplomado.java.jdbc.modelo.Categorias;
import org.diplomado.java.jdbc.modelo.Producto;
import org.diplomado.java.jdbc.util.ConexionBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoriolmpl implements Repositorio<Producto> {

    private Connection conn;
    public ProductoRepositoriolmpl(Connection conn){
        this.conn =conn;
    }

    private Producto crearObjetoProducto(ResultSet rs) throws SQLException{
        Producto p = new Producto();
        p.setId(rs.getLong("idproductos"));//como se llaman las tabla en sql
        p.setNombre(rs.getString("nombre"));//como se llaman las tabla en sql
        p.setPrecio(rs.getInt("precio"));//como se llaman las tabla en sql
        p.setFechaRegistro(rs.getDate("fecha_registro"));//como se llaman las tabla en sql
        p.setSku(rs.getString("sku"));
        Categorias categorias = new Categorias();
        categorias.setId(rs.getLong("idcategoriapro"));
        categorias.setNombre(rs.getString("categorias"));
        p.setCategorias(categorias);
        return p;
    }


    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet resultado = stmt.executeQuery("SELECT p.*, c.nombrecat as categorias FROM productos as p INNER JOIN categorias as c ON (p.idcategoriapro = c.idcategorias)"))
        {
            while (resultado.next()) {
                Producto p = crearObjetoProducto(resultado);
                productos.add(p);
            }
        }
        return productos;

    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombrecat as categorias FROM productos as p INNER JOIN categorias as c ON (p.idcategoriapro) WHERE p.idcategoriapro  = ?")){
            stmt.setLong(1,id);

            try (ResultSet resultado = stmt.executeQuery()){
                if (resultado.next()){
                    producto =crearObjetoProducto(resultado);
                }
            }

        }
        return producto;

    }

    @Override
    public Producto guardar(Producto producto) throws SQLException {
        String sql;
        if (/*producto != null &&*/ producto.getId() != null && producto.getId()>0){
            sql = "UPDATE productos SET nombre=?, precio=?, idcategoriapro=?, sku=? WHERE idproductos=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, idcategoriapro, sku, fecha_registro) VALUES(?,?,?,?,?)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategorias().getId());
            stmt.setString(4, producto.getSku());

            if (/*producto != null &&*/ producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(5, producto.getId());
            } else {
                stmt.setDate(5, new Date(producto.getFechaRegistro().getTime()));
            }
            stmt.executeUpdate();

            if (producto.getId() == null) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        producto.setId(rs.getLong(1));
                    }
                }
            }
            return producto;

        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM productos WHERE idproductos=?"))
        {
            stmt.setLong(1,id);
            stmt.executeUpdate();

        }

    }

}
