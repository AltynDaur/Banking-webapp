package com.epam.javalab.webapp.action;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public interface Action {

    abstract ActionResult execute(HttpServletRequest req, HttpServletResponse resp);
}
