package com.epam.javalab.webapp.action.admin;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminMainPageAction implements Action {
    @Override
    public com.epam.javalab.webapp.action.ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        return new ActionResult("admin/adminMainPage");
    }
}
