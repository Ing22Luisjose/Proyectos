package org.proyecto.delcarrito.webapp.registro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proyecto.delcarrito.webapp.modeladodeproductos.Industria;
import org.proyecto.delcarrito.webapp.almacen.IndustriaRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ServletIndustria")
public class IndustriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    try {
                        this.editarIndustria(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarIndustria(request, response);
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
                        this.insertarIndustria(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "modificar":
                    try {
                        this.modificarIndustria(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarIndustria(request, response);
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
        List<Industria> industrias = new IndustriaRepositoryImpl().listar();
        System.out.println("industrias = " + industrias);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("industrias", industrias);
        sesion.setAttribute("totalIndustrias", industrias.size());
        response.sendRedirect("Industria.jsp");
    }

    protected void editarIndustria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idIndustria = Long.parseLong(request.getParameter("id"));
        Industria industria = new IndustriaRepositoryImpl().porId(idIndustria);
        request.setAttribute("industria", industria);
        String jspEditar = "/WEB-INF/paginas/Industria/editarIndustria.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    protected void eliminarIndustria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idIndustria = Long.parseLong(request.getParameter("id"));
        new IndustriaRepositoryImpl().eliminar(idIndustria);
        this.accionDefault(request, response);
    }

    protected void insertarIndustria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int idIndustria = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String detalle = request.getParameter("detalle");

        Industria industria = new Industria(null,nombre, detalle);
        new IndustriaRepositoryImpl().guardar(industria);
        this.accionDefault(request, response);
    }

    protected void modificarIndustria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{

        int idIndustria = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String detalle = request.getParameter("detalle");

        Industria industria = new Industria(idIndustria,nombre, detalle);
        new IndustriaRepositoryImpl().guardar(industria);
        this.accionDefault(request, response);

    }

}
