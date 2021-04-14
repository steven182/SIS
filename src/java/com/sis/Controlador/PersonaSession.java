package com.sis.Controlador;

import com.sis.Modelo.IdPersonaDao;
import com.sis.Entidades.Persona;
import com.sis.Facade.PersonaFacade;
import com.sis.Facade.RolFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@SessionScoped
public class PersonaSession implements Serializable {

    private UploadedFile foto;

    @EJB
    PersonaFacade personaFacade; // declarar una variable de tipo PersonaFacade
    @EJB
    RolFacade rolFacade;
    @Inject
    private AsignacionTecnicoSession asignacionTecnico;
    @Inject
    private CargarArchivosControlador cargaArchivos;
    private Persona persona; //Declarar una variable de tipo Persona
    private List<Persona> listaPersona;
    private List<Persona> listaPersona2;
    private Persona personaSesion; // variable para obtener los datos de la persona dentro de la sesion
    private List<SelectItem> listTech;
    private List<SelectItem> listaClientes;
    private List<SelectItem> listaUserSesion;
    private List<SelectItem> listaClienteAsignacionProductos;
    private String asunto, mensaje;
    private List<Persona> listaCorreos;
    private List<Persona> listaPersonaAct;
    private List<SelectItem> listaUsuarioSessionContrato;
    private List<SelectItem> listaClientesContrato;

    Mailer mail;
    private String correo, correoRep, clave, claveRep;

    private String claveactual, nuevaclave, nuevaclaveRep;

    public PersonaSession() {

    }

    @PostConstruct
    public void init() {
        this.persona = new Persona(); //instanciar un objeto de tipo Persona
        this.listaCorreos = new ArrayList<>();
        listaPersonaAct = new ArrayList<>();
    }

    public List<SelectItem> getListaClientesContrato() {
        listaClientesContrato = new ArrayList<>();
        for (Persona per : personaFacade.listaClientesContrato()) {
            listaClientesContrato.add(new SelectItem(per, per.getPrimernombre() + " " + per.getPrimerapellido()));
        }
        return listaClientesContrato;
    }

    public void setListaClientesContrato(List<SelectItem> listaClientesContrato) {
        this.listaClientesContrato = listaClientesContrato;
    }

    public List<SelectItem> getListaUsuarioSessionContrato() {
        listaUsuarioSessionContrato = new ArrayList<>();
        for (Persona pr : personaFacade.listaUsuariosSesionContrato(personaSesion)) {
            listaUsuarioSessionContrato.add(new SelectItem(pr, pr.getPrimernombre() + " " + pr.getPrimerapellido()));
        }
        return listaUsuarioSessionContrato;
    }

    public void setListaUsuarioSessionContrato(List<SelectItem> listaUsuarioSessionContrato) {
        this.listaUsuarioSessionContrato = listaUsuarioSessionContrato;
    }

    public List<Persona> getListaPersonaAct() {
        return listaPersonaAct;
    }

    public void setListaPersonaAct(List<Persona> listaPersonaAct) {
        this.listaPersonaAct = listaPersonaAct;
    }

    public List<Persona> getListaPersona2() {
        return listaPersona2;
    }

    public void setListaPersona2(List<Persona> listaPersona2) {
        this.listaPersona2 = listaPersona2;
    }

    public List<Persona> getListaCorreos() {
        return listaCorreos;
    }

    public void setListaCorreos(List<Persona> listaCorreos) {
        this.listaCorreos = listaCorreos;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<SelectItem> getListaClienteAsignacionProductos() {
        listaClienteAsignacionProductos = new ArrayList<>();
        for (Persona p : personaFacade.listaClientesAsignacionProd()) {
            listaClienteAsignacionProductos.add(new SelectItem(p, p.getPrimernombre() + " " + p.getPrimerapellido()));
        }
        return listaClienteAsignacionProductos;
    }

    public void setListaClienteAsignacionProductos(List<SelectItem> listaClienteAsignacionProductos) {
        this.listaClienteAsignacionProductos = listaClienteAsignacionProductos;
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    public List<SelectItem> getListaUserSesion() {
        listaUserSesion = new ArrayList<>();
        for (Persona p : personaFacade.listaUsuariosSesion(personaSesion)) {
            listaUserSesion.add(new SelectItem(p, p.getPrimernombre() + " " + p.getPrimerapellido()));
        }
        return listaUserSesion;
    }

    public void setListaUserSesion(List<SelectItem> listaUserSesion) {
        this.listaUserSesion = listaUserSesion;
    }

    public List<SelectItem> getListaClientes() {
        listaClientes = new ArrayList<>();
        for (Persona cliente : personaFacade.listaClientes()) {
            listaClientes.add(new SelectItem(cliente, cliente.getPrimernombre() + " " + cliente.getPrimerapellido()));
        }
        return listaClientes;
    }

    public String getNuevaclave() {
        return nuevaclave;
    }

    public void setNuevaclave(String nuevaclave) {
        this.nuevaclave = nuevaclave;
    }

    public String getNuevaclaveRep() {
        return nuevaclaveRep;
    }

    public void setNuevaclaveRep(String nuevaclaveRep) {
        this.nuevaclaveRep = nuevaclaveRep;
    }

    public String getClaveactual() {
        return claveactual;
    }

    public void setClaveactual(String claveactual) {
        this.claveactual = claveactual;
    }

    public void setListaClientes(List<SelectItem> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<SelectItem> getListTech() {
        listTech = new ArrayList<>();
        for (Persona tec : personaFacade.listaTecnicosActivos()) {
            listTech.add(new SelectItem(tec, tec.getPrimernombre() + " " + tec.getPrimerapellido()));
        }
        return listTech;
    }

    public void setListTech(List<SelectItem> listTech) {
        this.listTech = listTech;
    }

    public CargarArchivosControlador getCargaArchivos() {
        return cargaArchivos;
    }

    public void setCargaArchivos(CargarArchivosControlador cargaArchivos) {
        this.cargaArchivos = cargaArchivos;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreoRep() {
        return correoRep;
    }

    public void setCorreoRep(String correoRep) {
        this.correoRep = correoRep;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveRep() {
        return claveRep;
    }

    public void setClaveRep(String claveRep) {
        this.claveRep = claveRep;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public Persona getPersonaSesion() {
        return personaSesion;
    }

    public void setPersonaSesion(Persona personaSesion) {
        this.personaSesion = personaSesion;
    }

    public String iniciarSesion() {
        personaSesion = new Persona(); //instanacia de personaSesion
        String redireccion = null;
        try {
            personaSesion = personaFacade.iniciarSesion(persona);
            persona = new Persona();
            if (personaSesion == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Usuario y/o  clave incorrectos"));
                return null;
            }
            if (personaSesion.getEstado() == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Usuario inactivo"));
                return null;
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLog", personaSesion);
            redireccion = "/Admin/Inicio?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        return redireccion;
    }

    public String registroCliente() throws UnsupportedEncodingException {
        if (!(correo.equals(correoRep))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los correos no coinciden"));
            return null;
        }
        //Correos.send(correo, "Registro Satisfactorio", "Usted se ha registrado exitosamente");
        persona.setFoto("indice.png");
        persona.setEstado(true);
        persona.setCorreo(correo);
        persona.setContrasena(clave);
        persona.setPermisoProducto(true);
        persona.setRolIdRol(rolFacade.getCliente());
        personaFacade.create(persona);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Registro Satisfactorio"));
        this.persona = new Persona();
        return "login?faces-redirect=true";
    }

    public String registroPersona() throws UnsupportedEncodingException {
        if (!(correo.equals(correoRep))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los correos no coinciden"));
            return null;
        }
        if (!(correo.equals(correoRep))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los correos no coinciden"));
            return null;
        }
        //Correos.send(correo, "Registro Satisfactorio", "Usted se ha registrado exitosamente");
        persona.setFoto("indice.png");
        persona.setEstado(true);
        persona.setCorreo(correo);
        persona.setContrasena(clave);
        persona.setRolIdRol(rolFacade.getCliente());
        personaFacade.create(persona);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Registro Satisfactorio"));
        String redireccion = "login?faces-redirect=true";
        this.persona = new Persona();
        return redireccion;
    }

    public void mostrarSession() {
        Persona personaSesionActiva = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLog");
    }

    public String cerrarSession() {
        String redireccion = null;
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            redireccion = "/login?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
        return redireccion;
    }

    public void editarUsuario(Persona persona) {
        this.persona = persona;
    }

    public void mostrarUsuario() {
        this.listaPersona = personaFacade.findAll();
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(personaFacade.findAll(), true);

    }

    public Persona getPersona(java.lang.Integer id) {
        return personaFacade.find(id);
    }

    @FacesConverter(forClass = Persona.class)
    public static class PersonaSessionConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaSession controller = (PersonaSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaSession");
            return controller.getPersona(getKey(value));
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
            if (object instanceof Persona) {
                Persona o = (Persona) object;
                return getStringKey(o.getIdPersona());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Persona.class.getName());
            }
        }
    }

    public void ingresarNuevoUsuario() {
        this.personaFacade.create(persona);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Creado"));
        this.listaPersona = personaFacade.findAll();
        this.persona = new Persona();
    }

    public void guardarPersona(Persona p) {
        personaFacade.edit(p);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        this.persona = new Persona();
    }

    public void cancelar2(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

    public void eliminarPersona(Persona persona) {
        this.personaFacade.remove(persona);
        listaPersona = personaFacade.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
    }

    public void cambiarFoto() {
        if ((cargaArchivos.getFile().getContentType().equals("image/jpeg")) || (cargaArchivos.getFile().getContentType().equals("image/jpg"))
                || (cargaArchivos.getFile().getContentType().equals("image/png"))) {
            cargaArchivos.subirFoto();
            personaSesion.setFoto(cargaArchivos.getPathReal());
            personaSesion.setPermisoProducto(true);
            personaFacade.edit(personaSesion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Foto cambiada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Extensión invalida, solo se permiten .jpg .png .jpeg "));
        }

    }

    public void listarUsuariosActivos() {
        listaPersona2 = personaFacade.listaUsuariosActivos();
    }

    public void listarUsuariosInactivos() {
        listaPersona = personaFacade.listaUsuariosInactivos();
    }

    public void cambiarClave() {
        if (!(nuevaclave.equals(nuevaclaveRep))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La clave nueva debe ser igual en los dos campos"));
        }
        else if (!(claveactual.equals(personaSesion.getContrasena()))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La clave actual no coincide con la de su usuario"));
        }

        else if ((nuevaclave.equals(nuevaclaveRep)) && (claveactual.equals(personaSesion.getContrasena()))) {
            personaSesion.setContrasena(nuevaclave);
            personaFacade.edit(personaSesion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contraseña cambiada"));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Los datos no coinciden"));
        }
    }

    public void personaActiva() {
        listaPersonaAct = personaFacade.listaUsuariosSesion(personaSesion);
    }

    public void listarTecnicosActivos() {
        listaPersona = personaFacade.listaTecnicosActivos();
    }

    public void listarTecnicosInactivos() {
        listaPersona = personaFacade.listaTecnicosInactivos();
    }

    public void inhabilitarTecnico(Persona persona) {
        persona.setEstado(false);
        personaFacade.edit(persona);
        listaPersona2 = personaFacade.listaUsuariosActivos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Técnico Inhabilitado"));
        //return "/GestUsuarios";
    }

    public void habilitarTecnico(Persona persona) {
        persona.setEstado(true);
        personaFacade.edit(persona);
        listaPersona = personaFacade.listaUsuariosInactivos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Técnico Habilitado"));
        //return "/GestUsuarios";
    }

    public String registroTecnico() throws UnsupportedEncodingException {
        if (!(correo.equals(correoRep))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los correos no coinciden"));
            return null;
        }
        if (!(correo.equals(correoRep))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los correos no coinciden"));
            return null;
        }
        //Correos.send(correo, "Registro Satisfactorio", "Usted se ha registrado exitosamente");
        persona.setFoto("indice.png");
        persona.setEstado(true);
        persona.setCorreo(correo);
        persona.setContrasena(clave);
        persona.setRolIdRol(rolFacade.getTecnico());
        personaFacade.create(persona);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Registro Satisfactorio"));
        asignacionTecnico.asignarTecnico(persona);
        String redireccion = "login?faces-redirect=true";
        this.persona = new Persona();
        return "/GestUsuarios";
    }

    public void listarClientes() {
        listaPersona = personaFacade.listaClientes();
    }

    public List<Persona> listaClientesContrato() {
        return personaFacade.findAll();
    }

    public void enviarCorreoMasivo() throws UnsupportedEncodingException {
        try {
            listaCorreos = personaFacade.listarUsuarios();
            for (int i = 0; i < listaCorreos.size(); i++) {
                MailerMasivo.send(listaCorreos.get(i).getCorreo(), asunto, mensaje);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Los correos se enviaron exitosamente"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error " + e));
        }
    }

}
