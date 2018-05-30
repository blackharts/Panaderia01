/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Control.exceptions.PreexistingEntityException;
import Model.PrecioVenta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yo
 */
public class PrecioVentaJpaController implements Serializable {

    public PrecioVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrecioVenta precioVenta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto procutoVenta = precioVenta.getProcutoVenta();
            if (procutoVenta != null) {
                procutoVenta = em.getReference(procutoVenta.getClass(), procutoVenta.getIdProducto());
                precioVenta.setProcutoVenta(procutoVenta);
            }
            em.persist(precioVenta);
            if (procutoVenta != null) {
                procutoVenta.getPrecioVentaCollection().add(precioVenta);
                procutoVenta = em.merge(procutoVenta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrecioVenta(precioVenta.getIdVenta()) != null) {
                throw new PreexistingEntityException("PrecioVenta " + precioVenta + " already exists.", ex);
            }
            throw ex;
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
            PrecioVenta persistentPrecioVenta = em.find(PrecioVenta.class, precioVenta.getIdVenta());
            Producto procutoVentaOld = persistentPrecioVenta.getProcutoVenta();
            Producto procutoVentaNew = precioVenta.getProcutoVenta();
            if (procutoVentaNew != null) {
                procutoVentaNew = em.getReference(procutoVentaNew.getClass(), procutoVentaNew.getIdProducto());
                precioVenta.setProcutoVenta(procutoVentaNew);
            }
            precioVenta = em.merge(precioVenta);
            if (procutoVentaOld != null && !procutoVentaOld.equals(procutoVentaNew)) {
                procutoVentaOld.getPrecioVentaCollection().remove(precioVenta);
                procutoVentaOld = em.merge(procutoVentaOld);
            }
            if (procutoVentaNew != null && !procutoVentaNew.equals(procutoVentaOld)) {
                procutoVentaNew.getPrecioVentaCollection().add(precioVenta);
                procutoVentaNew = em.merge(procutoVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = precioVenta.getIdVenta();
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
                precioVenta.getIdVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The precioVenta with id " + id + " no longer exists.", enfe);
            }
            Producto procutoVenta = precioVenta.getProcutoVenta();
            if (procutoVenta != null) {
                procutoVenta.getPrecioVentaCollection().remove(precioVenta);
                procutoVenta = em.merge(procutoVenta);
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
