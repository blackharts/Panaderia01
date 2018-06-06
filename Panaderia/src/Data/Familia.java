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
 * @author yo
 */
@Entity
@Table(name = "familia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Familia.findAll", query = "SELECT f FROM Familia f")
    , @NamedQuery(name = "Familia.findByFamiId", query = "SELECT f FROM Familia f WHERE f.famiId = :famiId")
    , @NamedQuery(name = "Familia.findByFamiNombre", query = "SELECT f FROM Familia f WHERE f.famiNombre = :famiNombre")})
public class Familia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fami_id")
    private Integer famiId;
    @Basic(optional = false)
    @Column(name = "fami_nombre")
    private String famiNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodFamilia")
    private Collection<Producto> productoCollection;
    @JoinColumn(name = "fami_linea", referencedColumnName = "line_id")
    @ManyToOne(optional = false)
    private Linea famiLinea;

    public Familia() {
    }

    public Familia(Integer famiId) {
        this.famiId = famiId;
    }

    public Familia(Integer famiId, String famiNombre) {
        this.famiId = famiId;
        this.famiNombre = famiNombre;
    }

    public Integer getFamiId() {
        return famiId;
    }

    public void setFamiId(Integer famiId) {
        this.famiId = famiId;
    }

    public String getFamiNombre() {
        return famiNombre;
    }

    public void setFamiNombre(String famiNombre) {
        this.famiNombre = famiNombre;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    public Linea getFamiLinea() {
        return famiLinea;
    }

    public void setFamiLinea(Linea famiLinea) {
        this.famiLinea = famiLinea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (famiId != null ? famiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familia)) {
            return false;
        }
        Familia other = (Familia) object;
        if ((this.famiId == null && other.famiId != null) || (this.famiId != null && !this.famiId.equals(other.famiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.Familia[ famiId=" + famiId + " ]";
    }
    
}
