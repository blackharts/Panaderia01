/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.IllegalOrphanException;
import Control.exceptions.NonexistentEntityException;
import Control.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.FamiliaProducto;
import Model.LineaProducto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author yo
 */
public class LineaProductoJpaController implements Serializable {

    public LineaProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LineaProducto lineaProducto) throws PreexistingEntityException, Exception {
        if (lineaProducto.getFamiliaProductoCollection() == null) {
            lineaProducto.setFamiliaProductoCollection(new ArrayList<FamiliaProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<FamiliaProducto> attachedFamiliaProductoCollection = new ArrayList<FamiliaProducto>();
            for (FamiliaProducto familiaProductoCollectionFamiliaProductoToAttach : lineaProducto.getFamiliaProductoCollection()) {
                familiaProductoCollectionFamiliaProductoToAttach = em.getReference(familiaProductoCollectionFamiliaProductoToAttach.getClass(), familiaProductoCollectionFamiliaProductoToAttach.getIdFamilia());
                attachedFamiliaProductoCollection.add(familiaProductoCollectionFamiliaProductoToAttach);
            }
            lineaProducto.setFamiliaProductoCollection(attachedFamiliaProductoCollection);
            em.persist(lineaProducto);
            for (FamiliaProducto familiaProductoCollectionFamiliaProducto : lineaProducto.getFamiliaProductoCollection()) {
                LineaProducto oldLineaProdOfFamiliaProductoCollectionFamiliaProducto = familiaProductoCollectionFamiliaProducto.getLineaProd();
                familiaProductoCollectionFamiliaProducto.setLineaProd(lineaProducto);
                familiaProductoCollectionFamiliaProducto = em.merge(familiaProductoCollectionFamiliaProducto);
                if (oldLineaProdOfFamiliaProductoCollectionFamiliaProducto != null) {
                    oldLineaProdOfFamiliaProductoCollectionFamiliaProducto.getFamiliaProductoCollection().remove(familiaProductoCollectionFamiliaProducto);
                    oldLineaProdOfFamiliaProductoCollectionFamiliaProducto = em.merge(oldLineaProdOfFamiliaProductoCollectionFamiliaProducto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLineaProducto(lineaProducto.getIdLinea()) != null) {
                throw new PreexistingEntityException("LineaProducto " + lineaProducto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LineaProducto lineaProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LineaProducto persistentLineaProducto = em.find(LineaProducto.class, lineaProducto.getIdLinea());
            Collection<FamiliaProducto> familiaProductoCollectionOld = persistentLineaProducto.getFamiliaProductoCollection();
            Collection<FamiliaProducto> familiaProductoCollectionNew = lineaProducto.getFamiliaProductoCollection();
            List<String> illegalOrphanMessages = null;
            for (FamiliaProducto familiaProductoCollectionOldFamiliaProducto : familiaProductoCollectionOld) {
                if (!familiaProductoCollectionNew.contains(familiaProductoCollectionOldFamiliaProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FamiliaProducto " + familiaProductoCollectionOldFamiliaProducto + " since its lineaProd field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<FamiliaProducto> attachedFamiliaProductoCollectionNew = new ArrayList<FamiliaProducto>();
            for (FamiliaProducto familiaProductoCollectionNewFamiliaProductoToAttach : familiaProductoCollectionNew) {
                familiaProductoCollectionNewFamiliaProductoToAttach = em.getReference(familiaProductoCollectionNewFamiliaProductoToAttach.getClass(), familiaProductoCollectionNewFamiliaProductoToAttach.getIdFamilia());
                attachedFamiliaProductoCollectionNew.add(familiaProductoCollectionNewFamiliaProductoToAttach);
            }
            familiaProductoCollectionNew = attachedFamiliaProductoCollectionNew;
            lineaProducto.setFamiliaProductoCollection(familiaProductoCollectionNew);
            lineaProducto = em.merge(lineaProducto);
            for (FamiliaProducto familiaProductoCollectionNewFamiliaProducto : familiaProductoCollectionNew) {
                if (!familiaProductoCollectionOld.contains(familiaProductoCollectionNewFamiliaProducto)) {
                    LineaProducto oldLineaProdOfFamiliaProductoCollectionNewFamiliaProducto = familiaProductoCollectionNewFamiliaProducto.getLineaProd();
                    familiaProductoCollectionNewFamiliaProducto.setLineaProd(lineaProducto);
                    familiaProductoCollectionNewFamiliaProducto = em.merge(familiaProductoCollectionNewFamiliaProducto);
                    if (oldLineaProdOfFamiliaProductoCollectionNewFamiliaProducto != null && !oldLineaProdOfFamiliaProductoCollectionNewFamiliaProducto.equals(lineaProducto)) {
                        oldLineaProdOfFamiliaProductoCollectionNewFamiliaProducto.getFamiliaProductoCollection().remove(familiaProductoCollectionNewFamiliaProducto);
                        oldLineaProdOfFamiliaProductoCollectionNewFamiliaProducto = em.merge(oldLineaProdOfFamiliaProductoCollectionNewFamiliaProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lineaProducto.getIdLinea();
                if (findLineaProducto(id) == null) {
                    throw new NonexistentEntityException("The lineaProducto with id " + id + " no longer exists.");
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
            LineaProducto lineaProducto;
            try {
                lineaProducto = em.getReference(LineaProducto.class, id);
                lineaProducto.getIdLinea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lineaProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<FamiliaProducto> familiaProductoCollectionOrphanCheck = lineaProducto.getFamiliaProductoCollection();
            for (FamiliaProducto familiaProductoCollectionOrphanCheckFamiliaProducto : familiaProductoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LineaProducto (" + lineaProducto + ") cannot be destroyed since the FamiliaProducto " + familiaProductoCollectionOrphanCheckFamiliaProducto + " in its familiaProductoCollection field has a non-nullable lineaProd field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(lineaProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LineaProducto> findLineaProductoEntities() {
        return findLineaProductoEntities(true, -1, -1);
    }

    public List<LineaProducto> findLineaProductoEntities(int maxResults, int firstResult) {
        return findLineaProductoEntities(false, maxResults, firstResult);
    }

    private List<LineaProducto> findLineaProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LineaProducto.class));
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

    public LineaProducto findLineaProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LineaProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getLineaProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LineaProducto> rt = cq.from(LineaProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
       public static boolean adicionar(String Nombre){
        boolean r=true;
        LineaProducto len= new LineaProducto();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("PanaderiaPU");
        EntityManager em=emf.createEntityManager();
        LineaProductoJpaController service =new LineaProductoJpaController (emf);
        em.getTransaction().begin();
        len.setNombreLinea(Nombre);
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
