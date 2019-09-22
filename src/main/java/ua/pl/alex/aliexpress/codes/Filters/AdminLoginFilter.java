package ua.pl.alex.aliexpress.codes.Filters;

import ua.pl.alex.aliexpress.codes.Entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aleksandr on 18.09.2019.
 */
@WebFilter(filterName = "AdminLoginFilter", urlPatterns = "/admin-panel/statistic/*")
public class AdminLoginFilter extends BaseHttpFilter {
    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        User user = (User) servletRequest.getSession().getAttribute("userByLogin");
        if (user != null && user.getPermissions().equals("admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.getSession().setAttribute("url", servletRequest.getRequestURI());
            servletRequest.getSession().setAttribute("message", "Проверьте правильность введения логина и пароля");
            servletResponse.sendRedirect(servletRequest.getContextPath() + "/pages/admin/login.jsp");
        }
    }
}
