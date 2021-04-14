package com.sis.Controlador;

import com.sis.Entidades.Contrato;
import com.sis.Entidades.Persona;
import com.sis.Facade.AsignacionFacade;
import com.sis.Facade.ContratoFacade;
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

@Named(value = "contratoSession")
@SessionScoped
public class ContratoSession implements Serializable {

    @EJB
    ContratoFacade contratoFacade;
    @EJB
    AsignacionFacade asignacionFacade;
    private Contrato contrato;
    private List<Contrato> listaContratos;
    private List<SelectItem> listaContratosActivos;
    @Inject
    private CargarArchivosControlador cargaArchivos;
    @Inject
    PersonaSession persona;

    public CargarArchivosControlador getCargaArchivos() {
        return cargaArchivos;
    }

    @PostConstruct
    public void init() {
        contrato = new Contrato();
        listaContratos = new ArrayList<>();
    }

    public List<SelectItem> getListaContratosActivos() {
        listaContratosActivos = new ArrayList<>();
        for (Contrato c : contratoFacade.listaContrato()) {
            listaContratosActivos.add(new SelectItem(c, c.getIdContrato() + " " + c.getPersonaidPersona().getPrimernombre() + " "
                    + c.getPersonaidPersona().getPrimerapellido()));
        }
        return listaContratosActivos;
    }

    public void setListaContratosActivos(List<SelectItem> listaContratosActivos) {
        this.listaContratosActivos = listaContratosActivos;
    }

    public void setCargaArchivos(CargarArchivosControlador cargaArchivos) {
        this.cargaArchivos = cargaArchivos;
    }

    public ContratoSession() {
        contrato = new Contrato();
        listaContratos = new ArrayList<>();
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public List<Contrato> getListaContratos() {
        return listaContratos;
    }

    public void setListaContratos(List<Contrato> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public void crearContratoArriendo() {
        contrato.getAsignacionidAsignacion().setEstado(false);
        asignacionFacade.edit(contrato.getAsignacionidAsignacion());
        contrato.setTipoContrato("AR");
        contratoFacade.create(contrato);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingrresado Correctamente"));
        contrato = new Contrato();
    }

    public void crearContratoServicioTecnico() {
        contrato.getAsignacionidAsignacion().setEstado(false);
        asignacionFacade.edit(contrato.getAsignacionidAsignacion());
        contrato.setTipoContrato("ST");
        contratoFacade.create(contrato);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingrresado Correctamente"));
        contrato = new Contrato();
    }

    public void guardarContrato(Contrato c) {
        contratoFacade.edit(c);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actualizado"));
        this.contrato = new Contrato();
    }

    public void eliminarContrato(Contrato c) {
        c.setEstado(false);
        contratoFacade.edit(c);
        listaContratos = contratoFacade.listaContrato();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
    }

    public void listarContratosActivos() {
        listaContratos = contratoFacade.listaContrato();
    }

    public void listaContratoSesion() {
        listaContratos = contratoFacade.listaContratoSesion(persona.getPersonaSesion());
    }

    public void cancelarContrato(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cancelado"));
    }

    public void edtiContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public void cargarContrato() {
        if (cargaArchivos.getFile3().getContentType().equals("application/pdf")) {
            cargaArchivos.subirContrato();
            contrato.setFotoContrato(cargaArchivos.getPathReal3());
            contratoFacade.edit(contrato);
            contrato = new Contrato();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contrato Cargado Con Exito"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
            (FacesMessage.SEVERITY_ERROR, "Error", "Extensión invalida, solo se permiten .pdf"));
        }

    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return IdPersonaDao.getSelectItems(contratoFacade.findAll(), true);

    }

    public Contrato getContrato(java.lang.Integer id) {
        return contratoFacade.find(id);
    }

    @FacesConverter(forClass = Contrato.class)
    public static class ContratoSessionConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratoSession controller = (ContratoSession) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratoSession");
            return controller.getContrato(getKey(value));
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
            if (object instanceof Contrato) {
                Contrato o = (Contrato) object;
                return getStringKey(o.getIdContrato());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Contrato.class.getName());
            }
        }
    }

}
