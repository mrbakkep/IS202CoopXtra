package no.uia.slit.auth;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 *
 * @author even
 */
@Entity
public class AuthUser implements Serializable {
   @Id
   private String username;
   private String password;
   @ElementCollection(fetch= FetchType.EAGER)
   @CollectionTable(name="AUTHGROUPS",
           joinColumns=@JoinColumn(name="username"))
   @Enumerated(EnumType.STRING)
   @Column(name="groupname")
   private Set<AuthGroup> groups;

   public AuthUser() {
      username = "";
      password = "";
      groups = new HashSet<AuthGroup>();
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) throws NoSuchAlgorithmException {
//      this.password = AuthUtils.digest(password);
       this.password = password;
   }

   public Set<AuthGroup> getGroups() {
      return groups;
   }

   public void addGroup(AuthGroup group) {
      groups.add(group);
   }

   public void setGroups(Set<AuthGroup> groups) {
      this.groups = groups;
   }

   @Override
   public int hashCode() {

      int hash = 5;
      hash = 83 * hash + (this.username != null ? this.username.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final AuthUser other = (AuthUser) obj;
      if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
         return false;
      }
      return true;
   }

   public String getGroupString() {
      StringBuilder sb = new StringBuilder("[ ");
      for (AuthGroup g : groups) {
         sb.append(g.name());
         sb.append(" ");
      }
      sb.append(" ]");
      return sb.toString();
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder(username);
      sb.append(" ");
      sb.append(getGroupString());
      return sb.toString();
   }
}
