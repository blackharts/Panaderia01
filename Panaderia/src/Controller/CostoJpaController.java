/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Data.Costo;
import Data.Linea;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Data.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author yo
 */
public class CostoJpaController implements Serializable {

    public CostoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static boolean agregar(int valor, Producto producto){
        boolean r = true;
        Costo costo = new Costo();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("PanaderiaPU");
        EntityManager em=emf.createEntityManager();
        CostoJpaController service =new CostoJpaController (emf);
        em.getTransaction().begin();
        costo.setCostValor(valor);
        costo.setCostProducto(producto);
        try{
            service.create(costo);
            em.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e);
            em.getTransaction().rollback();
            r=false;
            return r;
        }
        System.out.println("persisted"+costo);
        em.close();
        emf.close();
        return r;
   }
    
    public void create(Costo costo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto costoProducto = costo.getCostProducto();
            if (costoProducto != null) {
                costoProducto = em.getReference(costoProducto.getClass(), costoProducto.getProdId());
                costo.setCostProducto(costoProducto);
            }
            em.persist(costo);
            if (costoProducto != null) {
                costoProducto.getPrecioCostoCollection().add(costo);
                costoProducto = em.merge(costoProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Costo precioCosto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Costo persistentPrecioCosto = em.find(Costo.class, precioCosto.getCostId());
            Producto costProductoOld = persistentPrecioCosto.getCostProducto();
            Producto costProductoNew = precioCosto.getCostProducto();
            if (costProductoNew != null) {
                costProductoNew = em.getReference(costProductoNew.getClass(), costProductoNew.getProdId());
                precioCosto.setCostProducto(costProductoNew);
            }
            precioCosto = em.merge(precioCosto);
            if (costProductoOld != null && !costProductoOld.equals(costProductoNew)) {
                costProductoOld.getPrecioCostoCollection().remove(precioCosto);
                costProductoOld = em.merge(costProductoOld);
            }
            if (costProductoNew != null && !costProductoNew.equals(costProductoOld)) {
                costProductoNew.getPrecioCostoCollection().add(precioCosto);
                costProductoNew = em.merge(costProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = precioCosto.getCostId();
                if (findPrecioCosto(id) == null) {
                    throw new NonexistentEntityException("The precioCosto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Costo precioCosto;
            try {
                precioCosto = em.getReference(Costo.class, id);
                precioCosto.getCostId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The precioCosto with id " + id + " no longer exists.", enfe);
            }
            Producto costProducto = precioCosto.getCostProducto();
            if (costProducto != null) {
                costProducto.getPrecioCostoCollection().remove(precioCosto);
                costProducto = em.merge(costProducto);
            }
            em.remove(precioCosto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Costo> findPrecioCostoEntities() {
        return findPrecioCostoEntities(true, -1, -1);
    }

    public List<Costo> findPrecioCostoEntities(int maxResults, int firstResult) {
        return findPrecioCostoEntities(false, maxResults, firstResult);
    }

    private List<Costo> findPrecioCostoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Costo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Costo findPrecioCosto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Costo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrecioCostoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Costo> rt = cq.from(Costo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
