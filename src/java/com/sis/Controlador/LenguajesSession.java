package com.sis.Controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


@Named(value = "lenguajesSession")
@SessionScoped
public class LenguajesSession implements Serializable {

    private String localidad;
    private static Map<String, Object> listaIdiomas;
    
    public LenguajesSession() {
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Map<String, Object> getListaIdiomas() {
        return listaIdiomas;
    }

    public static void setListaIdiomas(Map<String, Object> listaIdiomas) {
        LenguajesSession.listaIdiomas = listaIdiomas;
    }
    static {
        listaIdiomas = new LinkedHashMap<String, Object>();
        
        Locale espanol = new Locale("ES");
        listaIdiomas.put("Español", espanol);
        listaIdiomas.put("English", Locale.ENGLISH);
        
        
    }
    public void cambiarIdioma(ValueChangeEvent s){
        String newLocaleValue = s.getNewValue().toString();
        for(Map.Entry<String, Object> entry : listaIdiomas.entrySet()){
            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }
}
