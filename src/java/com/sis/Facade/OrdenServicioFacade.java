package com.sis.Facade;

import com.sis.Entidades.OrdenServicio;
import com.sis.Entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class OrdenServicioFacade extends AbstractFacade<OrdenServicio> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenServicioFacade() {
        super(OrdenServicio.class);
    }

    public List<OrdenServicio> listaServiciosDisponiblesTecnico(Persona p) {
        Query consulta = em.createQuery("select o from OrdenServicio o where o.estadoServicio='S' and o.asignaciontecnicoidAsignacionTecnico.personaidPersona.idPersona=:id");
        consulta.setParameter("id", p.getIdPersona());
        return consulta.getResultList();
    }

    public List<OrdenServicio> listaServiciosDisponiblesTodos() {
        Query consulta = em.createQuery("select o from OrdenServicio o where o.estadoServicio='S'");
        return consulta.getResultList();
    }

    public List<OrdenServicio> listaServiciosActivos() {
        Query consulta = em.createNativeQuery("select * from ordenServicio where estadoServicio = 'A';", OrdenServicio.class);
        List<OrdenServicio> listaServicios = consulta.getResultList();
        return listaServicios;
    }

    public List<OrdenServicio> listaServiciosActivosSesion(Persona p) {
        Query con = em.createQuery("select p from OrdenServicio p where p.personaidPersona.idPersona=:id");
        con.setParameter("id", p.getIdPersona());
        return con.getResultList();
    }

    public List<OrdenServicio> listaServiciosSesion(Persona p) {// consulta con el fin de traer la persona de la sesion y el servicio activo
        Query con = em.createQuery("select p from OrdenServicio p where p.personaidPersona.idPersona=:id and p.estadoServicio='Ce'");
        con.setParameter("id", p.getIdPersona());
        return con.getResultList();
    }

    public List<OrdenServicio> listaFiltroClienteServicio(OrdenServicio o) { //lista para el cliente
        Query con = em.createQuery("select d.asignacionidAsignacion.detalleProductoidDetalleProducto.modelo from DetalleOrdenServicio d where d.ordenServicioidOrdenServicio.idOrdenServicio=:id and d.ordenServicioidOrdenServicio.personaidPersona.idPersona=:ir");
        con.setParameter("id", o.getIdOrdenServicio());
        con.setParameter("ir", o.getPersonaidPersona().getIdPersona());
        return con.getResultList();
    }

    public List<Object[]> listaReporte(OrdenServicio o) {
        Query con = em.createNativeQuery("select o.idOrdenServicio,o.fechaInicio,o.fechaFinalServicio,o.descripcionServicio,\n" +
"ifnull(o.descripcionFinalServicio, 'Sin Especificar')as descripcionFinalServicio,o.estadoServicio,\n" +
"group_concat(distinct p.primernombre, ' ',  p.primerapellido) as cliente,\n" +
"p.direccion,p.numeroIdentificacion,p.telefono,o.tipoServicio,p.correo,\n" +
"group_concat(dp.modelo) as modelo, \n" +
"group_concat(distinct t.primernombre, ' ',  t.primerapellido) as tecnico from ordenservicio as o\n" +
"inner join persona as p on o.persona_idPersona = p.idPersona\n" +
"inner join asignaciontecnico as a on o.asignaciontecnico_idAsignacionTecnico =  a.idAsignacionTecnico\n" +
"inner join persona as t on t.idPersona = a.Persona_idPersona\n" +
"inner join detalleordenservicio as ds on o.idOrdenServicio = ds.OrdenServicio_idOrdenServicio\n" +
"inner join asignacion as ag on ag.idAsignacion = ds.Asignacion_idAsignacion\n" +
"inner join detalleproducto as dp on ag.DetalleProducto_idDetalleProducto = dp.idDetalleProducto\n" +
"where o.idOrdenServicio = ? group by o.idOrdenServicio;");
        con.setParameter(1, o.getIdOrdenServicio());
        return con.getResultList();
    }
}
