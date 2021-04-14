package com.sis.Entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "ordenservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenServicio.findAll", query = "SELECT o FROM OrdenServicio o"),
    @NamedQuery(name = "OrdenServicio.findByIdOrdenServicio", query = "SELECT o FROM OrdenServicio o WHERE o.idOrdenServicio = :idOrdenServicio"),
    @NamedQuery(name = "OrdenServicio.findByFechaInicio", query = "SELECT o FROM OrdenServicio o WHERE o.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "OrdenServicio.findByFechaFinalServicio", query = "SELECT o FROM OrdenServicio o WHERE o.fechaFinalServicio = :fechaFinalServicio"),
    @NamedQuery(name = "OrdenServicio.findByTipoServicio", query = "SELECT o FROM OrdenServicio o WHERE o.tipoServicio = :tipoServicio"),
    @NamedQuery(name = "OrdenServicio.findByDescripcionServicio", query = "SELECT o FROM OrdenServicio o WHERE o.descripcionServicio = :descripcionServicio"),
    @NamedQuery(name = "OrdenServicio.findByInformeServicio", query = "SELECT o FROM OrdenServicio o WHERE o.informeServicio = :informeServicio"),
    @NamedQuery(name = "OrdenServicio.findByDescripcionFinalServicio", query = "SELECT o FROM OrdenServicio o WHERE o.descripcionFinalServicio = :descripcionFinalServicio"),
    @NamedQuery(name = "OrdenServicio.findByEvidenciaFotografica", query = "SELECT o FROM OrdenServicio o WHERE o.evidenciaFotografica = :evidenciaFotografica"),
    @NamedQuery(name = "OrdenServicio.findByBorrar", query = "SELECT o FROM OrdenServicio o WHERE o.borrar = :borrar"),
    @NamedQuery(name = "OrdenServicio.findByEstadoServicio", query = "SELECT o FROM OrdenServicio o WHERE o.estadoServicio = :estadoServicio"),
    @NamedQuery(name = "OrdenServicio.findByProductosSolicitados", query = "SELECT o FROM OrdenServicio o WHERE o.productosSolicitados = :productosSolicitados")})
public class OrdenServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrdenServicio")
    private Integer idOrdenServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFinalServicio")
    @Temporal(TemporalType.DATE)
    private Date fechaFinalServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipoServicio")
    private String tipoServicio;
    @Size(max = 300)
    @Column(name = "descripcionServicio")
    private String descripcionServicio;
    @Size(max = 300)
    @Column(name = "informeServicio")
    private String informeServicio;
    @Size(max = 300)
    @Column(name = "descripcionFinalServicio")
    private String descripcionFinalServicio;
    @Size(max = 300)
    @Column(name = "evidenciaFotografica")
    private String evidenciaFotografica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrar")
    private boolean borrar = true;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "estadoServicio")
    private String estadoServicio = "A";
    @Size(max = 300)
    @Column(name = "productosSolicitados")
    private String productosSolicitados;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenServicioidOrdenServicio")
    private List<CalificacionServicio> calificacionServicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenServicioidOrdenServicio")
    private List<DetalleOrdenServicio> detalleOrdenServicioList;
    @JoinColumn(name = "asignaciontecnico_idAsignacionTecnico", referencedColumnName = "idAsignacionTecnico")
    @ManyToOne(optional = false)
    private AsignacionTecnico asignaciontecnicoidAsignacionTecnico;
    @JoinColumn(name = "persona_idPersona", referencedColumnName = "idPersona")
    @ManyToOne(optional = false)
    private Persona personaidPersona;

    public OrdenServicio() {
    }

    public OrdenServicio(Integer idOrdenServicio) {
        this.idOrdenServicio = idOrdenServicio;
    }

    public OrdenServicio(Integer idOrdenServicio, Date fechaInicio, String tipoServicio, boolean borrar, String estadoServicio) {
        this.idOrdenServicio = idOrdenServicio;
        this.fechaInicio = fechaInicio;
        this.tipoServicio = tipoServicio;
        this.borrar = borrar;
        this.estadoServicio = estadoServicio;
    }

    public Integer getIdOrdenServicio() {
        return idOrdenServicio;
    }

    public void setIdOrdenServicio(Integer idOrdenServicio) {
        this.idOrdenServicio = idOrdenServicio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalServicio() {
        return fechaFinalServicio;
    }

    public void setFechaFinalServicio(Date fechaFinalServicio) {
        this.fechaFinalServicio = fechaFinalServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public String getInformeServicio() {
        return informeServicio;
    }

    public void setInformeServicio(String informeServicio) {
        this.informeServicio = informeServicio;
    }

    public String getDescripcionFinalServicio() {
        return descripcionFinalServicio;
    }

    public void setDescripcionFinalServicio(String descripcionFinalServicio) {
        this.descripcionFinalServicio = descripcionFinalServicio;
    }

    public String getEvidenciaFotografica() {
        return evidenciaFotografica;
    }

    public void setEvidenciaFotografica(String evidenciaFotografica) {
        this.evidenciaFotografica = evidenciaFotografica;
    }

    public boolean getBorrar() {
        return borrar;
    }

    public void setBorrar(boolean borrar) {
        this.borrar = borrar;
    }

    public String getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(String estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public String getProductosSolicitados() {
        return productosSolicitados;
    }

    public void setProductosSolicitados(String productosSolicitados) {
        this.productosSolicitados = productosSolicitados;
    }

    @XmlTransient
    @JsonIgnore
    public List<CalificacionServicio> getCalificacionServicioList() {
        return calificacionServicioList;
    }

    public void setCalificacionServicioList(List<CalificacionServicio> calificacionServicioList) {
        this.calificacionServicioList = calificacionServicioList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetalleOrdenServicio> getDetalleOrdenServicioList() {
        return detalleOrdenServicioList;
    }

    public void setDetalleOrdenServicioList(List<DetalleOrdenServicio> detalleOrdenServicioList) {
        this.detalleOrdenServicioList = detalleOrdenServicioList;
    }

    public AsignacionTecnico getAsignaciontecnicoidAsignacionTecnico() {
        return asignaciontecnicoidAsignacionTecnico;
    }

    public void setAsignaciontecnicoidAsignacionTecnico(AsignacionTecnico asignaciontecnicoidAsignacionTecnico) {
        this.asignaciontecnicoidAsignacionTecnico = asignaciontecnicoidAsignacionTecnico;
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
        hash += (idOrdenServicio != null ? idOrdenServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenServicio)) {
            return false;
        }
        OrdenServicio other = (OrdenServicio) object;
        if ((this.idOrdenServicio == null && other.idOrdenServicio != null) || (this.idOrdenServicio != null && !this.idOrdenServicio.equals(other.idOrdenServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sis.Entidades.OrdenServicio[ idOrdenServicio=" + idOrdenServicio + " ]";
    }
    
}
