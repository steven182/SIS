package com.sis.Controlador;

import com.sis.Modelo.IdPersonaDao;
import com.sis.Entidades.Marca;
import com.sis.Facade.MarcaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import org.primefaces.event.CloseEvent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

@Named
@SessionScoped
public class MarcaSession implements Serializable {

   
    @EJB
    MarcaFacade marcaFacade;
    private Marca marca;
    private List<Marca> listaMarca;
    private List<SelectItem> listaMarcasActivas;
    
    public MarcaSession() {
    }
    @PostConstruct
    public void init(){
       this.marca =  new Marca();
       listaMarca = new ArrayList<>();
    }

    public List<SelectItem> getListaMarcasActivas() {
        listaMarcasActivas = new ArrayList<>();
        for(Marca marca: marcaFacade.listaMarcasActivas()){
            listaMarcasActivas.add(new SelectItem(marca, marca.getNombreMarca()));
        }
        return listaMarcasActivas;
    }

    public void setListaMarcasActivas(List<SelectItem> listaMarcasActivas) {
        this.listaMarcasActivas = listaMarcasActivas;
    }

    public List<Marca> getListaMarca() {
        return listaMarca;
    }

    public void setListaMarca(List<Marca> listaMarca) {
        this.listaMarca = listaMarca;
    }
    
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    public void mostrarMarca(){
        listaMarca = marcaFacade.listaMarcasActivas();
    }
    public void ingresarMarca(){
        marcaFacade.create(marca);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Marca Registrada Correctamnete"));
        this.listaMarca = marcaFacade.listaMarcasActivas();
        this.marca = new Marca();
    }
    public void editarMarca(Marca marca){
        this.marca = marca;
    }
    public void guardarMarca(Marca m){
        marcaFacade.edit(m);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        this.marca = new Marca();
    }  
    public void cancelar(RowEditEvent event){
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado")); 
    }
    public void eliminarMarca(Marca marca){
        marca.setEstado(false);
        marcaFacade.edit(marca);
        listaMarca = marcaFacade.listaMarcasActivas();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
    }
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return IdPersonaDao.getSelectItems(marcaFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(marcaFacade.listaMarcasActivas(), true);
    }

    public Marca getMarca(java.lang.Integer id) {
        return marcaFacade.find(id);
    }

    @FacesConverter(forClass = Marca.class)
    public static class MarcaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MarcaSession controller = (MarcaSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "marcaSession");
            return controller.getMarca(getKey(value));
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
            if (object instanceof Marca) {
                Marca o = (Marca) object;
                return getStringKey(o.getIdMarca());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Marca.class.getName());
            }
        }

    }
}
