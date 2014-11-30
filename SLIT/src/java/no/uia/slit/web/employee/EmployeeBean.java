package no.uia.slit.web.employee;

import no.uia.slit.ejb.DepartmentEJB;
import no.uia.slit.ejb.EmployeeEJB;
import no.uia.slit.entity.Department;
import no.uia.slit.entity.Employee;
import no.uia.slit.web.View;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author evenal
 */
@Named("empbean")
@ConversationScoped
public class EmployeeBean implements Serializable {

    private static final long serialVersionUID = 42L;
    // The inject annotation asks the server to insert a suitable
    // object, i.e. a new Conversation object in the variable conv.
    @Inject
    private Conversation conv;
    @EJB
    private EmployeeEJB empEjb;
    @EJB
    private DepartmentEJB depEjb;
    private Employee emp;
    private boolean updating;

    public EmployeeBean() {
        emp = null;
        updating = false;
    }

    public long getEmpId() {
        if (null == emp) {
            return 0;
        } else {
            return emp.getId();
        }
    }

    public void setEmpId(long empid) {
        if (conv.isTransient()) {
            conv.begin();
        }
        updating = true;
        emp = empEjb.find(empid);
        if (emp == null) {
            updating = false;
            emp = new Employee();
            emp.setDepartment(depEjb.getDefaultDepartment());
        }

    }

    public boolean isUpdating() {
        return updating;
    }

    public String getName() {
        if (emp == null) {
            return null;
        } else {
            return emp.getName();
        }
    }

    public void setName(String name) {
        emp.setName(name);
    }

    public int getSalary() {
        return emp.getSalary();
    }

    public void setSalary(int salary) {
        emp.setSalary(salary);
    }

    public Department getDepartment() {
        return emp.getDepartment();
    }

    public void setDepartment(Department dep) {
        emp.setDepartment(dep);
    }

    public View saveEmployee() {
        if (updating) {
            empEjb.update(emp);
        } else {
            empEjb.insert(emp);
        }

        return View.studenter;
    }

    public View deleteEmployee() {
        empEjb.delete(emp);
        return View.studenter;
    }
}
