package org.ramon.jdbc337.repositorio;

import org.ramon.jdbc337.modelo.Categoria;
import org.ramon.jdbc337.modelo.Producto;
import org.ramon.jdbc337.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio implements Repositorio<Producto> {

    private Connection getConection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                     "inner join categorias as c ON (p.categoria_id = c.id)")) {
            while (rs.next()) {
                Producto p = crearProducto(rs);
                productos.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) {
        Producto producto = null;
        try (Connection conn = getConection();
             PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre AS categoria FROM productos as p inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")) {
             stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = crearProducto(rs);
                }
                // rs.close();  Alt + enter sobre ResultSet para anidar en try para el autoclose.
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId() > 0)  {
            sql = "UPDATE productos SET nombre = ?, precio = ?, categoria_id = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos (nombre, precio, categoria_id, fecha_registro) VALUES (?, ?, ?, ?)";
        }
        try(Connection conn = getConection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategoria().getId());

            if (producto.getId() != null && producto.getId() > 0) {
               stmt.setLong(4, producto.getId());
            } else {
                stmt.setDate(4, new Date(producto.getFechaRegistro().getTime()));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try (Connection conn = getConection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM productos WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //MÉTODOS PRIVADOS.
    //Método extraído del método listar (marcar lo que se desea extraer-> refactor -> extraer método)
    private Producto crearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro"));

        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombre(rs.getString("categoria"));

        p.setCategoria(categoria);
        return p;
    }
}
