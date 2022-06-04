package org.ramon.jdbc337.util;

import java.sql.*;

public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "1973";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
