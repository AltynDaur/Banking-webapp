package com.epam.javalab.webapp.servlet.client;

import com.epam.javalab.webapp.account.AccountType;
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

@WebServlet("/client/accountsTypeInfo")
public class AccountsTypeInfoAction extends HttpServlet {

    @Inject
    @JPA
    private AccountTypeDAO accountTypeDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<AccountType> accountTypeList = accountTypeDAO.findAll();
            req.setAttribute("accTypeList", accountTypeList);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/jsp/client/clientMainPage.jsp").forward(req, resp);
    }

}
