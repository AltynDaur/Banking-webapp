package com.epam.javalab.webapp.filter;

import com.epam.javalab.webapp.data.SessionBean;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;
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

@WebFilter("/client/*")
public class ClientFilter implements Filter {

    @Inject
    private Logger LOOGER;

    @Inject
    private SessionBean sessionBean;

    @Inject
    private FacesContext facesContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOOGER.debug("Client filter starting");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (sessionBean.getUser().getRole().equals(Role.CLIENT)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            FacesMessage message = new FacesMessage("You're not client");
            facesContext.addMessage(null, message);
        }
    }

    @Override
    public void destroy() {

    }
}
