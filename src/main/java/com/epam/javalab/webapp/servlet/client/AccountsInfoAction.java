package com.epam.javalab.webapp.servlet.client;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.user.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/client/accountsInfo")
public class AccountsInfoAction extends HttpServlet {

    @Inject
    @JPA
    private AccountDAO accountDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        int currentID = currentUser.getId();
        List<Account> currentAccList = accountDAO.findAllByUserID(currentID);
        req.setAttribute("accList", currentAccList);
        req.getRequestDispatcher("/WEB-INF/jsp/client/clientMainPage.jsp").forward(req, resp);
    }

}
