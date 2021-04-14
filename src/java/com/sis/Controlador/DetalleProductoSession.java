package com.sis.Controlador;

import com.sis.Entidades.DetalleProducto;
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

@Named(value = "detalleProductoSession")
@SessionScoped
public class DetalleProductoSession implements Serializable {

   
    @EJB
    DetalleProductoFacade detalleProductoFacade;
    @Inject private CargaArchivos cargaArchivos;
    private DetalleProducto detalleProducto;
    private List<DetalleProducto> listaDetalleProducto;
    private List<SelectItem> listaDetatProducDisponible;
    
     public DetalleProductoSession() {
    }

      @PostConstruct
     public void init(){
         this.detalleProducto = new DetalleProducto();
         listaDetalleProducto = new ArrayList<>();
     }

    public CargaArchivos getCargaArchivos() {
        return cargaArchivos;
    }

    public void setCargaArchivos(CargaArchivos cargaArchivos) {
        this.cargaArchivos = cargaArchivos;
    }

     public List<DetalleProducto> listPrueba(){
         return detalleProductoFacade.findAll();
     }
    public List<SelectItem> getListaDetatProducDisponible() {
        listaDetatProducDisponible = new ArrayList<>();
        for (DetalleProducto detalleProducto1 : detalleProductoFacade.listaDetalleProdActivos()) {
            listaDetatProducDisponible.add(new SelectItem(detalleProducto1,detalleProducto1.getModelo()));
        }
        return listaDetatProducDisponible;
    }

    public void setListaDetatProducDisponible(List<SelectItem> listaDetatProducDisponible) {
        this.listaDetatProducDisponible = listaDetatProducDisponible;
    }
     
    public DetalleProducto getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(DetalleProducto detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    public List<DetalleProducto> getListaDetalleProducto() {
        return listaDetalleProducto;
    }

    public void setListaDetalleProducto(List<DetalleProducto> listaDetalleProducto) {
        this.listaDetalleProducto = listaDetalleProducto;
    }
    public void ingresarProducto(){
        detalleProductoFacade.create(detalleProducto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Ingresado Correctamente"));
        this.listaDetalleProducto = detalleProductoFacade.listaDetalleProdBorrar();
        this.detalleProducto = new DetalleProducto();
    }
    public void mostarProductos(){
        this.listaDetalleProducto = detalleProductoFacade.listaDetalleProdBorrar();
    }
    public void actualizaProducto(DetalleProducto producto){
        detalleProductoFacade.edit(producto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        this.detalleProducto = new DetalleProducto();
    }
    public void cancelar(RowEditEvent event){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    } 
    public void eliminarProducto(DetalleProducto producto){
        producto.setBorrar(false);
        detalleProductoFacade.edit(producto);
        listaDetalleProducto = detalleProductoFacade.listaDetalleProdBorrar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
    }
    public void cargarProductos(){
        System.out.println(cargaArchivos.getFile3().getContentType());
        if (cargaArchivos.getFile3().getContentType().equalsIgnoreCase("application/vnd.ms-excel")) {
            cargaArchivos.cargarArchivos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Productos Añadidos con Exito"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Extensión invalida, solo se permiten .csv"));
 
        }
        
    }
    public SelectItem[] getItemsAvailableSelectMany() {
        return IdPersonaDao.getSelectItems(detalleProductoFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(detalleProductoFacade.findAll(), true);
    }

    public DetalleProducto getDetalleProducto(java.lang.Integer id) {
        return detalleProductoFacade.find(id);
    }

    @FacesConverter("detalleProductoSession")
    public static class DetalleProductoConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleProductoSession controller = (DetalleProductoSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleProductoSession");
            return controller.getDetalleProducto(getKey(value));
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
            if (object instanceof DetalleProducto) {
                DetalleProducto o = (DetalleProducto) object;
                return getStringKey(o.getIdDetalleProducto());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetalleProducto.class.getName());
            }
        }

    }

}
