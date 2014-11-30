/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.web.department;

import no.uia.slit.ejb.DepartmentEJB;
import no.uia.slit.entity.Department;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Even (UiA)
 */
@Named("depconverter")
@RequestScoped
@FacesConverter(forClass = Department.class)
public class DepartmentConverter implements Converter {

    @EJB
    DepartmentEJB departmentEjb;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        long depId = Long.parseLong(value);
        if (null == departmentEjb) {
            throw new ConverterException("No EJB!");
        }
        Department dep = departmentEjb.find(depId);
        if (null == dep) {
            throw new ConverterException("Cannot convert \"" + value + "\" to Department");
        }
        return dep;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (null != value && value instanceof Department) {
            Department dep = (Department) value;
            return "" + dep.getDepNo();
        }
        throw new ConverterException("Illegal value - must be a Department object");
    }

}
