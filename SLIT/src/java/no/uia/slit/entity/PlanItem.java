/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author even
 */
@Entity
public class PlanItem {
   @Id @GeneratedValue
   private long id;
   @ManyToOne
   private Plan plan;
   @ManyToOne
   private Module module;
   
   @Temporal(TemporalType.DATE)
   private Date plannedDate;
   
   public PlanItem() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Plan getPlan() {
      return plan;
   }

   public void setPlan(Plan plan) {
      this.plan = plan;
   }
   
   public Module getModule() {
      return module;
   }

   public void setModule(Module module) {
      this.module = module;
   }

   public Date getPlannedDate() {
      return plannedDate;
   }

   public void setPlannedDate(Date plannedDate) {
      this.plannedDate = plannedDate;
   }

   @Override
   public int hashCode() {
      int hash = 3;
      hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
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
      final PlanItem other = (PlanItem) obj;
      if (this.id != other.id) {
         return false;
      }
      return true;
   }
}
