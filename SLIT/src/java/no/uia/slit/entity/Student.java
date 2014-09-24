/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author even
 */
@Entity
public class Student {
   @Id @GeneratedValue
   private long Id;
   
   @Column(unique=true)
   private String username;

   public Student() {
   }

   public long getId() {
      return Id;
   }

   public void setId(long Id) {
      this.Id = Id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   @Override
   public int hashCode() {
      int hash = 3;
      hash = 67 * hash + (int) (this.Id ^ (this.Id >>> 32));
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
      final Student other = (Student) obj;
      if (this.Id != other.Id) {
         return false;
      }
      return true;
   }
}
