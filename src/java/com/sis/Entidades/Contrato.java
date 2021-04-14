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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findByIdContrato", query = "SELECT c FROM Contrato c WHERE c.idContrato = :idContrato"),
    @NamedQuery(name = "Contrato.findByTipoContrato", query = "SELECT c FROM Contrato c WHERE c.tipoContrato = :tipoContrato"),
    @NamedQuery(name = "Contrato.findByValorContrato", query = "SELECT c FROM Contrato c WHERE c.valorContrato = :valorContrato"),
    @NamedQuery(name = "Contrato.findByFechaInicio", query = "SELECT c FROM Contrato c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Contrato.findByFechaFin", query = "SELECT c FROM Contrato c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "Contrato.findByDescripcion", query = "SELECT c FROM Contrato c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Contrato.findByEstado", query = "SELECT c FROM Contrato c WHERE c.estado = :estado"),
    @NamedQuery(name = "Contrato.findByCosteManoObra", query = "SELECT c FROM Contrato c WHERE c.costeManoObra = :costeManoObra"),
    @NamedQuery(name = "Contrato.findByRespuestosIncluidos", query = "SELECT c FROM Contrato c WHERE c.respuestosIncluidos = :respuestosIncluidos"),
    @NamedQuery(name = "Contrato.findByFechaDePago", query = "SELECT c FROM Contrato c WHERE c.fechaDePago = :fechaDePago"),
    @NamedQuery(name = "Contrato.findByCopiasIncluidas", query = "SELECT c FROM Contrato c WHERE c.copiasIncluidas = :copiasIncluidas"),
    @NamedQuery(name = "Contrato.findByFotoContrato", query = "SELECT c FROM Contrato c WHERE c.fotoContrato = :fotoContrato")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idContrato")
    private Integer idContrato;
    @Size(max = 2)
    @Column(name = "tipoContrato")
    private String tipoContrato;
    @Column(name = "valorContrato")
    private Integer valorContrato;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private boolean estado = true;
    @Column(name = "costeManoObra")
    private Integer costeManoObra;
    @Column(name = "respuestosIncluidos")
    private boolean respuestosIncluidos = true;
    @Size(max = 45)
    @Column(name = "fechaDePago")
    private String fechaDePago;
    @Column(name = "copiasIncluidas")
    private Integer copiasIncluidas;
    @Size(max = 300)
    @Column(name = "fotoContrato")
    private String fotoContrato;
    @JoinColumn(name = "Persona_idPersona", referencedColumnName = "idPersona")
    @ManyToOne(optional = false)
    private Persona personaidPersona;
    @JoinColumn(name = "Asignacion_idAsignacion", referencedColumnName = "idAsignacion")
    @ManyToOne(optional = false)
    private Asignacion asignacionidAsignacion;

    public Contrato() {
    }

    public Contrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Integer getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(Integer valorContrato) {
        this.valorContrato = valorContrato;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Integer getCosteManoObra() {
        return costeManoObra;
    }

    public void setCosteManoObra(Integer costeManoObra) {
        this.costeManoObra = costeManoObra;
    }

    public boolean getRespuestosIncluidos() {
        return respuestosIncluidos;
    }

    public void setRespuestosIncluidos(boolean respuestosIncluidos) {
        this.respuestosIncluidos = respuestosIncluidos;
    }

    public String getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(String fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public Integer getCopiasIncluidas() {
        return copiasIncluidas;
    }

    public void setCopiasIncluidas(Integer copiasIncluidas) {
        this.copiasIncluidas = copiasIncluidas;
    }

    public String getFotoContrato() {
        return fotoContrato;
    }

    public void setFotoContrato(String fotoContrato) {
        this.fotoContrato = fotoContrato;
    }

    public Persona getPersonaidPersona() {
        return personaidPersona;
    }

    public void setPersonaidPersona(Persona personaidPersona) {
        this.personaidPersona = personaidPersona;
    }

    public Asignacion getAsignacionidAsignacion() {
        return asignacionidAsignacion;
    }

    public void setAsignacionidAsignacion(Asignacion asignacionidAsignacion) {
        this.asignacionidAsignacion = asignacionidAsignacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sis.Entidades.Contrato[ idContrato=" + idContrato + " ]";
    }
    
}
