/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Data.PrecioCosto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Data.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author luisa
 */
public class PrecioCostoJpaController implements Serializable {

    public PrecioCostoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrecioCosto precioCosto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto costProducto = precioCosto.getCostProducto();
            if (costProducto != null) {
                costProducto = em.getReference(costProducto.getClass(), costProducto.getProdId());
                precioCosto.setCostProducto(costProducto);
            }
            em.persist(precioCosto);
            if (costProducto != null) {
                costProducto.getPrecioCostoCollection().add(precioCosto);
                costProducto = em.merge(costProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrecioCosto precioCosto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PrecioCosto persistentPrecioCosto = em.find(PrecioCosto.class, precioCosto.getCostId());
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
            PrecioCosto precioCosto;
            try {
                precioCosto = em.getReference(PrecioCosto.class, id);
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

    public List<PrecioCosto> findPrecioCostoEntities() {
        return findPrecioCostoEntities(true, -1, -1);
    }

    public List<PrecioCosto> findPrecioCostoEntities(int maxResults, int firstResult) {
        return findPrecioCostoEntities(false, maxResults, firstResult);
    }

    private List<PrecioCosto> findPrecioCostoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrecioCosto.class));
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

    public PrecioCosto findPrecioCosto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrecioCosto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrecioCostoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrecioCosto> rt = cq.from(PrecioCosto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
