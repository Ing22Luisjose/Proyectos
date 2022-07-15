package org.diplomado.java.jdbc;

import java.sql.*;

public class conexxion {

    public static void main (String[] args) {

        String url = "jdbc:mysql://localhost:3306/java_curso";
        String username = "root";
        String password = "123456789";


        try{

            Connection conex = DriverManager.getConnection(url, username, password);
            Statement stmt  = conex.createStatement();
            ResultSet resultado = stmt.executeQuery(  "SELECT * FROM productos");

            while (resultado.next())
            {
                System.out.print(resultado.getInt("idproductos"));
                System.out.print(" | ");
                System.out.print(resultado.getString("nombre"));
                System.out.print(" | ");
                System.out.print(resultado.getInt("precio"));
                System.out.print(" | ");
                System.out.println(resultado.getDate("fecha_registro"));

            }

        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
