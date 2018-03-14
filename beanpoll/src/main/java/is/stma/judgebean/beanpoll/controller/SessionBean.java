package is.stma.judgebean.beanpoll.controller;

import is.stma.judgebean.beanpoll.model.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionBean extends AbstractFacesController implements Serializable {

    private static final long serialVersionUID = 721057087394449169L;

    @Produces
    @Named("sessionUser")
    @RequestScoped
    private User user;

    private String username;
    private String password;
    private String newPassword;

    @PostConstruct
    private void updateUsername() {
        if (null != user) {
            username = user.getLogName();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public User getUser() {
        if (null == user) {
            return new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        password = null;
        newPassword = null;
    }

    public boolean isAuthenticated() {
        return null != user;
    }

    public boolean isAdmin() {
        return null != user && user.isAdmin();
    }

    public boolean isJudge() {
        return null != user && user.isJudge();
    }

    public boolean hasTeam() {
        return null != user && null != user.getTeam();
    }

    public String logOut() {
        facesContext.getExternalContext().invalidateSession();
        return SessionController.LOGIN_PAGE;
    }

}