package Service;

import entity.Producto;
import Respository.ProductosRepository;

import javax.swing.*;
import java.math.BigDecimal;

public class Main {

    private static final ProductosRepository productosRepository = new ProductosRepository();

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog(null, """
                ---CRUD DE PRODUCTO DE LA TIENDA---
                1. Crear Producto
                2. Leer Producto
                3. Actualizar Producto
                4. Eliminar Producto
                5. Lista de Productos
                6. Salir
                """);

            switch (opcion) {
                case "1" -> crearProducto();
                case "2" -> leerProducto();
                case "3" -> actualizarProducto();
                case "4" -> eliminarProducto();
                case "5" -> listaProductos();
                case "6" -> salir = true;
                default -> JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }

        productosRepository.Cerrar(); // Cerrar conexión cuando finaliza el programa
    }

    public static void crearProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad:"));
        BigDecimal precio = new BigDecimal(JOptionPane.showInputDialog("Ingrese el precio:"));

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCantidad(cantidad);
        producto.setPrecio(precio);

        productosRepository.crear(producto);
    }

    public static void leerProducto() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del producto:"));
        Producto producto = productosRepository.leer(id);

        if (producto != null) {
            JOptionPane.showMessageDialog(null, producto);
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }
    }

    public static void actualizarProducto() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del producto a actualizar:"));
        Producto producto = productosRepository.leer(id);

        if (producto != null) {
            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:", producto.getNombre());
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad:", producto.getCantidad()));
            BigDecimal precio = new BigDecimal(JOptionPane.showInputDialog("Ingrese el nuevo precio:", producto.getPrecio()));

            producto.setNombre(nombre);
            producto.setCantidad(cantidad);
            producto.setPrecio(precio);

            productosRepository.Actualizar(producto);
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }
    }

    public static void eliminarProducto() {
        Long id = Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del producto a eliminar:"));
        Producto producto = productosRepository.leer(id);

        if (producto != null) {
            productosRepository.eliminar(id);
            JOptionPane.showMessageDialog(null, "Producto eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }
    }

    public static void listaProductos() {
        java.util.List<Producto> productos = productosRepository.leerTodos();
        StringBuilder lista = new StringBuilder("--- Lista de Productos ---\n");
        for (Producto p : productos) {
            lista.append(p).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }
}
