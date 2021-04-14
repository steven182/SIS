package com.sis.Facade;

import com.sis.Entidades.DetalleProducto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DetalleProductoFacade extends AbstractFacade<DetalleProducto> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleProductoFacade() {
        super(DetalleProducto.class);
    }
    
     public List<DetalleProducto> listaDetalleProdBorrar() {
        Query con = em.createNativeQuery("select * from detalleProducto where borrar = true", DetalleProducto.class);
        return con.getResultList();
    }

    public List<DetalleProducto> listaDetalleProdActivos() {
        Query consulta = em.createNativeQuery("select * from detalleProducto where estado = true;", DetalleProducto.class);
        List<DetalleProducto> listaOrden = consulta.getResultList();
        return listaOrden;
    }

    public String cargaDatos(String archivo, String tabla) {
        Query consulta = em.createNativeQuery("LOAD DATA LOCAL INFILE '" + archivo + "' IGNORE INTO TABLE `" + tabla + "` FIELDS TERMINATED BY ';' ENCLOSED BY '\\\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\r\\n' ");
        int resultado = consulta.executeUpdate();
        String mensaje = resultado + "Filas afectadas";
        return mensaje;
    }
    
}
