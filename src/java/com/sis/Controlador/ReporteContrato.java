package com.sis.Controlador;

import com.sis.Entidades.Contrato;
import com.sis.Entidades.ReporteContratosSis;
import com.sis.Facade.ContratoFacade;
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

@Named(value = "reporteContrato")
@SessionScoped
public class ReporteContrato implements Serializable {

    @EJB
    ContratoFacade contratoFacade;
    private List<Contrato> listaContrato;
    private List<Object[]> listaReporte;
    private List<ReporteContratosSis> listaContratos;
    JasperPrint jasperPrint;
    
    public ReporteContrato() {
        listaContrato = new ArrayList<>();
        
    }
    @PostConstruct
    public void init(){
        //listaContrato = contratoFacade.findAll();
        listaReporte = new ArrayList<>();
    }

    public List<Object[]> getListaReporte() {
        return listaReporte;
    }

    public void setListaReporte(List<Object[]> listaReporte) {
        this.listaReporte = listaReporte;
    }

    public List<ReporteContratosSis> getListaContratos() {
        return listaContratos;
    }

    public void setListaContratos(List<ReporteContratosSis> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public List<Contrato> getListaContrato() {
        listaContrato = contratoFacade.findAll();
        return listaContrato;
    }

    public void setListaContrato(List<Contrato> listaContrato) {
        this.listaContrato = listaContrato;
    }
    
    
    public void PDF(ActionEvent actionEvent) throws JRException, IOException{
      JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(listaContrato, false);
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRealPath("//Reporte//");
        jasperPrint=JasperFillManager.fillReport(ruta+"//ReporteContrato.jasper", new HashMap(),beanCollectionDataSource);
       HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
      httpServletResponse.addHeader("Content-disposition", "attachment; filename=\"reporte.pdf\";");httpServletResponse.addHeader("Content-disposition", "attachment; filename=reporteJoin.pdf");
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
            httpServletResponse.setHeader("Cache-Control", "no-cache");
            httpServletResponse.setHeader("Pragma", "no-cache");
       ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
       JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
       FacesContext.getCurrentInstance().responseComplete();  
   }
    public void mostrar() {
        listaReporte = contratoFacade.listaReporteContrato();
    }

    public void PDFJPQL(ActionEvent actionEvent) throws JRException, IOException {

        try {
            listaContratos = new ArrayList<>();
            listaReporte = contratoFacade.listaReporteContrato();
            for (Object[] r : listaReporte) {
                ReporteContratosSis report = new ReporteContratosSis();
                report.setIdContrato(Integer.parseInt(r[0].toString()));
                report.setTipoContrato(r[1].toString());
                report.setValorContrato(Integer.parseInt(r[2].toString()));
                report.setFechaInicio((java.sql.Date)r[3]);
                report.setFechaFin((java.sql.Date)r[4]);
                report.setDescripcion(r[5].toString());
                report.setCliente(r[6].toString());
                report.setModelo(r[7].toString());
                listaContratos.add(report);
            }
            HashMap parametros = new HashMap();
            //parametros.put("logo", "C:\\Users\\NelsonHernan.Lenovo-PC\\Documents\\NetBeansProjects\\ReportePDF\\web\\reportes\\cherry.jpg");
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaContratos, false);
            String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//Reporte//");
            jasperPrint = JasperFillManager.fillReport(ruta + "//ReporteContrato.jasper", new HashMap(), beanCollectionDataSource);
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
