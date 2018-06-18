/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Data.Familia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Data.Linea;
import Data.Producto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author KevinRoss
 */
public class FamiliaJpaController implements Serializable {

    public FamiliaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Familia familia) {
        if (familia.getProductoCollection() == null) {
            familia.setProductoCollection(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Linea famiLinea = familia.getFamiLinea();
            if (famiLinea != null) {
                famiLinea = em.getReference(famiLinea.getClass(), famiLinea.getLineId());
                familia.setFamiLinea(famiLinea);
            }
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : familia.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getProdId());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            familia.setProductoCollection(attachedProductoCollection);
            em.persist(familia);
            if (famiLinea != null) {
                famiLinea.getFamiliaCollection().add(familia);
                famiLinea = em.merge(famiLinea);
            }
            for (Producto productoCollectionProducto : familia.getProductoCollection()) {
                Familia oldProdFamiliaOfProductoCollectionProducto = productoCollectionProducto.getProdFamilia();
                productoCollectionProducto.setProdFamilia(familia);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldProdFamiliaOfProductoCollectionProducto != null) {
                    oldProdFamiliaOfProductoCollectionProducto.getProductoCollection().remove(productoCollectionProducto);
                    oldProdFamiliaOfProductoCollectionProducto = em.merge(oldProdFamiliaOfProductoCollectionProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Familia familia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Familia persistentFamilia = em.find(Familia.class, familia.getFamiId());
            Linea famiLineaOld = persistentFamilia.getFamiLinea();
            Linea famiLineaNew = familia.getFamiLinea();
            Collection<Producto> productoCollectionOld = persistentFamilia.getProductoCollection();
            Collection<Producto> productoCollectionNew = familia.getProductoCollection();
            List<String> illegalOrphanMessages = null;
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoCollectionOldProducto + " since its prodFamilia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (famiLineaNew != null) {
                famiLineaNew = em.getReference(famiLineaNew.getClass(), famiLineaNew.getLineId());
                familia.setFamiLinea(famiLineaNew);
            }
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getProdId());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            familia.setProductoCollection(productoCollectionNew);
            familia = em.merge(familia);
            if (famiLineaOld != null && !famiLineaOld.equals(famiLineaNew)) {
                famiLineaOld.getFamiliaCollection().remove(familia);
                famiLineaOld = em.merge(famiLineaOld);
            }
            if (famiLineaNew != null && !famiLineaNew.equals(famiLineaOld)) {
                famiLineaNew.getFamiliaCollection().add(familia);
                famiLineaNew = em.merge(famiLineaNew);
            }
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    Familia oldProdFamiliaOfProductoCollectionNewProducto = productoCollectionNewProducto.getProdFamilia();
                    productoCollectionNewProducto.setProdFamilia(familia);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldProdFamiliaOfProductoCollectionNewProducto != null && !oldProdFamiliaOfProductoCollectionNewProducto.equals(familia)) {
                        oldProdFamiliaOfProductoCollectionNewProducto.getProductoCollection().remove(productoCollectionNewProducto);
                        oldProdFamiliaOfProductoCollectionNewProducto = em.merge(oldProdFamiliaOfProductoCollectionNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = familia.getFamiId();
                if (findFamilia(id) == null) {
                    throw new NonexistentEntityException("The familia with id " + id + " no longer exists.");
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
            Familia familia;
            try {
                familia = em.getReference(Familia.class, id);
                familia.getFamiId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The familia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Producto> productoCollectionOrphanCheck = familia.getProductoCollection();
            for (Producto productoCollectionOrphanCheckProducto : productoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Familia (" + familia + ") cannot be destroyed since the Producto " + productoCollectionOrphanCheckProducto + " in its productoCollection field has a non-nullable prodFamilia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Linea famiLinea = familia.getFamiLinea();
            if (famiLinea != null) {
                famiLinea.getFamiliaCollection().remove(familia);
                famiLinea = em.merge(famiLinea);
            }
            em.remove(familia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Familia> findFamiliaEntities() {
        return findFamiliaEntities(true, -1, -1);
    }

    public List<Familia> findFamiliaEntities(int maxResults, int firstResult) {
        return findFamiliaEntities(false, maxResults, firstResult);
    }

    private List<Familia> findFamiliaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Familia.class));
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

    public Familia findFamilia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Familia.class, id);
        } finally {
            em.close();
        }
    }

    public int getFamiliaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Familia> rt = cq.from(Familia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
