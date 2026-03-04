package bisuteria.dao;

import bisuteria.conexion.ConexionBD;
import bisuteria.modelo.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Yo creo la clase ProductoDAO para manejar todas las operaciones CRUD de productos
 * Utilizo Statement en lugar de PreparedStatement como solicitaste
 */
public class ProductoDAO {

    /**
     * Yo creo la tabla de productos si no existe
     */
    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS producto ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL, "
                + "precio DOUBLE NOT NULL, "
                + "stock INT NOT NULL"
                + ")";

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("✓ Tabla de productos lista");
        } catch (Exception e) {
            System.out.println("Error al crear tabla de productos: " + e.getMessage());
        }
    }

    /**
     * Yo creo el método para registrar un nuevo producto
     */
    public boolean registrarProducto(Producto producto) {
        String sql = "INSERT INTO producto(nombre, precio, stock) VALUES('"
                + producto.getNombre() + "', "
                + producto.getPrecio() + ", "
                + producto.getStock() + ")";

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("✓ Producto registrado exitosamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Yo creo el método para obtener todos los productos registrados
     */
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                productos.add(producto);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }

        return productos;
    }

    /**
     * Yo creo el método para obtener un producto por su ID
     */
    public Producto obtenerProductoPorId(int id) {
        String sql = "SELECT * FROM producto WHERE id = " + id;
        Producto producto = null;

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            System.out.println("Error al obtener producto: " + e.getMessage());
        }

        return producto;
    }

    /**
     * Yo creo el método para actualizar los datos de un producto
     */
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre = '" + producto.getNombre()
                + "', precio = " + producto.getPrecio()
                + ", stock = " + producto.getStock()
                + " WHERE id = " + producto.getId();

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("✓ Producto actualizado exitosamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Yo creo el método para eliminar un producto por su ID
     */
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM producto WHERE id = " + id;

        try {
            Connection con = ConexionBD.getConexion();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("✓ Producto eliminado exitosamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}
