/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Control.exceptions.NonexistentEntityException;
import Control.exceptions.PreexistingEntityException;
import Model.FamiliaProducto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.LineaProducto;
import Model.Producto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yo
 */
public class FamiliaProductoJpaController implements Serializable {

    public FamiliaProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FamiliaProducto familiaProducto) throws PreexistingEntityException, Exception {
        if (familiaProducto.getProductoCollection() == null) {
            familiaProducto.setProductoCollection(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LineaProducto lineaProd = familiaProducto.getLineaProd();
            if (lineaProd != null) {
                lineaProd = em.getReference(lineaProd.getClass(), lineaProd.getIdLinea());
                familiaProducto.setLineaProd(lineaProd);
            }
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : familiaProducto.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getIdProducto());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            familiaProducto.setProductoCollection(attachedProductoCollection);
            em.persist(familiaProducto);
            if (lineaProd != null) {
                lineaProd.getFamiliaProductoCollection().add(familiaProducto);
                lineaProd = em.merge(lineaProd);
            }
            for (Producto productoCollectionProducto : familiaProducto.getProductoCollection()) {
                FamiliaProducto oldFamiliaProdOfProductoCollectionProducto = productoCollectionProducto.getFamiliaProd();
                productoCollectionProducto.setFamiliaProd(familiaProducto);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldFamiliaProdOfProductoCollectionProducto != null) {
                    oldFamiliaProdOfProductoCollectionProducto.getProductoCollection().remove(productoCollectionProducto);
                    oldFamiliaProdOfProductoCollectionProducto = em.merge(oldFamiliaProdOfProductoCollectionProducto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFamiliaProducto(familiaProducto.getIdFamilia()) != null) {
                throw new PreexistingEntityException("FamiliaProducto " + familiaProducto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FamiliaProducto familiaProducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FamiliaProducto persistentFamiliaProducto = em.find(FamiliaProducto.class, familiaProducto.getIdFamilia());
            LineaProducto lineaProdOld = persistentFamiliaProducto.getLineaProd();
            LineaProducto lineaProdNew = familiaProducto.getLineaProd();
            Collection<Producto> productoCollectionOld = persistentFamiliaProducto.getProductoCollection();
            Collection<Producto> productoCollectionNew = familiaProducto.getProductoCollection();
            if (lineaProdNew != null) {
                lineaProdNew = em.getReference(lineaProdNew.getClass(), lineaProdNew.getIdLinea());
                familiaProducto.setLineaProd(lineaProdNew);
            }
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getIdProducto());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            familiaProducto.setProductoCollection(productoCollectionNew);
            familiaProducto = em.merge(familiaProducto);
            if (lineaProdOld != null && !lineaProdOld.equals(lineaProdNew)) {
                lineaProdOld.getFamiliaProductoCollection().remove(familiaProducto);
                lineaProdOld = em.merge(lineaProdOld);
            }
            if (lineaProdNew != null && !lineaProdNew.equals(lineaProdOld)) {
                lineaProdNew.getFamiliaProductoCollection().add(familiaProducto);
                lineaProdNew = em.merge(lineaProdNew);
            }
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    productoCollectionOldProducto.setFamiliaProd(null);
                    productoCollectionOldProducto = em.merge(productoCollectionOldProducto);
                }
            }
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    FamiliaProducto oldFamiliaProdOfProductoCollectionNewProducto = productoCollectionNewProducto.getFamiliaProd();
                    productoCollectionNewProducto.setFamiliaProd(familiaProducto);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldFamiliaProdOfProductoCollectionNewProducto != null && !oldFamiliaProdOfProductoCollectionNewProducto.equals(familiaProducto)) {
                        oldFamiliaProdOfProductoCollectionNewProducto.getProductoCollection().remove(productoCollectionNewProducto);
                        oldFamiliaProdOfProductoCollectionNewProducto = em.merge(oldFamiliaProdOfProductoCollectionNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = familiaProducto.getIdFamilia();
                if (findFamiliaProducto(id) == null) {
                    throw new NonexistentEntityException("The familiaProducto with id " + id + " no longer exists.");
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
            FamiliaProducto familiaProducto;
            try {
                familiaProducto = em.getReference(FamiliaProducto.class, id);
                familiaProducto.getIdFamilia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The familiaProducto with id " + id + " no longer exists.", enfe);
            }
            LineaProducto lineaProd = familiaProducto.getLineaProd();
            if (lineaProd != null) {
                lineaProd.getFamiliaProductoCollection().remove(familiaProducto);
                lineaProd = em.merge(lineaProd);
            }
            Collection<Producto> productoCollection = familiaProducto.getProductoCollection();
            for (Producto productoCollectionProducto : productoCollection) {
                productoCollectionProducto.setFamiliaProd(null);
                productoCollectionProducto = em.merge(productoCollectionProducto);
            }
            em.remove(familiaProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FamiliaProducto> findFamiliaProductoEntities() {
        return findFamiliaProductoEntities(true, -1, -1);
    }

    public List<FamiliaProducto> findFamiliaProductoEntities(int maxResults, int firstResult) {
        return findFamiliaProductoEntities(false, maxResults, firstResult);
    }

    private List<FamiliaProducto> findFamiliaProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FamiliaProducto.class));
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

    public FamiliaProducto findFamiliaProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FamiliaProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getFamiliaProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FamiliaProducto> rt = cq.from(FamiliaProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
