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
@Table(name = "precio_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrecioVenta.findAll", query = "SELECT p FROM PrecioVenta p"),
    @NamedQuery(name = "PrecioVenta.findByPrecvId", query = "SELECT p FROM PrecioVenta p WHERE p.precvId = :precvId"),
    @NamedQuery(name = "PrecioVenta.findByPrecvValor", query = "SELECT p FROM PrecioVenta p WHERE p.precvValor = :precvValor"),
    @NamedQuery(name = "PrecioVenta.findByPrecvFechaIngreso", query = "SELECT p FROM PrecioVenta p WHERE p.precvFechaIngreso = :precvFechaIngreso")})
public class PrecioVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "precv_id")
    private Integer precvId;
    @Basic(optional = false)
    @Column(name = "precv_valor")
    private int precvValor;
    @Basic(optional = false)
    @Column(name = "precv_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date precvFechaIngreso;
    @JoinColumn(name = "precv_producto", referencedColumnName = "prod_id")
    @ManyToOne(optional = false)
    private Producto precvProducto;

    public PrecioVenta() {
    }

    public PrecioVenta(Integer precvId) {
        this.precvId = precvId;
    }

    public PrecioVenta(Integer precvId, int precvValor, Date precvFechaIngreso) {
        this.precvId = precvId;
        this.precvValor = precvValor;
        this.precvFechaIngreso = precvFechaIngreso;
    }

    public Integer getPrecvId() {
        return precvId;
    }

    public void setPrecvId(Integer precvId) {
        this.precvId = precvId;
    }

    public int getPrecvValor() {
        return precvValor;
    }

    public void setPrecvValor(int precvValor) {
        this.precvValor = precvValor;
    }

    public Date getPrecvFechaIngreso() {
        return precvFechaIngreso;
    }

    public void setPrecvFechaIngreso(Date precvFechaIngreso) {
        this.precvFechaIngreso = precvFechaIngreso;
    }

    public Producto getPrecvProducto() {
        return precvProducto;
    }

    public void setPrecvProducto(Producto precvProducto) {
        this.precvProducto = precvProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (precvId != null ? precvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecioVenta)) {
            return false;
        }
        PrecioVenta other = (PrecioVenta) object;
        if ((this.precvId == null && other.precvId != null) || (this.precvId != null && !this.precvId.equals(other.precvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.PrecioVenta[ precvId=" + precvId + " ]";
    }
    
}
