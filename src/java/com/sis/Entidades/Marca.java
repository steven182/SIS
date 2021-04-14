package com.sis.Entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "marca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m"),
    @NamedQuery(name = "Marca.findByIdMarca", query = "SELECT m FROM Marca m WHERE m.idMarca = :idMarca"),
    @NamedQuery(name = "Marca.findByEstado", query = "SELECT m FROM Marca m WHERE m.estado = :estado"),
    @NamedQuery(name = "Marca.findByNombreMarca", query = "SELECT m FROM Marca m WHERE m.nombreMarca = :nombreMarca")})
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMarca")
    private Integer idMarca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado = true;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreMarca")
    private String nombreMarca;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marcaidMarca")
    private List<DetalleProducto> detalleProductoList;

    public Marca() {
    }

    public Marca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Marca(Integer idMarca, boolean estado, String nombreMarca) {
        this.idMarca = idMarca;
        this.estado = estado;
        this.nombreMarca = nombreMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetalleProducto> getDetalleProductoList() {
        return detalleProductoList;
    }

    public void setDetalleProductoList(List<DetalleProducto> detalleProductoList) {
        this.detalleProductoList = detalleProductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMarca != null ? idMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) object;
        if ((this.idMarca == null && other.idMarca != null) || (this.idMarca != null && !this.idMarca.equals(other.idMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sis.Entidades.Marca[ idMarca=" + idMarca + " ]";
    }
    
}
