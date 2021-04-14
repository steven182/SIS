package com.sis.Controlador;

import com.sis.Entidades.DetalleOrdenServicio;
import com.sis.Entidades.OrdenServicio;
import com.sis.Facade.DetalleOrdenServicioFacade;
import com.sis.Facade.OrdenServicioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "chartOrdenServicio")
@SessionScoped
public class ChartOrdenServicio implements Serializable {
    
    @EJB
    DetalleOrdenServicioFacade OrdenServicio;
    @Inject private  PersonaSession personaSession;
    private BarChartModel barModel;
    private BarChartModel barModel2;
    private PieChartModel pieModel;
    private List<OrdenServicio> lista;

    @PostConstruct
    public void init(){
        lista = new ArrayList<>();
        listar();
    }
     public ChartOrdenServicio() {
    }

    public BarChartModel getBarModel2() {
        return barModel2;
    }

    public void setBarModel2(BarChartModel barModel2) {
        this.barModel2 = barModel2;
    }

    public PersonaSession getPersonaSession() {
        return personaSession;
    }

    public void setPersonaSession(PersonaSession personaSession) {
        this.personaSession = personaSession;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public List<OrdenServicio> getLista() {
        return lista;
    }

    public void setLista(List<OrdenServicio> lista) {
        this.lista = lista;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
     
    public void listar(){
        /*Map<String, Number> servicios = new HashMap<>();
       lista = detalleOrdenServicio.findAll();
        for (DetalleOrdenServicio o : lista) {
            servicios.put(o.getDetalleProductoidDetalleProducto().getModelo(),o.getOrdenServicioidOrdenServicio().getIdOrdenServicio());
        }*/
        //List<OrdenServicio> lista1 = new ArrayList<>();
        //List<Object[]> listaTotal = OrdenServicio.serviciosPorTecnico();
        List<Object[]> listaClientes = OrdenServicio.serviciosPorCliente(personaSession.getPersonaSesion());
        //graficar(listaTotal);
        graficar2(listaClientes);
        //graficoPie(lista1);
 
    }
    private void graficar(List<Object[]> lista){
        barModel = new BarChartModel();
        ChartSeries servicio = new ChartSeries();
        servicio.setLabel("Servicios");
        for (Object[] o : lista) {
            servicio.set(o[1],Integer.parseInt(o[2].toString()));
        }
        barModel.addSeries(servicio);
        barModel.setTitle("Cantidad de Productos por Servicio");
        barModel.setLegendPosition("ne");
        Axis x = barModel.getAxis(AxisType.X);
        x.setLabel("ID de Servicio");
        Axis y = barModel.getAxis(AxisType.Y);
        y.setLabel("Cantidad de Productos");
        y.setMin(0);
        y.setMax(10);
        
        }
        private void graficar2(List<Object[]> lista){
        barModel2 = new BarChartModel();
        ChartSeries servicio = new ChartSeries();
        servicio.setLabel("Servicios");
        for (Object[] o : lista) {
            servicio.set(o[1],Integer.parseInt(o[2].toString()));
        }
        barModel2.addSeries(servicio);
        barModel2.setTitle("Cantidad de Productos por Servicio");
        barModel2.setLegendPosition("ne");
        Axis x = barModel2.getAxis(AxisType.X);
        x.setLabel("ID de Servicio");
        Axis y = barModel2.getAxis(AxisType.Y);
        y.setLabel("Cantidad de Productos");
        y.setMin(0);
        y.setMax(10);
        
        }
    /*private void graficoPie(List<OrdenServicio> lista){
        pieModel = new PieChartModel();
        
        for (OrdenServicio o : lista) {
            pieModel.set(o.getAsignaciontecnicoidAsignacionTecnico().getPersonaidPersona().getPrimernombre(),
                    o.getIdOrdenServicio());
        }
        pieModel.setTitle("Servicios");
        pieModel.setLegendPosition("e");
        //pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
    }*/    
    }
    

   
    

