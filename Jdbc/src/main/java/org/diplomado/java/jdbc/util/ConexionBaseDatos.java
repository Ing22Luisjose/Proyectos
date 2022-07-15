package org.diplomado.java.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/java_curso";
    private static String  username = "root";
    private static String password = "123456789";
    //private static Connection conextres;

    private static BasicDataSource pool;


    /* prueba1
    private static ConexionBaseDatos miObjeto;

    private ConexionBaseDatos(){

        if (miObjeto==null){

            miObjeto = new ConexionBaseDatos();
        }

    }*/


    /* prueba 2
    public static Connection getInstance() throws SQLException{

        if (conextres == null ){
            conextres = DriverManager.getConnection(url, username, password);
        }
        return conextres;

    }*/

    //prueba3
    public static BasicDataSource getInstance() throws SQLException {

        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3);
            pool.setMinIdle(2);
            pool.setMaxIdle(5);
            pool.setMaxTotal(8);
        }
        return pool;
    }


    public static Connection getConnection() throws SQLException{
        return getInstance().getConnection();
    }



}

