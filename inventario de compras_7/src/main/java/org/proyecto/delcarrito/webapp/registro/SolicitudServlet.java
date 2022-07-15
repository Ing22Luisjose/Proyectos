package org.proyecto.delcarrito.webapp.registro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proyecto.delcarrito.webapp.modeladodeproductos.Solicitud;
import org.proyecto.delcarrito.webapp.almacen.IndustriaRepositoryImpl;
import org.proyecto.delcarrito.webapp.almacen.ProductoRepositoryImpl;
import org.proyecto.delcarrito.webapp.almacen.SolicitudRepostoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/ServletSolicitud")
public class SolicitudServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    try {
                        this.editarSolicitud(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarSolicitud(request, response);
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
                        this.insertarSolicitud(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "modificar":
                    try {
                        this.modificarSolicitud(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarSolicitud(request, response);
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
        List<Solicitud> solicitudes = new SolicitudRepostoryImpl().listar();
        System.out.println("solicitudes = " + solicitudes);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("solicitudes", solicitudes);
        sesion.setAttribute("totalSolicitudes", solicitudes.size());
        response.sendRedirect("Solicitudes.jsp");
    }

    protected void editarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idSolicitud = Long.parseLong(request.getParameter("id"));
        Solicitud solicitud = new SolicitudRepostoryImpl().porId(idSolicitud);
        request.setAttribute("solicitud", solicitud);
        String jspEditar = "/WEB-INF/paginas/Solicitud/editarCategoria.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    protected void eliminarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idSolicitud = Long.parseLong(request.getParameter("id"));
        new SolicitudRepostoryImpl().eliminar(idSolicitud);
        this.accionDefault(request, response);
    }

    protected void insertarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        ProductoRepositoryImpl productoRepository = new ProductoRepositoryImpl();
        IndustriaRepositoryImpl industriaRepository = new IndustriaRepositoryImpl();

        int idSolicitud = Integer.parseInt(request.getParameter("id"));
        Long idIndustria = Long.parseLong(request.getParameter("id_industria"));
        Long idProducto = Long.parseLong(request.getParameter("id_producto"));
        String fecha_final = request.getParameter("fecha_final");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(fecha_final);

        Solicitud solicitud = new Solicitud(null, industriaRepository.porId(idIndustria), productoRepository.porId(idProducto), date1, cantidad);
        new SolicitudRepostoryImpl().guardar(solicitud);
        this.accionDefault(request, response);
    }

    protected void modificarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        ProductoRepositoryImpl productoRepository = new ProductoRepositoryImpl();
        IndustriaRepositoryImpl industriaRepository = new IndustriaRepositoryImpl();

        int idSolicitud = Integer.parseInt(request.getParameter("id"));
        Long idIndustria = Long.parseLong(request.getParameter("id_industria"));
        Long idProducto = Long.parseLong(request.getParameter("id_producto"));
        String fecha_final = request.getParameter("fecha_final");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(fecha_final);

        Solicitud solicitud = new Solicitud(idSolicitud, industriaRepository.porId(idIndustria), productoRepository.porId(idProducto), date1, cantidad);
        new SolicitudRepostoryImpl().guardar(solicitud);
        this.accionDefault(request, response);

    }

}
