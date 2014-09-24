/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uia.slit.auth;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import no.uia.slit.web.JsfUtils;


/**
 *
 * @author even
 */
@Named("authInfoBean")
@RequestScoped
public class AuthInfoBean {
    @EJB private AuthPersistenceService authDb;

    public boolean isLoggedIn() {
        return null != JsfUtils.getUserName();
    }

    public String getUserName() {
        return JsfUtils.getUserName();
    }

    public String logout() {
        JsfUtils.logout();
        return "/index";
    }

    public String getInfo() {
        String userName = JsfUtils.getUserName();
        if (null != userName && !(userName.equals(""))) {
            AuthUser user = authDb.findUser(userName);
            if (null != user) {
                return "username="+user.getUsername()+", pwddigest="+user.getPassword()
                        +", groups="+user.getGroupString();
            }
        }
        return "Not logged in!";
    }

}
