package org.ramon.jdbc337;

import java.sql.*;

public class EjemploJdbc {

    public static void main(String[] args) {

        //Primera manera con demasiados try, catch, finally... Pero funciona.
        String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=Europe/Madrid";
        String username = "root";
        String password = "1973";

        Connection conn = null;
        Statement stmt = null;
        ResultSet resultado = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            resultado = stmt.executeQuery("SELECT * FROM productos");

            while (resultado.next()) {
                System.out.print("id: " + resultado.getInt(1) + " | ");
                System.out.print("Producto: " + resultado.getString(2) + " | ");
                System.out.print("Precio: " + resultado.getInt("precio") + " | ");
                System.out.print("Fecha de ingreso: " + resultado.getDate(4));
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
                stmt.close();
                resultado.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
