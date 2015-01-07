package com.epam.javalab.webapp.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        switch (statusCode){
            case 404:
                req.setAttribute("errCode",statusCode);
                req.setAttribute("errorMessage","err404");
                break;
            case 401:
                req.setAttribute("errCode",statusCode);
                req.setAttribute("errorMessage","err401");
                break;
            case 403:
                req.setAttribute("errCode",statusCode);
                req.setAttribute("errorMessage","err403");
                break;
            case 500:
                req.setAttribute("errCode",statusCode);
                req.setAttribute("errorMessage","err500");
                break;
            default:
                req.setAttribute("errCode",statusCode);
                req.setAttribute("errorMessage","defaultError");

        }
        req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req,resp);
    }


}
