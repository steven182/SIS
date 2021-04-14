package com.sis.Facade;

import com.sis.Entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
        public Persona iniciarSesion(Persona objPersona){
    
        Persona persona=null;
      
        Query consulta=em.createQuery("select p from Persona p where p.numeroIdentificacion=:documento and p.contrasena=:clave");
        consulta.setParameter("documento", objPersona.getNumeroIdentificacion());
        consulta.setParameter("clave", objPersona.getContrasena());
        List<Persona> listaUsuarios=consulta.getResultList();
        if(!listaUsuarios.isEmpty())
        {
        persona=listaUsuarios.get(0);
        }
        
        return persona;
    
    }

    public List<Persona> listaTecnicosActivos() {
        Query consulta = em.createNativeQuery("select * from persona where rol_IdRol = 3 AND estado = 1;", Persona.class);
        List<Persona> listTec = consulta.getResultList();
        return listTec;
    }

    public List<Persona> listaTecnicosInactivos() {
        Query consulta = em.createNativeQuery("select * from persona where rol_IdRol = 3 AND estado = 0;", Persona.class);
        List<Persona> listTec = consulta.getResultList();
        return listTec;
    }

    public List<Persona> listaClientes() { //lista SOLO para orden de servicio
        Query consulta = em.createNativeQuery("select * from persona where rol_IdRol = 2 and (permisoProducto = true) and (estado = true);", Persona.class);
        List<Persona> listClient = consulta.getResultList();
        return listClient;
    }
    public List<Persona> listaClientesContrato() { //lista SOLO para contrato
        Query consulta = em.createNativeQuery("select * from persona where rol_IdRol = 2;", Persona.class);
        List<Persona> listClient = consulta.getResultList();
        return listClient;
    }

    public List<Persona> listaUsuariosSesion(Persona p) {//lista SOLO para la orden de servicio
        Query con = em.createQuery("select p from Persona p where p.idPersona=:id and (p.permisoProducto = true) and (p.estado = true)");
        con.setParameter("id", p.getIdPersona());
        return con.getResultList();
    }
    public List<Persona> listaUsuariosSesionContrato(Persona p) {//lista SOLO para el contrato
        Query con = em.createQuery("select p from Persona p where p.idPersona=:id");
        con.setParameter("id", p.getIdPersona());
        return con.getResultList();
    }

    public void subirFoto(String archivo) {

        Query consulta = em.createNativeQuery("INSERT INTO PERSONA (foto)" + "VALUES (?)");
        consulta.setParameter(1, archivo);
        consulta.executeUpdate();
    }
    public Persona correoAdmin(){
        Persona p = null;
        Query con = em.createQuery("select p from Persona p where p.rolIdRol.idRol = 1");
        System.out.println(con);
        List<Persona> list = con.getResultList();
        p = list.get(0);
       return p; 
}
    public List<Persona> listaClientesAsignacionProd(){
        Query con = em.createQuery("select p from Persona p where p.rolIdRol.idRol != 3");
        return con.getResultList();
    }
    
    public List<Persona> listarUsuarios() {
        Query consulta = em.createNativeQuery("select * from persona where estado = true;", Persona.class);
        List<Persona> correos = consulta.getResultList();
        return correos;
    }
    
    public List<Object[]> charUsuariosActivos() {
        Query consulta = em.createNativeQuery("select case when estado = 0 then 'Inactivos' when estado = 1 then 'Activos' else 'Ninguno' end AS estado, count(estado) as cantidad from persona group by estado");
        List<Object[]> lista = consulta.getResultList();
        return lista;
    }
    
        public List<Persona> listaUsuariosActivos() {
        Query consulta = em.createNativeQuery("select * from persona where (rol_IdRol = 2 OR rol_IdRol = 3) AND estado = true;", Persona.class);
        List<Persona> listTec = consulta.getResultList();
        return listTec;
    }

    public List<Persona> listaUsuariosInactivos() {
        Query consulta = em.createNativeQuery("select * from persona where (rol_IdRol = 2 OR rol_IdRol = 3) AND estado = false ;", Persona.class);
        List<Persona> listTec = consulta.getResultList();
        return listTec;
    }
    
}
