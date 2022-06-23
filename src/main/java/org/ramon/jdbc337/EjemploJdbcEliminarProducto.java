package org.ramon.jdbc337;

import org.ramon.jdbc337.modelo.Producto;
import org.ramon.jdbc337.repositorio.ProductoRepositorioImpl;
import org.ramon.jdbc337.repositorio.Repositorio;
import org.ramon.jdbc337.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcEliminarProducto {
    public static void main(String[] args) {
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            Repositorio <Producto> repositorio = new ProductoRepositorioImpl();

            System.out.println("================LISTAR=================");
            repositorio.listar().forEach(System.out::println);

            System.out.println("================OBTENER POR ID=================");
            System.out.println(repositorio.porId(9L));

            System.out.println("================ELIMINAR PRODUCTO=================");

            repositorio.eliminar(6L);

            System.out.println("Producto eliminado con éxito!");
            System.out.println("================LISTA ACTUAL DE PRODUCTOS=================");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
