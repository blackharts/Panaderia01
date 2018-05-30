/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Control.exceptions.PreexistingEntityException;
import Model.Costos;
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
public class CostosJpaController implements Serializable {

    public CostosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Costos costos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoCosto = costos.getProductoCosto();
            if (productoCosto != null) {
                productoCosto = em.getReference(productoCosto.getClass(), productoCosto.getIdProducto());
                costos.setProductoCosto(productoCosto);
            }
            em.persist(costos);
            if (productoCosto != null) {
                productoCosto.getCostosCollection().add(costos);
                productoCosto = em.merge(productoCosto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCostos(costos.getIdCosto()) != null) {
                throw new PreexistingEntityException("Costos " + costos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Costos costos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Costos persistentCostos = em.find(Costos.class, costos.getIdCosto());
            Producto productoCostoOld = persistentCostos.getProductoCosto();
            Producto productoCostoNew = costos.getProductoCosto();
            if (productoCostoNew != null) {
                productoCostoNew = em.getReference(productoCostoNew.getClass(), productoCostoNew.getIdProducto());
                costos.setProductoCosto(productoCostoNew);
            }
            costos = em.merge(costos);
            if (productoCostoOld != null && !productoCostoOld.equals(productoCostoNew)) {
                productoCostoOld.getCostosCollection().remove(costos);
                productoCostoOld = em.merge(productoCostoOld);
            }
            if (productoCostoNew != null && !productoCostoNew.equals(productoCostoOld)) {
                productoCostoNew.getCostosCollection().add(costos);
                productoCostoNew = em.merge(productoCostoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = costos.getIdCosto();
                if (findCostos(id) == null) {
                    throw new NonexistentEntityException("The costos with id " + id + " no longer exists.");
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
            Costos costos;
            try {
                costos = em.getReference(Costos.class, id);
                costos.getIdCosto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The costos with id " + id + " no longer exists.", enfe);
            }
            Producto productoCosto = costos.getProductoCosto();
            if (productoCosto != null) {
                productoCosto.getCostosCollection().remove(costos);
                productoCosto = em.merge(productoCosto);
            }
            em.remove(costos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Costos> findCostosEntities() {
        return findCostosEntities(true, -1, -1);
    }

    public List<Costos> findCostosEntities(int maxResults, int firstResult) {
        return findCostosEntities(false, maxResults, firstResult);
    }

    private List<Costos> findCostosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Costos.class));
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

    public Costos findCostos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Costos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCostosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Costos> rt = cq.from(Costos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
