/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Data.Producto;
import Data.DetalleReceta;
import Data.Receta;
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

    public void create(Receta receta) {
        if (receta.getDetalleRecetaCollection() == null) {
            receta.setDetalleRecetaCollection(new ArrayList<DetalleReceta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto receProducto = receta.getReceProducto();
            if (receProducto != null) {
                receProducto = em.getReference(receProducto.getClass(), receProducto.getProdId());
                receta.setReceProducto(receProducto);
            }
            Collection<DetalleReceta> attachedDetalleRecetaCollection = new ArrayList<DetalleReceta>();
            for (DetalleReceta detalleRecetaCollectionDetalleRecetaToAttach : receta.getDetalleRecetaCollection()) {
                detalleRecetaCollectionDetalleRecetaToAttach = em.getReference(detalleRecetaCollectionDetalleRecetaToAttach.getClass(), detalleRecetaCollectionDetalleRecetaToAttach.getDrecId());
                attachedDetalleRecetaCollection.add(detalleRecetaCollectionDetalleRecetaToAttach);
            }
            receta.setDetalleRecetaCollection(attachedDetalleRecetaCollection);
            em.persist(receta);
            if (receProducto != null) {
                receProducto.getRecetaCollection().add(receta);
                receProducto = em.merge(receProducto);
            }
            for (DetalleReceta detalleRecetaCollectionDetalleReceta : receta.getDetalleRecetaCollection()) {
                Receta oldDrecRecetaOfDetalleRecetaCollectionDetalleReceta = detalleRecetaCollectionDetalleReceta.getDrecReceta();
                detalleRecetaCollectionDetalleReceta.setDrecReceta(receta);
                detalleRecetaCollectionDetalleReceta = em.merge(detalleRecetaCollectionDetalleReceta);
                if (oldDrecRecetaOfDetalleRecetaCollectionDetalleReceta != null) {
                    oldDrecRecetaOfDetalleRecetaCollectionDetalleReceta.getDetalleRecetaCollection().remove(detalleRecetaCollectionDetalleReceta);
                    oldDrecRecetaOfDetalleRecetaCollectionDetalleReceta = em.merge(oldDrecRecetaOfDetalleRecetaCollectionDetalleReceta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Receta receta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta persistentReceta = em.find(Receta.class, receta.getReceId());
            Producto receProductoOld = persistentReceta.getReceProducto();
            Producto receProductoNew = receta.getReceProducto();
            Collection<DetalleReceta> detalleRecetaCollectionOld = persistentReceta.getDetalleRecetaCollection();
            Collection<DetalleReceta> detalleRecetaCollectionNew = receta.getDetalleRecetaCollection();
            List<String> illegalOrphanMessages = null;
            for (DetalleReceta detalleRecetaCollectionOldDetalleReceta : detalleRecetaCollectionOld) {
                if (!detalleRecetaCollectionNew.contains(detalleRecetaCollectionOldDetalleReceta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleReceta " + detalleRecetaCollectionOldDetalleReceta + " since its drecReceta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (receProductoNew != null) {
                receProductoNew = em.getReference(receProductoNew.getClass(), receProductoNew.getProdId());
                receta.setReceProducto(receProductoNew);
            }
            Collection<DetalleReceta> attachedDetalleRecetaCollectionNew = new ArrayList<DetalleReceta>();
            for (DetalleReceta detalleRecetaCollectionNewDetalleRecetaToAttach : detalleRecetaCollectionNew) {
                detalleRecetaCollectionNewDetalleRecetaToAttach = em.getReference(detalleRecetaCollectionNewDetalleRecetaToAttach.getClass(), detalleRecetaCollectionNewDetalleRecetaToAttach.getDrecId());
                attachedDetalleRecetaCollectionNew.add(detalleRecetaCollectionNewDetalleRecetaToAttach);
            }
            detalleRecetaCollectionNew = attachedDetalleRecetaCollectionNew;
            receta.setDetalleRecetaCollection(detalleRecetaCollectionNew);
            receta = em.merge(receta);
            if (receProductoOld != null && !receProductoOld.equals(receProductoNew)) {
                receProductoOld.getRecetaCollection().remove(receta);
                receProductoOld = em.merge(receProductoOld);
            }
            if (receProductoNew != null && !receProductoNew.equals(receProductoOld)) {
                receProductoNew.getRecetaCollection().add(receta);
                receProductoNew = em.merge(receProductoNew);
            }
            for (DetalleReceta detalleRecetaCollectionNewDetalleReceta : detalleRecetaCollectionNew) {
                if (!detalleRecetaCollectionOld.contains(detalleRecetaCollectionNewDetalleReceta)) {
                    Receta oldDrecRecetaOfDetalleRecetaCollectionNewDetalleReceta = detalleRecetaCollectionNewDetalleReceta.getDrecReceta();
                    detalleRecetaCollectionNewDetalleReceta.setDrecReceta(receta);
                    detalleRecetaCollectionNewDetalleReceta = em.merge(detalleRecetaCollectionNewDetalleReceta);
                    if (oldDrecRecetaOfDetalleRecetaCollectionNewDetalleReceta != null && !oldDrecRecetaOfDetalleRecetaCollectionNewDetalleReceta.equals(receta)) {
                        oldDrecRecetaOfDetalleRecetaCollectionNewDetalleReceta.getDetalleRecetaCollection().remove(detalleRecetaCollectionNewDetalleReceta);
                        oldDrecRecetaOfDetalleRecetaCollectionNewDetalleReceta = em.merge(oldDrecRecetaOfDetalleRecetaCollectionNewDetalleReceta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = receta.getReceId();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta receta;
            try {
                receta = em.getReference(Receta.class, id);
                receta.getReceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The receta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DetalleReceta> detalleRecetaCollectionOrphanCheck = receta.getDetalleRecetaCollection();
            for (DetalleReceta detalleRecetaCollectionOrphanCheckDetalleReceta : detalleRecetaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Receta (" + receta + ") cannot be destroyed since the DetalleReceta " + detalleRecetaCollectionOrphanCheckDetalleReceta + " in its detalleRecetaCollection field has a non-nullable drecReceta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Producto receProducto = receta.getReceProducto();
            if (receProducto != null) {
                receProducto.getRecetaCollection().remove(receta);
                receProducto = em.merge(receProducto);
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
