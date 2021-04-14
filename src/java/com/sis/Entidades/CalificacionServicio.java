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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "calificacionservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalificacionServicio.findAll", query = "SELECT c FROM CalificacionServicio c"),
    @NamedQuery(name = "CalificacionServicio.findByIdCalificacionServicio", query = "SELECT c FROM CalificacionServicio c WHERE c.idCalificacionServicio = :idCalificacionServicio"),
    @NamedQuery(name = "CalificacionServicio.findByPuntualidad", query = "SELECT c FROM CalificacionServicio c WHERE c.puntualidad = :puntualidad"),
    @NamedQuery(name = "CalificacionServicio.findByOrdenTecnico", query = "SELECT c FROM CalificacionServicio c WHERE c.ordenTecnico = :ordenTecnico"),
    @NamedQuery(name = "CalificacionServicio.findByEjecucionServicio", query = "SELECT c FROM CalificacionServicio c WHERE c.ejecucionServicio = :ejecucionServicio"),
    @NamedQuery(name = "CalificacionServicio.findByComentariosServicio", query = "SELECT c FROM CalificacionServicio c WHERE c.comentariosServicio = :comentariosServicio"),
    @NamedQuery(name = "CalificacionServicio.findByEstado", query = "SELECT c FROM CalificacionServicio c WHERE c.estado = :estado"),
    @NamedQuery(name = "CalificacionServicio.findByBorrar", query = "SELECT c FROM CalificacionServicio c WHERE c.borrar = :borrar")})
public class CalificacionServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCalificacionServicio")
    private Integer idCalificacionServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntualidad")
    private int puntualidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ordenTecnico")
    private int ordenTecnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ejecucionServicio")
    private int ejecucionServicio;
    @Size(max = 300)
    @Column(name = "comentariosServicio")
    private String comentariosServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado = true;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrar")
    private boolean borrar = true;
    @JoinColumn(name = "OrdenServicio_idOrdenServicio", referencedColumnName = "idOrdenServicio")
    @ManyToOne(optional = false)
    private OrdenServicio ordenServicioidOrdenServicio;

    public CalificacionServicio() {
    }

    public CalificacionServicio(Integer idCalificacionServicio) {
        this.idCalificacionServicio = idCalificacionServicio;
    }

    public CalificacionServicio(Integer idCalificacionServicio, int puntualidad, int ordenTecnico, int ejecucionServicio, boolean estado, boolean borrar) {
        this.idCalificacionServicio = idCalificacionServicio;
        this.puntualidad = puntualidad;
        this.ordenTecnico = ordenTecnico;
        this.ejecucionServicio = ejecucionServicio;
        this.estado = estado;
        this.borrar = borrar;
    }

    public Integer getIdCalificacionServicio() {
        return idCalificacionServicio;
    }

    public void setIdCalificacionServicio(Integer idCalificacionServicio) {
        this.idCalificacionServicio = idCalificacionServicio;
    }

    public int getPuntualidad() {
        return puntualidad;
    }

    public void setPuntualidad(int puntualidad) {
        this.puntualidad = puntualidad;
    }

    public int getOrdenTecnico() {
        return ordenTecnico;
    }

    public void setOrdenTecnico(int ordenTecnico) {
        this.ordenTecnico = ordenTecnico;
    }

    public int getEjecucionServicio() {
        return ejecucionServicio;
    }

    public void setEjecucionServicio(int ejecucionServicio) {
        this.ejecucionServicio = ejecucionServicio;
    }

    public String getComentariosServicio() {
        return comentariosServicio;
    }

    public void setComentariosServicio(String comentariosServicio) {
        this.comentariosServicio = comentariosServicio;
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

    public OrdenServicio getOrdenServicioidOrdenServicio() {
        return ordenServicioidOrdenServicio;
    }

    public void setOrdenServicioidOrdenServicio(OrdenServicio ordenServicioidOrdenServicio) {
        this.ordenServicioidOrdenServicio = ordenServicioidOrdenServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalificacionServicio != null ? idCalificacionServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalificacionServicio)) {
            return false;
        }
        CalificacionServicio other = (CalificacionServicio) object;
        if ((this.idCalificacionServicio == null && other.idCalificacionServicio != null) || (this.idCalificacionServicio != null && !this.idCalificacionServicio.equals(other.idCalificacionServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sis.Entidades.CalificacionServicio[ idCalificacionServicio=" + idCalificacionServicio + " ]";
    }
    
}
