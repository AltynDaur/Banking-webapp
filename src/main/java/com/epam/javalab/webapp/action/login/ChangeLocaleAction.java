package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocaleAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ChangeLocaleAction.class);


    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("language: " + req.getParameter("language"));
        req.getSession().setAttribute("language", req.getParameter("language"));
        String path = req.getHeader("Referer").substring(41);
        ActionResult result = new ActionResult(path, true);
        return result;
    }
}
