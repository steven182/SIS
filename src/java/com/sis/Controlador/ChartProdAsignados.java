package com.sis.Controlador;

import com.sis.Facade.AsignacionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named(value = "chartProdAsignados")
@SessionScoped
public class ChartProdAsignados implements Serializable {

    @EJB
    AsignacionFacade asignacion;
    private BarChartModel barModel;
    private List<Object[]> listaProd;

    public ChartProdAsignados() {
    }

    @PostConstruct
    public void init() {
       // listar();
    }

    public List<Object[]> getListaProd() {
        return listaProd = asignacion.listaProductosPorPersona();
    }

    public void setListaProd(List<Object[]> listaProd) {
        this.listaProd = listaProd;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public void listar() {
        try {
            graficar(listaProd);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + e));
        }

    }

    private void graficar(List<Object[]> lista) {
        try {
            barModel = new BarChartModel();
            ChartSeries productos = new ChartSeries();
            productos.setLabel("Productos");
            for (Object[] o : lista) {
                productos.set(o[0], Integer.parseInt(o[1].toString()));
            }
            barModel.addSeries(productos);
            barModel.setTitle("Cantidad de Productos Asignados");
            barModel.setLegendPosition("ne");
            Axis x = barModel.getAxis(AxisType.X);
            x.setLabel("Nombre");
            Axis y = barModel.getAxis(AxisType.Y);
            y.setLabel("Cantidad de Productos");
            y.setMin(0);
            y.setMax(20);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: " + e));
        }

    }
}
