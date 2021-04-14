package com.sis.Facade;

import com.sis.Entidades.AsignacionTecnico;
import com.sis.Entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AsignacionTecnicoFacade extends AbstractFacade<AsignacionTecnico> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignacionTecnicoFacade() {
        super(AsignacionTecnico.class);
    }

    public List<AsignacionTecnico> listaTecnicosDispSession(Persona p) {
        Query con = em.createQuery("select t from AsignacionTecnico t where t.disponibilidad=true and (t.personaidPersona.idPersona=:id) and (t.personaidPersona.estado=true)");
        con.setParameter("id", p.getIdPersona());
        return con.getResultList();
    }

    public List<AsignacionTecnico> listaTecnicosDispSessionTodos() {
        Query con = em.createQuery("select t from AsignacionTecnico t where t.disponibilidad=true and (t.personaidPersona.estado=true)");
        return con.getResultList();
    }

}
