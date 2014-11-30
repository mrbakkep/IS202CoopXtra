/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.web.department;

import no.uia.slit.ejb.DepartmentEJB;
import no.uia.slit.entity.Department;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author evenal
 */
@Named("deplistbean")
@RequestScoped
public class DepartmentListBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    DepartmentEJB depEjb;

    public DepartmentListBean() {
    }

    public List<Department> getDepartments() {
        List<Department> l = depEjb.findAll();
        return l;
    }

    public boolean isDepartmentsDefined() {
        return depEjb.count() > 0;
    }
}
