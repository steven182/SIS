/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.Controlador;

import com.sis.Entidades.Persona;
import com.sis.Facade.PersonaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author ersal
 */
@Named(value = "charPersona")
@SessionScoped
public class ChartPersona implements Serializable {

    @EJB
    PersonaFacade personaFacade;
    private BarChartModel model;
    private List<Persona> lista;

    @PostConstruct
    public void init() {
        lista = new ArrayList<>();
        listar();
    }

    public ChartPersona() {
    }

    public List<Persona> getLista() {
        return lista;
    }

    public void setLista(List<Persona> lista) {
        this.lista = lista;
    }

    public BarChartModel getModel() {
        return model;
    }

    public void setModel(BarChartModel model) {
        this.model = model;
    }

    public void listar() {

        List<Object[]> listaTotal = personaFacade.charUsuariosActivos();
        graficar(listaTotal);
    }

    private void graficar(List<Object[]> lista) {
        model = new BarChartModel();
        ChartSeries usuarios = new ChartSeries();
        usuarios.setLabel("Usuarios");
        for (Object[] o : lista) {
            usuarios.set(o[0], Integer.parseInt(o[1].toString()));
        }
        model.addSeries(usuarios);
        model.setTitle("Lista de Usuarios");
        model.setLegendPosition("ne");
        Axis x = model.getAxis(AxisType.X);
        Axis y = model.getAxis(AxisType.Y);
        y.setLabel("Cantidad de Usuarios");
        y.setMin(0);
        y.setMax(10);
        
        
    }

}
