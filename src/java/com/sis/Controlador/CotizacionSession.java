package com.sis.Controlador;

import com.sis.Entidades.Asignacion;
import com.sis.Entidades.Cotizacion;
import com.sis.Entidades.DetalleCotizacion;
import com.sis.Facade.CotizacionFacade;
import com.sis.Facade.DetalleCotizacionFacade;
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

@Named(value = "cotizacionSession")
@SessionScoped
public class CotizacionSession implements Serializable {
    
    @EJB
    CotizacionFacade cotizacionFacade;
    @EJB
    DetalleCotizacionFacade detalleCotizacionFacade;
    @Inject
    AsignacionProductosSession asignacionProducto;
    @Inject
    PersonaSession persona;
    private Cotizacion cotizacion;
    private DetalleCotizacion detalleCotizacion;
    private List<DetalleCotizacion> listaDetalleCotizacion;
    private List<Asignacion> listAsignacion;
    private List<DetalleCotizacion> listaProdCotizacion;
    
    @PostConstruct
    public void init(){
        cotizacion = new Cotizacion();
        detalleCotizacion = new DetalleCotizacion();
        listaDetalleCotizacion = new ArrayList<>();
        listAsignacion = new ArrayList<>();
        listAsignacion = asignacionProducto.getListaAsignacion();
        listaProdCotizacion = new ArrayList<>();
    }

    public CotizacionSession() {
    }

    public List<DetalleCotizacion> getListaProdCotizacion(DetalleCotizacion d) {
        return listaProdCotizacion = detalleCotizacionFacade.listaFiltroClienteServicio(d);
    }

    public void setListaProdCotizacion(List<DetalleCotizacion> listaProdCotizacion) {
        this.listaProdCotizacion = listaProdCotizacion;
    }

    public List<Asignacion> getListAsignacion() {
        return listAsignacion;
    }

    public void setListAsignacion(List<Asignacion> listAsignacion) {
        this.listAsignacion = listAsignacion;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public DetalleCotizacion getDetalleCotizacion() {
        return detalleCotizacion;
    }

    public void setDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
        this.detalleCotizacion = detalleCotizacion;
    }

    public List<DetalleCotizacion> getListaDetalleCotizacion() {
        return listaDetalleCotizacion;
    }

    public void setListaDetalleCotizacion(List<DetalleCotizacion> listaDetalleCotizacion) {
        this.listaDetalleCotizacion = listaDetalleCotizacion;
    }
    
    public void crearCotizacion(){
        cotizacionFacade.create(cotizacion);
        for (Asignacion a : listAsignacion) {
            detalleCotizacion.setCotizacionidCotizacion(cotizacion);
            detalleCotizacion.setEstado("So");
            detalleCotizacion.setAsignacionidAsignacion(a);
            detalleCotizacionFacade.create(detalleCotizacion);

        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exito"));
        cotizacion = new Cotizacion();
        detalleCotizacion = new DetalleCotizacion();
    }
    public void mostrarCot(){
        listaDetalleCotizacion = detalleCotizacionFacade.listaDetCotizacion();
    }
    public void mostrarCotSesion(){
        listaDetalleCotizacion = detalleCotizacionFacade.listaServiciosActivosSesion(persona.getPersonaSesion());
    }
    public void editarOrden(DetalleCotizacion detalle) {
        detalleCotizacionFacade.edit(detalle);
        cotizacionFacade.edit(detalle.getCotizacionidCotizacion());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        //this.ordenServicio = new OrdenServicio();
    }
    public void editEnvioCotAdmin(){
        detalleCotizacion.setEstado("Ge");
        detalleCotizacionFacade.edit(detalleCotizacion);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cotización Enviada con Exito"));
        detalleCotizacion = new DetalleCotizacion();
        cotizacion = new Cotizacion();
    }
    public void editCotizacionAdmin(DetalleCotizacion dc){
        this.detalleCotizacion = dc;
        this.cotizacion = dc.getCotizacionidCotizacion();
    }

    public void cancelar(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }
    
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return IdPersonaDao.getSelectItems(detalleCotizacionFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(detalleCotizacionFacade.findAll(), true);
    }

    public DetalleCotizacion getDetalleCotizacion(java.lang.Integer id) {
        return detalleCotizacionFacade.find(id);
    }

    @FacesConverter(forClass = DetalleCotizacion.class)
    public static class DetalleCotizacionConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CotizacionSession controller = (CotizacionSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cotizacionSession");
            return controller.getDetalleCotizacion(getKey(value));
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
            if (object instanceof DetalleCotizacion) {
                DetalleCotizacion o = (DetalleCotizacion) object;
                return getStringKey(o.getIdDetalleCotizacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetalleCotizacion.class.getName());
            }
        }

    }
}
