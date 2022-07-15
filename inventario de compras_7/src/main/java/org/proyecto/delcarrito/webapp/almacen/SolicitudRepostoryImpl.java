package org.proyecto.delcarrito.webapp.almacen;

import org.proyecto.delcarrito.webapp.modeladodeproductos.Solicitud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitudRepostoryImpl implements CrudRepository<Solicitud> {

    private Connection conn;

    public SolicitudRepostoryImpl() {
    }

    public SolicitudRepostoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Solicitud> listar() throws SQLException {
        List<Solicitud> solicitudes = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT o.*, ind.*, pro.* FROM solicitud as o" +
                     "inner join industria as ind ON (o.id_industria = ind.id)" +
                     "inner join producto as pro ON (o.id_producto = pro.id)" +
                     "order by o.id ASC;")) {
            while (rs.next()) {
                Solicitud solicitud = getSolicitud(rs);
                solicitudes.add(solicitud);
            }
        }
        return solicitudes;
    }

    @Override
    public Solicitud porId(Long id) throws SQLException {
        Solicitud solicitud = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT o.*, ind.*, pro.* FROM solicitud as o\n" +
                "inner join industria as ind ON (o.id_industria = ind.id) \n" +
                "inner join producto as pro ON (o.id_producto = pro.id) \n" +
                "where o.id = 1")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    solicitud = getSolicitud(rs);
                }
            }
        }
        return solicitud;
    }

    @Override
    public void guardar(Solicitud solicitud) throws SQLException {
        String sql;
        if (solicitud.getId() != null && solicitud.getId() > 0) {
            sql = "update solicitud set id_industria=?, id_producto=?, fecha_final=?, cantidad=? where id=?";
        } else {
            sql = "insert into solicitud (id_industria, id_producto, fecha_final, cantidad) values (?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, solicitud.getIndustria().getId());
            stmt.setInt(2, solicitud.getProducto().getId());
            stmt.setDate(3, new Date(solicitud.getFechaFinal().getTime()));
            stmt.setInt(4, solicitud.getCantidad());
            if (solicitud.getId() != null && solicitud.getId() > 0) {
                stmt.setInt(5, solicitud.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from solicitud where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Solicitud getSolicitud(ResultSet rs) throws SQLException {
        Solicitud solicitud = new Solicitud();
        IndustriaRepositoryImpl industriaRepository = new IndustriaRepositoryImpl();
        ProductoRepositoryImpl productoRepository = new ProductoRepositoryImpl();
        solicitud.setId(rs.getInt("id"));
        solicitud.setIndustria(industriaRepository.porId(rs.getLong("id_industria")));
        solicitud.setProducto(productoRepository.porId(rs.getLong("id_producto")));
        solicitud.setFechaFinal(rs.getDate("fecha_final"));
        solicitud.setCantidad(rs.getInt("cantidad"));
        return solicitud;
    }

}
