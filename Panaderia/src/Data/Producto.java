/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KevinRoss
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByProdId", query = "SELECT p FROM Producto p WHERE p.prodId = :prodId")
    , @NamedQuery(name = "Producto.findByProdNombre", query = "SELECT p FROM Producto p WHERE p.prodNombre = :prodNombre")
    , @NamedQuery(name = "Producto.findByProdMarca", query = "SELECT p FROM Producto p WHERE p.prodMarca = :prodMarca")
    , @NamedQuery(name = "Producto.findByProdFormato", query = "SELECT p FROM Producto p WHERE p.prodFormato = :prodFormato")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prod_id")
    private Integer prodId;
    @Basic(optional = false)
    @Column(name = "prod_nombre")
    private String prodNombre;
    @Basic(optional = false)
    @Column(name = "prod_marca")
    private String prodMarca;
    @Basic(optional = false)
    @Column(name = "prod_formato")
    private String prodFormato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ppanProducto")
    private Collection<ProduccionPan> produccionPanCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receProducto")
    private Collection<Receta> recetaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drecProducto")
    private Collection<DetalleReceta> detalleRecetaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "precvProducto")
    private Collection<PrecioVenta> precioVentaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "costProducto")
    private Collection<PrecioCosto> precioCostoCollection;
    @JoinColumn(name = "prod_familia", referencedColumnName = "fami_id")
    @ManyToOne(optional = false)
    private Familia prodFamilia;
    @JoinColumn(name = "prod_linea", referencedColumnName = "line_id")
    @ManyToOne(optional = false)
    private Linea prodLinea;
    @JoinColumn(name = "prod_unidadmedida", referencedColumnName = "unid_id")
    @ManyToOne(optional = false)
    private UnidadMedida prodUnidadmedida;

    public Producto() {
    }

    public Producto(Integer prodId) {
        this.prodId = prodId;
    }

    public Producto(Integer prodId, String prodNombre, String prodMarca, String prodFormato) {
        this.prodId = prodId;
        this.prodNombre = prodNombre;
        this.prodMarca = prodMarca;
        this.prodFormato = prodFormato;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public String getProdMarca() {
        return prodMarca;
    }

    public void setProdMarca(String prodMarca) {
        this.prodMarca = prodMarca;
    }

    public String getProdFormato() {
        return prodFormato;
    }

    public void setProdFormato(String prodFormato) {
        this.prodFormato = prodFormato;
    }

    @XmlTransient
    public Collection<ProduccionPan> getProduccionPanCollection() {
        return produccionPanCollection;
    }

    public void setProduccionPanCollection(Collection<ProduccionPan> produccionPanCollection) {
        this.produccionPanCollection = produccionPanCollection;
    }

    @XmlTransient
    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
    }

    @XmlTransient
    public Collection<DetalleReceta> getDetalleRecetaCollection() {
        return detalleRecetaCollection;
    }

    public void setDetalleRecetaCollection(Collection<DetalleReceta> detalleRecetaCollection) {
        this.detalleRecetaCollection = detalleRecetaCollection;
    }

    @XmlTransient
    public Collection<PrecioVenta> getPrecioVentaCollection() {
        return precioVentaCollection;
    }

    public void setPrecioVentaCollection(Collection<PrecioVenta> precioVentaCollection) {
        this.precioVentaCollection = precioVentaCollection;
    }

    @XmlTransient
    public Collection<PrecioCosto> getPrecioCostoCollection() {
        return precioCostoCollection;
    }

    public void setPrecioCostoCollection(Collection<PrecioCosto> precioCostoCollection) {
        this.precioCostoCollection = precioCostoCollection;
    }

    public Familia getProdFamilia() {
        return prodFamilia;
    }

    public void setProdFamilia(Familia prodFamilia) {
        this.prodFamilia = prodFamilia;
    }

    public Linea getProdLinea() {
        return prodLinea;
    }

    public void setProdLinea(Linea prodLinea) {
        this.prodLinea = prodLinea;
    }

    public UnidadMedida getProdUnidadmedida() {
        return prodUnidadmedida;
    }

    public void setProdUnidadmedida(UnidadMedida prodUnidadmedida) {
        this.prodUnidadmedida = prodUnidadmedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodId != null ? prodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.prodId == null && other.prodId != null) || (this.prodId != null && !this.prodId.equals(other.prodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.Producto[ prodId=" + prodId + " ]";
    }
    
}
