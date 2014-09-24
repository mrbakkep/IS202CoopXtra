package no.uia.slit.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author even
 */
public class AuthUtils {

   /**
    * Create the MD5 digest of a password.
    */
   public static String digest(String password) throws NoSuchAlgorithmException {
      MessageDigest md = MessageDigest.getInstance("MD5");
      StringBuilder sb = new StringBuilder();
      byte[] bytes;

      md.reset();
      bytes = md.digest(password.getBytes());
      for (int i = 0; i < bytes.length; i++) {
         String hex = Integer.toHexString(0xFF & bytes[i]);
         if (hex.length() == 1) {
            sb.append("0");
         }
         sb.append(hex);
      }
      return sb.toString();
   }
}
