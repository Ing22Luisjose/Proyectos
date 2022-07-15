package org.proyecto.delcarrito.webapp.registro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.proyecto.delcarrito.webapp.modeladodeproductos.Producto;
import org.proyecto.delcarrito.webapp.almacen.CategoriaRepositoryImpl;
import org.proyecto.delcarrito.webapp.almacen.ProductoRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ServletProducto")
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    try {
                        this.editarProducto(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarProducto(request, response);
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
                        this.insertarProducto(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "modificar":
                    try {
                        this.modificarProducto(request, response);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "eliminar":
                    try {
                        this.eliminarProducto(request, response);
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
        List<Producto> productos = new ProductoRepositoryImpl().listar();
        System.out.println("productos = " + productos);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("productos", productos);
        sesion.setAttribute("totalProductos", productos.size());
        response.sendRedirect("Producto.jsp");
    }

    protected void editarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idProducto = Long.parseLong(request.getParameter("id"));
        Producto producto = new ProductoRepositoryImpl().porId(idProducto);
        request.setAttribute("producto", producto);
        String jspEditar = "/WEB-INF/paginas/Producto/editarCategoria.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    protected void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Long idProducto = Long.parseLong(request.getParameter("id"));
        new ProductoRepositoryImpl().eliminar(idProducto);
        this.accionDefault(request, response);
    }

    protected void insertarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        CategoriaRepositoryImpl categoriaRepository = new CategoriaRepositoryImpl();

        int idProducto = Integer.parseInt(request.getParameter("id"));
        Long idCategoria = Long.parseLong(request.getParameter("id_categoria"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Producto producto = new Producto(null, categoriaRepository.porId(idCategoria), nombre, descripcion, cantidad);
        new ProductoRepositoryImpl().guardar(producto);
        this.accionDefault(request, response);
    }

    protected void modificarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        CategoriaRepositoryImpl categoriaRepository = new CategoriaRepositoryImpl();

        int idProducto = Integer.parseInt(request.getParameter("id"));
        Long idCategoria = Long.parseLong(request.getParameter("id_categoria"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Producto producto = new Producto(idProducto, categoriaRepository.porId(idCategoria), nombre, descripcion, cantidad);
        new ProductoRepositoryImpl().guardar(producto);
        this.accionDefault(request, response);

    }

}
