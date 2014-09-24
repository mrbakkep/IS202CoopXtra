package no.uia.slit.ejb;

import no.uia.slit.auth.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author even
 */
public class EJBUtils {

   public static final String EJB_CONTEXT;

   static {
      try {
         EJB_CONTEXT = "java:global/" + new InitialContext().lookup("java:app/AppName") + "/";
      } catch (NamingException e) {
         throw new ExceptionInInitializerError(e);
      }
   }

   /**
    * Return an EJB instance of the specified class.
    *
    * @param <T> The EJB classname
    * @param ejbClass the EJB Class object.
    * @return an instance of T/ejbClass
    * @throws IllegalArgumentException if lookup fails
    */
   public static <T> T findEJB(Class<T> ejbClass) {
      String jndiName = EJB_CONTEXT + ejbClass.getSimpleName();
      try {
         return (T) new InitialContext().lookup(jndiName);
      } catch (NamingException e) {
         throw new IllegalArgumentException(
                 String.format("Lookup failed for EJB class %s, in JNDI %s",
                 ejbClass, jndiName),
                 e);
      }
   }
}
