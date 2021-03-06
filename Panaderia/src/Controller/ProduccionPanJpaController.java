/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Data.ProduccionPan;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Data.Producto;
import Data.UnidadMedida;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author KevinRoss
 */
public class ProduccionPanJpaController implements Serializable {

    public static void destroy(ProduccionPan p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProduccionPanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProduccionPanJpaController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProduccionPan produccionPan) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto ppanProducto = produccionPan.getPpanProducto();
            if (ppanProducto != null) {
                ppanProducto = em.getReference(ppanProducto.getClass(), ppanProducto.getProdId());
                produccionPan.setPpanProducto(ppanProducto);
            }
            UnidadMedida ppanUnidadMedida = produccionPan.getPpanUnidadMedida();
            if (ppanUnidadMedida != null) {
                ppanUnidadMedida = em.getReference(ppanUnidadMedida.getClass(), ppanUnidadMedida.getUnidId());
                produccionPan.setPpanUnidadMedida(ppanUnidadMedida);
            }
            em.persist(produccionPan);
            if (ppanProducto != null) {
                ppanProducto.getProduccionPanCollection().add(produccionPan);
                ppanProducto = em.merge(ppanProducto);
            }
            if (ppanUnidadMedida != null) {
                ppanUnidadMedida.getProduccionPanCollection().add(produccionPan);
                ppanUnidadMedida = em.merge(ppanUnidadMedida);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProduccionPan produccionPan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProduccionPan persistentProduccionPan = em.find(ProduccionPan.class, produccionPan.getPpanId());
            Producto ppanProductoOld = persistentProduccionPan.getPpanProducto();
            Producto ppanProductoNew = produccionPan.getPpanProducto();
            UnidadMedida ppanUnidadMedidaOld = persistentProduccionPan.getPpanUnidadMedida();
            UnidadMedida ppanUnidadMedidaNew = produccionPan.getPpanUnidadMedida();
            if (ppanProductoNew != null) {
                ppanProductoNew = em.getReference(ppanProductoNew.getClass(), ppanProductoNew.getProdId());
                produccionPan.setPpanProducto(ppanProductoNew);
            }
            if (ppanUnidadMedidaNew != null) {
                ppanUnidadMedidaNew = em.getReference(ppanUnidadMedidaNew.getClass(), ppanUnidadMedidaNew.getUnidId());
                produccionPan.setPpanUnidadMedida(ppanUnidadMedidaNew);
            }
            produccionPan = em.merge(produccionPan);
            if (ppanProductoOld != null && !ppanProductoOld.equals(ppanProductoNew)) {
                ppanProductoOld.getProduccionPanCollection().remove(produccionPan);
                ppanProductoOld = em.merge(ppanProductoOld);
            }
            if (ppanProductoNew != null && !ppanProductoNew.equals(ppanProductoOld)) {
                ppanProductoNew.getProduccionPanCollection().add(produccionPan);
                ppanProductoNew = em.merge(ppanProductoNew);
            }
            if (ppanUnidadMedidaOld != null && !ppanUnidadMedidaOld.equals(ppanUnidadMedidaNew)) {
                ppanUnidadMedidaOld.getProduccionPanCollection().remove(produccionPan);
                ppanUnidadMedidaOld = em.merge(ppanUnidadMedidaOld);
            }
            if (ppanUnidadMedidaNew != null && !ppanUnidadMedidaNew.equals(ppanUnidadMedidaOld)) {
                ppanUnidadMedidaNew.getProduccionPanCollection().add(produccionPan);
                ppanUnidadMedidaNew = em.merge(ppanUnidadMedidaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produccionPan.getPpanId();
                if (findProduccionPan(id) == null) {
                    throw new NonexistentEntityException("The produccionPan with id " + id + " no longer exists.");
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
            ProduccionPan produccionPan;
            try {
                produccionPan = em.getReference(ProduccionPan.class, id);
                produccionPan.getPpanId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produccionPan with id " + id + " no longer exists.", enfe);
            }
            Producto ppanProducto = produccionPan.getPpanProducto();
            if (ppanProducto != null) {
                ppanProducto.getProduccionPanCollection().remove(produccionPan);
                ppanProducto = em.merge(ppanProducto);
            }
            UnidadMedida ppanUnidadMedida = produccionPan.getPpanUnidadMedida();
            if (ppanUnidadMedida != null) {
                ppanUnidadMedida.getProduccionPanCollection().remove(produccionPan);
                ppanUnidadMedida = em.merge(ppanUnidadMedida);
            }
            em.remove(produccionPan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProduccionPan> findProduccionPanEntities() {
        return findProduccionPanEntities(true, -1, -1);
    }

    public List<ProduccionPan> findProduccionPanEntities(int maxResults, int firstResult) {
        return findProduccionPanEntities(false, maxResults, firstResult);
    }

    private List<ProduccionPan> findProduccionPanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProduccionPan.class));
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

    public ProduccionPan findProduccionPan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProduccionPan.class, id);
        } finally {
            em.close();
        }
    }

    public int getProduccionPanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProduccionPan> rt = cq.from(ProduccionPan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
