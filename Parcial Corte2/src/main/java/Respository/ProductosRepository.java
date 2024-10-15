package Respository;

import entity.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductosRepository {

    private final String fileName = "datos.dat";

    public void crear(Producto producto) {
        List<Producto> productos = leerTodos();
        productos.add(producto);
        guardarProductos(productos);
    }

    public Producto leer(Long id) {
        List<Producto> productos = leerTodos();
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public List<Producto> leerTodos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Producto>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void Actualizar(Producto productoActualizado) {
        List<Producto> productos = leerTodos();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == productoActualizado.getId()) {
                productos.set(i, productoActualizado);
                break;
            }
        }
        guardarProductos(productos);
    }

    public void eliminar(Long id) {
        List<Producto> productos = leerTodos();
        productos.removeIf(producto -> producto.getId() == id);
        guardarProductos(productos);
    }

    private void guardarProductos(List<Producto> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
