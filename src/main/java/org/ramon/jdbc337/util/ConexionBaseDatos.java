package org.ramon.jdbc337.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "1973";
    private static BasicDataSource pool;


    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(3); //Por defecto es 0 y se va incrementando hasta 8 como máximo
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8); //Por defecto es 8
        }
        return pool;
    }

    //La primera vez se realiza la conexión
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
