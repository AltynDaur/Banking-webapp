package com.epam.javalab.webapp.controller;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionFactory;
import com.epam.javalab.webapp.action.ActionResult;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    ActionFactory actionFactory = new ActionFactory();
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String actionName = getActionName(req);
        LOGGER.debug(actionName);
        Action currentAction = actionFactory.getAction(actionName);
        ActionResult result = currentAction.execute(req, resp);

        try {
            if (result.isRedirect()) {
                String finalPath = req.getContextPath() + req.getServletPath() + "/" + result.getPath();
                resp.sendRedirect(finalPath);
            } else {
                req.getRequestDispatcher("/WEB-INF/jsp/" + result.getPath() + ".jsp").forward(req, resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getActionName(HttpServletRequest req) {
        String actionName = req.getPathInfo().substring(1);
        return actionName;
    }


}
