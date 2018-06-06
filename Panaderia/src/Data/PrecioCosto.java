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
 * @author luisa
 */
@Entity
@Table(name = "precio_costo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrecioCosto.findAll", query = "SELECT p FROM PrecioCosto p"),
    @NamedQuery(name = "PrecioCosto.findByCostId", query = "SELECT p FROM PrecioCosto p WHERE p.costId = :costId"),
    @NamedQuery(name = "PrecioCosto.findByCostValor", query = "SELECT p FROM PrecioCosto p WHERE p.costValor = :costValor"),
    @NamedQuery(name = "PrecioCosto.findByCostFechaIngreso", query = "SELECT p FROM PrecioCosto p WHERE p.costFechaIngreso = :costFechaIngreso")})
public class PrecioCosto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cost_id")
    private Integer costId;
    @Basic(optional = false)
    @Column(name = "cost_valor")
    private int costValor;
    @Basic(optional = false)
    @Column(name = "cost_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date costFechaIngreso;
    @JoinColumn(name = "cost_producto", referencedColumnName = "prod_id")
    @ManyToOne(optional = false)
    private Producto costProducto;

    public PrecioCosto() {
    }

    public PrecioCosto(Integer costId) {
        this.costId = costId;
    }

    public PrecioCosto(Integer costId, int costValor, Date costFechaIngreso) {
        this.costId = costId;
        this.costValor = costValor;
        this.costFechaIngreso = costFechaIngreso;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public int getCostValor() {
        return costValor;
    }

    public void setCostValor(int costValor) {
        this.costValor = costValor;
    }

    public Date getCostFechaIngreso() {
        return costFechaIngreso;
    }

    public void setCostFechaIngreso(Date costFechaIngreso) {
        this.costFechaIngreso = costFechaIngreso;
    }

    public Producto getCostProducto() {
        return costProducto;
    }

    public void setCostProducto(Producto costProducto) {
        this.costProducto = costProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (costId != null ? costId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecioCosto)) {
            return false;
        }
        PrecioCosto other = (PrecioCosto) object;
        if ((this.costId == null && other.costId != null) || (this.costId != null && !this.costId.equals(other.costId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.PrecioCosto[ costId=" + costId + " ]";
    }
    
}
