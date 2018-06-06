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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yo
 */
@Entity
@Table(name = "linea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l")
    , @NamedQuery(name = "Linea.findByLineId", query = "SELECT l FROM Linea l WHERE l.lineId = :lineId")
    , @NamedQuery(name = "Linea.findByLineNombre", query = "SELECT l FROM Linea l WHERE l.lineNombre = :lineNombre")})
public class Linea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "line_id")
    private Integer lineId;
    @Basic(optional = false)
    @Column(name = "line_nombre")
    private String lineNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodLinea")
    private Collection<Producto> productoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "famiLinea")
    private Collection<Familia> familiaCollection;

    public Linea() {
    }

    public Linea(Integer lineId) {
        this.lineId = lineId;
    }

    public Linea(Integer lineId, String lineNombre) {
        this.lineId = lineId;
        this.lineNombre = lineNombre;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLineNombre() {
        return lineNombre;
    }

    public void setLineNombre(String lineNombre) {
        this.lineNombre = lineNombre;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @XmlTransient
    public Collection<Familia> getFamiliaCollection() {
        return familiaCollection;
    }

    public void setFamiliaCollection(Collection<Familia> familiaCollection) {
        this.familiaCollection = familiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineId != null ? lineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linea)) {
            return false;
        }
        Linea other = (Linea) object;
        if ((this.lineId == null && other.lineId != null) || (this.lineId != null && !this.lineId.equals(other.lineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.Linea[ lineId=" + lineId + " ]";
    }
    
}
