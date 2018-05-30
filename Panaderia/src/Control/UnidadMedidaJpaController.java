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
import Model.UnidadMedida;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yo
 */
public class UnidadMedidaJpaController implements Serializable {

    public UnidadMedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadMedida unidadMedida) throws PreexistingEntityException, Exception {
        if (unidadMedida.getProductoCollection() == null) {
            unidadMedida.setProductoCollection(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : unidadMedida.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getIdProducto());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            unidadMedida.setProductoCollection(attachedProductoCollection);
            em.persist(unidadMedida);
            for (Producto productoCollectionProducto : unidadMedida.getProductoCollection()) {
                UnidadMedida oldMedidaProductoOfProductoCollectionProducto = productoCollectionProducto.getMedidaProducto();
                productoCollectionProducto.setMedidaProducto(unidadMedida);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldMedidaProductoOfProductoCollectionProducto != null) {
                    oldMedidaProductoOfProductoCollectionProducto.getProductoCollection().remove(productoCollectionProducto);
                    oldMedidaProductoOfProductoCollectionProducto = em.merge(oldMedidaProductoOfProductoCollectionProducto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUnidadMedida(unidadMedida.getIdMedida()) != null) {
                throw new PreexistingEntityException("UnidadMedida " + unidadMedida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadMedida unidadMedida) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadMedida persistentUnidadMedida = em.find(UnidadMedida.class, unidadMedida.getIdMedida());
            Collection<Producto> productoCollectionOld = persistentUnidadMedida.getProductoCollection();
            Collection<Producto> productoCollectionNew = unidadMedida.getProductoCollection();
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getIdProducto());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            unidadMedida.setProductoCollection(productoCollectionNew);
            unidadMedida = em.merge(unidadMedida);
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    productoCollectionOldProducto.setMedidaProducto(null);
                    productoCollectionOldProducto = em.merge(productoCollectionOldProducto);
                }
            }
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    UnidadMedida oldMedidaProductoOfProductoCollectionNewProducto = productoCollectionNewProducto.getMedidaProducto();
                    productoCollectionNewProducto.setMedidaProducto(unidadMedida);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldMedidaProductoOfProductoCollectionNewProducto != null && !oldMedidaProductoOfProductoCollectionNewProducto.equals(unidadMedida)) {
                        oldMedidaProductoOfProductoCollectionNewProducto.getProductoCollection().remove(productoCollectionNewProducto);
                        oldMedidaProductoOfProductoCollectionNewProducto = em.merge(oldMedidaProductoOfProductoCollectionNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = unidadMedida.getIdMedida();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadMedida unidadMedida;
            try {
                unidadMedida = em.getReference(UnidadMedida.class, id);
                unidadMedida.getIdMedida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadMedida with id " + id + " no longer exists.", enfe);
            }
            Collection<Producto> productoCollection = unidadMedida.getProductoCollection();
            for (Producto productoCollectionProducto : productoCollection) {
                productoCollectionProducto.setMedidaProducto(null);
                productoCollectionProducto = em.merge(productoCollectionProducto);
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
