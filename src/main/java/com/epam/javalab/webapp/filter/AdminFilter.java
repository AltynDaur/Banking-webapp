package com.epam.javalab.webapp.filter;

import com.epam.javalab.webapp.data.SessionBean;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter  implements Filter{
    @Inject
    private Logger LOGGER;

    @Inject
    private SessionBean sessionBean;

    @Inject
    private FacesContext facesContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Starting Admin Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (sessionBean.getUser().getRole().equals(Role.ADMIN)) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            FacesMessage message = new FacesMessage("You're not admin");
            facesContext.addMessage(null, message);
        }
    }

    @Override
    public void destroy() {

    }
}
