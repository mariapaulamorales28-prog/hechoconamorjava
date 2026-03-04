package bisuteria.dao;

import bisuteria.conexion.ConexionBD;
import bisuteria.modelo.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Yo creo la clase UsuarioDAO para manejar todas las operaciones CRUD de usuarios
 * Aquí realizo todas las operaciones sin usar consultas preparadas, solo Statement
 */
public class UsuarioDAO {

    /**
     * Yo creo la tabla de usuarios si no existe
     * Contiene: id (autoincrement), nombre, correo y contraseña
     */
    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS usuario ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL, "
                + "correo VARCHAR(100) NOT NULL UNIQUE, "
                + "contraseña VARCHAR(255) NOT NULL"
                + ")";

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("✓ Tabla de usuarios lista");
        } catch (Exception e) {
            System.out.println("Error al crear tabla de usuarios: " + e.getMessage());
        }
    }

    /**
     * Yo creo el método para registrar un nuevo usuario
     */
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre, correo, contraseña) VALUES('"
                + usuario.getNombre() + "', '"
                + usuario.getCorreo() + "', '"
                + usuario.getContraseña() + "')";

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("✓ Usuario registrado exitosamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Yo creo el método para obtener todos los usuarios registrados
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña")
                );
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    /**
     * Yo creo el método para obtener un usuario por su ID
     */
    public Usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = " + id;
        Usuario usuario = null;

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña")
                );
            }
        } catch (Exception e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
        }

        return usuario;
    }

    /**
     * Yo creo el método para actualizar los datos de un usuario
     */
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = '" + usuario.getNombre()
                + "', correo = '" + usuario.getCorreo()
                + "', contraseña = '" + usuario.getContraseña()
                + "' WHERE id = " + usuario.getId();

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("✓ Usuario actualizado exitosamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Yo creo el método para eliminar un usuario por su ID
     */
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = " + id;

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("✓ Usuario eliminado exitosamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Yo creo el método para buscar un usuario por correo
     */
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        String sql = "SELECT * FROM usuario WHERE correo = '" + correo + "'";
        Usuario usuario = null;

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña")
                );
            }
        } catch (Exception e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
        }

        return usuario;
    }
}
