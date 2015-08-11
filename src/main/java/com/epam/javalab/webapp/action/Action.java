package com.epam.javalab.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public interface Action {
    ActionResult execute(HttpServletRequest request,HttpServletResponse response);
}
