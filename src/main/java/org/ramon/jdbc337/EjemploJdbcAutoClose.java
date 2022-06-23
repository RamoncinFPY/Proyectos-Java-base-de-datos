package org.ramon.jdbc337;

import java.sql.*;

public class EjemploJdbcAutoClose {

    public static void main(String[] args) {

        //A partir del '?' se usa para poner la fecha de la localidad donde se esté usando.
        String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=Europe/Madrid";
        String username = "root";
        String password = "1973";

        //Usamos dentro de los paréntesis el auto close del API.
        try ( Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet resultado = stmt.executeQuery("SELECT * FROM productos") ) {


            while (resultado.next()) {
                System.out.print("id: " + resultado.getInt(1) + " | ");
                System.out.print("Producto: " + resultado.getString(2) + " | ");
                System.out.print("Precio: " + resultado.getInt("precio") + " | ");
                System.out.print("Fecha de registro: " + resultado.getDate(4));
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
