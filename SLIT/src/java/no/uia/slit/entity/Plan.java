/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author even
 */
@Entity
public class Plan {
   @Id @GeneratedValue
   private long id;   
   @ManyToOne
   private Student student;
   private int version;
   
   @OneToMany(mappedBy="plan", cascade=CascadeType.ALL)
   @OrderBy("plannedDate")
   List<PlanItem> Items;

   public Plan() {
      Items = new ArrayList<PlanItem>();
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Student getStudent() {
      return student;
   }

   public void setStudent(Student student) {
      this.student = student;
   }

   public int getVersion() {
      return version;
   }

   public void setVersion(int version) {
      this.version = version;
   }

   public List<PlanItem> getItems() {
      return Items;
   }

   public void setItems(List<PlanItem> plan) {
      this.Items = plan;
   }

   @Override
   public int hashCode() {
      int hash = 5;
      hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
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
      final Plan other = (Plan) obj;
      if (this.id != other.id) {
         return false;
      }
      return true;
   }
}
