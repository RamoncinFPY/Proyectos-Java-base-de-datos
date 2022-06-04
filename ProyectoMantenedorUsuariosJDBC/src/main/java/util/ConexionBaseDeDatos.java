package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {

    private static String url = "jdbc:mysql://localhost:3306/tarea_37_usuarios?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "1973";

    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
