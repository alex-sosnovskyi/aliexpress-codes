package ua.pl.alex.aliexpress.codes.Controllers;

import ua.pl.alex.aliexpress.codes.Entity.User;
import ua.pl.alex.aliexpress.codes.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Aleksandr on 18.09.2019.
 */
@WebServlet(name = "WellcomeAdminController", urlPatterns = "/admin-panel")
public class WellcomeAdminController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("admin");
        String login = "", password = "";
        login = request.getParameter("login");
        password = request.getParameter("password");
        if (login.isEmpty() || password.isEmpty()) {
            request.getRequestDispatcher("/pages/admin/login.jsp").forward(request, response);
        } else {
            String trimedLogin = login.trim();
            String trimedPassword = password.trim();
            if (trimedLogin.length() == 0 || trimedLogin.length() > 20 || trimedPassword.length() == 0 || trimedPassword.length() > 20) {
                request.getRequestDispatcher("/pages/admin/login.jsp").forward(request, response);
            } else {
                List<User> usersByLogin = UserServiceImpl.getInstance().getByLogin(trimedLogin);
                if (usersByLogin.size() == 0) {
                    request.setAttribute("message", "Wrong user name or password");
                    response.sendRedirect(request.getContextPath() + "/pages/admin/login.jsp");
                } else {
                    if (!usersByLogin.get(0).getPassword().equals(trimedPassword)) {
                        request.setAttribute("message", "Wrong user name or password");
                        response.sendRedirect(request.getContextPath() + "/pages/admin/login.jsp");
                    } else {
                        request.getSession().setAttribute("userByLogin", usersByLogin.get(0));
                        response.sendRedirect(request.getContextPath()+"/admin-panel/statistic");
                    }
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/pages/admin/login.jsp").forward(request, response);
    }
}
