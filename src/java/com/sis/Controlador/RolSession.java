package com.sis.Controlador;

import com.sis.Entidades.Persona;
import com.sis.Entidades.Rol;
import com.sis.Facade.PersonaFacade;
import com.sis.Facade.RolFacade;
import com.sis.Modelo.IdPersonaDao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
import javax.servlet.http.Part;
import org.primefaces.event.RowEditEvent;

@Named
@SessionScoped
public class RolSession implements Serializable {

    public RolSession() {
    }
    @EJB
    RolFacade rolFacade;
    private Rol rol;
    private List<Rol> listaRol;
    private List<Rol> listaFiltro = new ArrayList<>();
    private Part file;
    private String nombre;
    private String pathReal;

    public List<Rol> getListaFiltro() {
        return listaFiltro;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    public void setListaFiltro(List<Rol> listaFiltro) {
        this.listaFiltro = listaFiltro;
    }

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @PostConstruct
    public void init() {
        rol = new Rol();
        //this.listaRol = new ArrayList<>();
        listaRol = rolFacade.findAll();
    }

    public void editarRol(Rol rol) {
        this.rol = rol;
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(rolFacade.findAll(), true);
    }

    public Rol getRol(java.lang.Integer id) {
        return rolFacade.find(id);
    }

    @FacesConverter(forClass = Rol.class)
    public static class RolConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolSession controller = (RolSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolSession");
            return controller.getRol(getKey(value));
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
            if (object instanceof Rol) {
                Rol o = (Rol) object;
                return getStringKey(o.getIdRol());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Rol.class.getName());
            }
        }

    }

    public void mostrarRol() {
        this.listaRol = rolFacade.findAll();
    }

    public void registrarRol() {
        this.rolFacade.create(rol);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Rol Registrado Correctamete"));
        this.listaRol = rolFacade.findAll();
        this.rol = new Rol();
    }

    public void actualizaRol(Rol rol) {
        rolFacade.edit(rol);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        this.rol = new Rol();
    }

    public void cancela(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));

    }

    public void eliminarRol(Rol rol) {
        this.rolFacade.remove(rol);
        listaRol = rolFacade.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
    }

    public void cargarArchivos() {
        if (file.getContentType().equalsIgnoreCase("application/vnd.ms-excel")) {
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
            path = path.substring(0, path.indexOf("\\build"));
            path = path + "\\web\\Archivos\\";
            try {
                this.nombre = file.getSubmittedFileName();
                pathReal = "/Sis2/Archivos/" + nombre;
                path = path + this.nombre;
                InputStream in = file.getInputStream();

                byte[] data = new byte[in.available()];
                in.read(data);
                File archivo = new File(path);
                FileOutputStream out = new FileOutputStream(archivo);
                out.write(data);
                in.close();
                out.close();
                path = path.replace("\\", "\\\\");
                rolFacade.cargaDatos(path, "rol");
                //archivo.delete();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Roles Añadidos con Exito"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Extensión invalida, solo se permiten .csv"));
        }

    }
}
