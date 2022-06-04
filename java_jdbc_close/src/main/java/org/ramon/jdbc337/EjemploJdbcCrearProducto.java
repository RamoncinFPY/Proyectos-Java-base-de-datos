package org.ramon.jdbc337;

import org.ramon.jdbc337.modelo.Categoria;
import org.ramon.jdbc337.modelo.Producto;
import org.ramon.jdbc337.repositorio.ProductoRepositorio;
import org.ramon.jdbc337.repositorio.Repositorio;
import org.ramon.jdbc337.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcCrearProducto {
    public static void main(String[] args) {
            Repositorio <Producto> repositorio = new ProductoRepositorio();

            System.out.println("================LISTAR=================");
            repositorio.listar().forEach(System.out::println);

            System.out.println("================OBTENER POR ID=================");
            System.out.println(repositorio.porId(7L));

            System.out.println("================INSERTAR NUEVO PRODUCTO=================");
            Producto producto = new Producto();
            producto.setNombre("Barra Z");
            producto.setPrecio(395);
            producto.setFechaRegistro(new Date());

            Categoria categoria = new Categoria();
            categoria.setId(1L);
            producto.setCategoria(categoria);

            repositorio.guardar(producto);
            System.out.println("Producto guardado con éxito!");

            System.out.println("================p1=================");
            repositorio.listar().forEach(System.out::println);

            System.out.println("================EDITAR PRODUCTO=================");
            Producto p2 = new Producto();
            producto.setId(9L);
            producto.setNombre("Barra simple");
            producto.setPrecio(250);

            repositorio.guardar(producto);
            System.out.println("Producto editado con éxito!");

            System.out.println("================p1=================");
            repositorio.listar().forEach(System.out::println);
    }
}
