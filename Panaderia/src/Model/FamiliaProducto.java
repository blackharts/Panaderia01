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
@Table(name = "familia_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FamiliaProducto.findAll", query = "SELECT f FROM FamiliaProducto f")
    , @NamedQuery(name = "FamiliaProducto.findByIdFamilia", query = "SELECT f FROM FamiliaProducto f WHERE f.idFamilia = :idFamilia")
    , @NamedQuery(name = "FamiliaProducto.findByNombreFamilia", query = "SELECT f FROM FamiliaProducto f WHERE f.nombreFamilia = :nombreFamilia")})
public class FamiliaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_FAMILIA")
    private Integer idFamilia;
    @Basic(optional = false)
    @Column(name = "NOMBRE_FAMILIA")
    private String nombreFamilia;
    @JoinColumn(name = "LINEA_PROD", referencedColumnName = "ID_LINEA")
    @ManyToOne(optional = false)
    private LineaProducto lineaProd;
    @OneToMany(mappedBy = "familiaProd")
    private Collection<Producto> productoCollection;

    public FamiliaProducto() {
    }

    public FamiliaProducto(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public FamiliaProducto(Integer idFamilia, String nombreFamilia) {
        this.idFamilia = idFamilia;
        this.nombreFamilia = nombreFamilia;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public LineaProducto getLineaProd() {
        return lineaProd;
    }

    public void setLineaProd(LineaProducto lineaProd) {
        this.lineaProd = lineaProd;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamilia != null ? idFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamiliaProducto)) {
            return false;
        }
        FamiliaProducto other = (FamiliaProducto) object;
        if ((this.idFamilia == null && other.idFamilia != null) || (this.idFamilia != null && !this.idFamilia.equals(other.idFamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.FamiliaProducto[ idFamilia=" + idFamilia + " ]";
    }
    
}
