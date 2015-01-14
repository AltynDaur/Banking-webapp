package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
//import com.epam.javalab.webapp.dao.h2Impl.H2UserDAO;
import com.epam.javalab.webapp.dao.JPAImpl.JPAUserDAO;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.service.UserService;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller/login")
public class LoginAction extends HttpServlet {

//    @Inject
//    private JPAUserDAO userDAO;

    @PersistenceContext(unitName = "Banking")
    private EntityManager em;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String password = EncryptByMD5.encrypt(req.getParameter("password"), firstName);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> currentUser = criteriaQuery.from(User.class);
        criteriaQuery.select(currentUser).where(cb.equal(currentUser.get("name"), firstName)).where(cb.equal(currentUser.get("password"), password));
        User user = em.createQuery(criteriaQuery).getSingleResult();

        if (currentUser != null) {
            req.getSession().setAttribute("user", currentUser);
            Role currentRole = user.getRole();
            if (currentRole.equals(Role.ADMIN)) {
                res.sendRedirect("admin/adminMainPage");

            } else if (currentRole.equals(Role.CLIENT)) {
                res.sendRedirect("client/clientMainPage");
            }

        } else {
            req.setAttribute("errorMessage", "ERROR!!!");
            req.getRequestDispatcher("/WEB-INF/jsp/loginPage.jsp").forward(req, res);

        }
    }


}
