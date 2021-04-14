package com.sis.Facade;

import com.sis.Entidades.DetalleCotizacion;
import com.sis.Entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DetalleCotizacionFacade extends AbstractFacade<DetalleCotizacion> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCotizacionFacade() {
        super(DetalleCotizacion.class);
    }
    public List<DetalleCotizacion> listaDetCotizacion(){
        Query con = em.createNativeQuery("select d.idDetalleCotizacion,d.valorCotizacion,d.fechaCotizacion,d.estado, d.descripcionAdmin,\n" +
"group_concat(d.cotizacion_idCotizacion) as cotizacion_idCotizacion ,d.Asignacion_idAsignacion,\n" +
"d.persona_idPersona from Detallecotizacion as d group by cotizacion_idCotizacion;", DetalleCotizacion.class);
        return con.getResultList();
    }
    public List<DetalleCotizacion> listaFiltroClienteServicio(DetalleCotizacion o) { //lista para el cliente
        Query con = em.createQuery("select d.asignacionidAsignacion.detalleProductoidDetalleProducto.modelo from DetalleCotizacion d where d.cotizacionidCotizacion.idCotizacion=:id");
        con.setParameter("id", o.getCotizacionidCotizacion().getIdCotizacion());
        return con.getResultList();
    }
    public List<DetalleCotizacion> listaServiciosActivosSesion(Persona p) {
        Query con = em.createNativeQuery("select d.idDetalleCotizacion,d.valorCotizacion,d.fechaCotizacion,d.estado, d.descripcionAdmin,\n" +
"group_concat(d.cotizacion_idCotizacion) as cotizacion_idCotizacion ,d.Asignacion_idAsignacion,\n" +
"d.persona_idPersona from Detallecotizacion as d inner join persona as p\n" +
"on p.idPersona = d.persona_idPersona where d.persona_idPersona = ? group by cotizacion_idCotizacion;", DetalleCotizacion.class);
        con.setParameter(1, p.getIdPersona());
        return con.getResultList();
    }
}
