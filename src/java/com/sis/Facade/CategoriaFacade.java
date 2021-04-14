package com.sis.Facade;

import com.sis.Entidades.Categoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
   public List<Categoria> listaCategoriasActivas(){
        Query consulta = em.createNativeQuery("select * from categoria where estado = true;", Categoria.class);
        List<Categoria> listaCategoria = consulta.getResultList();
        return listaCategoria;
    }

}
