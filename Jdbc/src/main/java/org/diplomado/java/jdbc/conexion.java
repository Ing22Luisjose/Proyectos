package org.diplomado.java.jdbc;
import  java.sql.*;


public class conexion {

    public static void main (String[] args) {

        String url = "jdbc:mysql://localhost:3306/java_curso";
        String username = "root";
        String password = "123456789";


        try{

            Connection conex = DriverManager.getConnection(url, username, password);
            Statement  stmt  = conex.createStatement();
            ResultSet resultado = stmt.executeQuery(  "SELECT * FROM productos");

            while (resultado.next())
            {
                System.out.println(resultado.getString("nombre"));
            }
            resultado.close();
            stmt.close();
            conex.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
