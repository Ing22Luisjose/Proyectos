package org.diplomado.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/url-get")
public class ParametrosGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String saludo = request.getParameter("saludo");
        String nombre = request.getParameter("nombre");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("   <meta charset=\"UTF-8\">");
        out.println("   <title>Parámetros Get de la url</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Parámetros Get de la url!!</h1>");
        if (saludo != null && nombre != null) {
            out.println("<h2>El saludo enviado es: " + saludo + " " + nombre + "</h2>");
        } else if (saludo != null) {
            out.println("<h2>El saludo enviado es: " + saludo + "</h2>");
        } else if (nombre != null) {
            out.println("<h2>El nombre enviado es: " + nombre + "</h2>");
        }else {
            out.println("<h2>No se han enviado parámetros =O </h2>");
        }
        try{
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            out.println("<h2>El codigo enviado es: " + codigo + "</h2>");
        }catch (NumberFormatException e){
            out.println("<h2>El codigo no se ha enviado, es null</h2>");
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

}
