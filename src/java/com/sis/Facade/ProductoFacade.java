package com.sis.Facade;

import com.sis.Entidades.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
   public List<Producto> listaProductosActivos(){
        Query consulta = em.createNativeQuery("select * from producto where estado = true;", Producto.class);
        List<Producto> listaProductos = consulta.getResultList();
        return listaProductos;
    }
 
}
