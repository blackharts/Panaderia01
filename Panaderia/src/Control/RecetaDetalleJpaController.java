/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Producto;
import Model.Receta;
import Model.RecetaDetalle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yo
 */
public class RecetaDetalleJpaController implements Serializable {

    public RecetaDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RecetaDetalle recetaDetalle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoId = recetaDetalle.getProductoId();
            if (productoId != null) {
                productoId = em.getReference(productoId.getClass(), productoId.getIdProducto());
                recetaDetalle.setProductoId(productoId);
            }
            Receta recetaId = recetaDetalle.getRecetaId();
            if (recetaId != null) {
                recetaId = em.getReference(recetaId.getClass(), recetaId.getIdReceta());
                recetaDetalle.setRecetaId(recetaId);
            }
            em.persist(recetaDetalle);
            if (productoId != null) {
                productoId.getRecetaDetalleCollection().add(recetaDetalle);
                productoId = em.merge(productoId);
            }
            if (recetaId != null) {
                recetaId.getRecetaDetalleCollection().add(recetaDetalle);
                recetaId = em.merge(recetaId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRecetaDetalle(recetaDetalle.getCantidad()) != null) {
                throw new PreexistingEntityException("RecetaDetalle " + recetaDetalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RecetaDetalle recetaDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecetaDetalle persistentRecetaDetalle = em.find(RecetaDetalle.class, recetaDetalle.getCantidad());
            Producto productoIdOld = persistentRecetaDetalle.getProductoId();
            Producto productoIdNew = recetaDetalle.getProductoId();
            Receta recetaIdOld = persistentRecetaDetalle.getRecetaId();
            Receta recetaIdNew = recetaDetalle.getRecetaId();
            if (productoIdNew != null) {
                productoIdNew = em.getReference(productoIdNew.getClass(), productoIdNew.getIdProducto());
                recetaDetalle.setProductoId(productoIdNew);
            }
            if (recetaIdNew != null) {
                recetaIdNew = em.getReference(recetaIdNew.getClass(), recetaIdNew.getIdReceta());
                recetaDetalle.setRecetaId(recetaIdNew);
            }
            recetaDetalle = em.merge(recetaDetalle);
            if (productoIdOld != null && !productoIdOld.equals(productoIdNew)) {
                productoIdOld.getRecetaDetalleCollection().remove(recetaDetalle);
                productoIdOld = em.merge(productoIdOld);
            }
            if (productoIdNew != null && !productoIdNew.equals(productoIdOld)) {
                productoIdNew.getRecetaDetalleCollection().add(recetaDetalle);
                productoIdNew = em.merge(productoIdNew);
            }
            if (recetaIdOld != null && !recetaIdOld.equals(recetaIdNew)) {
                recetaIdOld.getRecetaDetalleCollection().remove(recetaDetalle);
                recetaIdOld = em.merge(recetaIdOld);
            }
            if (recetaIdNew != null && !recetaIdNew.equals(recetaIdOld)) {
                recetaIdNew.getRecetaDetalleCollection().add(recetaDetalle);
                recetaIdNew = em.merge(recetaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Float id = recetaDetalle.getCantidad();
                if (findRecetaDetalle(id) == null) {
                    throw new NonexistentEntityException("The recetaDetalle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Float id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecetaDetalle recetaDetalle;
            try {
                recetaDetalle = em.getReference(RecetaDetalle.class, id);
                recetaDetalle.getCantidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recetaDetalle with id " + id + " no longer exists.", enfe);
            }
            Producto productoId = recetaDetalle.getProductoId();
            if (productoId != null) {
                productoId.getRecetaDetalleCollection().remove(recetaDetalle);
                productoId = em.merge(productoId);
            }
            Receta recetaId = recetaDetalle.getRecetaId();
            if (recetaId != null) {
                recetaId.getRecetaDetalleCollection().remove(recetaDetalle);
                recetaId = em.merge(recetaId);
            }
            em.remove(recetaDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RecetaDetalle> findRecetaDetalleEntities() {
        return findRecetaDetalleEntities(true, -1, -1);
    }

    public List<RecetaDetalle> findRecetaDetalleEntities(int maxResults, int firstResult) {
        return findRecetaDetalleEntities(false, maxResults, firstResult);
    }

    private List<RecetaDetalle> findRecetaDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RecetaDetalle.class));
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

    public RecetaDetalle findRecetaDetalle(Float id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RecetaDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecetaDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RecetaDetalle> rt = cq.from(RecetaDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
