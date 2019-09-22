package ua.pl.alex.aliexpress.codes.Controllers;

import ua.pl.alex.aliexpress.codes.Entity.Coupon;
import ua.pl.alex.aliexpress.codes.service.impl.CouponAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Aleksandr on 20.09.2019.
 */
@WebServlet(name = "StatisticController", urlPatterns = "/admin-panel/statistic")
public class StatisticController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        List<Coupon> couponsAll = CouponAdminServiceImpl.getInstance().getAll();
        request.setAttribute("coupons", couponsAll);
        request.getRequestDispatcher("/pages/admin/statistic.jsp").forward(request, response);
    }
}
