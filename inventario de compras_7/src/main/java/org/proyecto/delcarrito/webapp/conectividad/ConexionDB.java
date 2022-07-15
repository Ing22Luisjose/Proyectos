package org.proyecto.delcarrito.webapp.conectividad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static String url = "jdbc:mysql://localhost:3306/java_curso";
    private static String user = "root";
    private static String pass = "22420132";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

}
