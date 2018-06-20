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
 * @author KevinRoss
 */
@Entity
@Table(name = "produccion_pan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProduccionPan.findAll", query = "SELECT p FROM ProduccionPan p")
    , @NamedQuery(name = "ProduccionPan.findByPpanId", query = "SELECT p FROM ProduccionPan p WHERE p.ppanId = :ppanId")
    , @NamedQuery(name = "ProduccionPan.findByPpanProduccion", query = "SELECT p FROM ProduccionPan p WHERE p.ppanProduccion = :ppanProduccion")
    , @NamedQuery(name = "ProduccionPan.findByPpanFechaIngreso", query = "SELECT p FROM ProduccionPan p WHERE p.ppanFechaIngreso = :ppanFechaIngreso")})
public class ProduccionPan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ppan_id")
    private Integer ppanId;
    @Basic(optional = false)
    @Column(name = "ppan_produccion")
    private double ppanProduccion;
    @Basic(optional = false)
    @Column(name = "ppan_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ppanFechaIngreso;
    @JoinColumn(name = "ppan_producto", referencedColumnName = "prod_id")
    @ManyToOne(optional = false)
    private Producto ppanProducto;
    @JoinColumn(name = "ppan_unidad_medida", referencedColumnName = "unid_id")
    @ManyToOne(optional = false)
    private UnidadMedida ppanUnidadMedida;

    public ProduccionPan() {
    }

    public ProduccionPan(Integer ppanId) {
        this.ppanId = ppanId;
    }

    public ProduccionPan(Integer ppanId, double ppanProduccion, Date ppanFechaIngreso) {
        this.ppanId = ppanId;
        this.ppanProduccion = ppanProduccion;
        this.ppanFechaIngreso = ppanFechaIngreso;
    }

    public Integer getPpanId() {
        return ppanId;
    }

    public void setPpanId(Integer ppanId) {
        this.ppanId = ppanId;
    }

    public double getPpanProduccion() {
        return ppanProduccion;
    }

    public void setPpanProduccion(double ppanProduccion) {
        this.ppanProduccion = ppanProduccion;
    }

    public Date getPpanFechaIngreso() {
        return ppanFechaIngreso;
    }

    public void setPpanFechaIngreso(Date ppanFechaIngreso) {
        this.ppanFechaIngreso = ppanFechaIngreso;
    }

    public Producto getPpanProducto() {
        return ppanProducto;
    }

    public void setPpanProducto(Producto ppanProducto) {
        this.ppanProducto = ppanProducto;
    }

    public UnidadMedida getPpanUnidadMedida() {
        return ppanUnidadMedida;
    }

    public void setPpanUnidadMedida(UnidadMedida ppanUnidadMedida) {
        this.ppanUnidadMedida = ppanUnidadMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ppanId != null ? ppanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProduccionPan)) {
            return false;
        }
        ProduccionPan other = (ProduccionPan) object;
        if ((this.ppanId == null && other.ppanId != null) || (this.ppanId != null && !this.ppanId.equals(other.ppanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.ProduccionPan[ ppanId=" + ppanId + " ]";
    }
    
}
