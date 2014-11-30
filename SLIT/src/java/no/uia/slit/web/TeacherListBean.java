package no.uia.slit.web;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import no.uia.slit.ejb.TeacherPersister;
import no.uia.slit.entity.Teacher;

/**
 *
 * @author evenal
 */
@Named("teacherListBean")
@RequestScoped
public class TeacherListBean {
    @EJB
    TeacherPersister teacherSvc;

    public List<Teacher> getList() {
        List<Teacher> l = teacherSvc.findAll();
        return l;
    }
}
