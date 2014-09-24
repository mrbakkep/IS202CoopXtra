package no.uia.slit.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author even
 */
@Named("debugBean")
@SessionScoped
public class DebugBean implements Serializable {

    public DebugBean() {}

    public String getUserName() {
        return JsfUtils.getUserName();
    }

}
