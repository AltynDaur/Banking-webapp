package com.epam.javalab.webapp.action.admin.accountType;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.exception.DAOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/accountTypes")
public class AccountTypesListAction extends HttpServlet {

    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List accountTypes = null;
        try {
            accountTypes = accountTypeDAO.findAll();
        } catch (DAOException e) {
            req.setAttribute("message", "Database problems");
            resp.sendRedirect("admin/adminMainPage");
        }
        req.setAttribute("accTypeList", accountTypes);
        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminMain").forward(req, resp);
    }


}
