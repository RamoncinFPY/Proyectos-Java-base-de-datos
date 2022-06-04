package org.ramon.jdbc337;

import org.ramon.jdbc337.modelo.Producto;
import org.ramon.jdbc337.repositorio.ProductoRepositorio;
import org.ramon.jdbc337.repositorio.Repositorio;
import org.ramon.jdbc337.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcEditarProducto {
    public static void main(String[] args) {
        try (Connection conn = ConexionBaseDatos.getInstance()) {
            Repositorio <Producto> repositorio = new ProductoRepositorio();

            System.out.println("================LISTAR=================");
            repositorio.listar().forEach(System.out::println);

            System.out.println("================OBTENER POR ID=================");
            System.out.println(repositorio.porId(1L));

            System.out.println("================EDITAR PRODUCTO=================");
            Producto producto = new Producto();
            producto.setId(7L);
            producto.setNombre("Mini Altavoz 3w");
            producto.setPrecio(5);

            repositorio.guardar(producto);

            System.out.println("Producto editado con éxito!");
            System.out.println("================producto=================");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
