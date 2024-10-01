package Respository;

import entity.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.*;

public class ProductosRepository {

    private final EntityManagerFactory emf;

    public ProductosRepository() {
        emf = Persistence.createEntityManagerFactory("ProductosPIU");
    }
    public void crear(Producto producto) {
        EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
    }finally {
        em.close();
    }
    }
    public Producto leer(Long id) {
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Producto.class,id);
        }finally {
            em.close();
        }
    }
    public List<Producto>leerTodos(){
        try(EntityManager em = emf.createEntityManager()){
            return em.createQuery("from Producto").getResultList();
        }
    }
    public void Actualizar(Producto producto) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Producto producto =em.find(Producto.class,id);
            if(producto != null){
                em.remove(producto);
            }
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
    public void Cerrar(){
        emf.close();
    }

}
