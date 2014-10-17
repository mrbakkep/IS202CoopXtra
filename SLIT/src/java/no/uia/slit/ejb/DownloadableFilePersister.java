package no.uia.slit.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import no.uia.slit.entity.DownloadableFile;

/**
 *
 * @author evenal
 */
@Stateless
public class DownloadableFilePersister
        extends AbstractPersister<DownloadableFile> {

    @PersistenceContext
    EntityManager em;

    public DownloadableFilePersister() {
        super(DownloadableFile.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public byte[] getDownloadableContent(int id) {
        DownloadableFile file = this.find(id);
        if (null == file) {
            return null;
        } else {
            return file.getFile();
        }
    }
}
