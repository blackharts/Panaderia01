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
import Data.UnidadMedida;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author luisa
 */
public class UnidadMedidaJpaController implements Serializable {

    public UnidadMedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadMedida unidadMedida) {
        if (unidadMedida.getProductoCollection() == null) {
            unidadMedida.setProductoCollection(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : unidadMedida.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getProdId());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            unidadMedida.setProductoCollection(attachedProductoCollection);
            em.persist(unidadMedida);
            for (Producto productoCollectionProducto : unidadMedida.getProductoCollection()) {
                UnidadMedida oldProdUnidadmedidaOfProductoCollectionProducto = productoCollectionProducto.getProdUnidadmedida();
                productoCollectionProducto.setProdUnidadmedida(unidadMedida);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldProdUnidadmedidaOfProductoCollectionProducto != null) {
                    oldProdUnidadmedidaOfProductoCollectionProducto.getProductoCollection().remove(productoCollectionProducto);
                    oldProdUnidadmedidaOfProductoCollectionProducto = em.merge(oldProdUnidadmedidaOfProductoCollectionProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadMedida unidadMedida) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadMedida persistentUnidadMedida = em.find(UnidadMedida.class, unidadMedida.getUnidId());
            Collection<Producto> productoCollectionOld = persistentUnidadMedida.getProductoCollection();
            Collection<Producto> productoCollectionNew = unidadMedida.getProductoCollection();
            List<String> illegalOrphanMessages = null;
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoCollectionOldProducto + " since its prodUnidadmedida field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getProdId());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            unidadMedida.setProductoCollection(productoCollectionNew);
            unidadMedida = em.merge(unidadMedida);
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    UnidadMedida oldProdUnidadmedidaOfProductoCollectionNewProducto = productoCollectionNewProducto.getProdUnidadmedida();
                    productoCollectionNewProducto.setProdUnidadmedida(unidadMedida);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldProdUnidadmedidaOfProductoCollectionNewProducto != null && !oldProdUnidadmedidaOfProductoCollectionNewProducto.equals(unidadMedida)) {
                        oldProdUnidadmedidaOfProductoCollectionNewProducto.getProductoCollection().remove(productoCollectionNewProducto);
                        oldProdUnidadmedidaOfProductoCollectionNewProducto = em.merge(oldProdUnidadmedidaOfProductoCollectionNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = unidadMedida.getUnidId();
                if (findUnidadMedida(id) == null) {
                    throw new NonexistentEntityException("The unidadMedida with id " + id + " no longer exists.");
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
            UnidadMedida unidadMedida;
            try {
                unidadMedida = em.getReference(UnidadMedida.class, id);
                unidadMedida.getUnidId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadMedida with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Producto> productoCollectionOrphanCheck = unidadMedida.getProductoCollection();
            for (Producto productoCollectionOrphanCheckProducto : productoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UnidadMedida (" + unidadMedida + ") cannot be destroyed since the Producto " + productoCollectionOrphanCheckProducto + " in its productoCollection field has a non-nullable prodUnidadmedida field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(unidadMedida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadMedida> findUnidadMedidaEntities() {
        return findUnidadMedidaEntities(true, -1, -1);
    }

    public List<UnidadMedida> findUnidadMedidaEntities(int maxResults, int firstResult) {
        return findUnidadMedidaEntities(false, maxResults, firstResult);
    }

    private List<UnidadMedida> findUnidadMedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadMedida.class));
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

    public UnidadMedida findUnidadMedida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadMedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadMedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadMedida> rt = cq.from(UnidadMedida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
