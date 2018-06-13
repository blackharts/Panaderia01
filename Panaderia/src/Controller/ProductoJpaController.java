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
import Data.Familia;
import Data.Linea;
import Data.UnidadMedida;
import Data.ProduccionPan;
import java.util.ArrayList;
import java.util.Collection;
import Data.Receta;
import Data.DetalleReceta;
import Data.PrecioVenta;
import Data.PrecioCosto;
import Data.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author luisa
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getProduccionPanCollection() == null) {
            producto.setProduccionPanCollection(new ArrayList<ProduccionPan>());
        }
        if (producto.getRecetaCollection() == null) {
            producto.setRecetaCollection(new ArrayList<Receta>());
        }
        if (producto.getDetalleRecetaCollection() == null) {
            producto.setDetalleRecetaCollection(new ArrayList<DetalleReceta>());
        }
        if (producto.getPrecioVentaCollection() == null) {
            producto.setPrecioVentaCollection(new ArrayList<PrecioVenta>());
        }
        if (producto.getPrecioCostoCollection() == null) {
            producto.setPrecioCostoCollection(new ArrayList<PrecioCosto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Familia prodFamilia = producto.getProdFamilia();
            if (prodFamilia != null) {
                prodFamilia = em.getReference(prodFamilia.getClass(), prodFamilia.getFamiId());
                producto.setProdFamilia(prodFamilia);
            }
            Linea prodLinea = producto.getProdLinea();
            if (prodLinea != null) {
                prodLinea = em.getReference(prodLinea.getClass(), prodLinea.getLineId());
                producto.setProdLinea(prodLinea);
            }
            UnidadMedida prodUnidadmedida = producto.getProdUnidadmedida();
            if (prodUnidadmedida != null) {
                prodUnidadmedida = em.getReference(prodUnidadmedida.getClass(), prodUnidadmedida.getUnidId());
                producto.setProdUnidadmedida(prodUnidadmedida);
            }
            Collection<ProduccionPan> attachedProduccionPanCollection = new ArrayList<ProduccionPan>();
            for (ProduccionPan produccionPanCollectionProduccionPanToAttach : producto.getProduccionPanCollection()) {
                produccionPanCollectionProduccionPanToAttach = em.getReference(produccionPanCollectionProduccionPanToAttach.getClass(), produccionPanCollectionProduccionPanToAttach.getPpanId());
                attachedProduccionPanCollection.add(produccionPanCollectionProduccionPanToAttach);
            }
            producto.setProduccionPanCollection(attachedProduccionPanCollection);
            Collection<Receta> attachedRecetaCollection = new ArrayList<Receta>();
            for (Receta recetaCollectionRecetaToAttach : producto.getRecetaCollection()) {
                recetaCollectionRecetaToAttach = em.getReference(recetaCollectionRecetaToAttach.getClass(), recetaCollectionRecetaToAttach.getReceId());
                attachedRecetaCollection.add(recetaCollectionRecetaToAttach);
            }
            producto.setRecetaCollection(attachedRecetaCollection);
            Collection<DetalleReceta> attachedDetalleRecetaCollection = new ArrayList<DetalleReceta>();
            for (DetalleReceta detalleRecetaCollectionDetalleRecetaToAttach : producto.getDetalleRecetaCollection()) {
                detalleRecetaCollectionDetalleRecetaToAttach = em.getReference(detalleRecetaCollectionDetalleRecetaToAttach.getClass(), detalleRecetaCollectionDetalleRecetaToAttach.getDrecId());
                attachedDetalleRecetaCollection.add(detalleRecetaCollectionDetalleRecetaToAttach);
            }
            producto.setDetalleRecetaCollection(attachedDetalleRecetaCollection);
            Collection<PrecioVenta> attachedPrecioVentaCollection = new ArrayList<PrecioVenta>();
            for (PrecioVenta precioVentaCollectionPrecioVentaToAttach : producto.getPrecioVentaCollection()) {
                precioVentaCollectionPrecioVentaToAttach = em.getReference(precioVentaCollectionPrecioVentaToAttach.getClass(), precioVentaCollectionPrecioVentaToAttach.getPrecvId());
                attachedPrecioVentaCollection.add(precioVentaCollectionPrecioVentaToAttach);
            }
            producto.setPrecioVentaCollection(attachedPrecioVentaCollection);
            Collection<PrecioCosto> attachedPrecioCostoCollection = new ArrayList<PrecioCosto>();
            for (PrecioCosto precioCostoCollectionPrecioCostoToAttach : producto.getPrecioCostoCollection()) {
                precioCostoCollectionPrecioCostoToAttach = em.getReference(precioCostoCollectionPrecioCostoToAttach.getClass(), precioCostoCollectionPrecioCostoToAttach.getCostId());
                attachedPrecioCostoCollection.add(precioCostoCollectionPrecioCostoToAttach);
            }
            producto.setPrecioCostoCollection(attachedPrecioCostoCollection);
            em.persist(producto);
            if (prodFamilia != null) {
                prodFamilia.getProductoCollection().add(producto);
                prodFamilia = em.merge(prodFamilia);
            }
            if (prodLinea != null) {
                prodLinea.getProductoCollection().add(producto);
                prodLinea = em.merge(prodLinea);
            }
            if (prodUnidadmedida != null) {
                prodUnidadmedida.getProductoCollection().add(producto);
                prodUnidadmedida = em.merge(prodUnidadmedida);
            }
            for (ProduccionPan produccionPanCollectionProduccionPan : producto.getProduccionPanCollection()) {
                Producto oldPpanProductoOfProduccionPanCollectionProduccionPan = produccionPanCollectionProduccionPan.getPpanProducto();
                produccionPanCollectionProduccionPan.setPpanProducto(producto);
                produccionPanCollectionProduccionPan = em.merge(produccionPanCollectionProduccionPan);
                if (oldPpanProductoOfProduccionPanCollectionProduccionPan != null) {
                    oldPpanProductoOfProduccionPanCollectionProduccionPan.getProduccionPanCollection().remove(produccionPanCollectionProduccionPan);
                    oldPpanProductoOfProduccionPanCollectionProduccionPan = em.merge(oldPpanProductoOfProduccionPanCollectionProduccionPan);
                }
            }
            for (Receta recetaCollectionReceta : producto.getRecetaCollection()) {
                Producto oldReceProductoOfRecetaCollectionReceta = recetaCollectionReceta.getReceProducto();
                recetaCollectionReceta.setReceProducto(producto);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
                if (oldReceProductoOfRecetaCollectionReceta != null) {
                    oldReceProductoOfRecetaCollectionReceta.getRecetaCollection().remove(recetaCollectionReceta);
                    oldReceProductoOfRecetaCollectionReceta = em.merge(oldReceProductoOfRecetaCollectionReceta);
                }
            }
            for (DetalleReceta detalleRecetaCollectionDetalleReceta : producto.getDetalleRecetaCollection()) {
                Producto oldDrecProductoOfDetalleRecetaCollectionDetalleReceta = detalleRecetaCollectionDetalleReceta.getDrecProducto();
                detalleRecetaCollectionDetalleReceta.setDrecProducto(producto);
                detalleRecetaCollectionDetalleReceta = em.merge(detalleRecetaCollectionDetalleReceta);
                if (oldDrecProductoOfDetalleRecetaCollectionDetalleReceta != null) {
                    oldDrecProductoOfDetalleRecetaCollectionDetalleReceta.getDetalleRecetaCollection().remove(detalleRecetaCollectionDetalleReceta);
                    oldDrecProductoOfDetalleRecetaCollectionDetalleReceta = em.merge(oldDrecProductoOfDetalleRecetaCollectionDetalleReceta);
                }
            }
            for (PrecioVenta precioVentaCollectionPrecioVenta : producto.getPrecioVentaCollection()) {
                Producto oldPrecvProductoOfPrecioVentaCollectionPrecioVenta = precioVentaCollectionPrecioVenta.getPrecvProducto();
                precioVentaCollectionPrecioVenta.setPrecvProducto(producto);
                precioVentaCollectionPrecioVenta = em.merge(precioVentaCollectionPrecioVenta);
                if (oldPrecvProductoOfPrecioVentaCollectionPrecioVenta != null) {
                    oldPrecvProductoOfPrecioVentaCollectionPrecioVenta.getPrecioVentaCollection().remove(precioVentaCollectionPrecioVenta);
                    oldPrecvProductoOfPrecioVentaCollectionPrecioVenta = em.merge(oldPrecvProductoOfPrecioVentaCollectionPrecioVenta);
                }
            }
            for (PrecioCosto precioCostoCollectionPrecioCosto : producto.getPrecioCostoCollection()) {
                Producto oldCostProductoOfPrecioCostoCollectionPrecioCosto = precioCostoCollectionPrecioCosto.getCostProducto();
                precioCostoCollectionPrecioCosto.setCostProducto(producto);
                precioCostoCollectionPrecioCosto = em.merge(precioCostoCollectionPrecioCosto);
                if (oldCostProductoOfPrecioCostoCollectionPrecioCosto != null) {
                    oldCostProductoOfPrecioCostoCollectionPrecioCosto.getPrecioCostoCollection().remove(precioCostoCollectionPrecioCosto);
                    oldCostProductoOfPrecioCostoCollectionPrecioCosto = em.merge(oldCostProductoOfPrecioCostoCollectionPrecioCosto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getProdId());
            Familia prodFamiliaOld = persistentProducto.getProdFamilia();
            Familia prodFamiliaNew = producto.getProdFamilia();
            Linea prodLineaOld = persistentProducto.getProdLinea();
            Linea prodLineaNew = producto.getProdLinea();
            UnidadMedida prodUnidadmedidaOld = persistentProducto.getProdUnidadmedida();
            UnidadMedida prodUnidadmedidaNew = producto.getProdUnidadmedida();
            Collection<ProduccionPan> produccionPanCollectionOld = persistentProducto.getProduccionPanCollection();
            Collection<ProduccionPan> produccionPanCollectionNew = producto.getProduccionPanCollection();
            Collection<Receta> recetaCollectionOld = persistentProducto.getRecetaCollection();
            Collection<Receta> recetaCollectionNew = producto.getRecetaCollection();
            Collection<DetalleReceta> detalleRecetaCollectionOld = persistentProducto.getDetalleRecetaCollection();
            Collection<DetalleReceta> detalleRecetaCollectionNew = producto.getDetalleRecetaCollection();
            Collection<PrecioVenta> precioVentaCollectionOld = persistentProducto.getPrecioVentaCollection();
            Collection<PrecioVenta> precioVentaCollectionNew = producto.getPrecioVentaCollection();
            Collection<PrecioCosto> precioCostoCollectionOld = persistentProducto.getPrecioCostoCollection();
            Collection<PrecioCosto> precioCostoCollectionNew = producto.getPrecioCostoCollection();
            List<String> illegalOrphanMessages = null;
            for (ProduccionPan produccionPanCollectionOldProduccionPan : produccionPanCollectionOld) {
                if (!produccionPanCollectionNew.contains(produccionPanCollectionOldProduccionPan)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProduccionPan " + produccionPanCollectionOldProduccionPan + " since its ppanProducto field is not nullable.");
                }
            }
            for (Receta recetaCollectionOldReceta : recetaCollectionOld) {
                if (!recetaCollectionNew.contains(recetaCollectionOldReceta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Receta " + recetaCollectionOldReceta + " since its receProducto field is not nullable.");
                }
            }
            for (DetalleReceta detalleRecetaCollectionOldDetalleReceta : detalleRecetaCollectionOld) {
                if (!detalleRecetaCollectionNew.contains(detalleRecetaCollectionOldDetalleReceta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleReceta " + detalleRecetaCollectionOldDetalleReceta + " since its drecProducto field is not nullable.");
                }
            }
            for (PrecioVenta precioVentaCollectionOldPrecioVenta : precioVentaCollectionOld) {
                if (!precioVentaCollectionNew.contains(precioVentaCollectionOldPrecioVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrecioVenta " + precioVentaCollectionOldPrecioVenta + " since its precvProducto field is not nullable.");
                }
            }
            for (PrecioCosto precioCostoCollectionOldPrecioCosto : precioCostoCollectionOld) {
                if (!precioCostoCollectionNew.contains(precioCostoCollectionOldPrecioCosto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrecioCosto " + precioCostoCollectionOldPrecioCosto + " since its costProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (prodFamiliaNew != null) {
                prodFamiliaNew = em.getReference(prodFamiliaNew.getClass(), prodFamiliaNew.getFamiId());
                producto.setProdFamilia(prodFamiliaNew);
            }
            if (prodLineaNew != null) {
                prodLineaNew = em.getReference(prodLineaNew.getClass(), prodLineaNew.getLineId());
                producto.setProdLinea(prodLineaNew);
            }
            if (prodUnidadmedidaNew != null) {
                prodUnidadmedidaNew = em.getReference(prodUnidadmedidaNew.getClass(), prodUnidadmedidaNew.getUnidId());
                producto.setProdUnidadmedida(prodUnidadmedidaNew);
            }
            Collection<ProduccionPan> attachedProduccionPanCollectionNew = new ArrayList<ProduccionPan>();
            for (ProduccionPan produccionPanCollectionNewProduccionPanToAttach : produccionPanCollectionNew) {
                produccionPanCollectionNewProduccionPanToAttach = em.getReference(produccionPanCollectionNewProduccionPanToAttach.getClass(), produccionPanCollectionNewProduccionPanToAttach.getPpanId());
                attachedProduccionPanCollectionNew.add(produccionPanCollectionNewProduccionPanToAttach);
            }
            produccionPanCollectionNew = attachedProduccionPanCollectionNew;
            producto.setProduccionPanCollection(produccionPanCollectionNew);
            Collection<Receta> attachedRecetaCollectionNew = new ArrayList<Receta>();
            for (Receta recetaCollectionNewRecetaToAttach : recetaCollectionNew) {
                recetaCollectionNewRecetaToAttach = em.getReference(recetaCollectionNewRecetaToAttach.getClass(), recetaCollectionNewRecetaToAttach.getReceId());
                attachedRecetaCollectionNew.add(recetaCollectionNewRecetaToAttach);
            }
            recetaCollectionNew = attachedRecetaCollectionNew;
            producto.setRecetaCollection(recetaCollectionNew);
            Collection<DetalleReceta> attachedDetalleRecetaCollectionNew = new ArrayList<DetalleReceta>();
            for (DetalleReceta detalleRecetaCollectionNewDetalleRecetaToAttach : detalleRecetaCollectionNew) {
                detalleRecetaCollectionNewDetalleRecetaToAttach = em.getReference(detalleRecetaCollectionNewDetalleRecetaToAttach.getClass(), detalleRecetaCollectionNewDetalleRecetaToAttach.getDrecId());
                attachedDetalleRecetaCollectionNew.add(detalleRecetaCollectionNewDetalleRecetaToAttach);
            }
            detalleRecetaCollectionNew = attachedDetalleRecetaCollectionNew;
            producto.setDetalleRecetaCollection(detalleRecetaCollectionNew);
            Collection<PrecioVenta> attachedPrecioVentaCollectionNew = new ArrayList<PrecioVenta>();
            for (PrecioVenta precioVentaCollectionNewPrecioVentaToAttach : precioVentaCollectionNew) {
                precioVentaCollectionNewPrecioVentaToAttach = em.getReference(precioVentaCollectionNewPrecioVentaToAttach.getClass(), precioVentaCollectionNewPrecioVentaToAttach.getPrecvId());
                attachedPrecioVentaCollectionNew.add(precioVentaCollectionNewPrecioVentaToAttach);
            }
            precioVentaCollectionNew = attachedPrecioVentaCollectionNew;
            producto.setPrecioVentaCollection(precioVentaCollectionNew);
            Collection<PrecioCosto> attachedPrecioCostoCollectionNew = new ArrayList<PrecioCosto>();
            for (PrecioCosto precioCostoCollectionNewPrecioCostoToAttach : precioCostoCollectionNew) {
                precioCostoCollectionNewPrecioCostoToAttach = em.getReference(precioCostoCollectionNewPrecioCostoToAttach.getClass(), precioCostoCollectionNewPrecioCostoToAttach.getCostId());
                attachedPrecioCostoCollectionNew.add(precioCostoCollectionNewPrecioCostoToAttach);
            }
            precioCostoCollectionNew = attachedPrecioCostoCollectionNew;
            producto.setPrecioCostoCollection(precioCostoCollectionNew);
            producto = em.merge(producto);
            if (prodFamiliaOld != null && !prodFamiliaOld.equals(prodFamiliaNew)) {
                prodFamiliaOld.getProductoCollection().remove(producto);
                prodFamiliaOld = em.merge(prodFamiliaOld);
            }
            if (prodFamiliaNew != null && !prodFamiliaNew.equals(prodFamiliaOld)) {
                prodFamiliaNew.getProductoCollection().add(producto);
                prodFamiliaNew = em.merge(prodFamiliaNew);
            }
            if (prodLineaOld != null && !prodLineaOld.equals(prodLineaNew)) {
                prodLineaOld.getProductoCollection().remove(producto);
                prodLineaOld = em.merge(prodLineaOld);
            }
            if (prodLineaNew != null && !prodLineaNew.equals(prodLineaOld)) {
                prodLineaNew.getProductoCollection().add(producto);
                prodLineaNew = em.merge(prodLineaNew);
            }
            if (prodUnidadmedidaOld != null && !prodUnidadmedidaOld.equals(prodUnidadmedidaNew)) {
                prodUnidadmedidaOld.getProductoCollection().remove(producto);
                prodUnidadmedidaOld = em.merge(prodUnidadmedidaOld);
            }
            if (prodUnidadmedidaNew != null && !prodUnidadmedidaNew.equals(prodUnidadmedidaOld)) {
                prodUnidadmedidaNew.getProductoCollection().add(producto);
                prodUnidadmedidaNew = em.merge(prodUnidadmedidaNew);
            }
            for (ProduccionPan produccionPanCollectionNewProduccionPan : produccionPanCollectionNew) {
                if (!produccionPanCollectionOld.contains(produccionPanCollectionNewProduccionPan)) {
                    Producto oldPpanProductoOfProduccionPanCollectionNewProduccionPan = produccionPanCollectionNewProduccionPan.getPpanProducto();
                    produccionPanCollectionNewProduccionPan.setPpanProducto(producto);
                    produccionPanCollectionNewProduccionPan = em.merge(produccionPanCollectionNewProduccionPan);
                    if (oldPpanProductoOfProduccionPanCollectionNewProduccionPan != null && !oldPpanProductoOfProduccionPanCollectionNewProduccionPan.equals(producto)) {
                        oldPpanProductoOfProduccionPanCollectionNewProduccionPan.getProduccionPanCollection().remove(produccionPanCollectionNewProduccionPan);
                        oldPpanProductoOfProduccionPanCollectionNewProduccionPan = em.merge(oldPpanProductoOfProduccionPanCollectionNewProduccionPan);
                    }
                }
            }
            for (Receta recetaCollectionNewReceta : recetaCollectionNew) {
                if (!recetaCollectionOld.contains(recetaCollectionNewReceta)) {
                    Producto oldReceProductoOfRecetaCollectionNewReceta = recetaCollectionNewReceta.getReceProducto();
                    recetaCollectionNewReceta.setReceProducto(producto);
                    recetaCollectionNewReceta = em.merge(recetaCollectionNewReceta);
                    if (oldReceProductoOfRecetaCollectionNewReceta != null && !oldReceProductoOfRecetaCollectionNewReceta.equals(producto)) {
                        oldReceProductoOfRecetaCollectionNewReceta.getRecetaCollection().remove(recetaCollectionNewReceta);
                        oldReceProductoOfRecetaCollectionNewReceta = em.merge(oldReceProductoOfRecetaCollectionNewReceta);
                    }
                }
            }
            for (DetalleReceta detalleRecetaCollectionNewDetalleReceta : detalleRecetaCollectionNew) {
                if (!detalleRecetaCollectionOld.contains(detalleRecetaCollectionNewDetalleReceta)) {
                    Producto oldDrecProductoOfDetalleRecetaCollectionNewDetalleReceta = detalleRecetaCollectionNewDetalleReceta.getDrecProducto();
                    detalleRecetaCollectionNewDetalleReceta.setDrecProducto(producto);
                    detalleRecetaCollectionNewDetalleReceta = em.merge(detalleRecetaCollectionNewDetalleReceta);
                    if (oldDrecProductoOfDetalleRecetaCollectionNewDetalleReceta != null && !oldDrecProductoOfDetalleRecetaCollectionNewDetalleReceta.equals(producto)) {
                        oldDrecProductoOfDetalleRecetaCollectionNewDetalleReceta.getDetalleRecetaCollection().remove(detalleRecetaCollectionNewDetalleReceta);
                        oldDrecProductoOfDetalleRecetaCollectionNewDetalleReceta = em.merge(oldDrecProductoOfDetalleRecetaCollectionNewDetalleReceta);
                    }
                }
            }
            for (PrecioVenta precioVentaCollectionNewPrecioVenta : precioVentaCollectionNew) {
                if (!precioVentaCollectionOld.contains(precioVentaCollectionNewPrecioVenta)) {
                    Producto oldPrecvProductoOfPrecioVentaCollectionNewPrecioVenta = precioVentaCollectionNewPrecioVenta.getPrecvProducto();
                    precioVentaCollectionNewPrecioVenta.setPrecvProducto(producto);
                    precioVentaCollectionNewPrecioVenta = em.merge(precioVentaCollectionNewPrecioVenta);
                    if (oldPrecvProductoOfPrecioVentaCollectionNewPrecioVenta != null && !oldPrecvProductoOfPrecioVentaCollectionNewPrecioVenta.equals(producto)) {
                        oldPrecvProductoOfPrecioVentaCollectionNewPrecioVenta.getPrecioVentaCollection().remove(precioVentaCollectionNewPrecioVenta);
                        oldPrecvProductoOfPrecioVentaCollectionNewPrecioVenta = em.merge(oldPrecvProductoOfPrecioVentaCollectionNewPrecioVenta);
                    }
                }
            }
            for (PrecioCosto precioCostoCollectionNewPrecioCosto : precioCostoCollectionNew) {
                if (!precioCostoCollectionOld.contains(precioCostoCollectionNewPrecioCosto)) {
                    Producto oldCostProductoOfPrecioCostoCollectionNewPrecioCosto = precioCostoCollectionNewPrecioCosto.getCostProducto();
                    precioCostoCollectionNewPrecioCosto.setCostProducto(producto);
                    precioCostoCollectionNewPrecioCosto = em.merge(precioCostoCollectionNewPrecioCosto);
                    if (oldCostProductoOfPrecioCostoCollectionNewPrecioCosto != null && !oldCostProductoOfPrecioCostoCollectionNewPrecioCosto.equals(producto)) {
                        oldCostProductoOfPrecioCostoCollectionNewPrecioCosto.getPrecioCostoCollection().remove(precioCostoCollectionNewPrecioCosto);
                        oldCostProductoOfPrecioCostoCollectionNewPrecioCosto = em.merge(oldCostProductoOfPrecioCostoCollectionNewPrecioCosto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getProdId();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getProdId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ProduccionPan> produccionPanCollectionOrphanCheck = producto.getProduccionPanCollection();
            for (ProduccionPan produccionPanCollectionOrphanCheckProduccionPan : produccionPanCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the ProduccionPan " + produccionPanCollectionOrphanCheckProduccionPan + " in its produccionPanCollection field has a non-nullable ppanProducto field.");
            }
            Collection<Receta> recetaCollectionOrphanCheck = producto.getRecetaCollection();
            for (Receta recetaCollectionOrphanCheckReceta : recetaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Receta " + recetaCollectionOrphanCheckReceta + " in its recetaCollection field has a non-nullable receProducto field.");
            }
            Collection<DetalleReceta> detalleRecetaCollectionOrphanCheck = producto.getDetalleRecetaCollection();
            for (DetalleReceta detalleRecetaCollectionOrphanCheckDetalleReceta : detalleRecetaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetalleReceta " + detalleRecetaCollectionOrphanCheckDetalleReceta + " in its detalleRecetaCollection field has a non-nullable drecProducto field.");
            }
            Collection<PrecioVenta> precioVentaCollectionOrphanCheck = producto.getPrecioVentaCollection();
            for (PrecioVenta precioVentaCollectionOrphanCheckPrecioVenta : precioVentaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the PrecioVenta " + precioVentaCollectionOrphanCheckPrecioVenta + " in its precioVentaCollection field has a non-nullable precvProducto field.");
            }
            Collection<PrecioCosto> precioCostoCollectionOrphanCheck = producto.getPrecioCostoCollection();
            for (PrecioCosto precioCostoCollectionOrphanCheckPrecioCosto : precioCostoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the PrecioCosto " + precioCostoCollectionOrphanCheckPrecioCosto + " in its precioCostoCollection field has a non-nullable costProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Familia prodFamilia = producto.getProdFamilia();
            if (prodFamilia != null) {
                prodFamilia.getProductoCollection().remove(producto);
                prodFamilia = em.merge(prodFamilia);
            }
            Linea prodLinea = producto.getProdLinea();
            if (prodLinea != null) {
                prodLinea.getProductoCollection().remove(producto);
                prodLinea = em.merge(prodLinea);
            }
            UnidadMedida prodUnidadmedida = producto.getProdUnidadmedida();
            if (prodUnidadmedida != null) {
                prodUnidadmedida.getProductoCollection().remove(producto);
                prodUnidadmedida = em.merge(prodUnidadmedida);
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
