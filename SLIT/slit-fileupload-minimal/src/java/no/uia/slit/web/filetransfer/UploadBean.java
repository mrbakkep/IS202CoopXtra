package no.uia.slit.web.filetransfer;

import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import no.uia.slit.ejb.TeacherPersister;
import no.uia.slit.entity.DownloadableFile;
import no.uia.slit.entity.Teacher;
import no.uia.slit.web.JsfUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 *
 * @author evenal
 */
@Named("uploadbean")
@RequestScoped
public class UploadBean {

    @EJB
    TeacherPersister teacherEjb;

    private UploadedFile upload;

    public UploadBean() {
    }

    public void submit() throws IOException {
        String fileName = FilenameUtils.getName(upload.getName());
        String contentType = upload.getContentType();
        byte[] bytes = upload.getBytes();

        // Now you can save bytes in DB (and also content type?)
        DownloadableFile file = new DownloadableFile((fileName), contentType, bytes.length, bytes);

        storeFile(file);

    }

    protected void storeFile(DownloadableFile file) {
        JsfUtils.log(new FacesMessage(String.format("File '%s' of type '%s'"+
                        " uploaded by %s at %tc",
                file.getFilename(), file.getContentType(),
                file.getUsername(), file.getUploadTime())).toString());

        teacherEjb.setCv(JsfUtils.getUserName(), file);

    }

    public UploadedFile getUpload() {
        return upload;
    }

    public void setUpload(UploadedFile upload) {
        this.upload = upload;
    }

}
