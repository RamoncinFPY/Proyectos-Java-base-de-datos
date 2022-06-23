package org.ramon.jdbc337;

import org.ramon.jdbc337.modelo.Categoria;
import org.ramon.jdbc337.modelo.Producto;
import org.ramon.jdbc337.repositorio.ProductoRepositorioImpl;
import org.ramon.jdbc337.repositorio.Repositorio;

public class EjemploJdbcEditarProducto {
    public static void main(String[] args) {
            Repositorio <Producto> repositorio = new ProductoRepositorioImpl();

            System.out.println("================LISTAR=================");
            repositorio.listar().forEach(System.out::println);

            System.out.println("================OBTENER POR ID=================");
            System.out.println(repositorio.porId(1L));

            System.out.println("================EDITAR PRODUCTO=================");
            Producto producto = new Producto();
            producto.setId(10L);
            producto.setNombre("Barra duplex");
            producto.setPrecio(400);

            Categoria categoria = new Categoria();
            categoria.setId(1L);
            producto.setCategoria(categoria);

            repositorio.guardar(producto);
            System.out.println("Producto editado con éxito!");

            System.out.println("================producto=================");
            repositorio.listar().forEach(System.out::println);
    }
}
