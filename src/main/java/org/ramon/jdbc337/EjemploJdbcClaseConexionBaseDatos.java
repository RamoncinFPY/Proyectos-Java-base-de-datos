package org.ramon.jdbc337;

import org.ramon.jdbc337.util.ConexionBaseDatos;

import java.sql.*;

public class EjemploJdbcClaseConexionBaseDatos {

    public static void main(String[] args) {

        //Usamos dentro de los paréntesis el auto close del API.
        try (Connection conn = ConexionBaseDatos.getConnection() ;
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
