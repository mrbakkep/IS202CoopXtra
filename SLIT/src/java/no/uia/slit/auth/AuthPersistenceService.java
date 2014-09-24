package no.uia.slit.auth;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author even
 */
@Stateless
public class AuthPersistenceService {

   @PersistenceContext
   private EntityManager em;

   public AuthUser findUser(String name) {
      return em.find(AuthUser.class, name);
   }

   public List<AuthUser> findAllUsers() {
      TypedQuery<AuthUser> q = em.createQuery("select u from AuthUser u",
              AuthUser.class);
      System.out.print("Retrieving users:");
      List<AuthUser> uList = q.getResultList();
      for (AuthUser u : uList) System.out.print(" "+u.getUsername()+"/"+u.getPassword());
      System.out.println();
      if (null == uList || uList.isEmpty()) createDebugData();
      return uList;
   }

   public void saveUser(AuthUser user) {
      em.merge(user);
   }

   public void removeUser(AuthUser user) {
      em.remove(user);
   }

   public void createDebugData() {
       AuthUser admin = em.find(AuthUser.class, "admin");
       if (null == admin) {
           try {
               admin = new AuthUser();
               admin.setUsername("admin");
               admin.setPassword("admin");
               admin.addGroup(AuthGroup.admin);
               em.persist(admin);
           }
           catch (NoSuchAlgorithmException ex) {
               Logger.getLogger(AuthPersistenceService.class.getName()).log(Level.SEVERE,
                       null, ex);
           }
       }
   }
}