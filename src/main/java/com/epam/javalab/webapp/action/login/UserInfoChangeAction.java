package com.epam.javalab.webapp.action.login;

import com.epam.javalab.webapp.action.Action;
import com.epam.javalab.webapp.action.ActionResult;
import com.epam.javalab.webapp.dao.H2UserDAO;
import com.epam.javalab.webapp.security.EncryptByMD5;
import com.epam.javalab.webapp.user.Role;
import com.epam.javalab.webapp.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoChangeAction implements Action {

    public com.epam.javalab.webapp.action.ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        EncryptByMD5 security = new EncryptByMD5();
        ActionResult result = new ActionResult();
        String firstName = req.getParameter("firstName");
        User currentUser = (User) req.getSession().getAttribute("user");
        String password = null;
        if(req.getParameter("password").isEmpty()){
            password = EncryptByMD5.encrypt(req.getParameter("oldPass"),firstName);
        } else {
            if(req.getParameter("password").equals(req.getParameter("repeatPassword"))){
                password = EncryptByMD5.encrypt(req.getParameter("password"),firstName);
            }   else {
                result.setPath("userChangeInfoPage");
                return result;
            }

        }


        String email = req.getParameter("email");
        String oldPass = EncryptByMD5.encrypt(req.getParameter("oldPass"),firstName);

        Role role = currentUser.getRole();
        int userID = currentUser.getId();
        if (currentUser.getPassword().equals(oldPass)) {
            H2UserDAO h2UserDAO = new H2UserDAO();
            h2UserDAO.update(firstName, password, email, role, userID);
            req.setAttribute("message", "updateSuccess");
            result.setRedirect(true);
            chooseRole(role, result);
        } else {
            req.setAttribute("message", "errorChange");
            chooseRole(role, result);
        }
        return result;
    }

    private void chooseRole(Role role, ActionResult result) {
        if (role.equals(Role.ADMIN)) {
            result.setPath("admin/adminMainPage");
        } else {
            result.setPath("client/clientMainPage");
        }

    }
}
