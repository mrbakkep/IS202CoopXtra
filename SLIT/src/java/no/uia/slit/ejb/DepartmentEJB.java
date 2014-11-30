package no.uia.slit.ejb;

import no.uia.slit.entity.Department;
import no.uia.slit.entity.Employee;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This ejb handles database operations for Department objects. It does not have
 * any fields (except for the entity manager which is handled by the container),
 * which means it can't keep state, and is annotated with Stateless to mark it
 * as a stateless session bean. A stateless bean can be shared by several users,
 * so it is efficient with regards to resource usage.
 *
 * @author evenal
 */
@Stateless
public class DepartmentEJB extends AbstractFacade<Department> {

    /**
     * The PersistenceContext annotation is a request to the container
     * (GlassFish) to create or find an EntityManager and store it in the field
     * em. We get the EntityManager object we need, without having to write the
     * code to create it. This is called dependency injection.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * This method is used in the methods inherited from AbstractFacade to
     * access the EntityManager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartmentEJB() {
        super(Department.class);
    }

    /**
     * Method returns the first department it finds. It is used by the employee
     * page, to make sure that every employee belongs to a department
     */
    public Department getDefaultDepartment() {
        List<Department> depList = findAll();
        if (depList != null && depList.size() > 0) {
            return depList.get(0);
        } else {
            throw new IllegalStateException("No departments available.");
        }
    }

    /**
     * A wrapper for the find() method in AbstractFacade. It allows us to pass
     * the primary key as the primitive type long, rather than as the wrapper
     * type Long. It also forces loading of the department's employees
     */
    public Department find(long depNo) {
        Department dep = super.find(depNo);
        if (null != dep) {
            dep.getEmployees();
        }
        return dep;
    }

    @Override
    public Department update(Department dep) {
        Department dbDep = find(dep.getDepNo());
        if (!dbDep.getName().equals(dep.getName())) {
            dbDep.setName(dep.getName());
        }
        return super.update(dbDep);
    }

    @Override
    public void delete(Department dep) {
        Department dbDep = find(dep.getDepNo());
        List<Employee> emps = dbDep.getEmployees();
        if (emps != null && emps.size() > 0) {
            throw new EJBException("Cannot delete department with employees");
        }
        super.delete(dbDep);
    }

}
