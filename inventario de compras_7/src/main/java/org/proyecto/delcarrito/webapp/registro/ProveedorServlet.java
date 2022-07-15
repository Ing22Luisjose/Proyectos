package org.proyecto.delcarrito.webapp.registro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proyecto.delcarrito.webapp.modeladodeproductos.Proveedor;
import org.proyecto.delcarrito.webapp.almacen.ProveedorRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ServletProveedor")
public class ProveedorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    try {
                        this.editarProveedor(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarProveedor(request, response);
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
                        this.insertarProveedor(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "modificar":
                    try {
                        this.modificarProveedor(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarProveedor(request, response);
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
        List<Proveedor> proveedores = new ProveedorRepositoryImpl().listar();
        System.out.println("proveedores = " + proveedores);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("proveedores", proveedores);
        sesion.setAttribute("totalProveedores", proveedores.size());
        response.sendRedirect("Proveedor.jsp");
    }

    protected void editarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idProveedor = Long.parseLong(request.getParameter("id"));
        Proveedor proveedor = new ProveedorRepositoryImpl().porId(idProveedor);
        request.setAttribute("proveedor", proveedor);
        String jspEditar = "/WEB-INF/paginas/Proveedor/editarCategoria.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    protected void eliminarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idProveedor = Long.parseLong(request.getParameter("id"));
        new ProveedorRepositoryImpl().eliminar(idProveedor);
        this.accionDefault(request, response);
    }

    protected void insertarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int idProveedor = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Proveedor proveedor = new Proveedor(null,nombre, descripcion);
        new ProveedorRepositoryImpl().guardar(proveedor);
        this.accionDefault(request, response);
    }

    protected void modificarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        int idProveedor = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Proveedor proveedor = new Proveedor(idProveedor,nombre, descripcion);
        new ProveedorRepositoryImpl().guardar(proveedor);
        this.accionDefault(request, response);

    }

}
