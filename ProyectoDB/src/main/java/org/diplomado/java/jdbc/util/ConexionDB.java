package org.diplomado.java.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static String url = "jdbc:mysql://localhost:3306/java_curso";
    private static String user = "root";
    private static String pass = "castel3135289918";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(pass);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(5);
            pool.setMaxTotal(8);
        }
        return pool;
    }

    public  static  Connection getconnection() throws SQLException{
        return getInstance().getConnection();
    }
}
