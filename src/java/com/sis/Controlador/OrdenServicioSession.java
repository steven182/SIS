package com.sis.Controlador;

import com.sis.Entidades.OrdenServicio;
import com.sis.Facade.AsignacionTecnicoFacade;
import com.sis.Facade.OrdenServicioFacade;
import com.sis.Facade.PersonaFacade;
import com.sis.Modelo.IdPersonaDao;
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
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

@Named
@SessionScoped

public class OrdenServicioSession implements Serializable {

    @EJB
    OrdenServicioFacade ordenFacade;
    @EJB
    AsignacionTecnicoFacade asignacionTecnico;
    @EJB
    PersonaFacade personaFacade;
    @Inject
    PersonaSession persona;
    @Inject
    private CargaArchivos cargaArchivos;
    private OrdenServicio ordenServicio;
    private List<OrdenServicio> listaServicios;
    private List<SelectItem> listaServiciosDisp; //lista para el campo borrar
    private List<SelectItem> listaServiciosActivos; //lista para la vista AgregarProductos.xhtml
    private List<SelectItem> listaServiciosSesion; // lista para la vista Calificacion.xhtml
    private List<SelectItem> listaServicioActivosTodos; // lista para la vista Agregarproductos.xhtml
    private List<String> listaProdSolicitados;
    private List<OrdenServicio> listaServiciosPorCliente;
    private List<OrdenServicio> listaPrueba;

    public OrdenServicioSession() {

    }

    public List<OrdenServicio> getListaPrueba() {
        return listaPrueba;
    }

    public void setListaPrueba(List<OrdenServicio> listaPrueba) {
        this.listaPrueba = listaPrueba;
    }

    public List<OrdenServicio> getListaServiciosPorCliente(OrdenServicio o) {
        return listaServiciosPorCliente = ordenFacade.listaFiltroClienteServicio(o);
    }

    public void setListaServiciosPorCliente(List<OrdenServicio> listaServiciosPorCliente) {
        this.listaServiciosPorCliente = listaServiciosPorCliente;
    }

    public CargaArchivos getCargaArchivos() {
        return cargaArchivos;
    }

    public List<String> getListaProdSolicitados() {
        return listaProdSolicitados;
    }

    public void setListaProdSolicitados(List<String> listaProdSolicitados) {
        this.listaProdSolicitados = listaProdSolicitados;
    }

    public void setCargaArchivos(CargaArchivos cargaArchivos) {
        this.cargaArchivos = cargaArchivos;
    }

    public List<SelectItem> getListaServicioActivosTodos() {
        listaServicioActivosTodos = new ArrayList<>();
        for (OrdenServicio li : ordenFacade.listaServiciosActivos()) {
            listaServicioActivosTodos.add(new SelectItem(li, li.getIdOrdenServicio() + " " + li.getPersonaidPersona().getPrimernombre()
                    + " " + li.getPersonaidPersona().getPrimerapellido()));

        }
        return listaServicioActivosTodos;
    }

    public void setListaServicioActivosTodos(List<SelectItem> listaServicioActivosTodos) {
        this.listaServicioActivosTodos = listaServicioActivosTodos;
    }

    public List<SelectItem> getListaServiciosSesion() {
        listaServiciosSesion = new ArrayList<>();
        for (OrdenServicio o : ordenFacade.listaServiciosSesion(persona.getPersonaSesion())) {
            listaServiciosSesion.add(new SelectItem(o, o.getIdOrdenServicio() + " "
                    + o.getPersonaidPersona().getPrimernombre() + " " + o.getPersonaidPersona().getPrimerapellido()));
        }
        return listaServiciosSesion;
    }

    public void setListaServiciosSesion(List<SelectItem> listaServiciosSesion) {
        this.listaServiciosSesion = listaServiciosSesion;
    }

    public List<SelectItem> getListaServiciosActivos() {
        listaServiciosActivos = new ArrayList<>();
        for (OrdenServicio or : ordenFacade.listaServiciosActivosSesion(persona.getPersonaSesion())) {
            listaServiciosActivos.add(new SelectItem(or, or.getIdOrdenServicio() + " "
                    + or.getPersonaidPersona().getPrimernombre() + " " + or.getPersonaidPersona().getPrimerapellido()));
        }
        return listaServiciosActivos;
    }

    public void setListaServiciosActivos(List<SelectItem> listaServiciosActivos) {
        this.listaServiciosActivos = listaServiciosActivos;
    }

    public List<SelectItem> getListaServiciosDisp() {
        listaServiciosDisp = new ArrayList<>();
        for (OrdenServicio l : ordenFacade.listaServiciosDisponiblesTodos()) {
            listaServiciosDisp.add(new SelectItem(l, l.getIdOrdenServicio() + " "
                    + l.getPersonaidPersona().getPrimernombre() + " " + l.getPersonaidPersona().getPrimerapellido()));
        }
        return listaServiciosDisp;
    }

    public void setListaServiciosDisp(List<SelectItem> listaServiciosDisp) {
        this.listaServiciosDisp = listaServiciosDisp;
    }

    public List<OrdenServicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<OrdenServicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public OrdenServicio getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    @PostConstruct
    public void init() {
        ordenServicio = new OrdenServicio();
        listaServicios = new ArrayList<>();
        listaProdSolicitados = new ArrayList<>();
        listaServiciosPorCliente = new ArrayList<>();
        listaPrueba = new ArrayList<>();
    }

    public void tablaPersonaSession() {
         listaServicios = ordenFacade.listaServiciosActivosSesion(persona.getPersonaSesion());
    }
    public void tablaPersonas(){//metodo para el admin
        listaServicios = ordenFacade.findAll();
    }
    public void ingresoOrdenServicio() {
        ordenServicio.getPersonaidPersona().setPermisoProducto(false);
        personaFacade.edit(ordenServicio.getPersonaidPersona());
        ordenServicio.setProductosSolicitados(listaProdSolicitados.toString());
        ordenServicio.getAsignaciontecnicoidAsignacionTecnico().setDisponibilidad(false);
        asignacionTecnico.edit(ordenServicio.getAsignaciontecnicoidAsignacionTecnico());
        ordenFacade.create(ordenServicio);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Orden de Servicio Generada con Exito"));
        listaServicios = ordenFacade.listaServiciosDisponiblesTodos();
        try {
        String correoTecnico = ordenServicio.getAsignaciontecnicoidAsignacionTecnico().getPersonaidPersona().getCorreo();
        String correoCliente = ordenServicio.getPersonaidPersona().getCorreo();
        String correoAdmin = personaFacade.correoAdmin().getCorreo();
        List<String> listaCorreos = new ArrayList<>();
        listaCorreos.add(correoTecnico);
        listaCorreos.add(correoCliente);
        listaCorreos.add(correoAdmin);
        for (String c : listaCorreos) {
           Mailer.send(c,"Servicio SIS","Servicio Solicitado con exito","Los Productos solicitados son: " + ordenServicio.getProductosSolicitados()
           + "\n" + "La dirección del servicio es: " + ordenServicio.getPersonaidPersona().getDireccion() + "," +
                   "Usuario solicitante: " + ordenServicio.getPersonaidPersona().getPrimernombre() + " " + ordenServicio.getPersonaidPersona().getPrimerapellido()
           + "\n" + "Técnico asignado: " + ordenServicio.getAsignaciontecnicoidAsignacionTecnico().getPersonaidPersona().getPrimernombre() + " " +
                   ordenServicio.getAsignaciontecnicoidAsignacionTecnico().getPersonaidPersona().getPrimerapellido()); 
        } 
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Correo Invalido", e.getMessage()));
        }
        this.ordenServicio = new OrdenServicio();
    }

    public void mostarOden() {
        listaServicios = ordenFacade.listaServiciosDisponiblesTecnico(persona.getPersonaSesion());
    }
    public void mostarOrdenTodos(){
        listaServicios = ordenFacade.listaServiciosDisponiblesTodos();
    }

    public void editarOrden2(OrdenServicio orden) {
        ordenFacade.edit(orden);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        this.ordenServicio = new OrdenServicio();
    }

    public void cancelar(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

    public void eliminarServicio(OrdenServicio servicio) {
        servicio.setBorrar(false);
        ordenFacade.edit(servicio);
        listaServicios = ordenFacade.listaServiciosDisponiblesTodos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
    }

    public void cerrarServicioTabla(OrdenServicio o) {
        this.ordenServicio = o;
    }

    public void cerrarServicio() {
        System.out.println(cargaArchivos.getFile().getContentType());
        System.out.println(cargaArchivos.getFile2().getContentType());
        if ((cargaArchivos.getFile().getContentType().equals("application/pdf")) && (cargaArchivos.getFile2().getContentType().equals("image/jpeg"))
                || (cargaArchivos.getFile2().getContentType().equals("image/jpg") || (cargaArchivos.getFile2().getContentType().equals("image/png")))) {
        cargaArchivos.upload();
        cargaArchivos.upload2();
        ordenServicio.setInformeServicio(cargaArchivos.getPathReal());
        ordenServicio.setEvidenciaFotografica(cargaArchivos.getPathReal2());
        ordenServicio.setEstadoServicio("Ce");
        ordenFacade.edit(ordenServicio);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Servicio Cerrado Con Exito"));
        ordenServicio = new OrdenServicio();
        }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        (FacesMessage.SEVERITY_ERROR, "Error", "Extensión invalida, solo se permiten .pdf .jpg .png .jpeg "));
        ordenServicio = new OrdenServicio();
        }
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(ordenFacade.listaServiciosDisponiblesTodos(), true);
    }

    public OrdenServicio getOrdenServicio(java.lang.Integer id) {
        return ordenFacade.find(id);
    }

    @FacesConverter(forClass = OrdenServicio.class)
    public static class OrdenServicioConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdenServicioSession controller = (OrdenServicioSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordenServicioSession");
            return controller.getOrdenServicio(getKey(value));
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
            if (object instanceof OrdenServicio) {
                OrdenServicio o = (OrdenServicio) object;
                return getStringKey(o.getIdOrdenServicio());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + OrdenServicio.class.getName());
            }
        }
    }

}
