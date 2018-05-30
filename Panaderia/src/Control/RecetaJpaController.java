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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yo
 */
public class RecetaJpaController implements Serializable {

    public RecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Receta receta) throws PreexistingEntityException, Exception {
        if (receta.getRecetaDetalleCollection() == null) {
            receta.setRecetaDetalleCollection(new ArrayList<RecetaDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoId = receta.getProductoId();
            if (productoId != null) {
                productoId = em.getReference(productoId.getClass(), productoId.getIdProducto());
                receta.setProductoId(productoId);
            }
            Collection<RecetaDetalle> attachedRecetaDetalleCollection = new ArrayList<RecetaDetalle>();
            for (RecetaDetalle recetaDetalleCollectionRecetaDetalleToAttach : receta.getRecetaDetalleCollection()) {
                recetaDetalleCollectionRecetaDetalleToAttach = em.getReference(recetaDetalleCollectionRecetaDetalleToAttach.getClass(), recetaDetalleCollectionRecetaDetalleToAttach.getCantidad());
                attachedRecetaDetalleCollection.add(recetaDetalleCollectionRecetaDetalleToAttach);
            }
            receta.setRecetaDetalleCollection(attachedRecetaDetalleCollection);
            em.persist(receta);
            if (productoId != null) {
                productoId.getRecetaCollection().add(receta);
                productoId = em.merge(productoId);
            }
            for (RecetaDetalle recetaDetalleCollectionRecetaDetalle : receta.getRecetaDetalleCollection()) {
                Receta oldRecetaIdOfRecetaDetalleCollectionRecetaDetalle = recetaDetalleCollectionRecetaDetalle.getRecetaId();
                recetaDetalleCollectionRecetaDetalle.setRecetaId(receta);
                recetaDetalleCollectionRecetaDetalle = em.merge(recetaDetalleCollectionRecetaDetalle);
                if (oldRecetaIdOfRecetaDetalleCollectionRecetaDetalle != null) {
                    oldRecetaIdOfRecetaDetalleCollectionRecetaDetalle.getRecetaDetalleCollection().remove(recetaDetalleCollectionRecetaDetalle);
                    oldRecetaIdOfRecetaDetalleCollectionRecetaDetalle = em.merge(oldRecetaIdOfRecetaDetalleCollectionRecetaDetalle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReceta(receta.getIdReceta()) != null) {
                throw new PreexistingEntityException("Receta " + receta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Receta receta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta persistentReceta = em.find(Receta.class, receta.getIdReceta());
            Producto productoIdOld = persistentReceta.getProductoId();
            Producto productoIdNew = receta.getProductoId();
            Collection<RecetaDetalle> recetaDetalleCollectionOld = persistentReceta.getRecetaDetalleCollection();
            Collection<RecetaDetalle> recetaDetalleCollectionNew = receta.getRecetaDetalleCollection();
            if (productoIdNew != null) {
                productoIdNew = em.getReference(productoIdNew.getClass(), productoIdNew.getIdProducto());
                receta.setProductoId(productoIdNew);
            }
            Collection<RecetaDetalle> attachedRecetaDetalleCollectionNew = new ArrayList<RecetaDetalle>();
            for (RecetaDetalle recetaDetalleCollectionNewRecetaDetalleToAttach : recetaDetalleCollectionNew) {
                recetaDetalleCollectionNewRecetaDetalleToAttach = em.getReference(recetaDetalleCollectionNewRecetaDetalleToAttach.getClass(), recetaDetalleCollectionNewRecetaDetalleToAttach.getCantidad());
                attachedRecetaDetalleCollectionNew.add(recetaDetalleCollectionNewRecetaDetalleToAttach);
            }
            recetaDetalleCollectionNew = attachedRecetaDetalleCollectionNew;
            receta.setRecetaDetalleCollection(recetaDetalleCollectionNew);
            receta = em.merge(receta);
            if (productoIdOld != null && !productoIdOld.equals(productoIdNew)) {
                productoIdOld.getRecetaCollection().remove(receta);
                productoIdOld = em.merge(productoIdOld);
            }
            if (productoIdNew != null && !productoIdNew.equals(productoIdOld)) {
                productoIdNew.getRecetaCollection().add(receta);
                productoIdNew = em.merge(productoIdNew);
            }
            for (RecetaDetalle recetaDetalleCollectionOldRecetaDetalle : recetaDetalleCollectionOld) {
                if (!recetaDetalleCollectionNew.contains(recetaDetalleCollectionOldRecetaDetalle)) {
                    recetaDetalleCollectionOldRecetaDetalle.setRecetaId(null);
                    recetaDetalleCollectionOldRecetaDetalle = em.merge(recetaDetalleCollectionOldRecetaDetalle);
                }
            }
            for (RecetaDetalle recetaDetalleCollectionNewRecetaDetalle : recetaDetalleCollectionNew) {
                if (!recetaDetalleCollectionOld.contains(recetaDetalleCollectionNewRecetaDetalle)) {
                    Receta oldRecetaIdOfRecetaDetalleCollectionNewRecetaDetalle = recetaDetalleCollectionNewRecetaDetalle.getRecetaId();
                    recetaDetalleCollectionNewRecetaDetalle.setRecetaId(receta);
                    recetaDetalleCollectionNewRecetaDetalle = em.merge(recetaDetalleCollectionNewRecetaDetalle);
                    if (oldRecetaIdOfRecetaDetalleCollectionNewRecetaDetalle != null && !oldRecetaIdOfRecetaDetalleCollectionNewRecetaDetalle.equals(receta)) {
                        oldRecetaIdOfRecetaDetalleCollectionNewRecetaDetalle.getRecetaDetalleCollection().remove(recetaDetalleCollectionNewRecetaDetalle);
                        oldRecetaIdOfRecetaDetalleCollectionNewRecetaDetalle = em.merge(oldRecetaIdOfRecetaDetalleCollectionNewRecetaDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = receta.getIdReceta();
                if (findReceta(id) == null) {
                    throw new NonexistentEntityException("The receta with id " + id + " no longer exists.");
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
            Receta receta;
            try {
                receta = em.getReference(Receta.class, id);
                receta.getIdReceta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The receta with id " + id + " no longer exists.", enfe);
            }
            Producto productoId = receta.getProductoId();
            if (productoId != null) {
                productoId.getRecetaCollection().remove(receta);
                productoId = em.merge(productoId);
            }
            Collection<RecetaDetalle> recetaDetalleCollection = receta.getRecetaDetalleCollection();
            for (RecetaDetalle recetaDetalleCollectionRecetaDetalle : recetaDetalleCollection) {
                recetaDetalleCollectionRecetaDetalle.setRecetaId(null);
                recetaDetalleCollectionRecetaDetalle = em.merge(recetaDetalleCollectionRecetaDetalle);
            }
            em.remove(receta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Receta> findRecetaEntities() {
        return findRecetaEntities(true, -1, -1);
    }

    public List<Receta> findRecetaEntities(int maxResults, int firstResult) {
        return findRecetaEntities(false, maxResults, firstResult);
    }

    private List<Receta> findRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Receta.class));
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

    public Receta findReceta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Receta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Receta> rt = cq.from(Receta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
