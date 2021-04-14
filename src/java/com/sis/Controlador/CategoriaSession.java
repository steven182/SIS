package com.sis.Controlador;

import com.sis.Entidades.Categoria;
import com.sis.Facade.CategoriaFacade;
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
import org.primefaces.event.RowEditEvent;

@Named
@SessionScoped
public class CategoriaSession implements Serializable {

  
     @EJB
    CategoriaFacade categoriaFacade;
    private Categoria categoria;
    private List<Categoria> listaCategoria;
    private List<SelectItem> listaCategoActivas;
     public CategoriaSession() {
    }

    @PostConstruct
    public void init(){
        this.categoria = new Categoria();
        listaCategoria = new ArrayList<>();
    }

    public List<SelectItem> getListaCategoActivas() {
        listaCategoActivas = new ArrayList<>();
        for (Categoria categoria1 : categoriaFacade.listaCategoriasActivas()) {
            listaCategoActivas.add(new SelectItem(categoria1, categoria1.getNombreCategoria()));
        }
        return listaCategoActivas;
    }

    public void setListaCategoActivas(List<SelectItem> listaCategoActivas) {
        this.listaCategoActivas = listaCategoActivas;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public void mostrarCategoria(){
        this.listaCategoria = categoriaFacade.listaCategoriasActivas();
    }
    public void registroCategoria(){
        categoriaFacade.create(categoria);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria Registrada"));
        this.listaCategoria = categoriaFacade.listaCategoriasActivas();
        this.categoria = new Categoria();
    }
    public void editarCategoria(Categoria categoria){
        this.categoria = categoria;
    }
    public void guardarCategoria(Categoria c){
       categoriaFacade.edit(c);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
       this.categoria = new Categoria();
    }
    public void cancelar1(RowEditEvent event){
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado")); 
    }
    public void eliminarCategoria(Categoria categoria){
        categoria.setEstado(false);
        categoriaFacade.edit(categoria);
        listaCategoria = categoriaFacade.listaCategoriasActivas();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
    }
    public SelectItem[] getItemsAvailableSelectMany() {
        return IdPersonaDao.getSelectItems(categoriaFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(categoriaFacade.listaCategoriasActivas(), true);
    }

    public Categoria getCategoria(java.lang.Integer id) {
        return categoriaFacade.find(id);
    }

    @FacesConverter(forClass = Categoria.class)
    public static class CategoriaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoriaSession controller = (CategoriaSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoriaSession");
            return controller.getCategoria(getKey(value));
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
            if (object instanceof Categoria) {
                Categoria o = (Categoria) object;
                return getStringKey(o.getIdCategoria());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Categoria.class.getName());
            }
        }
    }
}
