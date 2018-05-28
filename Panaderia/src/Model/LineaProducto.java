/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "linea_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaProducto.findAll", query = "SELECT l FROM LineaProducto l")
    , @NamedQuery(name = "LineaProducto.findByIdLinea", query = "SELECT l FROM LineaProducto l WHERE l.idLinea = :idLinea")
    , @NamedQuery(name = "LineaProducto.findByNombreLinea", query = "SELECT l FROM LineaProducto l WHERE l.nombreLinea = :nombreLinea")})
public class LineaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_LINEA")
    private Integer idLinea;
    @Column(name = "NOMBRE_LINEA")
    private String nombreLinea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lineaProd")
    private Collection<FamiliaProducto> familiaProductoCollection;

    public LineaProducto() {
    }

    public LineaProducto(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    @XmlTransient
    public Collection<FamiliaProducto> getFamiliaProductoCollection() {
        return familiaProductoCollection;
    }

    public void setFamiliaProductoCollection(Collection<FamiliaProducto> familiaProductoCollection) {
        this.familiaProductoCollection = familiaProductoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLinea != null ? idLinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineaProducto)) {
            return false;
        }
        LineaProducto other = (LineaProducto) object;
        if ((this.idLinea == null && other.idLinea != null) || (this.idLinea != null && !this.idLinea.equals(other.idLinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.LineaProducto[ idLinea=" + idLinea + " ]";
    }
    
}
