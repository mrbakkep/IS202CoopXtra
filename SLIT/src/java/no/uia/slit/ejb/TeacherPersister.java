package no.uia.slit.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import no.uia.slit.entity.DownloadableFile;
import no.uia.slit.entity.Teacher;

/**
 *
 * @author hvorerdu
 */

@Stateless
public class TeacherPersister
        extends AbstractPersister<Teacher> {

    @PersistenceContext
    EntityManager em;

    @EJB
    DownloadableFilePersister fileEJB;

    public TeacherPersister() {
        super(Teacher.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Teacher findByUsername(String username) {
        TypedQuery<Teacher> q = em.createNamedQuery("Teacher.byUsername",
                Teacher.class);
        q.setParameter("username", username);
        List<Teacher> l = q.getResultList();
        // unique usernames -> l contains one teacher, or none
        if (l.size() == 1) {
            return l.get(0);
        } else {
            return null;
        }
    }

    public void setCv(String username, DownloadableFile file) {
        Teacher t = findByUsername(username);

        if (null == t) {
            throw new IllegalStateException("There is no teacher with username "
                    + username + " You can't add a CV to a nonexisting user.");
        }

        System.out.println("Changing cv of " + t.getUsername() + " to " + file.getFilename());
        file = fileEJB.insert(file);
        t.setCv(file);
        em.merge(t);
    }

    public Teacher createTeacher(String username) {
        Teacher t = new Teacher();
        t.setUsername(username);
        em.merge(t);
        return t;
    }

    public void delete(String username) {
        TypedQuery<Teacher> t = em.createNamedQuery("Teacher.byUsername", Teacher.class);
        t.setParameter("username", username);
        List<Teacher> ls = t.getResultList();
        if (ls.size() == 1) {
            delete(ls.get(0));
        }
    }
}