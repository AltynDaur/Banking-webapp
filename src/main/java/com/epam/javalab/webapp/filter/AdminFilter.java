package com.epam.javalab.webapp.filter;

import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.apache.log4j.Logger;

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
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Starting Admin Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser.getRole().equals(Role.ADMIN)) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            request.setAttribute("error", "YOU SHALL NOT PASS!!!");
            response.sendRedirect(request.getContextPath() + request.getServletContext() + "/loginPage");
        }
    }

    @Override
    public void destroy() {

    }
}
