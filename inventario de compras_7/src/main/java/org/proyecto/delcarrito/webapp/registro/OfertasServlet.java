package org.proyecto.delcarrito.webapp.registro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proyecto.delcarrito.webapp.modeladodeproductos.Ofertas;
import org.proyecto.delcarrito.webapp.almacen.OfertasRepositoryImpl;
import org.proyecto.delcarrito.webapp.almacen.ProveedorRepositoryImpl;
import org.proyecto.delcarrito.webapp.almacen.SolicitudRepostoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ServletOfertas")
public class OfertasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    try {
                        this.editarOferta(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarOferta(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                default:
                    try {
                        this.accionDefault(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    try {
                        this.insertarOferta(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "modificar":
                    try {
                        this.modificarOferta(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarOferta(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                default:
                    try {
                        this.accionDefault(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
            }

        } else {
            try {
                this.accionDefault(request, response);
            } catch (SQLException e) {


            }
        }
    }

    protected void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Ofertas> ofertas = new OfertasRepositoryImpl().listar();
        System.out.println("ofertas = " + ofertas);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("ofertas", ofertas);
        sesion.setAttribute("totalOfertas", ofertas.size());
        response.sendRedirect("Ofertas.jsp");
    }

    protected void editarOferta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idOferta = Long.parseLong(request.getParameter("id"));
        Ofertas oferta = new OfertasRepositoryImpl().porId(idOferta);
        request.setAttribute("oferta", oferta);
        String jspEditar = "/WEB-INF/paginas/Ofertas/editarOfertas.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    protected void eliminarOferta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idOferta = Long.parseLong(request.getParameter("id"));
        new OfertasRepositoryImpl().eliminar(idOferta);
        this.accionDefault(request, response);
    }

    protected void insertarOferta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        ProveedorRepositoryImpl proveedorRepository = new ProveedorRepositoryImpl();
        SolicitudRepostoryImpl solicitudRepostory = new SolicitudRepostoryImpl();

        int idOferta = Integer.parseInt(request.getParameter("id"));
        Long idProveedor = Long.parseLong(request.getParameter("id_proveedor"));
        Long idSolicitud = Long.parseLong(request.getParameter("id_solicitud"));
        int adjudicado=Integer.parseInt(request.getParameter("adjudicado"));
        Long precio = Long.parseLong(request.getParameter("precio"));

        Ofertas oferta = new Ofertas(null, proveedorRepository.porId(idProveedor), solicitudRepostory.porId(idSolicitud), adjudicado, precio);
        new OfertasRepositoryImpl().guardar(oferta);
        this.accionDefault(request, response);
    }

    protected void modificarOferta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ProveedorRepositoryImpl proveedorRepository = new ProveedorRepositoryImpl();
        SolicitudRepostoryImpl solicitudRepostory = new SolicitudRepostoryImpl();

        int idOferta = Integer.parseInt(request.getParameter("id"));
        Long idProveedor = Long.parseLong(request.getParameter("id_proveedor"));
        Long idSolicitud = Long.parseLong(request.getParameter("id_solicitud"));
        int adjudicado=Integer.parseInt(request.getParameter("adjudicado"));
        Long precio = Long.parseLong(request.getParameter("precio"));

        Ofertas oferta = new Ofertas(idOferta, proveedorRepository.porId(idProveedor), solicitudRepostory.porId(idSolicitud), adjudicado, precio);
        new OfertasRepositoryImpl().guardar(oferta);
        this.accionDefault(request, response);

    }

}
