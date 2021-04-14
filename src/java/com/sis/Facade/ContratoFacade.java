package com.sis.Facade;

import com.sis.Entidades.Contrato;
import com.sis.Entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ContratoFacade extends AbstractFacade<Contrato> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoFacade() {
        super(Contrato.class);
    }
    
    public List<Contrato> listaContrato (){
        Query consulta = em.createNativeQuery("select * FROM contrato where estado=1;", Contrato.class);
        List<Contrato> liscont = consulta.getResultList();
        return liscont;
        
    }
    public List<Object[]> listaReporteContrato(){
        Query con = em.createNativeQuery("select c.idContrato, ifnull(c.tipoContrato, 'Sin Especificar') as tipoContrato, \n" +
"ifnull(c.valorContrato, 0000) as valorContrato, c.fechaInicio,c.fechaFin, c.descripcion,\n" +
"group_concat( distinct p.primernombre, ' ', p.primerapellido) as\n" +
"cliente, dp.modelo from contrato as c\n" +
"inner join persona as p on c.Persona_idPersona = p.idPersona\n" +
"inner join asignacion as a on a.idAsignacion = c.Asignacion_idAsignacion\n" +
"inner join detalleproducto as dp on dp.idDetalleProducto = a.DetalleProducto_idDetalleProducto\n" +
"group by c.idContrato;");
        return con.getResultList();
    }
    public List<Contrato> listaContratoSesion(Persona p){
        Query con = em.createQuery("select ct from Contrato ct where ct.personaidPersona.idPersona=:id");
        con.setParameter("id", p.getIdPersona());
        return con.getResultList();
    }
    
}
