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
@Table(name = "asignaciontecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignacionTecnico.findAll", query = "SELECT a FROM AsignacionTecnico a"),
    @NamedQuery(name = "AsignacionTecnico.findByIdAsignacionTecnico", query = "SELECT a FROM AsignacionTecnico a WHERE a.idAsignacionTecnico = :idAsignacionTecnico"),
    @NamedQuery(name = "AsignacionTecnico.findByDisponibilidad", query = "SELECT a FROM AsignacionTecnico a WHERE a.disponibilidad = :disponibilidad"),
    @NamedQuery(name = "AsignacionTecnico.findByEstado", query = "SELECT a FROM AsignacionTecnico a WHERE a.estado = :estado")})
public class AsignacionTecnico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsignacionTecnico")
    private Integer idAsignacionTecnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disponibilidad")
    private boolean disponibilidad = true;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado = true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignaciontecnicoidAsignacionTecnico")
    private List<OrdenServicio> ordenServicioList;
    @JoinColumn(name = "Persona_idPersona", referencedColumnName = "idPersona")
    @ManyToOne(optional = false)
    private Persona personaidPersona;

    public AsignacionTecnico() {
    }

    public AsignacionTecnico(Integer idAsignacionTecnico) {
        this.idAsignacionTecnico = idAsignacionTecnico;
    }

    public AsignacionTecnico(Integer idAsignacionTecnico, boolean disponibilidad, boolean estado) {
        this.idAsignacionTecnico = idAsignacionTecnico;
        this.disponibilidad = disponibilidad;
        this.estado = estado;
    }

    public Integer getIdAsignacionTecnico() {
        return idAsignacionTecnico;
    }

    public void setIdAsignacionTecnico(Integer idAsignacionTecnico) {
        this.idAsignacionTecnico = idAsignacionTecnico;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    @JsonIgnore
    public List<OrdenServicio> getOrdenServicioList() {
        return ordenServicioList;
    }

    public void setOrdenServicioList(List<OrdenServicio> ordenServicioList) {
        this.ordenServicioList = ordenServicioList;
    }

    public Persona getPersonaidPersona() {
        return personaidPersona;
    }

    public void setPersonaidPersona(Persona personaidPersona) {
        this.personaidPersona = personaidPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionTecnico != null ? idAsignacionTecnico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionTecnico)) {
            return false;
        }
        AsignacionTecnico other = (AsignacionTecnico) object;
        if ((this.idAsignacionTecnico == null && other.idAsignacionTecnico != null) || (this.idAsignacionTecnico != null && !this.idAsignacionTecnico.equals(other.idAsignacionTecnico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sis.Entidades.AsignacionTecnico[ idAsignacionTecnico=" + idAsignacionTecnico + " ]";
    }
    
}
