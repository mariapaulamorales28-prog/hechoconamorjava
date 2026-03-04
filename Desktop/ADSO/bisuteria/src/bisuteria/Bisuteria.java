/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bisuteria;

import java.util.Scanner;
import java.util.List;
import bisuteria.dao.UsuarioDAO;
import bisuteria.dao.ProductoDAO;
import bisuteria.modelo.Usuario;
import bisuteria.modelo.Producto;

/**
 * Yo creo la clase principal del proyecto Bisutería Hechos con Amor
 * 
 * @author MariaPaulaMorales
 */
public class Bisuteria {

    // Yo creo las instancias globales para acceder a los DAOs
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static ProductoDAO productoDAO = new ProductoDAO();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     * Yo creo el método principal de donde inicia el proyecto
     */
    public static void main(String[] args) {
        // Yo creo las tablas necesarias al iniciar
        usuarioDAO.crearTabla();
        productoDAO.crearTabla();

        System.out.println("\n================================");
        System.out.println("  BISUTERÍA HECHOS CON AMOR");
        System.out.println("================================\n");

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    menuRegistroUsuarios();
                    break;
                case 2:
                    salir = true;
                    System.out.println("\n¡Hasta luego! Gracias por usar Bisutería Hechos con Amor");
                    break;
                default:
                    System.out.println("⚠ Opción no válida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }

    /**
     * Yo creo el método para mostrar el menú principal
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Gestionar Usuarios (Registro)");
        System.out.println("2. Salir");
        System.out.print("Elige una opción: ");
    }

    /**
     * Yo creo el método para el menú de registro de usuarios
     */
    private static void menuRegistroUsuarios() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Registrar nuevo usuario");
            System.out.println("2. Ver todos los usuarios");
            System.out.println("3. Buscar usuario por ID");
            System.out.println("4. Actualizar usuario");
            System.out.println("5. Eliminar usuario");
            System.out.println("6. Volver al menú principal");
            System.out.print("Elige una opción: ");

            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    registrarNuevoUsuario();
                    break;
                case 2:
                    verTodosLosUsuarios();
                    break;
                case 3:
                    buscarUsuarioPorId();
                    break;
                case 4:
                    actualizarUsuario();
                    break;
                case 5:
                    eliminarUsuario();
                    break;
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("⚠ Opción no válida.");
            }
        }
    }

    /**
     * Yo creo el método para registrar un nuevo usuario
     */
    private static void registrarNuevoUsuario() {
        System.out.println("\n--- REGISTRAR NUEVO USUARIO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, correo, contraseña);
        usuarioDAO.registrarUsuario(usuario);
    }

    /**
     * Yo creo el método para ver todos los usuarios
     */
    private static void verTodosLosUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() + " | Nombre: " + usuario.getNombre()
                        + " | Correo: " + usuario.getCorreo());
            }
        }
    }

    /**
     * Yo creo el método para buscar un usuario por ID
     */
    private static void buscarUsuarioPorId() {
        System.out.println("\n--- BUSCAR USUARIO ---");
        System.out.print("Ingresa el ID del usuario: ");
        int id = obtenerNumero();

        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);
        if (usuario != null) {
            System.out.println("\n✓ Usuario encontrado:");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Correo: " + usuario.getCorreo());
        } else {
            System.out.println("⚠ Usuario no encontrado.");
        }
    }

    /**
     * Yo creo el método para actualizar un usuario
     */
    private static void actualizarUsuario() {
        System.out.println("\n--- ACTUALIZAR USUARIO ---");
        System.out.print("Ingresa el ID del usuario a actualizar: ");
        int id = obtenerNumero();

        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);
        if (usuario != null) {
            System.out.print("Nuevo nombre: ");
            usuario.setNombre(scanner.nextLine());

            System.out.print("Nuevo correo: ");
            usuario.setCorreo(scanner.nextLine());

            System.out.print("Nueva contraseña: ");
            usuario.setContraseña(scanner.nextLine());

            usuarioDAO.actualizarUsuario(usuario);
        } else {
            System.out.println("⚠ Usuario no encontrado.");
        }
    }

    /**
     * Yo creo el método para eliminar un usuario
     */
    private static void eliminarUsuario() {
        System.out.println("\n--- ELIMINAR USUARIO ---");
        System.out.print("Ingresa el ID del usuario a eliminar: ");
        int id = obtenerNumero();

        usuarioDAO.eliminarUsuario(id);
    }

    /**
     * Yo creo el método para el menú de productos
     */
    private static void menuProductos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Registrar nuevo producto");
            System.out.println("2. Ver todos los productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Volver al menú principal");
            System.out.print("Elige una opción: ");

            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    registrarNuevoProducto();
                    break;
                case 2:
                    verTodosLosProductos();
                    break;
                case 3:
                    buscarProductoPorId();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("⚠ Opción no válida.");
            }
        }
    }

    /**
     * Yo creo el método para registrar un nuevo producto
     */
    private static void registrarNuevoProducto() {
        System.out.println("\n--- REGISTRAR NUEVO PRODUCTO ---");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = obtenerDecimal();

        System.out.print("Stock: ");
        int stock = obtenerNumero();

        Producto producto = new Producto(0, nombre, precio, stock);
        productoDAO.registrarProducto(producto);
    }

    /**
     * Yo creo el método para ver todos los productos
     */
    private static void verTodosLosProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        List<Producto> productos = productoDAO.obtenerTodosLosProductos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto producto : productos) {
                System.out.println("ID: " + producto.getId() + " | " + producto.getNombre()
                        + " | Precio: $" + producto.getPrecio() + " | Stock: " + producto.getStock());
            }
        }
    }

    /**
     * Yo creo el método para buscar un producto por ID
     */
    private static void buscarProductoPorId() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        System.out.print("Ingresa el ID del producto: ");
        int id = obtenerNumero();

        Producto producto = productoDAO.obtenerProductoPorId(id);
        if (producto != null) {
            System.out.println("\n✓ Producto encontrado:");
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Precio: $" + producto.getPrecio());
            System.out.println("Stock: " + producto.getStock());
        } else {
            System.out.println("⚠ Producto no encontrado.");
        }
    }

    /**
     * Yo creo el método para actualizar un producto
     */
    private static void actualizarProducto() {
        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");
        System.out.print("Ingresa el ID del producto a actualizar: ");
        int id = obtenerNumero();

        Producto producto = productoDAO.obtenerProductoPorId(id);
        if (producto != null) {
            System.out.print("Nuevo nombre: ");
            producto.setNombre(scanner.nextLine());

            System.out.print("Nuevo precio: ");
            producto.setPrecio(obtenerDecimal());

            System.out.print("Nuevo stock: ");
            producto.setStock(obtenerNumero());

            productoDAO.actualizarProducto(producto);
        } else {
            System.out.println("⚠ Producto no encontrado.");
        }
    }

    /**
     * Yo creo el método para eliminar un producto
     */
    private static void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        System.out.print("Ingresa el ID del producto a eliminar: ");
        int id = obtenerNumero();

        productoDAO.eliminarProducto(id);
    }

    /**
     * Yo creo el método auxiliar para obtener una opción numérica del usuario
     */
    private static int obtenerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            return opcion;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Yo creo el método auxiliar para obtener un número entero del usuario
     */
    private static int obtenerNumero() {
        try {
            int numero = Integer.parseInt(scanner.nextLine());
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("⚠ Por favor ingresa un número válido.");
            return -1;
        }
    }

    /**
     * Yo creo el método auxiliar para obtener un número decimal del usuario
     */
    private static double obtenerDecimal() {
        try {
            double numero = Double.parseDouble(scanner.nextLine());
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("⚠ Por favor ingresa un número válido.");
            return -1;
        }
    }
}
