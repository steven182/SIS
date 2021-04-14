package com.sis.Entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "detalleordenservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleOrdenServicio.findAll", query = "SELECT d FROM DetalleOrdenServicio d"),
    @NamedQuery(name = "DetalleOrdenServicio.findByIdDetalleOrdenServicio", query = "SELECT d FROM DetalleOrdenServicio d WHERE d.idDetalleOrdenServicio = :idDetalleOrdenServicio"),
    @NamedQuery(name = "DetalleOrdenServicio.findByEstado", query = "SELECT d FROM DetalleOrdenServicio d WHERE d.estado = :estado"),
    @NamedQuery(name = "DetalleOrdenServicio.findByBorrar", query = "SELECT d FROM DetalleOrdenServicio d WHERE d.borrar = :borrar")})
public class DetalleOrdenServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleOrdenServicio")
    private Integer idDetalleOrdenServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado = true;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrar")
    private boolean borrar = true;
    @JoinColumn(name = "Asignacion_idAsignacion", referencedColumnName = "idAsignacion")
    @ManyToOne(optional = false)
    private Asignacion asignacionidAsignacion;
    @JoinColumn(name = "OrdenServicio_idOrdenServicio", referencedColumnName = "idOrdenServicio")
    @ManyToOne(optional = false)
    private OrdenServicio ordenServicioidOrdenServicio;

    public DetalleOrdenServicio() {
    }

    public DetalleOrdenServicio(Integer idDetalleOrdenServicio) {
        this.idDetalleOrdenServicio = idDetalleOrdenServicio;
    }

    public DetalleOrdenServicio(Integer idDetalleOrdenServicio, boolean estado, boolean borrar) {
        this.idDetalleOrdenServicio = idDetalleOrdenServicio;
        this.estado = estado;
        this.borrar = borrar;
    }

    public Integer getIdDetalleOrdenServicio() {
        return idDetalleOrdenServicio;
    }

    public void setIdDetalleOrdenServicio(Integer idDetalleOrdenServicio) {
        this.idDetalleOrdenServicio = idDetalleOrdenServicio;
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

    public Asignacion getAsignacionidAsignacion() {
        return asignacionidAsignacion;
    }

    public void setAsignacionidAsignacion(Asignacion asignacionidAsignacion) {
        this.asignacionidAsignacion = asignacionidAsignacion;
    }

    public OrdenServicio getOrdenServicioidOrdenServicio() {
        return ordenServicioidOrdenServicio;
    }

    public void setOrdenServicioidOrdenServicio(OrdenServicio ordenServicioidOrdenServicio) {
        this.ordenServicioidOrdenServicio = ordenServicioidOrdenServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleOrdenServicio != null ? idDetalleOrdenServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOrdenServicio)) {
            return false;
        }
        DetalleOrdenServicio other = (DetalleOrdenServicio) object;
        if ((this.idDetalleOrdenServicio == null && other.idDetalleOrdenServicio != null) || (this.idDetalleOrdenServicio != null && !this.idDetalleOrdenServicio.equals(other.idDetalleOrdenServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sis.Entidades.DetalleOrdenServicio[ idDetalleOrdenServicio=" + idDetalleOrdenServicio + " ]";
    }
    
}
