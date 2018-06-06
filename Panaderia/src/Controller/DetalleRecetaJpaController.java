/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Data.DetalleReceta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Data.Producto;
import Data.Receta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yo
 */
public class DetalleRecetaJpaController implements Serializable {

    public DetalleRecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleReceta detalleReceta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto drecProducto = detalleReceta.getDrecProducto();
            if (drecProducto != null) {
                drecProducto = em.getReference(drecProducto.getClass(), drecProducto.getProdId());
                detalleReceta.setDrecProducto(drecProducto);
            }
            Receta drecReceta = detalleReceta.getDrecReceta();
            if (drecReceta != null) {
                drecReceta = em.getReference(drecReceta.getClass(), drecReceta.getReceId());
                detalleReceta.setDrecReceta(drecReceta);
            }
            em.persist(detalleReceta);
            if (drecProducto != null) {
                drecProducto.getDetalleRecetaCollection().add(detalleReceta);
                drecProducto = em.merge(drecProducto);
            }
            if (drecReceta != null) {
                drecReceta.getDetalleRecetaCollection().add(detalleReceta);
                drecReceta = em.merge(drecReceta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleReceta detalleReceta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleReceta persistentDetalleReceta = em.find(DetalleReceta.class, detalleReceta.getDrecId());
            Producto drecProductoOld = persistentDetalleReceta.getDrecProducto();
            Producto drecProductoNew = detalleReceta.getDrecProducto();
            Receta drecRecetaOld = persistentDetalleReceta.getDrecReceta();
            Receta drecRecetaNew = detalleReceta.getDrecReceta();
            if (drecProductoNew != null) {
                drecProductoNew = em.getReference(drecProductoNew.getClass(), drecProductoNew.getProdId());
                detalleReceta.setDrecProducto(drecProductoNew);
            }
            if (drecRecetaNew != null) {
                drecRecetaNew = em.getReference(drecRecetaNew.getClass(), drecRecetaNew.getReceId());
                detalleReceta.setDrecReceta(drecRecetaNew);
            }
            detalleReceta = em.merge(detalleReceta);
            if (drecProductoOld != null && !drecProductoOld.equals(drecProductoNew)) {
                drecProductoOld.getDetalleRecetaCollection().remove(detalleReceta);
                drecProductoOld = em.merge(drecProductoOld);
            }
            if (drecProductoNew != null && !drecProductoNew.equals(drecProductoOld)) {
                drecProductoNew.getDetalleRecetaCollection().add(detalleReceta);
                drecProductoNew = em.merge(drecProductoNew);
            }
            if (drecRecetaOld != null && !drecRecetaOld.equals(drecRecetaNew)) {
                drecRecetaOld.getDetalleRecetaCollection().remove(detalleReceta);
                drecRecetaOld = em.merge(drecRecetaOld);
            }
            if (drecRecetaNew != null && !drecRecetaNew.equals(drecRecetaOld)) {
                drecRecetaNew.getDetalleRecetaCollection().add(detalleReceta);
                drecRecetaNew = em.merge(drecRecetaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleReceta.getDrecId();
                if (findDetalleReceta(id) == null) {
                    throw new NonexistentEntityException("The detalleReceta with id " + id + " no longer exists.");
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
            DetalleReceta detalleReceta;
            try {
                detalleReceta = em.getReference(DetalleReceta.class, id);
                detalleReceta.getDrecId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleReceta with id " + id + " no longer exists.", enfe);
            }
            Producto drecProducto = detalleReceta.getDrecProducto();
            if (drecProducto != null) {
                drecProducto.getDetalleRecetaCollection().remove(detalleReceta);
                drecProducto = em.merge(drecProducto);
            }
            Receta drecReceta = detalleReceta.getDrecReceta();
            if (drecReceta != null) {
                drecReceta.getDetalleRecetaCollection().remove(detalleReceta);
                drecReceta = em.merge(drecReceta);
            }
            em.remove(detalleReceta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleReceta> findDetalleRecetaEntities() {
        return findDetalleRecetaEntities(true, -1, -1);
    }

    public List<DetalleReceta> findDetalleRecetaEntities(int maxResults, int firstResult) {
        return findDetalleRecetaEntities(false, maxResults, firstResult);
    }

    private List<DetalleReceta> findDetalleRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleReceta.class));
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

    public DetalleReceta findDetalleReceta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleReceta.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleReceta> rt = cq.from(DetalleReceta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
