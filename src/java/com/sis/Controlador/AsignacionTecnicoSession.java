package com.sis.Controlador;

import com.sis.Entidades.AsignacionTecnico;
import com.sis.Entidades.Persona;
import com.sis.Facade.AsignacionTecnicoFacade;
import com.sis.Modelo.IdPersonaDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@Named(value = "asignacionTecnicoSession")
@SessionScoped
public class AsignacionTecnicoSession implements Serializable {

  
    @EJB
    AsignacionTecnicoFacade asignacionTecnicoFacade;
    @Inject private PersonaSession personaSession;
    private AsignacionTecnico asignacionTecnico;
    private List<AsignacionTecnico> listaAsigancion;
    private List<SelectItem> listaTecnicosDisponiblesSession;
    private List<SelectItem> listaTecnicosDisponiblesTodos;
 
    
    public AsignacionTecnicoSession() {
        asignacionTecnico = new AsignacionTecnico();
    }

    public List<SelectItem> getListaTecnicosDisponiblesTodos() {
        listaTecnicosDisponiblesTodos = new ArrayList<>();
        for (AsignacionTecnico l : asignacionTecnicoFacade.listaTecnicosDispSessionTodos()) {
            listaTecnicosDisponiblesTodos.add(new SelectItem(l, l.getPersonaidPersona().getPrimernombre() + " " 
                    + l.getPersonaidPersona().getPrimerapellido()));
        }
        return listaTecnicosDisponiblesTodos;
    }

    public void setListaTecnicosDisponiblesTodos(List<SelectItem> listaTecnicosDisponiblesTodos) {
        this.listaTecnicosDisponiblesTodos = listaTecnicosDisponiblesTodos;
    }

    public PersonaSession getPersonaSession() {
        return personaSession;
    }

    public void setPersonaSession(PersonaSession personaSession) {
        this.personaSession = personaSession;
    }

    public List<SelectItem> getListaTecnicosDisponibles() {
        listaTecnicosDisponiblesSession = new ArrayList<>();
        for (AsignacionTecnico a : asignacionTecnicoFacade.listaTecnicosDispSession(personaSession.getPersonaSesion())) {
            listaTecnicosDisponiblesSession.add(new SelectItem(a, a.getPersonaidPersona().getPrimernombre() + " " 
                    + a.getPersonaidPersona().getPrimerapellido()));
        }
        return listaTecnicosDisponiblesSession;
    }

    public void setListaTecnicosDisponibles(List<SelectItem> listaTecnicosDisponibles) {
        this.listaTecnicosDisponiblesSession = listaTecnicosDisponibles;
    }

    public List<AsignacionTecnico> getListaAsigancion() {
        return listaAsigancion;
    }

    public void setListaAsigancion(List<AsignacionTecnico> listaAsigancion) {
        this.listaAsigancion = listaAsigancion;
    }
    
    public AsignacionTecnico getAsignacionTecnico() {
        return asignacionTecnico;
    }

    public void setAsignacionTecnico(AsignacionTecnico asignacionTecnico) {
        this.asignacionTecnico = asignacionTecnico;
    }
    @PostConstruct
    public void init(){
        asignacionTecnico = new AsignacionTecnico();
        listaAsigancion = asignacionTecnicoFacade.findAll();
    }
    public void guardarAsignacion(){
        asignacionTecnicoFacade.create(asignacionTecnico);
        asignacionTecnico = new AsignacionTecnico();
    }
    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(asignacionTecnicoFacade.findAll(), true); 
    }

    public AsignacionTecnico getAsignaciontecnico(java.lang.Integer id) {
        return asignacionTecnicoFacade.find(id);
    }

    @FacesConverter(forClass = AsignacionTecnico.class)
    public static class AsignacionTecnicoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsignacionTecnicoSession controller = (AsignacionTecnicoSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asignacionTecnicoSession");
            return controller.getAsignaciontecnico(getKey(value));
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
            if (object instanceof AsignacionTecnico) {
                AsignacionTecnico o = (AsignacionTecnico) object;
                return getStringKey(o.getIdAsignacionTecnico());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + AsignacionTecnico.class.getName());
            }
        }

    }
    
    public void asignarTecnico(Persona persona){
        asignacionTecnico.setDisponibilidad(true);
        asignacionTecnico.setEstado(true);
        asignacionTecnico.setPersonaidPersona(persona);
        asignacionTecnicoFacade.create(asignacionTecnico);
    }
    
}
