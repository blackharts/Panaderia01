/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author kalbl
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombreProducto", query = "SELECT p FROM Producto p WHERE p.nombreProducto = :nombreProducto")
    , @NamedQuery(name = "Producto.findByMarcaProducto", query = "SELECT p FROM Producto p WHERE p.marcaProducto = :marcaProducto")
    , @NamedQuery(name = "Producto.findByFormatoProducto", query = "SELECT p FROM Producto p WHERE p.formatoProducto = :formatoProducto")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    @Column(name = "MARCA_PRODUCTO")
    private String marcaProducto;
    @Column(name = "FORMATO_PRODUCTO")
    private Integer formatoProducto;
    @OneToMany(mappedBy = "productoCosto")
    private Collection<Costos> costosCollection;
    @OneToMany(mappedBy = "productoId")
    private Collection<Receta> recetaCollection;
    @OneToMany(mappedBy = "procutoVenta")
    private Collection<PrecioVenta> precioVentaCollection;
    @OneToMany(mappedBy = "productoId")
    private Collection<RecetaDetalle> recetaDetalleCollection;
    @JoinColumn(name = "FAMILIA_PROD", referencedColumnName = "ID_FAMILIA")
    @ManyToOne
    private FamiliaProducto familiaProd;
    @JoinColumn(name = "MEDIDA_PRODUCTO", referencedColumnName = "ID_MEDIDA")
    @ManyToOne
    private UnidadMedida medidaProducto;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public Integer getFormatoProducto() {
        return formatoProducto;
    }

    public void setFormatoProducto(Integer formatoProducto) {
        this.formatoProducto = formatoProducto;
    }

    @XmlTransient
    public Collection<Costos> getCostosCollection() {
        return costosCollection;
    }

    public void setCostosCollection(Collection<Costos> costosCollection) {
        this.costosCollection = costosCollection;
    }

    @XmlTransient
    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
    }

    @XmlTransient
    public Collection<PrecioVenta> getPrecioVentaCollection() {
        return precioVentaCollection;
    }

    public void setPrecioVentaCollection(Collection<PrecioVenta> precioVentaCollection) {
        this.precioVentaCollection = precioVentaCollection;
    }

    @XmlTransient
    public Collection<RecetaDetalle> getRecetaDetalleCollection() {
        return recetaDetalleCollection;
    }

    public void setRecetaDetalleCollection(Collection<RecetaDetalle> recetaDetalleCollection) {
        this.recetaDetalleCollection = recetaDetalleCollection;
    }

    public FamiliaProducto getFamiliaProd() {
        return familiaProd;
    }

    public void setFamiliaProd(FamiliaProducto familiaProd) {
        this.familiaProd = familiaProd;
    }

    public UnidadMedida getMedidaProducto() {
        return medidaProducto;
    }

    public void setMedidaProducto(UnidadMedida medidaProducto) {
        this.medidaProducto = medidaProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Producto[ idProducto=" + idProducto + " ]";
    }
    
}
