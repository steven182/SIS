package com.sis.Controlador;

import com.sis.Entidades.Asignacion;
import com.sis.Entidades.DetalleOrdenServicio;
import com.sis.Entidades.OrdenServicio;
import com.sis.Facade.AsignacionFacade;
import com.sis.Facade.DetalleOrdenServicioFacade;
import com.sis.Facade.DetalleProductoFacade;
import com.sis.Facade.OrdenServicioFacade;
import com.sis.Modelo.IdPersonaDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

@Named(value = "detalleOrdenServicioSession")
@SessionScoped
public class DetalleOrdenServicioSession implements Serializable {

    @EJB
    DetalleOrdenServicioFacade detalleServicioFacade;
    @EJB
    DetalleProductoFacade detalleProductoFacade;
    @EJB
    OrdenServicioFacade ordenServicioFacade;
    @EJB
    AsignacionFacade asignacionfacade;
    @Inject
    PersonaSession persona;
    @Inject
    AsignacionProductosSession asignacionProducto;
    private DetalleOrdenServicio detalleServicio;
    private List<DetalleOrdenServicio> listaDetalleOrden;
    private List<DetalleOrdenServicio[]> listaObjetos;
    private List<DetalleOrdenServicio> listaAgr;
    private OrdenServicio ordenServicio;
    private List<Asignacion> listaAsignacion;

    public DetalleOrdenServicioSession() {
    }

    public List<Asignacion> getListaAsignacion() {
        return listaAsignacion;
    }

    public void setListaAsignacion(List<Asignacion> listaAsignacion) {
        this.listaAsignacion = listaAsignacion;
    }

    public OrdenServicio getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public void setListaAgr(List<DetalleOrdenServicio> listaAgr) {
        this.listaAgr = listaAgr;
    }

    public List<DetalleOrdenServicio[]> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(List<DetalleOrdenServicio[]> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    @PostConstruct
    public void init() {
        detalleServicio = new DetalleOrdenServicio();
        listaDetalleOrden = new ArrayList<>();
        listaAgr = new ArrayList<>();
        ordenServicio = new OrdenServicio();
        listaAsignacion = new ArrayList<>();
        listaAsignacion = asignacionProducto.getListaAsignacion();
    }
    public List<DetalleOrdenServicio> getListaDetalleOrden() {
        return listaDetalleOrden;

    }
    public void setListaDetalleOrden(List<DetalleOrdenServicio> listaDetalleOrden) {
        this.listaDetalleOrden = listaDetalleOrden;
    }

    public DetalleOrdenServicio getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(DetalleOrdenServicio detalleServicio) {
        this.detalleServicio = detalleServicio;
    }
    public void asignarServicio(){
            detalleServicio.setOrdenServicioidOrdenServicio(ordenServicio);
            detalleServicio.getOrdenServicioidOrdenServicio().setEstadoServicio("S");
            ordenServicioFacade.edit(detalleServicio.getOrdenServicioidOrdenServicio());
        for (Asignacion l : listaAsignacion) {
            detalleServicio.setAsignacionidAsignacion(l);
            detalleServicio.getAsignacionidAsignacion().setEstado(false);

            asignacionfacade.edit(detalleServicio.getAsignacionidAsignacion());

            detalleServicioFacade.create(detalleServicio);        
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Productos Asignados Correctamente"));
        asignacionProducto.getListaAsignarServicio().clear();
    }

    public void obtenerInfo(){
        asignacionProducto.asignarServicio(ordenServicio);
    }
    public void prodEditTecnico(){
        listaDetalleOrden = detalleServicioFacade.listaProdEditTecnico(persona.getPersonaSesion());
    }
    public void editarDetalleOrden(DetalleOrdenServicio detalle) {
        detalleServicioFacade.edit(detalle);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        detalleServicio = new DetalleOrdenServicio();
    }

    public void cancelar(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return IdPersonaDao.getSelectItems(detalleServicioFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(detalleServicioFacade.findAll(), true);
    }

    public DetalleOrdenServicio getDetalleOrdenServicio(java.lang.Integer id) {
        return detalleServicioFacade.find(id);
    }

    @FacesConverter(forClass = DetalleOrdenServicio.class)
    public static class DetalleOrdenServicioConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleOrdenServicioSession controller = (DetalleOrdenServicioSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleOrdenServicioSession");
            return controller.getDetalleOrdenServicio(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetalleOrdenServicio) {
                DetalleOrdenServicio o = (DetalleOrdenServicio) object;
                return getStringKey(o.getIdDetalleOrdenServicio());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetalleOrdenServicio.class.getName());
            }
        }

    }

}
