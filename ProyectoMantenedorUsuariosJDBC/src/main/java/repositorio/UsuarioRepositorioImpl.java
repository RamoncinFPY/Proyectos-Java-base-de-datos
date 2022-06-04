package repositorio;

import model.Usuario;
import util.ConexionBaseDeDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorioImpl implements Repositorio <Usuario>{

    private Connection getConection() throws SQLException {
        return ConexionBaseDeDatos.getInstance();
    }
    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try(Connection conn = getConection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")){

            while (rs.next()) {
                Usuario usuario = crearUsuario(rs);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    @Override
    public Usuario porId(int id) {
        Usuario usuario = null;
        try (Connection conn = getConection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = crearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    @Override
    public void guardar(Usuario usuario) {
        String sql;
        //usuario.getId() != null &&
        if (usuario.getId() > 0)  {
            sql = "UPDATE usuarios SET username = ?, password = ?, email = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO usuarios (username, password, email) VALUES (?, ?, ?)";
        }
        try(Connection conn = getConection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());

            //usuario.getId() != null &&
            if (usuario.getId() > 0) {
                stmt.setInt(4, usuario.getId());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int id) {
        try (Connection conn = getConection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuarios WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario crearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEmail(rs.getString("email"));
        return usuario;
    }
}
