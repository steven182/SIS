package com.sis.Controlador;

import com.sis.Entidades.CalificacionServicio;
import com.sis.Facade.AsignacionTecnicoFacade;
import com.sis.Facade.CalificacionServicioFacade;
import com.sis.Facade.OrdenServicioFacade;
import com.sis.Facade.PersonaFacade;
import com.sis.Modelo.IdPersonaDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

@Named(value = "calificacionServicioSession")
@SessionScoped
public class CalificacionServicioSession implements Serializable {

    @EJB
    CalificacionServicioFacade calificacionFacade;
    @EJB
    OrdenServicioFacade ordenServicioFacade;
    @EJB
    PersonaFacade personaFacade;
    @EJB
    AsignacionTecnicoFacade asignacionTecnico;
    private CalificacionServicio calificacionServicio;
    private List<CalificacionServicio> listaCalificacion;
    
    public CalificacionServicioSession() {
        calificacionServicio = new CalificacionServicio();
        listaCalificacion = new ArrayList<>();
    }

    public CalificacionServicio getCalificacionServicio() {
        return calificacionServicio;
    }

    public void setCalificacionServicio(CalificacionServicio calificacionServicio) {
        this.calificacionServicio = calificacionServicio;
    }

    public List<CalificacionServicio> getListaCalificacion() {
        return listaCalificacion;
    }

    public void setListaCalificacion(List<CalificacionServicio> listaCalificacion) {
        this.listaCalificacion = listaCalificacion;
    }
    public void calificar(){
        calificacionServicio.getOrdenServicioidOrdenServicio().getPersonaidPersona().setPermisoProducto(true);
        calificacionServicio.getOrdenServicioidOrdenServicio().getAsignaciontecnicoidAsignacionTecnico().setDisponibilidad(true);
        calificacionServicio.getOrdenServicioidOrdenServicio().setEstadoServicio("Ca");
        ordenServicioFacade.edit(calificacionServicio.getOrdenServicioidOrdenServicio());
        personaFacade.edit(calificacionServicio.getOrdenServicioidOrdenServicio().getPersonaidPersona());
        asignacionTecnico.edit(calificacionServicio.getOrdenServicioidOrdenServicio().getAsignaciontecnicoidAsignacionTecnico());
        calificacionFacade.create(calificacionServicio);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servicio Calificado"));
        calificacionServicio = new CalificacionServicio();
    }
    public SelectItem[] getItemsAvailableSelectMany() {
        return IdPersonaDao.getSelectItems(calificacionFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(calificacionFacade.findAll(), true);
    }

    public CalificacionServicio getCalificacion(java.lang.Integer id) {
        return calificacionFacade.find(id);
    }

    @FacesConverter(forClass = CalificacionServicio.class)
    public static class CalificacionConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CalificacionServicioSession controller = (CalificacionServicioSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "calificacionServicioSession");
            return controller.getCalificacion(getKey(value));
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
            if (object instanceof CalificacionServicio) {
                CalificacionServicio o = (CalificacionServicio) object;
                return getStringKey(o.getIdCalificacionServicio());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CalificacionServicio.class.getName());
            }
        }
    }
}
