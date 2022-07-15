package org.proyecto.delcarrito.webapp.almacen;

import org.proyecto.delcarrito.webapp.modeladodeproductos.Ofertas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfertasRepositoryImpl implements CrudRepository<Ofertas> {

    private Connection conn;

    public OfertasRepositoryImpl() {
    }

    public OfertasRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Ofertas> listar() throws SQLException {
        List<Ofertas> ofertas = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT o.*, pro.*, sol.* FROM ofertas as o" +
                     "inner join proveedor as pro ON (o.id_proveedor = pro.id) " +
                     "inner join solicitud as sol ON (o.id_solicitud = sol.id) " +
                     "order by o.id ASC")) {
            while (rs.next()) {
                Ofertas ofer = getOferta(rs);
                ofertas.add(ofer);
            }
        }
        return ofertas;
    }

    @Override
    public Ofertas porId(Long id) throws SQLException {
        Ofertas oferta = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT o.*, pro.* , sol.* FROM ofertas as o" +
                "inner join proveedor as pro ON (o.id_proveedor = pro.id) " +
                "inner join solicitud as sol ON (o.id_solicitud = sol.id) " +
                "WHERE o.id = 1")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    oferta = getOferta(rs);
                }
            }
        }
        return oferta;
    }

    @Override
    public void guardar(Ofertas ofertas) throws SQLException {
        String sql;
        if (ofertas.getId() != null && ofertas.getId() > 0) {
            sql = "update ofertas set id_proveedor=?, id_solicitud=?, adjudicado=?, precio=? where id=?";
        } else {
            sql = "insert into ofertas (id_proveedor, id_solicitud, adjudicado, precio) values (?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ofertas.getProveedor().getId());
            stmt.setInt(2, ofertas.getSolicitud().getId());
            stmt.setInt(3, ofertas.getAdjudicado());
            stmt.setLong(4, ofertas.getPrecio());

            if (ofertas.getId() != null && ofertas.getId() > 0) {
                stmt.setInt(5, ofertas.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from ofertas where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Ofertas getOferta(ResultSet rs) throws SQLException {
        Ofertas ofer = new Ofertas();
        ProveedorRepositoryImpl proveedorRepository = new ProveedorRepositoryImpl();
        SolicitudRepostoryImpl solicitudRepostory = new SolicitudRepostoryImpl();
        ofer.setId(rs.getInt("id"));
        ofer.setProveedor(proveedorRepository.porId(rs.getLong("id_proveedor")));
        ofer.setSolicitud(solicitudRepostory.porId(rs.getLong("id_solicitud")));
        ofer.setAdjudicado(rs.getInt("adjudicado"));
        ofer.setPrecio(rs.getLong("precio"));
        return ofer;
    }

}
