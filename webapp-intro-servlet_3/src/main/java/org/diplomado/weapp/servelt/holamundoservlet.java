package org.diplomado.weapp.servelt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/hola-mundo")
public class holamundoservlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println(" <meta charset=\"UTF-8\">");
        out.println(" <title> hola mundo servlet </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> hola mundo servlet oki!</h1>");
        out.println("</body>");
        out.println("</html>");

    }
}