package com.epam.javalab.webapp.action.admin.accountType;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2AccountTypeDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AccountTypesListAction implements Action {
    @Override
    public com.epam.javalab.webapp.action.ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        H2AccountTypeDAO h2AccountTypeDAO = new H2AccountTypeDAO();
        List accountTypes = h2AccountTypeDAO.findAll();
        req.setAttribute("accTypeList", accountTypes);
        req.setAttribute("usersList",null);
        req.setAttribute("accList",null);
        ActionResult result = new ActionResult("admin/adminMainPage");
        return result;
    }
}
