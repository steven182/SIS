package com.sis.Entidades;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "detallecotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCotizacion.findAll", query = "SELECT d FROM DetalleCotizacion d"),
    @NamedQuery(name = "DetalleCotizacion.findByIdDetalleCotizacion", query = "SELECT d FROM DetalleCotizacion d WHERE d.idDetalleCotizacion = :idDetalleCotizacion"),
    @NamedQuery(name = "DetalleCotizacion.findByValorCotizacion", query = "SELECT d FROM DetalleCotizacion d WHERE d.valorCotizacion = :valorCotizacion"),
    @NamedQuery(name = "DetalleCotizacion.findByFechaCotizacion", query = "SELECT d FROM DetalleCotizacion d WHERE d.fechaCotizacion = :fechaCotizacion"),
    @NamedQuery(name = "DetalleCotizacion.findByEstado", query = "SELECT d FROM DetalleCotizacion d WHERE d.estado = :estado"),
    @NamedQuery(name = "DetalleCotizacion.findByBorrar", query = "SELECT d FROM DetalleCotizacion d WHERE d.borrar = :borrar")})
public class DetalleCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleCotizacion")
    private Integer idDetalleCotizacion;
    @Column(name = "valorCotizacion")
    private Integer valorCotizacion;
    @Column(name = "fechaCotizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCotizacion;
    @Size(max = 300)
    @Column(name = "descripcionAdmin")
    private String descripcionAdmin;
    @Size(max = 5)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrar")
    private boolean borrar = true;
    @JoinColumn(name = "Asignacion_idAsignacion", referencedColumnName = "idAsignacion")
    @ManyToOne(optional = false)
    private Asignacion asignacionidAsignacion;
    @JoinColumn(name = "cotizacion_idCotizacion", referencedColumnName = "idCotizacion")
    @ManyToOne(optional = false)
    private Cotizacion cotizacionidCotizacion;
    @JoinColumn(name = "persona_idPersona", referencedColumnName = "idPersona")
    @ManyToOne(optional = false)
    private Persona personaidPersona;

    public DetalleCotizacion() {
    }

    public DetalleCotizacion(Integer idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public DetalleCotizacion(Integer idDetalleCotizacion, boolean borrar) {
        this.idDetalleCotizacion = idDetalleCotizacion;
        this.borrar = borrar;
    }

    public Integer getIdDetalleCotizacion() {
        return idDetalleCotizacion;
    }

    public void setIdDetalleCotizacion(Integer idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public Integer getValorCotizacion() {
        return valorCotizacion;
    }

    public void setValorCotizacion(Integer valorCotizacion) {
        this.valorCotizacion = valorCotizacion;
    }

    public Date getFechaCotizacion() {
        return fechaCotizacion;
    }

    public void setFechaCotizacion(Date fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }

    public String getDescripcionAdmin() {
        return descripcionAdmin;
    }

    public void setDescripcionAdmin(String descripcionAdmin) {
        this.descripcionAdmin = descripcionAdmin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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

    public Cotizacion getCotizacionidCotizacion() {
        return cotizacionidCotizacion;
    }

    public void setCotizacionidCotizacion(Cotizacion cotizacionidCotizacion) {
        this.cotizacionidCotizacion = cotizacionidCotizacion;
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
        hash += (idDetalleCotizacion != null ? idDetalleCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCotizacion)) {
            return false;
        }
        DetalleCotizacion other = (DetalleCotizacion) object;
        if ((this.idDetalleCotizacion == null && other.idDetalleCotizacion != null) || (this.idDetalleCotizacion != null && !this.idDetalleCotizacion.equals(other.idDetalleCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sis.Entidades.DetalleCotizacion[ idDetalleCotizacion=" + idDetalleCotizacion + " ]";
    }
    
}
