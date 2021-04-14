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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "asignacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignacion.findAll", query = "SELECT a FROM Asignacion a"),
    @NamedQuery(name = "Asignacion.findByIdAsignacion", query = "SELECT a FROM Asignacion a WHERE a.idAsignacion = :idAsignacion"),
    @NamedQuery(name = "Asignacion.findByEstado", query = "SELECT a FROM Asignacion a WHERE a.estado = :estado"),
    @NamedQuery(name = "Asignacion.findByBorrar", query = "SELECT a FROM Asignacion a WHERE a.borrar = :borrar")})
public class Asignacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsignacion")
    private Integer idAsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado = true;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrar")
    private boolean borrar = true;
    @JoinColumn(name = "DetalleProducto_idDetalleProducto", referencedColumnName = "idDetalleProducto")
    @ManyToOne(optional = false)
    private DetalleProducto detalleProductoidDetalleProducto;
    @JoinColumn(name = "persona_idPersona", referencedColumnName = "idPersona")
    @ManyToOne(optional = false)
    private Persona personaidPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignacionidAsignacion")
    private List<Contrato> contratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignacionidAsignacion")
    private List<DetalleCotizacion> detalleCotizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignacionidAsignacion")
    private List<DetalleOrdenServicio> detalleOrdenServicioList;

    public Asignacion() {
    }

    public Asignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Asignacion(Integer idAsignacion, boolean estado, boolean borrar) {
        this.idAsignacion = idAsignacion;
        this.estado = estado;
        this.borrar = borrar;
    }

    public Integer getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getBorrar() {
        return borrar;
    }

    public void setBorrar(boolean borrar) {
        this.borrar = borrar;
    }

    public DetalleProducto getDetalleProductoidDetalleProducto() {
        return detalleProductoidDetalleProducto;
    }

    public void setDetalleProductoidDetalleProducto(DetalleProducto detalleProductoidDetalleProducto) {
        this.detalleProductoidDetalleProducto = detalleProductoidDetalleProducto;
    }

    public Persona getPersonaidPersona() {
        return personaidPersona;
    }

    public void setPersonaidPersona(Persona personaidPersona) {
        this.personaidPersona = personaidPersona;
    }

    @XmlTransient
    @JsonIgnore
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetalleCotizacion> getDetalleCotizacionList() {
        return detalleCotizacionList;
    }

    public void setDetalleCotizacionList(List<DetalleCotizacion> detalleCotizacionList) {
        this.detalleCotizacionList = detalleCotizacionList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetalleOrdenServicio> getDetalleOrdenServicioList() {
        return detalleOrdenServicioList;
    }

    public void setDetalleOrdenServicioList(List<DetalleOrdenServicio> detalleOrdenServicioList) {
        this.detalleOrdenServicioList = detalleOrdenServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacion != null ? idAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignacion)) {
            return false;
        }
        Asignacion other = (Asignacion) object;
        if ((this.idAsignacion == null && other.idAsignacion != null) || (this.idAsignacion != null && !this.idAsignacion.equals(other.idAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getPersonaidPersona().getPrimernombre() + " " + getPersonaidPersona().getPrimerapellido();
    }
    
}
