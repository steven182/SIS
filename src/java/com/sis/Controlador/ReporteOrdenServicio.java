package com.sis.Controlador;

import com.sis.Entidades.OrdenServicio;
import com.sis.Entidades.Persona;
import com.sis.Entidades.ReporteServicio;
import com.sis.Facade.OrdenServicioFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named(value = "reporteOrdenServicio")
@SessionScoped
public class ReporteOrdenServicio implements Serializable {

    @EJB
    OrdenServicioFacade ordenServicioFacade;
    private List<Object[]> listaReportes;
    private List<ReporteServicio> listaReporteServicio;
    JasperPrint jasperPrint;
    private OrdenServicio ordenServicio;

    public ReporteOrdenServicio() {
    }

    @PostConstruct
    public void init() {
        listaReportes = new ArrayList<>();
        //listaFinal();
        ordenServicio = new OrdenServicio();
    }

    public List<Object[]> getListaReportes() {
        return listaReportes;
    }

    public void setListaReportes(List<Object[]> listaReportes) {
        this.listaReportes = listaReportes;
    }

    public List<ReporteServicio> getListaReporteServicio() {
        return listaReporteServicio;
    }

    public void setListaReporteServicio(List<ReporteServicio> listaReporteServicio) {
        this.listaReporteServicio = listaReporteServicio;
    }

    /*public void listaFinal(){
      listaReporteServicio = new ArrayList<>(); 
      //listaReportes = ordenServicioFacade.listaReporte();
        for (Object[] r : listaReportes = ordenServicioFacade.listaReporte()) {
            ReporteServicio report = new ReporteServicio();
            report.setIdServicio(Integer.parseInt(r[0].toString()));
            report.setFechaInicio((java.sql.Date) r[1]);
            report.setFechaFinal((java.sql.Date) r[2]);
            report.setDescInicio(r[3].toString());
            report.setDescFinal(r[4].toString());
            report.setEstado(r[5].toString());
            report.setCliente(r[6].toString());
            report.setModelo(r[7].toString());
            report.setTecnico(r[8].toString());
            listaReportes.add(r);
        }
    }*/
    public void mostrar() {
        listaReportes = ordenServicioFacade.listaReporte(ordenServicio);
    }

    public void PDFJPQL(OrdenServicio ord) throws JRException, IOException {

        try {
            listaReporteServicio = new ArrayList<>();
            listaReportes = ordenServicioFacade.listaReporte(ord);
            for (Object[] r : listaReportes) {
                ReporteServicio report = new ReporteServicio();
                report.setIdOrdenServicio(Integer.parseInt(r[0].toString()));
                report.setFechaInicio((java.sql.Date) r[1]);
                report.setFechaFinalServicio((java.sql.Date) r[2]);
                report.setDescripcionServicio(r[3].toString());
                report.setDescripcionFinalServicio(r[4].toString());
                report.setEstadoServicio(r[5].toString());
                report.setCliente(r[6].toString());
                report.setDireccion(r[7].toString());
                report.setNumeroIdentificacion(r[8].toString());
                report.setTelefono(Long.parseLong(r[9].toString()));
                report.setTipoServicio(r[10].toString());
                report.setCorreo(r[11].toString());
                report.setModelo(r[12].toString());
                report.setTecnico(r[12].toString());
                listaReporteServicio.add(report);
            }
            HashMap parametros = new HashMap();
            //parametros.put("logo", "C:\\Users\\NelsonHernan.Lenovo-PC\\Documents\\NetBeansProjects\\ReportePDF\\web\\reportes\\cherry.jpg");
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaReporteServicio, false);
            String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//Reporte//");
            jasperPrint = JasperFillManager.fillReport(ruta + "//ReporteServicio.jasper", new HashMap(), beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=reporteJoin.pdf");
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
            httpServletResponse.setHeader("Cache-Control", "no-cache");
            httpServletResponse.setHeader("Pragma", "no-cache");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (NumberFormatException | JRException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + e));
        }

    }
}
