package no.uia.slit.web;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Common solutions to common problems
 *
 * @author even
 */
public class JsfUtils {

    /**
     * Display an error message.
     *
     * @param id - the id of the component msg is associated to
     * @param msg - the message
     */
    public static void addMessage(String id, String msg) {
        FacesContext ctxt = getFacesContext();
        ctxt.addMessage(id, new FacesMessage(msg));
    }

    /**
     * Write to the server log
     */
    public static void log(String message) {
        ExternalContext context = getExternalContext();
        context.log(message);
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    public static HttpServletResponse getResponse() {
        FacesContext context = getFacesContext();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        return response;
    }

    public static String getUserName() {
        ExternalContext context = getExternalContext();
        Object requestObject = context.getRequest();
        if (requestObject instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) requestObject;
            return req.getRemoteUser();
        } else {
            return null;
        }
    }

    public static void logout() {
        FacesContext ctxt = FacesContext.getCurrentInstance();
        ExternalContext extCtxt = ctxt.getExternalContext();
        HttpSession session = (HttpSession) (extCtxt.getSession(false));
        session.invalidate();
    }

    /**
     * Get the pagename (view id) of the current page.
     *
     * @return
     */
    public static String getCurrentViewId() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getViewRoot().getViewId();
    }

    public static Object getContextAttribute(Object key) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map attributes = context.getAttributes();
        return attributes.get(key);
    }

    public static void setContextAttribute(Object key, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map attributes = context.getAttributes();
        attributes.put(key, value);
    }

    public static String getUiString(String key) {
        return getString("no.uia.slit.web.uistrings", key);
    }

    /**
     * This method retrieves properties from a resource bundle, so they can be
     * used in the java code.
     *
     * @param baseName basename of the resource file. The same value as the
     * basename tag(s) in faces-config.xml
     * @param key the name of the property
     * @return
     */
    private static String getString(String baseName, String key) {
        FacesContext ctxt = FacesContext.getCurrentInstance();
        Application app = ctxt.getApplication();
        Locale loc = getLocale(ctxt);
        ClassLoader cl = getClassLoader();
        ResourceBundle bundle;
        String str = null;
        if (null != baseName) {
            bundle = ResourceBundle.getBundle(baseName, loc, cl);
            if (null != bundle) {
                str = bundle.getString(key);
            }
        }
        if (null == str) {
            str = "Missing resource: " + baseName + "." + key;
        }

        return str;
    }

    private static Locale getLocale(FacesContext ctxt) {
        Locale loc = null;
        UIViewRoot root = ctxt.getViewRoot();
        if (null != root) {
            loc = root.getLocale();
        }
        if (loc == null) {
            loc = Locale.getDefault();
        }
        return loc;
    }

    private static ClassLoader getClassLoader() {
        ClassLoader ldr = Thread.currentThread().getContextClassLoader();
        if (null == ldr) {
            ldr = ClassLoader.getSystemClassLoader();
        }
        return ldr;
    }
}
