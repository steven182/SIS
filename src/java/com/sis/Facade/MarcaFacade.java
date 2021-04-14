package com.sis.Facade;

import com.sis.Entidades.Marca;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MarcaFacade extends AbstractFacade<Marca> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaFacade() {
        super(Marca.class);
    }

    public List<Marca> listaMarcasActivas() {
        Query consulta = em.createNativeQuery("select * from Marca where estado = true;", Marca.class);
        List<Marca> listaMarcas = consulta.getResultList();
        return listaMarcas;
    }

}
