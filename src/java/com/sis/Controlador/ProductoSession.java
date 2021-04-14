package com.sis.Controlador;

import com.sis.Modelo.IdPersonaDao;
import com.sis.Entidades.Producto;
import com.sis.Facade.ProductoFacade;
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
public class ProductoSession implements Serializable {

    public ProductoSession() {
    }
    @EJB
    ProductoFacade productoFacade;
    private Producto producto;
    private List<Producto> listaProducto;
    private List<SelectItem> listaProductosActivos;

    @PostConstruct
    public void init() {
        this.producto = new Producto();
        listaProducto = new ArrayList<>();
    }

    public List<SelectItem> getListaProductosActivos() {
        listaProductosActivos = new ArrayList<>();
        for(Producto producto: productoFacade.listaProductosActivos()){
            listaProductosActivos.add(new SelectItem(producto, producto.getNombreProducto()));
        }
        return listaProductosActivos;
    }

    public void setListaProductosActivos(List<SelectItem> listaProductosActivos) {
        this.listaProductosActivos = listaProductosActivos;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void ingresoProducto() {
        productoFacade.create(producto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Registrado"));
        this.listaProducto = productoFacade.listaProductosActivos();
        this.producto = new Producto();
    }

    public void mostrarProductos() {
        listaProducto = productoFacade.listaProductosActivos();
    }

    public void guardarProducto(Producto p) {
        productoFacade.edit(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        this.producto = new Producto();
    }

    public void cancelar2(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

    public void eliminarProducto(Producto producto) {
        producto.setEstado(false);
        productoFacade.edit(producto);
        this.listaProducto = productoFacade.listaProductosActivos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return IdPersonaDao.getSelectItems(productoFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(productoFacade.listaProductosActivos(), true);
    }

    public Producto getProducto(java.lang.Integer id) {
        return productoFacade.find(id);
    }

    @FacesConverter(forClass = Producto.class)
    public static class ProductoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductoSession controller = (ProductoSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productoSession");
            return controller.getProducto(getKey(value));
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
            if (object instanceof Producto) {
                Producto o = (Producto) object;
                return getStringKey(o.getIdProducto());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Producto.class.getName());
            }
        }

    }

}
