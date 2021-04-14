package com.sis.Facade;

import com.sis.Entidades.DetalleOrdenServicio;
import com.sis.Entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DetalleOrdenServicioFacade extends AbstractFacade<DetalleOrdenServicio> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleOrdenServicioFacade() {
        super(DetalleOrdenServicio.class);
    }
    public List<Object[]> serviciosPorCliente(Persona p) {
        Query con = em.createNativeQuery("select group_concat(idDetalleOrdenServicio) idDetalleOrdenServicio, \n"
                + "OrdenServicio_idOrdenServicio,\n"
                + "count(DetalleProducto_idDetalleProducto) detalleproducto_idDetalleProducto\n"
                + "from detalleordenservicio inner join ordenservicio as o on idOrdenServicio = OrdenServicio_idOrdenServicio \n"
                + "inner join persona as p on idPersona = persona_idPersona where p.idPersona = ? \n"
                + "group by OrdenServicio_idOrdenServicio;");
        con.setParameter("1", p.getIdPersona());
        return con.getResultList();
    }
    public List<DetalleOrdenServicio> listaProdEditTecnico(Persona p){//lista que trae los productos segun el tecnico
        Query con = em.createQuery("select o from DetalleOrdenServicio o where o.ordenServicioidOrdenServicio.asignaciontecnicoidAsignacionTecnico.personaidPersona.idPersona =:id");
        con.setParameter("id", p.getIdPersona());
        return con.getResultList();
    }

}
