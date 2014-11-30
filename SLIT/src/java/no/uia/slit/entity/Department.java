package no.uia.slit.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/** This is an entity class, i.e. it is mapped to a database table.
 * The default table name is the same as the class name, so Department objects
 * will be stored in the table DEPARTMENT. */
@Entity
public class Department implements Serializable {

    private static final long serialVersionUID = 42L;

    /** The Id annotation is used to mark the depNo field as the primary key.
     * The GeneratedValue annotation turns on automatic generation of
     * primary key values */
    @Id
    @GeneratedValue
    private long depNo;


    /** The NotNull and Size annotations are constraints on the values of name.
     * The default column name is the same as the field name, so no annotations
     * are needed to specify column name, unless you have to map to an existing
     * database */
    @NotNull
    @Size(min = 1)
    private String name;

    /** The OneToMany annotation marks employees as a one-to-many relationship.
     * The mappedBy parameter indicates that the foreign key is in the
     * department field/column in employee */
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;


    /** Getters and setters */
    public Department() {
        employees = new ArrayList<Employee>();
    }

    public long getDepNo() {
        return depNo;
    }

    public void setDepNo(long depNo) {
        this.depNo = depNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    /** hashCode() and equals() must be overridden, and they must be
     * consistent. If a.equals(b) then a.hashCode() == b.hashCode() must alse
     * be true */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.depNo ^ (this.depNo >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Department other = (Department) obj;
        if (depNo != other.depNo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(depNo);
        sb.append(" ");
        sb.append(name);
//        sb.append(" {");
//        for (Employee emp : employees) {
//            sb.append(" ");
//            sb.append(emp);
//        }
//        sb.append(" }");

        return sb.toString();
    }
}
