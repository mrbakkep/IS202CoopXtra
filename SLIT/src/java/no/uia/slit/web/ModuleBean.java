package no.uia.slit.web;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import no.uia.slit.ejb.ModulePersistenceService;
import no.uia.slit.entity.Module;


/**
 *
 * @author even
 */
@Named("moduleBean")
@ConversationScoped
public class ModuleBean implements Serializable {
    @EJB private ModulePersistenceService moduleSvc;
    private Module selectedModule;

    public ModuleBean() {
    }


    public List<Module> getAllModules() {
        return moduleSvc.findAll();
    }

    /** Set up module form for adding a new module */
    public Page createModule() {
        selectedModule = new Module();
        return Page.module;
    }

    /** Prepare module form for editing an existing module.
     * If the requested module does not exist, a new module
     * will be created instead. */
    public Page editModule(long id) {
        selectedModule = moduleSvc.find(id);
        if (null == selectedModule) {
            selectedModule = new Module();
        }
        return Page.module;
    }

    public Module getSelectedModule() {
        return selectedModule;
    }
}
