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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "detalleproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleProducto.findAll", query = "SELECT d FROM DetalleProducto d"),
    @NamedQuery(name = "DetalleProducto.findByIdDetalleProducto", query = "SELECT d FROM DetalleProducto d WHERE d.idDetalleProducto = :idDetalleProducto"),
    @NamedQuery(name = "DetalleProducto.findByModelo", query = "SELECT d FROM DetalleProducto d WHERE d.modelo = :modelo"),
    @NamedQuery(name = "DetalleProducto.findByPrecio", query = "SELECT d FROM DetalleProducto d WHERE d.precio = :precio"),
    @NamedQuery(name = "DetalleProducto.findByCantidad", query = "SELECT d FROM DetalleProducto d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleProducto.findByContadorMaquina", query = "SELECT d FROM DetalleProducto d WHERE d.contadorMaquina = :contadorMaquina"),
    @NamedQuery(name = "DetalleProducto.findByDescripcion", query = "SELECT d FROM DetalleProducto d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DetalleProducto.findBySerialProducto", query = "SELECT d FROM DetalleProducto d WHERE d.serialProducto = :serialProducto"),
    @NamedQuery(name = "DetalleProducto.findByEstado", query = "SELECT d FROM DetalleProducto d WHERE d.estado = :estado"),
    @NamedQuery(name = "DetalleProducto.findByBorrar", query = "SELECT d FROM DetalleProducto d WHERE d.borrar = :borrar")})
public class DetalleProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleProducto")
    private Integer idDetalleProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "precio")
    private Integer precio;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "contadorMaquina")
    private Integer contadorMaquina;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "serialProducto")
    private String serialProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado = true;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrar")
    private boolean borrar = true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleProductoidDetalleProducto")
    private List<Asignacion> asignacionList;
    @JoinColumn(name = "Marca_idMarca", referencedColumnName = "idMarca")
    @ManyToOne(optional = false)
    private Marca marcaidMarca;
    @JoinColumn(name = "Producto_idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Producto productoidProducto;

    public DetalleProducto() {
    }

    public DetalleProducto(Integer idDetalleProducto) {
        this.idDetalleProducto = idDetalleProducto;
    }

    public DetalleProducto(Integer idDetalleProducto, String modelo, String serialProducto, boolean estado, boolean borrar) {
        this.idDetalleProducto = idDetalleProducto;
        this.modelo = modelo;
        this.serialProducto = serialProducto;
        this.estado = estado;
        this.borrar = borrar;
    }

    public Integer getIdDetalleProducto() {
        return idDetalleProducto;
    }

    public void setIdDetalleProducto(Integer idDetalleProducto) {
        this.idDetalleProducto = idDetalleProducto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getContadorMaquina() {
        return contadorMaquina;
    }

    public void setContadorMaquina(Integer contadorMaquina) {
        this.contadorMaquina = contadorMaquina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSerialProducto() {
        return serialProducto;
    }

    public void setSerialProducto(String serialProducto) {
        this.serialProducto = serialProducto;
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

    @XmlTransient
    @JsonIgnore
    public List<Asignacion> getAsignacionList() {
        return asignacionList;
    }

    public void setAsignacionList(List<Asignacion> asignacionList) {
        this.asignacionList = asignacionList;
    }

    public Marca getMarcaidMarca() {
        return marcaidMarca;
    }

    public void setMarcaidMarca(Marca marcaidMarca) {
        this.marcaidMarca = marcaidMarca;
    }

    public Producto getProductoidProducto() {
        return productoidProducto;
    }

    public void setProductoidProducto(Producto productoidProducto) {
        this.productoidProducto = productoidProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleProducto != null ? idDetalleProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleProducto)) {
            return false;
        }
        DetalleProducto other = (DetalleProducto) object;
        if ((this.idDetalleProducto == null && other.idDetalleProducto != null) || (this.idDetalleProducto != null && !this.idDetalleProducto.equals(other.idDetalleProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sis.Entidades.DetalleProducto[ idDetalleProducto=" + idDetalleProducto + " ]";
    }
    
}
