package no.uia.slit.auth;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import no.uia.slit.web.JsfUtils;
import no.uia.slit.web.Page;

/**
 *
 * @author even
 */
@Named(value="authBean")
@SessionScoped
public class AuthBean implements Serializable {
   @EJB
   private AuthPersistenceService authSvc;
   private AuthUser selectedUser;
   private String password1, password2;

    public AuthBean() {
    }

    public List<AuthUser> getUsers() {
       return authSvc.findAllUsers();
    }

    public void createDebugData() {
       authSvc.createDebugData();
    }

    public Page editNewUser() {
        System.out.println("editNewUser() creating...");
       selectedUser = new AuthUser();
       return Page.user;
    }

    public Page editUser(String username) {
       selectedUser = authSvc.findUser(username);
       if (null == selectedUser) {
          selectedUser = new AuthUser();
       }
       return Page.user;
    }

    public AuthUser getSelectedUser() {
        System.out.println("getSelectedUser() returning "+selectedUser);
       return selectedUser;
    }

    public String getPassword1() {
       return password1;
    }

    public void setPassword1(String password) throws NoSuchAlgorithmException {
//       this.password1 = AuthUtils.digest(password);
       this.password1 = (password);
    }

    public String getPassword2() {
       return password2;
    }

    public void setPassword2(String password) throws NoSuchAlgorithmException {
//       this.password1 = AuthUtils.digest(password);
       this.password2 = (password);
    }

    public List<SelectItem> getGroupItems() {
       List<SelectItem> l = new ArrayList<SelectItem>();
       for (AuthGroup g : AuthGroup.values())
          l.add(new SelectItem(g, g.name()));
       return l;
    }

    public Page saveSelectedUser() throws NoSuchAlgorithmException {
       if (password1 != null && (! password1.equals(""))) {
System.out.println(password1+" "+password2);
          if (password1.equals(password2)) {
             selectedUser.setPassword(password1);
          }
          else {
             JsfUtils.addMessage("userform:pwd1", "Passwords don't match!");
             return Page.user;
          }
       }

       authSvc.saveUser(selectedUser);
       return Page.users;
    }

    public Page deleteSelectedUser() {
        authSvc.removeUser(selectedUser);
        return Page.users;
    }

    public Page deleteUser(String username) {
        AuthUser user = authSvc.findUser(username);
        if (null != user) authSvc.removeUser(user);
        else JsfUtils.addMessage(null, "No such user: "+username);
        return Page.users;
    }
}
