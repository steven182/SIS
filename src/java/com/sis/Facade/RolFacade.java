package com.sis.Facade;

import com.sis.Entidades.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "Sis2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    public Rol getCliente() {
        Query consulta = em.createNamedQuery("Rol.findByRolCliente");
        return (Rol) consulta.getSingleResult();
    }

    public Rol getTecnico() {
        Query consulta = em.createNamedQuery("Rol.findByRolTecnico");
        return (Rol) consulta.getSingleResult();
    }

    public String cargaDatos(String archivo, String tabla) {
        Query consulta = em.createNativeQuery("LOAD DATA LOCAL INFILE '" + archivo + "' IGNORE INTO TABLE `" + tabla + "` FIELDS TERMINATED BY ';' ENCLOSED BY '\\\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\r\\n' ");
        int resultado = consulta.executeUpdate();
        String mensaje = resultado + "Filas afectadas";
        return mensaje;
    }
}
