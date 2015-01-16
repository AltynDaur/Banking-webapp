package com.epam.javalab.webapp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

    abstract ActionResult execute(HttpServletRequest req, HttpServletResponse resp);
}
