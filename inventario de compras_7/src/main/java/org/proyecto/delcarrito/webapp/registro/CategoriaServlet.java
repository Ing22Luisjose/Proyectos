package org.proyecto.delcarrito.webapp.registro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proyecto.delcarrito.webapp.modeladodeproductos.Categoria;
import org.proyecto.delcarrito.webapp.almacen.CategoriaRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ServletCategoria")
public class CategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    try {
                        this.editarCategoria(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarCategoria(request, response);
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
                        this.insertarCategoria(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "modificar":
                    try {
                        this.modificarCategoria(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarCategoria(request, response);
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
        List<Categoria> categorias = new CategoriaRepositoryImpl().listar();
        System.out.println("categoria = " + categorias);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("categoria", categorias);
        sesion.setAttribute("totalCategorias", categorias.size());
        response.sendRedirect("Categoria.jsp");
    }

    protected void editarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idCategoria = Long.parseLong(request.getParameter("id"));
        Categoria categoria = new CategoriaRepositoryImpl().porId(idCategoria);
        request.setAttribute("categoria", categoria);
        String jspEditar = "/WEB-INF/paginas/Categoria/editarCategoria.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    protected void eliminarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idCategoria = Long.parseLong(request.getParameter("id"));
        new CategoriaRepositoryImpl().eliminar(idCategoria);
        this.accionDefault(request, response);
    }

    protected void insertarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int idCategoria = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");

        Categoria categoria = new Categoria(null, nombre);
        new CategoriaRepositoryImpl().guardar(categoria);
        this.accionDefault(request, response);
    }

    protected void modificarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int idCategoria = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");

        Categoria categoria = new Categoria(idCategoria, nombre);
        new CategoriaRepositoryImpl().guardar(categoria);
        this.accionDefault(request, response);

    }

}
