/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author even
 */
@Entity
public class Module {
   @Id @GeneratedValue
   private long id;
   @Column(unique=true)
   private String name;
   private String description;
   @ManyToOne
   private Module requiredModule;
   
   public Module() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Module getRequiredModule() {
      return requiredModule;
   }

   public void setRequiredModule(Module requiredModule) {
      this.requiredModule = requiredModule;
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
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
      final Module other = (Module) obj;
      if (this.id != other.id) {
         return false;
      }
      return true;
   }
}
