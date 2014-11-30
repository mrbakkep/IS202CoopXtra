package no.uia.slit.web.employee;

import no.uia.slit.ejb.EmployeeEJB;
import no.uia.slit.entity.Employee;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author evenal
 */
@Named("emplistbean")
public class EmployeeListBean {

    @EJB
    private EmployeeEJB empEjb;

    public List<Employee> getEmployees() {
        return empEjb.findAll();
    }

}
