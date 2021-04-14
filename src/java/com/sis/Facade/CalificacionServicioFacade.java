/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sis.Facade;

import com.sis.Entidades.CalificacionServicio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Steven
 */
@Stateless
public class CalificacionServicioFacade extends AbstractFacade<CalificacionServicio> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalificacionServicioFacade() {
        super(CalificacionServicio.class);
    }
    
}
