package ua.pl.alex.aliexpress.codes.Controllers;

import ua.pl.alex.aliexpress.codes.Entity.Coupon;
import ua.pl.alex.aliexpress.codes.service.impl.CouponAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Aleksandr on 21.09.2019.
 */
@WebServlet(name = "AddController", urlPatterns = "/admin-panel/statistic/add")
public class AddController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        req.getRequestDispatcher("/pages/admin/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String name = "";
        name = req.getParameter("name");
        if(name.isEmpty()){
            req.getRequestDispatcher("/pages/admin/add.jsp").forward(req, resp);
        }
        String description = "";
        description= req.getParameter("description");
        if(description.isEmpty()){
            req.getRequestDispatcher("/pages/admin/add.jsp").forward(req, resp);
        }
        String code = "";
                code= req.getParameter("code");
        String link = "";
        link = req.getParameter("link");
        if(link.isEmpty()){
            req.getRequestDispatcher("/pages/admin/add.jsp").forward(req, resp);
        }
        String region = "";
        region = req.getParameter("region");
        if(region.isEmpty()){
            req.getRequestDispatcher("/pages/admin/add.jsp").forward(req, resp);
        }
        String dayOn = "";
        dayOn = req.getParameter("dayOn");
        if(dayOn.isEmpty()){
            req.getRequestDispatcher("/pages/admin/add.jsp").forward(req, resp);
        }
        String dayOf = "";
        dayOf = req.getParameter("dayOf");
        if(dayOf.isEmpty()){
            req.getRequestDispatcher("/pages/admin/add.jsp").forward(req, resp);
        }
        String rating = "";
        rating = req.getParameter("rating");
        if(rating.isEmpty()){
            req.getRequestDispatcher("/pages/admin/add.jsp").forward(req, resp);
        }
        LocalDate.parse(dayOn).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOn;
        Date dateOf;
        try {
            dateOn = format.parse(dayOn);
            dateOf = format.parse(dayOf);
            Coupon couponToAdd = new Coupon(name, description, code, link, region, dateOn, dateOf);
            couponToAdd.setRating(Integer.valueOf(rating));
            CouponAdminServiceImpl.getInstance().add(couponToAdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath()+"/admin-panel/statistic");
    }
}
