package no.uia.slit.ejb;

import no.uia.slit.entity.Department;
import no.uia.slit.entity.Employee;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author evenal
 */
@Stateless
public class EmployeeEJB extends AbstractFacade<Employee> {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private DepartmentEJB depEJb;

    public EmployeeEJB() {
        super(Employee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Employee has a relation to Department, so it is necessary to overeride
     * insert and update, to make sure they maintain the relations
     */
    @Override
    public Employee insert(Employee newEmp) {
        Department dep = depEJb.find(newEmp.getDepartment().getDepNo());
        newEmp = super.insert(newEmp);
        dep.getEmployees().add(newEmp);
        newEmp.setDepartment(dep);
        return newEmp;
    }

    /**
     * Update is a bit more complex, because the old and new departments must
     * both be updated. In addition we need to get managed objects to ensure
     * that everything propagates to the database
     */
    @Override
    public Employee update(Employee editedEmp) {
        Employee dbEmp = find(editedEmp.getId());
        if (null == dbEmp) {
            insert(editedEmp);
        }

        long newDepNo = editedEmp.getDepartment().getDepNo();
        Department newDep = depEJb.find(newDepNo);
        Department oldDep = dbEmp.getDepartment();
        editedEmp = super.update(editedEmp);

        editedEmp.setDepartment(newDep);
        if (!newDep.equals(oldDep)) {
            oldDep.getEmployees().remove(editedEmp);
            newDep.getEmployees().add(editedEmp);
            editedEmp.setDepartment(newDep);
        }
        return editedEmp;
    }

    /**
     * delete() must also be overrided to, ensure that the deleted employee is
     * removed from the list in Department
     *
     * @param emp
     */
    @Override
    public void delete(Employee emp) {
        emp = em.merge(emp);
        Department dep = emp.getDepartment();
        dep.getEmployees().remove(emp);
        super.delete(emp);
    }

}
