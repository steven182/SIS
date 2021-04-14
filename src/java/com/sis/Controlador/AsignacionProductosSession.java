package com.sis.Controlador;

import com.sis.Entidades.Asignacion;
import com.sis.Entidades.DetalleProducto;
import com.sis.Entidades.OrdenServicio;
import com.sis.Facade.AsignacionFacade;
import com.sis.Facade.DetalleProductoFacade;
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

@Named(value = "asignacionProductosSession")
@SessionScoped
public class AsignacionProductosSession implements Serializable {

    @EJB
    AsignacionFacade asignacionFacade;
    @EJB
    DetalleProductoFacade detalleProdcutoFacade;
    @Inject
    private DetalleProductoSession detalleProdcuto;
    private Asignacion asignacion;
    private List<Asignacion> listaAsignacion;
    private List<DetalleProducto> listProd;
    private List<SelectItem> listaAsignarServicio;
    private List<Asignacion> listaProdCliente;
    private List<SelectItem> listaProductosAdmin;
    
    public AsignacionProductosSession() {
    }
    @PostConstruct
    public void init(){
        asignacion = new Asignacion();
        listaAsignacion = new ArrayList<>();
        listProd = new ArrayList<>();
        listProd = detalleProdcuto.getListaDetalleProducto();
        listaProdCliente = new ArrayList<>();
    }

    public List<SelectItem> getListaProductosAdmin() {
        listaProductosAdmin = new ArrayList<>();
        for (Asignacion a : asignacionFacade.listaProductosAdmin()) {
            listaProductosAdmin.add(new SelectItem(a, a.getDetalleProductoidDetalleProducto().getModelo()));
        }
        return listaProductosAdmin;
    }

    public void setListaProductosAdmin(List<SelectItem> listaProductosAdmin) {
        this.listaProductosAdmin = listaProductosAdmin;
    }

    public List<Asignacion> getListaProdCliente(DetalleProducto o) {
        return listaProdCliente = asignacionFacade.listaDetailProd(o);
    }

    public void setListaProdCliente(List<Asignacion> listaProdCliente) {
        this.listaProdCliente = listaProdCliente;
    }


    public List<SelectItem> getListaAsignarServicio() {
        return listaAsignarServicio;
    }

    public void setListaAsignarServicio(List<SelectItem> listaAsignarServicio) {
        this.listaAsignarServicio = listaAsignarServicio;
    }

    public DetalleProductoSession getDetalleProdcuto() {
        return detalleProdcuto;
    }

    public void setDetalleProdcuto(DetalleProductoSession detalleProdcuto) {
        this.detalleProdcuto = detalleProdcuto;
    }

    public List<DetalleProducto> getListProd() {
        return listProd;
    }

    public void setListProd(List<DetalleProducto> listProd) {
        this.listProd = listProd;
    }
    
    public Asignacion getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }

    public List<Asignacion> getListaAsignacion() {
        return listaAsignacion;
    }

    public void setListaAsignacion(List<Asignacion> listaAsignacion) {
        this.listaAsignacion = listaAsignacion;
    }
    public void asignarServicio(OrdenServicio o){
        listaAsignarServicio = new ArrayList<>();
        for (Asignacion a : asignacionFacade.listaAsignarServicio(o)) {
            listaAsignarServicio.add(new SelectItem(a, a.getDetalleProductoidDetalleProducto().getModelo()));
        }
    }
    public void asignarProductos(){
        for (DetalleProducto d : listProd) {
            asignacion.setDetalleProductoidDetalleProducto(d);
            asignacion.getDetalleProductoidDetalleProducto().setEstado(false);
            detalleProdcutoFacade.edit(asignacion.getDetalleProductoidDetalleProducto());
            asignacionFacade.create(asignacion);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Productos Asignados Correctamente"));
    }
    public void mostarAsignacion(){
        listaAsignacion = asignacionFacade.listaBorrar();
    }
    public void editarAsignacion(Asignacion a){
        asignacionFacade.edit(a);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        asignacion = new Asignacion();
    }
    public void cancelar(RowEditEvent event){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }
    public void eliminarAsignacion(Asignacion a){
        a.setBorrar(false);
        asignacionFacade.edit(a);
        listaAsignacion = asignacionFacade.listaBorrar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
    }
    public SelectItem[] getItemsAvailableSelectMany() {
        return IdPersonaDao.getSelectItems(asignacionFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(asignacionFacade.findAll(), true);
    }

    public Asignacion getAsignacionProducto(java.lang.Integer id) {
        return asignacionFacade.find(id);
    }

    @FacesConverter("asignacion")
    public static class AsignacionProductoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsignacionProductosSession controller = (AsignacionProductosSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asignacionProductosSession");
            return controller.getAsignacionProducto(getKey(value));
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
            if (object instanceof Asignacion) {
                Asignacion o = (Asignacion) object;
                return getStringKey(o.getIdAsignacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Asignacion.class.getName());
            }
        }

    }
}
