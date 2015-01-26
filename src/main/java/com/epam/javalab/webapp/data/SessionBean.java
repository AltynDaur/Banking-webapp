package com.epam.javalab.webapp.data;

import com.epam.javalab.webapp.user.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Named
@Stateful
@SessionScoped
public class SessionBean implements Serializable {

    private Locale locale;

    private User user;

    @RequestScoped
    private String repeatPassword;

    @Inject
    private List<String> languages;



    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
        user = new User();
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void clear() {
        setUser(null);
        setLanguage(null);
    }
}