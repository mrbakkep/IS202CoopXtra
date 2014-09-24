/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * This class represents the results of an assessment of a student's
 * results for a particular module.
 *
 * @author even
 */
@Entity
public class Assessment implements Serializable {
   @Id @GeneratedValue
   private long id;
   @ManyToOne
   private Student student;
   @ManyToOne
   private Module module;
   private Date assessmentDate;
   private boolean achievedLearningGoals; // true means the student passed
   private String teachersComment;

   public Assessment() {
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

   public Module getModule() {
      return module;
   }

   public void setModule(Module module) {
      this.module = module;
   }

   public Date getApprovedDate() {
      return assessmentDate;
   }

   public void setApprovedDate(Date approvedDate) {
      this.assessmentDate = approvedDate;
   }

   public boolean isApproved() {
      return achievedLearningGoals;
   }

   public void setApproved(boolean approved) {
      this.achievedLearningGoals = approved;
   }

   public String getTeachersComment() {
      return teachersComment;
   }

   public void setTeachersComment(String comment) {
      this.teachersComment = comment;
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 13 * hash + (int) (this.id ^ (this.id >>> 32));
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
      final Assessment other = (Assessment) obj;
      if (this.id != other.id) {
         return false;
      }
      return true;
   }
}
