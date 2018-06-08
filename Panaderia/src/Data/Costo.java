/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "precio_costo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrecioCosto.findAll", query = "SELECT p FROM PrecioCosto p")
    , @NamedQuery(name = "PrecioCosto.findByCostId", query = "SELECT p FROM PrecioCosto p WHERE p.costId = :costId")
    , @NamedQuery(name = "PrecioCosto.findByCostValor", query = "SELECT p FROM PrecioCosto p WHERE p.costValor = :costValor")
    , @NamedQuery(name = "PrecioCosto.findByCostFechaIngreso", query = "SELECT p FROM PrecioCosto p WHERE p.costFechaIngreso = :costFechaIngreso")})
public class Costo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cost_id")
    private Integer costoId;
    @Basic(optional = false)
    @Column(name = "cost_valor")
    private int costoValor;
    @Basic(optional = false)
    @Column(name = "cost_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date costoFechaIngreso;
    @JoinColumn(name = "cost_producto", referencedColumnName = "prod_id")
    @ManyToOne(optional = false)
    private Producto costoProducto;

    public Costo() {
    }

    public Costo(Integer costId) {
        this.costoId = costId;
    }

    public Costo(Integer costoId, int costoValor, Date costoFechaIngreso, Producto costoProducto) {
        this.costoId = costoId;
        this.costoValor = costoValor;
        this.costoFechaIngreso = costoFechaIngreso;
        this.costoProducto = costoProducto;
    }

    public Integer getCostId() {
        return costoId;
    }

    public void setCostId(Integer costId) {
        this.costoId = costId;
    }

    public int getCostValor() {
        return costoValor;
    }

    public void setCostValor(int costValor) {
        this.costoValor = costValor;
    }

    public Date getCostFechaIngreso() {
        return costoFechaIngreso;
    }

    public void setCostFechaIngreso(Date costFechaIngreso) {
        this.costoFechaIngreso = costFechaIngreso;
    }

    public Producto getCostProducto() {
        return costoProducto;
    }

    public void setCostProducto(Producto costProducto) {
        this.costoProducto = costProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (costoId != null ? costoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Costo)) {
            return false;
        }
        Costo other = (Costo) object;
        if ((this.costoId == null && other.costoId != null) || (this.costoId != null && !this.costoId.equals(other.costoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.PrecioCosto[ costId=" + costoId + " ]";
    }
    
}
