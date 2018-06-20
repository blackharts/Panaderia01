/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Data.PrecioVenta;
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
 * @author KevinRoss
 */
public class PrecioVentaJpaController implements Serializable {

    public PrecioVentaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PanaderiaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrecioVenta precioVenta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto precvProducto = precioVenta.getPrecvProducto();
            if (precvProducto != null) {
                precvProducto = em.getReference(precvProducto.getClass(), precvProducto.getProdId());
                precioVenta.setPrecvProducto(precvProducto);
            }
            em.persist(precioVenta);
            if (precvProducto != null) {
                precvProducto.getPrecioVentaCollection().add(precioVenta);
                precvProducto = em.merge(precvProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrecioVenta precioVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrecioVenta persistentPrecioVenta = em.find(PrecioVenta.class, precioVenta.getPrecvId());
            Producto precvProductoOld = persistentPrecioVenta.getPrecvProducto();
            Producto precvProductoNew = precioVenta.getPrecvProducto();
            if (precvProductoNew != null) {
                precvProductoNew = em.getReference(precvProductoNew.getClass(), precvProductoNew.getProdId());
                precioVenta.setPrecvProducto(precvProductoNew);
            }
            precioVenta = em.merge(precioVenta);
            if (precvProductoOld != null && !precvProductoOld.equals(precvProductoNew)) {
                precvProductoOld.getPrecioVentaCollection().remove(precioVenta);
                precvProductoOld = em.merge(precvProductoOld);
            }
            if (precvProductoNew != null && !precvProductoNew.equals(precvProductoOld)) {
                precvProductoNew.getPrecioVentaCollection().add(precioVenta);
                precvProductoNew = em.merge(precvProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = precioVenta.getPrecvId();
                if (findPrecioVenta(id) == null) {
                    throw new NonexistentEntityException("The precioVenta with id " + id + " no longer exists.");
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
            PrecioVenta precioVenta;
            try {
                precioVenta = em.getReference(PrecioVenta.class, id);
                precioVenta.getPrecvId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The precioVenta with id " + id + " no longer exists.", enfe);
            }
            Producto precvProducto = precioVenta.getPrecvProducto();
            if (precvProducto != null) {
                precvProducto.getPrecioVentaCollection().remove(precioVenta);
                precvProducto = em.merge(precvProducto);
            }
            em.remove(precioVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrecioVenta> findPrecioVentaEntities() {
        return findPrecioVentaEntities(true, -1, -1);
    }

    public List<PrecioVenta> findPrecioVentaEntities(int maxResults, int firstResult) {
        return findPrecioVentaEntities(false, maxResults, firstResult);
    }

    private List<PrecioVenta> findPrecioVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrecioVenta.class));
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

    public PrecioVenta findPrecioVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrecioVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrecioVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrecioVenta> rt = cq.from(PrecioVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
