package no.uia.slit.ejb;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import no.uia.slit.entity.Module;


/**
 *
 * @author even
 */
public class ModulePersistenceService {

   @PersistenceContext
   private EntityManager em;

   /** Retrieve the module with the specified id from the database */
   public Module find(long id) {
       Module m = em.find(Module.class, id);
       return m;
   }

   /** Return a list of all modules defined in the database */
   public List<Module> findAll() {
      TypedQuery<Module> q = em.createQuery("select m from Module m",
              Module.class);
      List<Module> modList = q.getResultList();
      return modList;
   }

   /** Save a module in the database */
   public void save(Module m) {
       em.merge(m);
   }

   /** Remove a module from the database */
   public void remove(Module m) {
       em.remove(m);
   }
}
