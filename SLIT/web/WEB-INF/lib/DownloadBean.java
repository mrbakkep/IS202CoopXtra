package no.uia.slit.web.filetransfer;

import java.io.IOException;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import no.uia.slit.ejb.DownloadableFilePersister;
import no.uia.slit.entity.DownloadableFile;
import no.uia.slit.web.JsfUtils;

/**
 *
 * @author evenal
 */
@Named("downloadBean")
@RequestScoped
public class DownloadBean {

    @EJB
    DownloadableFilePersister fileSvc;

    public String downloadFile(int id) {
        DownloadableFile file = fileSvc.find(id);
        byte[] contents = fileSvc.getDownloadableContent(id);
        if (contents != null) {
            ExternalContext ec = JsfUtils.getExternalContext();
            ec.responseReset();
            ec.setResponseContentType(file.getContentType());
            ec.setResponseContentLength(file.getFilesize());
            // The Save As popup magic is done here. You can give the file
            // any name you want, this only won't work in MSIE, it will use
            // the current request URL as file name instead.
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\""
                    + file.getFilename() + "\"");
            try (OutputStream out = ec.getResponseOutputStream()) {
                out.write(contents);
                out.flush();
                JsfUtils.getFacesContext().responseComplete();
            } catch (IOException e) {
                JsfUtils.addMessage(null, "The requested file does not exist");
            }
            return "index";
        } else {
            return "index";
        }
    }
}
