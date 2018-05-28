/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author kalbl
 */
@Entity
@Table(name = "precio_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrecioVenta.findAll", query = "SELECT p FROM PrecioVenta p")
    , @NamedQuery(name = "PrecioVenta.findByIdVenta", query = "SELECT p FROM PrecioVenta p WHERE p.idVenta = :idVenta")
    , @NamedQuery(name = "PrecioVenta.findByFechaVenta", query = "SELECT p FROM PrecioVenta p WHERE p.fechaVenta = :fechaVenta")
    , @NamedQuery(name = "PrecioVenta.findByValorVenta", query = "SELECT p FROM PrecioVenta p WHERE p.valorVenta = :valorVenta")})
public class PrecioVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID-VENTA")
    private Integer idVenta;
    @Column(name = "FECHA_VENTA")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    @Column(name = "VALOR_VENTA")
    private Integer valorVenta;
    @JoinColumn(name = "PROCUTO_VENTA", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne
    private Producto procutoVenta;

    public PrecioVenta() {
    }

    public PrecioVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Integer valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Producto getProcutoVenta() {
        return procutoVenta;
    }

    public void setProcutoVenta(Producto procutoVenta) {
        this.procutoVenta = procutoVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecioVenta)) {
            return false;
        }
        PrecioVenta other = (PrecioVenta) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.PrecioVenta[ idVenta=" + idVenta + " ]";
    }
    
}
