package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
//import com.epam.javalab.webapp.dao.H2UserDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.service.UserService;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {

    @Override
    public com.epam.javalab.webapp.action.ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        EncryptByMD5 encryptByDES = new EncryptByMD5();
        String firstName = req.getParameter("firstName");
        String password = EncryptByMD5.encrypt(req.getParameter("password"), firstName);
        UserService service = new UserService();
        User currentUser = (User) service.findByName(firstName, password);

        //H2UserDAO h2UserDAO = new H2UserDAO();
        //User currentUser = h2UserDAO.findUser(firstName,password);
        ActionResult result = new ActionResult();

        if(currentUser!=null){
            req.getSession().setAttribute("user", currentUser);
            Role currentRole = currentUser.getRole();
            if(currentRole.equals(Role.ADMIN)){
                result.setPath("admin/adminMainPage");
                result.setRedirect(true);

            } else if(currentRole.equals(Role.CLIENT)){
                result.setPath("client/clientMainPage");
                result.setRedirect(true);

            }

        } else {
            req.setAttribute("errorMessage","ERROR!!!");
            result.setPath("loginPage");
        }
        return result;
    }
}
