/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yo
 */
@Entity
@Table(name = "detalle_receta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleReceta.findAll", query = "SELECT d FROM DetalleReceta d")
    , @NamedQuery(name = "DetalleReceta.findByDrecId", query = "SELECT d FROM DetalleReceta d WHERE d.drecId = :drecId")
    , @NamedQuery(name = "DetalleReceta.findByDrecCantidad", query = "SELECT d FROM DetalleReceta d WHERE d.drecCantidad = :drecCantidad")})
public class DetalleReceta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "drec_id")
    private Integer drecId;
    @Basic(optional = false)
    @Column(name = "drec_cantidad")
    private int drecCantidad;
    @JoinColumn(name = "drec_producto", referencedColumnName = "prod_id")
    @ManyToOne(optional = false)
    private Producto drecProducto;
    @JoinColumn(name = "drec_receta", referencedColumnName = "rece_id")
    @ManyToOne(optional = false)
    private Receta drecReceta;

    public DetalleReceta() {
    }

    public DetalleReceta(Integer drecId) {
        this.drecId = drecId;
    }

    public DetalleReceta(Integer drecId, int drecCantidad) {
        this.drecId = drecId;
        this.drecCantidad = drecCantidad;
    }

    public Integer getDrecId() {
        return drecId;
    }

    public void setDrecId(Integer drecId) {
        this.drecId = drecId;
    }

    public int getDrecCantidad() {
        return drecCantidad;
    }

    public void setDrecCantidad(int drecCantidad) {
        this.drecCantidad = drecCantidad;
    }

    public Producto getDrecProducto() {
        return drecProducto;
    }

    public void setDrecProducto(Producto drecProducto) {
        this.drecProducto = drecProducto;
    }

    public Receta getDrecReceta() {
        return drecReceta;
    }

    public void setDrecReceta(Receta drecReceta) {
        this.drecReceta = drecReceta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (drecId != null ? drecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleReceta)) {
            return false;
        }
        DetalleReceta other = (DetalleReceta) object;
        if ((this.drecId == null && other.drecId != null) || (this.drecId != null && !this.drecId.equals(other.drecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.DetalleReceta[ drecId=" + drecId + " ]";
    }
    
}
