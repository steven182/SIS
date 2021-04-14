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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Persona.findByPrimernombre", query = "SELECT p FROM Persona p WHERE p.primernombre = :primernombre"),
    @NamedQuery(name = "Persona.findBySegundonombre", query = "SELECT p FROM Persona p WHERE p.segundonombre = :segundonombre"),
    @NamedQuery(name = "Persona.findByPrimerapellido", query = "SELECT p FROM Persona p WHERE p.primerapellido = :primerapellido"),
    @NamedQuery(name = "Persona.findBySegundoapellido", query = "SELECT p FROM Persona p WHERE p.segundoapellido = :segundoapellido"),
    @NamedQuery(name = "Persona.findByTipoIdentificacion", query = "SELECT p FROM Persona p WHERE p.tipoIdentificacion = :tipoIdentificacion"),
    @NamedQuery(name = "Persona.findByNumeroIdentificacion", query = "SELECT p FROM Persona p WHERE p.numeroIdentificacion = :numeroIdentificacion"),
    @NamedQuery(name = "Persona.findByDireccion", query = "SELECT p FROM Persona p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Persona.findByCorreo", query = "SELECT p FROM Persona p WHERE p.correo = :correo"),
    @NamedQuery(name = "Persona.findByGenero", query = "SELECT p FROM Persona p WHERE p.genero = :genero"),
    @NamedQuery(name = "Persona.findByContrasena", query = "SELECT p FROM Persona p WHERE p.contrasena = :contrasena"),
    @NamedQuery(name = "Persona.findByEstado", query = "SELECT p FROM Persona p WHERE p.estado = :estado"),
    @NamedQuery(name = "Persona.findByFoto", query = "SELECT p FROM Persona p WHERE p.foto = :foto"),
    @NamedQuery(name = "Persona.findByPermisoProducto", query = "SELECT p FROM Persona p WHERE p.permisoProducto = :permisoProducto")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPersona")
    private Integer idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "primernombre")
    private String primernombre;
    @Size(max = 25)
    @Column(name = "segundonombre")
    private String segundonombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "primerapellido")
    private String primerapellido;
    @Size(max = 25)
    @Column(name = "segundoapellido")
    private String segundoapellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipoIdentificacion")
    private String tipoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "numeroIdentificacion")
    private String numeroIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado = true;
    @Size(max = 50)
    @Column(name = "foto")
    private String foto;
    @Column(name = "permisoProducto")
    private boolean permisoProducto = true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaidPersona")
    private List<Asignacion> asignacionList;
    @JoinColumn(name = "ciudad_idciudad", referencedColumnName = "idciudad")
    @ManyToOne(optional = false)
    private Ciudad ciudadIdciudad;
    @JoinColumn(name = "rol_IdRol", referencedColumnName = "IdRol")
    @ManyToOne(optional = false)
    private Rol rolIdRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaidPersona")
    private List<Contrato> contratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaidPersona")
    private List<DetalleCotizacion> detalleCotizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaidPersona")
    private List<OrdenServicio> ordenServicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaidPersona")
    private List<AsignacionTecnico> asignacionTecnicoList;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, String primernombre, String primerapellido, String tipoIdentificacion, String numeroIdentificacion, String direccion, String telefono, String correo, String genero, String contrasena, boolean estado) {
        this.idPersona = idPersona;
        this.primernombre = primernombre;
        this.primerapellido = primerapellido;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.genero = genero;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getPrimernombre() {
        return primernombre;
    }

    public void setPrimernombre(String primernombre) {
        this.primernombre = primernombre;
    }

    public String getSegundonombre() {
        return segundonombre;
    }

    public void setSegundonombre(String segundonombre) {
        this.segundonombre = segundonombre;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean getPermisoProducto() {
        return permisoProducto;
    }

    public void setPermisoProducto(boolean permisoProducto) {
        this.permisoProducto = permisoProducto;
    }

    @XmlTransient
    @JsonIgnore
    public List<Asignacion> getAsignacionList() {
        return asignacionList;
    }

    public void setAsignacionList(List<Asignacion> asignacionList) {
        this.asignacionList = asignacionList;
    }

    public Ciudad getCiudadIdciudad() {
        return ciudadIdciudad;
    }

    public void setCiudadIdciudad(Ciudad ciudadIdciudad) {
        this.ciudadIdciudad = ciudadIdciudad;
    }

    public Rol getRolIdRol() {
        return rolIdRol;
    }

    public void setRolIdRol(Rol rolIdRol) {
        this.rolIdRol = rolIdRol;
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
    public List<OrdenServicio> getOrdenServicioList() {
        return ordenServicioList;
    }

    public void setOrdenServicioList(List<OrdenServicio> ordenServicioList) {
        this.ordenServicioList = ordenServicioList;
    }

    @XmlTransient
    @JsonIgnore
    public List<AsignacionTecnico> getAsignacionTecnicoList() {
        return asignacionTecnicoList;
    }

    public void setAsignacionTecnicoList(List<AsignacionTecnico> asignacionTecnicoList) {
        this.asignacionTecnicoList = asignacionTecnicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sis.Entidades.Persona[ idPersona=" + idPersona + " ]";
    }
    
}
