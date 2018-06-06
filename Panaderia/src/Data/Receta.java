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
 * @author luisa
 */
@Entity
@Table(name = "receta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r"),
    @NamedQuery(name = "Receta.findByReceId", query = "SELECT r FROM Receta r WHERE r.receId = :receId")})
public class Receta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rece_id")
    private Integer receId;
    @JoinColumn(name = "rece_producto", referencedColumnName = "prod_id")
    @ManyToOne(optional = false)
    private Producto receProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drecReceta")
    private Collection<DetalleReceta> detalleRecetaCollection;

    public Receta() {
    }

    public Receta(Integer receId) {
        this.receId = receId;
    }

    public Integer getReceId() {
        return receId;
    }

    public void setReceId(Integer receId) {
        this.receId = receId;
    }

    public Producto getReceProducto() {
        return receProducto;
    }

    public void setReceProducto(Producto receProducto) {
        this.receProducto = receProducto;
    }

    @XmlTransient
    public Collection<DetalleReceta> getDetalleRecetaCollection() {
        return detalleRecetaCollection;
    }

    public void setDetalleRecetaCollection(Collection<DetalleReceta> detalleRecetaCollection) {
        this.detalleRecetaCollection = detalleRecetaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receId != null ? receId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.receId == null && other.receId != null) || (this.receId != null && !this.receId.equals(other.receId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.Receta[ receId=" + receId + " ]";
    }
    
}
