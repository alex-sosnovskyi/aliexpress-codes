package ua.pl.alex.aliexpress.codes.Controllers;

import ua.pl.alex.aliexpress.codes.Entity.Coupon;
import ua.pl.alex.aliexpress.codes.service.impl.CouponAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Aleksandr on 20.09.2019.
 */
@WebServlet(name = "DeleteController", urlPatterns = "/admin-panel/statistic/delete")
public class DeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession(false);
        if(null!=session){
            String id = req.getParameter("id");
            if(id.isEmpty()){
                resp.sendRedirect(req.getContextPath()+"/admin-panel/statistic");
            }else{
                CouponAdminServiceImpl.getInstance().removeById(id.trim());
              //  List<Coupon> all = CouponAdminServiceImpl.getInstance().getAll();
              //  req.setAttribute("coupons", all);
               // req.getRequestDispatcher("/pages/admin/statistic.jsp").forward(req, resp);
                resp.sendRedirect(req.getContextPath()+"/admin-panel/statistic");
            }
        }else {
            resp.sendRedirect(req.getContextPath() + "/pages/admin/login.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }
}
