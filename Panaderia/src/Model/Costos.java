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
@Table(name = "costos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Costos.findAll", query = "SELECT c FROM Costos c")
    , @NamedQuery(name = "Costos.findByIdCosto", query = "SELECT c FROM Costos c WHERE c.idCosto = :idCosto")
    , @NamedQuery(name = "Costos.findByFechaCosto", query = "SELECT c FROM Costos c WHERE c.fechaCosto = :fechaCosto")
    , @NamedQuery(name = "Costos.findByValorCosto", query = "SELECT c FROM Costos c WHERE c.valorCosto = :valorCosto")})
public class Costos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_COSTO")
    private Integer idCosto;
    @Column(name = "FECHA_COSTO")
    @Temporal(TemporalType.DATE)
    private Date fechaCosto;
    @Column(name = "VALOR_COSTO")
    private Integer valorCosto;
    @JoinColumn(name = "PRODUCTO_COSTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne
    private Producto productoCosto;

    public Costos() {
    }

    public Costos(Integer idCosto) {
        this.idCosto = idCosto;
    }

    public Integer getIdCosto() {
        return idCosto;
    }

    public void setIdCosto(Integer idCosto) {
        this.idCosto = idCosto;
    }

    public Date getFechaCosto() {
        return fechaCosto;
    }

    public void setFechaCosto(Date fechaCosto) {
        this.fechaCosto = fechaCosto;
    }

    public Integer getValorCosto() {
        return valorCosto;
    }

    public void setValorCosto(Integer valorCosto) {
        this.valorCosto = valorCosto;
    }

    public Producto getProductoCosto() {
        return productoCosto;
    }

    public void setProductoCosto(Producto productoCosto) {
        this.productoCosto = productoCosto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCosto != null ? idCosto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Costos)) {
            return false;
        }
        Costos other = (Costos) object;
        if ((this.idCosto == null && other.idCosto != null) || (this.idCosto != null && !this.idCosto.equals(other.idCosto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Costos[ idCosto=" + idCosto + " ]";
    }
    
}
