/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.Controlador;

import com.sis.Entidades.Ciudad;
import com.sis.Facade.CiudadFacade;
import com.sis.Modelo.IdPersonaDao;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author ersal
 */
@Named(value = "ciudadSession")
@Dependent
public class CiudadSession {

    /**
     * Creates a new instance of CiudadSession
     */
    public CiudadSession() {
        this.ciudad = new Ciudad();
        listaCiudad = new ArrayList();
    }
    
    @EJB
    CiudadFacade ciudadFacade;
    private Ciudad ciudad;
    private List<Ciudad> listaCiudad;

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return IdPersonaDao.getSelectItems(ciudadFacade.findAll(), false);
    }

    public Ciudad getCiudad(java.lang.Integer id) {
        return ciudadFacade.find(id);
    }

    @FacesConverter(forClass = Ciudad.class)
    public static class CiudadConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CiudadSession controller = (CiudadSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ciudadSession");
            return controller.getCiudad(getKey(value));
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
            if (object instanceof Ciudad) {
                Ciudad o = (Ciudad) object;
                return getStringKey(o.getIdciudad());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ciudad.class.getName());
            }
        }

    }
    
}
