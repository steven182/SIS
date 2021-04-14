package com.sis.Facade;

import com.sis.Entidades.Asignacion;
import com.sis.Entidades.DetalleProducto;
import com.sis.Entidades.OrdenServicio;
import com.sis.Entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AsignacionFacade extends AbstractFacade<Asignacion> {
    
    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AsignacionFacade() {
        super(Asignacion.class);
    }

    public List<Object[]> listaProductosPorPersona() {
        Query con = em.createNativeQuery("select group_concat(distinct p.primernombre, ' ', p.primerapellido) nombres,\n"
                + "count(a.DetalleProducto_idDetalleProducto) as total\n"
                + "from persona as p inner join asignacion as a on a.persona_idPersona = p.idPersona\n"
                + "where p.rol_IdRol != 3 group by p.primernombre;");
        return con.getResultList();
    }
    public List<Asignacion> listaBorrar(){
        Query con = em.createNativeQuery("select * from asignacion where borrar = true;", Asignacion.class);
        return con.getResultList();
    }
    public List<Asignacion> listaAsignarServicio(OrdenServicio o){
        Query con = em.createQuery("select a from Asignacion a where a.estado = true and a.personaidPersona.idPersona =:id and a.detalleProductoidDetalleProducto.estado = false");
        con.setParameter("id", o.getPersonaidPersona().getIdPersona());
        return con.getResultList();
    }
    public List<Asignacion> listaDetailProd(DetalleProducto d){
        Query con = em.createQuery("select a from Asignacion a where a.detalleProductoidDetalleProducto.idDetalleProducto=:id");
        con.setParameter("id", d.getIdDetalleProducto());
        return con.getResultList();
    }
    public List<Asignacion> listaProductosAdmin(){
        Query con = em.createQuery("select a from Asignacion a where a.personaidPersona.rolIdRol.idRol=1 and a.estado=true");
        return con.getResultList();
    }
}
