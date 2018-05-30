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
import Model.FamiliaProducto;
import Model.Costos;
import java.util.ArrayList;
import java.util.Collection;
import Model.Receta;
import Model.PrecioVenta;
import Model.Producto;
import Model.RecetaDetalle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yo
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) throws PreexistingEntityException, Exception {
        if (producto.getCostosCollection() == null) {
            producto.setCostosCollection(new ArrayList<Costos>());
        }
        if (producto.getRecetaCollection() == null) {
            producto.setRecetaCollection(new ArrayList<Receta>());
        }
        if (producto.getPrecioVentaCollection() == null) {
            producto.setPrecioVentaCollection(new ArrayList<PrecioVenta>());
        }
        if (producto.getRecetaDetalleCollection() == null) {
            producto.setRecetaDetalleCollection(new ArrayList<RecetaDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FamiliaProducto familiaProd = producto.getFamiliaProd();
            if (familiaProd != null) {
                familiaProd = em.getReference(familiaProd.getClass(), familiaProd.getIdFamilia());
                producto.setFamiliaProd(familiaProd);
            }
            Collection<Costos> attachedCostosCollection = new ArrayList<Costos>();
            for (Costos costosCollectionCostosToAttach : producto.getCostosCollection()) {
                costosCollectionCostosToAttach = em.getReference(costosCollectionCostosToAttach.getClass(), costosCollectionCostosToAttach.getIdCosto());
                attachedCostosCollection.add(costosCollectionCostosToAttach);
            }
            producto.setCostosCollection(attachedCostosCollection);
            Collection<Receta> attachedRecetaCollection = new ArrayList<Receta>();
            for (Receta recetaCollectionRecetaToAttach : producto.getRecetaCollection()) {
                recetaCollectionRecetaToAttach = em.getReference(recetaCollectionRecetaToAttach.getClass(), recetaCollectionRecetaToAttach.getIdReceta());
                attachedRecetaCollection.add(recetaCollectionRecetaToAttach);
            }
            producto.setRecetaCollection(attachedRecetaCollection);
            Collection<PrecioVenta> attachedPrecioVentaCollection = new ArrayList<PrecioVenta>();
            for (PrecioVenta precioVentaCollectionPrecioVentaToAttach : producto.getPrecioVentaCollection()) {
                precioVentaCollectionPrecioVentaToAttach = em.getReference(precioVentaCollectionPrecioVentaToAttach.getClass(), precioVentaCollectionPrecioVentaToAttach.getIdVenta());
                attachedPrecioVentaCollection.add(precioVentaCollectionPrecioVentaToAttach);
            }
            producto.setPrecioVentaCollection(attachedPrecioVentaCollection);
            Collection<RecetaDetalle> attachedRecetaDetalleCollection = new ArrayList<RecetaDetalle>();
            for (RecetaDetalle recetaDetalleCollectionRecetaDetalleToAttach : producto.getRecetaDetalleCollection()) {
                recetaDetalleCollectionRecetaDetalleToAttach = em.getReference(recetaDetalleCollectionRecetaDetalleToAttach.getClass(), recetaDetalleCollectionRecetaDetalleToAttach.getCantidad());
                attachedRecetaDetalleCollection.add(recetaDetalleCollectionRecetaDetalleToAttach);
            }
            producto.setRecetaDetalleCollection(attachedRecetaDetalleCollection);
            em.persist(producto);
            if (familiaProd != null) {
                familiaProd.getProductoCollection().add(producto);
                familiaProd = em.merge(familiaProd);
            }
            for (Costos costosCollectionCostos : producto.getCostosCollection()) {
                Producto oldProductoCostoOfCostosCollectionCostos = costosCollectionCostos.getProductoCosto();
                costosCollectionCostos.setProductoCosto(producto);
                costosCollectionCostos = em.merge(costosCollectionCostos);
                if (oldProductoCostoOfCostosCollectionCostos != null) {
                    oldProductoCostoOfCostosCollectionCostos.getCostosCollection().remove(costosCollectionCostos);
                    oldProductoCostoOfCostosCollectionCostos = em.merge(oldProductoCostoOfCostosCollectionCostos);
                }
            }
            for (Receta recetaCollectionReceta : producto.getRecetaCollection()) {
                Producto oldProductoIdOfRecetaCollectionReceta = recetaCollectionReceta.getProductoId();
                recetaCollectionReceta.setProductoId(producto);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
                if (oldProductoIdOfRecetaCollectionReceta != null) {
                    oldProductoIdOfRecetaCollectionReceta.getRecetaCollection().remove(recetaCollectionReceta);
                    oldProductoIdOfRecetaCollectionReceta = em.merge(oldProductoIdOfRecetaCollectionReceta);
                }
            }
            for (PrecioVenta precioVentaCollectionPrecioVenta : producto.getPrecioVentaCollection()) {
                Producto oldProcutoVentaOfPrecioVentaCollectionPrecioVenta = precioVentaCollectionPrecioVenta.getProcutoVenta();
                precioVentaCollectionPrecioVenta.setProcutoVenta(producto);
                precioVentaCollectionPrecioVenta = em.merge(precioVentaCollectionPrecioVenta);
                if (oldProcutoVentaOfPrecioVentaCollectionPrecioVenta != null) {
                    oldProcutoVentaOfPrecioVentaCollectionPrecioVenta.getPrecioVentaCollection().remove(precioVentaCollectionPrecioVenta);
                    oldProcutoVentaOfPrecioVentaCollectionPrecioVenta = em.merge(oldProcutoVentaOfPrecioVentaCollectionPrecioVenta);
                }
            }
            for (RecetaDetalle recetaDetalleCollectionRecetaDetalle : producto.getRecetaDetalleCollection()) {
                Producto oldProductoIdOfRecetaDetalleCollectionRecetaDetalle = recetaDetalleCollectionRecetaDetalle.getProductoId();
                recetaDetalleCollectionRecetaDetalle.setProductoId(producto);
                recetaDetalleCollectionRecetaDetalle = em.merge(recetaDetalleCollectionRecetaDetalle);
                if (oldProductoIdOfRecetaDetalleCollectionRecetaDetalle != null) {
                    oldProductoIdOfRecetaDetalleCollectionRecetaDetalle.getRecetaDetalleCollection().remove(recetaDetalleCollectionRecetaDetalle);
                    oldProductoIdOfRecetaDetalleCollectionRecetaDetalle = em.merge(oldProductoIdOfRecetaDetalleCollectionRecetaDetalle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProducto(producto.getIdProducto()) != null) {
                throw new PreexistingEntityException("Producto " + producto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            FamiliaProducto familiaProdOld = persistentProducto.getFamiliaProd();
            FamiliaProducto familiaProdNew = producto.getFamiliaProd();
            Collection<Costos> costosCollectionOld = persistentProducto.getCostosCollection();
            Collection<Costos> costosCollectionNew = producto.getCostosCollection();
            Collection<Receta> recetaCollectionOld = persistentProducto.getRecetaCollection();
            Collection<Receta> recetaCollectionNew = producto.getRecetaCollection();
            Collection<PrecioVenta> precioVentaCollectionOld = persistentProducto.getPrecioVentaCollection();
            Collection<PrecioVenta> precioVentaCollectionNew = producto.getPrecioVentaCollection();
            Collection<RecetaDetalle> recetaDetalleCollectionOld = persistentProducto.getRecetaDetalleCollection();
            Collection<RecetaDetalle> recetaDetalleCollectionNew = producto.getRecetaDetalleCollection();
            if (familiaProdNew != null) {
                familiaProdNew = em.getReference(familiaProdNew.getClass(), familiaProdNew.getIdFamilia());
                producto.setFamiliaProd(familiaProdNew);
            }
            Collection<Costos> attachedCostosCollectionNew = new ArrayList<Costos>();
            for (Costos costosCollectionNewCostosToAttach : costosCollectionNew) {
                costosCollectionNewCostosToAttach = em.getReference(costosCollectionNewCostosToAttach.getClass(), costosCollectionNewCostosToAttach.getIdCosto());
                attachedCostosCollectionNew.add(costosCollectionNewCostosToAttach);
            }
            costosCollectionNew = attachedCostosCollectionNew;
            producto.setCostosCollection(costosCollectionNew);
            Collection<Receta> attachedRecetaCollectionNew = new ArrayList<Receta>();
            for (Receta recetaCollectionNewRecetaToAttach : recetaCollectionNew) {
                recetaCollectionNewRecetaToAttach = em.getReference(recetaCollectionNewRecetaToAttach.getClass(), recetaCollectionNewRecetaToAttach.getIdReceta());
                attachedRecetaCollectionNew.add(recetaCollectionNewRecetaToAttach);
            }
            recetaCollectionNew = attachedRecetaCollectionNew;
            producto.setRecetaCollection(recetaCollectionNew);
            Collection<PrecioVenta> attachedPrecioVentaCollectionNew = new ArrayList<PrecioVenta>();
            for (PrecioVenta precioVentaCollectionNewPrecioVentaToAttach : precioVentaCollectionNew) {
                precioVentaCollectionNewPrecioVentaToAttach = em.getReference(precioVentaCollectionNewPrecioVentaToAttach.getClass(), precioVentaCollectionNewPrecioVentaToAttach.getIdVenta());
                attachedPrecioVentaCollectionNew.add(precioVentaCollectionNewPrecioVentaToAttach);
            }
            precioVentaCollectionNew = attachedPrecioVentaCollectionNew;
            producto.setPrecioVentaCollection(precioVentaCollectionNew);
            Collection<RecetaDetalle> attachedRecetaDetalleCollectionNew = new ArrayList<RecetaDetalle>();
            for (RecetaDetalle recetaDetalleCollectionNewRecetaDetalleToAttach : recetaDetalleCollectionNew) {
                recetaDetalleCollectionNewRecetaDetalleToAttach = em.getReference(recetaDetalleCollectionNewRecetaDetalleToAttach.getClass(), recetaDetalleCollectionNewRecetaDetalleToAttach.getCantidad());
                attachedRecetaDetalleCollectionNew.add(recetaDetalleCollectionNewRecetaDetalleToAttach);
            }
            recetaDetalleCollectionNew = attachedRecetaDetalleCollectionNew;
            producto.setRecetaDetalleCollection(recetaDetalleCollectionNew);
            producto = em.merge(producto);
            if (familiaProdOld != null && !familiaProdOld.equals(familiaProdNew)) {
                familiaProdOld.getProductoCollection().remove(producto);
                familiaProdOld = em.merge(familiaProdOld);
            }
            if (familiaProdNew != null && !familiaProdNew.equals(familiaProdOld)) {
                familiaProdNew.getProductoCollection().add(producto);
                familiaProdNew = em.merge(familiaProdNew);
            }
            for (Costos costosCollectionOldCostos : costosCollectionOld) {
                if (!costosCollectionNew.contains(costosCollectionOldCostos)) {
                    costosCollectionOldCostos.setProductoCosto(null);
                    costosCollectionOldCostos = em.merge(costosCollectionOldCostos);
                }
            }
            for (Costos costosCollectionNewCostos : costosCollectionNew) {
                if (!costosCollectionOld.contains(costosCollectionNewCostos)) {
                    Producto oldProductoCostoOfCostosCollectionNewCostos = costosCollectionNewCostos.getProductoCosto();
                    costosCollectionNewCostos.setProductoCosto(producto);
                    costosCollectionNewCostos = em.merge(costosCollectionNewCostos);
                    if (oldProductoCostoOfCostosCollectionNewCostos != null && !oldProductoCostoOfCostosCollectionNewCostos.equals(producto)) {
                        oldProductoCostoOfCostosCollectionNewCostos.getCostosCollection().remove(costosCollectionNewCostos);
                        oldProductoCostoOfCostosCollectionNewCostos = em.merge(oldProductoCostoOfCostosCollectionNewCostos);
                    }
                }
            }
            for (Receta recetaCollectionOldReceta : recetaCollectionOld) {
                if (!recetaCollectionNew.contains(recetaCollectionOldReceta)) {
                    recetaCollectionOldReceta.setProductoId(null);
                    recetaCollectionOldReceta = em.merge(recetaCollectionOldReceta);
                }
            }
            for (Receta recetaCollectionNewReceta : recetaCollectionNew) {
                if (!recetaCollectionOld.contains(recetaCollectionNewReceta)) {
                    Producto oldProductoIdOfRecetaCollectionNewReceta = recetaCollectionNewReceta.getProductoId();
                    recetaCollectionNewReceta.setProductoId(producto);
                    recetaCollectionNewReceta = em.merge(recetaCollectionNewReceta);
                    if (oldProductoIdOfRecetaCollectionNewReceta != null && !oldProductoIdOfRecetaCollectionNewReceta.equals(producto)) {
                        oldProductoIdOfRecetaCollectionNewReceta.getRecetaCollection().remove(recetaCollectionNewReceta);
                        oldProductoIdOfRecetaCollectionNewReceta = em.merge(oldProductoIdOfRecetaCollectionNewReceta);
                    }
                }
            }
            for (PrecioVenta precioVentaCollectionOldPrecioVenta : precioVentaCollectionOld) {
                if (!precioVentaCollectionNew.contains(precioVentaCollectionOldPrecioVenta)) {
                    precioVentaCollectionOldPrecioVenta.setProcutoVenta(null);
                    precioVentaCollectionOldPrecioVenta = em.merge(precioVentaCollectionOldPrecioVenta);
                }
            }
            for (PrecioVenta precioVentaCollectionNewPrecioVenta : precioVentaCollectionNew) {
                if (!precioVentaCollectionOld.contains(precioVentaCollectionNewPrecioVenta)) {
                    Producto oldProcutoVentaOfPrecioVentaCollectionNewPrecioVenta = precioVentaCollectionNewPrecioVenta.getProcutoVenta();
                    precioVentaCollectionNewPrecioVenta.setProcutoVenta(producto);
                    precioVentaCollectionNewPrecioVenta = em.merge(precioVentaCollectionNewPrecioVenta);
                    if (oldProcutoVentaOfPrecioVentaCollectionNewPrecioVenta != null && !oldProcutoVentaOfPrecioVentaCollectionNewPrecioVenta.equals(producto)) {
                        oldProcutoVentaOfPrecioVentaCollectionNewPrecioVenta.getPrecioVentaCollection().remove(precioVentaCollectionNewPrecioVenta);
                        oldProcutoVentaOfPrecioVentaCollectionNewPrecioVenta = em.merge(oldProcutoVentaOfPrecioVentaCollectionNewPrecioVenta);
                    }
                }
            }
            for (RecetaDetalle recetaDetalleCollectionOldRecetaDetalle : recetaDetalleCollectionOld) {
                if (!recetaDetalleCollectionNew.contains(recetaDetalleCollectionOldRecetaDetalle)) {
                    recetaDetalleCollectionOldRecetaDetalle.setProductoId(null);
                    recetaDetalleCollectionOldRecetaDetalle = em.merge(recetaDetalleCollectionOldRecetaDetalle);
                }
            }
            for (RecetaDetalle recetaDetalleCollectionNewRecetaDetalle : recetaDetalleCollectionNew) {
                if (!recetaDetalleCollectionOld.contains(recetaDetalleCollectionNewRecetaDetalle)) {
                    Producto oldProductoIdOfRecetaDetalleCollectionNewRecetaDetalle = recetaDetalleCollectionNewRecetaDetalle.getProductoId();
                    recetaDetalleCollectionNewRecetaDetalle.setProductoId(producto);
                    recetaDetalleCollectionNewRecetaDetalle = em.merge(recetaDetalleCollectionNewRecetaDetalle);
                    if (oldProductoIdOfRecetaDetalleCollectionNewRecetaDetalle != null && !oldProductoIdOfRecetaDetalleCollectionNewRecetaDetalle.equals(producto)) {
                        oldProductoIdOfRecetaDetalleCollectionNewRecetaDetalle.getRecetaDetalleCollection().remove(recetaDetalleCollectionNewRecetaDetalle);
                        oldProductoIdOfRecetaDetalleCollectionNewRecetaDetalle = em.merge(oldProductoIdOfRecetaDetalleCollectionNewRecetaDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            FamiliaProducto familiaProd = producto.getFamiliaProd();
            if (familiaProd != null) {
                familiaProd.getProductoCollection().remove(producto);
                familiaProd = em.merge(familiaProd);
            }
            Collection<Costos> costosCollection = producto.getCostosCollection();
            for (Costos costosCollectionCostos : costosCollection) {
                costosCollectionCostos.setProductoCosto(null);
                costosCollectionCostos = em.merge(costosCollectionCostos);
            }
            Collection<Receta> recetaCollection = producto.getRecetaCollection();
            for (Receta recetaCollectionReceta : recetaCollection) {
                recetaCollectionReceta.setProductoId(null);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            Collection<PrecioVenta> precioVentaCollection = producto.getPrecioVentaCollection();
            for (PrecioVenta precioVentaCollectionPrecioVenta : precioVentaCollection) {
                precioVentaCollectionPrecioVenta.setProcutoVenta(null);
                precioVentaCollectionPrecioVenta = em.merge(precioVentaCollectionPrecioVenta);
            }
            Collection<RecetaDetalle> recetaDetalleCollection = producto.getRecetaDetalleCollection();
            for (RecetaDetalle recetaDetalleCollectionRecetaDetalle : recetaDetalleCollection) {
                recetaDetalleCollectionRecetaDetalle.setProductoId(null);
                recetaDetalleCollectionRecetaDetalle = em.merge(recetaDetalleCollectionRecetaDetalle);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
