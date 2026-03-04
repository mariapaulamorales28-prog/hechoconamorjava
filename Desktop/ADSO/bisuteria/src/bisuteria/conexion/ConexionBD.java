package bisuteria.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    
    

    public static Connection getConexion() {

        Connection conexion = null;

        try {
            String url = "jdbc:mysql://localhost:3306/bisuteria";
            String usuario = "root";
            String contraseña = "";

            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa");

        } catch (Exception e) {
            System.out.println("Error de conexión:"+e.getMessage());
        }

        return conexion;
    }
}