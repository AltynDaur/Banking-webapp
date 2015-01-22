package com.epam.javalab.webapp.util;

import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

public class Resources {
    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext(unitName = "Banking")
    private EntityManager em;

    @Produces
    public Logger getLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
    public List<String> languages() {
        List<String> languages = new ArrayList<>();
        languages.add("ru");
        languages.add("en");
        return languages;
    }

    ;

}
