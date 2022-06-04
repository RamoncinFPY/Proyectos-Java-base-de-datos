package org.ramon.jdbc337;

import org.ramon.jdbc337.modelo.Producto;
import org.ramon.jdbc337.repositorio.ProductoRepositorio;
import org.ramon.jdbc337.repositorio.Repositorio;
import org.ramon.jdbc337.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploJdbcInterfaceRepositorio {

    public static void main(String[] args) {

        //Usamos dentro de los paréntesis el auto close del API.
        try (Connection conn = ConexionBaseDatos.getInstance()) {

            Repositorio<Producto> repositorio = new ProductoRepositorio();

            //Una manera de imprimir los resultados
            System.out.println("=========foreach al vuelo=============");
            repositorio.listar().forEach(p -> {
                System.out.print("Id: " + p.getId());
                System.out.print("\t | ");
                System.out.print("Producto: " +p.getNombre());
                System.out.print("\t\t | ");
                System.out.print("Precio: " + p.getPrecio());
                System.out.print("\t | ");
                System.out.print("Fecha de registro: " + p.getFechaRegistro());
                System.out.println();
            });

            //Otra manera de imprimir los datos.
            System.out.println("=========foreach abreviado, más toString()=============");
            repositorio.listar().forEach(System.out::println);

            //Imprimo los datos usando el método porId(1L);
            System.out.println("=========Usando el método porId();=============");
            System.out.println(repositorio.porId(2L));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
