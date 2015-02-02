package com.epam.javalab.webapp.controller.admin.updatebeans;

import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.service.UserService;
import com.epam.javalab.webapp.user.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


@ViewScoped
@ManagedBean
public class UpdateUserBean {

    @Inject
    private UserService userService;

    @Inject
    private SupportBB supportBB;

    private User selectedUser;

    private String oldPassword;

    private String currentPassword;

    private String repeatedPassword;

    public String updatePassword() {

        User user = userService.findById(supportBB.getSomeValue());
        if (user.getPassword().equals(EncryptByMD5.encrypt(oldPassword, user.getName()))) {
            if (currentPassword.equals(repeatedPassword)) {
                user.setPassword(EncryptByMD5.encrypt(currentPassword, user.getName()));
                userService.update(user);
                return supportBB.onFinish();
            } else {
                FacesContext.getCurrentInstance().addMessage("repeatPass", new FacesMessage("Repeated password not equals new password"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("oldPass", new FacesMessage("You entered wrong password!"));
        }
        return null;
    }


    public String update(User user) {
        userService.update(user);
        selectedUser = null;
        return "usersPage?faces-redirect=true";
    }

    public void select(User user) {
        selectedUser = new User();
        selectedUser = user;
    }

    public boolean isSelected(User user) {
        if (selectedUser == null)
            return false;
        else if (user == null) {
            return false;
        } else
            return user.equals(selectedUser);
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
