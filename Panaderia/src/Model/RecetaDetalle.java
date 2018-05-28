/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kalbl
 */
@Entity
@Table(name = "receta_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecetaDetalle.findAll", query = "SELECT r FROM RecetaDetalle r")
    , @NamedQuery(name = "RecetaDetalle.findByCantidad", query = "SELECT r FROM RecetaDetalle r WHERE r.cantidad = :cantidad")})
public class RecetaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private Float cantidad;
    @JoinColumn(name = "PRODUCTO_ID", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne
    private Producto productoId;
    @JoinColumn(name = "RECETA_ID", referencedColumnName = "ID_RECETA")
    @ManyToOne
    private Receta recetaId;

    public RecetaDetalle() {
    }

    public RecetaDetalle(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    public Receta getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Receta recetaId) {
        this.recetaId = recetaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cantidad != null ? cantidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaDetalle)) {
            return false;
        }
        RecetaDetalle other = (RecetaDetalle) object;
        if ((this.cantidad == null && other.cantidad != null) || (this.cantidad != null && !this.cantidad.equals(other.cantidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.RecetaDetalle[ cantidad=" + cantidad + " ]";
    }
    
}
