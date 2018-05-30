/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Model.UnidadMedida;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    public void create(UnidadMedida unidadMedida) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(unidadMedida);
            em.getTransaction().commit();
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
            unidadMedida = em.merge(unidadMedida);
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
   public static boolean agregar(String Codigo,String descripcion){
    boolean r=true;
        UnidadMedida len= new UnidadMedida();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("PanaderiaPU");
        EntityManager em=emf.createEntityManager();
        UnidadMedidaJpaController service =new UnidadMedidaJpaController (emf);
        em.getTransaction().begin();
        len.setCodigo(Codigo);
        len.setDescripcion(descripcion);
        try{
            service.create(len);
            em.getTransaction().commit();
        }catch(Exception e){
            System.out.println(e);
            em.getTransaction().rollback();
            r=false;
            return r;
        }
        System.out.println("persisted"+len);
        em.close();
        emf.close();
        return r;
   }
    
 
}
