package no.uia.slit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;


/**
 * The Document entity represents an uploaded file.
 * @author even
 */
@Entity
public class Document {
    @Id @GeneratedValue
    private long id;
    private String filename;
    private String mimeType;
    @Lob
    byte[] content;


    public Document() {
        set(null, null, null);
    }
    public Document(String filename, String mimeType, byte[] content) {
        set(filename, mimeType, content);
    }
        
    private final void set(String filename, String mimeType, byte[] content){
        this.filename = filename;
        this.mimeType = mimeType;
        this.content = content;
    }
}
