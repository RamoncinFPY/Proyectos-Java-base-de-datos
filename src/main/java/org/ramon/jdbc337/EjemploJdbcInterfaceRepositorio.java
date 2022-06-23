package org.ramon.jdbc337;

import org.ramon.jdbc337.modelo.Producto;
import org.ramon.jdbc337.repositorio.ProductoRepositorioImpl;
import org.ramon.jdbc337.repositorio.Repositorio;

public class EjemploJdbcInterfaceRepositorio {

    public static void main(String[] args) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

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
    }
}
