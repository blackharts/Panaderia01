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
@Table(name = "unidad_medida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadMedida.findAll", query = "SELECT u FROM UnidadMedida u")
    , @NamedQuery(name = "UnidadMedida.findByUnidId", query = "SELECT u FROM UnidadMedida u WHERE u.unidId = :unidId")
    , @NamedQuery(name = "UnidadMedida.findByUnidCodigo", query = "SELECT u FROM UnidadMedida u WHERE u.unidCodigo = :unidCodigo")
    , @NamedQuery(name = "UnidadMedida.findByUnidDescripcion", query = "SELECT u FROM UnidadMedida u WHERE u.unidDescripcion = :unidDescripcion")})
public class UnidadMedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "unid_id")
    private Integer unidId;
    @Basic(optional = false)
    @Column(name = "unid_codigo")
    private String unidCodigo;
    @Basic(optional = false)
    @Column(name = "unid_descripcion")
    private String unidDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodUnidadmedida")
    private Collection<Producto> productoCollection;

    public UnidadMedida() {
    }

    public UnidadMedida(Integer unidId) {
        this.unidId = unidId;
    }

    public UnidadMedida(Integer unidId, String unidCodigo, String unidDescripcion) {
        this.unidId = unidId;
        this.unidCodigo = unidCodigo;
        this.unidDescripcion = unidDescripcion;
    }

    public Integer getUnidId() {
        return unidId;
    }

    public void setUnidId(Integer unidId) {
        this.unidId = unidId;
    }

    public String getUnidCodigo() {
        return unidCodigo;
    }

    public void setUnidCodigo(String unidCodigo) {
        this.unidCodigo = unidCodigo;
    }

    public String getUnidDescripcion() {
        return unidDescripcion;
    }

    public void setUnidDescripcion(String unidDescripcion) {
        this.unidDescripcion = unidDescripcion;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidId != null ? unidId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadMedida)) {
            return false;
        }
        UnidadMedida other = (UnidadMedida) object;
        if ((this.unidId == null && other.unidId != null) || (this.unidId != null && !this.unidId.equals(other.unidId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.UnidadMedida[ unidId=" + unidId + " ]";
    }
    
}
